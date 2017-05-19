package com.cct.mvi_example.data;

import com.cct.mvi_example.bussines.model.ApiResponse;
import com.cct.mvi_example.bussines.model.Movie;
import com.cct.mvi_example.data.rest.ApiClient;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Observable;

/**
 * Created by Carlos Carrasco Torres on 17/05/2017.
 */

public class RepositoryImpl implements Repository {

    private ApiClient apiClient;

    public RepositoryImpl(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    @Override
    public Flowable<ApiResponse<Movie>> getPopularMovies() {
        return apiClient.getRestAdapter().getArticleDetail("1");
    }
}
