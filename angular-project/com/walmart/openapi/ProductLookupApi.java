package com.walmart.openapi;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Function;
import com.ning.http.client.AsyncHttpClient;
import com.ning.http.client.Response;
import com.walmart.openapi.guice.GuiceInjector;
import com.walmart.openapi.responses.Item;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.util.concurrent.Futures.lazyTransform;
import static com.walmart.openapi.ApiConstants.*;

/**
 * Make calls to the product lookup API.
 *
 * Useful to fetch the latest price and availability of Walmart.com items.
 */
public class ProductLookupApi {

    private Logger log = LoggerFactory.getLogger(ProductLookupApi.class);

    private final String ITEMS = "/items/%s";

    private String feedsUrl;
    private ObjectMapper objectMapper;
    private AsyncHttpClient asyncHttpClient;
    private String lsPublisherId;
    private String apiKey;

    /**
     *
     * @param apiKey - Can be obtained by registering on developer.walmartlabs.com
     * @param lsPublisherId - By providing your publisher id you can get paid links
     *                      associated with your linkshare account. You can register
     *                      at affiliates.walmart.com to create your linkshare account.
     */
    public ProductLookupApi(String apiKey, String lsPublisherId) {
        this.apiKey = apiKey;
        this.lsPublisherId = lsPublisherId;
        this.asyncHttpClient = GuiceInjector.getInstance(AsyncHttpClient.class);
        this.objectMapper = GuiceInjector.getInstance(ObjectMapper.class);
        this.feedsUrl = GuiceInjector.getNamedInstance(String.class, API_END_POINT.getValue()) + ITEMS;
    }

    /**
     *
     * @param apiKey - Can be obtained by registering on developer.walmartlabs.com
     */
    public ProductLookupApi(String apiKey) {
        this(apiKey, DEFAULT_PUBLISHER_ID.getValue());
    }

    /**
     * Call this method to get details about an item in Walmart catalog.
     * @param itemId id of the item to be fetched.
     * @return Returns an json with all the details about the item requested.
     * @throws java.util.concurrent.ExecutionException
     * @throws InterruptedException
     * @throws java.io.IOException
     */
    public Item getItem(int itemId) throws ExecutionException, InterruptedException, IOException {
        Future<Response> f = getResponseFuture(itemId);
        Response r = f.get();
        return objectMapper.readValue(r.getResponseBody(), Item.class);
    }

    private Future<Response> getResponseFuture(int itemId) throws IOException {
        checkArgument(itemId > 0, "Invalid item id");
        Future<Response> f;
        String itemEndPoint = String.format(feedsUrl, itemId);
        f = asyncHttpClient.prepareGet(itemEndPoint)
                .addQueryParameter(API_KEY.getValue(), apiKey)
                .addQueryParameter(FORMAT.getValue(), JSON_FORMAT.getValue())
                .addQueryParameter(LS_PUBLISHER_ID.getValue(), lsPublisherId)
                .execute();
        return f;
    }

    /**
     * Call this method to get details about an item in Walmart catalog.
     * @param itemId id of the item to be fetched.
     * @return Returns an json with all the details about the item requested.
     * @throws java.util.concurrent.ExecutionException
     * @throws InterruptedException
     * @throws java.io.IOException
     */
    public Future<Item> getItemFuture(int itemId) throws ExecutionException, InterruptedException, IOException, NullPointerException {
        Future<Response> f = getResponseFuture(itemId);
        Function<Response, Item> itemFunction =
                new Function<Response, Item>() {
                    public Item apply(Response response) {
                        try {
                            return objectMapper.readValue(response.getResponseBody(), Item.class);
                        } catch (IOException e) {
                            log.error("Parse exception", e);
                        }
                        return null;
                    }
                };
        return lazyTransform(f, itemFunction);
    }
}

