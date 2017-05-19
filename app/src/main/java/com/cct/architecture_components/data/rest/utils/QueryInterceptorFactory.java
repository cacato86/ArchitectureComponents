
package com.cct.architecture_components.data.rest.utils;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;

public class QueryInterceptorFactory {

    private static final String APIKEY = "93aea0c77bc168d8bbce3918cefefa45";

    public Interceptor getQueryInterceptor() {
        return chain -> {

            Request original = chain.request();
            HttpUrl originalHttpUrl = original.url();

            HttpUrl url = originalHttpUrl.newBuilder()
                    .addQueryParameter("api_key", APIKEY)
                    .build();

            Request.Builder requestBuilder = original.newBuilder()
                    .url(url);

            Request request = requestBuilder.build();
            return chain.proceed(request);
        };
    }

}
