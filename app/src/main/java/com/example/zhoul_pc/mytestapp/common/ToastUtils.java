package com.example.zhoul_pc.mytestapp.common;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by lili on 2017/4/9.
 */
public class ToastUtils {
    public static void shortToast(Context context,String toast){
        Toast.makeText(context,toast,Toast.LENGTH_SHORT).show();
    }

    public static void netError(Context context){
        Toast.makeText(context,"连接失败，请稍后再试",Toast.LENGTH_SHORT).show();
    }
}
