package com.example.retrofitutil;

import android.util.Log;
import androidx.annotation.NonNull;
import com.example.retrofitutil.constant.Constant;
import com.example.retrofitutil.datainterface.ResponseBaseInterface;
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
import java.util.concurrent.TimeUnit;

public class RetrofitUtil {

    private static RetrofitUtil retrofitUtil = new RetrofitUtil();
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
                        Constant.cookieStore.put(url.host(), cookies);
                    }

                    @Override
                    public List<Cookie> loadForRequest(HttpUrl url) {
                        List<Cookie> cookies = Constant.cookieStore.get(url.host());
                        if (cookies != null) {
                            return cookies;
                        }
                        return new ArrayList<Cookie>();
                    }
                })
                .sslSocketFactory(createSSLSocketFactory(), new TrustAllCerts())
                .addInterceptor(new BaseUrlInterceptor())
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

    public ResponseBean get(String url) {
        return get(url, null);
    }

    public ResponseBean get(String url, Object tag) {
        return get(url, tag, false);
    }


    public ResponseBean get(@NonNull String url, Object tag, @NonNull boolean changeThread) {
        ResponseBaseInterface responseBaseInterface = retrofit.create(ResponseBaseInterface.class);
        Call<ResponseBean> call = responseBaseInterface.get(url);
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
                Response<ResponseBean> s = call.execute();
                return s.body();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public <T> ResponseBean post(@NonNull String url, T t, @NonNull boolean changeThread){

        return null;
    }

    public <T> void upload(T t, ResponseListener listener) {
        MultipartBody.Builder builder = new MultipartBody.Builder();
        builder.setType(MultipartBody.FORM);
        try {
            Bean2ParmaUtil.getUrl(t);
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
