package com.cct.architecture_components.data.rest;

import android.arch.lifecycle.MediatorLiveData;
import android.os.AsyncTask;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.annotation.WorkerThread;

import com.cct.architecture_components.bussines.model.ApiResponse;
import com.cct.architecture_components.bussines.model.ResourceComplez;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;

/**
 * Created by carlos.carrasco on 15/06/2017.
 */

public abstract class NetworkBoundResource<ResultType, RequestType> {
    private final Flowable<ResourceComplez<ResultType>> result;
    private FlowableEmitter emitter;


    @MainThread
    protected NetworkBoundResource() {
        result = Flowable.create(e -> {
            emitter = e;
            emitter.onNext(ResourceComplez.loading(null));
            Flowable<ResultType> dbSource = loadFromDb();
            if (shouldFetch()) {
                fetchFromNetwork(dbSource);
            } else {
                emitter.onNext(ResourceComplez.success(dbSource.blockingFirst()));
            }
        }, BackpressureStrategy.BUFFER);
    }

    private void fetchFromNetwork(final Flowable<ResultType> dbSource) {
        Flowable<ApiResponse<RequestType>> apiResponse = createCall();
        // we re-attach dbSource as a new source,
        // it will dispatch its latest value quickly

        emitter.onNext(ResourceComplez.loading(dbSource.blockingFirst()));

        saveCallResult(apiResponse.blockingFirst().getResults());
        emitter.onNext(ResourceComplez.success(apiResponse.blockingFirst()));
    }

    @WorkerThread
    protected abstract void saveCallResult(@NonNull RequestType item);

    // Called with the data in the database to decide whether it should be
    // fetched from the network.
    @MainThread
    protected abstract boolean shouldFetch();

    // Called to get the cached data from the database
    @NonNull
    @MainThread
    protected abstract Flowable<ResultType> loadFromDb();

    // Called to create the API call.
    @NonNull
    @MainThread
    protected abstract Flowable<ApiResponse<RequestType>> createCall();

    // Called when the fetch fails. The child class may want to reset components
    // like rate limiter.
    @MainThread
    protected void onFetchFailed() {
    }

    public final Flowable<ResourceComplez<ResultType>> getAsFlowable() {
        return result;
    }

}