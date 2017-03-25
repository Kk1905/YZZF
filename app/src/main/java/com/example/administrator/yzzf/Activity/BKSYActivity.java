package com.example.administrator.yzzf.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import com.example.administrator.yzzf.Adapter.BKSYFragmentPagerAdapter;
import com.example.administrator.yzzf.CustomView.NoScrollViewPager;
import com.example.administrator.yzzf.Fragment.BKSY_Quanbu_Fragment;
import com.example.administrator.yzzf.Fragment.BKSY_Remen_Fragment;
import com.example.administrator.yzzf.Fragment.BKSY_Zuixin_Fragment;
import com.example.administrator.yzzf.R;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/2/28 0028.
 */

public class BKSYActivity extends AppCompatActivity implements View.OnClickListener {
    private TabLayout mTabLayout;
    private NoScrollViewPager container;
    private BKSYFragmentPagerAdapter adapter;
    private List<Fragment> mList_fragment;
    private List<String> mList_title;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bksy);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_bksy);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowTitleEnabled(false);
        }

        initView();

        toolbar.findViewById(R.id.toolbar_bksy_sousuo).setOnClickListener(this);
        findViewById(R.id.bksy_imageview_fatie).setOnClickListener(this);

    }

    //初始化各个数据
    private void initView() {
        mTabLayout = (TabLayout) findViewById(R.id.bksy_tabLayout);
        mTabLayout.setTabMode(TabLayout.MODE_FIXED);
        container = (NoScrollViewPager) findViewById(R.id.bksy_fragment_container);

        //为mlist_fragment添加元素
        mList_fragment = new ArrayList<>();
        mList_fragment.add(new BKSY_Quanbu_Fragment());
        mList_fragment.add(new BKSY_Zuixin_Fragment());
        mList_fragment.add(new BKSY_Remen_Fragment());

        //为mlist_title添加元素
        mList_title = new ArrayList<>();
        String[] titles = getResources().getStringArray(R.array.tab_name);
        for (int i = 0; i < titles.length; i++) {
            mList_title.add(titles[i]);
        }
        adapter = new BKSYFragmentPagerAdapter(getSupportFragmentManager(), mList_fragment, mList_title);

        //这里取消viewPager的左右滑动功能
        container.setNoScroll(true);

        //viewPager加载adapter
        container.setAdapter(adapter);
        //将tablayout和viewPager关联
        mTabLayout.setupWithViewPager(container);

        initTab();
    }

    //为tablayout添加各种tab标签
    private void initTab() {
        for (int i = 0; i < mList_title.size(); i++) {
            //获取对应位置的Tab
            TabLayout.Tab tab = mTabLayout.getTabAt(i);
            if (tab != null) {
                tab.setCustomView(R.layout.custom_item_tablayout);
                TextView textView = (TextView) tab.getCustomView().findViewById(R.id.custom_item_tablayout_tabname);
                textView.setText(mList_title.get(i));
            }
        }
        mTabLayout.getTabAt(0).select();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.toolbar_bksy_sousuo:
                Intent intent = new Intent(this, SouSuoActivity.class);
                startActivity(intent);
                break;
            case R.id.bksy_imageview_fatie:
                Intent intent1 = new Intent(this, FaTieActivity.class);
                startActivity(intent1);
                break;
        }
    }
}
