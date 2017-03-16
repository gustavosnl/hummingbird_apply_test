package com.glima.hummingbird.network;

import com.glima.hummingbird.model.Film;

import java.util.List;

/**
 * Created by gustavo on 15/03/17.
 */

public interface FilmsCallBack {

    void onFetchFilmsCompleted(List<Film> films);

    void onFetchFilmsError();
}
