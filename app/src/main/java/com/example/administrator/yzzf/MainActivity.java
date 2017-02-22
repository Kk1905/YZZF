package com.example.administrator.yzzf;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;

import static android.R.attr.fragment;
import static android.view.View.X;


/**
 * Created by Administrator on 2017/2/15 0015.
 */

public class MainActivity extends AppCompatActivity implements BottomNavigationBar.OnTabSelectedListener {

    BottomNavigationBar bottomNavigationBar;
    FragmentManager mFragmentManager;
//    Fragment mFragment;
//
//    @Override
//    protected Fragment createFragment() {
//        mFragment = new ZhuyeFragment();
//        return mFragment;
//    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iniBottomNavigationBar();
        initView();

    }

    protected void initView() {
        mFragmentManager = getSupportFragmentManager();
        Fragment fragment = mFragmentManager.findFragmentById(R.id.fragment_container);
        if (fragment == null) {
            fragment = new ZhuyeFragment();
            mFragmentManager.beginTransaction()
                    .add(R.id.fragment_container, fragment)
                    .commit();
        }
    }


    //初始化BottomNavigationBar
    private void iniBottomNavigationBar() {
        bottomNavigationBar = (BottomNavigationBar) findViewById(R.id.bottom_navigation_bar);
        bottomNavigationBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC);
        bottomNavigationBar.setMode(BottomNavigationBar.MODE_FIXED);
        bottomNavigationBar.addItem(new BottomNavigationItem(R.drawable.zhuye, R.string.zheye))
                .addItem(new BottomNavigationItem(R.drawable.zhuye_bankuai, R.string.bankuai))
                .addItem(new BottomNavigationItem(R.mipmap.yangzi_daohang, R.string.yangzi_daohang).setInActiveColorResource(R.color.yangzi))
                .addItem(new BottomNavigationItem(R.drawable.gexing, R.string.gexing))
                .addItem(new BottomNavigationItem(R.drawable.wode, R.string.wode))
                .setActiveColor(R.color.colorAccent)
                .setBarBackgroundColor(R.color.main_background)
                .setInActiveColor(R.color.daohang)
                .initialise();

        bottomNavigationBar.setTabSelectedListener(this);
    }

    private void replaceFragment(Fragment fragment) {
        mFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit();
    }

    @Override
    public void onTabSelected(int position) {
        switch (position) {
            case 0:
                replaceFragment(new ZhuyeFragment());
                break;
            case 1:
                replaceFragment(new BanKuaiFragment());
                break;
        }
    }

    @Override
    public void onTabUnselected(int position) {
//DO SOMETHING
    }

    @Override
    public void onTabReselected(int position) {
//DO SOMETHING
    }
}
