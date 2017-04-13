package com.example.administrator.yzzf.Activity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.yzzf.Adapter.AllPinglunItemAdapter;
import com.example.administrator.yzzf.Bean.PinglunItemBean;
import com.example.administrator.yzzf.R;
import com.example.administrator.yzzf.Util.ActivityStack;
import com.example.administrator.yzzf.Util.Constant;
import com.example.administrator.yzzf.Util.Json2NewsUtil;
import com.example.administrator.yzzf.Util.MyApplication;
import com.example.administrator.yzzf.Util.NetUtil;
import com.liaoinstan.springview.container.DefaultFooter;
import com.liaoinstan.springview.container.DefaultHeader;
import com.liaoinstan.springview.widget.SpringView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/4/9 0009.
 */

public class AllPinglunActivity extends AppCompatActivity implements View.OnClickListener {
    private SpringView mSpringView;
    private RecyclerView mRecyclerView;
    private AllPinglunItemAdapter mAdapter;
    private List<PinglunItemBean> mDatas = new ArrayList<>();
    private static final int REFRESH = 0;
    private static final int LOAD = 1;
    private static final int SERVICE_ERROR = 0X110;
    private static final int NO_NET_WORK = 0X111;
    private static final int COMPLETE_REFRESH = 0X112;
    private static final int COMPLETE_LOAD = 0X113;

    private int articleId;
    private int currentPage = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_pinglun);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_login);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowTitleEnabled(false);
        }
        TextView title_textview = (TextView) findViewById(R.id.login_toolbar_title);
        title_textview.setText(R.string.quanbupinglun);

        findViewById(R.id.toolbar_up).setOnClickListener(this);
        mSpringView = (SpringView) findViewById(R.id.all_pinglun_springview);
        mRecyclerView = (RecyclerView) findViewById(R.id.all_pinglun_recyclerview);
        initView();

        ActivityStack.getInstance().addActivity(this);
    }

    private void initView() {
        articleId = getIntent().getIntExtra("articleId", 0);
        mAdapter = new AllPinglunItemAdapter(AllPinglunActivity.this, mDatas);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(AllPinglunActivity.this));
//        mRecyclerView.addItemDecoration(new DividerItemDecoration(AllPinglunActivity.this, DividerItemDecoration.VERTICAL));
        mRecyclerView.setAdapter(mAdapter);
        mSpringView.setHeader(new DefaultHeader(AllPinglunActivity.this));
        mSpringView.setFooter(new DefaultFooter(AllPinglunActivity.this));
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
                new DownloadDatas().execute(REFRESH);
            }

            @Override
            public void onLoadmore() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mSpringView.onFinishFreshAndLoad();
                    }
                }, 1000);
                new DownloadDatas().execute(LOAD);
            }
        });

        //第一次进来就执行刷新操作，加载第一轮最新数据
        new DownloadDatas().execute(REFRESH);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.toolbar_up:
                finish();
                break;
        }
    }

    private class DownloadDatas extends AsyncTask<Integer, Void, Integer> {

        @Override
        protected Integer doInBackground(Integer... params) {
            switch (params[0]) {
                case REFRESH:
                    return refresh();
                case LOAD:
                    return loadMore();
                default:
                    return -1;
            }
        }

        @Override
        protected void onPostExecute(Integer integer) {
            switch (integer) {
                case NO_NET_WORK:
                    Toast.makeText(AllPinglunActivity.this, "没有网络连接", Toast.LENGTH_SHORT).show();
                    break;
                case SERVICE_ERROR:
                    Toast.makeText(AllPinglunActivity.this, "服务器异常，请稍后再试", Toast.LENGTH_SHORT).show();
                    break;
                case COMPLETE_REFRESH:
                    mAdapter.replace(mDatas);
                    break;
                case COMPLETE_LOAD:
                    mAdapter.addAll(mDatas);
                    break;
            }
        }
    }

    private Integer refresh() {
        if (NetUtil.checkNet(AllPinglunActivity.this)) {
            //有网络
            String url = Constant.PING_LUN_REFRESH;
            url += "?articleId=" + articleId;
//            Log.e("kkkboy", url);
            try {
                //获取最新一轮数据
                mDatas = Json2NewsUtil.getPinglunItem(AllPinglunActivity.this, url);
                Log.e("kkkboy", mDatas.size() + "---refresh---->");
                return COMPLETE_REFRESH;
            } catch (Exception e) {
                e.printStackTrace();
                return SERVICE_ERROR;
            }
        } else {
            //没有网络
            return NO_NET_WORK;
        }
    }

    private Integer loadMore() {
        if (NetUtil.checkNet(AllPinglunActivity.this)) {
            currentPage += 1;
            String url = Constant.PING_LUN_LOAD;
            url += "?articleId=" + articleId + "&pagination=" + currentPage;
            try {
                mDatas = Json2NewsUtil.getPinglunItem(AllPinglunActivity.this, url);
                return COMPLETE_LOAD;
            } catch (Exception e) {
                e.printStackTrace();
                currentPage -= 1;
                return SERVICE_ERROR;
            }
        } else {

            return NO_NET_WORK;
        }
    }
}
