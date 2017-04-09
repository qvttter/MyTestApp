package com.example.zhoul_pc.mytestapp.retrofit.service;

import com.example.zhoul_pc.mytestapp.retrofit.bean.ACEntertainmentEntity;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by lili on 2017/4/9.
 */
public interface ACService {

    @GET("index/change")
    Call<ACEntertainmentEntity> getACEntertainmentList(@Query("channelId") int channelId
            , @Query("page") int page);
}
