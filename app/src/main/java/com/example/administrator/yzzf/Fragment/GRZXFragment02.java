package com.example.administrator.yzzf.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.example.administrator.yzzf.Activity.WDDDActivity;
import com.example.administrator.yzzf.Activity.WDTZActivity;
import com.example.administrator.yzzf.Activity.WDZLActivity;
import com.example.administrator.yzzf.Activity.XTXXActivity;
import com.example.administrator.yzzf.R;

import cn.bingoogolapple.badgeview.BGABadgeImageView;

/**
 * Created by Administrator on 2017/3/17 0017.
 */

public class GRZXFragment02 extends BaseFragment implements BottomNavigationBar.OnTabSelectedListener, View.OnClickListener {

    private BGABadgeImageView mBGABadgeImageView01, mBGABadgeImageView02, mBGABadgeImageView03, mBGABadgeImageView04;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = LayoutInflater.from(mAppCompatActivity).inflate(R.layout.fragment_grzx02, container, false);
        mToolbar = (Toolbar) view.findViewById(R.id.toolbar_grzx);
        mAppCompatActivity.setSupportActionBar(mToolbar);
        ActionBar actionBar = mAppCompatActivity.getSupportActionBar();
        if (actionBar != null) {

            actionBar.setDisplayShowTitleEnabled(false);
        }

        view.findViewById(R.id.grzx_chakanquanbu).setOnClickListener(this);
        view.findViewById(R.id.my_ziliao).setOnClickListener(this);
        view.findViewById(R.id.my_tiezi).setOnClickListener(this);
        view.findViewById(R.id.xitong).setOnClickListener(this);
        view.findViewById(R.id.grzx_jifen_textview_duihuan).setOnClickListener(this);
        view.findViewById(R.id.grzx_chakanquanbu).setOnClickListener(this);

        mBGABadgeImageView01 = (BGABadgeImageView) view.findViewById(R.id.grzx_daifukuan);
        mBGABadgeImageView02 = (BGABadgeImageView) view.findViewById(R.id.grzx_daishouhuo);
        mBGABadgeImageView03 = (BGABadgeImageView) view.findViewById(R.id.grzx_daifahuo);
        mBGABadgeImageView04 = (BGABadgeImageView) view.findViewById(R.id.grzx_tuikuan);
        mBGABadgeImageView01.showTextBadge("9");
        mBGABadgeImageView02.showTextBadge("5");
        mBGABadgeImageView03.showTextBadge("4");
        mBGABadgeImageView04.showTextBadge("1");

        return view;
    }


    @Override
    public void onTabSelected(int position) {

    }

    @Override
    public void onTabUnselected(int position) {

    }

    @Override
    public void onTabReselected(int position) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.my_tiezi:
                Intent intent02 = new Intent(mAppCompatActivity, WDTZActivity.class);
                mAppCompatActivity.startActivity(intent02);
                break;
            case R.id.my_ziliao:
                Intent intent = new Intent(mAppCompatActivity, WDZLActivity.class);
                mAppCompatActivity.startActivity(intent);
                break;
            case R.id.xitong:
                Intent intent05 = new Intent(mAppCompatActivity, XTXXActivity.class);
                mAppCompatActivity.startActivity(intent05);
                break;
            case R.id.grzx_chakanquanbu:
                Intent intent03 = new Intent(mAppCompatActivity, WDDDActivity.class);
                mAppCompatActivity.startActivity(intent03);
                break;
        }
    }
}
