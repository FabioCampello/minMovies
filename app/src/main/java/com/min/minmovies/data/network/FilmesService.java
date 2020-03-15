package com.min.minmovies.data.network;

import com.min.minmovies.data.network.response.FilmesResult;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface FilmesService {

    @GET("/movie/popular")
    Call<FilmesResult> recuperaListaFilmesPopulares(@Query("api_key") String apiKey);

}
