package com.example.administrator.yzzf.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.example.administrator.yzzf.Adapter.WDDDAdapter;
import com.example.administrator.yzzf.Fragment.WDDDFragment;
import com.example.administrator.yzzf.R;
import com.viewpagerindicator.TabPageIndicator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/3/20 0020.
 */

public class WDDDActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener, View.OnClickListener {
    private ViewPager mViewPager;
    private TabPageIndicator mTabPageIndicator;
    private List<String> titles;
    private List<Fragment> mFragments;
    private WDDDAdapter mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wodedingdan);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_login);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowTitleEnabled(false);
        }
        TextView textView_title = (TextView) findViewById(R.id.login_toolbar_title);
        textView_title.setText(R.string.wodedingdan);
        findViewById(R.id.toolbar_up).setOnClickListener(this);
        mViewPager = (ViewPager) findViewById(R.id.qbdd_view_pager);
        mTabPageIndicator = (TabPageIndicator) findViewById(R.id.tab_page_indicator);
        titles = new ArrayList<>();
        String[] temp_titles = getResources().getStringArray(R.array.tab_wddd);
        for (String title : temp_titles) {
            titles.add(title);
        }
        mFragments = new ArrayList<>();
        for (int i = 0; i < titles.size(); i++) {
            Fragment fragment = new WDDDFragment();
            Bundle bundle = new Bundle();
            bundle.putString("text", "" + (i + 1));
            fragment.setArguments(bundle);
            mFragments.add(fragment);
        }
        mAdapter = new WDDDAdapter(getSupportFragmentManager(), titles, mFragments);
        mViewPager.setAdapter(mAdapter);
        mTabPageIndicator.setViewPager(mViewPager);
        mTabPageIndicator.setOnPageChangeListener(this);
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        mViewPager.setCurrentItem(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.toolbar_up:
                finish();
                break;
        }
    }
}
