package com.example.administrator.yzzf.Activity;


import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.example.administrator.yzzf.Fragment.BanKuaiFragment;
import com.example.administrator.yzzf.Fragment.GRZXFragment;
import com.example.administrator.yzzf.R;
import com.example.administrator.yzzf.Fragment.ZhuyeFragment;


/**
 * Created by Administrator on 2017/2/15 0015.
 */

public class MainActivity extends AppCompatActivity implements BottomNavigationBar.OnTabSelectedListener {

    BottomNavigationBar bottomNavigationBar;
    FragmentManager mFragmentManager;
    private static final String CURRENT_POSITION = "current_position";
    private static final int ZHU_YE = 0;
    private static final int BAN_KUAI = 1;
    private static final int YANGZI_ZHIFU = 2;
    private static final int GE_XING = 3;
    private static final int WO_DE = 4;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        if (savedInstanceState != null) {
            int currentPosition = savedInstanceState.getInt(CURRENT_POSITION, 0);
            initBottomNavigationBar(currentPosition);
        } else {
            initBottomNavigationBar(ZHU_YE);
        }

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        int currentPosition = bottomNavigationBar.getCurrentSelectedPosition();
        outState.putInt(CURRENT_POSITION, currentPosition);
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
    private void initBottomNavigationBar(int currentPosition) {
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

        bottomNavigationBar.selectTab(currentPosition);

    }

    private void replaceFragment(Fragment fragment) {
        mFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit();
    }

    @Override
    public void onTabSelected(int position) {

        switch (position) {
            case ZHU_YE:
                replaceFragment(new ZhuyeFragment());
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);
                break;
            case BAN_KUAI:
                replaceFragment(new BanKuaiFragment());
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                break;
            case YANGZI_ZHIFU:
                break;
            case GE_XING:

                break;
            case WO_DE:
                replaceFragment(new GRZXFragment());
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
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
