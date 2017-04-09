package com.example.zhoul_pc.mytestapp.retrofit.biz;

import com.example.zhoul_pc.mytestapp.common.BaseUrl;
import com.example.zhoul_pc.mytestapp.retrofit.bean.ACEntertainmentEntity;
import com.example.zhoul_pc.mytestapp.retrofit.inter4face.IACBiz;
import com.example.zhoul_pc.mytestapp.retrofit.listener.OnCommonRetrofitListener;
import com.example.zhoul_pc.mytestapp.retrofit.service.ACService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by lili on 2017/4/9.
 */
public class ACEBiz implements IACBiz {
    private Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BaseUrl.BASE_URL_AC)
            .addConverterFactory((GsonConverterFactory.create()))
            .build();

    ACService service = retrofit.create(ACService.class);

    @Override
    public void getACEntertainMentList(int page, final OnCommonRetrofitListener listener) {
        service.getACEntertainmentList(60,page)
                .enqueue(new Callback<ACEntertainmentEntity>() {
                    @Override
                    public void onResponse(Call<ACEntertainmentEntity> call, Response<ACEntertainmentEntity> response) {
                        if (response.isSuccessful()){
                            listener.onSuccess(response.body().getData().getHits());
                        }
                    }

                    @Override
                    public void onFailure(Call<ACEntertainmentEntity> call, Throwable t) {
                        listener.onFail();
                    }
                });
    }

}
