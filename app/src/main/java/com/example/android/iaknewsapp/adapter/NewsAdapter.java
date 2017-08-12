package com.example.android.iaknewsapp.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.android.iaknewsapp.R;
import com.example.android.iaknewsapp.model.ArticlesItem;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by DITHA on 06/08/2017.
 */

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {

    List<ArticlesItem> articlesItems;

    public NewsAdapter(List<ArticlesItem> articlesItems) {
        this.articlesItems = articlesItems;
    }

    @Override
    public NewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news, parent, false);
        NewsViewHolder viewHolder = new NewsViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(NewsViewHolder holder, int position) {
        ArticlesItem news = articlesItems.get(position);
        holder.bind(news);
    }

    @Override
    public int getItemCount() {
        return articlesItems.size();
    }

    static class NewsViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.imgNewsPhoto) ImageView IMG_NEWS_PHOTO;
        @BindView(R.id.tvNewsTitle) TextView TV_NEWS_TITLE;
        @BindView(R.id.tvNewsDesc) TextView TV_NEWS_DESC;

        public NewsViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(ArticlesItem newsItem){
            Glide.with((itemView.getContext())).load(newsItem.getUrlToImage()).into(IMG_NEWS_PHOTO);
            TV_NEWS_TITLE.setText(newsItem.getTitle());
            TV_NEWS_DESC.setText(newsItem.getDescription());
        }
    }
}
