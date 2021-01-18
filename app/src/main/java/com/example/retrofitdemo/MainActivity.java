package com.example.retrofitdemo;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.example.retrofitutil.RetrofitUtil;
import com.example.retrofitutil.datainterface.ResponseBean;
import okhttp3.*;
import retrofit2.Retrofit;

import java.io.IOException;


public class MainActivity extends AppCompatActivity {


    private String TAG = "MainActivity";
    private String BASE_URL = "http://www.izaodao.com/Api/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sendGet("dsapi/");

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

    /**
     *
     * @param url
     */
    private void sendGet(String url){
        new Thread(){
            @Override
            public void run() {
                try {
                    RequestBean requestBean = new RequestBean();
                    ResponseBean responseBean = RetrofitUtil.getInstance().get(requestBean, MyRequest.class, new retrofit2.Callback() {
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
