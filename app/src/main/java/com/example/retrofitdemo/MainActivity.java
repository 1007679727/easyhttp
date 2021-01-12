package com.example.retrofitdemo;

import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.retrofitutil.RetrofitUtil;
import com.google.gson.GsonBuilder;
import retrofit2.*;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity {


    private String TAG = "MainActivity";
    private String BASE_URL = "http://www.izaodao.com/Api/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        MyApiEndpointInterface apiServer = retrofit.create(MyApiEndpointInterface.class);
        Call<RetrofitEntity> call =  apiServer.getAllVideo(true);
        call.enqueue(new Callback<RetrofitEntity>() {
            @Override
            public void onResponse(Call<RetrofitEntity> call, Response<RetrofitEntity> response) {
                Log.e(TAG, "onResponse: " + response.body());
            }

            @Override
            public void onFailure(Call<RetrofitEntity> call, Throwable t) {

            }
        });
    }
}
