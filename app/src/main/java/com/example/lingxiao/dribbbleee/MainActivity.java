package com.example.lingxiao.dribbbleee;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.lingxiao.dribbbleee.View.BucketList.BucketListFragment;
import com.example.lingxiao.dribbbleee.View.ShotList.ShotListFragment;
import com.example.lingxiao.dribbbleee.dribbble.Dribbble;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.drawer_layout) DrawerLayout drawerLayout;
    @BindView(R.id.drawer) NavigationView navigationView;

    private ActionBarDrawerToggle drawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        setupDrawer();

        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.fragment_container, ShotListFragment.newInstance())
                    .commit();
        }
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setupDrawer() {
        drawerToggle = new ActionBarDrawerToggle(
                this,                  /* host Activity */
                drawerLayout,          /* DrawerLayout object */
                R.string.open_drawer,         /* "open drawer" description */
                R.string.close_drawer         /* "close drawer" description */
        );

        drawerLayout.setDrawerListener(drawerToggle);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                if (item.isChecked()) {
                    drawerLayout.closeDrawers();
                    return true;
                }

                Fragment fragment = null;
                switch (item.getItemId()) {
                    case R.id.drawer_item_home:
                        fragment = ShotListFragment.newInstance();
                        setTitle(R.string.title_home);
                        break;
                    case R.id.drawer_item_likes:
                        fragment = ShotListFragment.newInstance();
                        setTitle(R.string.title_likes);
                        break;
                    case R.id.drawer_item_buckets:
                        fragment = BucketListFragment.newInstance();
                        setTitle(R.string.title_buckets);
                        break;
                }

                drawerLayout.closeDrawers();

                if (fragment != null) {
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.fragment_container, fragment)
                            .commit();
                    return true;
                }

                return false;
            }
        });

        setupNavHeader();
    }

    private void setupNavHeader() {
        View headerView = navigationView.getHeaderView(0);

        ((TextView) headerView.findViewById(R.id.nav_header_user_name)).setText(
                Dribbble.getCurrentUser().name);

//        try {
//            Log.i("name", ModelUtils.toString(Dribbble.getUser(), new TypeToken<User>(){}));
//        } catch (IOException e) {
//            e.printStackTrace();
//            Toast.makeText(getApplicationContext(), "error", Toast.LENGTH_LONG).show();
//        }

        Glide.with(headerView.getContext())
                .load(Dribbble.getCurrentUser().avatar_url)
                .placeholder(R.drawable.user_picture_placeholder)
                .error(R.drawable.error_image_notfound)
                .into((ImageView) headerView.findViewById(R.id.nav_header_user_picture));

        headerView.findViewById(R.id.nav_header_logout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dribbble.logout(MainActivity.this);

                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}

