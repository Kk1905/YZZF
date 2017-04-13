package com.example.administrator.yzzf.Activity;



import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.example.administrator.yzzf.Adapter.MainAdapter;
import com.example.administrator.yzzf.CustomView.NoScrollViewPager;
import com.example.administrator.yzzf.Fragment.BanKuaiFragment02;
import com.example.administrator.yzzf.Fragment.BaseFragment;
import com.example.administrator.yzzf.Fragment.GRZXFragment02;
import com.example.administrator.yzzf.Fragment.GXQMFragment;
import com.example.administrator.yzzf.Fragment.GXQMFragment02;
import com.example.administrator.yzzf.Fragment.ZhuyeFragment02;
import com.example.administrator.yzzf.R;
import com.example.administrator.yzzf.Util.ActivityStack;
import com.example.administrator.yzzf.Util.Constant;
import com.idescout.sql.SqlScoutServer;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by Administrator on 2017/2/15 0015.
 */

public class MainActivity extends AppCompatActivity implements BottomNavigationBar.OnTabSelectedListener, View.OnClickListener,
        ViewPager.OnPageChangeListener, ZhuyeFragment02.ChangeTab {

    BottomNavigationBar bottomNavigationBar;
    NoScrollViewPager mViewPager;
    private MainAdapter mMainAdapter;
    private List<BaseFragment> mFragments = new ArrayList<>();
    private static final String CURRENT_POSITION = "current_position";
    private int userId;
    private String phone;
    private boolean hasLogin = false;


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
        Log.e("kkkboy", "MainActivity--------->onCreate");
        ActivityStack.getInstance().addActivity(this);
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
        SharedPreferences preferences = this.getApplicationContext().getSharedPreferences(Constant.YZZF_APP, MODE_PRIVATE);
        userId = Integer.parseInt(preferences.getString("userId", "-1"));
        phone = preferences.getString("moible", "");

        if (userId == -1) {
            hasLogin = false;
        } else {
            hasLogin = true;
        }
        mViewPager = (NoScrollViewPager) findViewById(R.id.main_viewPager);
        Fragment fragment01 = new ZhuyeFragment02();
        Bundle bundle01 = new Bundle();
        bundle01.putInt("userId", userId);
        fragment01.setArguments(bundle01);
        mFragments.add(new ZhuyeFragment02());

        Fragment fragment02 = new BanKuaiFragment02();
        Bundle bundle02 = new Bundle();
        bundle02.putInt("userId", userId);
        fragment02.setArguments(bundle02);
        mFragments.add(new BanKuaiFragment02());

        Fragment fragment03 = new GXQMFragment();
        Bundle bundle03 = new Bundle();
        bundle03.putBoolean("hasLogin", hasLogin);
        fragment03.setArguments(bundle03);
        mFragments.add(new GXQMFragment02());

        Fragment fragment04 = new GRZXFragment02();
        Bundle bundle04 = new Bundle();
        bundle04.putInt("userId", userId);
        bundle04.putString("phone", phone);
        fragment04.setArguments(bundle04);
        mFragments.add(new GRZXFragment02());
        mMainAdapter = new MainAdapter(getSupportFragmentManager(), mFragments);
        mViewPager.setAdapter(mMainAdapter);
        mViewPager.addOnPageChangeListener(this);
        mViewPager.setCurrentItem(0);
    }

    protected void initView() {
        initBottomNavigationBar();
        initViewPager();
    }


    @Override
    public void onTabSelected(int position) {
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

    @Override
    public void changeTab(int position) {
        mViewPager.setCurrentItem(position);
    }

//    @Override
//    public View onCreateView(String name, Context context, AttributeSet attrs) {
//        Log.e("kkkboy", "MainActivity--------->onCreateView");
//        return super.onCreateView(name, context, attrs);
//
//    }
//
//    @Override
//    protected void onRestart() {
//        Log.e("kkkboy", "MainActivity--------->onRestart");
//        super.onRestart();
//    }
//
//    @Override
//    protected void onStop() {
//        Log.e("kkkboy", "MainActivity--------->onStop");
//        super.onStop();
//    }
//
//    @Override
//    protected void onResume() {
//        Log.e("kkkboy", "MainActivity--------->onResume");
//        super.onResume();
//    }
//
//    @Override
//    protected void onDestroy() {
//        Log.e("kkkboy", "MainActivity--------->onDestroy");
//        super.onDestroy();
//    }
}
