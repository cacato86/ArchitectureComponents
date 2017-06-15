package com.cct.architecture_components.presentation.popularmovies;

import android.arch.core.util.Function;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;

import com.cct.architecture_components.bussines.model.Movie;
import com.cct.architecture_components.bussines.model.Resource;
import com.cct.architecture_components.bussines.model.ResourceComplez;
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

    private final MutableLiveData<Integer> numPage = new MutableLiveData<>();
    private final LiveData<ResourceComplez<List<Movie>>> moviesNextPage;
    private LiveData<ResourceComplez<List<Movie>>> movies;

    @Inject
    public PopularMoviesViewModel(GetPopularMoviesUseCase getPopularMoviesUseCase) {

        movies = getPopularMoviesUseCase.executeUseCase();

        moviesNextPage = Transformations.switchMap(numPage, input -> {
            getPopularMoviesUseCase.addPageNumber(input);
            return getPopularMoviesUseCase.executeUseCase();
        });
    }

    public LiveData<ResourceComplez<List<Movie>>> getMovies() {
        return movies;
    }

    public void setPageNumer(int pageNumber) {
        numPage.setValue(pageNumber);
    }

    public LiveData<ResourceComplez<List<Movie>>> getNextPage() {
        return moviesNextPage;
    }

}
