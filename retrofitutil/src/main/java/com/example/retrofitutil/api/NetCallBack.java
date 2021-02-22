package com.example.retrofitutil.api;

public interface NetCallBack<T> {
    void onSuccess(T t);
    void onError(T t);
}
