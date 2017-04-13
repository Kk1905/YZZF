package com.example.administrator.yzzf.WeChat;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.example.administrator.yzzf.R;
import com.example.administrator.yzzf.Util.Json2NewsUtil;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.ImageLoadingListener;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelmsg.WXImageObject;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.modelmsg.WXTextObject;
import com.tencent.mm.opensdk.modelmsg.WXVideoObject;
import com.tencent.mm.opensdk.modelmsg.WXWebpageObject;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

import static android.R.attr.bitmap;
import static com.example.administrator.yzzf.Util.Json2NewsUtil.GetLocalOrNetBitmap;
import static com.example.administrator.yzzf.Util.Json2NewsUtil.getBmp;
import static com.sina.weibo.sdk.openapi.legacy.AccountAPI.CAPITAL.F;


/**
 * Created by Administrator on 2017/3/3 0003.
 */

public class WeChatShareManager {
    private static final int THUMB_SIZE = 150;
    private static final String APP_ID = "wxa9114b008700020e";

    public static final int WECHAT_SHARE_WAY_TEXT = 0;//文字
    public static final int WECHAT_SHARE_WAY_PICTURE = 1;//图片
    public static final int WECHAT_SHARE_WAY_WEBPAGE = 2;//链接
    public static final int WECHAT_SHARE_WAY_VIDEO = 3;//视频

    public static final int WECHAT_SHARE_TYPE_TALK = SendMessageToWX.Req.WXSceneSession;//会话类型
    public static final int WECHAT_SHARE_TYPE_FRIENDS = SendMessageToWX.Req.WXSceneTimeline;//朋友圈

    private ShareContent mShareContentText, mShareContentPicture, mShareContentWebPage, mShareContentVideo;
    private static WeChatShareManager mInstance;
    private IWXAPI mIWXAPI;
    private Context mContext;

    //自定义一个抽象的内部类
    private abstract class ShareContent {
        protected abstract int getShareWay();

        protected abstract String getContent();

        protected abstract String getTitle();

        protected abstract String getUrl();

        protected abstract int getPictureResource();

        protected abstract String getPictureUrl();

    }

    //构造不同的类来继承ShareContent
    //文字
    public class ShareContentText extends ShareContent {
        private String content;

        public ShareContentText(String content) {
            this.content = content;
        }

        @Override
        protected int getShareWay() {
            return WECHAT_SHARE_WAY_TEXT;
        }

        @Override
        protected String getContent() {
            return content;
        }

        @Override
        protected String getTitle() {
            return null;
        }

        @Override
        protected String getUrl() {
            return null;
        }

        @Override
        protected int getPictureResource() {
            return -1;
        }

        @Override
        protected String getPictureUrl() {
            return null;
        }
    }

    //图片
    public class ShareContentPicture extends ShareContent {
        private int pictureResource;

        public ShareContentPicture(int pictureResource) {
            this.pictureResource = pictureResource;
        }

        @Override
        protected int getShareWay() {
            return WECHAT_SHARE_WAY_PICTURE;
        }

        @Override
        protected String getContent() {
            return null;
        }

        @Override
        protected String getTitle() {
            return null;
        }

        @Override
        protected String getUrl() {
            return null;
        }

        @Override
        protected int getPictureResource() {
            return pictureResource;
        }

        @Override
        protected String getPictureUrl() {
            return null;
        }
    }

    //链接
    public class ShareContentWebPage extends ShareContent {
        private String content;
        private String title;
        private int pictureResource;
        private String pictureUrl;
        private String url;

        public ShareContentWebPage(String content, String title, String pictureUrl, String url) {
            this.content = content;
            this.title = title;
            this.pictureUrl = pictureUrl;
            this.url = url;
        }

        @Override
        protected int getShareWay() {
            return WECHAT_SHARE_WAY_WEBPAGE;
        }

        @Override
        protected String getContent() {
            return content;
        }

        @Override
        protected String getTitle() {
            return title;
        }

        @Override
        protected String getUrl() {
            return url;
        }

        @Override
        protected int getPictureResource() {
            return pictureResource;
        }

        @Override
        protected String getPictureUrl() {
            return pictureUrl;
        }
    }

    //视频
    public class ShareContentVideo extends ShareContent {
        private String url;

        public ShareContentVideo(String url) {
            this.url = url;
        }

        @Override
        protected int getShareWay() {
            return WECHAT_SHARE_WAY_VIDEO;
        }

        @Override
        protected String getContent() {
            return null;
        }

