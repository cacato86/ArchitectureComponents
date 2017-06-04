
package com.cct.architecture_components.bussines.viewmodel;

import com.cct.architecture_components.presentation.popularmovies.PopularMoviesViewModel;
import com.cct.architecture_components.presentation.search.SearchViewModel;

import dagger.Subcomponent;

@Subcomponent
public interface ViewModelSubComponent {
    @Subcomponent.Builder
    interface Builder {
        ViewModelSubComponent build();
    }

    PopularMoviesViewModel popularMoviesVM();
    SearchViewModel searchVM();
}
