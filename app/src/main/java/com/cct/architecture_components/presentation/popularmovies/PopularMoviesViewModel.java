package com.cct.architecture_components.presentation.popularmovies;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;

import com.cct.architecture_components.bussines.model.Movie;
import com.cct.architecture_components.bussines.model.Resource;
import com.cct.architecture_components.bussines.model.Status;
import com.cct.architecture_components.bussines.usecases.GetPopularMoviesUseCase;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by Carlos Carrasco Torres on 18/05/2017.
 */

@Singleton
public class PopularMoviesViewModel extends ViewModel {

    private LiveData<Resource<List<Movie>>> movies;
    private GetPopularMoviesUseCase getPopularMoviesUseCase;

    @Inject
    public PopularMoviesViewModel(GetPopularMoviesUseCase getPopularMoviesUseCase) {
        this.getPopularMoviesUseCase = getPopularMoviesUseCase;
    }

    public LiveData<Resource<List<Movie>>> getMovies() {
        if (movies == null) {
            movies = getPopularMoviesUseCase.executeUseCase();
        }
        return movies;
    }

    public LiveData<Resource<List<Movie>>> getNextPage(int pageNumber) {
        getPopularMoviesUseCase.addPageNumber(pageNumber);
        LiveData<Resource<List<Movie>>> newMovies = getPopularMoviesUseCase.executeUseCase();
        return Transformations.switchMap(newMovies, input -> {
            if (input.status == Status.SUCCESS) {
                movies.getValue().data.addAll(input.data);
            }
            return movies;
        });
    }

}
