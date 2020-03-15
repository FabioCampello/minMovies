package com.min.minmovies.data.network.response;

import com.squareup.moshi.Json;

import java.util.List;

public class FilmesResult {

    @Json(name = "results")
    private final List<FilmesResponse> results;

    public FilmesResult(List<FilmesResponse> results) {
        this.results = results;
    }

    public List<FilmesResponse> getResults() {
        return results;
    }

}
