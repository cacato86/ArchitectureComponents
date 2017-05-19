package com.cct.architecture_components.data;

import com.cct.architecture_components.bussines.model.ApiResponse;
import com.cct.architecture_components.data.rest.ApiClient;
import com.cct.architecture_components.bussines.model.Movie;

import io.reactivex.Flowable;

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
