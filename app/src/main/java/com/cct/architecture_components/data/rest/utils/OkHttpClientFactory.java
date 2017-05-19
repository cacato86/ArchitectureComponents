
package com.cct.architecture_components.data.rest.utils;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;

/**
 * The type Ok http client factory.
 */
public class OkHttpClientFactory {
    private final Interceptor loggingInterceptor;
    private final Interceptor queryInterceptor;
    private final Interceptor requestInterceptor;

    /**
     * Instantiates a new Ok http client factory.
     *
     * @param logging the logging
     * @param query   the query
     */
    public OkHttpClientFactory(Interceptor logging, Interceptor query) {
        this.loggingInterceptor = logging;
        this.queryInterceptor = query;
        this.requestInterceptor = null;
    }

    /**
     * Instantiates a new Ok http client factory.
     *
     * @param logging the logging
     * @param query   the query
     */
    public OkHttpClientFactory(Interceptor logging, Interceptor query, Interceptor request) {
        this.loggingInterceptor = logging;
        this.queryInterceptor = query;
        this.requestInterceptor = request;
    }

    /**
     * Gets okhttp client.
     *
     * @return the okhttp client
     */
    public OkHttpClient getOkhttpClient() {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        if (requestInterceptor != null) {
            httpClient.addNetworkInterceptor(requestInterceptor);
        }
        httpClient.addInterceptor(loggingInterceptor);
        httpClient.addInterceptor(queryInterceptor);
        httpClient.retryOnConnectionFailure(true);
        return httpClient.build();
    }
}
