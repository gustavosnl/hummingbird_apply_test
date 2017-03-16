package com.glima.hummingbird.view.list;

import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.glima.hummingbird.R;
import com.glima.hummingbird.databinding.ViewCardMovieBinding;
import com.glima.hummingbird.model.Movie;
import com.glima.hummingbird.view.list.item.MovieItemViewModel;
import com.glima.hummingbird.view.list.model.MovieListViewModel;

import java.util.ArrayList;
import java.util.List;

import static android.databinding.DataBindingUtil.inflate;
import static android.view.LayoutInflater.from;

/**
 * Created by gustavo on 15/03/17.
 */

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MovieItemViewHolder> {

    private List<Movie> movies = new ArrayList<>();

    @Override
    public MovieItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MovieItemViewHolder(inflate(from(parent.getContext()), R.layout.view_card_movie, parent, false));
    }

    @Override
    public void onBindViewHolder(MovieItemViewHolder holder, int position) {
        holder.attachMovie(movies.get(position));
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public void attachViewModel(MovieListViewModel viewModel) {
        movies.addAll(viewModel.getMovies());
        notifyDataSetChanged();
    }

    class MovieItemViewHolder extends RecyclerView.ViewHolder {
        ViewDataBinding viewDataBinding;
        MovieItemViewModel viewModel;

        public MovieItemViewHolder(ViewDataBinding viewDataBinding) {
            super(viewDataBinding.getRoot());
            this.viewDataBinding = viewDataBinding;
        }

        public void attachMovie(Movie movie) {
            viewModel = new MovieItemViewModel(movie);
            ((ViewCardMovieBinding) viewDataBinding).setMovie(viewModel);
        }
    }
}
