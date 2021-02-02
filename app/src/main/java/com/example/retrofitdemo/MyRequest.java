package com.example.retrofitdemo;

import com.example.retrofitdemo.res.GetLogisticsListRes;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.*;


public interface MyRequest  {
    @GET
    Call<GetLogisticsListRes> get(@Url String url);
}
