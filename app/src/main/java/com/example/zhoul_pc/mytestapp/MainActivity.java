package com.example.zhoul_pc.mytestapp;

import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.zhoul_pc.mytestapp.Adapter.ACPageAdapter;
import com.example.zhoul_pc.mytestapp.UI.Activity.Fragment.ACEntertainmentFragment;
import com.example.zhoul_pc.mytestapp.UI.Activity.Fragment.ACGameFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    //    @BindView(R.id.btn_firework_list)
//    Button btnFireworkList;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;
    @BindView(R.id.nav_view)
    NavigationView navigationView;

    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.viewPager)
    ViewPager viewPager;

    //    @BindView(R.id.pullToRefresh)
//    FireworkyPullToRefreshLayout refreshLayout;
//    @BindView(R.id.recyclerView)
//    RecyclerView recyclerView;
    public static final String AC_TYPE = "ac_type";
    private List<String> list;
    private Handler handler;
    private ACPageAdapter acPageAdapter;
    private List<String> titles;
    private List<Fragment> fragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);
        initView();
        initEvent();

    }

    private void initView() {
        toolbar.setTitle("首页");
        setSupportActionBar(toolbar);

        fragments = new ArrayList<>();
        fragments.add(ACEntertainmentFragment.newInstance("ac_entertainment"));
        fragments.add(ACGameFragment.newInstance("doc"));
        fragments.add(ACGameFragment.newInstance("test"));
        fragments.add(ACGameFragment.newInstance("question_bank"));
        fragments.add(ACGameFragment.newInstance("question_bank"));
        fragments.add(ACGameFragment.newInstance("question_bank"));
        fragments.add(ACGameFragment.newInstance("question_bank"));
        fragments.add(ACGameFragment.newInstance("question_bank"));
        fragments.add(ACGameFragment.newInstance("question_bank"));
        titles = new ArrayList<>();
        titles.add("娱乐");
        titles.add("游戏");
        titles.add("动画");
        titles.add("音乐");
        titles.add("舞蹈·彼女");
        titles.add("影视");
        titles.add("鱼塘");
        titles.add("科技");
        titles.add("体育");
        acPageAdapter = new ACPageAdapter(getSupportFragmentManager(), fragments, titles);


        viewPager.setAdapter(acPageAdapter);
        tabLayout.setupWithViewPager(viewPager);

//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        recyclerView.setAdapter(adapter);
    }

    private void initEvent() {
//        btnFireworkList.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(MainActivity.this, FireworkListActivity.class);
//                startActivity(intent);
//            }
//        });

//        refreshLayout.setOnRefreshListener(new FireworkyPullToRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//                relay(3000);
//            }
//        });
    }

    private void resetDate() {
        list.clear();
        for (int i = 0; i < 60; i++) {
            String str = "我是第" + i + ";" + System.currentTimeMillis();
            list.add(str);
        }
//        refreshLayout.setRefreshing(false);
    }

    private void relay(int time) {
        handler.postDelayed(mRunnable, time);
    }

    private Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            resetDate();
        }
    };

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
