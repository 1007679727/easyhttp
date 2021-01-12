package com.example.retrofitutil;

import com.example.retrofitutil.constant.Constant;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.List;

public class BaseUrlInterceptor implements Interceptor {
    private String localUrl;

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
        HttpUrl url = request.url();
        Request.Builder builder = request.newBuilder()
                .header("terminalId", Constant.ADDRESS)
                .header("terminalSerialNumber", Constant.SERIAL_NUMBER);
        List<String> list = request.headers("url_name");
        if (list != null && !list.isEmpty()) {
            builder.removeHeader("url_name");
            String headersValue = list.get(0);
            HttpUrl newUrl = null;
            if (".".equals(headersValue)) {
                newUrl = HttpUrl.parse(Constant.BASE_URL);
            } else {
                newUrl = url;
            }
            HttpUrl newFullUrl = url.newBuilder()
                    .scheme(newUrl.scheme())
                    .host(newUrl.host())
                    .port(newUrl.port())
                    .build();
            return chain.proceed(builder.url(newFullUrl).build());
        }
        return chain.proceed(request);
    }
}
