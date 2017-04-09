package com.example.zhoul_pc.mytestapp.UI.Activity.inter4face;

import com.example.zhoul_pc.mytestapp.retrofit.bean.ACEntertainmentEntity;

import java.util.ArrayList;

/**
 * Created by lili on 2017/4/9.
 */
public interface IACEntertainmentView {
    void getACEntertainmentList(ArrayList<ACEntertainmentEntity.DataBean.HitsBean> list);
    void onFail();
    void onFail(String msg);
}
