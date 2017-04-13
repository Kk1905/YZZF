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

import com.example.administrator.yzzf.Activity.LoginActivity;
import com.example.administrator.yzzf.Activity.TestActivity;
import com.example.administrator.yzzf.Adapter.NewsItemAdapter;
import com.example.administrator.yzzf.Bean.LunboBean;
import com.example.administrator.yzzf.Bean.NewsItemBean;
import com.example.administrator.yzzf.CustomView.ImageCycleView;
import com.example.administrator.yzzf.Dao.NewsItemDao;
import com.example.administrator.yzzf.R;
import com.example.administrator.yzzf.Util.Constant;
import com.example.administrator.yzzf.Util.Json2NewsUtil;
import com.example.administrator.yzzf.Util.NetUtil;
import com.example.administrator.yzzf.Util.RefreshTimeUtil;

import java.util.ArrayList;

import me.maxwin.view.IXListViewLoadMore;
import me.maxwin.view.IXListViewRefreshListener;
import me.maxwin.view.XListView;


/**
 * Created by Administrator on 2017/2/19 0019.
 */

public class ZhuyeFragment extends BaseFragment implements IXListViewRefreshListener, IXListViewLoadMore, View.OnClickListener {

    private static final int LOAD_MORE = 0;
    private static final int REFRESH = 1;
    private static final int NON_NETWORK = 0X112;
    private static final int ERROR_SERVICE = 0X113;

    private boolean isFirstIn = true;//是否是首次显示
    private boolean hasNetWork = false;//是否有网络
    private boolean isDataFromNet;//数据是否来自网络

    private ArrayList<LunboBean> lunboDatas = new ArrayList<>();
    XListView newsItemListView;
    NewsItemAdapter mAdapter;
    NewsItemDao mNewsItemDao;
    private ImageCycleView mImageCycleView;

    ArrayList<NewsItemBean> mDatas = new ArrayList<>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LunboBean lunboBean01 = new LunboBean();
        lunboBean01.setPictureUrl("http://img.taodiantong.cn/v55183/infoimg/2013-07/130720115322ky.jpg");
        lunboBean01.setTitle("0110101101110100101");
        lunboBean01.setUrl("http://news.sina.com.cn/china/xlxw/2017-03-23/doc-ifycstww0684406.shtml");
        lunboDatas.add(lunboBean01);

        LunboBean lunboBean02 = new LunboBean();
        lunboBean02.setPictureUrl("http://pic30.nipic.com/20130626/8174275_085522448172_2.jpg");
        lunboBean02.setTitle("020202020202020202");
        lunboBean02.setUrl("http://news.sina.com.cn/c/nd/2017-03-23/doc-ifycstww0746052.shtml");
        lunboDatas.add(lunboBean02);

        LunboBean lunboBean03 = new LunboBean();
        lunboBean03.setPictureUrl("");
        lunboBean03.setTitle("03030303030303030303030303030303030303");
        lunboBean03.setUrl("http://news.sina.com.cn/o/2017-03-23/doc-ifycsukm3308612.shtml");
        lunboDatas.add(lunboBean03);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_zhuye, container, false);
        mToolbar = (Toolbar) view.findViewById(R.id.toolbar_zhuye02);
        view.findViewById(R.id.toolbar_zhuye_login02).setOnClickListener(this);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mNewsItemDao = new NewsItemDao(mAppCompatActivity);
        mAdapter = new NewsItemAdapter(mAppCompatActivity, mDatas, lunboDatas, new NewsItemAdapter.CallBack() {
            @Override
            public void xListViewItemClick(View v) {
                switch (v.getId()) {
                    case R.id.item_zhuye_head_tuijian:

                        break;
                    case R.id.item_zhuye_head_remen:

                        break;

                }
            }

            @Override
            public void getImageCycleView(ImageCycleView imageCycleView) {
                mImageCycleView = imageCycleView;
            }
        });


        newsItemListView = (XListView) getView().findViewById(R.id.item_scrollView);
        newsItemListView.setAdapter(mAdapter);
        newsItemListView.setPullLoadEnable(this);//设置上拉加载
        newsItemListView.setPullRefreshEnable(this);//设置下拉刷新
        newsItemListView.setRefreshTime(RefreshTimeUtil.getRefreshTime(mAppCompatActivity, Constant.ZY_FRAGMENT_TYPE));

        newsItemListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(mAppCompatActivity, TestActivity.class);
//                intent.putExtra("url", mDatas.get(position - 2).getStringUrl());//传递一个url参数过去给详情Activity
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
    public void onPause() {
        super.onPause();
        mImageCycleView.pushImageCycle();
    }

    @Override
    public void onStop() {
        super.onStop();
        mImageCycleView.pushImageCycle();
        Log.e("kkkboy", "onStop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mImageCycleView.pushImageCycle();
        Log.e("kkkboy", "onDestroyView");
    }

    @Override
    public void onRefresh() {
        //XListView的下拉刷新
        new LoadDataTask().execute(REFRESH);
    }

    @Override
    public void onLoadMore() {
        //XListView的上拉加载

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.toolbar_zhuye_login02:
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
                    return refreshData();
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
            mNewsItemDao.removeAllisIndex("0");
            //将新的数据保存，在没有网络的时候用
            mNewsItemDao.add(mDatas);
            //TODO 还有一个表也要操作
            Log.e("kkkboy", "mDatas----------------->" + String.valueOf(mDatas.size()));
        } else {
            //没有网络的情况下
            hasNetWork = false;
            isDataFromNet = false;
            //TODO 从数据库加载数据显示
//            mDatas = mNewsItemDao.list("0");
            //返回没有网络的flag
            return NON_NETWORK;
        }
        return -1;
    }

    //下拉加载数据
    private void loadMore() {

    }
}
