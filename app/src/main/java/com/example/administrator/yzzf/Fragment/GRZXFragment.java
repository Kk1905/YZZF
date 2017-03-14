package com.example.administrator.yzzf.Fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.administrator.yzzf.Activity.WDHYActivity;
import com.example.administrator.yzzf.Activity.WDTZActivity;
import com.example.administrator.yzzf.Activity.WDZLActivity;
import com.example.administrator.yzzf.Activity.XTXXActivity;
import com.example.administrator.yzzf.Activity.XiangQingActivity;
import com.example.administrator.yzzf.Activity.YSSZActivity;
import com.example.administrator.yzzf.Activity.ZNXActivity;
import com.example.administrator.yzzf.CustomView.CustomGRZXItem;
import com.example.administrator.yzzf.R;
import com.example.administrator.yzzf.Util.Constant;
import com.example.administrator.yzzf.Util.FragmentFactory;

/**
 * Created by Administrator on 2017/2/23 0023.
 */

public class GRZXFragment extends BaseFragment implements View.OnClickListener {
    CustomGRZXItem wdzl, wdhy, wdtz, znx, yssz, xtxx;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = LayoutInflater.from(mAppCompatActivity).inflate(R.layout.fragment_grzx, container, false);
        mToolbar = (Toolbar) view.findViewById(R.id.toolbar_grzx);
        mAppCompatActivity.setSupportActionBar(mToolbar);


        ActionBar actionBar = mAppCompatActivity.getSupportActionBar();
        if (actionBar != null) {

            actionBar.setDisplayShowTitleEnabled(false);
        }

        //统一找view对象
        wdzl = (CustomGRZXItem) view.findViewById(R.id.my_ziliao);
        wdhy = (CustomGRZXItem) view.findViewById(R.id.my_friends);
        wdtz = (CustomGRZXItem) view.findViewById(R.id.my_tiezi);
        znx = (CustomGRZXItem) view.findViewById(R.id.zhan_nei_xin);
        yssz = (CustomGRZXItem) view.findViewById(R.id.privite);
        xtxx = (CustomGRZXItem) view.findViewById(R.id.xitong);
        //设置监听
        wdzl.setOnClickListener(this);
        wdhy.setOnClickListener(this);
        wdtz.setOnClickListener(this);
        znx.setOnClickListener(this);
        yssz.setOnClickListener(this);
        xtxx.setOnClickListener(this);
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.my_ziliao:

                Intent intent = new Intent(mAppCompatActivity, WDZLActivity.class);
                mAppCompatActivity.startActivity(intent);
                break;
            case R.id.my_friends:

                Intent intent01 = new Intent(mAppCompatActivity, WDHYActivity.class);
                mAppCompatActivity.startActivity(intent01);
                break;
            case R.id.my_tiezi:
                Intent intent02 = new Intent(mAppCompatActivity, WDTZActivity.class);
                mAppCompatActivity.startActivity(intent02);
                break;
            case R.id.zhan_nei_xin:
                Intent intent03 = new Intent(mAppCompatActivity, ZNXActivity.class);
                mAppCompatActivity.startActivity(intent03);
                break;
            case R.id.privite:
                Intent intent04 = new Intent(mAppCompatActivity, YSSZActivity.class);
                mAppCompatActivity.startActivity(intent04);
                break;
            case R.id.xitong:
                Intent intent05 = new Intent(mAppCompatActivity, XTXXActivity.class);
                mAppCompatActivity.startActivity(intent05);
                break;

        }
    }
}
