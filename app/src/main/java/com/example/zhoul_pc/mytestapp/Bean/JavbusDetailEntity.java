package com.example.zhoul_pc.mytestapp.Bean;

import java.io.Serializable;

/**
 * Created by lili on 2017/6/24.
 */
public class JavbusDetailEntity implements Serializable{
    private String title;
    private String mainImg;

    public String getMainImg() {
        return mainImg;
    }

    public void setMainImg(String mainImg) {
        this.mainImg = mainImg;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
