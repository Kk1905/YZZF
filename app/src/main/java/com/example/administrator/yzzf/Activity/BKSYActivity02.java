package com.example.administrator.yzzf.Activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.util.SparseArrayCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.yzzf.Adapter.BKSYItemAdapter;
import com.example.administrator.yzzf.Adapter.NewsItemRecyclerViewAdapter;
import com.example.administrator.yzzf.Bean.NewsItemBean;
import com.example.administrator.yzzf.Dao.NewsItemDao;
import com.example.administrator.yzzf.R;
import com.example.administrator.yzzf.Util.ActivityStack;
import com.example.administrator.yzzf.Util.Constant;
import com.example.administrator.yzzf.Util.Json2NewsUtil;
import com.example.administrator.yzzf.Util.MyApplication;
import com.example.administrator.yzzf.Util.NetUtil;
import com.example.administrator.yzzf.Util.RefreshTimeUtil;
import com.liaoinstan.springview.container.DefaultFooter;
import com.liaoinstan.springview.container.DefaultHeader;
import com.liaoinstan.springview.widget.SpringView;

import java.util.ArrayList;
import java.util.List;

import me.maxwin.view.XListView;

/**
 * Created by Administrator on 2017/3/24 0024.
 */

public class BKSYActivity02 extends AppCompatActivity implements View.OnClickListener {

    private static final int LOAD_MORE = 0;
    private static final int REFRESH = 1;
    private static final int LOAD_MORE_COMPELETE = 3;
    private static final int NON_NETWORK = 0X112;
    private static final int ERROR_SERVICE = 0X113;

    private boolean isFirstIn = true;//是否是首次显示
    private boolean hasNetWork = false;//是否有网络
    private boolean isDataFromNet;//数据是否来自网络
    private int currentPage = 0;//当前所处的页数，主要用来判断加载更多时，怎么加载数据

    private RecyclerView mRecyclerView;
    private SpringView mSpringView;
    private BKSYItemAdapter mAdapter;
    private ArrayList<NewsItemBean> mDatas = new ArrayList<>();
    ArrayList<NewsItemBean> loadMoreDatas = new ArrayList<>();
    private SparseArrayCompat<View> headerViews = new SparseArrayCompat();
    private NewsItemDao mNewsItemDao;
    private View header;
    private static int HEADER_VIEW_TYPE = 10000;

