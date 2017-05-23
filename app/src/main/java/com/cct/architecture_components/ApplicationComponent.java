
package com.cct.architecture_components;

import android.content.Context;

import com.cct.architecture_components.bussines.viewmodel.ViewModelComponent;
import com.cct.architecture_components.bussines.viewmodel.ViewModelModule;
import com.cct.architecture_components.common.router.RouterModule;
import com.cct.architecture_components.data.Repository;
import com.cct.architecture_components.data.rest.utils.LoggingInterceptorFactory;
import com.cct.architecture_components.data.rest.utils.OkHttpClientFactory;
import com.cct.architecture_components.data.rest.utils.QueryInterceptorFactory;
import com.cct.architecture_components.presentation.popularmovies.PopularMoviesComponent;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Component;
import io.reactivex.Scheduler;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    Context context();

    LoggingInterceptorFactory loginInterceptor();

    QueryInterceptorFactory queryInterceptor();

    OkHttpClientFactory okHttp();

    Repository repository();

    @Named("subscriber")
    Scheduler subscriber();

    @Named("observer")
    Scheduler observer();

    PopularMoviesComponent newPopularMoviesComponent(ViewModelModule viewModelModule, RouterModule routerModule);

    ViewModelComponent newViewModelComponent(ViewModelModule viewModelModule);
}
