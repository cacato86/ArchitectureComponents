package com.cct.architecture_components.bussines.model;

import io.reactivex.observers.DisposableObserver;

/**
 * Created by Carlos Carrasco Torres on 22/05/2017.
 */

public abstract class SimpleDisposableObserver<T> extends DisposableObserver<T> {

    @Override
    public void onError(Throwable e) {
        //Do stuff if need
    }

    @Override
    public void onComplete() {
        //Do stuff if need
    }
}
