package com.example.zhoul_pc.mytestapp.UI.Activity;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.example.zhoul_pc.mytestapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by lili on 2017/5/29.
 */
public class PaintActivity extends AppCompatActivity{
    @BindView(R.id.ll)
    LinearLayout ll;
    @BindView(R.id.btn_ok)
    Button btnOk;
    @BindView(R.id.et_num)
    EditText etNum;
    @BindView(R.id.btn_drop)
    Button btnDrop;

    private int screenWidth = 0;
    private int screenHeight = 0;

    //获取起点坐标
//    int[] location2 = new int[2];
//    readingIV.getLocationInWindow(location2);
//    int x1 = location2[0];
//    int y1 = location2[1];
//    //获取终点坐标，最近拍摄的坐标
//    int[] location = new int[2];
//    mRecentPhoto.getLocationInWindow(location);
//    int x2 = location[0];
//    int y2 = location[1];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paint);
        ButterKnife.bind(this);
        initView();
        initEvent();
    }

    private void initEvent() {
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PaintView paintView = new PaintView(PaintActivity.this,
                        null, Integer.parseInt(etNum.getText().toString()));
                ll.removeAllViews();
                ll.addView(paintView);
            }
        });

        btnDrop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void initView(){
        WindowManager windowManager= (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
        Display display=windowManager.getDefaultDisplay();
        screenWidth=display.getWidth();
        screenHeight = display.getHeight();
    }

    private void animator(){
        //抛物线动画
//        ObjectAnimator translateAnimationX = ObjectAnimator.ofFloat(view, "translationX", 0, -(x1 - x2) - offsetX);
//        translateAnimationX.setDuration(800);
//        translateAnimationX.setInterpolator(new LinearInterpolator());
////        translateAnimationX.start();
//        ObjectAnimator translateAnimationY = ObjectAnimator.ofFloat(view, "translationY", 0, y2 - y1 + offsetY);
//        translateAnimationY.setDuration(800);
//        translateAnimationY.setInterpolator(new AccelerateInterpolator());
//
//        //缩小动画
//        ObjectAnimator scaleX = ObjectAnimator.ofFloat(view, "scaleX", 1, 0);
//        scaleX.setDuration(200);
//        ObjectAnimator scaleY = ObjectAnimator.ofFloat(view, "scaleY", 1, 0);
//        scaleY.setDuration(200);
//        scaleY.addListener(new Animator.AnimatorListener() {
//            @Override
//            public void onAnimationStart(Animator animation) {
//            }
//
//            @Override
//            public void onAnimationEnd(Animator animation) {
//                anim_mask_layout.removeView(readingIV);
//                setLvTouch(activity, false);
//            }
//
//            @Override
//            public void onAnimationCancel(Animator animation) {
//            }
//
//            @Override
//            public void onAnimationRepeat(Animator animation) {
//            }
//        });
//        AnimatorSet animatorSet = new AnimatorSet();
//        animatorSet.play(translateAnimationX).with(translateAnimationY);
//        animatorSet.play(scaleX).with(scaleY).after(translateAnimationX);
//        animatorSet.start();
    }
}
