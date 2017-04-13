package com.example.administrator.yzzf.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.yzzf.Bean.NewsItemBean;
import com.example.administrator.yzzf.Bean.PinglunItemBean;
import com.example.administrator.yzzf.CustomView.Custom_RMPL_XiangQing;
import com.example.administrator.yzzf.R;
import com.example.administrator.yzzf.ShowDialog.Show_FenXiang_Dialog;
import com.example.administrator.yzzf.ShowDialog.Show_XiangQing_Write_Pinglun_Dialog;
import com.example.administrator.yzzf.Util.ActivityStack;
import com.example.administrator.yzzf.Util.Constant;
import com.example.administrator.yzzf.Util.Json2NewsUtil;
import com.example.administrator.yzzf.Util.MyApplication;
import com.example.administrator.yzzf.Util.NetUtil;
import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

import cn.bingoogolapple.badgeview.BGABadgeImageView;

import static com.example.administrator.yzzf.R.id.webView;


/**
 * Created by Administrator on 2017/3/14 0014.
 */

public class TestActivity extends AppCompatActivity implements View.OnClickListener {
    private FrameLayout mFrameLayout;
    private FrameLayout mFrameLayout02;
    private ScrollView mScrollView;
    private LinearLayout mLinearLayout;
    private BGABadgeImageView mBGABadgeImageView;
    private WebView mWebView;
    //    private String url;//跳转url
    private Show_XiangQing_Write_Pinglun_Dialog mShow_xiangQing_write_pinglun_dialog = null;
    private Show_FenXiang_Dialog mShow_fenXiang_dialog = null;
    private EditText editText;
    private int height;
    private TextView title_textview;
    private TextView source_textview;
    private TextView publish_time_textview;
    private NewsItemBean mNewsItemBean;
    private int pinglun_num;
    private LinearLayout linearLayout;
    private Handler mHandler = new Handler();
    //评论有关的view
    private View reply_view01;
    private TextView textView_username01;
    private TextView textView_hourago01;
    private TextView textView_content01;

    private View reply_view02;
    private TextView textView_username02;
    private TextView textView_hourago02;
    private TextView textView_content02;

    private View reply_view03;
    private TextView textView_username03;
    private TextView textView_hourago03;
    private TextView textView_content03;
    private TextView no_pinglun;

    //用户id
    private int extendUserId;
    //文章内容
    private String content;
    //文章标题
    private String article_title = "";
    //图片
    private String pictureUrl = "";
    //文章id
    private int articleId;
    //跳转url，这个是重点，要带参数,不是最终的
    private String article_url = "";

