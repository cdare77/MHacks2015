package com.walmart.openapi;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Function;
import com.ning.http.client.AsyncHttpClient;
import com.ning.http.client.Response;
import com.walmart.openapi.guice.GuiceInjector;
import com.walmart.openapi.responses.SearchResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.util.concurrent.Futures.lazyTransform;
import static com.walmart.openapi.ApiConstants.*;

/**
 * Make calls to the search API.
 */
public class SearchApi {

    private Logger log = LoggerFactory.getLogger(ProductLookupApi.class);

    private final String SEARCH = "/search";

    private String apiKey;
    private String lsPublisherId;
    private AsyncHttpClient asyncHttpClient;
    private ObjectMapper objectMapper;
    private String feedsUrl;

    /**
     *
     * @param apiKey - Can be obtained by registering on developer.walmartlabs.com
     * @param lsPublisherId - By providing your publisher id you can get paid links
     *                      associated with your linkshare account. You can register
     *                      at affiliates.walmart.com to create your linkshare account.
     */
    public SearchApi(String apiKey, String lsPublisherId) {
        this.apiKey = apiKey;
        this.lsPublisherId = lsPublisherId;
        this.asyncHttpClient = GuiceInjector.getInstance(AsyncHttpClient.class);
        this.objectMapper = GuiceInjector.getInstance(ObjectMapper.class);
        this.feedsUrl = GuiceInjector.getNamedInstance(String.class, API_END_POINT.getValue()) + SEARCH;
    }

    /**
     *
     * @param apiKey - Can be obtained by registering on developer.walmartlabs.com
     */
    public SearchApi(String apiKey) {
        this(apiKey, DEFAULT_PUBLISHER_ID.getValue());
    }

    /*
     * Call this method to get search response for a query string.
     * @param queryString Search query for which items are requested.
     * @return Returns the search response for the given search query.
     * @throws IOException
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public SearchResponse getSearchResponse(String queryString) throws IOException, ExecutionException, InterruptedException {
        Future<Response> f = getResponseFuture(queryString);
        Response r = f.get();
        return objectMapper.readValue(r.getResponseBody(), SearchResponse.class);
    }

    private Future<Response> getResponseFuture(String queryString) throws IOException {
        checkArgument(null != queryString || queryString.isEmpty(), "Invalid search query string");
        Future<Response> f;

        f = asyncHttpClient.prepareGet(feedsUrl)
                .addQueryParameter(API_KEY.getValue(), apiKey)
                .addQueryParameter(FORMAT.getValue(), JSON_FORMAT.getValue())
                .addQueryParameter(LS_PUBLISHER_ID.getValue(), lsPublisherId)
                .addQueryParameter(QUERY.getValue(), queryString)
                .execute();
        return f;
    }

    /*
     * Call this method to get search response for a query string.
     * @param queryString Search query for which items are requested.
     * @return Returns the search response for the given search query.
     * @throws IOException
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public Future<SearchResponse> getSearchResponseFuture(String queryString) throws IOException, ExecutionException, InterruptedException {
        Future<Response> f = getResponseFuture(queryString);

        Function<Response, SearchResponse> itemFunction =

                new Function<Response, SearchResponse>() {
                    public SearchResponse apply(Response response) {
                        try {
                            return objectMapper.readValue(response.getResponseBody(), SearchResponse.class);
                        } catch (IOException e) {
                            log.error("Parse exception", e);
                        }
                        return null;
                    }
                };
        return lazyTransform(f, itemFunction);
    }


}
