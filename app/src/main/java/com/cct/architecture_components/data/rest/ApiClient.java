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

package com.cct.architecture_components.data.rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * The type Api client.
 */
public class ApiClient {
    private static final String URL_BASE = "https://api.themoviedb.org";

    private final OkHttpClient okHttpClient;
    private Api client;

    /**
     * Instantiates a new Api client.
     *
     * @param okHttpClient the ok http client
     */
    public ApiClient(OkHttpClient okHttpClient) {
        this.okHttpClient = okHttpClient;
    }

    /**
     * Gets rest adapter.
     *
     * @return the rest adapter
     */
    public Api getRestAdapter() {
        if (client == null) {

            Retrofit retrofit = new Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(getGsonConverter())
                    .baseUrl(URL_BASE)
                    .client(okHttpClient)
                    .build();

            client = retrofit.create(Api.class);
        }
        return client;
    }

    /**
     * Gets the gson converter used to parse API responses.
     */
    private GsonConverterFactory getGsonConverter() {
        Gson gson = new GsonBuilder()
                .create();
        return GsonConverterFactory.create(gson);
    }
}
