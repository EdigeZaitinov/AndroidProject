package com.example.titc_lab.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.titc_lab.NewsDetails;
import com.example.titc_lab.R;
import com.example.titc_lab.models.Result;
import com.example.titc_lab.models.SearchMovies;
import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Callback;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {
    Context context;
    List<Result> news;

    public NewsAdapter(Context context, List<Result>news){
        this.context=context;
        this.news=news;
    }
    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.news_item,parent,false);
        return new NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, final int position) {
        holder.raiting.setText(Double.toString(news.get(position).getPopularity()));
        holder.name.setText(news.get(position).getTitle());
        holder.date.setText(news.get(position).getReleaseDate());
        Picasso.Builder builder = new Picasso.Builder(context);
        builder.downloader(new OkHttp3Downloader(context));
        builder.build().load("https://image.tmdb.org/t/p/original"+news.get(position).getPosterPath())
                .placeholder((R.drawable.ic_launcher_background))
                .error(R.drawable.ic_launcher_background)
                .into(holder.image);
        holder.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, NewsDetails.class);
                intent.putExtra(Result.class.getSimpleName(), news.get(position));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return news.size();
    }

    public class NewsViewHolder extends RecyclerView.ViewHolder{
        TextView raiting,name,date;
        ImageView image;
        RelativeLayout item;
        public NewsViewHolder(@NonNull View itemView) {
            super(itemView);
            raiting=itemView.findViewById(R.id.item_raiting);
            name=itemView.findViewById(R.id.item_name);
            date=itemView.findViewById(R.id.item_date);
            image=itemView.findViewById(R.id.item_image);
            item=itemView.findViewById(R.id.item);
        }
    }
}
