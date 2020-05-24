package com.example.retrofit_picasso;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
            Retrofit retrofit=new Retrofit.Builder()
                    .baseUrl("https://api.themoviedb.org/3/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build();
            RetrofitService service=retrofit.create(RetrofitService.class);
            service.getNews("0581b7383bf18be60b37e4c87d8511f1","news").enqueue(new Callback<SearchMovies>() {
                @Override
                public void onResponse(Call<SearchMovies> call, Response<SearchMovies> response) {
                    Log.e("Search movies",response.body().getResults().size()+"");
                }

                @Override
                public void onFailure(Call<SearchMovies> call, Throwable t) {

                }
            });
//            service.getTodos().enqueue(new Callback<List<Todo>>() {
//                @Override
//                public void onResponse(Call<List<Todo>> call, Response<List<Todo>> response) {
//                    Log.e("response",response.body().size()+"");
//                }
//
//                @Override
//                public void onFailure(Call<List<Todo>> call, Throwable t) {
//
//                }
//            });
//            service.getTodoById(1).enqueue(new Callback<Todo>() {
//                @Override
//                public void onResponse(Call<Todo> call, Response<Todo> response) {
//                    Log.e("response",response.body().getTitle());
//                }
//
//                @Override
//                public void onFailure(Call<Todo> call, Throwable t) {
//
//                }
//            });
//            service.getTodoByUserId(1).enqueue(new Callback<List<Todo>>() {
//                @Override
//                public void onResponse(Call<List<Todo>> call, Response<List<Todo>> response) {
//                    Log.e("response",response.body()+"");
//                }
//
//                @Override
//                public void onFailure(Call<List<Todo>> call, Throwable t) {
//
//                }
//            });
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }
}
