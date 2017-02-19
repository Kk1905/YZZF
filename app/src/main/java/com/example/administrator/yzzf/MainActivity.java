package com.example.administrator.yzzf;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;



/**
 * Created by Administrator on 2017/2/15 0015.
 */

public class MainActivity extends BaseFragmentActivity implements BottomNavigationBar.OnTabSelectedListener {

    Fragment mFragment;
    @Override
    protected Fragment createFragment() {
        mFragment=new ZhuyeFragment();
        return mFragment;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        iniBottomNavigationBar();

    }


    //初始化BottomNavigationBar
    private void iniBottomNavigationBar() {
        BottomNavigationBar bottomNavigationBar = (BottomNavigationBar) findViewById(R.id.bottom_navigation_bar);
        bottomNavigationBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC);
        bottomNavigationBar.setMode(BottomNavigationBar.MODE_FIXED);
        bottomNavigationBar.addItem(new BottomNavigationItem(R.drawable.zhuye, R.string.zheye))
                .addItem(new BottomNavigationItem(R.drawable.bankuai, R.string.bankuai))
                .addItem(new BottomNavigationItem(R.mipmap.yangzi_daohang, R.string.yangzi_daohang).setInActiveColorResource(R.color.yangzi))
                .addItem(new BottomNavigationItem(R.drawable.gexing, R.string.gexing))
                .addItem(new BottomNavigationItem(R.drawable.wode, R.string.wode))
                .setActiveColor(R.color.colorPrimary)
                .setBarBackgroundColor(R.color.main_background)
                .setInActiveColor(R.color.daohang)
                .initialise();
        bottomNavigationBar.setTabSelectedListener(this);
    }


    @Override
    public void onTabSelected(int position) {
//DO SOMETHING
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
