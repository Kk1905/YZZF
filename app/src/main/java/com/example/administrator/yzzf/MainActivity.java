package com.example.administrator.yzzf;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.Window;
import android.widget.ScrollView;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshHorizontalScrollView;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;
import static android.os.Build.VERSION_CODES.M;


/**
 * Created by Administrator on 2017/2/15 0015.
 */

public class MainActivity extends AppCompatActivity implements BottomNavigationBar.OnTabSelectedListener {
    private PullToRefreshScrollView mPullToRefreshScrollView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iniToolBarSet();
        iniBottomNavigationBar();

        //获取pullToRefreshScrollView组件
        mPullToRefreshScrollView = (PullToRefreshScrollView) findViewById(R.id.item_scrollview);
        //获取scrollView组件
        ScrollView scrollView = mPullToRefreshScrollView.getRefreshableView();
        //设置下拉刷新
        mPullToRefreshScrollView.setMode(PullToRefreshBase.Mode.PULL_FROM_START);
//        //设置刷新标签
//        mPullToRefreshScrollView.getLoadingLayoutProxy().setRefreshingLabel("正在刷新");
//        //设置下拉标签
//        mPullToRefreshScrollView.getLoadingLayoutProxy().setPullLabel("下拉刷新");
//        //设置释放标签
//        mPullToRefreshScrollView.getLoadingLayoutProxy().setReleaseLabel("释放开始刷新");

        //设置监听器
        mPullToRefreshScrollView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ScrollView>() {
            @Override
            public void onRefresh(PullToRefreshBase<ScrollView> pullToRefreshBase) {
                new GetDataTask().execute();
            }
        });


    }

    private class GetDataTask extends AsyncTask<Void, Void, Object> {

        @Override
        protected Object doInBackground(Void... params) {
//            try {
//                Thread.sleep(2000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
            Log.d("kk", "---------------->"+Thread.currentThread().getName());

            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            mPullToRefreshScrollView.onRefreshComplete();
            super.onPostExecute(o);
        }
    }

    //初始化BottomNavigationBar
    private void iniBottomNavigationBar() {
        BottomNavigationBar bottomNavigationBar = (BottomNavigationBar) findViewById(R.id.bottom_navigation_bar);
        bottomNavigationBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC);
        bottomNavigationBar.setMode(BottomNavigationBar.MODE_FIXED);
        bottomNavigationBar.addItem(new BottomNavigationItem(R.drawable.zhuye, R.string.zheye))
                .addItem(new BottomNavigationItem(R.drawable.bankuai, R.string.bankuai))
                .addItem(new BottomNavigationItem(R.mipmap.yangzi_daohang, R.string.yangzi_daohang).setInActiveColorResource(R.color.yangzi))
                .addItem(new BottomNavigationItem(R.drawable.gexing, R.string.gexing))
                .addItem(new BottomNavigationItem(R.drawable.wode, R.string.wode))
                .setActiveColor(R.color.colorPrimary)
                .setBarBackgroundColor(R.color.main_background)
                .setInActiveColor(R.color.daohang)
                .initialise();
        bottomNavigationBar.setTabSelectedListener(this);
    }

    //初始化ToolBar
    private void iniToolBarSet() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_zhuye);

        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowTitleEnabled(false);
        }
//        actionBar.setDisplayHomeAsUpEnabled(true);
//        actionBar.setHomeButtonEnabled(true);

    }

    @Override
    public void onTabSelected(int position) {
//DO SOMETHING
    }

    @Override
    public void onTabUnselected(int position) {
//DO SOMETHING
    }

    @Override
    public void onTabReselected(int position) {
//DO SOMETHING
    }
}
