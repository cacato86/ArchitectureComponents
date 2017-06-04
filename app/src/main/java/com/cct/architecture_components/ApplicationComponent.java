
package com.cct.architecture_components;

import android.app.*;
import android.content.Context;

import com.cct.architecture_components.bussines.viewmodel.FactoryViewModel;
import com.cct.architecture_components.common.router.RouterModule;
import com.cct.architecture_components.data.Repository;
import com.cct.architecture_components.data.rest.utils.LoggingInterceptorFactory;
import com.cct.architecture_components.data.rest.utils.OkHttpClientFactory;
import com.cct.architecture_components.data.rest.utils.QueryInterceptorFactory;
import com.cct.architecture_components.presentation.popularmovies.PopularMoviesActivity;
import com.cct.architecture_components.presentation.popularmovies.PopularMoviesComponent;
import com.cct.architecture_components.presentation.search.SearchActivity;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import io.reactivex.Scheduler;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
    void inject(SearchActivity searchActivity);

    PopularMoviesComponent newPopularMoviesComponent(RouterModule routerModule);
}
