package com.example.android.iaknewsapp.rest;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by DITHA on 12/08/2017.
 */

public class APIClient {

    private final String BASE_URL = "https://newsapi.org/v1/";
    private Retrofit mRetrofit;

    public Retrofit getRetrofitClient(){
        if(mRetrofit == null){
            mRetrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return mRetrofit;
    }
}
