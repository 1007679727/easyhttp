package com.example.retrofitutil;

import android.util.Log;
import androidx.annotation.NonNull;
import com.example.retrofitutil.datainterface.ResponseBaseInterface;
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
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

public class RetrofitUtil {

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
//                .addInterceptor(new BaseResposeBeanInterceptor())
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
     * Retrofit retrofit = new Retrofit.Builder()
     * .baseUrl(BASE_URL)
     * .addConverterFactory(GsonConverterFactory.create())
     * .build();
     * MyApiEndpointInterface apiServer = retrofit.create(MyApiEndpointInterface.class);
     * Call<RetrofitEntity> call =  apiServer.getAllVideo(true);
     * call.enqueue(new Callback<RetrofitEntity>() {
     *
     * @Override public void onResponse(Call<RetrofitEntity> call, Response<RetrofitEntity> response) {
     * Log.e(TAG, "onResponse: " + response.body());
     * }
     * @Override public void onFailure(Call<RetrofitEntity> call, Throwable t) {
     * <p>
     * }
     * });
     */
    public <T> ResponseBean get(T t) {
        try {
            return get(Bean2ParmaUtil.postUrl(t));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ResponseBean get(String url) {
        return get(url, null);
    }

    public ResponseBean get(String url, boolean changeThread) {
        return get(url, null, changeThread);
    }

    public ResponseBean get(String url, Object tag) {
        return get(url, tag, false);
    }

    public ResponseBean get(@NonNull String url, Object tag, @NonNull boolean changeThread) {
        if (tag != null) {
            baseUrlInterceptor.addTmpRequest2Map(url, tag);
        }
        ResponseBaseInterface responseBaseInterface = retrofit.create(ResponseBaseInterface.class);
        Call<ResponseBean> call = responseBaseInterface.get(url);
        return sendRequest(call, changeThread);
    }

    public ResponseBean get(RequestEntity requestEntity) {
        return get(requestEntity.getUrl(), requestEntity.getTags(), requestEntity.isChangeThread());
    }


//    public ResponseBean post(String url){
//        return post(url,RequestBody.create(MediaType.get()),null,false);
//    }
    public <T> ResponseBean post(T t) {
        try {
            return post(Bean2ParmaUtil.postUrl(t));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ResponseBean post(RequestEntity requestEntity) {
        return post(requestEntity.getUrl(), requestEntity.getBody(), requestEntity.getTags(),requestEntity.isChangeThread());
    }

    public <T> ResponseBean post(@NonNull String url, RequestBody body,Object tag, @NonNull boolean changeThread) {
        if (tag != null) {
            baseUrlInterceptor.addTmpRequest2Map(url, tag);
        }
        ResponseBaseInterface responseBaseInterface = retrofit.create(ResponseBaseInterface.class);
        Call<ResponseBean> call = responseBaseInterface.post(url, body);
        return sendRequest(call, changeThread);
    }

    private ResponseBean sendRequest(Call call, boolean changeThread) {
        if (changeThread) {
            call.enqueue(new Callback<ResponseBean>() {
                @Override
                public void onResponse(Call<ResponseBean> call, Response<ResponseBean> response) {
                    Log.e("TAG", "onResponse: success");
                }

                @Override
                public void onFailure(Call<ResponseBean> call, Throwable t) {
                    Log.e("TAG", "onResponse: fail" + t);
                }
            });
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

    public <T> void upload(T t) {

        try {
            post(Bean2ParmaUtil.postUrl(t));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }

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

    public <T> void create(T t,T t2) {

    }

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
