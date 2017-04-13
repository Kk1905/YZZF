package com.example.administrator.yzzf.Util;

import android.content.SharedPreferences;

import com.example.administrator.yzzf.R;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;


/**
 * Created by Administrator on 2017/3/10 0010.
 */

public class MyApplication extends android.app.Application {
    @Override
    public void onCreate() {
        super.onCreate();
        //初始化ImageLoader,.showImageForEmptyUri(R.drawable.zhuye_kongbai)
        DisplayImageOptions mOptions = new DisplayImageOptions.Builder()

                .showStubImage(R.drawable.zhuye_kongbai)//下载期间显示的图片
                .showImageOnFail(R.drawable.zhuye_kongbai)//加载失败或者解码失败时显示的图片
                .cacheInMemory()
                .cacheOnDisc()
                .displayer(new RoundedBitmapDisplayer(20)).displayer(new FadeInBitmapDisplayer(300))
                .build();

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getApplicationContext())
                .defaultDisplayImageOptions(mOptions)
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .denyCacheImageMultipleSizesInMemory()
                .discCacheFileNameGenerator(new Md5FileNameGenerator())
                .tasksProcessingOrder(QueueProcessingType.LIFO).build();

        ImageLoader.getInstance().init(config);

    }

}
