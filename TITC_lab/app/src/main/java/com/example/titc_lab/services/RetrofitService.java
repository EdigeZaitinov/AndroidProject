package com.example.titc_lab.services;

import com.example.titc_lab.models.SearchMovies;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RetrofitService {

    @GET("search/movie")
    Call<SearchMovies> getNews(@Query("api_key") String api_key, @Query("query") String query);
}
