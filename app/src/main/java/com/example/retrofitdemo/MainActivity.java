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
        RetrofitUtil.getInstance().get("dsapi/",true);

        new Thread(){
            @Override
            public void run() {
                RetrofitUtil.getInstance().get("dsapi/");
            }
        }.start();
        new Thread(){
            @Override
            public void run() {
                RetrofitUtil.getInstance().get("dsapi/");
            }
        }.start();
        RetrofitUtil.getInstance().get("dsapi/",true);
    }


}
