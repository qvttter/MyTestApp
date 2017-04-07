package com.example.zhoul_pc.mytestapp.UI.Activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.cleveroad.pulltorefresh.firework.FireworkyPullToRefreshLayout;
import com.example.zhoul_pc.mytestapp.Adapter.FireworkListAdapter;
import com.example.zhoul_pc.mytestapp.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/4/6.
 */

public class FireworkListActivity extends AppCompatActivity {
    @BindView(R.id.pullToRefresh)
    FireworkyPullToRefreshLayout refreshLayout;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private FireworkListAdapter adapter;
    private List<String> list;
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firework_list);
        ButterKnife.bind(this);
        initView();
        initEvent();

    }

    private void initEvent() {
        refreshLayout.setOnRefreshListener(new FireworkyPullToRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                relay(3000);
            }
        });
    }

    private void initView() {
        list = new ArrayList<>();
        handler = new Handler();
        for (int i = 0; i < 60; i++) {
            String str = "我是第"+ i + "条数据";
            list.add(str);
        }
        adapter = new FireworkListAdapter(list,this);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    private void resetDate(){
        list.clear();
        for (int i = 0; i < 60; i++) {
            String str = "我是第"+ i +";"+System.currentTimeMillis();
            list.add(str);
        }
        refreshLayout.setRefreshing(false);
    }

    private void relay(int time){
        handler.postDelayed(mRunnable, time);
    }

    private Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            resetDate();
        }
    };
}
