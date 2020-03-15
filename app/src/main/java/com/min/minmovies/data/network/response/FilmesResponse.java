package com.min.minmovies.data.network.response;

import com.squareup.moshi.Json;

import java.util.List;

public class FilmesResponse {

    @Json(name = "poster_path")
    private final String poster_path;

    @Json(name = "original_title")
    private final String original_title;

    public FilmesResponse(String poster_path, String original_title) {
        this.poster_path = poster_path;
        this.original_title = original_title;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public String getOriginal_title() {
        return original_title;
    }

}
