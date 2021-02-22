package com.example.retrofitutil.api;


import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.*;

import java.util.Map;

public interface RequestBaseInterface {


    @GET
    Observable<ResponseBody> get(@Url String url);

    @GET
    Observable<ResponseBody> getToken(@Url String url, @Header("Token") String token);

    @GET
    Observable<ResponseBody> getQuery(@Url String url, @QueryMap Map<String, Object> map);

    @GET
    Observable<ResponseBody> getQueryToken(@Url String url, @QueryMap Map<String, Object> map, @Header("Token") String token);

    @POST
    @Headers("Accept:application/json")
    Observable<ResponseBody> postJson(@Url String url, @Body RequestBody requestBody);

    @POST
    @Headers("Accept:application/json")
    Observable<ResponseBody> postJSonToken(@Url String url, @Body RequestBody requestBody, @Header("token") String token);

    @FormUrlEncoded
    @POST
    Observable<ResponseBody> postForm(@Url String url, @FieldMap Map<String, Object> map);

    @POST
    Observable<ResponseBody> postMapToken(@Url String url, @FieldMap Map<String, Object> map, @Header("token") String token);

    @POST
    @Headers("Accept:application/json")
    Observable<ResponseBody> postJsonMapToken(@Url String url, @Body RequestBody requestBody, @QueryMap Map<String, Object> map, @Header("token") String token);

    @POST
    @Multipart
    Observable<ResponseBody> postFileToken(@Url String url, @Part MultipartBody.Part file, @Header("token") String token);

    @POST
    @Multipart
    Observable<ResponseBody> postFilesText(@Url String url, @PartMap Map<String, RequestBody> map, @Header("token") String token);

}
