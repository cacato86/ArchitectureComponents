/*
 * Copyright (c) 2016 AXA Group Solutions.
 *
 * Licensed under the AXA Group Solutions License (the "License");
 * you may not use this file except in compliance with the License.
 * A copy of the License can be found in the LICENSE.TXT file distributed
 * together with this file.
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.cct.mvi_example.bussines.usecases;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.LiveDataReactiveStreams;
import android.support.annotation.NonNull;

import com.cct.mvi_example.data.Repository;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;

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

    protected abstract Flowable<T> buildUseCaseObservable();

    public LiveData<T> executeUseCase() {
        return LiveDataReactiveStreams.fromPublisher(
                this.buildUseCaseObservable()
                        .subscribeOn(subscriberScheduler)
                        .observeOn(observableScheduler));
    }
}

