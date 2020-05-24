package com.example.titc_lab;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.titc_lab.models.Result;
import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

public class NewsDetails extends AppCompatActivity {

    TextView raiting,news_name,name,date,description;
    ImageView image;
    Result news;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_details);

        news_name=findViewById(R.id.news_name);
        raiting=findViewById(R.id.raiting);
        name=findViewById(R.id.name);
        date=findViewById(R.id.date);
        image=findViewById(R.id.image);
        description=findViewById(R.id.description);
        getData();
        setData();
    }
    private void getData(){
        Bundle data=getIntent().getExtras();
        if(data!=null){
            news=(Result) data.getSerializable(Result.class.getSimpleName());
        }
    }
    private void setData(){
        news_name.setText(news.getTitle());
        raiting.setText(Double.toString(news.getPopularity()));
        name.setText(news.getTitle());
        date.setText(news.getReleaseDate());
        Picasso.Builder builder = new Picasso.Builder(this);
        builder.downloader(new OkHttp3Downloader(this));
        builder.build().load("https://image.tmdb.org/t/p/original"+news.getPosterPath())
                .placeholder((R.drawable.ic_launcher_background))
                .error(R.drawable.ic_launcher_background)
                .into(image);
        description.setText(news.getOverview());
    }
}
