package com.example.administrator.yzzf.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.yzzf.Activity.BKSYActivity;
import com.example.administrator.yzzf.Activity.BKSYActivity02;
import com.example.administrator.yzzf.Activity.ShangPinActivity;
import com.example.administrator.yzzf.R;

/**
 * Created by Administrator on 2017/3/24 0024.
 */

public class BanKuaiFragment02 extends BaseFragment implements View.OnClickListener {
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = LayoutInflater.from(mAppCompatActivity).inflate(R.layout.fragment_bankuai02, container, false);
        mToolbar = (Toolbar) view.findViewById(R.id.toolbar_bankuai);
        mAppCompatActivity.setSupportActionBar(mToolbar);
        ActionBar actionBar = mAppCompatActivity.getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowTitleEnabled(false);
        }
        view.findViewById(R.id.fragment_bankuai_tuijian).setOnClickListener(this);
        view.findViewById(R.id.fragment_bankuai_junshi).setOnClickListener(this);

        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fragment_bankuai_tuijian:
                Intent intent = new Intent(mAppCompatActivity, ShangPinActivity.class);
                mAppCompatActivity.startActivity(intent);
                break;
            case R.id.fragment_bankuai_junshi:
                Intent intent02 = new Intent(mAppCompatActivity, BKSYActivity02.class);
                intent02.putExtra("title", "军事");
                mAppCompatActivity.startActivity(intent02);
                break;
        }
    }
}
