package com.example.administrator.yzzf.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.administrator.yzzf.CustomView.GlideImageLoader;
import com.example.administrator.yzzf.R;
import com.example.administrator.yzzf.Util.ActivityStack;
import com.example.administrator.yzzf.Util.MyApplication;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

import static com.sina.weibo.sdk.openapi.legacy.AccountAPI.CAPITAL.A;

/**
 * Created by Administrator on 2017/4/1 0001.
 */

public class ActivityTest03 extends AppCompatActivity {
    private Banner banner;

    //设置图片标题:自动对应
    String title01 = new String("十大星级品牌联盟，全场2折起");
    String title02 = new String("全场2折起");
    String title03 = new String("十大星级品牌联盟");
    String title04 = new String("嗨购5折不要停");
    String title05 = new String("12趁现在");
    String title06 = new String("嗨购5折不要停，12.12趁现在");
    String title07 = new String("实打实大顶顶顶顶");

    List<String> titles = new ArrayList<>();
    List<String> images = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_zhuye_heade04);
        banner = (Banner) findViewById(R.id.banner);

        titles.add(title01);
        titles.add(title02);
        titles.add(title03);
        titles.add(title04);
        titles.add(title05);
        titles.add(title06);
        titles.add(title07);

        images.add("");
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
        banner.setDelayTime(3000);

        //设置banner动画效果
        banner.setBannerAnimation(Transformer.Accordion);

        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());

        //设置图片资源:可选图片网址/资源文件，默认用Glide加载,也可自定义图片的加载框架
        //所有设置参数方法都放在此方法之前执行
        banner.setImages(images);

        //设置点击事件，下标是从0开始
        banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                Intent intent = new Intent(ActivityTest03.this, TestActivity.class);
                startActivity(intent);
            }
        });

        //开始渲染
        banner.start();

        ActivityStack.getInstance().addActivity(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        //开始轮播
        banner.startAutoPlay();
    }

    @Override
    protected void onStop() {
        super.onStop();
        //结束轮播
        banner.stopAutoPlay();
    }
}
