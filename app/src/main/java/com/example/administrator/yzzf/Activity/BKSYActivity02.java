package com.example.administrator.yzzf.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.example.administrator.yzzf.Adapter.BKSYItemAdapter;
import com.example.administrator.yzzf.Bean.NewsItemBean;
import com.example.administrator.yzzf.R;

import java.util.ArrayList;
import java.util.List;

import me.maxwin.view.XListView;

/**
 * Created by Administrator on 2017/3/24 0024.
 */

public class BKSYActivity02 extends AppCompatActivity implements View.OnClickListener {
    private XListView mXListView;
    private BKSYItemAdapter mAdapter;
    private List<NewsItemBean> mDatas = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bksy02);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_login);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowTitleEnabled(false);
        }
        TextView title = (TextView) findViewById(R.id.login_toolbar_title);
        title.setText(getIntent().getStringExtra("title"));
        findViewById(R.id.toolbar_up).setOnClickListener(this);
        mXListView = (XListView) findViewById(R.id.bksy_activity_xlistview);
        initView();
    }

    private void initView() {
        mAdapter = new BKSYItemAdapter(this, mDatas);
        mXListView.setAdapter(mAdapter);
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
