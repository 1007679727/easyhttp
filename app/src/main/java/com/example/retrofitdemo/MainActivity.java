package com.example.retrofitdemo;

import android.os.Bundle;
import android.util.Log;
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
     * 案例：使用方式获取RetrofitUtil实例对象，直接请求即可
     * @param url
     */
    private void sendGet(String url){
//        try {
//            RetrofitUtil.getInstance().get("http://47.102.36.228:12311/police-run/PSPC/DPS/getAllPoliceStationJurisdiction/320102000000",new retrofit2.Callback() {
//                @Override
//                public void onResponse(retrofit2.Call call, retrofit2.Response response) {
//                    ResponseBean responseBean1 = (ResponseBean) response.body();
//                    Log.e(TAG, "onResponse: "+response.body()+response.message() );
//                }
//
//                @Override
//                public void onFailure(retrofit2.Call call, Throwable t) {
//                    Log.e(TAG, "onFailure: ",t );
//                }
//            });
//        } catch (Exception e) {
//            Log.e(TAG, "sendGet: ",e );
//        }
        new Thread(){
            @Override
            public void run() {
                try {
                    RequestBean requestBean = new RequestBean();
                    ResponseBean responseBean = RetrofitUtil.getInstance().get(requestBean, MyRequest.class);
                    Log.e(TAG, "run: "+responseBean );
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();

    }

}
