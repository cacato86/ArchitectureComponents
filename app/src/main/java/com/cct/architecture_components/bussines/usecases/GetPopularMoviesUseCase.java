package com.cct.architecture_components.bussines.usecases;

import android.support.annotation.NonNull;

import com.cct.architecture_components.bussines.model.Movie;
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

public class GetPopularMoviesUseCase extends AbstractUseCase<List<Movie>> {

    private Integer pageNumber = 1;

    @Inject
    public GetPopularMoviesUseCase(@NonNull Repository repository,
                                   @Named("subscriber") @NonNull Scheduler subscriberScheduler,
                                   @Named("observer") @NonNull Scheduler observableScheduler) {
        super(repository, subscriberScheduler, observableScheduler);
    }

    public void addPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    @Override
    protected Flowable<ResourceComplez<List<Movie>>> buildUseCaseObservable() {
        return repository.getPopularMovies(pageNumber);
                /*.map(movieApiResponse -> Resource.success(movieApiResponse.getResults()))
                .startWith(Resource.loading("Loading..."));*/
    }

}
