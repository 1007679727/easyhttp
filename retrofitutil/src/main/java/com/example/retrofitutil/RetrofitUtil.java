package com.example.retrofitutil;

import android.annotation.SuppressLint;
import android.util.Log;
import androidx.annotation.NonNull;
import com.example.retrofitutil.api.NetCallBack;
import com.example.retrofitutil.api.RequestBaseInterface;
import com.example.retrofitutil.uitl.FileUtil;
import com.example.retrofitutil.uitl.MacAddressUtils;
import com.google.gson.Gson;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.plugins.RxJavaPlugins;
import io.reactivex.schedulers.Schedulers;
import okhttp3.*;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.File;
import java.io.IOException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * Retrofit的工具类，基于单例模式，支持get、post请求、文件的上传下载等
 * 支持cookie和https
 */
public class RetrofitUtil {

    private static ConcurrentHashMap<String, List<Cookie>> cookieStore = new ConcurrentHashMap();
    private static RetrofitUtil retrofitUtil;
    private static String BASE_URL = "http://47.102.36.228:12311/";
    private RequestBaseInterface requestBean;
    private OkHttpClient client;
    private Retrofit retrofit;
    private Gson gson;

    public RetrofitUtil(String baseUrl) {
        BASE_URL = baseUrl;
        init();
        RxJavaPlugins.setErrorHandler(new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                throwable.printStackTrace();
                Log.e("TAG", "accept: "+throwable.toString() );
            }
        });
        retrofitUtil = this;
    }

    /**
     * 默认的初始化方法
     * 创建一个okHttpclient和一个Retrofit
     */
    private void init() {
        gson = new Gson();
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
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request request = chain.request().newBuilder()
                                .addHeader("terminalId", MacAddressUtils.getLocalInetAddress().getHostAddress())
                                .build();
                        return chain.proceed(request);
                    }
                })
                .build();

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
//                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        requestBean = retrofit.create(RequestBaseInterface.class);
    }

    /**
     * @return RetrofitUtil 实例
     */
    public static RetrofitUtil getInstance() {
        if (retrofitUtil == null) {
            retrofitUtil = new RetrofitUtil("http://127.0.0.1");
        }
        return retrofitUtil;
    }

    /**
     * post请求，带bean参数
     *
     * @param url      url
     * @param param    bean类参数
     * @param cls      返回bean类
     * @param callBack 网络执行回调
     */
    public void post(String url, Object param, Class cls, NetCallBack callBack) {
        post(url, gson.toJson(param), cls, callBack);
    }
    public void post(String url, Object param, Class cls, NetCallBack callBack,boolean toMainThread) {
        post(url, gson.toJson(param), cls, callBack,toMainThread);
    }

    /**
     * post请求 带json格式数据
     *
     * @param url      url
     * @param param    json格式数据
     * @param cls      返回bean类
     * @param callBack 网络执行回调
     */
    public void post(String url, String param, Class cls, NetCallBack callBack) {
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json;charset=utf-8"), param);
        sendRequest(requestBean.postJson(url, requestBody), cls, callBack);
    }

    public void post(String url, String param, Class cls, NetCallBack callBack,boolean toMainThread) {
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json;charset=utf-8"), param);
        sendRequestCurrentThread(requestBean.postJson(url, requestBody), cls, callBack);
    }


    /**
     * post请求 带json格式数据+token
     *
     * @param url      url
     * @param param    json格式数据
     * @param token    token
     * @param cls      返回bean类
     * @param callBack 网络执行回调
     */
    public void post(String url, String param, String token, Class cls, NetCallBack callBack) {
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json;charset=utf-8"), param);
        sendRequest(requestBean.postJSonToken(url, requestBody, token), cls, callBack);
    }
    public void post(String url, String param, String token, Class cls, NetCallBack callBack,boolean toMainThread) {
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json;charset=utf-8"), param);
        sendRequestCurrentThread(requestBean.postJSonToken(url, requestBody, token), cls, callBack);
    }

    /**
     * post请求 表单提交
     *
     * @param url      url
     * @param map      Field map
     * @param cls      返回bean类
     * @param callBack 网络执行回调
     */
    public void post(String url, Map<String, Object> map, Class cls, NetCallBack callBack) {
        sendRequest(requestBean.postForm(url, map), cls, callBack);
    }
    public void post(String url, Map<String, Object> map, Class cls, NetCallBack callBack,boolean toMainThread) {
        sendRequestCurrentThread(requestBean.postForm(url, map), cls, callBack);
    }

    /**
     * post请求 表单提交 带token
     *
     * @param url      url
     * @param map      Field map
     * @param token    token
     * @param cls      返回bean类
     * @param callBack 网络执行回调
     */
    public void post(String url, Map<String, Object> map, String token, Class cls, NetCallBack callBack) {
        sendRequest(requestBean.postMapToken(url, map, token), cls, callBack);
    }
    public void post(String url, Map<String, Object> map, String token, Class cls, NetCallBack callBack,boolean toMainThread) {
        sendRequestCurrentThread(requestBean.postMapToken(url, map, token), cls, callBack);
    }

    /**
     * post请求 json + query + token
     *
     * @param url      url
     * @param param    json格式数据
     * @param map      query map
     * @param token    token
     * @param cls      返回bean类
     * @param callBack 网络执行回调
     */
    public void post(String url, String param, Map<String, Object> map, String token, Class cls, NetCallBack callBack) {
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json;charset=utf-8"), param);
        sendRequest(requestBean.postJsonMapToken(url, requestBody, map, token), cls, callBack);
    }

    public void post(String url, String param, Map<String, Object> map, String token, Class cls, NetCallBack callBack,boolean toMainThread) {
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json;charset=utf-8"), param);
        sendRequestCurrentThread(requestBean.postJsonMapToken(url, requestBody, map, token), cls, callBack);
    }

    /**
     * 上传文件单个
     *
     * @param url      url
     * @param file     file
     * @param token    token
     * @param cls      返回bean类
     * @param callBack 网络执行回调
     */
    public void upload(String url, File file, String token, Class cls, NetCallBack callBack) {
        MultipartBody.Part body = FileUtil.getPart("file", file);
        sendRequest(requestBean.postFileToken(url, body, token), cls, callBack);
    }
    public void upload(String url, File file, String token, Class cls, NetCallBack callBack,boolean toMainThread) {
        MultipartBody.Part body = FileUtil.getPart("file", file);
        sendRequestCurrentThread(requestBean.postFileToken(url, body, token), cls, callBack);
    }

    /**
     * 上传多个文件
     *
     * @param url      url
     * @param files    files
     * @param token    token
     * @param cls      返回bean类
     * @param callBack 网络执行回调
     */
    public void uploadFilesAndText(String url, List<File> files, String token, Class cls, NetCallBack callBack) {
        Map map = FileUtil.filesToMultipartBodyParts(files);
        sendRequest(requestBean.postFilesText(url, map, token), cls, callBack);
    }

    public void uploadFilesAndText(String url, List<File> files, String token, Class cls, NetCallBack callBack,boolean toMainThread) {
        Map map = FileUtil.filesToMultipartBodyParts(files);
        sendRequestCurrentThread(requestBean.postFilesText(url, map, token), cls, callBack);
    }
    /**
     * get无参
     *
     * @param url      url
     * @param cls      返回bean类
     * @param callBack 网络执行回调
     */
    public void get(String url, Class cls, NetCallBack callBack) {
        sendRequest(requestBean.get(url), cls, callBack);
    }
    public void get(String url, Class cls, NetCallBack callBack,boolean toMainThread) {
        sendRequestCurrentThread(requestBean.get(url), cls, callBack);
    }

    /**
     * get有token
     *
     * @param url      url
     * @param token    token
     * @param cls      返回bean类
     * @param callBack 网络执行回调
     */
    public void get(String url, String token, Class cls, NetCallBack callBack) {
        sendRequest(requestBean.getToken(url, token), cls, callBack);
    }
    public void get(String url, String token, Class cls, NetCallBack callBack,boolean toMainThread) {
        sendRequestCurrentThread(requestBean.getToken(url, token), cls, callBack);
    }

    /**
     * get 有Query
     *
     * @param url      url
     * @param map      query map
     * @param cls      返回bean类
     * @param callBack 网络执行回调
     */
    public void get(String url, Map<String, Object> map, Class cls, NetCallBack callBack) {
        sendRequest(requestBean.getQuery(url, map), cls, callBack);
    }

    public void get(String url, Map<String, Object> map, Class cls, NetCallBack callBack,boolean toMainThread) {
        sendRequestCurrentThread(requestBean.getQuery(url, map), cls, callBack);
    }
    /**
     * get 有query 和 token
     *
     * @param url      url
     * @param map      query map
     * @param token    token
     * @param cls      返回bean类
     * @param callBack 网络执行回调
     */
    public void get(String url, Map<String, Object> map, String token, Class cls, NetCallBack callBack) {
        sendRequest(requestBean.getQueryToken(url, map, token), cls, callBack);
    }
    public void get(String url, Map<String, Object> map, String token, Class cls, NetCallBack callBack,boolean toMainThread) {
        sendRequestCurrentThread(requestBean.getQueryToken(url, map, token), cls, callBack);
    }

    /**
     * 请求实际发送函数
     *
     * @param observable Observable对象
     * @param cls        返回bean类
     * @param callBack   网络执行回调
     */
    private void sendRequest(Observable observable, final Class cls, final NetCallBack callBack) {
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {
                        try {
                            callBack.onSuccess(gson.fromJson(responseBody.string(), cls));
                        } catch (IOException e) {
                            Log.e("TAG", "onNext: "+e );
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.onError(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void sendRequestCurrentThread(Observable observable, final Class cls, final NetCallBack callBack) {
        observable.subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {
                        try {
                            callBack.onSuccess(gson.fromJson(responseBody.string(), cls));
                        } catch (IOException e) {
                            callBack.onError(e.getMessage());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.onError(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public  void cancel(@NonNull Object tag){
        for (Call call : client.dispatcher().queuedCalls()) {
            if (tag.equals(call.request().tag())) {
                call.cancel();
            }
        }
        for (Call call : client.dispatcher().runningCalls()) {
            if (tag.equals(call.request().tag())) {
                call.cancel();
            }
        }
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
