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

import com.example.administrator.yzzf.Activity.WDHYActivity;
import com.example.administrator.yzzf.Activity.WDTZActivity;
import com.example.administrator.yzzf.Activity.WDZLActivity;
import com.example.administrator.yzzf.CustomView.CustomGRZXItem;
import com.example.administrator.yzzf.R;

/**
 * Created by Administrator on 2017/2/23 0023.
 */

public class GRZXFragment extends BaseFragment implements View.OnClickListener {
    CustomGRZXItem wdzl, wdhy, wdtz;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = LayoutInflater.from(mAppCompatActivity).inflate(R.layout.fragment_grzx, container, false);
        mToolbar = (Toolbar) view.findViewById(R.id.toolbar_grzx);
        mAppCompatActivity.setSupportActionBar(mToolbar);

        ActionBar actionBar = mAppCompatActivity.getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowTitleEnabled(false);
        }
        //统一找view对象
        wdzl = (CustomGRZXItem) view.findViewById(R.id.my_ziliao);
        wdhy = (CustomGRZXItem) view.findViewById(R.id.my_friends);
        wdtz = (CustomGRZXItem) view.findViewById(R.id.my_tiezi);
        //设置监听
        wdzl.setOnClickListener(this);
        wdhy.setOnClickListener(this);
        wdtz.setOnClickListener(this);
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case android.R.id.home:
                Log.d("BanKuaiFragment", "------------------>home selected");
                mAppCompatActivity.getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, new ZhuyeFragment())
                        .commit();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.my_ziliao:
                Log.d("kk", "-------------->my_ziliao");
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
        }
    }
}
