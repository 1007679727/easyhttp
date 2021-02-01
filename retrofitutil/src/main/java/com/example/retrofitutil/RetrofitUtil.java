package com.example.retrofitutil;

import android.text.TextUtils;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.example.retrofitutil.datainterface.RequestBaseInterface;
import com.example.retrofitutil.datainterface.ResponseBean;
import com.example.retrofitutil.exception.CreateCallFailException;
import com.example.retrofitutil.exception.NullBaseURLException;
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
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * Retrofit的工具类，基于单例模式，支持get、post请求、文件的上传下载等
 * 支持cookie和https
 */
public class RetrofitUtil {

    private static final CharSequence GET = "get";
    private static final CharSequence POST = "post";
    private static ConcurrentHashMap<String, List<Cookie>> cookieStore = new ConcurrentHashMap();
    private static RetrofitUtil retrofitUtil;
    private static String BASE_URL;
    private BaseUrlInterceptor baseUrlInterceptor;
    private Retrofit retrofit;
    private OkHttpClient client;

    private RetrofitUtil() {
        BASE_URL = "127.0.0.1";
        baseUrlInterceptor = new BaseUrlInterceptor();
        init();
        retrofitUtil = this;
    }

    public RetrofitUtil(String BASE_URL){
        this.BASE_URL = BASE_URL;
        this.baseUrlInterceptor = new BaseUrlInterceptor();
        init();
        retrofitUtil = this;
    }

    public RetrofitUtil(String BASE_URL,BaseUrlInterceptor interceptor){
        this.BASE_URL = BASE_URL;
        this.baseUrlInterceptor = interceptor;
        init();
        retrofitUtil = this;
    }

