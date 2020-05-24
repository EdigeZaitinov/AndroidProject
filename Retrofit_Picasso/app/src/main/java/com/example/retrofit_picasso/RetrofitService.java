package com.example.retrofit_picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RetrofitService {
    @GET("todos/")
    Call<List<Todo>> getTodos();

    @GET("todos/{id}")
    Call<Todo> getTodoById(@Path("id")int todoId);

    @GET("todos/")
    Call<List<Todo>> getTodoByUserId(@Query("userId")int userId);

    @GET("search/movie")
    Call<SearchMovies> getNews(@Query("api_key") String api_key, @Query("query") String query);
}
