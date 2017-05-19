
package com.cct.architecture_components.data.rest;

import com.cct.architecture_components.bussines.model.ApiResponse;
import com.cct.architecture_components.bussines.model.Movie;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * The interface Api.
 */
public interface Api {

    String ENDPOINT_IMAGES = "https://image.tmdb.org/t/p/";
    String DEFAULT_SIZE_IMAGES = "w154/";
    String ENDPOINT_POPULAR_MOVIES = "/3/movie/popular";

    @GET(ENDPOINT_POPULAR_MOVIES)
    Flowable<ApiResponse<Movie>> getArticleDetail(@Query("page") String page);
}
