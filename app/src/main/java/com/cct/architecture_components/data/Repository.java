package com.cct.architecture_components.data;

import com.cct.architecture_components.bussines.model.ApiResponse;
import com.cct.architecture_components.bussines.model.Movie;
import com.cct.architecture_components.bussines.model.ResourceComplez;
import com.cct.architecture_components.bussines.model.SearchQuery;

import java.util.List;

import io.reactivex.Flowable;

/**
 * Created by Carlos Carrasco Torres on 17/05/2017.
 */

public interface Repository {
    Flowable<ResourceComplez<List<Movie>>> getPopularMovies(Integer pageNumber);

    Flowable<ApiResponse<List<Movie>>> getSearch(String query, Integer pageNumber);
}
