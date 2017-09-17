package com.example.lingxiao.dribbbleee.View.ShotList;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.lingxiao.dribbbleee.Model.Shot;
import com.example.lingxiao.dribbbleee.R;

import java.util.List;

/**
 * Created by lingxiao on 9/16/17.
 */

public class ShotListAdapter extends RecyclerView.Adapter{
    private List<Shot> data;

    public ShotListAdapter(List<Shot> data){
        this.data = data;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_shot, parent, false);
        return new ShotListViewHolder(view);

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Shot shot = data.get(position);

        ShotListViewHolder viewHolder = (ShotListViewHolder) holder;
        viewHolder.view_count.setText(String.valueOf(shot.views_count));
        viewHolder.like_count.setText(String.valueOf(shot.likes_count));
        viewHolder.bucket_count.setText(String.valueOf(shot.buckets_count));
        viewHolder.shot_image.setImageResource(R.drawable.shot_placeholder);

    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
