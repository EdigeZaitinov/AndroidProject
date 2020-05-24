package com.example.titc_lab;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.titc_lab.adapters.NewsAdapter;
import com.example.titc_lab.clients.RetrofitClient;
import com.example.titc_lab.models.Result;
import com.example.titc_lab.models.SearchMovies;
import com.example.titc_lab.services.RetrofitService;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private NewsAdapter adapter;
    public RecyclerView newsRecycler;
    List<Result> news;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            RetrofitService service= RetrofitClient.getRetrofitInstance().create(RetrofitService.class);
            service.getNews("0581b7383bf18be60b37e4c87d8511f1","news").enqueue(new Callback<SearchMovies>() {
                @Override
                public void onResponse(Call<SearchMovies> call, Response<SearchMovies> response) {
                    news=response.body().getResults();
                    generateDataList(news);
                }

                @Override
                public void onFailure(Call<SearchMovies> call, Throwable t) {

                }
            });
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    private void generateDataList(List<Result> news) {
        newsRecycler = findViewById(R.id.news_recycler);
        adapter = new NewsAdapter(this,news);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        newsRecycler.setLayoutManager(layoutManager);
        newsRecycler.setAdapter(adapter);
    }
}
