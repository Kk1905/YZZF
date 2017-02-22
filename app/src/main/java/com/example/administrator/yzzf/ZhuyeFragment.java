package com.example.administrator.yzzf;

import android.content.Intent;
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
import android.widget.ScrollView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;

/**
 * Created by Administrator on 2017/2/19 0019.
 */

public class ZhuyeFragment extends Fragment implements View.OnClickListener {
    AppCompatActivity mAppCompatActivity;
    PullToRefreshScrollView mPullToRefreshScrollView;
    View xinwenView;

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
//        xinwenView = view.findViewById(R.id.include_item_scrollview)
//                .findViewById(R.id.include_item_recyclerview);
        xinwenView = view.findViewById(R.id.item_recyclerview);
        xinwenView.setOnClickListener(this);

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

        Intent intent = new Intent(mAppCompatActivity, XiangQingActivity.class);
        startActivity(intent);

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
