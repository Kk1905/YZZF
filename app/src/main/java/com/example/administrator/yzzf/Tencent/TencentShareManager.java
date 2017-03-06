package com.example.administrator.yzzf.Tencent;

import android.app.Activity;
import android.os.Bundle;
import com.tencent.connect.share.QQShare;
import com.tencent.tauth.Tencent;

import static com.tencent.connect.share.QQShare.SHARE_TO_QQ_TYPE_DEFAULT;


/**
 * Created by Administrator on 2017/3/6 0006.
 */

public class TencentShareManager {
    public static final int SHARE_TO_QQ = 0;//分享到qq
    public static final int SHARE_TO_QZONE = 1;//分享到QQ空间

    private static final String APP_ID = "1105945257";

    public Tencent mTencent;
    private Activity mActivity;
    private static final int SHARE_TO_QQ_TEXT = SHARE_TO_QQ_TYPE_DEFAULT;//qq图文分享
    private static final int SHARE_TO_QQ_IMAGE = QQShare.SHARE_TO_QQ_TYPE_IMAGE;//qq纯图片分享
    private static final int SHARE_TO_QQ_MUSIC = QQShare.SHARE_TO_QQ_TYPE_AUDIO;//qq音乐分享
    private static final int SHARE_TO_QQ_APP = QQShare.SHARE_TO_QQ_TYPE_APP;//qq应用分享
    private static TencentShareManager mTencentShareManager;
    private BaseIUIListener mBaseIUIListener;
    public static final String TITLE = "title"; //分享的标题
    public static final String SUMMARY = "summary";//分享的摘要
    public static final String TARGET_URL = "target_url";//好友点击分享的消息后跳转到的url
    public static final String IMAGE_URL = "image_url";//分享图片的url或者本地路径
    public static final String APP_NAME = "app_name";//手Q客户端顶部，替换“返回”按钮的文字，如果为空，用返回代替
    public static final String IMAGE_LOCAL_URL = "image_local_url";//以图片的形式分享时，图片的本地路径，为必选
    public static final String AUDIO_URL = "audio_url";//以音乐的形式分享时，音乐文件的远程链接，以url的形式传入，不支持本地音乐，必选
    /*
    分享额外选项，两种类型可选（默认是不隐藏分享到 QZone 按钮且不自
    动打开分享到 QZone 的对话框）：
    Tencent.SHARE_TO_QQ_FLAG_QZONE_AUTO_OPEN，分享时自动打
    开分享到 QZone 的对话框。
    Tencent.SHARE_TO_QQ_FLAG_QZONE_ITEM_HIDE，分享时隐藏分享
    到 QZone 按钮
    */
    private static final String EXT_INT = "ext_int";

    private TencentShareManager(Activity activity) {
        mActivity = activity;
        mBaseIUIListener = new BaseIUIListener();
    }

    public Tencent getTencent() {
        Tencent.createInstance(APP_ID, mActivity);
        return mTencent;
    }

    public BaseIUIListener getBaseIUIListener() {
        return mBaseIUIListener;
    }

    //单例，非线程安全，在UI线程中使用
    public static TencentShareManager getTencentShareManager(Activity activity) {
        if (mTencentShareManager == null) {
            mTencentShareManager = new TencentShareManager(activity);
        }
        return mTencentShareManager;
    }

    public void shareByTencent(int shareStyle, int shareType, Bundle qqBundle) {

        switch (shareStyle) {
            case SHARE_TO_QQ:
                shareToQQ(shareType, qqBundle);
                break;
            case SHARE_TO_QZONE:
                shareToQzone(shareType, qqBundle);
                break;
        }
    }

    private void shareToQQ(int shareType, Bundle qqBundle) {
        switch (shareType) {
            case SHARE_TO_QQ_TEXT:
                shareToQQ_Text(qqBundle);
                break;
            case SHARE_TO_QQ_IMAGE:
                shareToQQ_Image(qqBundle);
                break;
            case SHARE_TO_QQ_MUSIC:
                shareToQQ_Music(qqBundle);
                break;
            case SHARE_TO_QQ_APP:
                shareToQQ_APP(qqBundle);
                break;

        }
    }

