package com.cct.architecture_components.data;

import com.cct.architecture_components.bussines.model.ApiResponse;
import com.cct.architecture_components.bussines.model.Movie;
import com.cct.architecture_components.bussines.model.SearchQuery;
import com.cct.architecture_components.data.rest.ApiClient;

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
    public Flowable<ApiResponse<Movie>> getPopularMovies(Integer pageNumber) {
        return apiClient.getRestAdapter().getArticleDetail(pageNumber.toString());
    }

    @Override
    public Flowable<ApiResponse<Movie>> getSearch(SearchQuery searchQuery) {
        return apiClient.getRestAdapter()
                .getSearch(searchQuery.getStringQuery(), searchQuery.getPageNumber().toString());
    }
}
