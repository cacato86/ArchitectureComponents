package com.cct.mvi_example.presentation.popularmovies;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cct.mvi_example.R;
import com.cct.mvi_example.bussines.model.Movie;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Carlos Carrasco Torres on 18/05/2017.
 */

public class PopularMoviesRecyclerViewAdapter extends RecyclerView.Adapter<PopularMoviesRecyclerViewAdapter.PopularMoviesRecyclerViewHolders> {

    private List<Movie> itemList;
    private Context context;

    public PopularMoviesRecyclerViewAdapter(Context context, List<Movie> itemList) {
        this.itemList = itemList;
        this.context = context;
    }

    @Override
    public PopularMoviesRecyclerViewHolders onCreateViewHolder(ViewGroup parent, int viewType) {

        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_list_layout, null);
        PopularMoviesRecyclerViewHolders rcv = new PopularMoviesRecyclerViewHolders(layoutView);
        return rcv;
    }

    @Override
    public void onBindViewHolder(PopularMoviesRecyclerViewHolders holder, int position) {
        holder.title.setText(itemList.get(position).getTitle());
        holder.image.setImageResource(itemList.get(position).getVoteCount());
    }

    @Override
    public int getItemCount() {
        return this.itemList.size();
    }

    public class PopularMoviesRecyclerViewHolders extends RecyclerView.ViewHolder {
        @BindView(R.id.title)
        TextView title;
        @BindView(R.id.image)
        ImageView image;
        public PopularMoviesRecyclerViewHolders(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

    }
}