package com.glima.hummingbird.network;

import com.glima.hummingbird.model.Film;

import java.util.List;

/**
 * Created by gustavo on 14/03/17.
 */

public interface Interactor extends NetworkTask<List<Film>> {

    void fetchPopularFilms();
}
