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

    public static final int INITIAL_PAGE_NUMBER = 1;
    private Integer pageNumber;
    private String searchQuery;

    @Inject
    public GetSeachUseCase(@NonNull Repository repository,
                           @Named("subscriber") @NonNull Scheduler subscriberScheduler,
                           @Named("observer") @NonNull Scheduler observableScheduler) {
        super(repository, subscriberScheduler, observableScheduler);
    }

    public void addSearchQuery(String searchQuery) {
        this.searchQuery = searchQuery;
    }

    public void addPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    @Override
    protected Flowable<Resource<List<Movie>>> buildUseCaseObservable() {
        return repository.getSearch(searchQuery, pageNumber)
                .map(movieApiResponse -> Resource.success(movieApiResponse.getResults()));
    }
}
