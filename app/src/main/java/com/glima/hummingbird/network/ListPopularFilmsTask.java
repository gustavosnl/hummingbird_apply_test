package com.glima.hummingbird.network;

import android.content.Context;
import android.os.AsyncTask;

import com.glima.hummingbird.BuildConfig;
import com.glima.hummingbird.model.Film;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import static com.glima.hummingbird.BuildConfig.API_URL;

/**
 * Created by gustavo on 14/03/17.
 */

public class ListPopularFilmsTask extends AsyncTask<String, Void, List<Film>> {

    private final String API_KEY = "?api_key=".concat(BuildConfig.API_KEY);
    private final String POPULAR_FILMS_PATH = "discover/movie";
    private final String SORT_ASC_QUERY_PARAMETER = "&sort_by=popularity.desc";
    private HttpURLConnection urlConnection;
    private Context mContext;

    @Override
    protected List<Film> doInBackground(String... params) {
        try {
            URL url = new URL(API_URL
                    .concat(POPULAR_FILMS_PATH)
                    .concat(API_KEY)
                    .concat(SORT_ASC_QUERY_PARAMETER));

            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(urlConnection.getInputStream()));

            StringBuffer json = new StringBuffer(1024);
            String tmp = "";
            while ((tmp = reader.readLine()) != null)
                json.append(tmp);
            reader.close();

            urlConnection.disconnect();

            JSONObject jsonObject = new JSONObject();


            return (List<Film>) new JSONObject(json.toString());
        } catch (Exception e) {
            return null;
        }
    }

}
