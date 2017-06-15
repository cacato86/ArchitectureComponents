package com.cct.architecture_components.presentation.search;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;

import com.cct.architecture_components.bussines.model.Movie;
import com.cct.architecture_components.bussines.model.Resource;
import com.cct.architecture_components.bussines.model.ResourceComplez;
import com.cct.architecture_components.bussines.usecases.GetSeachUseCase;

import org.reactivestreams.Publisher;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;

import static com.cct.architecture_components.bussines.usecases.GetSeachUseCase.INITIAL_PAGE_NUMBER;

/**
 * Created by Carlos Carrasco Torres on 18/05/2017.
 */

@Singleton
public class SearchViewModel extends ViewModel {

    private final MutableLiveData<String> searchQuery = new MutableLiveData<>();
    private final MutableLiveData<Integer> numPage = new MutableLiveData<>();
    private LiveData<ResourceComplez<List<Movie>>> searchMovies;
    private LiveData<ResourceComplez<List<Movie>>> searchMoviesNextPage;

    @Inject
    public SearchViewModel(GetSeachUseCase getSearchUseCase) {
        searchMovies = Transformations.switchMap(searchQuery, (String input) -> {
            getSearchUseCase.addPageNumber(INITIAL_PAGE_NUMBER);
            getSearchUseCase.addSearchQuery(input);
            return getSearchUseCase.executeUseCase();
        });

        searchMoviesNextPage = Transformations.switchMap(numPage, (Integer input) -> {
            getSearchUseCase.addPageNumber(input);
            return getSearchUseCase.executeUseCase();
        });
    }

    public void setQuery(Observable<String> query) {
        query.subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s -> searchQuery.setValue(s));
    }

    public void setPageNumer(int pageNumber) {
        numPage.setValue(pageNumber);
    }

    public LiveData<ResourceComplez<List<Movie>>> getSearch() {
        return searchMovies;
    }

    public LiveData<ResourceComplez<List<Movie>>> getNextPage() {
        return searchMoviesNextPage;
    }

}
