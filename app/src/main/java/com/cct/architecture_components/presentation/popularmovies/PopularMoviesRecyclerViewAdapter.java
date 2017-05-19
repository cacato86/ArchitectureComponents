package com.cct.architecture_components.presentation.popularmovies;

import android.content.Context;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.cct.architecture_components.bussines.model.Movie;
import com.cct.architecture_components.presentation.customviews.MovieGridView;

import java.util.List;
import java.util.Objects;

/**
 * Created by Carlos Carrasco Torres on 18/05/2017.
 */

public class PopularMoviesRecyclerViewAdapter extends RecyclerView.Adapter<PopularMoviesRecyclerViewAdapter.PopularMoviesRecyclerViewHolders> {

    private List<? extends Movie> mMovieList;
    private Context context;

    public PopularMoviesRecyclerViewAdapter(Context context, List<Movie> movieList) {
        this.mMovieList = movieList;
        this.context = context;
    }

    public void setProductList(final List<? extends Movie> movieList) {
        if (movieList == null) {
            mMovieList = movieList;
            notifyItemRangeInserted(0, movieList.size());
        } else {
            DiffUtil.DiffResult result = DiffUtil.calculateDiff(new DiffUtil.Callback() {
                @Override
                public int getOldListSize() {
                    return mMovieList.size();
                }

                @Override
                public int getNewListSize() {
                    return movieList.size();
                }

                @Override
                public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                    return mMovieList.get(oldItemPosition).getId() ==
                            movieList.get(newItemPosition).getId();
                }

                @Override
                public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                    Movie movie = movieList.get(newItemPosition);
                    Movie oldMovie = movieList.get(oldItemPosition);
                    return movie.getId() == oldMovie.getId()
                            && movie.getTitle() == oldMovie.getTitle();
                }
            });
            mMovieList = movieList;
            result.dispatchUpdatesTo(this);
        }
    }

    @Override
    public PopularMoviesRecyclerViewHolders onCreateViewHolder(ViewGroup parent, int viewType) {
        MovieGridView movieGridView = new MovieGridView(context);
        PopularMoviesRecyclerViewHolders rcv = new PopularMoviesRecyclerViewHolders(movieGridView);
        return rcv;
    }

    @Override
    public void onBindViewHolder(PopularMoviesRecyclerViewHolders holder, int position) {
        holder.movieView.fillMovie(mMovieList.get(position));
    }

    @Override
    public int getItemCount() {
        return this.mMovieList.size();
    }

    public class PopularMoviesRecyclerViewHolders extends RecyclerView.ViewHolder {
        private final MovieGridView movieView;

        public PopularMoviesRecyclerViewHolders(View itemView) {
            super(itemView);
            movieView = (MovieGridView) itemView;
        }
    }

}