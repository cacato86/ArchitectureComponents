
package com.cct.architecture_components.bussines.viewmodel;

import com.cct.architecture_components.PerActivityScope;
import com.cct.architecture_components.presentation.popularmovies.PopularMoviesViewModel;
import com.cct.architecture_components.presentation.search.SearchActivity;
import com.cct.architecture_components.presentation.search.SearchViewModel;

import dagger.Subcomponent;

@PerActivityScope
@Subcomponent(modules = {ViewModelModule.class})
public interface ViewModelComponent {

    PopularMoviesViewModel popularMoviesVM();

    SearchViewModel searchVM();

    void inject(SearchActivity popularMoviesActivity);
}
