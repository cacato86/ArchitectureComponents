package com.cct.architecture_components.presentation.search;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;

import com.cct.architecture_components.bussines.model.Movie;
import com.cct.architecture_components.bussines.model.SearchQuery;
import com.cct.architecture_components.bussines.model.SimpleDisposableObserver;
import com.cct.architecture_components.bussines.usecases.GetSeachUseCase;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;

/**
 * Created by Carlos Carrasco Torres on 18/05/2017.
 */

@Singleton
public class SearchViewModel extends ViewModel {

    private LiveData<List<Movie>> searchMovies;
    private GetSeachUseCase getSearchUseCase;

    private static int INITIAL_PAGE_NUMBER = 1;

    @Inject
    public SearchViewModel(GetSeachUseCase getSearchUseCase) {
        this.getSearchUseCase = getSearchUseCase;
    }

    public LiveData<List<Movie>> getSearch(Observable<String> searchQueryObs) {
        if (searchMovies == null) {
            getSearchUseCase.addParameters(createSeachQuery(searchQueryObs, INITIAL_PAGE_NUMBER));
            searchMovies = getSearchUseCase.executeUseCase();
        }
        return searchMovies;
    }

    public LiveData<List<Movie>> getNextPage(int pageNumber) {
        LiveData<List<Movie>> newSearchMovies = getSearchUseCase.getNewPage(pageNumber);
        return Transformations.switchMap(newSearchMovies, input -> {
            searchMovies.getValue().addAll(input);
            return searchMovies;
        });
    }

    //Kotlin optional parameter :(
    private SearchQuery createSeachQuery(Observable<String> query, Integer pageNumber) {
        return new SearchQuery(query, pageNumber);
    }

}
