package com.example.retrofitdemo;

import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.retrofitutil.RetrofitUtil;
import com.example.retrofitutil.datainterface.ResponseBean;
import com.google.gson.GsonBuilder;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.*;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.util.Observable;


public class MainActivity extends AppCompatActivity {


    private String TAG = "MainActivity";
    private String BASE_URL = "http://www.izaodao.com/Api/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        sendGet();
        sendGet("dsapi/");
//        RetrofitUtil.getInstance().get("dsapi/",true);
//
////        new Thread(){
////            @Override
////            public void run() {
////                RetrofitUtil.getInstance().post("dsapi/");
////            }
////        }.start();
//        new Thread(){
//            @Override
//            public void run() {
//                RetrofitUtil.getInstance().get("dsapi/");
//            }
//        }.start();
//        RetrofitUtil.getInstance().get("dsapi/",true);
    }

    private void sendGet(){
        Retrofit.Builder builder = new Retrofit.Builder();
        Retrofit retrofit = builder.baseUrl(BASE_URL).build();
        MyRequest c = retrofit.create(MyRequest.class);
        OkHttpClient okHttpClient = new OkHttpClient();
        Call call = okHttpClient.newCall(new Request.Builder().url("http://www.izaodao.com/Api/").build());
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

            }
        });
    }

    private void sendGet(String url){
        new Thread(){
            @Override
            public void run() {
                try {
                    ResponseBean responseBean = RetrofitUtil.getInstance().get(url, MyRequest.class, new retrofit2.Callback() {
                        @Override
                        public void onResponse(retrofit2.Call call, retrofit2.Response response) {
                            ResponseBean responseBean1 = (ResponseBean) response.body();
                            responseBean1.getValue();
                        }

                        @Override
                        public void onFailure(retrofit2.Call call, Throwable t) {

                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();

    }

}
