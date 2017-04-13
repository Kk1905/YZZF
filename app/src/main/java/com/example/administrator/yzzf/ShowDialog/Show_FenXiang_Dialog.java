package com.example.administrator.yzzf.ShowDialog;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Toast;

import com.example.administrator.yzzf.Activity.FengxiangActivity;
import com.example.administrator.yzzf.R;
import com.example.administrator.yzzf.Tencent.BaseIUIListener;
import com.example.administrator.yzzf.Tencent.TencentShareManager;
import com.example.administrator.yzzf.WeChat.WeChatShareManager;
import com.example.administrator.yzzf.WeiBo.WeiBoShareManager;
import com.tencent.connect.share.QQShare;
import com.tencent.connect.share.QzonePublish;
import com.tencent.tauth.Tencent;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/2/27 0027.
 */

public class Show_FenXiang_Dialog extends BaseShowDialog implements View.OnClickListener {
    private static final String SDCARD_ROOT = Environment.getExternalStorageDirectory().getAbsolutePath();//内存卡的绝对路径
    private WeChatShareManager mWeChatShareManager;
    private TencentShareManager mTencentShareManager;
    private WeiBoShareManager mWeiBoShareManager;
    private Tencent mTencent;
    private BaseIUIListener mBaseIUIListener;
    private Context mContext;

    //文章标题
    private String article_title = "";
    //图片
    private String pictureUrl = "";
    //跳转url，这个是重点，要带参数
    private String article_url = "";

    public Show_FenXiang_Dialog(Activity activity, String title, String pictureUrl, String article_url) {
        super(activity);
        mContext = activity;
        article_title = title;
        this.pictureUrl = pictureUrl;
        this.article_url = article_url;

        mWeiBoShareManager = WeiBoShareManager.getWeiBoShareManager(mActivity);
        mWeChatShareManager = WeChatShareManager.getInstance(mActivity);
        //获取Tencent实例，Tencent是QQ分享SDK中的一个重要类，开发者可通过Tencent类访问腾讯开放的OpenAPI。
        mTencentShareManager = TencentShareManager.getTencentShareManager(mActivity);
        mBaseIUIListener = mTencentShareManager.getBaseIUIListener();
        mTencent = mTencentShareManager.getTencent();
    }

    public void showDialog(int layoutId) {
        super.showDialog(layoutId, 1);
        mAlertDialog.findViewById(R.id.xiangqing_dialog_weixin_pengyouquan).setOnClickListener(this);
        mAlertDialog.findViewById(R.id.xiangqing_dialog_weixin).setOnClickListener(this);
        mAlertDialog.findViewById(R.id.xiangqing_dialog_qq).setOnClickListener(this);
        mAlertDialog.findViewById(R.id.xiangqing_dialog_qqkongjian).setOnClickListener(this);
        mAlertDialog.findViewById(R.id.xiangqing_dialog_weixin_pengyouquan).setOnClickListener(this);
        mAlertDialog.findViewById(R.id.xiangqing_dialog_weibo).setOnClickListener(this);
        mAlertDialog.findViewById(R.id.xiangqing_dialog_quxiao).setOnClickListener(this);
    }

    //监测手机是否装了微信
    private boolean isWeChatAvaliable() {
        try {
            mActivity.getPackageManager().getPackageInfo("com.tencent.mm", PackageManager.GET_ACTIVITIES);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(mContext, FengxiangActivity.class);
        intent.putExtra("title", article_title);
        intent.putExtra("pictureUrl", pictureUrl);
        intent.putExtra("targetUrl", article_url);
        switch (v.getId()) {
            case R.id.xiangqing_dialog_quxiao:
                dismiss();
                break;
            case R.id.xiangqing_dialog_weixin_pengyouquan:
                if (!isWeChatAvaliable()) {
                    Toast.makeText(mActivity, R.string.wxapp_not_installed, Toast.LENGTH_SHORT).show();
                }
                intent.putExtra("share", "朋友圈");
                mContext.startActivity(intent);
                dismiss();
                break;
            case R.id.xiangqing_dialog_weixin:
                if (!isWeChatAvaliable()) {
                    Toast.makeText(mActivity, R.string.wxapp_not_installed, Toast.LENGTH_SHORT).show();
                }
                intent.putExtra("share", "微信好友");
                mContext.startActivity(intent);
                dismiss();
                break;
            case R.id.xiangqing_dialog_qq:
                intent.putExtra("share", "QQ");
                mContext.startActivity(intent);
                dismiss();
                break;
            case R.id.xiangqing_dialog_qqkongjian:
                intent.putExtra("share", "QQ空间");
                mContext.startActivity(intent);
                dismiss();
                break;
            case R.id.xiangqing_dialog_weibo:
                intent.putExtra("share", "微博");
                mContext.startActivity(intent);
                dismiss();
//                //分享一个文本到微博试一下
//                Bundle weiBoBundle = new Bundle();
//                weiBoBundle.putString(WeiBoShareManager.TITLE, "微博分享");
//                weiBoBundle.putString(WeiBoShareManager.TEXT, "这是正文");
//                String image_path = SDCARD_ROOT + "/logo.png";
//                weiBoBundle.putString(WeiBoShareManager.IMAGE_PATH, image_path);
//                mWeiBoShareManager.sendMessage(weiBoBundle, true, true, false, false, false, false);
//
//                dismiss();
                break;
            case R.id.xiangqing_pinglun_dialog_quxiao:
                dismiss();
                break;
            case R.id.xiangqing_pinglun_dialog_fasong:

                break;
            case R.id.xiangqing_pinglun_dialog_content:

                break;

        }

    }
}
