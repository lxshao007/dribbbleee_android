package com.example.lingxiao.dribbbleee.View.ShotList;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.lingxiao.dribbbleee.Model.Shot;
import com.example.lingxiao.dribbbleee.Utils.ModelUtils;
import com.example.lingxiao.dribbbleee.View.ShotDetail.ShotActivity;
import com.example.lingxiao.dribbbleee.View.ShotDetail.ShotFragment;
import com.example.lingxiao.dribbbleee.R;
import com.google.gson.reflect.TypeToken;

import java.util.List;

/**
 * Created by lingxiao on 9/16/17.
 */

public class ShotListAdapter extends RecyclerView.Adapter {

    private static final int VIEW_TYPE_SHOT = 1;
    private static final int VIEW_TYPE_LOADING = 2;

    private List<Shot> data;
    private LoadMoreListener loadMoreListener;
    private boolean showLoading;

    public ShotListAdapter(@NonNull List<Shot> data, @NonNull LoadMoreListener loadMoreListener) {
        this.data = data;
        this.loadMoreListener = loadMoreListener;
        this.showLoading = true;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_SHOT) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_shot,
                    parent, false);
            return new ShotListViewHolder(view);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_loading,
                    parent, false);
            return new RecyclerView.ViewHolder(view) {
            };
        }

    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        int viewType = getItemViewType(position);
        if (viewType == VIEW_TYPE_SHOT) {
            final Shot shot = data.get(position);

            ShotListViewHolder viewHolder = (ShotListViewHolder) holder;
            viewHolder.view_count.setText(String.valueOf(shot.views_count));
            viewHolder.like_count.setText(String.valueOf(shot.likes_count));
            viewHolder.bucket_count.setText(String.valueOf(shot.buckets_count));

            Glide.with(holder.itemView.getContext())
                    .load(shot.getImageUrl())
                    .placeholder(R.drawable.shot_placeholder)
                    .error(R.drawable.error_image_notfound)
                    .into(viewHolder.shot_image);

            viewHolder.shot_list_cover.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Context context = holder.itemView.getContext();
                    Intent intent = new Intent(context, ShotActivity.class);
                    intent.putExtra(ShotFragment.KEY_SHOT,
                            ModelUtils.toString(shot, new TypeToken<Shot>() {
                            }));
                    intent.putExtra(ShotActivity.KEY_SHOT_TITLE, shot.title);
                    context.startActivity(intent);
                }
            });
        } else {
            loadMoreListener.onLoadMore();
        }

    }

    @Override
    public int getItemCount() {

        return showLoading ? data.size() + 1 : data.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position < data.size()
                ? VIEW_TYPE_SHOT
                : VIEW_TYPE_LOADING;
    }

    public void appendData(@NonNull List<Shot> moreShots) {
        data.addAll(moreShots);
        notifyDataSetChanged();
    }

    public int getDataCount() {
        return data.size();
    }

    public void setShowLoading(boolean showLoading) {
        this.showLoading = showLoading;
        notifyDataSetChanged();
    }

    public interface LoadMoreListener {
        void onLoadMore();
    }


}
