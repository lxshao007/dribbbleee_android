package com.example.lingxiao.dribbbleee.View.ShotList;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.lingxiao.dribbbleee.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by lingxiao on 9/16/17.
 */

public class ShotListViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.shot_view_count) public TextView view_count;
    @BindView(R.id.shot_like_count) public TextView like_count;
    @BindView(R.id.shot_bucket_count) public TextView bucket_count;
    @BindView(R.id.shot_image) public ImageView shot_image;
    @BindView(R.id.shot_list_cover) LinearLayout shot_list_cover;

    public ShotListViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
