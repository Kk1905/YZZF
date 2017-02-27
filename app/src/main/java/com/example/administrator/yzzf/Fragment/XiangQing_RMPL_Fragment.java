package com.example.administrator.yzzf.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.administrator.yzzf.Activity.AllPingLunActivity;
import com.example.administrator.yzzf.Activity.AllPinglunFragment;
import com.example.administrator.yzzf.Activity.XiangQingActivity;
import com.example.administrator.yzzf.R;

/**
 * Created by Administrator on 2017/2/27 0027.
 */

public class XiangQing_RMPL_Fragment extends BaseFragment implements View.OnClickListener {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = LayoutInflater.from(mAppCompatActivity).inflate(R.layout.fragment_xiangqing_rmpl, container, false);
        mToolbar = (Toolbar) view.findViewById(R.id.toolbar_xiangqing);
        mAppCompatActivity.setSupportActionBar(mToolbar);

        ActionBar actionBar = mAppCompatActivity.getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowTitleEnabled(false);
        }
        view.findViewById(R.id.gengduopinglun).setOnClickListener(this);
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        mAppCompatActivity.getMenuInflater().inflate(R.menu.menu_main, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_dingwei:
                return true;
            case R.id.menu_login:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.gengduopinglun:
                mAppCompatActivity.getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.xiangqing_fragment_container, new AllPinglunFragment())
                        .commit();
                break;
        }
    }
}
