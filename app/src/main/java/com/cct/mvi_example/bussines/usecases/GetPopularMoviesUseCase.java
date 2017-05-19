package com.cct.mvi_example.bussines.usecases;

import android.support.annotation.NonNull;

import com.cct.mvi_example.bussines.model.Movie;
import com.cct.mvi_example.data.Repository;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Scheduler;

/**
 * Created by Carlos Carrasco Torres on 17/05/2017.
 */

public class GetPopularMoviesUseCase extends AbstractUseCase<List<Movie>> {

    @Inject
    public GetPopularMoviesUseCase(@NonNull Repository repository,
                                   @Named("subscriber") @NonNull Scheduler subscriberScheduler,
                                   @Named("observer") @NonNull Scheduler observableScheduler) {
        super(repository, subscriberScheduler, observableScheduler);
    }

    @Override
    protected Flowable<List<Movie>> buildUseCaseObservable() {
        return repository.getPopularMovies().map(movieApiResponse -> movieApiResponse.getResults());
    }
}
