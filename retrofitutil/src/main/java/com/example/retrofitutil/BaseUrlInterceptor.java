package com.example.retrofitutil;

import android.util.Log;
import com.example.retrofitutil.uitl.Constant;
import okhttp3.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class BaseUrlInterceptor implements Interceptor {
    private String localUrl;

    private HashMap<String, Object> tmpRequestMap = new HashMap<>();

    /**
     * 作用1：统一增加请求头 terminalId 和 terminalSerialNumber
     * 作用2：拦截请求、修改BaseUrl 为 localUrl
     *
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

    private void executeUrl(Chain chain) {
        Request request = chain.request();
        HttpUrl url = request.url();


        Headers oldHeaders = request.headers();
        for (String name : oldHeaders.names()) {

        }
    }


    public void addTmpRequest2Map(String url, Object tag) {
        tmpRequestMap.put(url, tag);
    }
}
