package com.example.lingxiao.dribbbleee.View.BucketList;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lingxiao.dribbbleee.Model.Bucket;
import com.example.lingxiao.dribbbleee.Model.Shot;
import com.example.lingxiao.dribbbleee.R;
import com.example.lingxiao.dribbbleee.View.ShotList.ShotListAdapter;
import com.example.lingxiao.dribbbleee.View.ShotList.ShotListFragment;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by lingxiao on 9/17/17.
 */

public class BucketListFragment extends Fragment {
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    public static BucketListFragment newInstance() {
        return new BucketListFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recyclerview, container, false);
        ButterKnife.bind(this, view);
        return view;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        BucketListAdapter adapter = new BucketListAdapter(fakeData());
        recyclerView.setAdapter(adapter);

    }

    private List<Bucket> fakeData() {
        List<Bucket> bucketList = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 20; ++i) {
            Bucket bucket = new Bucket();
            bucket.name = "bucket" + i;
            bucket.shot_counts = random.nextInt(50);
            bucket.isChoosing = false;

            bucketList.add(bucket);
        }
        return bucketList;
    }

}
