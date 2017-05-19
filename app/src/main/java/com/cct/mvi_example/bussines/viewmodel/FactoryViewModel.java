package com.cct.mvi_example.bussines.viewmodel;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.v4.util.ArrayMap;

import com.cct.mvi_example.presentation.popularmovies.PopularMoviesComponent;
import com.cct.mvi_example.presentation.popularmovies.PopularMoviesViewModel;

import java.util.Map;
import java.util.concurrent.Callable;

import javax.inject.Inject;

/**
 * Created by Carlos Carrasco Torres on 19/05/2017.
 */

public class FactoryViewModel implements ViewModelProvider.Factory {
    private final ArrayMap<Class, Callable<? extends ViewModel>> creators;

    @Inject
    public FactoryViewModel(PopularMoviesViewModel popularMoviesViewModel) {
        creators = new ArrayMap<>();
        creators.put(PopularMoviesViewModel.class, () -> popularMoviesViewModel);
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        Callable<? extends ViewModel> creator = creators.get(modelClass);
        if (creator == null) {
            for (Map.Entry<Class, Callable<? extends ViewModel>> entry : creators.entrySet()) {
                if (modelClass.isAssignableFrom(entry.getKey())) {
                    creator = entry.getValue();
                    break;
                }
            }
        }
        if (creator == null) {
            throw new IllegalArgumentException("unknown model class " + modelClass);
        }
        try {
            return (T) creator.call();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
