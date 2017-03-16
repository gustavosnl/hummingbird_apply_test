package com.glima.hummingbird.view.list.model;

import com.glima.hummingbird.model.Film;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gustavo on 15/03/17.
 */

public class FilmListViewModel {

    private List<Film> films = new ArrayList<>();

    public FilmListViewModel(List<Film> films) {
        this.films.addAll(films);
    }

    public List<Film> getFilms() {
        return films;
    }
}
