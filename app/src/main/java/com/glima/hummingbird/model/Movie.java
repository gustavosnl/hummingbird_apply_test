package com.glima.hummingbird.model;

import java.io.Serializable;

/**
 * Created by gustavo on 14/03/17.
 */

public class Movie implements Serializable {

    private String id;
    private String title;
    private String overview;
    private String thumbnail;
    private String poster;
    private String date;

    public Movie(String id, String title, String overview, String thumbnail, String poster, String date) {
        this.id = id;
        this.title = title;
        this.overview = overview;
        this.thumbnail = thumbnail;
        this.poster = poster;
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getOverview() {
        return overview;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public String getPoster() {
        return poster;
    }

    public String getDate() {
        return date;
    }
}
