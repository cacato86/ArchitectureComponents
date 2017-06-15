package com.cct.architecture_components.data;

import android.support.annotation.NonNull;

import com.cct.architecture_components.bussines.model.ApiResponse;
import com.cct.architecture_components.bussines.model.Movie;
import com.cct.architecture_components.bussines.model.ResourceComplez;
import com.cct.architecture_components.data.db.DataBase;
import com.cct.architecture_components.data.db.entities.ApiResponseDB;
import com.cct.architecture_components.data.rest.ApiClient;
import com.cct.architecture_components.data.rest.NetworkBoundResource;

import java.util.List;

import io.reactivex.Flowable;

/**
 * Created by Carlos Carrasco Torres on 17/05/2017.
 */

public class RepositoryImpl implements Repository {

    private ApiClient apiClient;
    private DataBase dataBase;

    public RepositoryImpl(ApiClient apiClient, DataBase dataBase) {
        this.apiClient = apiClient;
        this.dataBase = dataBase;
    }

    @Override
    public Flowable<ResourceComplez<List<Movie>>> getPopularMovies(Integer pageNumber) {

        return new NetworkBoundResource<List<Movie>, List<Movie>>() {

            @Override
            protected void saveCallResult(@NonNull List<Movie> item) {
                dataBase.movieDao().insertAll(new ApiResponseDB(pageNumber, item, 0, 0));
            }

            @Override
            protected boolean shouldFetch() {
                return true;
            }

            @NonNull
            @Override
            protected Flowable<List<Movie>> loadFromDb() {
                return dataBase.movieDao().loadPopularMovies(pageNumber)
                        .map(apiResponseDB -> apiResponseDB.getResults());
            }


            @NonNull
            @Override
            protected Flowable<ApiResponse<List<Movie>>> createCall() {
                return apiClient.getRestAdapter().getPopularMovies(pageNumber.toString())
                        .map(listApiResponse -> listApiResponse.getResults());
            }

        }.getAsFlowable();


    }

    @Override
    public Flowable<ApiResponse<List<Movie>>> getSearch(String query, Integer pageNumber) {
        return apiClient.getRestAdapter()
                .getSearch(query, pageNumber.toString());
    }
}
