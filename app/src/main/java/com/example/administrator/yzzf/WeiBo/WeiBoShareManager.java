package com.example.administrator.yzzf.WeiBo;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.Toast;

import com.example.administrator.yzzf.R;
import com.sina.weibo.sdk.api.ImageObject;
import com.sina.weibo.sdk.api.MusicObject;
import com.sina.weibo.sdk.api.TextObject;
import com.sina.weibo.sdk.api.VideoObject;
import com.sina.weibo.sdk.api.VoiceObject;
import com.sina.weibo.sdk.api.WebpageObject;
import com.sina.weibo.sdk.api.WeiboMessage;
import com.sina.weibo.sdk.api.WeiboMultiMessage;
import com.sina.weibo.sdk.api.share.IWeiboShareAPI;
import com.sina.weibo.sdk.api.share.SendMessageToWeiboRequest;
import com.sina.weibo.sdk.api.share.SendMultiMessageToWeiboRequest;
import com.sina.weibo.sdk.api.share.WeiboShareSDK;
import com.sina.weibo.sdk.utils.Utility;


/**
 * Created by Administrator on 2017/3/7 0007.
 */

public class WeiBoShareManager {
    private static final String APP_KEY = "1367983379";
    private Context mContext;
    private IWeiboShareAPI mIWeiboShareAPI;
    private static WeiBoShareManager mWeiBoShareManager;

    public static final String TEXT = "text";
    public static final String IMAGE_PATH = "image_path";

    public static final String TITLE = "title";
    public static final String DESCRIPTION = "description";
    public static final String ACTION_URL = "action_url";
    public static final String DATA_URL = "data_url";
    public static final String DATA_HD_URL = "data_hd_url";
    public static final String DEFAULT_TEXT = "default_text";


    private WeiBoShareManager(Context context) {
        mContext = context;
        mIWeiboShareAPI = WeiboShareSDK.createWeiboAPI(mContext, APP_KEY);
        if (mIWeiboShareAPI.isWeiboAppInstalled()) {
            mIWeiboShareAPI.registerApp();
        } else {
            Toast.makeText(mContext, R.string.weiboapp_not_installed, Toast.LENGTH_SHORT).show();
        }
    }

    public IWeiboShareAPI getIWeiboShareAPI() {
        mIWeiboShareAPI.registerApp();
        return mIWeiboShareAPI;
    }

    //非线程安全，请在UI线程下使用
    public static WeiBoShareManager getWeiBoShareManager(Context context) {
        if (mWeiBoShareManager == null) {
            mWeiBoShareManager = new WeiBoShareManager(context);
        }
        return mWeiBoShareManager;
    }

    private TextObject getTextObj(Bundle bundle) {
        TextObject textObject = new TextObject();
        textObject.text = bundle.getString(TEXT, null);//文本
        return textObject;
    }

    private ImageObject getImageObj(Bundle bundle) {
        ImageObject imageObject = new ImageObject();
        Bitmap bitmap = BitmapFactory.decodeFile(bundle.getString(IMAGE_PATH));//通过本地图片来产生位图对象
        imageObject.setImageObject(bitmap);
        return imageObject;
    }

    /**
     * 创建多媒体（网页）消息对象。
     *
     * @return 多媒体（网页）消息对象。
     */
    private WebpageObject getWebpageObj(Bundle bundle) {
        WebpageObject mediaObject = new WebpageObject();
        mediaObject.identify = Utility.generateGUID();
        mediaObject.title = bundle.getString(TITLE);
        mediaObject.description = bundle.getString(DESCRIPTION);

        // 设置 Bitmap 类型的图片到网页对象里
        mediaObject.setThumbImage(BitmapFactory.decodeResource(mContext.getResources(), R.drawable.yangzi));
        mediaObject.actionUrl = bundle.getString(ACTION_URL);
        mediaObject.defaultText = bundle.getString(DEFAULT_TEXT);
        return mediaObject;
    }

