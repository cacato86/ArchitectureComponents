
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

    //Wrap using Config request
    String ENDPOINT_IMAGES = "https://image.tmdb.org/t/p/";
    String DEFAULT_SIZE_IMAGES_LIST = "w300/";

    String ENDPOINT_POPULAR_MOVIES = "/3/movie/popular";
    String ENDPOINT_SEARCH = "/3/search/movie";

    @GET(ENDPOINT_POPULAR_MOVIES)
    Flowable<ApiResponse<Movie>> getArticleDetail(@Query("page") String page);

    @GET(ENDPOINT_SEARCH)
    Flowable<ApiResponse<Movie>> getSearch(@Query("query") String query, @Query("page") String page);
}
