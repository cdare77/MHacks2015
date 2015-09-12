package com.walmart.openapi;

import com.walmart.openapi.responses.Item;
import junit.framework.TestCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

public class ProductLookupApiTest extends TestCase {

    ProductLookupApi rq;
    Logger log = LoggerFactory.getLogger(ProductLookupApiTest.class);

    @Override
    public void setUp() throws IOException {
        FileInputStream input = new FileInputStream("config.properties");
        Properties prop = new Properties();
        prop.load(input);
        String apiKey = prop.getProperty("apikey");
        String lsPublisherId = prop.getProperty("lspublisherid");
        rq = new ProductLookupApi(apiKey, lsPublisherId);
    }

    /**
     * Test item lookup.
     * @throws IOException
     * @throws java.util.concurrent.ExecutionException
     * @throws InterruptedException
     */
    public void test_item_lookup() throws IOException, ExecutionException, InterruptedException {
        Item item = rq.getItem(22144351);
        log.debug("Id : " +  item.getItemId());
        log.debug("Description : " + item.getName());
        log.debug("Price : " + item.getSalePrice());
    }

}