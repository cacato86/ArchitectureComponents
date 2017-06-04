
package com.cct.architecture_components.presentation.popularmovies;

import com.cct.architecture_components.PerActivityScope;
import com.cct.architecture_components.common.router.Router;
import com.cct.architecture_components.common.router.RouterModule;

import dagger.Subcomponent;

@PerActivityScope
@Subcomponent(modules = { RouterModule.class} )
public interface PopularMoviesComponent {

    Router router();

    void inject(PopularMoviesActivity popularMoviesActivity);
}
