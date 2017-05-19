package com.cct.mvi_example.data;

import com.cct.mvi_example.bussines.model.ApiResponse;
import com.cct.mvi_example.bussines.model.Movie;

import io.reactivex.Flowable;
import io.reactivex.Observable;

/**
 * Created by Carlos Carrasco Torres on 17/05/2017.
 */

public interface Repository {
    Flowable<ApiResponse<Movie>> getPopularMovies();
}
