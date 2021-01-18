package com.example.retrofitutil;

import android.util.Log;
import androidx.annotation.NonNull;
import com.example.retrofitutil.datainterface.RequestBaseInterface;
import com.example.retrofitutil.datainterface.ResponseBean;
import com.example.retrofitutil.uitl.Constant;
import okhttp3.*;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

public class RetrofitUtil {

    private static final CharSequence GET = "get";
    private static final CharSequence POST = "post";
    private static RetrofitUtil retrofitUtil = new RetrofitUtil();
    private static ConcurrentHashMap<String, List<Cookie>> cookieStore = new ConcurrentHashMap();
    private BaseUrlInterceptor baseUrlInterceptor = new BaseUrlInterceptor();
    private Retrofit retrofit;

    private RetrofitUtil() {
        defaultBuilderMethod();
    }

    private void defaultBuilderMethod() {
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(1, TimeUnit.MINUTES)
                .cookieJar(new CookieJar() {
                    @Override
                    public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
                        cookieStore.put(url.host(), cookies);
                    }

                    @Override
                    public List<Cookie> loadForRequest(HttpUrl url) {
                        List<Cookie> cookies = cookieStore.get(url.host());
                        if (cookies != null) {
                            return cookies;
                        }
                        return new ArrayList<Cookie>();
                    }
                })
                .sslSocketFactory(createSSLSocketFactory(), new TrustAllCerts())
                .addInterceptor(baseUrlInterceptor)
                .build();

