package com.example.zhoul_pc.mytestapp.retrofit.listener;

/**
 * Created by ZHOUL-PC on 2016/11/28.
 */

public interface OnCommonRetrofitListener<T> {
    void onSuccess(T t);
    void onFail();
    void onFail(String msg);
}
