package com.walmart.openapi.guice;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MappingJsonFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.AbstractModule;
import com.google.inject.Inject;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.name.Names;
import com.ning.http.client.AsyncHttpClient;

public class Dependencies extends AbstractModule {

    @Override
    protected void configure() {
        bindConstant().annotatedWith(
                Names.named("apiendpoint")).
                to("http://api.walmartlabs.com/v1");
    }

    @Provides
    @Singleton
    @Inject
    AsyncHttpClient providesAsyncHttpClient() {
        return new AsyncHttpClient();
    }

    @Provides
    @Singleton
    @Inject
    ObjectMapper providesObjectMapper() {
        return new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    @Provides
    @Singleton
    @Inject
    MappingJsonFactory providesMappingJsonFactory(ObjectMapper objectMapper) {
        return new MappingJsonFactory(objectMapper);
    }



}