    private List<PinglunItemBean> mPinglunItemBeanList = new ArrayList<>();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_login);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowTitleEnabled(false);
        }
        TextView title = (TextView) findViewById(R.id.login_toolbar_title);
        title.setText(R.string.xiangqing);
        findViewById(R.id.toolbar_up).setOnClickListener(this);
        editText = (EditText) findViewById(R.id.woyao_edittext);
        //预先加载好评论的itemView,这样虽然有重复代码，但是可以节约资源
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        reply_view01 = LayoutInflater.from(TestActivity.this).inflate(R.layout.custom_item_rmpl, null);
        reply_view01.setLayoutParams(params);
        textView_username01 = (TextView) reply_view01.findViewById(R.id.custom_rmpl_username);
        textView_hourago01 = (TextView) reply_view01.findViewById(R.id.custom_rmpl_time01);
        textView_content01 = (TextView) reply_view01.findViewById(R.id.custom_rmpl_content);

        reply_view02 = LayoutInflater.from(TestActivity.this).inflate(R.layout.custom_item_rmpl, null);
        reply_view02.setLayoutParams(params);
        textView_username02 = (TextView) reply_view02.findViewById(R.id.custom_rmpl_username);
        textView_hourago02 = (TextView) reply_view02.findViewById(R.id.custom_rmpl_time01);
        textView_content02 = (TextView) reply_view02.findViewById(R.id.custom_rmpl_content);

        reply_view03 = LayoutInflater.from(TestActivity.this).inflate(R.layout.custom_item_rmpl, null);
        reply_view03.setLayoutParams(params);
        textView_username03 = (TextView) reply_view03.findViewById(R.id.custom_rmpl_username);
        textView_hourago03 = (TextView) reply_view03.findViewById(R.id.custom_rmpl_time01);
        textView_content03 = (TextView) reply_view03.findViewById(R.id.custom_rmpl_content);

        linearLayout = (LinearLayout) findViewById(R.id.three_pinglun);//装载三条评论的viewGroup
        no_pinglun = (TextView) findViewById(R.id.no_pinglun);//没有任何评论时显示

        editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    if (extendUserId == -1) {
                        //说明用户没有登录
                        AlertDialog dialog = new AlertDialog.Builder(TestActivity.this)
                                .setCancelable(true)
                                .setTitle(R.string.tishi)
                                .setMessage(R.string.xinwen_pinglun_tishi)
                                .setPositiveButton(R.string.xinwen_qu_denglun, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(final DialogInterface dialog, int which) {
                                        editText.clearFocus();
                                        dialog.dismiss();
                                    }
                                }).create();
                        dialog.show();
                    } else if (mShow_xiangQing_write_pinglun_dialog == null) {
                        mShow_xiangQing_write_pinglun_dialog =
                                new Show_XiangQing_Write_Pinglun_Dialog(TestActivity.this, new Show_XiangQing_Write_Pinglun_Dialog.AddPinglun() {
                                    @Override
                                    public void add_pinglun(String content, final Show_XiangQing_Write_Pinglun_Dialog dialog, final EditText editText) {
                                        if (linearLayout != null) {
                                            refresh_Pinglun();//提交评论刷新界面
                                            new Handler().postDelayed(new Runnable() {
                                                @Override
                                                public void run() {
                                                    dialog.dismiss();
                                                    editText.setText("");
                                                }
                                            }, 300);
                                        }
                                    }
                                }, mHandler, articleId, extendUserId);
                    }
                    mShow_xiangQing_write_pinglun_dialog.showDialog(R.layout.custom_xiangqing_pinglun_dialog);
                    editText.clearFocus();
                }
            }
        });
        findViewById(R.id.pinglun_fenxiang).setOnClickListener(this);
        //更多评论按钮
        findViewById(R.id.xinwen_more_pinglun).setOnClickListener(this);
        mBGABadgeImageView = (BGABadgeImageView) findViewById(R.id.pinglun_imageview);
//        setPinlun_num("2333");

        findViewById(R.id.pinglun_imageview).setOnClickListener(this);
        title_textview = (TextView) findViewById(R.id.xinwen_title);
        source_textview = (TextView) findViewById(R.id.xinwen_source);
        publish_time_textview = (TextView) findViewById(R.id.xinwen_time);

        //计算LinerLayout的总高度
        mScrollView = (ScrollView) findViewById(R.id.xinwen_scrollview);
        mScrollView.setOnClickListener(this);
        mLinearLayout = (LinearLayout) findViewById(R.id.xinwen_content);
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                height = mLinearLayout.getMeasuredHeight();
//                Log.e("kkkboy", height + "---------------->");
//            }
//        }, 500);
        mFrameLayout = (FrameLayout) findViewById(R.id.xinwen_framelayout);
        mFrameLayout02 = (FrameLayout) findViewById(R.id.xinwen_framelayout02);
        //初始化标题等等
        initTitle();
        //初始化webview
        initwebView();

        ActivityStack.getInstance().addActivity(this);
    }

