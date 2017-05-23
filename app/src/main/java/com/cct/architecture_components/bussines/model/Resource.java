package com.cct.architecture_components.bussines.model;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import static com.cct.architecture_components.bussines.model.Status.ERROR;
import static com.cct.architecture_components.bussines.model.Status.LOADING;
import static com.cct.architecture_components.bussines.model.Status.SUCCESS;

/**
 * Created by Carlos Carrasco Torres on 23/05/2017.
 */

//a generic class that describes a data with a status
public class Resource<T> {
    @NonNull
    public final Status status;
    @Nullable public final T data;
    @Nullable public final String message;
    private Resource(@NonNull Status status, @Nullable T data, @Nullable String message) {
        this.status = status;
        this.data = data;
        this.message = message;
    }

    public static <T> Resource<T> success(@NonNull T data) {
        return new Resource<>(SUCCESS, data, null);
    }

    public static <T> Resource<T> error(String msg) {
        return new Resource<>(ERROR, null, msg);
    }

    public static <T> Resource<T> loading(String msg) {
        return new Resource<>(LOADING, null, msg);
    }
}