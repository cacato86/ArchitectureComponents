package com.cct.mvi_example.presentation.popularmovies;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.cct.mvi_example.bussines.model.Movie;
import com.cct.mvi_example.bussines.usecases.GetPopularMoviesUseCase;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by Carlos Carrasco Torres on 18/05/2017.
 */

@Singleton
public class PopularMoviesViewModel extends ViewModel {

    private LiveData<List<Movie>> movies;
    private GetPopularMoviesUseCase getPopularMoviesUseCase;

    @Inject
    public PopularMoviesViewModel(GetPopularMoviesUseCase getPopularMoviesUseCase) {
        this.getPopularMoviesUseCase = getPopularMoviesUseCase;
    }

    public LiveData<List<Movie>> getMovies() {
        if (movies == null) {
            movies = getPopularMoviesUseCase.executeUseCase();
        }
        return movies;
    }

}
