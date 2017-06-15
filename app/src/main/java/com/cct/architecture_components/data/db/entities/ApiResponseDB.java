package com.cct.architecture_components.data.db.entities;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.TypeConverters;

import com.cct.architecture_components.bussines.model.ApiResponse;
import com.cct.architecture_components.bussines.model.Movie;
import com.cct.architecture_components.data.db.converters.MovieListTypeConverter;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Carlos Carrasco Torres on 18/05/2017.
 */

@Entity(primaryKeys = "page")
@TypeConverters(MovieListTypeConverter.class)
public class ApiResponseDB {

    private Integer page;
    private List<Movie> results = null;
    private Integer totalResults;
    private Integer totalPages;

    public ApiResponseDB(Integer page, List<Movie> results, Integer totalResults, Integer totalPages) {
        this.page = page;
        this.results = results;
        this.totalResults = totalResults;
        this.totalPages = totalPages;
    }

    public ApiResponse<List<Movie>> convertToApiResponse() {
        return new ApiResponse(page, results, totalResults, totalPages);
    }

    public Integer getPage() {
        return page;
    }

    public List<Movie> getResults() {
        return results;
    }

    public Integer getTotalResults() {
        return totalResults;
    }

    public Integer getTotalPages() {
        return totalPages;
    }
}
