package com.example.zhoul_pc.mytestapp.UI.Activity.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.zhoul_pc.mytestapp.MainActivity;
import com.example.zhoul_pc.mytestapp.R;

import butterknife.ButterKnife;

/**
 * Created by lili on 2017/4/9.
 */
public class ACGameFragment extends Fragment {

    private View rootView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_ac_entertainment, null);
        ButterKnife.bind(this, rootView);
        initView();
        initEvent();

        return rootView;
    }

    private void initEvent() {

    }

    private void initView() {

    }

    public static ACGameFragment newInstance(String text) {
        ACGameFragment fragment = new ACGameFragment();
        Bundle bundle = new Bundle();
        bundle.putString(MainActivity.AC_TYPE, text);
        fragment.setArguments(bundle);
        return fragment;
    }
}
