package com.example.retrofitutil;

import com.example.retrofitutil.uitl.Constant;
import okhttp3.*;

import java.io.IOException;
import java.util.HashMap;

/**
 * 基础的tag拦截器，提供修改tag，增加tag的功能
 */
public class BaseUrlInterceptor implements Interceptor {

    // 保存tags的map
    private HashMap<String, Object> tmpRequestMap = new HashMap<>();

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

    private Request.Builder executeHeaders(Request request) {

        return request.newBuilder()
                .header("terminalId", Constant.ADDRESS)
                .header("terminalSerialNumber", Constant.SERIAL_NUMBER)
                .tag(tmpRequestMap.remove(request.url().url()));
    }

    public void addTmpRequest2Map(String url, Object tag) {
        tmpRequestMap.put(url, tag);
    }
}
