package com.example.administrator.yzzf.Fragment;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.util.SparseArrayCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.yzzf.Activity.ActivityTest03;
import com.example.administrator.yzzf.Activity.BKSYActivity02;
import com.example.administrator.yzzf.Activity.FengxiangActivity;
import com.example.administrator.yzzf.Activity.LoginActivity;
import com.example.administrator.yzzf.Activity.MainActivity;
import com.example.administrator.yzzf.Activity.TestActivity;
import com.example.administrator.yzzf.Adapter.NewsItemRecyclerViewAdapter;
import com.example.administrator.yzzf.Bean.GuanggaoItemBean;
import com.example.administrator.yzzf.Bean.LunboBean;
import com.example.administrator.yzzf.Bean.NewsItemBean;
import com.example.administrator.yzzf.CustomView.GlideImageLoader;
import com.example.administrator.yzzf.CustomView.ImageCycleView;
import com.example.administrator.yzzf.Dao.NewsItemDao;
import com.example.administrator.yzzf.R;
import com.example.administrator.yzzf.Util.Constant;
import com.example.administrator.yzzf.Util.Json2NewsUtil;
import com.example.administrator.yzzf.Util.NetUtil;
import com.example.administrator.yzzf.Util.RefreshTimeUtil;
import com.liaoinstan.springview.container.DefaultFooter;
import com.liaoinstan.springview.container.MeituanHeader;
import com.liaoinstan.springview.widget.SpringView;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static android.content.Context.MODE_PRIVATE;


/**
 * Created by Administrator on 2017/3/27 0027.
 */

public class ZhuyeFragment02 extends BaseFragment implements View.OnClickListener, LoginFragment.ChangeToolBar {
    private static final int LOAD_MORE = 0;
    private static final int REFRESH = 1;
    private static final int LOAD_MORE_COMPELETE = 3;
    private static final int NON_NETWORK = 0X112;
    private static final int ERROR_SERVICE = 0X113;

    private boolean isFirstIn = true;//是否是首次显示
    private boolean hasNetWork = false;//是否有网络
    private boolean isDataFromNet;//数据是否来自网络
    private int currentPage = 0;//当前所处的页数，主要用来判断加载更多时，怎么加载数据

    private SpringView mSpringView;
    private RecyclerView mRecyclerView;
    private NewsItemRecyclerViewAdapter mAdapter;
    private NewsItemDao mNewsItemDao;
    private ImageCycleView mImageCycleView;
    private Banner banner;
    private int forum_id = 2;
    private ChangeTab mChangeTab;//通知activity转化下部导航的接口

    ArrayList<NewsItemBean> mDatas = new ArrayList<>();
    ArrayList<NewsItemBean> loadMoreDatas = new ArrayList<>();

    private static int HEADER_ITEM_VIEW_TYPE = 10000;

    private int[] pullAnimSrcs = new int[]{R.drawable.shuaxin02, R.drawable.shuaxin01};
    private int[] refreshAnimSrcs = new int[]{R.drawable.shuaxin03, R.drawable.shuaxin04, R.drawable.shuaxin05};
//    private int[] loadingAnimSrcs = new int[]{R.drawable.mt_loading01, R.drawable.mt_loading02};

//    private int[] pullAnimSrcs = new int[]{R.drawable.mt_pull, R.drawable.mt_pull01, R.drawable.mt_pull02, R.drawable.mt_pull03, R.drawable.mt_pull04, R.drawable.mt_pull05};
//    private int[] refreshAnimSrcs = new int[]{R.drawable.mt_refreshing01, R.drawable.mt_refreshing02, R.drawable.mt_refreshing03, R.drawable.mt_refreshing04, R.drawable.mt_refreshing05, R.drawable.mt_refreshing06};
//    private int[] loadingAnimSrcs = new int[]{R.drawable.mt_loading01, R.drawable.mt_loading02};

