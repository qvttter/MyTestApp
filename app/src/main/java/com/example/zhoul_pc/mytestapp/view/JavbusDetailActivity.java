package com.example.zhoul_pc.mytestapp.view;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.zhoul_pc.mytestapp.Bean.JavbusDetailEntity;
import com.example.zhoul_pc.mytestapp.R;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by lili on 2017/6/24.
 */
public class JavbusDetailActivity extends BaseActivity {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_main_img)
    ImageView ivMainImg;

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_javbus_detail);
        ButterKnife.bind(this);
        initView();
        initEvent();
        new Thread(networkTask).start();
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Bundle data = msg.getData();
            JavbusDetailEntity val = (JavbusDetailEntity) data.getSerializable("value");
            // TODO
            // UI界面的更新等相关操作

            tvTitle.setText(val.getTitle());
            Glide.with(context)
                    .load(val.getMainImg())
                    .into(ivMainImg);

        }
    };

    Runnable networkTask = new Runnable() {

        @Override
        public void run() {
            // TODO
            Document doc = null;
            JavbusDetailEntity entity = new JavbusDetailEntity();
            try {
                doc = Jsoup.connect("https://www.javbus2.com/GVG-126").get();
                Element body = doc.body();
                //Elements containerFluid = body.select("div.container-fluid");//class型用“.”，id的用#
                Elements container = body.select("div.container");
                entity.setTitle(container.select("h3").text());
                Elements rowMovie = container.select("div.movie");
                Elements screenCap = rowMovie.select("div.screencap");
                entity.setMainImg(screenCap.select("a.bigImage").attr("href"));
                Elements info = rowMovie.select("div.info");
                String str = info.select("p").text();
                entity.setFanhao(str);
//                Elements waterfall = container.select("div#waterfall");
//                Elements item = waterfall.select("div.item masonry-brick");

            } catch (IOException e) {
                e.printStackTrace();
            }
            Message msg = new Message();
            Bundle data = new Bundle();
            data.putSerializable("value", entity);
            msg.setData(data);
            handler.sendMessage(msg);
        }
    };


    private void initView() {
        context = this;
    }

    private void initEvent() {

    }
}
