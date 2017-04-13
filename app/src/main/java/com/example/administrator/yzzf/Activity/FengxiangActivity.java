package com.example.administrator.yzzf.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.administrator.yzzf.Adapter.GuanggaoItemAdapter;
import com.example.administrator.yzzf.Bean.GuanggaoItemBean;
import com.example.administrator.yzzf.R;
import com.example.administrator.yzzf.Tencent.BaseIUIListener;
import com.example.administrator.yzzf.Tencent.TencentShareManager;
import com.example.administrator.yzzf.Util.ActivityStack;
import com.example.administrator.yzzf.Util.Constant;
import com.example.administrator.yzzf.Util.Json2NewsUtil;
import com.example.administrator.yzzf.Util.MyApplication;
import com.example.administrator.yzzf.WeChat.WeChatShareManager;
import com.example.administrator.yzzf.WeiBo.WeiBoShareManager;
import com.tencent.connect.share.QQShare;
import com.tencent.connect.share.QzoneShare;
import com.tencent.tauth.Tencent;

import java.util.ArrayList;
import java.util.List;

import static com.example.administrator.yzzf.Tencent.TencentShareManager.APP_NAME;
import static com.example.administrator.yzzf.Tencent.TencentShareManager.IMAGE_URL;
import static com.example.administrator.yzzf.Tencent.TencentShareManager.IMAGE_URL_LIST;
import static com.example.administrator.yzzf.Tencent.TencentShareManager.SUMMARY;
import static com.example.administrator.yzzf.Tencent.TencentShareManager.TARGET_URL;
import static com.tencent.connect.share.QQShare.SHARE_TO_QQ_TYPE_DEFAULT;

/**
 * Created by Administrator on 2017/3/30 0030.
 */

public class FengxiangActivity extends AppCompatActivity implements View.OnClickListener {
    private RecyclerView mRecyclerView;
    private GuanggaoItemAdapter mAdapter;
    private List<GuanggaoItemBean> mDatas = new ArrayList<>();
    private int share_type;
    private static final int PENG_YOU_QUAN = 0;
    private static final int WX_HAO_YOU = 1;
    private static final int QQ_HAO_YOU = 2;
    private static final int QQ_ZONE = 3;
    private static final int WEI_BO = 4;

    //广告id
    private int personalsignid;
    //分享时的标题
    private String title;
    //分享时缩略图的路径
    private String pictureUrl;
    //分享时概要
    private String summary;
    //分享的链接
    private String targetUrl;