    private boolean hasLogin = false;//默认为false
    private int userId;
    private String phone;
    TextView login_textview;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Activity activity = getActivity();
        if (activity instanceof ChangeTab) {
            mChangeTab = (ChangeTab) activity;
        } else {
            try {
                throw new Exception("activity must instanceof ChangeTab");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
//        Bundle bundle = getArguments();
//        if (bundle != null) {
//            userId = bundle.getInt("userId");
//        }
        Log.e("kkkboy", "zhuyeFragment02--------->onCreate");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_zhuye02, container, false);
        mToolbar = (Toolbar) view.findViewById(R.id.toolbar_zhuye02);
        login_textview = (TextView) view.findViewById(R.id.toolbar_zhuye_login02);
        login_textview.setOnClickListener(this);

        SharedPreferences preferences = mAppCompatActivity.getApplicationContext().getSharedPreferences(Constant.YZZF_APP, MODE_PRIVATE);
        userId = Integer.parseInt(preferences.getString("userId", "-1"));
        phone = preferences.getString("mobile", "");
//        Log.e("kkkboy", "userId------------------>" + userId);
//        Map<String,Object> map= (Map<String, Object>) preferences.getAll();
        //判断是否用户已登录
        if (userId == -1) {
            //说明用户没有登录
            hasLogin = false;
            login_textview.setText(R.string.login);
        } else {
            hasLogin = true;
            //表示已登录，修改toolbar
            login_textview.setText("切换");
        }
        mSpringView = (SpringView) view.findViewById(R.id.fragment_zhuye_spring_view);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.fragment_zhuye_recycler_view);
        view.findViewById(R.id.item_zhuye_head_tuijian).setOnClickListener(this);
        view.findViewById(R.id.item_zhuye_head_remen).setOnClickListener(this);
        view.findViewById(R.id.item_zhuye_head_shehui).setOnClickListener(this);
        view.findViewById(R.id.item_zhuye_head_yule).setOnClickListener(this);
        view.findViewById(R.id.item_zhuye_head_keji).setOnClickListener(this);
        view.findViewById(R.id.item_zhuye_head_gengduo).setOnClickListener(this);

        Log.e("kkkboy", "zhuyeFragment02--------->onCreateView");
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mNewsItemDao = new NewsItemDao(mAppCompatActivity);
        mAdapter = new NewsItemRecyclerViewAdapter(mAppCompatActivity, mDatas, new NewsItemRecyclerViewAdapter.CallBack() {
            @Override
            public void onItemClick(View v, NewsItemBean newsItemBean) {
                Intent intent = new Intent(mAppCompatActivity, TestActivity.class);
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
                mAppCompatActivity.startActivity(intent);
            }
        });

        mRecyclerView = (RecyclerView) getView().findViewById(R.id.fragment_zhuye_recycler_view);
        mSpringView = (SpringView) getView().findViewById(R.id.fragment_zhuye_spring_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mAppCompatActivity));
        //为recyclerview添加一个增加或删除item的动画效果
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        //添加分割线
        mRecyclerView.addItemDecoration(new DividerItemDecoration(mAppCompatActivity, DividerItemDecoration.VERTICAL));
        //设置头
        setHeaders(mRecyclerView);
        //设置adapter
        mRecyclerView.setAdapter(mAdapter);
        //实例化假的stickyView
        final View fakeStickyView = getView().findViewById(R.id.fake_sticky_view);
        //监听recyclerview的滚动，写出吸顶效果
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {

                //在Recyclerview的top+1像素位置处设置点，找当前的item
                View stickyView = recyclerView.findChildViewUnder(fakeStickyView.getMeasuredWidth() / 2, 1);
                if (stickyView == null || stickyView.getContentDescription() != null) {
                    fakeStickyView.setVisibility(View.VISIBLE);

                } else {
                    fakeStickyView.setVisibility(View.GONE);
                }
            }
        });

        //设置SpringView的样式
        mSpringView.setType(SpringView.Type.FOLLOW);
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


        mSpringView.setHeader(new MeituanHeader(mAppCompatActivity, pullAnimSrcs, refreshAnimSrcs));
