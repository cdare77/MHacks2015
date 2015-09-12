package com.walmart.openapi;

public enum ApiConstants {

    API_KEY("apiKey"),
    API_END_POINT("apiendpoint"),
    ENTIRE_FEED(""),
    DEFAULT_PUBLISHER_ID(""),
    FORMAT("format"),
    JSON_FORMAT("json"),
    LS_PUBLISHER_ID("lsPublisherId"),
    QUERY("query");

    private final String value;

    ApiConstants(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
