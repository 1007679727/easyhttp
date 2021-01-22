package com.example.retrofitutil.datainterface;

import org.json.JSONObject;

public class ResponseBean {
    private int code;
    private String message;

    public String getMessage() {
        return message;
    }

    public int getCode() {
        return code;
    }

    /**
     * 提供一个默认的实现，没有任何参数和方法
     * 此处不提供默认返回值的原因是retrofit创建call对象的时候会遍历父类和子类的所有变量
     * 容易导致冲突，所以仅提供一个getValue方法
     * @return JSONObject
     */
    public  JSONObject getValue(){
        return null;
    };
}
