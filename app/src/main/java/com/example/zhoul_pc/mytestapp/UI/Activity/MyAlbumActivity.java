package com.example.zhoul_pc.mytestapp.UI.Activity;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.zhoul_pc.mytestapp.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by lili on 2017/5/29.
 */
public class MyAlbumActivity extends AppCompatActivity {
    @BindView(R.id.tv_test)
    TextView tvTest;
    @BindView(R.id.rv_album)
    RecyclerView rvMyAlbum;

    private Cursor cursor;
    private MyAlbumAdapter adapter;

    String[] mediaColumns = new String[]{
            MediaStore.Video.Media.DATA,
            MediaStore.Video.Media._ID,
            MediaStore.Video.Media.TITLE,
            MediaStore.Video.Media.MIME_TYPE
    };

    String[] thumbColumns = new String[]{
            MediaStore.Video.Thumbnails.DATA,
            MediaStore.Video.Thumbnails.VIDEO_ID
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_album);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        tvTest.setText(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).getAbsolutePath());

//        cursor = this.managedQuery(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, mediaColumns, null, null, null);
        Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                cursor = MyAlbumActivity.this.managedQuery(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, mediaColumns, null, null, null);
                ArrayList<VideoInfo> videoList = new ArrayList<VideoInfo>();
                if (cursor.moveToFirst()) {
                    do {
                        VideoInfo info = new VideoInfo();

                        info.filePath = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DATA));
                        info.mimeType = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.MIME_TYPE));
                        info.title = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.TITLE));

                        //获取当前Video对应的Id，然后根据该ID获取其Thumb
                        int id = cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Video.Media._ID));
                        String selection = MediaStore.Video.Thumbnails.VIDEO_ID + "=?";
                        String[] selectionArgs = new String[]{
                                id + ""
                        };
                        Cursor thumbCursor = MyAlbumActivity.this.managedQuery(MediaStore.Video.Thumbnails.EXTERNAL_CONTENT_URI, thumbColumns, selection, selectionArgs, null);

                        if (thumbCursor.moveToFirst()) {
                            info.thumbPath = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Thumbnails.DATA));

                        }

                        //然后将其加入到videoList
                        videoList.add(info);

                    } while (cursor.moveToNext());
                }

                setAdapter(videoList);
            }
        });

    }

    private void setAdapter(ArrayList<VideoInfo> videoList){
        adapter = new MyAlbumAdapter(this,videoList);
        rvMyAlbum.setLayoutManager(new StaggeredGridLayoutManager(3, LinearLayout.VERTICAL));
        rvMyAlbum.setAdapter(adapter);
    }

    static class VideoInfo{
        String filePath;
        String mimeType;
        String thumbPath;
        String title;
    }

    static class MyAlbumAdapter extends RecyclerView.Adapter{
        private Context context;
        private ArrayList<VideoInfo> list;
        //private View.OnClickListener listener;

        public MyAlbumAdapter(Context context, ArrayList<VideoInfo> list) {
            this.context = context;
            this.list = list;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(context).inflate(R.layout.item_fire_list,parent,false);

            return new ItemViewHolder(v);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            final ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
            final VideoInfo videoInfo = list.get(position);
            Glide.with(context)
                    .load(videoInfo.thumbPath)
                    .into(itemViewHolder.ivCover);

            itemViewHolder.tvTitle.setText(videoInfo.title);
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        private static class ItemViewHolder extends RecyclerView.ViewHolder {
            private ImageView ivCover;
            private TextView tvTitle;


            public ItemViewHolder(View itemView) {
                super(itemView);
                ivCover = (ImageView) itemView.findViewById(R.id.iv_cover);
                tvTitle = (TextView) itemView.findViewById(R.id.tv_txt);
            }
        }

        public void setList(ArrayList<VideoInfo> list) {
            this.list = list;
            notifyDataSetChanged();
        }

        public void addList(ArrayList<VideoInfo> list) {
            this.list.addAll(list);
            notifyDataSetChanged();
        }

        public VideoInfo getItemData(int position) {
            return (VideoInfo) list.get(position);
        }
    }
}
