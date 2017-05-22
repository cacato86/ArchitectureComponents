package com.cct.architecture_components.bussines.usecases;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.LiveDataReactiveStreams;
import android.support.annotation.NonNull;

import com.cct.architecture_components.bussines.model.ApiResponse;
import com.cct.architecture_components.bussines.model.Movie;
import com.cct.architecture_components.bussines.model.SearchQuery;
import com.cct.architecture_components.data.Repository;

import org.reactivestreams.Publisher;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;

/**
 * Created by Carlos Carrasco Torres on 17/05/2017.
 */

public class GetSeachUseCase extends AbstractUseCase<List<Movie>> {

    @NonNull
    private final Scheduler subscriberScheduler;
    @NonNull
    private final Scheduler observableScheduler;
    private SearchQuery searchQuery;

    @Inject
    public GetSeachUseCase(@NonNull Repository repository,
                           @Named("subscriber") @NonNull Scheduler subscriberScheduler,
                           @Named("observer") @NonNull Scheduler observableScheduler) {
        super(repository, subscriberScheduler, observableScheduler);
        this.subscriberScheduler = subscriberScheduler;
        this.observableScheduler = observableScheduler;
    }

    public void addParameters(SearchQuery searchQuery) {
        this.searchQuery = searchQuery;
    }

    @Override
    protected Flowable<List<Movie>> buildUseCaseObservable() {
        return covertObservableToFlowable(searchQuery.getQuery())
                .flatMap(new Function<String, Publisher<List<Movie>>>() {
                    @Override
                    public Publisher<List<Movie>> apply(String s) throws Exception {
                        searchQuery.setStringQuery(s);
                        return repository.getSearch(searchQuery)
                                .map(movieApiResponse -> movieApiResponse.getResults());
                    }
                });
    }

    public LiveData<List<Movie>> getNewPage(Integer pageNumber) {
        searchQuery.setPageNumber(pageNumber);
        return createLiveData(repository.getSearch(searchQuery)
                .map(movieApiResponse -> movieApiResponse.getResults()));
    }

    protected Flowable<String> covertObservableToFlowable(Observable<String> observable) {
        return observable.toFlowable(BackpressureStrategy.BUFFER)
                .subscribeOn(AndroidSchedulers.mainThread());
    }
}
