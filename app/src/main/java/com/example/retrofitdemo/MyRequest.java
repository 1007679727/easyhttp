package com.example.retrofitdemo;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Url;


public interface MyRequest  {
    @GET
    Call<Translation> get(@Url String url);

    @POST
    Call<Translation> post(@Url String url,@Body RequestBody body);
}