    /**
     * 创建多媒体（音乐）消息对象。
     *
     * @return 多媒体（音乐）消息对象。
     */
    private MusicObject getMusicObj(Bundle bundle) {
        // 创建媒体消息
        MusicObject musicObject = new MusicObject();
        musicObject.identify = Utility.generateGUID();
        musicObject.title = bundle.getString(TITLE);
        musicObject.description = bundle.getString(DESCRIPTION);

        // 设置 Bitmap 类型的图片到音乐对象里
        musicObject.setThumbImage(BitmapFactory.decodeResource(mContext.getResources(), R.drawable.yangzi));
        musicObject.actionUrl = bundle.getString(ACTION_URL);
        musicObject.dataUrl = bundle.getString(DATA_URL);
        musicObject.dataHdUrl = bundle.getString(DATA_HD_URL);
        musicObject.duration = 10;
        musicObject.defaultText = "Music 默认文案";
        return musicObject;
    }

    /**
     * 创建多媒体（视频）消息对象。
     *
     * @return 多媒体（视频）消息对象。
     */
    private VideoObject getVideoObj(Bundle bundle) {
        // 创建媒体消息
        VideoObject videoObject = new VideoObject();
        videoObject.identify = Utility.generateGUID();
        videoObject.title = bundle.getString(TITLE);
        videoObject.description = bundle.getString(DESCRIPTION);

        // 设置 Bitmap 类型的图片到视频对象里
        videoObject.setThumbImage(BitmapFactory.decodeResource(mContext.getResources(), R.drawable.yangzi));
        videoObject.actionUrl = bundle.getString(ACTION_URL);
        videoObject.dataUrl = bundle.getString(DATA_URL);
        videoObject.dataHdUrl = bundle.getString(DATA_HD_URL);
        videoObject.duration = 10;
        videoObject.defaultText = "Vedio 默认文案";
        return videoObject;
    }

    /**
     * 创建多媒体（音频）消息对象。
     *
     * @return 多媒体（音乐）消息对象。
     */
    private VoiceObject getVoiceObj(Bundle bundle) {
        // 创建媒体消息
        VoiceObject voiceObject = new VoiceObject();
        voiceObject.identify = Utility.generateGUID();
        voiceObject.title = bundle.getString(TITLE);
        voiceObject.description = bundle.getString(DESCRIPTION);

        // 设置 Bitmap 类型的图片到视频对象里
        voiceObject.setThumbImage(BitmapFactory.decodeResource(mContext.getResources(), R.drawable.yangzi));
        voiceObject.actionUrl = bundle.getString(ACTION_URL);
        voiceObject.dataUrl = bundle.getString(DATA_URL);
        voiceObject.dataHdUrl = bundle.getString(DATA_HD_URL);
        voiceObject.duration = 10;
        voiceObject.defaultText = "Voice 默认文案";
        return voiceObject;
    }

