package com.glima.hummingbird.model;

/**
 * Created by gustavo on 14/03/17.
 */

public class Film {

    private String id;
    private String title;
    private String overview;
    private String poster;
    private String date;

    public Film(String id, String title, String overview, String poster, String date) {
        this.id = id;
        this.title = title;
        this.overview = overview;
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

    public String getPoster() {
        return poster;
    }

    public String getDate() {
        return date;
    }
}
