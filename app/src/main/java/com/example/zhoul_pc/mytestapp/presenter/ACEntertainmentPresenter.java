package com.example.zhoul_pc.mytestapp.presenter;

import com.example.zhoul_pc.mytestapp.UI.Activity.inter4face.IACEntertainmentView;
import com.example.zhoul_pc.mytestapp.retrofit.bean.ACEntertainmentEntity;
import com.example.zhoul_pc.mytestapp.retrofit.biz.ACEBiz;
import com.example.zhoul_pc.mytestapp.retrofit.inter4face.IACBiz;
import com.example.zhoul_pc.mytestapp.retrofit.listener.OnCommonRetrofitListener;

import java.util.ArrayList;

/**
 * Created by lili on 2017/4/9.
 */
public class ACEntertainmentPresenter {
    private IACEntertainmentView iacEntertainmentView;
    private IACBiz iacBiz;

    public ACEntertainmentPresenter(IACEntertainmentView iacEntertainmentView) {
        this.iacEntertainmentView = iacEntertainmentView;
        this.iacBiz = new ACEBiz();
    }

    public void getACEntertainmentList(int page){
        iacBiz.getACEntertainMentList(page, new OnCommonRetrofitListener() {
            @Override
            public void onSuccess(Object o) {
                iacEntertainmentView.getACEntertainmentList((ArrayList<ACEntertainmentEntity.DataBean.HitsBean>) o);
            }

            @Override
            public void onFail() {
                iacEntertainmentView.onFail();
            }

            @Override
            public void onFail(String msg) {
                iacEntertainmentView.onFail(msg);
            }
        });
    }
}