        @Override
        protected String getTitle() {
            return null;
        }

        @Override
        protected String getUrl() {
            return url;
        }

        @Override
        protected int getPictureResource() {
            return -1;
        }

        @Override
        protected String getPictureUrl() {
            return null;
        }
    }

    //获取文字内容对象的方法，相当于实例化了内部类对象
    public ShareContentText getShareContentText(String content) {
        if (mShareContentText == null) {
            mShareContentText = new ShareContentText(content);
        }
        return (ShareContentText) mShareContentText;
    }

    //获取图片内容对象的方法
    public ShareContentPicture getShareContentPicture(int pictureResource) {
        if (mShareContentPicture == null) {
            mShareContentPicture = new ShareContentPicture(pictureResource);
        }
        return (ShareContentPicture) mShareContentPicture;
    }

    //获取链接内容对象的方法
    public ShareContentWebPage getShareContentWebPage(String content, String title, String pictureUrl, String url) {
        if (mShareContentWebPage == null) {
            mShareContentWebPage = new ShareContentWebPage(content, title, pictureUrl, url);
        }
        return (ShareContentWebPage) mShareContentWebPage;
    }

    //获取视频内容的方法
    public ShareContentVideo getShareContentVideo(String url) {
        if (mShareContentVideo == null) {
            mShareContentVideo = new ShareContentVideo(url);
        }
        return (ShareContentVideo) mShareContentVideo;
    }


    private WeChatShareManager(Context context) {
        mContext = context;
        //初始化数据
        initWeChatShare(context);
    }

