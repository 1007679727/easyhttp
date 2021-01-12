package com.example.retrofitdemo;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Url;

public interface MyApiEndpointInterface {
    @POST("AppFiftyToneGraph/videoLink")
    Call<RetrofitEntity> getAllVideo(@Body boolean once_no);
}
