package com.glima.hummingbird.view.model;

import com.glima.hummingbird.model.Movie;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gustavo on 15/03/17.
 */

public class MovieListViewModel {

    private List<Movie> movies = new ArrayList<>();

    public MovieListViewModel(List<Movie> movies) {
        this.movies.addAll(movies);
    }

    public List<Movie> getMovies() {
        return movies;
    }
}
