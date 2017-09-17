package com.example.lingxiao.dribbbleee;

import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.example.lingxiao.dribbbleee.View.ShotList.ShotListFragment;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowTitleEnabled(false);

        setupShotListFragment();


    }

    private void setupShotListFragment() {
        ShotListFragment shotListFragment = new ShotListFragment();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, shotListFragment).commit();
    }
}
