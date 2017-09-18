package com.example.lingxiao.dribbbleee.View.BucketList;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lingxiao.dribbbleee.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by lingxiao on 9/17/17.
 */

public class BucketListViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.bucket_name) TextView bucketName;
    @BindView(R.id.bucket_shot_count) TextView bucketShotCount;
    @BindView(R.id.bucket_shot_chosen) ImageView bucketShotChosen;

    public BucketListViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
