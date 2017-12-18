package com.glima.hummingbird.network.task;

import android.os.AsyncTask;

import com.glima.hummingbird.BuildConfig;
import com.glima.hummingbird.model.Movie;
import com.glima.hummingbird.network.callback.MoviesCallBack;
import com.glima.hummingbird.network.deserialize.MoviesDeserializer;

import org.json.JSONException;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by gustavo on 19/03/17.
 */

public abstract class MoviesTask extends AsyncTask<String, Void, List<Movie>> {

    protected final String API_KEY = "?api_key=".concat(BuildConfig.API_KEY);
    protected final String PAGE_QUERY = "&page=";

    private HttpURLConnection mUrlConnection;
    private final MoviesCallBack mCallBack;

    public MoviesTask(MoviesCallBack callBack) {
        mCallBack = callBack;
    }

    @Override
    protected void onPreExecute() {
        mCallBack.showProgress();
    }

    @Override
    protected List<Movie> doInBackground(String... params) {

        try {
            mUrlConnection = (HttpURLConnection) buildUrl(params).openConnection();
            mUrlConnection.setRequestMethod("GET");
            mUrlConnection.connect();

            return MoviesDeserializer.deserialize(mUrlConnection.getInputStream());

        } catch (IOException | JSONException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    protected void onPostExecute(List<Movie> movies) {
        mCallBack.onFetchMoviesCompleted(movies);
    }

    protected abstract URL buildUrl(String... params) throws MalformedURLException;
}
