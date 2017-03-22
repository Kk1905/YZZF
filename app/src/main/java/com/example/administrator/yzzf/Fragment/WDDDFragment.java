package com.example.administrator.yzzf.Fragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.administrator.yzzf.Adapter.WdddItemAdapter;
import com.example.administrator.yzzf.Bean.WdddItemBean;
import com.example.administrator.yzzf.R;
import com.example.administrator.yzzf.Util.Json2NewsUtil;
import com.example.administrator.yzzf.Util.NetUtil;

import java.util.ArrayList;
import java.util.List;

import me.maxwin.view.IXListViewLoadMore;
import me.maxwin.view.XListView;

import static com.sina.weibo.sdk.openapi.legacy.AccountAPI.CAPITAL.A;

/**
 * Created by Administrator on 2017/3/20 0020.
 */

public class WDDDFragment extends BaseFragment implements IXListViewLoadMore, View.OnClickListener {
    private XListView mXListView;
    private List<WdddItemBean> mDatas;
    private String url;
    private WdddItemAdapter mAdapter;
    private boolean hasNetWork;
    private static final int NO_NET_WORK = 0X111;
    private static final int ERROR_SERVICE = 0X112;
    private static final int NORMAL = 0X113;
    private FrameLayout mFrameLayout, mFrameLayout02;
    private Button chongxinjiazai;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = LayoutInflater.from(mAppCompatActivity).inflate(R.layout.wddd_fragment, container, false);

        mXListView = (XListView) view.findViewById(R.id.wddd_fragment_xlistview);
        //设置上拉加载更多
        mXListView.setPullLoadEnable(this);
        mFrameLayout = (FrameLayout) view.findViewById(R.id.wddd_fragment_framenlayout);
        mFrameLayout02 = (FrameLayout) view.findViewById(R.id.wddd_fragment_framenlayout02);
        chongxinjiazai = (Button) view.findViewById(R.id.wddd_fragment_button);
        chongxinjiazai.setOnClickListener(this);
        mAdapter = new WdddItemAdapter(mAppCompatActivity, mDatas, new WdddItemAdapter.OnViewChangeListener() {
            @Override
            public void setOnViewChange(View v) {
                switch (v.getId()) {
                    case R.id.item_wodedingdan_image_button:
                        //TODO
                        break;
                    case com.example.administrator.yzzf.R.id.item_wodedingdan_quxiao:
                        //TODO
                        break;
                }
            }
        });
        new ReadDataTask().execute();

        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDatas = new ArrayList<>();
        url = getArguments().getString("url");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.wddd_fragment_button:
                new ReadDataTask().execute();
                break;
        }
    }

    private class ReadDataTask extends AsyncTask<Void, Void, Integer> {

        @Override
        protected Integer doInBackground(Void... params) {
            return loadMore();
        }

        @Override
        protected void onPostExecute(Integer integer) {
            super.onPostExecute(integer);
            switch (integer) {
                case NO_NET_WORK:
                    mFrameLayout.setVisibility(View.VISIBLE);
                    mFrameLayout02.setVisibility(View.GONE);
                    mXListView.setVisibility(View.GONE);
                    break;
                case ERROR_SERVICE:
                    mFrameLayout.setVisibility(View.GONE);
                    mFrameLayout02.setVisibility(View.VISIBLE);
                    mXListView.setVisibility(View.GONE);
                    break;
                case NORMAL:
                    mFrameLayout.setVisibility(View.GONE);
                    mFrameLayout02.setVisibility(View.GONE);
                    mXListView.setVisibility(View.VISIBLE);
                    mXListView.setAdapter(mAdapter);
            }

        }
    }

    @Override
    public void onLoadMore() {

        //TODO
    }

    private Integer loadMore() {

        //真正执行访问服务器的方法
        if (NetUtil.checkNet(mAppCompatActivity)) {
            //有网络
            hasNetWork = true;
            try {
                //从网络获取数据
                mDatas = Json2NewsUtil.getWdddMessages(mAppCompatActivity, url);

            } catch (Exception e) {
                e.printStackTrace();
                return ERROR_SERVICE;
            }
        } else {
            //无网络连接
            hasNetWork = false;
            return NO_NET_WORK;
        }
        return NORMAL;
    }
}
