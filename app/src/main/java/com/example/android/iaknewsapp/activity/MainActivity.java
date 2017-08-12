package com.example.android.iaknewsapp.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.android.iaknewsapp.BuildConfig;
import com.example.android.iaknewsapp.R;
import com.example.android.iaknewsapp.adapter.NewsAdapter;
import com.example.android.iaknewsapp.model.APIResponse;
import com.example.android.iaknewsapp.model.ArticlesItem;
import com.example.android.iaknewsapp.rest.APIClient;
import com.example.android.iaknewsapp.rest.APIService;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.rvNews)
    RecyclerView RV_NEWS;

    private LinearLayoutManager layoutManager;
    private NewsAdapter adapterDummy;
    private NewsAdapter mAdapterAPI;
    private List<ArticlesItem> mListArticle = new ArrayList<>();

    private final String NEWS_SOURCE = "techcrunch";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        //adapterDummy = new NewsAdapter(getDummyData());
        mAdapterAPI = new NewsAdapter(mListArticle);

        RV_NEWS.setLayoutManager(layoutManager);
        //RV_NEWS.setAdapter(adapterDummy);
        RV_NEWS.setAdapter(mAdapterAPI);

        getData();
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

    private void getData(){
        APIService apiService = APIClient.getRetrofitClient().create(APIService.class);
        Call<APIResponse> apiResponseCall = apiService.getTechCrunch(NEWS_SOURCE, BuildConfig.API_KEY);

        apiResponseCall.enqueue(new Callback<APIResponse>() {
            @Override
            public void onResponse(Call<APIResponse> call, Response<APIResponse> response) {
                APIResponse apiResponse = response.body();
                if(apiResponse != null){
                    mListArticle = apiResponse.getArticles();
                    mAdapterAPI.setData(mListArticle);
                }
            }

            @Override
            public void onFailure(Call<APIResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Parsing Gagal " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
