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
public class ResourceComplez<T> {
    @NonNull public final Status status;
    @Nullable public final T data;
    @Nullable public final String message;
    private ResourceComplez(@NonNull Status status, @Nullable T data, @Nullable String message) {
        this.status = status;
        this.data = data;
        this.message = message;
    }

    public static <T> ResourceComplez<T> success(@NonNull T data) {
        return new ResourceComplez<>(SUCCESS, data, null);
    }

    public static <T> ResourceComplez<T> error(String msg, @Nullable T data) {
        return new ResourceComplez<>(ERROR, data, msg);
    }

    public static <T> ResourceComplez<T> loading(@Nullable T data) {
        return new ResourceComplez<>(LOADING, data, null);
    }
}