package com.example.zhoul_pc.mytestapp.view;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.example.zhoul_pc.mytestapp.R;

/**
 * Created by lili on 2017/6/10.
 */
public class CustomImageView extends View {
    /**
     * 第一圈的颜色
     */
    private int mFirstColor ;
    /**
     * 第二圈的颜色
     */
    private int mSecondColor ;
    /**
     * 圈的宽度
     */
    private int mCircleWidth ;
    /**
     * 画笔
     */
    private Paint mPaint;



    public CustomImageView(Context context, AttributeSet attrs)
    {
        this(context, attrs, 0);
        mPaint = new Paint();
    }

    public CustomImageView(Context context)
    {
        this(context, null);
    }

    /**
     * 必要的初始化，获得一些自定义的值
     *
     * @param context
     * @param attrs
     * @param defStyle
     */
    public CustomImageView(Context context, AttributeSet attrs, int defStyle)
    {
        super(context, attrs, defStyle);

    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        mCircleWidth = 50;
        mFirstColor = getContext().getResources().getColor(R.color.colorPrimary);

        int centre = getWidth() / 2; // 获取圆心的x坐标
        int radius = centre - mCircleWidth / 2;// 半径
        mPaint.setStrokeWidth(mCircleWidth); // 设置圆环的宽度
        mPaint.setAntiAlias(true); // 消除锯齿
        mPaint.setStyle(Paint.Style.STROKE); // 设置空心
//        RectF oval = new RectF(centre - radius, centre - radius, centre + radius, centre + radius); // 用于定义的圆弧的形状和大小的界限
        mPaint.setColor(mFirstColor); // 设置圆环的颜色
        canvas.drawCircle(centre, centre, radius, mPaint); // 画出圆环
//        mPaint.setColor(mSecondColor); // 设置圆环的颜色

    }
}