    /**
     * 第三方应用发送请求消息到微博，唤起微博分享界面。
     *
     * @see {@link #sendMultiMessage} 或者 {@link #sendSingleMessage}
     */
    public void sendMessage(Bundle bundle, boolean hasText, boolean hasImage,
                            boolean hasWebpage, boolean hasMusic, boolean hasVideo, boolean hasVoice) {

        if (mIWeiboShareAPI.isWeiboAppSupportAPI()) {
            int supportApi = mIWeiboShareAPI.getWeiboAppSupportAPI();
            if (supportApi >= 10351 /*ApiUtils.BUILD_INT_VER_2_2*/) {
                sendMultiMessage(bundle, hasText, hasImage, hasWebpage, hasMusic, hasVideo, hasVoice);
            } else {
                sendSingleMessage(bundle, hasText, hasImage, hasWebpage, hasMusic, hasVideo/*, hasVoice*/);
            }
        } else {
            Toast.makeText(mContext, R.string.weiboapp_not_installed, Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 第三方应用发送请求消息到微博，唤起微博分享界面。
     * 注意：当 {@link IWeiboShareAPI#getWeiboAppSupportAPI()} >= 10351 时，支持同时分享多条消息，
     * 同时可以分享文本、图片以及其它媒体资源（网页、音乐、视频、声音中的一种）。
     *
     * @param hasText    分享的内容是否有文本
     * @param hasImage   分享的内容是否有图片
     * @param hasWebpage 分享的内容是否有网页
     * @param hasMusic   分享的内容是否有音乐
     * @param hasVideo   分享的内容是否有视频
     * @param hasVoice   分享的内容是否有声音
     */
    private void sendMultiMessage(Bundle bundle, boolean hasText, boolean hasImage, boolean hasWebpage,
                                  boolean hasMusic, boolean hasVideo, boolean hasVoice) {

        // 1. 初始化微博的分享消息
        WeiboMultiMessage weiboMessage = new WeiboMultiMessage();
        if (hasText) {
            weiboMessage.textObject = getTextObj(bundle);
        }

        if (hasImage) {
            weiboMessage.imageObject = getImageObj(bundle);
        }

        // 用户可以分享其它媒体资源（网页、音乐、视频、声音中的一种）
        if (hasWebpage) {
            weiboMessage.mediaObject = getWebpageObj(bundle);
        }
        if (hasMusic) {
            weiboMessage.mediaObject = getMusicObj(bundle);
        }
        if (hasVideo) {
            weiboMessage.mediaObject = getVideoObj(bundle);
        }
        if (hasVoice) {
            weiboMessage.mediaObject = getVoiceObj(bundle);
        }

        // 2. 初始化从第三方到微博的消息请求,可以是多请求
        SendMultiMessageToWeiboRequest request = new SendMultiMessageToWeiboRequest();
        // 用transaction唯一标识一个请求
        request.transaction = String.valueOf(System.currentTimeMillis());
        request.multiMessage = weiboMessage;

        // 3. 发送请求消息到微博，唤起微博分享界面
        mIWeiboShareAPI.sendRequest((Activity) mContext, request);
    }

    /**
     * 第三方应用发送请求消息到微博，唤起微博分享界面。
     * 当{@link IWeiboShareAPI#getWeiboAppSupportAPI()} < 10351 时，只支持分享单条消息，即
     * 文本、图片、网页、音乐、视频中的一种，不支持Voice消息。
     *
     * @param hasText    分享的内容是否有文本
     * @param hasImage   分享的内容是否有图片
     * @param hasWebpage 分享的内容是否有网页
     * @param hasMusic   分享的内容是否有音乐
     * @param hasVideo   分享的内容是否有视频
     */
    private void sendSingleMessage(Bundle bundle, boolean hasText, boolean hasImage, boolean hasWebpage,
                                   boolean hasMusic, boolean hasVideo/*, boolean hasVoice*/) {

        // 1. 初始化微博的分享消息
        // 用户可以分享文本、图片、网页、音乐、视频中的一种
        WeiboMessage weiboMessage = new WeiboMessage();
        if (hasText) {
            weiboMessage.mediaObject = getTextObj(bundle);
        }
        if (hasImage) {
            weiboMessage.mediaObject = getImageObj(bundle);
        }
        if (hasWebpage) {
            weiboMessage.mediaObject = getWebpageObj(bundle);
        }
        if (hasMusic) {
            weiboMessage.mediaObject = getMusicObj(bundle);
        }
        if (hasVideo) {
            weiboMessage.mediaObject = getVideoObj(bundle);
        }
        /*if (hasVoice) { //不支持声音
            weiboMessage.mediaObject = getVoiceObj();
        }*/

        // 2. 初始化从第三方到微博的消息请求，只能是单请求
        SendMessageToWeiboRequest request = new SendMessageToWeiboRequest();
        // 用transaction唯一标识一个请求
        request.transaction = String.valueOf(System.currentTimeMillis());
        request.message = weiboMessage;

        // 3. 发送请求消息到微博，唤起微博分享界面
        mIWeiboShareAPI.sendRequest((Activity) mContext, request);
    }
}
