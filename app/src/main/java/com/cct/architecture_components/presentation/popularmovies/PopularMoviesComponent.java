
package com.cct.architecture_components.presentation.popularmovies;

import com.cct.architecture_components.PerActivityScope;
import com.cct.architecture_components.bussines.viewmodel.ViewModelModule;
import com.cct.architecture_components.common.router.Router;
import com.cct.architecture_components.common.router.RouterModule;
import com.cct.architecture_components.presentation.search.SearchActivity;

import dagger.Subcomponent;

@PerActivityScope
@Subcomponent(modules = {ViewModelModule.class, RouterModule.class} )
public interface PopularMoviesComponent {

    PopularMoviesViewModel popularMoviesVM();

    Router router();

    void inject(PopularMoviesActivity popularMoviesActivity);
}
