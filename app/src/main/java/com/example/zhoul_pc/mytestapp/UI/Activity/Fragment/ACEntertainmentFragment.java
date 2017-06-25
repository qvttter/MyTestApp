package com.example.zhoul_pc.mytestapp.UI.Activity.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.cleveroad.pulltorefresh.firework.FireworkyPullToRefreshLayout;
import com.example.zhoul_pc.mytestapp.Adapter.FireworkListAdapter;
import com.example.zhoul_pc.mytestapp.Application.App;
import com.example.zhoul_pc.mytestapp.MainActivity;
import com.example.zhoul_pc.mytestapp.R;
import com.example.zhoul_pc.mytestapp.UI.Activity.inter4face.IACEntertainmentView;
import com.example.zhoul_pc.mytestapp.common.ToastUtils;
import com.example.zhoul_pc.mytestapp.presenter.ACEntertainmentPresenter;
import com.example.zhoul_pc.mytestapp.retrofit.bean.ACEntertainmentEntity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by lili on 2017/4/9.
 * acfun,娱乐板块
 */
public class ACEntertainmentFragment extends Fragment implements IACEntertainmentView {
    private View rootView;

//    @BindView(R.id.pullToRefresh)
//    FireworkyPullToRefreshLayout refreshLayout;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private FireworkListAdapter adapter;
    private ACEntertainmentPresenter presenter;
    private int page= 1;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_ac_entertainment, null);
        ButterKnife.bind(this, rootView);
        initView();
        initEvent();

        return rootView;
    }

    public static ACEntertainmentFragment newInstance(String text) {
        ACEntertainmentFragment fragment = new ACEntertainmentFragment();
        Bundle bundle = new Bundle();
        bundle.putString(MainActivity.AC_TYPE, text);
        fragment.setArguments(bundle);
        return fragment;
    }

    private void initEvent() {
//                refreshLayout.setOnRefreshListener(new FireworkyPullToRefreshLayout.OnRefreshListener() {
//                    @Override
//                    public void onRefresh() {
//                        getList();
//                    }
//                });
    }

    private void initView() {
        presenter = new ACEntertainmentPresenter(this);
        adapter = new FireworkListAdapter(new ArrayList<ACEntertainmentEntity.DataBean.HitsBean>(),getActivity());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
        getList();
    }

    @Override
    public void getACEntertainmentList(ArrayList<ACEntertainmentEntity.DataBean.HitsBean> list) {
        Log.e("", "getACEntertainmentList: "+list.size()+list.get(0).getTitle() );
        adapter.setList(list);
    }

    @Override
    public void onFail() {
        ToastUtils.netError(App.getInstance());
    }

    @Override
    public void onFail(String msg) {
        ToastUtils.shortToast(App.getInstance(),msg);
    }

    private void getList(){
        //presenter.getACEntertainmentList(page);
    }
}