//        mSpringView.setFooter(new MeituanFooter(mAppCompatActivity, loadingAnimSrcs));
        mSpringView.setFooter(new DefaultFooter(mAppCompatActivity));
        if (isFirstIn) {
            //第一次进来刷新数据
            new LoadDataTask().execute(REFRESH);
            isFirstIn = false;
        }
        Log.e("kkkboy", "zhuyeFragment02--------->onActivityCreate");
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.e("kkkboy", "zhuyeFragment02--------->onAttach");
    }


    private void setHeaders(RecyclerView recyclerView) {
        View view01 = LayoutInflater.from(mAppCompatActivity).inflate(R.layout.item_zhuye_heade04, recyclerView, false);
        View view02 = LayoutInflater.from(mAppCompatActivity).inflate(R.layout.fake_sticky_view, recyclerView, false);
        SparseArrayCompat headerViews = new SparseArrayCompat();
        headerViews.append(HEADER_ITEM_VIEW_TYPE, view01);
        headerViews.append(HEADER_ITEM_VIEW_TYPE + 1, view02);
        mAdapter.setHeaderViews(headerViews);

//        mImageCycleView = (ImageCycleView) view01.findViewById(R.id.image_cycle_view);

        view02.findViewById(R.id.item_zhuye_head_tuijian).setOnClickListener(this);
        view02.findViewById(R.id.item_zhuye_head_remen).setOnClickListener(this);
        view02.findViewById(R.id.item_zhuye_head_shehui).setOnClickListener(this);
        view02.findViewById(R.id.item_zhuye_head_yule).setOnClickListener(this);
        view02.findViewById(R.id.item_zhuye_head_keji).setOnClickListener(this);
        view02.findViewById(R.id.item_zhuye_head_gengduo).setOnClickListener(this);
//        mImageCycleView.setImageResources(lunboDatas);
        banner = (Banner) view01.findViewById(R.id.banner);
        initBanner(banner);
    }

    private void initBanner(Banner banner) {
        //设置图片标题:自动对应
        String title01 = new String("十大星级品牌联盟，全场2折起");
        String title02 = new String("全场2折起");
        String title03 = new String("十大星级品牌联盟");
        String title04 = new String("嗨购5折不要停");
        String title05 = new String("12趁现在");
        String title06 = new String("嗨购5折不要停，12.12趁现在");

        List<String> titles = new ArrayList<>();
        List<String> images = new ArrayList<>();
        titles.add(title01);
        titles.add(title02);
        titles.add(title03);
        titles.add(title04);
        titles.add(title05);
        titles.add(title06);


        images.add("http://img.zcool.cn/community/0166c756e1427432f875520f7cc838.jpg");
        images.add("http://img.zcool.cn/community/018fdb56e1428632f875520f7b67cb.jpg");
        images.add("http://img.zcool.cn/community/01c8dc56e1428e6ac72531cbaa5f2c.jpg");
        images.add("http://img.zcool.cn/community/01fda356640b706ac725b2c8b99b08.jpg");
        images.add("http://img.zcool.cn/community/01fd2756e142716ac72531cbf8bbbf.jpg");
        images.add("http://img.zcool.cn/community/0114a856640b6d32f87545731c076a.jpg");
        //设置样式,默认为:Banner.NOT_INDICATOR(不显示指示器和标题)
        banner.setBannerStyle(BannerConfig.NUM_INDICATOR_TITLE);

        //设置轮播样式（没有标题默认为右边,有标题时默认左边）
        banner.setIndicatorGravity(BannerConfig.RIGHT);

        //设置标题集合（当banner样式有显示title时）
        banner.setBannerTitles(titles);

        //设置是否自动轮播（不设置则默认自动）
        banner.isAutoPlay(true);

        //设置轮播图片间隔时间（不设置默认为2000）
        banner.setDelayTime(2500);

        //设置banner动画效果
        banner.setBannerAnimation(Transformer.Accordion);

        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());

        //设置图片资源:可选图片网址/资源文件，默认用Glide加载,也可自定义图片的加载框架
        //所有设置参数方法都放在此方法之前执行
        banner.setImages(images);

        //设置点击事件，下标是从0开始
