package com.cct.architecture_components.presentation.popularmovies;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.cct.architecture_components.bussines.model.Movie;
import com.cct.architecture_components.presentation.customviews.MovieGridView;

import java.util.List;

/**
 * Created by Carlos Carrasco Torres on 18/05/2017.
 */

public class PopularMoviesRecyclerViewAdapter extends RecyclerView.Adapter<PopularMoviesRecyclerViewAdapter.PopularMoviesRecyclerViewHolders> {

    private List<Movie> movieList;
    private Context context;

    public PopularMoviesRecyclerViewAdapter(Context context, List<Movie> movieList) {
        this.movieList = movieList;
        this.context = context;
    }

    @Override
    public PopularMoviesRecyclerViewHolders onCreateViewHolder(ViewGroup parent, int viewType) {
        MovieGridView movieGridView = new MovieGridView(context);
        PopularMoviesRecyclerViewHolders rcv = new PopularMoviesRecyclerViewHolders(movieGridView);
        return rcv;
    }

    @Override
    public void onBindViewHolder(PopularMoviesRecyclerViewHolders holder, int position) {
        holder.movieView.fillMovie(movieList.get(position));
    }

    @Override
    public int getItemCount() {
        return this.movieList.size();
    }

    public class PopularMoviesRecyclerViewHolders extends RecyclerView.ViewHolder {

        private final MovieGridView movieView;

        public PopularMoviesRecyclerViewHolders(View itemView) {
            super(itemView);
            movieView = (MovieGridView) itemView;
        }

    }
}