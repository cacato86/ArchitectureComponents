package com.cct.architecture_components.bussines.model;

import com.cct.architecture_components.data.db.entities.ApiResponseDB;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Carlos Carrasco Torres on 18/05/2017.
 */

public class ApiResponse<T> {

    @SerializedName("page")
    @Expose
    private Integer page;
    @SerializedName("results")
    @Expose
    private T results = null;
    @SerializedName("total_results")
    @Expose
    private Integer totalResults;
    @SerializedName("total_pages")
    @Expose
    private Integer totalPages;

    public ApiResponse(Integer page, T results, Integer totalResults, Integer totalPages) {
        this.page = page;
        this.results = results;
        this.totalResults = totalResults;
        this.totalPages = totalPages;
    }

    public ApiResponseDB convertToApiResponseDB() {
        return new ApiResponseDB(page, (List<Movie>) results, totalResults, totalPages);
    }

    public Integer getPage() {
        return page;
    }

    public T getResults() {
        return results;
    }

    public Integer getTotalResults() {
        return totalResults;
    }

    public Integer getTotalPages() {
        return totalPages;
    }
}
