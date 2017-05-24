package com.cct.architecture_components.bussines.usecases;

import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.cct.architecture_components.bussines.model.Movie;
import com.cct.architecture_components.bussines.model.Resource;
import com.cct.architecture_components.bussines.model.SearchQuery;
import com.cct.architecture_components.data.Repository;

import org.reactivestreams.Publisher;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;

/**
 * Created by Carlos Carrasco Torres on 17/05/2017.
 */

public class GetSeachUseCase extends AbstractUseCase<List<Movie>> {


    private static final int INITIAL_PAGE_NUMBER = 1;
    private SearchQuery searchQuery;

    @Inject
    public GetSeachUseCase(@NonNull Repository repository,
                           @Named("subscriber") @NonNull Scheduler subscriberScheduler,
                           @Named("observer") @NonNull Scheduler observableScheduler) {
        super(repository, subscriberScheduler, observableScheduler);
    }

    public void addParameters(SearchQuery searchQuery) {
        this.searchQuery = searchQuery;
    }

    //switchMap - uses the last emited value from the observable - use last call and show ONLY last results
    @Override
    protected Flowable<Resource<List<Movie>>> buildUseCaseObservable() {
        return covertObservableToFlowable(searchQuery.getQuery())
                .switchMap(new Function<String, Publisher<Resource<List<Movie>>>>() {
                    @Override
                    public Publisher<Resource<List<Movie>>> apply(String s) throws Exception {
                        searchQuery.setStringQuery(s);
                        searchQuery.setPageNumber(INITIAL_PAGE_NUMBER);
                        return repository.getSearch(searchQuery)
                                .map(movieApiResponse -> Resource.success(movieApiResponse.getResults()));
                    }
                }).startWith(Resource.loading("Write something to search films"));
    }

    public LiveData<Resource<List<Movie>>> getNewPage(Integer pageNumber) {
        searchQuery.setPageNumber(pageNumber);
        return createLiveData(repository.getSearch(searchQuery)
                .map(movieApiResponse -> Resource.success(movieApiResponse.getResults())));
    }

    protected Flowable<String> covertObservableToFlowable(Observable<String> observable) {
        return observable.toFlowable(BackpressureStrategy.BUFFER)
                .subscribeOn(AndroidSchedulers.mainThread());
    }
}
