package com.example.administrator.yzzf.Activity;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.example.administrator.yzzf.Adapter.MainAdapter;
import com.example.administrator.yzzf.CustomView.NoScrollViewPager;
import com.example.administrator.yzzf.Fragment.BanKuaiFragment;
import com.example.administrator.yzzf.Fragment.BaseFragment;
import com.example.administrator.yzzf.Fragment.GRZXFragment;
import com.example.administrator.yzzf.Fragment.GRZXFragment02;
import com.example.administrator.yzzf.Fragment.GXQMFragment;
import com.example.administrator.yzzf.R;
import com.example.administrator.yzzf.Fragment.ZhuyeFragment;
import com.idescout.sql.SqlScoutServer;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Administrator on 2017/2/15 0015.
 */

public class MainActivity extends AppCompatActivity implements BottomNavigationBar.OnTabSelectedListener, View.OnClickListener,
        ViewPager.OnPageChangeListener{

    BottomNavigationBar bottomNavigationBar;
    NoScrollViewPager mViewPager;
    private MainAdapter mMainAdapter;
    private List<BaseFragment> mFragments = new ArrayList<>();
    private static final String CURRENT_POSITION = "current_position";
//    private static final int ZHU_YE = 0;
//    private static final int BAN_KUAI = 1;
//    private static final int YANGZI_ZHIFU = 2;
//    private static final int GE_XING = 3;
//    private static final int WO_DE = 4;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SqlScoutServer.create(this, getPackageName());
        setContentView(R.layout.activity_main);
        initView();
        if (savedInstanceState != null) {
            int currentPosition = savedInstanceState.getInt(CURRENT_POSITION, 0);
            bottomNavigationBar.selectTab(currentPosition);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        int currentPosition = bottomNavigationBar.getCurrentSelectedPosition();
        outState.putInt(CURRENT_POSITION, currentPosition);
    }

    //初始化BottomNavigationBar
    private void initBottomNavigationBar() {
        bottomNavigationBar = (BottomNavigationBar) findViewById(R.id.bottom_navigation_bar);
        bottomNavigationBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC);
        bottomNavigationBar.setMode(BottomNavigationBar.MODE_FIXED);
        bottomNavigationBar.addItem(new BottomNavigationItem(R.drawable.zhuye, R.string.zheye))
                .addItem(new BottomNavigationItem(R.drawable.zhuye_bankuai, R.string.bankuai))
                .addItem(new BottomNavigationItem(R.drawable.gexing, R.string.gexing))
                .addItem(new BottomNavigationItem(R.drawable.wode, R.string.wode))
                .setActiveColor(R.color.colorAccent)
                .setBarBackgroundColor(R.color.main_background)
                .setInActiveColor(R.color.daohang)
                .initialise();

        bottomNavigationBar.setTabSelectedListener(this);

    }


    //初始化viewPager
    private void initViewPager() {
        mViewPager = (NoScrollViewPager) findViewById(R.id.main_viewPager);
        mFragments.add(new ZhuyeFragment());
        mFragments.add(new BanKuaiFragment());
        mFragments.add(new GXQMFragment());
        mFragments.add(new GRZXFragment02());
        mMainAdapter = new MainAdapter(getSupportFragmentManager(), mFragments);
        mViewPager.setAdapter(mMainAdapter);
        mViewPager.addOnPageChangeListener(this);
        mViewPager.setCurrentItem(0);
    }

    protected void initView() {
//        mFragmentManager = getSupportFragmentManager();
//        Fragment fragment = mFragmentManager.findFragmentById(R.id.fragment_container);
//        if (fragment == null) {
//            fragment = new ZhuyeFragment();
//            mFragmentManager.beginTransaction()
//                    .addToBackStack(null)
//                    .add(R.id.fragment_container, fragment)
//                    .commit();
//        }
        initBottomNavigationBar();
        initViewPager();
    }

//    private void replaceFragment(Fragment fragment) {
//        mFragmentManager.beginTransaction()
//                .replace(R.id.fragment_container, fragment)
//                .commit();
//    }

    @Override
    public void onTabSelected(int position) {
//        switch (position) {
//            case ZHU_YE:
//                Fragment newFragment = new ZhuyeFragment();
//                mFragmentFactory.replaceFragment(newFragment);
//                break;
//            case BAN_KUAI:
//                Fragment newFragment01 = new BanKuaiFragment();
//                mFragmentFactory.replaceFragment(newFragment01);
//                break;
//            case YANGZI_ZHIFU:
//                break;
//            case GE_XING:
//                Fragment newFragment02 = new GXQMFragment();
//                mFragmentFactory.replaceFragment(newFragment02);
//                break;
//            case WO_DE:
//                Fragment newFragment03 = new GRZXFragment();
//                mFragmentFactory.replaceFragment(newFragment03);
//                break;
//        }
        mViewPager.setCurrentItem(position);
    }

    @Override
    public void onTabUnselected(int position) {

    }

    @Override
    public void onTabReselected(int position) {

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        bottomNavigationBar.selectTab(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
        }
    }

}
