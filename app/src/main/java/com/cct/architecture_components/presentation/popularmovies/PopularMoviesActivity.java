package com.cct.architecture_components.presentation.popularmovies;

import android.arch.lifecycle.LifecycleActivity;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.cct.architecture_components.Application;
import com.cct.architecture_components.R;
import com.cct.architecture_components.bussines.model.Movie;
import com.cct.architecture_components.bussines.model.Status;
import com.cct.architecture_components.bussines.viewmodel.ViewModelModule;
import com.cct.architecture_components.common.EndlessScrollListener;
import com.cct.architecture_components.common.router.Router;
import com.cct.architecture_components.common.router.RouterModule;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Carlos Carrasco Torres on 18/05/2017.
 */

public class PopularMoviesActivity extends LifecycleActivity {

    private static final int NUM_COLUMS = 2;

    @Inject
    ViewModelProvider.Factory factoryViewModel;
    @Inject
    Router router;

    @BindView(R.id.recycler)
    RecyclerView recyclerView;
    @BindView(R.id.status_text)
    TextView statusText;

    @OnClick(R.id.fab_search)
    protected void searchClick() {
        router.routeToSearchActivity();
    }

    private PopularMoviesViewModel viewModel;
    private GridLayoutManager gridLayoutManager;
    private PopularMoviesRecyclerViewAdapter movieAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        injectDependencies();
        setContentView(R.layout.popular_movies_layout_activity);
        viewModel = ViewModelProviders.of(this, factoryViewModel).get(PopularMoviesViewModel.class);
        ButterKnife.bind(this);
        createRecyclerView();
        getPopularMovies();
    }

    private void injectDependencies() {
        ((Application) getApplication()).getApplicationComponent()
                .newPopularMoviesComponent(new ViewModelModule(), new RouterModule(this))
                .inject(this);

    }

    private void createRecyclerView() {
        gridLayoutManager = new GridLayoutManager(this, NUM_COLUMS);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(gridLayoutManager);
        movieAdapter = new PopularMoviesRecyclerViewAdapter(PopularMoviesActivity.this, new ArrayList<>());
        recyclerView.setAdapter(movieAdapter);
        addEndlessScrollListenerForPagination();
    }

    private void getPopularMovies() {
        viewModel.getMovies().observe(this, movies -> {
            if (movies.status == Status.LOADING) {
                setUILoading(movies.message);
            } else if (movies.status == Status.SUCCESS) {
                setUISucces(movies.data);
            } else if (movies.status == Status.ERROR) {
                setUIError(movies.message);
            }
        });
    }

    private void addEndlessScrollListenerForPagination() {
        recyclerView.addOnScrollListener(new EndlessScrollListener(gridLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                viewModel.getNextPage(page).observe(PopularMoviesActivity.this,
                        movies -> setUISucces(movies.data));
            }
        });
    }

    private void setUILoading(String msg) {
        hideStatus(false);
        statusText.setText(msg);
    }

    private void setUISucces(List<Movie> data) {
        hideStatus(true);
        movieAdapter.setMovietList(data);
    }

    private void setUIError(String message) {
        hideStatus(false);
        statusText.setText("Error: " + message);
    }

    private void hideStatus(boolean hideStatus) {
        statusText.setVisibility(hideStatus ? View.GONE : View.VISIBLE);
    }

}
