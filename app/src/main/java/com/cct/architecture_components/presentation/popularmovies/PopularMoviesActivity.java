package com.cct.architecture_components.presentation.popularmovies;

import android.arch.lifecycle.LifecycleActivity;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.cct.architecture_components.Application;
import com.cct.architecture_components.R;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Carlos Carrasco Torres on 18/05/2017.
 */

public class PopularMoviesActivity extends LifecycleActivity {

    public static final int NUM_COLUMS = 4;

    @BindView(R.id.recycler)
    RecyclerView recyclerView;

    @Inject
    ViewModelProvider.Factory factoryViewModel;

    private PopularMoviesViewModel viewModel;
    private Unbinder unbinder;
    private GridLayoutManager gridLayoutManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        injectDependencies();
        setContentView(R.layout.popular_movies_layout);
        viewModel = ViewModelProviders.of(this, factoryViewModel).get(PopularMoviesViewModel.class);
        unbinder = ButterKnife.bind(this);
        createRecyclerView();
        getPopularMovies();
    }

    private void injectDependencies() {
        ((Application) getApplication()).getApplicationComponent()
                .newPopularMoviesComponent(new PopularMoviesModule())
                .inject(this);
    }

    private void createRecyclerView() {
        gridLayoutManager = new GridLayoutManager(this, NUM_COLUMS);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(gridLayoutManager);
    }

    private void getPopularMovies() {
        viewModel.getMovies().observe(this, movies -> {
            PopularMoviesRecyclerViewAdapter rcAdapter =
                    new PopularMoviesRecyclerViewAdapter(PopularMoviesActivity.this, movies);
            recyclerView.setAdapter(rcAdapter);
        });
    }

}
