package com.example.retrofitutil.datainterface;

import com.example.retrofitutil.MacAddressUtils;
import com.example.retrofitutil.constant.Constant;
import retrofit2.Call;
import retrofit2.http.*;

public interface ResponseBaseInterface {
    @GET
    Call<DataBean> get(@Url String url,boolean changeThread);

    @POST
    Call<DataBean> post(@Url String url,boolean changeThread);

}
