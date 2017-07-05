package com.example.zhoul_pc.mytestapp.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.example.zhoul_pc.mytestapp.Adapter.JavItemAdapter;
import com.example.zhoul_pc.mytestapp.Bean.JavItemEntity;
import com.example.zhoul_pc.mytestapp.R;
import com.example.zhoul_pc.mytestapp.common.BaseUrl;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

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

    private JavItemAdapter adapter;
    private List<JavItemEntity> itemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_javbus);
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
            String val = data.getString("value");
            // TODO
            // UI界面的更新等相关操作
            adapter.setList(itemList);
            tvTitle.setText(val);
            dismisDialog();
        }
    };

    Runnable networkTask = new Runnable() {

        @Override
        public void run() {
            // TODO
            Document doc = null;
            try {
                doc = Jsoup.parse(new URL(BaseUrl.BASE_JAV2), 5000);
                Element body = doc.body();
                Elements containerFluid = body.select("div.container-fluid");
                Elements row = containerFluid.select("div.row");
                //Elements row1 = row.select("div.row");
                Elements waterfall = row.select("div#waterfall");
                Elements item = waterfall.select("div.item");
                for (int i = 0; i < item.size(); i++) {
                    JavItemEntity entity = new JavItemEntity();

                    Elements movieBox = item.get(i).select("a.movie-box");
                    //超链接
                    Elements a = movieBox.select("a[href]");
                    entity.setUrl(a.attr("href"));
                    //封面
                    Elements photoFrame = movieBox.select("div.photo-frame");
                    Elements image = photoFrame.select("img[src]");
                    entity.setImg(image.attr("src"));


                    //标题
                    Elements photoInfo = movieBox.select("div.photo-info");
                    Elements title = photoInfo.select("span");
                    entity.setTitle(title.text());

                    itemList.add(entity);
//                        String photoUrl = a.attr("href");
//                        photoUrlList.add(photoUrl);
                }

                //Elements movieBox = containerFluid.select("a[AttrName=movie-box]");

            } catch (IOException e) {
                e.printStackTrace();
            }
            //String title = doc.head().getElementsByTag("title").text();
            Message msg = new Message();
            Bundle data = new Bundle();
            data.putString("value", "1");
            msg.setData(data);
            handler.sendMessage(msg);
        }
    };


    private void initView() {
        showDialog();
        itemList = new ArrayList<>();
        adapter = new JavItemAdapter(new ArrayList<JavItemEntity>(),this);
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        rcvJavbus.setLayoutManager((staggeredGridLayoutManager));
        rcvJavbus.setAdapter(adapter);
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
