package com.example.zhoul_pc.mytestapp.UI.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.zhoul_pc.mytestapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by lili on 2017/5/29.
 */
public class AddProjectActivity extends AppCompatActivity {
    @BindView(R.id.et_project_name)
    EditText edProjectName;
    @BindView(R.id.iv_cover)
    ImageView ivCover;
    @BindView(R.id.ll_cover)
    LinearLayout llCover;
    @BindView(R.id.et_content)
    EditText etContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_project);
        ButterKnife.bind(this);

        initView();
        initEvent();
    }

    private void initView() {

    }

    private void initEvent() {

    }
}
