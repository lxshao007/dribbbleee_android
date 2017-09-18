package com.example.lingxiao.dribbbleee.View.BucketList;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lingxiao.dribbbleee.Model.Bucket;
import com.example.lingxiao.dribbbleee.R;


import java.util.List;

/**
 * Created by lingxiao on 9/17/17.
 */

public class BucketListAdapter extends RecyclerView.Adapter {

    private List<Bucket>  data;

    public BucketListAdapter(List<Bucket> data) {
        this.data = data;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_bucket,
                parent, false);
        return new BucketListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Bucket bucket = data.get(position);

        BucketListViewHolder viewHolder = (BucketListViewHolder) holder;
        viewHolder.bucketName.setText(bucket.name);
        viewHolder.bucketShotCount.setText(String.valueOf(bucket.shot_counts));


    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
