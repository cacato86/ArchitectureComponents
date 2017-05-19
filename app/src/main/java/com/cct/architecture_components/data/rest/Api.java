/*
 * Copyright (c) 2016 AXA Group Solutions.
 *
 * Licensed under the AXA Group Solutions License (the "License");
 * you may not use this file except in compliance with the License.
 * A copy of the License can be found in the LICENSE.TXT file distributed
 * together with this file.
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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

    public static String ENDPOINT_IMAGES = "https://image.tmdb.org/t/p/";
    public static String DEFAULT_SIZE_IMAGES = "w154/";

    String ENDPOINT_POPULAR_MOVIES = "/3/movie/popular";

    @GET(ENDPOINT_POPULAR_MOVIES)
    Flowable<ApiResponse<Movie>> getArticleDetail(@Query("page") String page);
}
