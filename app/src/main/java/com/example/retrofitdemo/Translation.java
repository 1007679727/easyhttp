package com.example.retrofitdemo;


import android.util.Log;
import com.example.retrofitutil.datainterface.ResponseBean;
import org.json.JSONObject;

public class Translation implements ResponseBean {
    private int sid;
    private String tts;
    private String content;
    private String note;
    private int love;
    private String translation;
    private String picture;
    private String picture2;
    private String caption;
    private String dataline;
    private int s_pv;
    private int SP_pv;
    private String[] tags;
    private String fenxiang_img;




    //定义 输出返回数据 的方法
    public void show() {
        Log.e("TAG", "Rxjava翻译结果：" + sid);
        Log.e("TAG", "Rxjava翻译结果：" + tts);
        Log.e("TAG", "Rxjava翻译结果：" + content);
        Log.e("TAG", "Rxjava翻译结果：" + note);
        Log.e("TAG", "Rxjava翻译结果：" + love);
        Log.e("TAG", "Rxjava翻译结果：" + translation);
    }

    @Override
    public JSONObject getValue() {
        show();
        return null;
    }
}
