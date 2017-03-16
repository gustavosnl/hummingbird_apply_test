package com.glima.hummingbird.network;

import com.glima.hummingbird.model.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by gustavo on 15/03/17.
 */

public class FilmsDeserializer {

    public static List<Movie> deserialize(InputStream inputStream) throws JSONException, IOException {

        BufferedInputStream stream = new BufferedInputStream(inputStream);
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        StringBuilder output = new StringBuilder(stream.available());
        String line;

        while ((line = reader.readLine()) != null) {
            output.append(line).append('\n');
        }

        JSONObject json = new JSONObject(output.toString());
        JSONArray jsonArray = json.getJSONArray("results");

        List<Movie> movies = new ArrayList<>();
        for (int i = 0; i < json.getJSONArray("results").length(); i++) {
            JSONObject jsonFilm = jsonArray.getJSONObject(i);

            movies.add(new Movie(
                    jsonFilm.getString("id"),
                    jsonFilm.getString("title"),
                    jsonFilm.getString("overview"),
                    jsonFilm.getString("poster_path"),
                    jsonFilm.getString("release_date")));
        }

        return movies;
    }
}