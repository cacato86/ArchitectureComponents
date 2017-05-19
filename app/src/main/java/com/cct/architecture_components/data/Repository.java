package com.cct.architecture_components.data;

import com.cct.architecture_components.bussines.model.ApiResponse;
import com.cct.architecture_components.bussines.model.Movie;

import io.reactivex.Flowable;

/**
 * Created by Carlos Carrasco Torres on 17/05/2017.
 */

public interface Repository {
    Flowable<ApiResponse<Movie>> getPopularMovies();
}
