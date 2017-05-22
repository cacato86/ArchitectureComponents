package com.cct.architecture_components.bussines.viewmodel;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.v4.util.ArrayMap;

import com.cct.architecture_components.presentation.popularmovies.PopularMoviesViewModel;
import com.cct.architecture_components.presentation.search.SearchViewModel;

import java.util.Map;
import java.util.concurrent.Callable;

import javax.inject.Inject;

/**
 * Created by Carlos Carrasco Torres on 19/05/2017.
 */

public class FactoryViewModel implements ViewModelProvider.Factory {
    private final ArrayMap<Class, Callable<? extends ViewModel>> creators;

    @Inject
    public FactoryViewModel(PopularMoviesViewModel popularMoviesViewModel,
                            SearchViewModel searchViewModel) {
        creators = new ArrayMap<>();
        creators.put(PopularMoviesViewModel.class, () -> popularMoviesViewModel);
        creators.put(SearchViewModel.class, () -> searchViewModel);
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
