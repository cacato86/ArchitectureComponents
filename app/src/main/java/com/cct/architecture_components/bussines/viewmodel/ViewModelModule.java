
package com.cct.architecture_components.bussines.viewmodel;

import android.arch.lifecycle.ViewModelProvider;

import com.cct.architecture_components.PerActivityScope;
import com.cct.architecture_components.bussines.usecases.GetPopularMoviesUseCase;
import com.cct.architecture_components.bussines.usecases.GetSeachUseCase;
import com.cct.architecture_components.bussines.viewmodel.FactoryViewModel;
import com.cct.architecture_components.presentation.popularmovies.PopularMoviesViewModel;
import com.cct.architecture_components.presentation.search.SearchViewModel;

import dagger.Module;
import dagger.Provides;

/**
 * Dagger module that provides objects which will live during the application lifecycle.
 */
@Module
public class ViewModelModule {

    @Provides
    @PerActivityScope
    PopularMoviesViewModel providePopularMoviesViewModel(GetPopularMoviesUseCase getPopularMoviesUseCase) {
        return new PopularMoviesViewModel(getPopularMoviesUseCase);
    }

    @Provides
    @PerActivityScope
    SearchViewModel provideSearchViewModel(GetSeachUseCase getSeachUseCase) {
        return new SearchViewModel(getSeachUseCase);
    }

    @Provides
    @PerActivityScope
    ViewModelProvider.Factory provideViewModelFactory(PopularMoviesViewModel popularMoviesViewModel,
                                                      SearchViewModel searchViewModel) {
        return new FactoryViewModel(popularMoviesViewModel, searchViewModel);
    }

}
