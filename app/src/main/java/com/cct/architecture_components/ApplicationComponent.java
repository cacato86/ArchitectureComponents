
package com.cct.architecture_components;

import com.cct.architecture_components.common.router.RouterModule;
import com.cct.architecture_components.presentation.popularmovies.PopularMoviesComponent;
import com.cct.architecture_components.presentation.search.SearchActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
    void inject(SearchActivity searchActivity);

    PopularMoviesComponent newPopularMoviesComponent(RouterModule routerModule);
}
