package com.walmart.openapi;

import com.walmart.openapi.exceptions.FeedParseException;
import com.walmart.openapi.guice.GuiceInjector;
import com.walmart.openapi.responses.Item;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.MappingJsonFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.*;
import java.util.NoSuchElementException;
import java.util.zip.GZIPInputStream;

import static com.google.common.base.Preconditions.checkArgument;
import static com.walmart.openapi.ApiConstants.ENTIRE_FEED;
import static com.walmart.openapi.ApiConstants.API_END_POINT;

/**
 * Feed manager lets you iterate over the items in data feeds. We
 * choose the Iterator pattern because the category feeds/full feeds
 * for some of the categories are really huge(>1GB)
 * and cannot be loaded in memory for analysis.
 *
 * Note : It does not cache the result locally i.e on successive
 * runs it makes a network call to download the feeds again
 * from the open api end points.
 */
public class DataFeedManager {

    private final String FEEDS = "/feeds";

    private String apiKey;
    private String feedsUrl;
    private MappingJsonFactory mappingJsonFactory;
    private Logger log = LoggerFactory.getLogger(DataFeedManager.class);

    public enum FeedsType {
        PREORDER, BESTSELLERS, SPECIALBUY, ROLLBACK, CLEARANCE, ITEMS
    }

    public DataFeedManager(String apiKey) {
        this.apiKey = apiKey;
        this.feedsUrl = GuiceInjector.getNamedInstance(String.class, API_END_POINT.getValue()) + FEEDS;
        this.mappingJsonFactory = GuiceInjector.getInstance(MappingJsonFactory.class);
    }

    /**
     * Reads a JsonParser to extract a list of items and then closes the socket.
     */
    public static class FeedIterator {

        private final JsonParser jsonParser;
        private JsonToken nextJsonToken;

        public FeedIterator(JsonParser jsonParser) throws IOException, FeedParseException {
            this.jsonParser = jsonParser;
            readFeed();
        }

        /**
         * Retruns true if there is another item in the feed to be consumed else returns false.
         *
         * @return true or false
         */
        public boolean hasNext() {
            return JsonToken.END_ARRAY != nextJsonToken;
        }

        /**
         * @return Returns the next item in the feed to be consumed.
         */
        synchronized public Item next() throws IOException {
            Item item = readNextItemJson();
            if (null == item)
                throw new NoSuchElementException();
            nextJsonToken = jsonParser.nextToken();
            return item;
        }

        /**
         * Reads an object which contains items.
         * {
         *     items : [
         *      {
         *          itemid : "",
         *          ..
         *      },
         *      {
         *          itemid : "",
         *          ..
         *      },
         *      {
         *          itemid : "",
         *          ..
         *      }
         *     ]
         * }
         *
         * Warning : Does not close json parser or the stream.
         */
        private void readFeed() throws IOException, FeedParseException {
            JsonToken jsonToken = jsonParser.nextToken();
            if (jsonToken != JsonToken.START_OBJECT) throw new FeedParseException("Unexpected start object in the json stream");
            jsonParser.nextToken();
            if (!jsonParser.getCurrentName().equalsIgnoreCase("items")) throw new FeedParseException("Missing items json in the stream");
            readArray();
        }

        /**
         * Reads a list of items from the json stream.
         * [
         *  {
         *      itemid : ""
         *      ...
         *  },
         *  {
         *      itemid : ""
         *      ...
         *  },{
         *      itemid : ""
         *      ...
         *  }     *
         * ]
         *
         */
        private void readArray() throws IOException, FeedParseException {
            JsonToken jsonToken = jsonParser.nextToken();
            if (jsonToken != JsonToken.START_ARRAY) throw new FeedParseException("Expected start array in the stream");
            jsonToken = jsonParser.nextToken();
            if (jsonToken != JsonToken.START_OBJECT) throw new FeedParseException("Expected start object in the stream");
        }

        /**
         * Reads an ItemJson object from the json stream.
         * {
         *     itemid : ""
         *     ...
         *     ...
         * }
         * @return next item from the feed.
         */
        private Item readNextItemJson( ) throws IOException {
            return jsonParser.readValueAs(Item.class);
        }

        /**
         * Close the stream.
         * @throws IOException
         */
        public void close() throws IOException {
            if (!jsonParser.isClosed())
                jsonParser.close();
        }

        /**
         * If user forgets to clean up the resources, close the
         * socket.
         * @throws IOException
         */
        @Override
        public void finalize() throws Throwable {
            super.finalize();
            close();
        }
    }

    /**
     * Generate the base url to download the feed.
     * @param feedType type of feed requested. eg. bestsellers, special buy etc.
     * @return Returns the base url
     */
    private String getBaseUrl(FeedsType feedType) {
        return feedsUrl + "/" + feedType.toString().toLowerCase();
    }

    /**
     * Returns the feed url with the parameters.
     * @param feedType feedType type of feed requested. eg. bestsellers, special buy etc.
     * @param categoryId Returns the end point for the given category.
     * @return Returns the url end point of the requested feed type and category.
     */
    private String getUrl(FeedsType feedType, String categoryId) throws URISyntaxException {

        String baseUrl = getBaseUrl(feedType);
        String url = baseUrl + "?apiKey=" + apiKey;
        if ((null != categoryId) && !categoryId.isEmpty())
            url = url + "&categoryId=" + categoryId;
        return url;
    }

    /**
     * Return the iterator for the entire feed.
     * Note : Walmart catalog has millions of items.
     * @param feedsType
     * @return
     * @throws Exception
     */
    public FeedIterator getFeedIterator(FeedsType feedsType) throws Exception {
        return getFeedIterator(feedsType, ENTIRE_FEED.getValue());
    }

    /**
     * Open a connection to the server, unzip the stream and then create a JsonParser.
     * @param feedsType Type of feed eg. specialbuys, rollbacks, etc
     * @param categoryId category id for which the iterator is requested.
     * @return Returns iterator which supports hasNext() and next() methods to parse the feed.
     * @throws Exception
     */
    public FeedIterator getFeedIterator(FeedsType feedsType, final String categoryId) throws Exception {
        checkArgument(null != feedsType, "Invalid feeds type");
        checkArgument(null != categoryId, "Invalid category id");
        URL url = new URL(getUrl(feedsType, categoryId));
        HttpURLConnection http = (HttpURLConnection)url.openConnection();
        if (http.getResponseCode() == HttpURLConnection.HTTP_OK) {
            GZIPInputStream gzis;
            InputStream inputStream = http.getInputStream();
            gzis = new GZIPInputStream(inputStream);
            return new FeedIterator(mappingJsonFactory.createParser(gzis));
        } else {
            log.error("Bad response code:" + http.getResponseCode() + " for:" + http.getURL());
        }
        throw new UnknownServiceException("Invalid http response from the server.");
    }
}

