package com.example.retrofitutil;

import okhttp3.*;

import java.io.IOException;
import java.util.HashMap;

/**
 * 基础的tag拦截器，提供修改tag，增加tag的功能
 * 如需拓展可继承此类
 */
public class BaseUrlInterceptor implements Interceptor {

    // 保存tags的map
    private HashMap<String, Object> tmpTagRequestMap = new HashMap<>();
    private HashMap<String, Headers> tmpHeadersRequestMap = new HashMap<>();

    /**
     * 作用1：统一增加请求头 terminalId 和 terminalSerialNumber
     * 作用2：拦截请求、修改BaseUrl 为 localUrl
     * @param chain
     * @return
     * @throws IOException
     */
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Request.Builder requestBuilder = executeHeaders(request);
        return chain.proceed(requestBuilder.build());
    }

    /**
     * 重构请求体，添加header和tag等
     * @param request 原请求体
     * @return
     */
    private Request.Builder executeHeaders(Request request) {
        Request.Builder r = request.newBuilder();
        if (tmpHeadersRequestMap.containsKey(request.url())) {
            r.headers(tmpHeadersRequestMap.remove(request.url()));
        }
        if (tmpTagRequestMap.containsKey(request.url())){
            r.tag(tmpTagRequestMap.remove(request.url()));
        }
        return r;
    }

    /**
     * 为请求添加tag
     * @param url 分辨请求的标记
     * @param tag 具体tag内容
     */
    public void addTmpRequest2Map(String url, Object tag) {
        tmpTagRequestMap.put(url, tag);
    }

    /**
     * 为请求添加header
     * @param url 分辨请求的标记
     * @param headers 具体的header内容
     */
    public void addHeaders(String url, Headers headers) {
        tmpHeadersRequestMap.put(url,headers);
    }
}
