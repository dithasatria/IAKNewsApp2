package com.example.android.iaknewsapp.rest;

import com.example.android.iaknewsapp.model.APIResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by DITHA on 12/08/2017.
 */

public interface APIService {

    @GET("articles")
    Call<APIResponse> getTechCrunch(
            @Query("source") String source,
            @Query("apiKey") String apiKey
    );
}
