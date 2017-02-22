package com.example.administrator.yzzf.Fragment;

import android.app.Activity;
import android.content.Intent;
import android.media.Image;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ScrollView;

import com.example.administrator.yzzf.Activity.MeiRiJingXuanActivity;
import com.example.administrator.yzzf.Activity.XiangQingActivity;
import com.example.administrator.yzzf.R;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.V;

/**
 * Created by Administrator on 2017/2/19 0019.
 */

public class ZhuyeFragment extends Fragment implements View.OnClickListener {
    AppCompatActivity mAppCompatActivity;
    PullToRefreshScrollView mPullToRefreshScrollView;
    View xinwenView;
    View meirijingxuanView;
    ImageView huatiTiaoZhuanImageView;
    View tiaozhuanXiangQingView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_zhuye, container, false);
        Toolbar mToolbar = (Toolbar) view.findViewById(R.id.toolbar_zhuye);
        mAppCompatActivity = (AppCompatActivity) getActivity();
        mAppCompatActivity.setSupportActionBar(mToolbar);
        ActionBar actionBar = mAppCompatActivity.getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowTitleEnabled(false);
        }
        //在这里统一获取view对象
        xinwenView = view.findViewById(R.id.item_recyclerview);
        meirijingxuanView = view.findViewById(R.id.meiri_jingxuan_imageview);
        huatiTiaoZhuanImageView = (ImageView) view.findViewById(R.id.htjl_tiaozhuan_imageview);
        tiaozhuanXiangQingView = view.findViewById(R.id.xiangqing_tiaozhuan_zhuye);
        //在这里统一对view对象设置监听事件
        xinwenView.setOnClickListener(this);
        meirijingxuanView.setOnClickListener(this);
        huatiTiaoZhuanImageView.setOnClickListener(this);
        tiaozhuanXiangQingView.setOnClickListener(this);

        //获取pullToRefreshScrollView组件
        mPullToRefreshScrollView = (PullToRefreshScrollView) view.findViewById(R.id.item_scrollview);
        //获取scrollView组件
        ScrollView scrollView = mPullToRefreshScrollView.getRefreshableView();
        //设置下拉刷新
        mPullToRefreshScrollView.setMode(PullToRefreshBase.Mode.PULL_FROM_START);
        //设置刷新标签
        mPullToRefreshScrollView.getLoadingLayoutProxy().setRefreshingLabel("正在刷新");
        //设置下拉标签
        mPullToRefreshScrollView.getLoadingLayoutProxy().setPullLabel("下拉刷新");
        //设置释放标签
        mPullToRefreshScrollView.getLoadingLayoutProxy().setReleaseLabel("释放开始刷新");
        //设置监听器
        mPullToRefreshScrollView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ScrollView>() {
            @Override
            public void onRefresh(PullToRefreshBase<ScrollView> pullToRefreshBase) {
                new GetDataTask().execute();
            }
        });
        return view;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.item_recyclerview:
                Intent intent01 = new Intent(mAppCompatActivity, XiangQingActivity.class);
                startActivity(intent01);
                break;
            case R.id.meiri_jingxuan_imageview:
                Intent intent02 = new Intent(mAppCompatActivity, MeiRiJingXuanActivity.class);
                startActivity(intent02);
                break;
            case R.id.htjl_tiaozhuan_imageview:
                Intent intent03 = new Intent(mAppCompatActivity, MeiRiJingXuanActivity.class);
                startActivity(intent03);
                break;
            case R.id.xiangqing_tiaozhuan_zhuye:
                Intent intent04 = new Intent(mAppCompatActivity, XiangQingActivity.class);
                startActivity(intent04);
                break;
        }


    }

    private class GetDataTask extends AsyncTask<Void, Void, Object> {

        @Override
        protected Object doInBackground(Void... params) {
//            try {
//                Thread.sleep(2000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
            Log.d("kk", "---------------->" + Thread.currentThread().getName());

            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            mPullToRefreshScrollView.onRefreshComplete();
            super.onPostExecute(o);
        }
    }
}
