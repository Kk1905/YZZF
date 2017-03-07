package com.example.administrator.yzzf.Util;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Toast;

import com.example.administrator.yzzf.Activity.XiangQingActivity;
import com.example.administrator.yzzf.BaseShowDialog;
import com.example.administrator.yzzf.R;
import com.example.administrator.yzzf.Tencent.BaseIUIListener;
import com.example.administrator.yzzf.Tencent.TencentShareManager;
import com.example.administrator.yzzf.WeChat.WeChatShareManager;
import com.tencent.connect.share.QQShare;
import com.tencent.connect.share.QzonePublish;
import com.tencent.connect.share.QzoneShare;
import com.tencent.tauth.Tencent;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static com.example.administrator.yzzf.R.string.qq;

/**
 * Created by Administrator on 2017/2/27 0027.
 */

public class Show_FenXiang_Dialog extends BaseShowDialog implements View.OnClickListener {
    private static final String SDCARD_ROOT = Environment.getExternalStorageDirectory().getAbsolutePath();//内存卡的绝对路径
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
        mAlertDialog.findViewById(R.id.xiangqing_dialog_qqkongjian).setOnClickListener(this);
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
                }
                break;
            case R.id.xiangqing_dialog_qq:
                //分享到qq好友,模拟发送一次音乐
                Bundle qqBundle = new Bundle();
                //标题
                qqBundle.putString(TencentShareManager.TITLE, "Title:666");
                //music_url
                qqBundle.putString(TencentShareManager.AUDIO_URL, "http://staff2.ustc.edu.cn/~wdw/softdown/index.asp/0042515_05.ANDY.mp3");
                //target_url
                qqBundle.putString(TencentShareManager.TARGET_URL, "http://staff2.ustc.edu.cn/~wdw/softdown/index.asp/0042515_05.ANDY.mp3");
                //概要
                qqBundle.putString(TencentShareManager.SUMMARY, "summary:这么长写毛啊");
                qqBundle.putString(TencentShareManager.APP_NAME, "扬子智服头条");
                mTencentShareManager.shareByTencent(TencentShareManager.SHARE_TO_QQ,
                        QQShare.SHARE_TO_QQ_TYPE_AUDIO, qqBundle);
                break;
            case R.id.xiangqing_dialog_qqkongjian:
                //发表一个说说到空间测试一下
                Bundle qzoneBundle = new Bundle();
                qzoneBundle.putString(TencentShareManager.TITLE, "扬子智服社区头条测试");
                qzoneBundle.putString(TencentShareManager.SUMMARY, "扬子智服社区头条测试,请别赞我，我会骄傲的");
                String url01 = SDCARD_ROOT + "/logo.png";
                String url02 = SDCARD_ROOT + "/kk.mp4";
//                qzoneBundle.putString(TencentShareManager.VIDEO_PATH, url02);
                ArrayList<String> imageUrls = new ArrayList<>();
                imageUrls.add(url01);
                qzoneBundle.putStringArrayList(TencentShareManager.IMAGE_URL_LIST, imageUrls);
//                qzoneBundle.putString(TencentShareManager.TARGET_URL, "http://blog.csdn.net/pdskyzcc1/article/details/51881693");
                mTencentShareManager.shareByTencent(TencentShareManager.SHARE_TO_QZONE,
                        QzonePublish.PUBLISH_TO_QZONE_TYPE_PUBLISHMOOD, qzoneBundle);
                Toast.makeText(mActivity, "qqkongjian--------->", Toast.LENGTH_SHORT).show();

                break;
            case R.id.xiangqing_dialog_weibo:
                //发表一个说说到空间测试一下
//                Bundle qzoneBundle = new Bundle();
//                qzoneBundle.putString(TencentShareManager.TITLE, "扬子智服社区头条测试");
//                qzoneBundle.putString(TencentShareManager.SUMMARY, "扬子智服社区头条测试,请别赞我，我会骄傲的");
//                String url01 = SDCARD_ROOT + "/logo.png";
//                String url02 = SDCARD_ROOT + "/logo01.png";
//                List<String> imageUrls = new ArrayList<>();
//                imageUrls.add(url01);
//                imageUrls.add(url02);
//                qzoneBundle.putStringArrayList(TencentShareManager.IMAGE_URL_LIST, (ArrayList<String>) imageUrls);
//                mTencentShareManager.shareByTencent(TencentShareManager.SHARE_TO_QZONE,
//                        QzonePublish.PUBLISH_TO_QZONE_TYPE_PUBLISHMOOD, qzoneBundle);
                Bundle qzoneBundle01 = new Bundle();
                qzoneBundle01.putString(TencentShareManager.TITLE, "QQ图文分享");
                qzoneBundle01.putString(TencentShareManager.TARGET_URL, "http://m.haiwainet.cn/ttc/3542657/2017/0306/content_30775194_1.html?tt_group_id=6394321712565174529");
                qzoneBundle01.putString(TencentShareManager.SUMMARY, "概要");
                ArrayList<String> imageUrlss = new ArrayList<>();
                String url001 = SDCARD_ROOT + "/logo.png";
                String url002 = SDCARD_ROOT + "/logo01.png";
                imageUrlss.add(url001);
                imageUrlss.add(url002);
                qzoneBundle01.putStringArrayList(TencentShareManager.IMAGE_URL_LIST, imageUrlss);
                mTencentShareManager.shareByTencent(TencentShareManager.SHARE_TO_QZONE,
                        QzoneShare.SHARE_TO_QZONE_TYPE_IMAGE_TEXT, qzoneBundle01);
                Toast.makeText(mActivity, "qqkongjian--------->", Toast.LENGTH_SHORT).show();
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
