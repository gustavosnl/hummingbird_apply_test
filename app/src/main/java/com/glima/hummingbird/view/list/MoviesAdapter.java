package com.glima.hummingbird.view.list;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.glima.hummingbird.R;
import com.glima.hummingbird.model.Film;
import com.glima.hummingbird.view.list.item.FilmItemViewModel;
import com.glima.hummingbird.view.list.model.FilmListViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gustavo on 15/03/17.
 */

public class FilmsAdapter extends RecyclerView.Adapter<FilmsAdapter.FilmItemViewHolder> {

    private List<Film> films = new ArrayList<>();
    private Context context;

    public FilmsAdapter(Context context) {
        this.context = context;
    }

    @Override
    public FilmItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new FilmItemViewHolder(DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.view_card__film, parent, false));
    }

    @Override
    public void onBindViewHolder(FilmItemViewHolder holder, int position) {
        holder.attachTVFilm(films.get(position));
    }

    @Override
    public int getItemCount() {
        return films.size();
    }

    public void attachViewModel(FilmListViewModel viewModel) {
        films.addAll(viewModel.getFilms());
        notifyDataSetChanged();
    }

    class FilmItemViewHolder extends RecyclerView.ViewHolder {
        ViewDataBinding viewDataBinding;
        FilmItemViewModel viewModel;

        public FilmItemViewHolder(ViewDataBinding viewDataBinding) {
            super(viewDataBinding.getRoot());
            this.viewDataBinding = viewDataBinding;
            itemView.setOnClickListener(this);
        }

        public void attachTVFilm(Film film) {
            viewModel = new FilmItemViewModel(film);
            ((ViewCardFilmBinding) viewDataBinding).setTvFilm(viewModel);
        }

    }
}
