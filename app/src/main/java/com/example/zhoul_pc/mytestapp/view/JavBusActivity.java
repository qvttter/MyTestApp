package com.example.zhoul_pc.mytestapp.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.zhoul_pc.mytestapp.R;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by lili on 2017/6/24.
 */
public class JavBusActivity extends BaseActivity {
    @BindView(R.id.rcv_javbus)
    RecyclerView rcvJavbus;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.btn_detail)
    Button btnDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_javbus);
        ButterKnife.bind(this);
        initView();
        initEvent();
        //new Thread(networkTask).start();
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Bundle data = msg.getData();
            String val = data.getString("value");
            // TODO
            // UI界面的更新等相关操作

            tvTitle.setText(val);
        }
    };

    Runnable networkTask = new Runnable() {

        @Override
        public void run() {
            // TODO
            Document doc = null;
            String title = "";
            try {
                doc = Jsoup.connect("https://www.javbus2.com/GVG-126").get();
                Element body = doc.body();
                //Elements containerFluid = body.select("div.container-fluid");//class型用“.”，id的用#
                Elements container = body.select("div.container");
                title = container.select("h3").text();
                Log.e("", "标题: "+title );
                Elements waterfall = container.select("div#waterfall");
                Elements item = waterfall.select("div.item masonry-brick");

            } catch (IOException e) {
                e.printStackTrace();
            }
            Message msg = new Message();
            Bundle data = new Bundle();
            data.putString("value", title);
            msg.setData(data);
            handler.sendMessage(msg);
        }
    };


    private void initView() {

    }

    private void initEvent() {
        btnDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(JavBusActivity.this,JavbusDetailActivity.class));
            }
        });
    }
}