    private int forum_id;
    private int userId;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bksy02);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_login);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowTitleEnabled(false);
        }
        TextView title_textview = (TextView) findViewById(R.id.login_toolbar_title);
        String title = getIntent().getStringExtra("title");
        title_textview.setText(title);
        userId = getIntent().getIntExtra("userId", -1);
        //根据传来的标题确定版块id
        switch (title) {
            case "推荐":
                forum_id = 1;
                break;
            case "热点":
                forum_id = 2;
                break;
            case "社会":
                forum_id = 3;
                break;
            case "娱乐":
                forum_id = 4;
                break;
            case "科技":
                forum_id = 5;
                break;
            case "体育":
                forum_id = 6;
                break;
            case "汽车":
                forum_id = 7;
                break;
            case "财经":
                forum_id = 8;
                break;
            case "搞笑":
                forum_id = 9;
                break;
            case "军事":
                forum_id = 10;
                break;
            case "国际":
                forum_id = 11;
                break;
            case "时尚":
                forum_id = 12;
                break;
            case "旅游":
                forum_id = 13;
                break;
            case "探索":
                forum_id = 14;
                break;
            case "育儿":
                forum_id = 15;
                break;
            case "养生":
                forum_id = 16;
                break;
            case "故事":
                forum_id = 17;
                break;
            case "美文":
                forum_id = 18;
                break;
            case "游戏":
                forum_id = 19;
                break;
            case "历史":
                forum_id = 20;
                break;
            case "美食":
                forum_id = 21;
                break;

        }
        findViewById(R.id.toolbar_up).setOnClickListener(this);
        mRecyclerView = (RecyclerView) findViewById(R.id.activity_bksy_recycler_view);
        mSpringView = (SpringView) findViewById(R.id.activity_bksy_spring_view);
        mNewsItemDao = new NewsItemDao(BKSYActivity02.this);
        initView();

        ActivityStack.getInstance().addActivity(this);
    }

    private void initView() {

        mAdapter = new BKSYItemAdapter(this, mDatas, new NewsItemRecyclerViewAdapter.CallBack() {
            @Override
            public void onItemClick(View v, NewsItemBean newsItemBean) {
                Intent intent = new Intent(BKSYActivity02.this, TestActivity.class);
                if (newsItemBean != null) {
                    Bundle bundle = new Bundle();
                    //文章内容
                    bundle.putString("content", newsItemBean.getContent());
                    //文章标题
                    bundle.putString("title", newsItemBean.getTitle());
                    //来源
                    bundle.putString("source", newsItemBean.getSource());
                    //发布时间
                    bundle.putString("time", newsItemBean.getDisplayAdddate());
                    //使用app的用户的id,测试随便用个，57
                    bundle.putInt("userId", userId);
                    //图片url
                    bundle.putString("pictureUrl", newsItemBean.getPicture1());
                    //文章id
                    bundle.putInt("articleId", newsItemBean.getId());

                    //将bundle传递
                    intent.putExtra("bundle", bundle);
                }
                BKSYActivity02.this.startActivity(intent);
            }
        });
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
//        setHeader(mRecyclerView);
        mRecyclerView.setAdapter(mAdapter);
        mSpringView.setType(SpringView.Type.OVERLAP);
        mSpringView.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mSpringView.onFinishFreshAndLoad();
                    }
                }, 1000);
                new LoadDataTask().execute(REFRESH);
            }

            @Override
            public void onLoadmore() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mSpringView.onFinishFreshAndLoad();
                    }
                }, 1000);
                new LoadDataTask().execute(LOAD_MORE);
            }
        });
        mSpringView.setHeader(new DefaultHeader(this));
        mSpringView.setFooter(new DefaultFooter(this));

        isFirstIn = true;//每次onCreate调用,都视为第一次进来
        if (isFirstIn) {
            //第一次进来刷新数据
            new LoadDataTask().execute(REFRESH);
            isFirstIn = false;
            Log.e("kkkboy", "BKSY---------REFRESH01--------->");
        }
    }

    //从网上更新数据的异步任务
    private class LoadDataTask extends AsyncTask<Integer, Void, Integer> {

        @Override
        protected Integer doInBackground(Integer... params) {
            switch (params[0]) {
                case REFRESH:
                    return refreshData();
                case LOAD_MORE:
                    return loadMore();
            }
            return -1;
        }

        @Override
        protected void onPostExecute(Integer integer) {

            switch (integer) {
                case NON_NETWORK:
                    Toast.makeText(BKSYActivity02.this, R.string.xinwen_no_net, Toast.LENGTH_SHORT).show();
                    break;
                case ERROR_SERVICE:
                    Toast.makeText(BKSYActivity02.this, R.string.xinwen_service, Toast.LENGTH_SHORT).show();
                    break;
                case LOAD_MORE_COMPELETE:
                    //加载更多完成
                    mAdapter.addDatas(loadMoreDatas);//将最新数据追加
                    return;
                default:
                    break;
            }
            //数据刷新完成
            mAdapter.replaceDatas(mDatas);//将数据刷新

            //更新刷新的时间
//            newsItemListView.setRefreshTime(RefreshTimeUtil.getRefreshTime(mAppCompatActivity, Constant.ZY_FRAGMENT_TYPE));
//
//            newsItemListView.stopRefresh();//停止刷新，此时对应的效果就是listView的header隐藏
        }
    }

    //下拉刷新数据
    private Integer refreshData() {
        if (NetUtil.checkNet(BKSYActivity02.this)) {
            hasNetWork = true;//有网络
            isDataFromNet = true;//此时设置数据来自网络

            //设置刷新时间
            RefreshTimeUtil.setRefreshTime(BKSYActivity02.this, Constant.ZY_FRAGMENT_TYPE);

            try {
                //获取最新的数据
                String url = Constant.NEWS_REFRESH;
                url += "?forumId=" + forum_id;//设置请求参数
                mDatas = Json2NewsUtil.getNewsItem(BKSYActivity02.this, url);

                //此时清除手机SQLite数据库里的旧数据,forumid=?,属于什么版块的新闻
                mNewsItemDao.removeAllforumid(forum_id);
                //将新的数据保存，在没有网络的时候用
                mNewsItemDao.add(mDatas);

            } catch (Exception e) {
                e.printStackTrace();
                //代码运行报错，此时数据是否来自网络还应该改为false
                isDataFromNet = false;
                //返回服务器错误的flag
                return ERROR_SERVICE;
            }

        } else {
            //没有网络的情况下
            hasNetWork = false;
            isDataFromNet = false;
            //TODO 从数据库加载数据显示
            mDatas = mNewsItemDao.listByForumid(forum_id, 0);
            //返回没有网络的flag
            return NON_NETWORK;
        }
        return -1;
    }

    //上拉加载数据
    private Integer loadMore() {
        loadMoreDatas.clear();//每次加载更多都先清除之前加载的数据
        if (isDataFromNet) {
            //数据来自网络，有网
            currentPage += 1;
            String url = Constant.NEWS_LOAD;
            url += "?forumId=" + forum_id + "&pagination=" + currentPage;//请求参数
            try {
                loadMoreDatas = Json2NewsUtil.getNewsItem(BKSYActivity02.this, url);
                mNewsItemDao.add(loadMoreDatas);//将数据保存到SQLite

                return LOAD_MORE_COMPELETE;
            } catch (Exception e) {
                e.printStackTrace();
                currentPage -= 1;
                return ERROR_SERVICE;//服务器异常
            }
        } else {
            //从数据库加载
            currentPage += 1;
            loadMoreDatas = mNewsItemDao.listByForumid(forum_id, currentPage);
            return LOAD_MORE_COMPELETE;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.toolbar_up:
                finish();
                break;
        }
    }
}
