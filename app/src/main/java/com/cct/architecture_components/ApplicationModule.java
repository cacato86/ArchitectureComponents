
package com.cct.architecture_components;

import android.app.Application;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.persistence.room.Room;
import android.content.Context;

import com.cct.architecture_components.bussines.viewmodel.FactoryViewModel;
import com.cct.architecture_components.bussines.viewmodel.ViewModelSubComponent;
import com.cct.architecture_components.data.Repository;
import com.cct.architecture_components.data.RepositoryImpl;
import com.cct.architecture_components.data.db.DataBase;
import com.cct.architecture_components.data.rest.ApiClient;
import com.cct.architecture_components.data.rest.utils.LoggingInterceptorFactory;
import com.cct.architecture_components.data.rest.utils.OkHttpClientFactory;
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
@Module(subcomponents = ViewModelSubComponent.class)
public class ApplicationModule {

    public static final String DATABASE_NAME = "database.db";
    private final Application application;

    public ApplicationModule(Application application) {
        this.application = application;
    }

    @Provides
    @Singleton
    Context provideApplicationContext() {
        return this.application;
    }

    @Provides
    @Singleton
    QueryInterceptorFactory provideQueryInterceptorFactory() {
        return new QueryInterceptorFactory();
    }

    @Provides
    @Singleton
    LoggingInterceptorFactory provideLoggingInterceptorFactory() {
        return new LoggingInterceptorFactory();
    }

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
    DataBase provideDb() {
        return Room.databaseBuilder(application, DataBase.class, DATABASE_NAME).build();
    }

    @Provides
    @Singleton
    Repository provideRepository(ApiClient apiClient, DataBase dataBase) {
        return new RepositoryImpl(apiClient, dataBase);
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

    @Singleton
    @Provides
    ViewModelProvider.Factory provideViewModelFactory(
            ViewModelSubComponent.Builder viewModelSubComponent) {
        return new FactoryViewModel(viewModelSubComponent.build());
    }

}
