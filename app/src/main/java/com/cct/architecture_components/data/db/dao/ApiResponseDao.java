package com.cct.architecture_components.data.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.cct.architecture_components.bussines.model.ApiResponse;
import com.cct.architecture_components.bussines.model.Movie;
import com.cct.architecture_components.data.db.entities.ApiResponseDB;

import java.util.List;

import io.reactivex.Flowable;

/**
 * Created by carlos.carrasco on 07/06/2017.
 */

@Dao
public interface ApiResponseDao {
    @Query("SELECT * FROM ApiResponseDB where page = :numberPage")
    Flowable<ApiResponseDB> loadPopularMovies(int numberPage);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(ApiResponseDB products);

}
