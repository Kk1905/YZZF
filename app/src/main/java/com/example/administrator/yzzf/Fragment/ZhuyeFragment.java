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
import android.widget.AdapterView;
import android.widget.Toast;

import com.example.administrator.yzzf.Activity.BKSYActivity;
import com.example.administrator.yzzf.Activity.LoginActivity;
import com.example.administrator.yzzf.Activity.MeiRiJingXuanActivity;
import com.example.administrator.yzzf.Activity.TestActivity;
import com.example.administrator.yzzf.Activity.XiangQingActivity;
import com.example.administrator.yzzf.Adapter.NewsItemAdapter;
import com.example.administrator.yzzf.Bean.NewsItemBean;
import com.example.administrator.yzzf.Dao.NewsItemDao;
import com.example.administrator.yzzf.R;
import com.example.administrator.yzzf.Util.Constant;
import com.example.administrator.yzzf.Util.Json2NewsUtil;
import com.example.administrator.yzzf.Util.NetUtil;
import com.example.administrator.yzzf.Util.RefreshTimeUtil;

import java.util.ArrayList;

import me.maxwin.view.IXListViewRefreshListener;
import me.maxwin.view.XListView;


/**
 * Created by Administrator on 2017/2/19 0019.
 */

public class ZhuyeFragment extends BaseFragment implements IXListViewRefreshListener, View.OnClickListener {

    private static final int LOAD_MORE = 0;
    private static final int REFRESH = 1;
    private static final int NON_NETWORK = 2;
    private static final int ERROR_SERVICE = 3;

    private boolean isFirstIn = true;//是否是首次显示
    private boolean hasNetWork = false;//是否有网络
    private boolean isDataFromNet;//数据是否来自网络
    XListView newsItemListView;
    NewsItemAdapter mAdapter;
    NewsItemDao mNewsItemDao;


    ArrayList<NewsItemBean> mDatas = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_zhuye, container, false);
        mToolbar = (Toolbar) view.findViewById(R.id.toolbar_zhuye);
        view.findViewById(R.id.toolbar_zhuye_login).setOnClickListener(this);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mNewsItemDao = new NewsItemDao(mAppCompatActivity);
        mAdapter = new NewsItemAdapter(mAppCompatActivity, mDatas, new NewsItemAdapter.CallBack() {
            @Override
            public void xListViewItemClick(View v) {
                switch (v.getId()) {
                    case R.id.meiri_jingxuan_imageView:
                        Intent intent02 = new Intent(mAppCompatActivity, MeiRiJingXuanActivity.class);
                        mAppCompatActivity.startActivity(intent02);
                        break;
                    case R.id.shequ_gonggao_imageView:
                        break;
                    case R.id.huatijiaoliu_imageView:
                        Intent intent04 = new Intent(mAppCompatActivity, BKSYActivity.class);
                        mAppCompatActivity.startActivity(intent04);
                        break;
                    case R.id.yezhuchangshi_imageView:
                        break;
                    case R.id.zulinzhihuan_imageView:
                        break;
                    case R.id.htjl_tiaozhuan_imageview:
                        Intent intent = new Intent(mAppCompatActivity, BKSYActivity.class);
                        mAppCompatActivity.startActivity(intent);
                        break;
                    case R.id.xiangqing_tiaozhuan_zhuye:
                        Intent intent01 = new Intent(mAppCompatActivity, XiangQingActivity.class);
                        mAppCompatActivity.startActivity(intent01);
                        break;
                }
            }
        });


        newsItemListView = (XListView) getView().findViewById(R.id.item_scrollView);
        newsItemListView.setAdapter(mAdapter);
//        newsItemListView.setPullLoadEnable(this); 在这个Fragment不需要上拉加载更多
        newsItemListView.setPullRefreshEnable(this);
        newsItemListView.setRefreshTime(RefreshTimeUtil.getRefreshTime(mAppCompatActivity, Constant.ZY_FRAGMENT_TYPE));

        newsItemListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(mAppCompatActivity, TestActivity.class);
                intent.putExtra("url", mDatas.get(position - 2).getStringUrl());//传递一个url参数过去给详情Activity
                mAppCompatActivity.startActivity(intent);

            }
        });
        //手动为listView设置高度，因为和ScrollView嵌套，导致了一些问题，listView无法准确计算高度
//        ListViewHeightUtil.setListViewHeightBasedOnChildren(newsItemListView);
        if (isFirstIn) {
            newsItemListView.startRefresh();//第一次进来，直接刷新
            isFirstIn = false;
        } else {
            newsItemListView.NotRefreshAtBegin();
        }
    }

    @Override
    public void onRefresh() {
        //XListView的下拉刷新
        new LoadDataTask().execute(REFRESH);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.toolbar_zhuye_login:
                Intent intent = new Intent(mAppCompatActivity, LoginActivity.class);
                mAppCompatActivity.startActivity(intent);
                break;
        }
    }

    //从网上更新数据的异步任务
    private class LoadDataTask extends AsyncTask<Integer, Void, Integer> {

        @Override
        protected Integer doInBackground(Integer... params) {
            switch (params[0]) {
                case REFRESH:
                    refreshData();
                    break;
            }
            return -1;
        }

        @Override
        protected void onPostExecute(Integer integer) {

            switch (integer) {
                case NON_NETWORK:
                    Toast.makeText(mAppCompatActivity, "没有网络连接", Toast.LENGTH_SHORT).show();
                    break;
                case ERROR_SERVICE:
                    Toast.makeText(mAppCompatActivity, "服务器异常，请稍后再试", Toast.LENGTH_SHORT).show();
                    break;
                default:
                    break;
            }
            mAdapter.setDatas(mDatas);
            mAdapter.notifyDataSetChanged();
            //更新刷新的时间
            newsItemListView.setRefreshTime(RefreshTimeUtil.getRefreshTime(mAppCompatActivity, Constant.ZY_FRAGMENT_TYPE));

            newsItemListView.stopRefresh();//停止刷新，此时对应的效果就是listView的header隐藏
        }
    }

    //下拉刷新数据
    private Integer refreshData() {
        if (NetUtil.checkNet(mAppCompatActivity)) {
            hasNetWork = true;//有网络
            isDataFromNet = true;//此时设置数据来自网络

            //设置刷新时间
            RefreshTimeUtil.setRefreshTime(mAppCompatActivity, Constant.ZY_FRAGMENT_TYPE);

            try {
                //获取最新的数据
                String url = "http://192.168.0.21:8080/Spring/ace/test";
                mDatas = Json2NewsUtil.getNewsItem(mAppCompatActivity, url);

            } catch (Exception e) {
                e.printStackTrace();
                //代码运行报错，此时数据是否来自网络还应该改为false
                isDataFromNet = false;
                //返回服务器错误的flag
                return ERROR_SERVICE;
            }
            //此时清除手机SQLite数据库里的旧数据,isindex=1
            mNewsItemDao.removeAll("0");
            //将新的数据保存，在没有网络的时候用
            mNewsItemDao.add(mDatas);
            //TODO 还有一个表也要操作
            Log.e("kkkboy", "mDatas----------------->" + String.valueOf(mDatas.size()));
        } else {
            //没有网络的情况下
            hasNetWork = false;
            isDataFromNet = false;
            //TODO 从数据库加载数据显示
            mDatas = mNewsItemDao.list("0");
            //返回没有网络的flag
            return NON_NETWORK;
        }
        return -1;
    }

}
