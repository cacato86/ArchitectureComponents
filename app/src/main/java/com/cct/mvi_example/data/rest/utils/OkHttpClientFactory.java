/*
 * Copyright (c) 2016 AXA Group Solutions.
 *
 * Licensed under the AXA Group Solutions License (the "License");
 * you may not use this file except in compliance with the License.
 * A copy of the License can be found in the LICENSE.TXT file distributed
 * together with this file.
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.cct.mvi_example.data.rest.utils;

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
