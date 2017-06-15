package com.cct.architecture_components.bussines.usecases;

import android.support.annotation.NonNull;

import com.cct.architecture_components.bussines.model.Movie;
import com.cct.architecture_components.bussines.model.Resource;
import com.cct.architecture_components.bussines.model.ResourceComplez;
import com.cct.architecture_components.data.Repository;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Flowable;
import io.reactivex.Scheduler;

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
    protected Flowable<ResourceComplez<List<Movie>>> buildUseCaseObservable() {
        return null;
    }
        /*return repository.getSearch(searchQuery, pageNumber)
                .map(movieApiResponse -> Resource.success(movieApiResponse.getResults()));
    }*/
}