        retrofit = new Retrofit.Builder()
                .baseUrl(Constant.BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    public static RetrofitUtil getInstance() {
        return retrofitUtil;
    }

    /**
     * 默认泛型的get请求方式
     *
     * @param t
     * @param <T>
     * @return
     * @throws Exception
     */
    public <T> ResponseBean get(T t) throws Exception {
        return get(Bean2ParmaUtil.postUrl(t), null);
    }

    /**
     * 泛型请求
     *
     * @param t                泛型，java bean对象
     * @param requestInterface
     * @param <T>
     * @return
     */
    public <T> ResponseBean get(T t, Class<?> requestInterface) throws Exception {
        return get(Bean2ParmaUtil.postUrl(t), requestInterface);
    }

    public <T> ResponseBean get(T t, Class<?> requestInterface, Callback callback) throws Exception {
        RequestEntity url = Bean2ParmaUtil.postUrl(t);
        return get(Bean2ParmaUtil.postUrl(t), requestInterface, callback);
    }

    /**
     * 处理实体类的get请求
     *
     * @param requestEntity
     * @param requestInterface
     * @return
     * @throws Exception
     */
    public ResponseBean get(RequestEntity requestEntity, Class<?> requestInterface, Callback callback) throws Exception {
        return get(requestEntity.getUrl(), requestEntity.getTags(), requestInterface, callback);
    }

    /**
     * 默认的get请求
     *
     * @param url
     * @return
     * @throws Exception
     */
    public ResponseBean get(String url) throws Exception {
        return get(url, null, null);
    }

    public ResponseBean get(String url, Callback callback) throws Exception {
        return get(url, null, callback);
    }

    /**
     * 带有指定类型的get请求
     *
     * @param url
     * @param requestInterface
     * @return
     * @throws Exception
     */
    public ResponseBean get(String url, Class<?> requestInterface, Callback callback) throws Exception {
        return get(url, null, requestInterface, callback);
    }

    /**
     * get请求实际封装位置
     * 由于通过反射的方式拿到传入对象的方法
     *
     * @param url              请求地址
     * @param tag              http请求使用的tag
     * @param requestInterface retrofit封装的方法
     * @return
     */
    public ResponseBean get(@NonNull String url, Object tag, Class<?> requestInterface, Callback callback) throws Exception {
        if (tag != null) {
            baseUrlInterceptor.addTmpRequest2Map(url, tag);
        }
        Call call = null;
        if (requestInterface == null) {
            RequestBaseInterface request = retrofit.create(RequestBaseInterface.class);
            call = request.get(url);
        } else {
            Object response = retrofit.create(requestInterface);
            for (Method declaredMethod : response.getClass().getDeclaredMethods()) {
                if (declaredMethod.getName().contains(GET))
                    try {
                        call = (Call) declaredMethod.invoke(response, url);
                    } catch (Exception e) {
                        throw new Exception("don't create retrofit call! please check your interface has get method?");
                    }
            }
        }
        return sendRequest(call, callback);
    }

    /**
     * 默认的请求类型 泛型
     *
     * @param t
     * @param <T>
     * @return
     * @throws Exception
     */
    public <T> ResponseBean post(@NonNull T t) throws Exception {
        return post(t, null);
    }

    public <T> ResponseBean post(@NonNull T t, Callback callback) throws Exception {
        return post(Bean2ParmaUtil.postUrl(t), callback);
    }

    public ResponseBean post(@NonNull RequestEntity requestEntity, Callback callback) throws Exception {
        return post(requestEntity.getUrl(), requestEntity.getBody(), requestEntity.getTags(), callback, null);
    }

    public ResponseBean post(@NonNull String url, RequestBody body) throws Exception {
        return post(url, body, null);
    }

    public ResponseBean post(@NonNull String url, RequestBody body, Object tag) throws Exception {
        return post(url, body, null, null);
    }

    public ResponseBean post(@NonNull String url, RequestBody body, Object tag, Callback callback) throws Exception {
        return post(url, body, tag, callback, null);
    }

    /**
     * post请求
     *
     * @param url              非空，post请求的url
     * @param body             http body
     * @param tag              http tag
     * @param callback         回调函数，若同步执行不需要回调
     * @param requestInterface 请求实现类型
     * @return
     * @throws Exception
     */
    public ResponseBean post(@NonNull String url, RequestBody body, Object tag, Callback callback, Class<?> requestInterface) throws Exception {
        if (tag != null) {
            baseUrlInterceptor.addTmpRequest2Map(url, tag);
        }
        Call call = null;
        if (requestInterface == null) {
            RequestBaseInterface request = retrofit.create(RequestBaseInterface.class);
            call = request.post(url, body);
        } else {
            Object response = retrofit.create(requestInterface);
            for (Method declaredMethod : response.getClass().getDeclaredMethods()) {
                if (declaredMethod.getName().contains(POST))
                    try {
                        call = (Call) declaredMethod.invoke(response, url, body);
                    } catch (Exception e) {
                        throw new Exception("don't create retrofit call! please check your interface has get method?");
                    }
            }
        }
        return sendRequest(call, callback);
    }

    /**
     * 上传文件的功能实现，使用的方法是multipartBody
     * 尚未实现断点续传
     *
     * @param t
     * @param uploadSuccessCallback
     * @param <T>
     */
    public <T> void upload(T t, @NonNull Callback uploadSuccessCallback) {
        try {
            post(Bean2ParmaUtil.postUrl(t), uploadSuccessCallback);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    /**
     * 实际请求发送的地方
     * 同步方法返回一个Bean类，提供基础方法getValue
     * 异步方法则传入一个回调函数
     *
     * @param call
     * @param callback
     * @return ResponseBean
     */
    private ResponseBean sendRequest(Call call, Callback callback) {
        if (callback != null) {
            call.enqueue(callback);
        } else {
            try {
                Response<ResponseBean> response = call.execute();
                response.body().getValue();
                return response.body();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 用于SSL，可忽略
     *
     * @return
     */
    private static SSLSocketFactory createSSLSocketFactory() {
        SSLSocketFactory ssfFactory = null;

        try {
            SSLContext sc = SSLContext.getInstance("TLS");
            sc.init(null, new TrustManager[]{new TrustAllCerts()}, new SecureRandom());

            ssfFactory = sc.getSocketFactory();
        } catch (Exception e) {
        }

        return ssfFactory;
    }

    /**
     * 支持X509
     */
    private static class TrustAllCerts implements X509TrustManager {
        @Override
        public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        }

        @Override
        public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        }

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[0];
        }
    }
}
