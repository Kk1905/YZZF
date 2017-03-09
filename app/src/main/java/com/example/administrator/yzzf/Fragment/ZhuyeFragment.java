package com.example.administrator.yzzf.Fragment;


import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;

import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ScrollView;


import com.example.administrator.yzzf.Activity.BKSYActivity;
import com.example.administrator.yzzf.Activity.MeiRiJingXuanActivity;
import com.example.administrator.yzzf.Activity.XiangQingActivity;
import com.example.administrator.yzzf.Adapter.NewsItemAdapter;
import com.example.administrator.yzzf.Bean.NewsItemBean;
import com.example.administrator.yzzf.R;
import com.example.administrator.yzzf.Util.Json2NewsUtil;
import com.example.administrator.yzzf.Util.ListViewHeightUtil;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;

import java.util.ArrayList;


import me.maxwin.view.XListView;


/**
 * Created by Administrator on 2017/2/19 0019.
 */

public class ZhuyeFragment extends BaseFragment implements View.OnClickListener {

    PullToRefreshScrollView mPullToRefreshScrollView;
    View meirijingxuanView;
    ImageView huatiTiaoZhuanImageView;
    View tiaozhuanXiangQingView;
    ListView newsItemListView;
    NewsItemAdapter mAdapter;
    ArrayList<NewsItemBean> mDatas;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_zhuye, container, false);
        mToolbar = (Toolbar) view.findViewById(R.id.toolbar_zhuye);

        mDatas = Json2NewsUtil.test(mAppCompatActivity);

        mAdapter = new NewsItemAdapter(mAppCompatActivity, mDatas);
        //在这里统一获取view对象

        meirijingxuanView = view.findViewById(R.id.meiri_jingxuan_imageview);
        huatiTiaoZhuanImageView = (ImageView) view.findViewById(R.id.htjl_tiaozhuan_imageview);
        tiaozhuanXiangQingView = view.findViewById(R.id.xiangqing_tiaozhuan_zhuye);
        newsItemListView = (ListView) view.findViewById(R.id.newsItem_listView);
        newsItemListView.setAdapter(mAdapter);
        //手动为listView设置高度，因为和ScrollView嵌套，导致了一些问题，listView无法准确计算高度
        ListViewHeightUtil.setListViewHeightBasedOnChildren(newsItemListView);


        //在这里统一对view对象设置监听事件
//        xinwenView.setOnClickListener(this);
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

        view.findViewById(R.id.htjl_tiaozhuan_imageview).setOnClickListener(this);
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
                Intent intent = new Intent(mAppCompatActivity, BKSYActivity.class);
                startActivity(intent);
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
