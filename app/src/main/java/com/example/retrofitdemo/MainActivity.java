package com.example.retrofitdemo;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import com.example.retrofitdemo.res.GetLogisticsListRes;
import com.example.retrofitutil.RetrofitUtil;
import com.example.retrofitutil.datainterface.ResponseBean;
import com.example.retrofitutil.exception.CreateCallFailException;
import com.example.retrofitutil.exception.NullBaseURLException;
import com.google.gson.Gson;
import okhttp3.*;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class MainActivity extends AppCompatActivity {


    private String TAG = "MainActivity";
    private String BASE_URL = "http://47.102.36.228:12311/";
    Map<String, String> headers = new HashMap<>();
    private Button get;
    private Button post;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initRetrofit();
        init();

    }

    /**
     * 初始化所必须的步骤，因为需要提供baseUrl和自定义拦截器
     */
    private void initRetrofit() {
        new RetrofitUtil(BASE_URL);
    }

    private void init() {
        headers.put("AuthorizationToken", "userInfoBean.getToken()");
        headers.put("userInfo", "new Gson().toJson(userInfo)");

        get = findViewById(R.id.get);
        post = findViewById(R.id.post);
        get.setOnClickListener(new View.OnClickListener() {
            // get 请求方式
            // 参数详解：MyRequest.class 这是来自于retrofit的方法，当前仅有get和post两种类型。原理是基于动态代理去翻译注解，生成类
            // Callback 函数执行后的回调，这里以在主线程无需手动处理。原理 请求后通过handler发回来的
            @Override
            public void onClick(View v) {
                String url = new StringBuilder(BASE_URL).append("public-security-duty/yjqwApp/list?page=1&rows=100").toString();//&jsonStr=").append(Gson().toJson(stupidParam))
                try {
                    RetrofitUtil.getInstance().get(url, "loadLogistics", headers, MyRequest.class, new Callback() {
                        @Override
                        public void onResponse(Call call, Response response) {
                            Log.e(TAG, "onResponse: "+response.body() );
                            get.setText("true");
                            get.setVisibility(View.GONE);
                        }

                        @Override
                        public void onFailure(Call call, Throwable t) {
                            get.setText("false");
                        }
                    });
                } catch (CreateCallFailException e) {
                    e.printStackTrace();
                } catch (NullBaseURLException e) {
                    e.printStackTrace();
                }
            }
        });
    }


}
