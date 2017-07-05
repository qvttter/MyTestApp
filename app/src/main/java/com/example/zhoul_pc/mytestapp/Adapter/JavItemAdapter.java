package com.example.zhoul_pc.mytestapp.Adapter;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.zhoul_pc.mytestapp.Bean.JavItemEntity;
import com.example.zhoul_pc.mytestapp.Bean.JavbusDetailEntity;
import com.example.zhoul_pc.mytestapp.R;
import com.example.zhoul_pc.mytestapp.view.JavbusDetailActivity;

import java.util.List;

/**
 * Created by Administrator on 2017/2/8.
 */

public class JavItemAdapter extends RecyclerView.Adapter<JavItemAdapter.ViewHolder>{
    private List<JavItemEntity> list;
    private LayoutInflater inflater;
    private Context context;
    public static final String URL = "url";

    public JavItemAdapter(List<JavItemEntity> list, Context mContext) {
        this.list = list;
        inflater=LayoutInflater. from(mContext);
        this.context = mContext;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.jav_item_index,parent, false);
        ViewHolder holder= new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final JavItemEntity entity = list.get(position);
        Glide.with(context)
                .load(entity.getImg())
                .into(holder.ivCover);

        holder.tvTitle.setText(entity.getTitle());
        holder.tvCode.setText(entity.getCode());
        holder.tvTime.setText(entity.getTime());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, JavbusDetailActivity.class);
                intent.putExtra(URL,entity.getUrl());
                context.startActivity(intent);
            }
        });

    }

    public void setList(List<JavItemEntity> list){
        this.list = list;
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private ImageView ivCover;
        private TextView tvTitle;
        private TextView tvCode;
        private TextView tvTime;
        public ViewHolder(View itemView) {
            super(itemView);
            ivCover = (ImageView) itemView.findViewById(R.id.iv_cover);
            tvTitle = (TextView) itemView.findViewById(R.id.tv_title);
            tvCode = (TextView) itemView.findViewById(R.id.tv_code);
            tvTime = (TextView) itemView.findViewById(R.id.tv_time);
        }
    }
}
