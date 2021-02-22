package com.example.retrofitdemo;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import com.example.retrofitutil.RetrofitUtil;
import com.example.retrofitutil.api.NetCallBack;

import java.util.HashMap;
import java.util.Map;
import java.util.Observable;


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
        init();

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
                RetrofitUtil.getInstance().get(url, Translation.class, new NetCallBack() {
                    @Override
                    public void onSuccess(Object o) {
                        ((Translation)o).show();
                    }

                    @Override
                    public void onError(Object o) {
                        Log.e(TAG, "onError: "+o );
                    }
                });
            }
        });
    }


}
