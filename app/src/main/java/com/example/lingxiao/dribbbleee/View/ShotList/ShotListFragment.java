package com.example.lingxiao.dribbbleee.View.ShotList;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.os.AsyncTaskCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lingxiao.dribbbleee.Model.Shot;
import com.example.lingxiao.dribbbleee.dribbble.Dribbble;
import com.example.lingxiao.dribbbleee.R;
import com.google.gson.JsonSyntaxException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by lingxiao on 9/15/17.
 */

public class ShotListFragment extends Fragment {


    @BindView(R.id.recycler_view) RecyclerView recyclerView;

    private ShotListAdapter adapter;
    private static final int COUNT_PER_PAGE = 20;

    public static ShotListFragment newInstance() {
        return new ShotListFragment();
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

        adapter = new ShotListAdapter(new ArrayList<Shot>(), new ShotListAdapter.LoadMoreListener() {
            @Override
            public void onLoadMore() {
                AsyncTaskCompat.executeParallel(new LoadShotTask(adapter.getDataCount() / COUNT_PER_PAGE + 1));
            }
        });
        recyclerView.setAdapter(adapter);

    }

    private class LoadShotTask extends AsyncTask<Void, Void, List<Shot>> {

        int page;

        public LoadShotTask(int page) {
            this.page = page;
        }

        @Override
        protected List<Shot> doInBackground(Void... params) {
            // executed on non-UI thread
            try {
                return Dribbble.getShots(page);
            } catch (IOException | JsonSyntaxException e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(List<Shot> shots) {
            // executed on UI thread!!!!
            if (shots != null) {
                adapter.appendData(shots);
            } else {
                Snackbar.make(getView(), "Error!", Snackbar.LENGTH_LONG).show();
            }
        }
    }


}