    //有关分享的对象
    private static final String SDCARD_ROOT = Environment.getExternalStorageDirectory().getAbsolutePath();//内存卡的绝对路径
    private WeChatShareManager mWeChatShareManager;
    private TencentShareManager mTencentShareManager;
    private WeiBoShareManager mWeiBoShareManager;
    private Tencent mTencent;
    private BaseIUIListener mBaseIUIListener;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fenxiang02);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_login);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowTitleEnabled(false);
        }
        TextView title_textview = (TextView) findViewById(R.id.login_toolbar_title);
        title_textview.setText("分享");
        findViewById(R.id.toolbar_up).setOnClickListener(this);
        mRecyclerView = (RecyclerView) findViewById(R.id.activity_guanggao_recyclerview);

        String url = Constant.CHOCE_ADV;
        try {
            mDatas = Json2NewsUtil.getGuanggaoItem(FengxiangActivity.this, url);
        } catch (Exception e) {
            e.printStackTrace();
        }
        mWeChatShareManager = WeChatShareManager.getInstance(FengxiangActivity.this);
        mWeiBoShareManager = WeiBoShareManager.getWeiBoShareManager(FengxiangActivity.this);
        mTencentShareManager = TencentShareManager.getTencentShareManager(FengxiangActivity.this);
        //QQ分享的回调接口
        mBaseIUIListener = mTencentShareManager.getBaseIUIListener();
        //获取Tencent实例，Tencent是QQ分享SDK中的一个重要类，开发者可通过Tencent类访问腾讯开放的OpenAPI。
        mTencent = mTencentShareManager.getTencent();

        title = getIntent().getStringExtra("title");
        pictureUrl = getIntent().getStringExtra("pictureUrl");
        targetUrl = getIntent().getStringExtra("targetUrl");

        //判断分享方式
        String shareType = getIntent().getStringExtra("share");
        switch (shareType) {
            case "朋友圈":
                share_type = PENG_YOU_QUAN;
                break;
            case "微信好友":
                share_type = WX_HAO_YOU;
                break;
            case "QQ":
                share_type = QQ_HAO_YOU;
                break;
            case "QQ空间":
                share_type = QQ_ZONE;
                break;
            case "微博":
                share_type = WEI_BO;
                break;
        }

        initRecyclerView();

        ActivityStack.getInstance().addActivity(this);
    }

    private void initRecyclerView() {
        mAdapter = new GuanggaoItemAdapter(FengxiangActivity.this, mDatas, new GuanggaoItemAdapter.GuanggaoSelected() {
            @Override
            public void onGuanggaoSelected(View v, String jifen, String yifenxiang_num, int id) {
                personalsignid = id;//广告id
                AlertDialog alertDialog = new AlertDialog.Builder(FengxiangActivity.this)
                        .setCancelable(true)
                        .setMessage("您选择的广告价值  " + jifen + "  积分值，已有  " + yifenxiang_num + "  人分享过此广告，您确定要分享此广告么？")
                        .setNegativeButton(R.string.quxiao, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .setPositiveButton(R.string.queding, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //选择好广告后，就分享出去
                                summary = "社区头条，分享的不只是新闻";//内容介绍
                                //组装微信分享的内容
                                String lastTargetUrl = targetUrl + "&personalsignid=" + personalsignid;//链接地址
                                WeChatShareManager.ShareContentWebPage shareContentWebPage = mWeChatShareManager.getShareContentWebPage(summary, title, pictureUrl, lastTargetUrl);

                                switch (share_type) {
                                    case PENG_YOU_QUAN:
                                        //朋友圈
                                        mWeChatShareManager.shareByWeChat(shareContentWebPage, WeChatShareManager.WECHAT_SHARE_TYPE_FRIENDS);
                                        dialog.dismiss();
                                        break;
                                    case WX_HAO_YOU:
                                        Log.e("kkkboy", "最后分享url----------------->" + lastTargetUrl);
                                        //微信好友
                                        mWeChatShareManager.shareByWeChat(shareContentWebPage, WeChatShareManager.WECHAT_SHARE_TYPE_TALK);
                                        dialog.dismiss();
                                        break;
                                    case QQ_HAO_YOU:
                                        //QQ好友，shareToQQ_Text，使用图文分享的方式
                                        Bundle bundle = new Bundle();

                                        //分享的标题，必选
                                        if (title == null) {
                                            title = "";
                                        }
                                        bundle.putString(TencentShareManager.TITLE, title);
                                        //分享的摘要，可选
                                        bundle.putString(TencentShareManager.SUMMARY, summary);
                                        //好友点击分享的消息后跳转到的url，必选
                                        bundle.putString(TencentShareManager.TARGET_URL, lastTargetUrl);
                                        //分享的图片url或者本地路径，可选
                                        bundle.putString(TencentShareManager.IMAGE_URL, pictureUrl);
                                        //分享的app_name，可选，默认文字为“返回”
                                        bundle.putString(TencentShareManager.APP_NAME, "社区头条");
                                        mTencentShareManager.shareByTencent(TencentShareManager.SHARE_TO_QQ, QQShare.SHARE_TO_QQ_TYPE_DEFAULT, bundle);
                                        dialog.dismiss();
                                        break;
                                    case QQ_ZONE:
                                        //QQ空间
                                        Bundle bundle1 = new Bundle();
                                        if (title == null) {
                                            title = "";
                                        }
                                        bundle1.putString(TencentShareManager.TITLE, title);//必填
                                        bundle1.putString(TencentShareManager.TARGET_URL, lastTargetUrl);//必填
                                        bundle1.putString(TencentShareManager.SUMMARY, summary);//选填

                                        //多个图片的数组
                                        ArrayList<String> image_urls = new ArrayList<String>();
                                        image_urls.add(pictureUrl);
                                        bundle1.putStringArrayList(TencentShareManager.IMAGE_URL_LIST, image_urls);//以ArrayList形式传入，选填
                                        mTencentShareManager.shareByTencent(TencentShareManager.SHARE_TO_QZONE, QzoneShare.SHARE_TO_QZONE_TYPE_IMAGE_TEXT, bundle1);
                                        dialog.dismiss();
                                        break;
                                    case WEI_BO:
                                        Bundle weiBoBundle = new Bundle();
                                        //text对象
                                        weiBoBundle.putString(WeiBoShareManager.TEXT, title + "\n" + summary + lastTargetUrl);
                                        //下面全部包含在webpage对象中
//                                        weiBoBundle.putString(WeiBoShareManager.TITLE, title);
//                                        weiBoBundle.putString(WeiBoShareManager.DESCRIPTION, summary);//概要
                                        weiBoBundle.putString(WeiBoShareManager.IMAGE_URL, pictureUrl);//缩略图路径
//                                        weiBoBundle.putString(WeiBoShareManager.ACTION_URL, lastTargetUrl);//链接地址
//                                        weiBoBundle.putString(WeiBoShareManager.DEFAULT_TEXT, summary);
                                        mWeiBoShareManager.sendMessage(weiBoBundle, true, true, false, false, false, false);
                                        break;
                                }

                            }
                        })
                        .create();
                alertDialog.show();
            }
        });
        //设置布局管理
        mRecyclerView.setLayoutManager(new GridLayoutManager(FengxiangActivity.this, 2));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
//        mRecyclerView.addItemDecoration(null);//不添加任何分隔
        mRecyclerView.setAdapter(mAdapter);
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
