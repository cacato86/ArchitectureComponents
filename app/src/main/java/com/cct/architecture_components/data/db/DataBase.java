package com.cct.architecture_components.data.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.cct.architecture_components.data.db.dao.ApiResponseDao;
import com.cct.architecture_components.data.db.entities.ApiResponseDB;

/**
 * Created by carlos.carrasco on 07/06/2017.
 */

@Database(entities = {ApiResponseDB.class}, version = 1)
public abstract class DataBase extends RoomDatabase {
    abstract public ApiResponseDao movieDao();
}
