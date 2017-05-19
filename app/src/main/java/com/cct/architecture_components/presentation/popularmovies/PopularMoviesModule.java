
package com.cct.architecture_components.presentation.popularmovies;

import android.arch.lifecycle.ViewModelProvider;

import com.cct.architecture_components.PerActivityScope;
import com.cct.architecture_components.bussines.usecases.GetPopularMoviesUseCase;
import com.cct.architecture_components.bussines.viewmodel.FactoryViewModel;

import dagger.Module;
import dagger.Provides;

/**
 * Dagger module that provides objects which will live during the application lifecycle.
 */
@Module
public class PopularMoviesModule {

    @Provides
    @PerActivityScope
    PopularMoviesViewModel providePopularMoviesViewModel(GetPopularMoviesUseCase getPopularMoviesUseCase) {
        return new PopularMoviesViewModel(getPopularMoviesUseCase);
    }

    @Provides
    @PerActivityScope
    ViewModelProvider.Factory provideViewModelFactory(PopularMoviesViewModel popularMoviesViewModel) {
        return new FactoryViewModel(popularMoviesViewModel);
    }

}
