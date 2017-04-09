package com.example.zhoul_pc.mytestapp.Application;

import android.app.Application;

/**
 * Created by lili on 2017/4/9.
 */
public class App extends Application{
    private static App instance;
    public static synchronized App getInstance(){
        return instance;
    }
}
