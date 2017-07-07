package com.example.zhoul_pc.mytestapp.common;

import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.turn.ttorrent.client.Client;
import com.turn.ttorrent.client.SharedTorrent;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.NoSuchAlgorithmException;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by lili on 2017/7/7.
 */
public class BitDownloadUtils {
    public static void download() throws IOException, NoSuchAlgorithmException {
        new Thread(networkTask).start();
    }

    final static Runnable networkTask = new Runnable() {

        @Override
        public void run() {
            // TODO
            // 在这里进行 http request.网络请求相关操作
            Client client = null;
            try {
                client = new Client(InetAddress.getLocalHost(),
                        SharedTorrent.fromFile(new File(Environment.getExternalStorageDirectory() +
                                        "/Pictures/test_torrent.torrent"),
                                new File(Environment.getExternalStorageDirectory() + "/post/")));
            } catch (IOException e) {
                e.printStackTrace();
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }

            client.setMaxDownloadRate(50.0);
            client.setMaxUploadRate(50.0);

            client.addObserver(new Observer() {
                @Override
                public void update(Observable observable, Object data) {
                    Client client = (Client) observable;
                    float progress = client.getTorrent().getCompletion();
                    Log.e("下载进度", "update: " + progress);
                    // Do something with progress.
                    Message msg = new Message();
                    Bundle bundle = new Bundle();
                    bundle.putString("value", "请求结果"+progress);
                    msg.setData(bundle);
                    handler.sendMessage(msg);
                }
            });

            client.download();

            client.waitForCompletion();


        }
    };

    final static Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Bundle data = msg.getData();
            String val = data.getString("value");
            Log.e("下载进度", "update: " + val);

            // TODO
            // UI界面的更新等相关操作
        }
    };
}
