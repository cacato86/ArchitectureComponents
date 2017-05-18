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

package com.cct.mvi_example.data.rest;

import com.cct.mvi_example.bussines.model.ApiResponse;
import com.cct.mvi_example.bussines.model.Movie;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * The interface Api.
 */
public interface Api {

    String ENDPOINT_POPULAR_MOVIES = "/3/movie/popular";

    @GET(ENDPOINT_POPULAR_MOVIES)
    Observable<ApiResponse<Movie>> getArticleDetail(@Query("page") String page);
}
