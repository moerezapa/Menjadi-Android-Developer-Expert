package com.zap.movieandtvshow.utilities;

import com.zap.movieandtvshow.model.Movies;
import com.zap.movieandtvshow.model.Response;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface API {
    @GET("/discover/movie")
    Call<Response> getMovieList(@Query("api_key") String apiKey);

    @GET("/discover/tv")
    Call<Response> getTVList(@Query("api_key") String apiKey);

    @GET("discover/movie")
    Call<MoviesList> getMovies(@Query("api_key") String apiKey);
}
