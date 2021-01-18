package com.example.retrofitutil.datainterface;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.*;

public interface RequestBaseInterface {
    @GET
    Call get(@Url String url);

    @POST
    Call post(@Url String url,@Body RequestBody body);

}
