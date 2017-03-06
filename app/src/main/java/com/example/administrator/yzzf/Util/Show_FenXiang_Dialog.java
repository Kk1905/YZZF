package com.example.administrator.yzzf.Util;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.administrator.yzzf.Activity.XiangQingActivity;
import com.example.administrator.yzzf.BaseShowDialog;
import com.example.administrator.yzzf.R;
import com.example.administrator.yzzf.Tencent.BaseIUIListener;
import com.example.administrator.yzzf.Tencent.TencentShareManager;
import com.example.administrator.yzzf.WeChat.WeChatShareManager;
import com.tencent.connect.share.QQShare;
import com.tencent.tauth.Tencent;

/**
 * Created by Administrator on 2017/2/27 0027.
 */

public class Show_FenXiang_Dialog extends BaseShowDialog implements View.OnClickListener {
    private WeChatShareManager mWeChatShareManager;
    private TencentShareManager mTencentShareManager;
    private Tencent mTencent;
    private BaseIUIListener mBaseIUIListener;

    public Show_FenXiang_Dialog(Activity activity) {
        super(activity);

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
        mAlertDialog.findViewById(R.id.xiangqing_dialog_weixin_pengyouquan).setOnClickListener(this);
        mAlertDialog.findViewById(R.id.xiangqing_dialog_weibo).setOnClickListener(this);
        mAlertDialog.findViewById(R.id.xiangqing_dialog_shuaxin).setOnClickListener(this);
        mAlertDialog.findViewById(R.id.xiangqing_dialog_fuzhilianjie).setOnClickListener(this);
        mAlertDialog.findViewById(R.id.xiangqing_dialog_jubao).setOnClickListener(this);
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

        switch (v.getId()) {
            case R.id.xiangqing_dialog_quxiao:
                dismiss();
                break;
            case R.id.xiangqing_dialog_weixin_pengyouquan:
//                if (!isWeChatAvaliable()) {
//                    Toast.makeText(mActivity, "请先安装微信", Toast.LENGTH_SHORT).show();
//                    break;
//                } else {
//                    WeChatShareManager.ShareContentVideo shareContentVideo = mWeChatShareManager.getShareContentVideo(
//                            "http://baidu.hz.letv.com/kan/agSlT?fr=v.baidu.com/");
//                    mWeChatShareManager.shareByWeChat(shareContentVideo, WeChatShareManager.WECHAT_SHARE_TYPE_FRIENDS);
//                    Toast.makeText(mActivity, "请先安装微信", Toast.LENGTH_SHORT).show();
//                    break;
//                }
                WeChatShareManager.ShareContentText shareContentText = mWeChatShareManager.getShareContentText("这是来自扬子社区头条的微信SDK测试信息");
                mWeChatShareManager.shareByWeChat(shareContentText, WeChatShareManager.WECHAT_SHARE_TYPE_FRIENDS);
                Toast.makeText(mActivity, "分享文字成功", Toast.LENGTH_SHORT).show();
                break;
            case R.id.xiangqing_dialog_weixin:
                if (isWeChatAvaliable()) {
                    Toast.makeText(mActivity, "请先安装微信", Toast.LENGTH_SHORT).show();
                }break;
            case R.id.xiangqing_dialog_qq:
                //分享到qq好友,模拟发送一次音乐
                Bundle qqBundle = new Bundle();
                //标题
                qqBundle.putString(TencentShareManager.TITLE, "666");
                //music_url
                qqBundle.putString(TencentShareManager.AUDIO_URL, "http://staff2.ustc.edu.cn/~wdw/softdown/index.asp/0042515_05.ANDY.mp3");
                //target_url
                qqBundle.putString(TencentShareManager.TARGET_URL, "http://staff2.ustc.edu.cn/~wdw/softdown/index.asp/0042515_05.ANDY.mp3");
                //概要
                qqBundle.putString(TencentShareManager.SUMMARY, "这么长写毛啊");
                qqBundle.putString(TencentShareManager.APP_NAME, "扬子智服头条");
                mTencentShareManager.shareByTencent(TencentShareManager.SHARE_TO_QQ,
                        QQShare.SHARE_TO_QQ_TYPE_AUDIO, qqBundle);
                break;
            case R.id.xiangqing_dialog_qqkongjian:
                break;
            case R.id.xiangqing_dialog_weibo:
                break;
            case R.id.xiangqing_dialog_shuaxin:
                break;
            case R.id.xiangqing_dialog_fuzhilianjie:
                break;
            case R.id.xiangqing_dialog_jubao:
                break;

            case R.id.xiangqing_pinglun_dialog_quxiao:
                dismiss();
                break;
            case R.id.xiangqing_pinglun_dialog_fasong:

                break;
            case R.id.xiangqing_pinglun_dialog_content:

                break;
            case R.id.xiangqing_pinglun_dialog_face:

                break;
        }

    }
}
