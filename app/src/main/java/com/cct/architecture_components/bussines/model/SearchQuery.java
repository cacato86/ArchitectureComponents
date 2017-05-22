package com.cct.architecture_components.bussines.model;

import io.reactivex.Observable;

/**
 * Created by Carlos Carrasco Torres on 22/05/2017.
 */

public class SearchQuery {
    Observable<String> query;
    Integer pageNumber;
    String stringQuery;

    public String getStringQuery() {
        return stringQuery;
    }

    public void setStringQuery(String stringQuery) {
        this.stringQuery = stringQuery;
    }

    public SearchQuery(Observable<String> query, Integer pageNumber) {
        this.query = query;
        this.pageNumber = pageNumber;
    }

    public Observable<String> getQuery() {
        return query;
    }

    public void setQuery(Observable<String> query) {
        this.query = query;
    }

    public Integer getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }
}
