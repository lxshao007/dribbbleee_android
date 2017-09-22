package com.example.lingxiao.dribbbleee.View.ShotDetail;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.lingxiao.dribbbleee.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ShotActivity extends AppCompatActivity {

    public static final String KEY_SHOT_TITLE = "shot_title";

    @BindView(R.id.toolbar) Toolbar toolBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shot);

        ButterKnife.bind(this);

        setupToolBarUI();

        setupShotFragment();

    }


    private void setupToolBarUI() {
        setSupportActionBar(toolBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setupShotFragment() {
        ShotFragment newFragment = ShotFragment.newInstance(getIntent().getExtras());

        getSupportFragmentManager().beginTransaction()
                .add(R.id.shot_detail_fragment_container, newFragment).commit();
    }


}
