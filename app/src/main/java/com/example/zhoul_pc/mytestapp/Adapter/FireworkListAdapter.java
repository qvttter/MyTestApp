package com.example.zhoul_pc.mytestapp.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.zhoul_pc.mytestapp.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ZHOUL-PC on 2017/4/7.
 */

public class FireworkListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<String> list;
    private Context context;

    public FireworkListAdapter(List<String> list,Context context) {
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
        final String str = list.get(position);

        itemViewHolder.tvTxt.setText(str);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    private static class ItemViewHolder extends RecyclerView.ViewHolder {
        private TextView tvTxt;


        public ItemViewHolder(View itemView) {
            super(itemView);
            tvTxt = (TextView) itemView.findViewById(R.id.tv_txt);
        }
    }

    public void setList(ArrayList<String> list) {
        this.list = list;
        notifyDataSetChanged();
    }
}
