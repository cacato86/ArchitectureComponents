package com.cct.architecture_components.data.db.converters;

import android.arch.persistence.room.TypeConverter;
import android.arch.persistence.room.util.StringUtil;

import com.cct.architecture_components.bussines.model.Movie;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

/**
 * Created by carlos.carrasco on 14/06/2017.
 */

public class MovieListTypeConverter {
    @TypeConverter
    public static List<Movie> stringToMovieList(String list) {
        if (list == null) {
            return Collections.emptyList();
        }
        Type listType = new TypeToken<List<Movie>>() {
        }.getType();
        return new Gson().fromJson(list, listType);
    }

    @TypeConverter
    public static String movieListToString(List<Movie> movies) {
        return new Gson().toJson(movies);
    }
}