//        banner.setOnBannerListener(new OnBannerListener() {
//            @Override
//            public void OnBannerClick(int position) {
//                Intent intent = new Intent(mAppCompatActivity, TestActivity.class);
//                startActivity(intent);
//            }
//        });

        //开始渲染
        banner.start();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.item_zhuye_head_tuijian:
                Intent intent01 = new Intent(mAppCompatActivity, BKSYActivity02.class);
                intent01.putExtra("title", "推荐");
                intent01.putExtra("userId", userId);
                mAppCompatActivity.startActivity(intent01);
                break;
            case R.id.item_zhuye_head_remen:
                Intent intent02 = new Intent(mAppCompatActivity, BKSYActivity02.class);
                intent02.putExtra("title", "热点");
                intent02.putExtra("userId", userId);
                mAppCompatActivity.startActivity(intent02);
                break;
            case R.id.item_zhuye_head_shehui:
                Intent intent03 = new Intent(mAppCompatActivity, BKSYActivity02.class);
                intent03.putExtra("title", "社会");
                intent03.putExtra("userId", userId);
                mAppCompatActivity.startActivity(intent03);
                break;
            case R.id.item_zhuye_head_yule:
                Intent intent04 = new Intent(mAppCompatActivity, BKSYActivity02.class);
                intent04.putExtra("title", "娱乐");
                intent04.putExtra("userId", userId);
                startActivity(intent04);
                break;
            case R.id.item_zhuye_head_keji:
                Intent intent05 = new Intent(mAppCompatActivity, BKSYActivity02.class);
                intent05.putExtra("title", "科技");
                intent05.putExtra("userId", userId);
                startActivity(intent05);
                break;
            case R.id.item_zhuye_head_gengduo:
                mChangeTab.changeTab(1);
                break;
            case R.id.toolbar_zhuye_login02:
                Intent intent = new Intent(mAppCompatActivity, LoginActivity.class);
                mAppCompatActivity.startActivity(intent);
                break;
        }
    }

    @Override
    public void changeToolBar() {
        login_textview.setText("切换");
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
                    Toast.makeText(mAppCompatActivity, R.string.xinwen_no_net, Toast.LENGTH_SHORT).show();
                    break;
                case ERROR_SERVICE:
                    Toast.makeText(mAppCompatActivity, R.string.xinwen_service, Toast.LENGTH_SHORT).show();
                    return;//既然服务器异常，就没必要更新数据了
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
        if (NetUtil.checkNet(mAppCompatActivity)) {
            hasNetWork = true;//有网络
            isDataFromNet = true;//此时设置数据来自网络

            //设置刷新时间
            RefreshTimeUtil.setRefreshTime(mAppCompatActivity, Constant.ZY_FRAGMENT_TYPE);

            try {
                //获取最新的数据
                String url = Constant.NEWS_REFRESH;
                url += "?forumId=" + forum_id;
                mDatas = Json2NewsUtil.getNewsItem(mAppCompatActivity, url);

                //此时清除手机SQLite数据库里的旧数据,froumid=2,首页显示的新闻
                mNewsItemDao.removeAllforumid(forum_id);
                //将新的数据保存，在没有网络的时候用
                mNewsItemDao.add(mDatas);

//                Log.e("kkkboy", "mDatas----------------->" + String.valueOf(mDatas.size()));
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
            //从服务器加载数据
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
            url += "?forumId=" + forum_id + "&pagination=" + currentPage;
            try {
                loadMoreDatas = Json2NewsUtil.getNewsItem(mAppCompatActivity, url);
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
    public void onStart() {
        super.onStart();
        //开始轮播
        banner.startAutoPlay();
    }

    @Override
    public void onPause() {
        super.onPause();
        //结束轮播
        banner.stopAutoPlay();
    }

    @Override
    public void onStop() {
        super.onStop();
        //结束轮播
        banner.stopAutoPlay();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        //结束轮播
        banner.stopAutoPlay();
    }

    public interface ChangeTab {
        void changeTab(int position);

    }
}
