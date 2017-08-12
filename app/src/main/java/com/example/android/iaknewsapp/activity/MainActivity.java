package com.example.android.iaknewsapp.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.android.iaknewsapp.R;
import com.example.android.iaknewsapp.adapter.NewsAdapter;
import com.example.android.iaknewsapp.model.ArticlesItem;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.rvNews)
    RecyclerView RV_NEWS;

    private LinearLayoutManager layoutManager;
    private NewsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        adapter = new NewsAdapter(getDummyData());

        RV_NEWS.setLayoutManager(layoutManager);
        RV_NEWS.setAdapter(adapter);
    }

    private List<ArticlesItem> getDummyData(){
        List<ArticlesItem> dummyList = new ArrayList<>();
        for(int i = 0; i < 10; i++){
            ArticlesItem item = new ArticlesItem();
            item.setTitle(String.valueOf(i) + getString(R.string.dummy_news_title));
            item.setDescription(getString(R.string.dummy_news_desc));
            item.setUrlToImage("http://i2.cdn.cnn.com/cnnnext/dam/assets/170804164112-bill-clinton-monica-lewinsky-file-super-tease.jpg");
            dummyList.add(item);
        }

        return dummyList;
    }

}