//    //此处加载toolbar的menu
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }
//
//    //此处响应menu的点击事件
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        return super.onOptionsItemSelected(item);
//    }

    //设置评论个数
    private void setPinlun_num(String num) {
        mBGABadgeImageView.showTextBadge(num);
    }

    //初始化标题等等
    private void initTitle() {
        Bundle bundle = getIntent().getBundleExtra("bundle");
        //标题
        article_title = bundle.getString("title");
        //来源
        String source = bundle.getString("source");
        //时间
        String publish_time = bundle.getString("time");
        //文章内容
        content = bundle.getString("content");
        //图片
        pictureUrl = bundle.getString("pictureUrl");
        //文章id
        articleId = bundle.getInt("articleId");
        //用户id
        extendUserId = bundle.getInt("userId");
        //分享链接
        article_url = Constant.SHARE_URL + "?extendUserId=" + extendUserId + "&articleId=" + articleId;
        title_textview.setText(article_title);
        source_textview.setText(source);
        publish_time_textview.setText(publish_time);
    }

    //初始化webView X5内核的
    private void initwebView() {
        mWebView = (com.tencent.smtt.sdk.WebView) findViewById(R.id.test_webView);
        WebSettings webSettings = mWebView.getSettings();
        //如果访问的页面中要与Javascript交互，则webview必须设置支持Javascript
        webSettings.setJavaScriptEnabled(true);

        //加入JavaScript的回调
        mWebView.addJavascriptInterface(this, "MyApp");

        //支持插件
        webSettings.setPluginsEnabled(true);
        //设置自适应屏幕，两者合用
        webSettings.setUseWideViewPort(true); //将图片调整到适合webview的大小
        webSettings.setLoadWithOverviewMode(true); // 缩放至屏幕的大小
        //缩放操作
        webSettings.setSupportZoom(false); //支持缩放，默认为true。是下面那个的前提。
        webSettings.setBuiltInZoomControls(false); //设置内置的缩放控件。若为false，则该WebView不可缩放
        webSettings.setDisplayZoomControls(false); //隐藏原生的缩放控件

        //这是网上解决webview嵌套在scrollview时，出现大片空白的方法，要配合上面的禁止缩放，牺牲了webview的缩放
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        mWebView.setVerticalScrollBarEnabled(false);
        mWebView.setVerticalScrollbarOverlay(false);
        mWebView.setHorizontalScrollBarEnabled(false);
        mWebView.setHorizontalScrollbarOverlay(false);

        //适配密度
        int screenDensity = getResources().getDisplayMetrics().densityDpi;
        WebSettings.ZoomDensity zoomDensity = WebSettings.ZoomDensity.MEDIUM;
        switch (screenDensity) {
            case DisplayMetrics.DENSITY_LOW:
                zoomDensity = WebSettings.ZoomDensity.CLOSE;
                break;
            case DisplayMetrics.DENSITY_MEDIUM:
                zoomDensity = WebSettings.ZoomDensity.MEDIUM;
                break;
            case DisplayMetrics.DENSITY_HIGH:
                zoomDensity = WebSettings.ZoomDensity.FAR;
                break;
        }
        webSettings.setDefaultZoom(zoomDensity);

        webSettings.setDefaultTextEncodingName("utf-8");//设置编码格式

        mWebView.setWebViewClient(new WebViewClient() {
            //使得打开网页时不调用系统浏览器， 而是在本WebView中显示
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            //开始加载时回调
            @Override
            public void onPageStarted(WebView webView, String s, Bitmap bitmap) {
                //在加载前清楚缓存
                mWebView.clearCache(true);
                mWebView.clearHistory();

                //提交给后台，通知该新闻已被查看一次
                final String url = Constant.ARTICLE_ADD;
                final String post = "articleId=" + articleId;
                //因为不需要用返回的结果，所以直接开启子线程运行
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Json2NewsUtil.post2Service(url, post);
                    }
                }).start();

                mFrameLayout.setVisibility(View.VISIBLE);//开始加载就显示一个全屏的过度图片

                if (!NetUtil.checkNet(TestActivity.this)) {
                    //没有网络的情况下
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            mFrameLayout.setVisibility(View.GONE);
                            mScrollView.setVisibility(View.GONE);
                            mFrameLayout02.setVisibility(View.VISIBLE);
                        }
                    }, 1000);
                } else {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            mFrameLayout.setVisibility(View.GONE);
                        }
                    }, 1000);
                }
            }

            //加载完成时回调
            @Override
            public void onPageFinished(WebView webView, String s) {
//                webView.loadUrl("javascript:MyApp.resize(document.body.getBoundingClientRect().height)");
                //初始化评论相关内容
                refresh_Pinglun();

            }

            @Override
            public void onReceivedError(WebView webView, int i, String s, String s1) {
                super.onReceivedError(webView, i, s, s1);
            }
        });
        //可以通过该方法获取网页加载的进度
