
package com.cct.architecture_components.bussines.usecases;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.LiveDataReactiveStreams;
import android.support.annotation.NonNull;

import com.cct.architecture_components.bussines.model.Resource;
import com.cct.architecture_components.data.Repository;

import io.reactivex.Flowable;
import io.reactivex.Scheduler;

/**
 * AbstractUseCase base class for defining business logic based on use cases, is using Rx observables.
 *
 * @param <T> Data class.
 */
public abstract class AbstractUseCase<T> {

    /**
     * The Repository.
     */
    protected final Repository repository;
    private final Scheduler subscriberScheduler;
    private final Scheduler observableScheduler;

    /**
     * Constructor.
     *
     * @param repository          The repository to be used.
     * @param subscriberScheduler Subscribers scheduler.
     * @param observableScheduler Observables scheduler.
     */
    public AbstractUseCase(@NonNull Repository repository,
                           @NonNull Scheduler subscriberScheduler,
                           @NonNull Scheduler observableScheduler) {
        if (repository == null) {
            throw new IllegalArgumentException("Repository cannot be null");
        }
        if (subscriberScheduler == null) {
            throw new IllegalArgumentException("Subscriber scheduler cannot be null");
        }
        if (observableScheduler == null) {
            throw new IllegalArgumentException("Observable scheduler cannot be null");
        }

        this.repository = repository;
        this.subscriberScheduler = subscriberScheduler;
        this.observableScheduler = observableScheduler;
    }

    protected abstract Flowable<Resource<T>> buildUseCaseObservable();

    public LiveData<Resource<T>> executeUseCase() {
        return createLiveData(buildUseCaseObservable());
    }

    protected LiveData<Resource<T>> createLiveData(Flowable<Resource<T>> observable) {
        return LiveDataReactiveStreams.fromPublisher(observable.subscribeOn(subscriberScheduler)
                .observeOn(observableScheduler)
                .startWith(Resource.loading())
                .onErrorReturn(throwable -> Resource.error(throwable.getLocalizedMessage())));

    }
}

