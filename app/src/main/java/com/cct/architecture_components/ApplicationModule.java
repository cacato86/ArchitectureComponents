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

package com.cct.architecture_components;

import android.app.Application;
import android.content.Context;

import com.cct.architecture_components.data.Repository;
import com.cct.architecture_components.data.rest.ApiClient;
import com.cct.architecture_components.data.rest.utils.OkHttpClientFactory;
import com.cct.architecture_components.data.RepositoryImpl;
import com.cct.architecture_components.data.rest.utils.LoggingInterceptorFactory;
import com.cct.architecture_components.data.rest.utils.QueryInterceptorFactory;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Dagger module that provides objects which will live during the application lifecycle.
 */
@Module
public class ApplicationModule {

    private final Application application;

    /**
     * Instantiates a new Application module.
     *
     * @param application the application
     */
    public ApplicationModule(Application application) {
        this.application = application;
    }

    /**
     * Provide application context context.
     *
     * @return the context
     */
    @Provides
    @Singleton
    Context provideApplicationContext() {
        return this.application;
    }

    /**
     * Provide query interceptor factory query interceptor factory.
     *
     * @return the query interceptor factory
     */
    @Provides
    @Singleton
    QueryInterceptorFactory provideQueryInterceptorFactory() {
        return new QueryInterceptorFactory();
    }

    /**
     * Provide logging interceptor factory logging interceptor factory.
     *
     * @return the logging interceptor factory
     */
    @Provides
    @Singleton
    LoggingInterceptorFactory provideLoggingInterceptorFactory() {
        return new LoggingInterceptorFactory();
    }

    /**
     * Provide okhttp client ok http client factory.
     *
     * @param queryInterceptor   the query interceptor
     * @param loggingInterceptor the logging interceptor
     * @return the ok http client factory
     */
    @Provides
    @Singleton
    OkHttpClientFactory provideOkhttpClient(QueryInterceptorFactory queryInterceptor,
                                            LoggingInterceptorFactory loggingInterceptor) {
        return new OkHttpClientFactory(loggingInterceptor.getLoggingInterceptor(),
                queryInterceptor.getQueryInterceptor());
    }

    @Provides
    @Singleton
    ApiClient provideApiClient(OkHttpClientFactory okHttpClientFactory) {
        return new ApiClient(okHttpClientFactory.getOkhttpClient());
    }

    @Provides
    @Singleton
    Repository provideRepository(ApiClient apiClient) {
        return new RepositoryImpl(apiClient);
    }


    @Provides
    @Named("subscriber")
    @Singleton
    Scheduler provideSubscriberScheduler() {
        return Schedulers.io();
    }

    @Provides
    @Named("observer")
    @Singleton
    Scheduler provideObserverScheduler() {
        return AndroidSchedulers.mainThread();
    }



}
