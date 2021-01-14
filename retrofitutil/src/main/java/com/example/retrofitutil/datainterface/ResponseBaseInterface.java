package com.example.retrofitutil.datainterface;

import com.example.retrofitutil.MacAddressUtils;
import com.example.retrofitutil.ResponseBean;
import com.example.retrofitutil.constant.Constant;
import retrofit2.Call;
import retrofit2.http.*;

public interface ResponseBaseInterface {
    @GET
    Call<ResponseBean> get(@Url String url);

    @POST
    Call<ResponseBean> post(@Url String url,@Body Body body);

}
