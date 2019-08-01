package com.example.zhoul_pc.mytestapp.view;

import android.support.v7.app.AppCompatActivity;

import com.afollestad.materialdialogs.MaterialDialog;

/**
 * Created by lili on 2017/6/24.
 */
public class BaseActivity extends AppCompatActivity{
    private MaterialDialog materialDialog;

    protected void showDialog(){
        materialDialog = new MaterialDialog.Builder(this)
                .title("提示")
                .content("加载中，请稍后...")
                .progress(true,0)
                .show();
    }

    protected void showDialog(String msg){
        materialDialog = new MaterialDialog.Builder(this)
                .title("提示,23e")
                .content(msg)
                .progress(true,0)
                .show();
    }

    protected void dismisDialog(){
        if (materialDialog!=null){
            materialDialog.dismiss();
        }else{
            return;
        }
    }
}