    /**
     * 默认的初始化方法
     * 创建一个okHttpclient和一个Retrofit
     */
    private void init() {
        client = new OkHttpClient.Builder()
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
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    /**
     * 获取retrofitUtil对象
     * @return RetrofitUtil
     * @throws NullBaseURLException
     */
    public static RetrofitUtil getInstance() throws NullBaseURLException {
        if (BASE_URL == null) {
            throw new NullBaseURLException();

        }
        return retrofitUtil;
    }

    public void setBaseURL(String baseUrl) {
        this.BASE_URL = baseUrl;
    }

    public void setBaseUrlInterceptor(){
    }

    /**
     * 同步实现的 默认泛型的get请求方式
     * @param t 泛型，java bean对象
     * @param <T>
     * @return
     * @throws CreateCallFailException
     */
    public <T> ResponseBean get(T t) throws CreateCallFailException {
        return get(Bean2ParmaUtil.parseUrl(t), null);
    }

    /**
     * 同步实现的 泛型请求
     * @param t 泛型，java bean对象
     * @param requestInterface 请求接口，参考retrofit请求实现，必须为接口
     * @param <T>
     * @return
     * @throws CreateCallFailException
     */
    public <T> ResponseBean get(T t, Class<?> requestInterface) throws CreateCallFailException {
        return get(Bean2ParmaUtil.parseUrl(t), requestInterface,null);
    }

    /**
     * 异步实现的 泛型请求，回调方法在主线程
     * @param t 泛型，java bean对象
     * @param requestInterface 请求接口，参考retrofit请求实现，必须为接口
     * @param callback
     * @param <T>
     * @return
     * @throws CreateCallFailException
     */
    public <T> ResponseBean get(T t, Class<?> requestInterface, Callback callback) throws CreateCallFailException {
        return get(Bean2ParmaUtil.parseUrl(t), requestInterface, callback);
    }

    /**
     * 处理实体类的get请求
     * @param requestEntity 请求实体
     * @param requestInterface 请求接口，参考retrofit请求实现，必须为接口
     * @return
     * @throws CreateCallFailException
     */
    public ResponseBean get(RequestEntity requestEntity, Class<?> requestInterface, Callback callback) throws CreateCallFailException {
        return get(requestEntity.getUrl(), requestEntity.getTags(),requestEntity.getHeaders(), requestInterface, callback);
    }

    /**
     * 同步 默认的get请求
     * 基于url请求
     * @param url
     * @return
     * @throws CreateCallFailException
     */
    public ResponseBean get(String url) throws CreateCallFailException {
        return get(url, null, null);
    }

    /**
     * 异步 默认的get请求
     * 基于url请求，回调函数在主线程
     * @param url
     * @return
     * @throws CreateCallFailException
     */
    public ResponseBean get(String url, Callback callback) throws CreateCallFailException {
        return get(url, null,null, null,callback);
    }

    /**
     * get请求实际封装位置
     * 通过反射的方式拿到传入对象的方法
     * @param url 非空地址
     * @param tag tag 可为空
     * @param headersMap headers 可为空
     * @param requestInterface 请求接口 可为空，为空则给定默认值
     * @param callback 回调函数、如果是异步则传入
     * @return
     * @throws CreateCallFailException 由于未知错误，导致创建call失败，具体原因需要具体分析
     */
    public ResponseBean get(@NonNull String url, Object tag, Map<String, String> headersMap,Class<?> requestInterface, Callback callback) throws CreateCallFailException {
        dealTagAndHeader(url,tag,headersMap);
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
                        e.printStackTrace();
                        throw new CreateCallFailException();
                    }
            }
        }
        return sendRequest(call, callback);
    }

    private void dealTagAndHeader(String url ,Object tag, Map<String, String> headersMap) {
        if (tag != null) {
            baseUrlInterceptor.addTmpRequest2Map(url, tag);
        }
        if (headersMap != null){
            Headers.Builder headers = new Headers.Builder();
            Iterator it = headersMap.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry en = (Map.Entry) it.next();
                String key = (String) en.getKey();
                String val = (String) en.getValue();
                headers.add(key, val);
            }
            baseUrlInterceptor.addHeaders(url,headers.build());
        }
    }

    /**
     * 同步的 默认的请求类型 泛型
     *
     * @param t
     * @param <T>
     * @return
     * @throws Exception
     */
    public <T> ResponseBean post(@NonNull T t) throws Exception {
        return post(t, null);
    }

    /**
     * 异步的 切换线程的post请求
     * @param t
     * @param callback
     * @param <T>
     * @return
     * @throws Exception
     */
    public <T> ResponseBean post(@NonNull T t, Callback callback) throws Exception {
        return post(Bean2ParmaUtil.parseUrl(t), callback);
    }

    public ResponseBean post(@NonNull RequestEntity requestEntity, Callback callback) throws Exception {
        return post(requestEntity.getUrl(), requestEntity.getBody(), requestEntity.getTags(),requestEntity.getHeaders(), callback, null);
    }

    public ResponseBean post(@NonNull String url, RequestBody body, Object tag,Map<String, String> headers, Callback callback) throws Exception {
        return post(url, body, tag, headers, callback, null);
    }

    public <T> void post(@NonNull String url, @NonNull Map<String, String> params, @NonNull Object tag, final Class<T> resCls, @Nullable Callback callback) {
        FormBody.Builder builder = new FormBody.Builder();
        for (String key : params.keySet()) {
            builder.add(key, params.get(key));
        }
        RequestBody requestBody = builder.build();
        try {
            post(url, requestBody, tag, null, callback, resCls);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public <T> void post(@NonNull String url, @NonNull String param, @NonNull Object tag, final Class<T> resCls, @Nullable Callback callback) {
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json;charset=utf-8"), param);
        try {
            post(url,requestBody,tag,null,callback ,resCls);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public <T> void post(@NonNull String url, @NonNull String param, final Map<String, String> headers, @NonNull Object tag, final Class<T> resCls, @Nullable Callback callback) {
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json;charset=utf-8"), param);
        try {
            post(url, requestBody, tag, headers, callback,resCls);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * post请求
     * @param url              非空，post请求的url
     * @param body             http body
     * @param tag              http tag
     * @param headersMap
     * @param callback         回调函数，若同步执行不需要回调
     * @param requestInterface 请求实现类型
     * @return
     * @throws Exception
     */
    public ResponseBean post(@NonNull String url, @NonNull RequestBody body, Object tag, Map<String, String> headersMap, Callback callback, Class<?> requestInterface) throws Exception {
        dealTagAndHeader(url,tag,headersMap);
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
     * @param t
     * @param callback
     * @param <T>
     */
    public <T> void upload(T t, @NonNull Callback callback) {
        try {
            post(Bean2ParmaUtil.parseUrl(t), callback);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public <T> void upload(@NonNull String url, @NonNull Map<String, String> params, @NonNull File file, @NonNull String name, @NonNull Object tag, Class<T> resCls, @NonNull Callback callback) {
        MultipartBody.Builder builder = new MultipartBody.Builder();
        builder.setType(MultipartBody.FORM);
        if (params != null) {
            for (String key : params.keySet()) {
                if (!params.get(key).isEmpty()) {
                    builder.addFormDataPart(key, params.get(key));
                }
            }
        }
        builder.addFormDataPart(name, file.getName(), RequestBody.create(MediaType.parse("application/octet-stream"), file));
        RequestBody requestBody = builder.build();
        try {
            post(url, requestBody, tag, null, callback, resCls);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public <T> void uploadFiles(@NonNull String url, @NonNull Map<String, String> params, @NonNull List<File> files, @NonNull String name, @NonNull Object tag, Class<T> resCls, @Nullable Callback callback) {
        MultipartBody.Builder builder = new MultipartBody.Builder();
        builder.setType(MultipartBody.FORM);
        if (params != null) {
            for (String key : params.keySet()) {
                if (!TextUtils.isEmpty(params.get(key))) {
                    builder.addFormDataPart(key, params.get(key));
                }
            }
        }
        for (File file : files) {
            builder.addFormDataPart(name, file.getName(), RequestBody.create(MediaType.parse(Bean2ParmaUtil.guessMimeType(file.getName())), file));
        }
        RequestBody requestBody = builder.build();
        try {
            post(url, requestBody, tag, null, callback, resCls);
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