//        mWebView.setWebChromeClient(new WebChromeClient() {
//            //网页加载进度改变时回调
//            @Override
//            public void onProgressChanged(WebView webView, int i) {
//                super.onProgressChanged(webView, i);
//            }
//
//            //获取title的时候回调
//            @Override
//            public void onReceivedTitle(WebView webView, String s) {
//                super.onReceivedTitle(webView, s);
//            }
//        });
//        Log.e("kkkboy", "---------->" + content);
        String style = "<link rel=\"stylesheet\" type=\"text/css\" href=\"style_one.css\" />";
        if (content == null || !content.startsWith("<") || !content.endsWith(">")) {

            content = "对不起，内容加载出错";
        }
        mWebView.loadDataWithBaseURL("file:///android_asset/", "<html>" + "<head>" + style + "</head>" + "<body>" + content + "</body></html>", "text/html", "utf-8", null);

    }

    private void refresh_Pinglun() {

        FutureTask<List<PinglunItemBean>> futureTask = new FutureTask<List<PinglunItemBean>>(new Callable<List<PinglunItemBean>>() {
            @Override
            public List<PinglunItemBean> call() throws Exception {
                String url = Constant.PING_LUN_REFRESH + "?articleId=" + articleId;
                return Json2NewsUtil.getPinglunItem(TestActivity.this, url);
            }
        });
        new Thread(futureTask).start();
        try {
            mPinglunItemBeanList = futureTask.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        int size = mPinglunItemBeanList.size();
        if (size > 0) {
            no_pinglun.setVisibility(View.GONE);
            pinglun_num = mPinglunItemBeanList.get(0).getSeveral();
            setPinlun_num(pinglun_num + "");
        } else {
            no_pinglun.setVisibility(View.VISIBLE);
        }

        int i = 0;
        for (PinglunItemBean pinglunItemBean : mPinglunItemBeanList) {
            i++;
            add_reply(i, pinglunItemBean.getReplyname(), pinglunItemBean.getHourAgo(), pinglunItemBean.getContent());
            if (i == 3 || i == size) {
                return;
            }
        }
    }

    private void add_reply(int i, String username, String hourago, String content) {
        switch (i) {
            case 1:
                linearLayout.removeAllViews();//先移除所有评论
                textView_username01.setText(username);
                textView_hourago01.setText(hourago);
                textView_content01.setText(content);
                linearLayout.addView(reply_view01);
                break;
            case 2:
                textView_username02.setText(username);
                textView_hourago02.setText(hourago);
                textView_content02.setText(content);
                linearLayout.addView(reply_view02);
                break;
            case 3:
                textView_username03.setText(username);
                textView_hourago03.setText(hourago);
                textView_content03.setText(content);
                linearLayout.addView(reply_view03);
                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.toolbar_up:
//                finish();
                if (mWebView.canGoBack()) {
                    mWebView.goBack();
                } else {
                    finish();
                }
                break;
            case R.id.pinglun_fenxiang:
                if (extendUserId == -1) {
                    //说明用户没有登录
                    AlertDialog dialog = new AlertDialog.Builder(TestActivity.this)
                            .setCancelable(true)
                            .setTitle(R.string.tishi)
                            .setMessage(R.string.xinwen_fenxiang_tishi)
                            .setPositiveButton(R.string.xinwen_qu_denglun, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(final DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            }).create();
                    dialog.show();
                }
                if (mShow_fenXiang_dialog == null) {
                    mShow_fenXiang_dialog = new Show_FenXiang_Dialog(TestActivity.this, article_title, pictureUrl, article_url);
                }
                mShow_fenXiang_dialog.showDialog(R.layout.custom_xiangqing_fenxiang_dialog);
                break;
            case R.id.pinglun_imageview:
                height = mLinearLayout.getMeasuredHeight();
                mScrollView.smoothScrollTo(0, height);
                break;
            case R.id.xinwen_more_pinglun:
                if (mPinglunItemBeanList.size() > 3) {
                    Intent intent = new Intent(TestActivity.this, AllPinglunActivity.class);
                    intent.putExtra("articleId", articleId);
                    startActivity(intent);
                } else {
                    Toast.makeText(TestActivity.this, "没有更多评论", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        if ((keyCode == KEYCODE_BACK) && mWebView.canGoBack()) {
//            mWebView.goBack();
//            return true;
//        }
//        return super.onKeyDown(keyCode, event);
//    }


    @Override
    protected void onDestroy() {
        //很重要，清楚webview缓存
        if (mWebView != null) {
            mWebView.loadDataWithBaseURL(null, "", "text/html", "utf-8", null);
            mWebView.clearHistory();

            ((ViewGroup) mWebView.getParent()).removeView(mWebView);
            mWebView.destroy();
            mWebView = null;
        }
        super.onDestroy();
    }

//    @JavascriptInterface
//    public void resize(final float height) {
//        ViewGroup.LayoutParams layoutParams = mWebView.getLayoutParams();
//        TestActivity.this.runOnUiThread(new Runnable() {
//            @Override
//            public void run() {
//                mWebView.setLayoutParams(new LinearLayout.LayoutParams(getResources().getDisplayMetrics().widthPixels, (int) (height * getResources().getDisplayMetrics().density)));
//            }
//        });
//    }
}
