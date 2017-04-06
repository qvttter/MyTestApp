package com.example.zhoul_pc.mytestapp.UI.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import com.cleveroad.pulltorefresh.firework.FireworkyPullToRefreshLayout;
import com.example.zhoul_pc.mytestapp.R;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firework_list);
        ButterKnife.bind(this);
        initView();
        initEvent();

    }

    private void initEvent() {

    }

    private void initView() {
    }
}