    //单例，获取WeChatShareManager实例，非线程安全，在UI线程中操作(一般在主Activity的onCreate方法里)
    public static WeChatShareManager getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new WeChatShareManager(context);
        }
        return mInstance;
    }

    //注册应用到微信APP
    private void initWeChatShare(Context context) {
        if (mIWXAPI == null) {
            mIWXAPI = WXAPIFactory.createWXAPI(context, APP_ID, true);
        }
        mIWXAPI.registerApp(APP_ID);//完成注册
    }

    public IWXAPI getIWXAPI() {
        return mIWXAPI;
    }

    //主方法，通过微信分享,传入分享内容和分享类型两个参数
    public void shareByWeChat(ShareContent shareContent, int shareType) {
        switch (shareContent.getShareWay()) {
            case WECHAT_SHARE_WAY_TEXT:
                shareText(shareContent, shareType);
                break;
            case WECHAT_SHARE_WAY_PICTURE:
                sharePicture(shareContent, shareType);
                break;
            case WECHAT_SHARE_WAY_WEBPAGE:
                shareWebPage(shareContent, shareType);
                break;
            case WECHAT_SHARE_WAY_VIDEO:
                shareVideo(shareContent, shareType);
                break;
        }
    }

    //分享文字
    private void shareText(ShareContent shareContent, int shareType) {
        String text = shareContent.getContent();
        //初始化一个WXTextObject对象
        WXTextObject wxTextObject = new WXTextObject();
        wxTextObject.text = text;
        //WXTextObject对象来初始化一个WXMediaMessage对象
        WXMediaMessage wxMediaMessage = new WXMediaMessage();
        wxMediaMessage.mediaObject = wxTextObject;
        wxMediaMessage.description = text;
        //构造一个Req,用于发送请求信息给微信
        SendMessageToWX.Req req = new SendMessageToWX.Req();
        //transaction是req请求中的一个字段，它用于表示唯一标识这个请求，我们用系统当前时间与传入一个字段的形式可以保证此字段值的唯一
        req.transaction = buildTransaction("textShare");
        req.message = wxMediaMessage;
        //选择发送场景。可以是发送到会话WXSceneSession，也可以是发送到朋友圈WXSceneTimeLine
        req.scene = shareType;
        mIWXAPI.sendReq(req);
    }

    //分享图片
    private void sharePicture(ShareContent shareContent, int shareType) {
        //获取位图对象
        Bitmap bitmap = BitmapFactory.decodeResource(mContext.getResources(), shareContent.getPictureResource());

        WXImageObject wxImageObject = new WXImageObject(bitmap);
        WXMediaMessage wxMediaMessage = new WXMediaMessage();
        wxMediaMessage.mediaObject = wxImageObject;

        Bitmap thumbBitmap = Bitmap.createScaledBitmap(bitmap, THUMB_SIZE, THUMB_SIZE, true);//将原位图进行缩放成新的位图
        bitmap.recycle();//手动回收原位图

        wxMediaMessage.thumbData = Util.bmpToByteArray(thumbBitmap, true);//设置缩略图

        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = buildTransaction("pictureShare");
        req.message = wxMediaMessage;//总的来说，最后就是将WXMediaMessage整合，将其分享
        req.scene = shareType;
        mIWXAPI.sendReq(req);

    }

    //分享网页链接
    private void shareWebPage(final ShareContent shareContent, int shareType) {
        WXWebpageObject wxWebpageObject = new WXWebpageObject();
        wxWebpageObject.webpageUrl = shareContent.getUrl();
        final WXMediaMessage wxMediaMessage = new WXMediaMessage(wxWebpageObject);
        wxMediaMessage.description = shareContent.getContent();
        wxMediaMessage.title = shareContent.getTitle();

//        Bitmap bitmap = BitmapFactory.decodeResource(mContext.getResources(), shareContent.getPictureResource());
        FutureTask<byte[]> futureTask = new FutureTask(new Callable<byte[]>() {
            @Override
            public byte[] call() throws Exception {
                Bitmap thumb = Bitmap.createScaledBitmap(Json2NewsUtil.getBmp(shareContent.getPictureUrl()), 300, 300, true);//压缩Bitmap

                return Util.bmpToByteArray(thumb, true);
            }
        });
        new Thread(futureTask).start();
        try {

            wxMediaMessage.thumbData = futureTask.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

//        new DownLoadBitmap().execute(shareContent.getPictureUrl(), shareContent, shareType);
//        wxMediaMessage.thumbData = Util.bmpToByteArray(thumb, true);
        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = buildTransaction("webPageShare");
        req.message = wxMediaMessage;
        req.scene = shareType;

        mIWXAPI.sendReq(req);
    }

    //分享视频
    private void shareVideo(ShareContent shareContent, int shareType) {
        WXVideoObject wxVideoObject = new WXVideoObject();
        wxVideoObject.videoUrl = shareContent.getUrl();
        WXMediaMessage wxMediaMessage = new WXMediaMessage(wxVideoObject);

        wxMediaMessage.title = shareContent.getTitle();
        wxMediaMessage.description = shareContent.getContent();
        Bitmap thumb = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.send_music_thumb);
//      BitmapFactory.decodeStream(new URL(video.videoUrl).openStream());
        /**
         * 测试过程中会出现这种情况，会有个别手机会出现调不起微信客户端的情况。造成这种情况的原因是微信对缩略图的大小、title、description等参数的大小做了限制，所以有可能是大小超过了默认的范围。
         * 一般情况下缩略图超出比较常见。Title、description都是文本，一般不会超过。
         */
        Bitmap thumbBitmap = Bitmap.createScaledBitmap(thumb, THUMB_SIZE, THUMB_SIZE, true);
        thumb.recycle();
        wxMediaMessage.thumbData = Util.bmpToByteArray(thumbBitmap, true);

        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = buildTransaction("video");
        req.message = wxMediaMessage;
        req.scene = shareType;
        mIWXAPI.sendReq(req);
    }

    private String buildTransaction(final String type) {
        return type == null ? String.valueOf(System.currentTimeMillis()) : type + System.currentTimeMillis();
    }

    private class DownLoadBitmap extends AsyncTask<Object, Void, Bitmap> {
        ShareContent mShareContent;
        int shareType;

        @Override
        protected Bitmap doInBackground(Object... params) {
            mShareContent = (ShareContent) params[1];
            shareType = (int) params[2];
//            return Json2NewsUtil.getBmp((String) params[0]);
            return BitmapFactory.decodeResource(mContext.getResources(), R.drawable.yangzi);
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {

            WXWebpageObject wxWebpageObject = new WXWebpageObject();
            wxWebpageObject.webpageUrl = mShareContent.getUrl();
            final WXMediaMessage wxMediaMessage = new WXMediaMessage(wxWebpageObject);
            wxMediaMessage.description = mShareContent.getContent();
            wxMediaMessage.title = mShareContent.getTitle();
            Bitmap thumb = Bitmap.createScaledBitmap(bitmap, 300, 300, true);//压缩Bitmap
            wxMediaMessage.thumbData = Util.bmpToByteArray(thumb, true);
            SendMessageToWX.Req req = new SendMessageToWX.Req();
            req.transaction = buildTransaction("webPageShare");
            req.message = wxMediaMessage;
            req.scene = shareType;

            mIWXAPI.sendReq(req);
        }
    }
}
