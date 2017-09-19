package com.example.lingxiao.dribbbleee.View.ShotList;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.lingxiao.dribbbleee.Model.Shot;
import com.example.lingxiao.dribbbleee.R;
import com.example.lingxiao.dribbbleee.Utils.ModelUtils;
import com.example.lingxiao.dribbbleee.View.ShotDetail.ShotActivity;
import com.example.lingxiao.dribbbleee.View.ShotDetail.ShotFragment;
import com.google.gson.reflect.TypeToken;

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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_shot,
                parent, false);
        return new ShotListViewHolder(view);

    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        final Shot shot = data.get(position);

        ShotListViewHolder viewHolder = (ShotListViewHolder) holder;
        viewHolder.view_count.setText(String.valueOf(shot.views_count));
        viewHolder.like_count.setText(String.valueOf(shot.likes_count));
        viewHolder.bucket_count.setText(String.valueOf(shot.buckets_count));
        viewHolder.shot_image.setImageResource(R.drawable.shot_placeholder);

        viewHolder.shot_list_cover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = holder.itemView.getContext();
                Intent intent = new Intent(context, ShotActivity.class);
                intent.putExtra(ShotFragment.KEY_SHOT,
                        ModelUtils.toString(shot, new TypeToken<Shot>(){}));
                intent.putExtra(ShotActivity.KEY_SHOT_TITLE, shot.title);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