    //图文分享
    private void shareToQQ_Text(Bundle qqBundle) {
        final Bundle bundle = new Bundle();
        //分享的类型，为默认类型,必选
        bundle.putInt(QQShare.SHARE_TO_QQ_KEY_TYPE, SHARE_TO_QQ_TYPE_DEFAULT);
        //分享的标题，必选
        bundle.putString(QQShare.SHARE_TO_QQ_TITLE, qqBundle.getString(TITLE));
        //分享的摘要，可选
        bundle.putString(QQShare.SHARE_TO_QQ_SUMMARY, qqBundle.getString(SUMMARY, null));
        //好友点击分享的消息后跳转到的url，必选
        bundle.putString(QQShare.SHARE_TO_QQ_TARGET_URL, qqBundle.getString(TARGET_URL));
        //分享的图片url或者本地路径，可选
        bundle.putString(QQShare.SHARE_TO_QQ_IMAGE_URL, qqBundle.getString(IMAGE_URL, null));
        //分享的app_name，可选，默认文字为“返回”
        bundle.putString(QQShare.SHARE_TO_QQ_APP_NAME, qqBundle.getString(APP_NAME, null));

        bundle.putInt(QQShare.SHARE_TO_QQ_EXT_INT, qqBundle.getInt(EXT_INT, QQShare.SHARE_TO_QQ_FLAG_QZONE_AUTO_OPEN));
        //分享到qq好友
        mTencent.shareToQQ(mActivity, bundle, mBaseIUIListener);
    }

    //纯图片分享
    private void shareToQQ_Image(Bundle qqBundle) {
        final Bundle bundle = new Bundle();
        bundle.putInt(QQShare.SHARE_TO_QQ_KEY_TYPE, QQShare.SHARE_TO_QQ_TYPE_IMAGE);
        //分享图片本地地址，必选
        bundle.putString(QQShare.SHARE_TO_QQ_IMAGE_LOCAL_URL, qqBundle.getString(IMAGE_LOCAL_URL));
        //分享appName，可选
        bundle.putString(QQShare.SHARE_TO_QQ_APP_NAME, qqBundle.getString(APP_NAME, null));
        bundle.putInt(QQShare.SHARE_TO_QQ_EXT_INT, qqBundle.getInt(EXT_INT, QQShare.SHARE_TO_QQ_FLAG_QZONE_AUTO_OPEN));

        mTencent.shareToQQ(mActivity, bundle, mBaseIUIListener);
    }

    //音乐分享
    private void shareToQQ_Music(Bundle qqBundle) {
        final Bundle bundle = new Bundle();
        bundle.putInt(QQShare.SHARE_TO_QQ_KEY_TYPE, QQShare.SHARE_TO_QQ_TYPE_AUDIO);
        //音乐的远程链接，以url的形式传入，不支持本地音乐，必选
        bundle.putString(QQShare.SHARE_TO_QQ_AUDIO_URL, qqBundle.getString(AUDIO_URL));
        //目标url，必选
        bundle.putString(QQShare.SHARE_TO_QQ_TARGET_URL, qqBundle.getString(TARGET_URL));
        //标题，必选
        bundle.putString(QQShare.SHARE_TO_QQ_TITLE, qqBundle.getString(TITLE));
        //可选
        bundle.putString(QQShare.SHARE_TO_QQ_IMAGE_URL, qqBundle.getString(IMAGE_URL, null));
        //可选
        bundle.putString(QQShare.SHARE_TO_QQ_SUMMARY, qqBundle.getString(SUMMARY, null));
        //可选
        bundle.putInt(QQShare.SHARE_TO_QQ_EXT_INT, qqBundle.getInt(EXT_INT, QQShare.SHARE_TO_QQ_FLAG_QZONE_AUTO_OPEN));
        //可选
        bundle.putString(QQShare.SHARE_TO_QQ_APP_NAME, qqBundle.getString(APP_NAME, null));
        mTencent.shareToQQ(mActivity, bundle, mBaseIUIListener);
    }

    //APP分享
    private void shareToQQ_APP(Bundle qqBundle) {
        final Bundle bundle = new Bundle();
        bundle.putInt(QQShare.SHARE_TO_QQ_KEY_TYPE, QQShare.SHARE_TO_QQ_TYPE_APP);
        //分享标题，必选
        bundle.putString(QQShare.SHARE_TO_QQ_TITLE, qqBundle.getString(TITLE));
        //音乐的远程链接，以url的形式传入，不支持本地音乐，可选
        bundle.putString(QQShare.SHARE_TO_QQ_AUDIO_URL, qqBundle.getString(AUDIO_URL));
        //目标url，可选
        bundle.putString(QQShare.SHARE_TO_QQ_TARGET_URL, qqBundle.getString(TARGET_URL));
        //可选
        bundle.putString(QQShare.SHARE_TO_QQ_IMAGE_URL, qqBundle.getString(IMAGE_URL, null));
        //可选
        bundle.putString(QQShare.SHARE_TO_QQ_SUMMARY, qqBundle.getString(SUMMARY, null));
        //可选
        bundle.putInt(QQShare.SHARE_TO_QQ_EXT_INT, qqBundle.getInt(EXT_INT, QQShare.SHARE_TO_QQ_FLAG_QZONE_AUTO_OPEN));
        //可选
        bundle.putString(QQShare.SHARE_TO_QQ_APP_NAME, qqBundle.getString(APP_NAME, null));

        mTencent.shareToQQ(mActivity,bundle,mBaseIUIListener);
    }

    private void shareToQzone(int shareType, Bundle qqBundle) {

    }

}
