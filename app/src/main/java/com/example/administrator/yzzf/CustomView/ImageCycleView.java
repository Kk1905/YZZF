package com.example.administrator.yzzf.CustomView;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.example.administrator.yzzf.Activity.TestActivity;
import com.example.administrator.yzzf.Bean.LunboBean;
import com.example.administrator.yzzf.R;
import com.nostra13.universalimageloader.core.ImageLoader;
import java.util.ArrayList;

/**
 * Created by Administrator on 2017/3/23 0023.
 */

public class ImageCycleView extends LinearLayout {

    /**
     * 图片资源列表
     */
    private ArrayList<LunboBean> mAdList = new ArrayList<LunboBean>();
    ;
    /**
     * 上下文
     */
    private Context mContext;

    /**
     * 图片轮播视图
     */
    private CycleViewPager cycleViewPager = null;

    /**
     * 滚动图片视图适配器
     */
    private ImageCycleAdapter mAdvAdapter;

    /**
     * 底下的标题
     */
    private TextView title;

    /**
     * 图片轮播指示器控件
     */
    private ViewGroup mGroup;

    /**
     * 图片轮播指示器-个图
     */
    private ImageView mImageView = null;

    /**
     * 滚动图片指示器-视图列表
     */
    private ImageView[] mImageViews = null;

    /**
     * 图片滚动当前图片下标
     */
    private int mImageIndex = 1;

    /**
     * 手机密度
     */
    private float mScale;

    /**
     * @param context
     */
    public ImageCycleView(Context context) {
        super(context);
    }


    /**
     * @param context
     * @param attrs
     */
    public ImageCycleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        mScale = context.getResources().getDisplayMetrics().density;
        LayoutInflater.from(context).inflate(R.layout.custom_image_cycle_view, this);
        cycleViewPager = (CycleViewPager) findViewById(R.id.pager_banner);
        cycleViewPager.setOnPageChangeListener(new GuidePageChangeListener());
        cycleViewPager.setOnTouchListener(new OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_UP:
                        // 开始图片自动滚动
                        startImageTimerTask();
                        break;
                    default:
                        // 停止图片自动滚动
                        stopImageTimerTask();
                        break;
                }
                return false;
            }
        });
        // 滚动图片右下指示器视图
        mGroup = (ViewGroup) findViewById(R.id.viewGroup);
        // 文本标题控件
        title = (TextView) findViewById(R.id.lunbo_title);

    }

    /**
     * 装填图片数据
     */
    public void setImageResources(ArrayList<LunboBean> mDatas) {
        // 清除所有子视图
        mGroup.removeAllViews();
        // 图片广告数量
        final int imageCount = mDatas.size();
        mImageViews = new ImageView[imageCount];
        for (int i = 0; i < imageCount; i++) {
            mImageView = new ImageView(mContext);
            int imageParams = (int) (mScale * 20 + 0.5f);// XP与DP转换，适应不同分辨率
            int imagePadding = (int) (mScale * 5 + 0.5f);
            LinearLayout.LayoutParams layout = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            layout.setMargins(3, 0, 3, 0);
            mImageView.setLayoutParams(layout);
            //mImageView.setPadding(imagePadding, imagePadding, imagePadding, imagePadding);
            mImageViews[i] = mImageView;
            if (i == 0) {
                mImageViews[i].setBackgroundResource(R.drawable.icon_point_pre);
            } else {
                mImageViews[i].setBackgroundResource(R.drawable.icon_point);
            }
            mGroup.addView(mImageViews[i]);
        }
        mAdvAdapter = new ImageCycleAdapter(mContext, mDatas);
        cycleViewPager.setAdapter(mAdvAdapter);
        startImageTimerTask();
    }

    /**
     * 开始轮播(手动控制自动轮播与否，便于资源控制)
     */
    public void startImageCycle() {
        startImageTimerTask();
    }

    /**
     * 暂停轮播——用于节省资源
     */
    public void pushImageCycle() {
        stopImageTimerTask();
    }

    /**
     * 开始图片滚动任务
     */
    private void startImageTimerTask() {
        stopImageTimerTask();
        // 图片每3秒滚动一次
        mHandler.postDelayed(mImageTimerTask, 3000);
    }

    /**
     * 停止图片滚动任务
     */
    private void stopImageTimerTask() {
        mHandler.removeCallbacks(mImageTimerTask);
    }

    private Handler mHandler = new Handler();

    /**
     * 图片自动轮播Task
     */
    private Runnable mImageTimerTask = new Runnable() {

        @Override
        public void run() {
            if (mImageViews != null) {
                // 下标等于图片列表长度说明已滚动到最后一张图片,重置下标
                if ((++mImageIndex) == mImageViews.length + 1) {
                    mImageIndex = 1;
                }
                cycleViewPager.setCurrentItem(mImageIndex);
            }
        }
    };

    /**
     * 轮播图片状态监听器
     *
     * @author minking
     */
    private final class GuidePageChangeListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrollStateChanged(int state) {
            if (state == ViewPager.SCROLL_STATE_IDLE)
                startImageTimerTask(); // 开始下次计时
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
        }

        @Override
        public void onPageSelected(int index) {
            if (index == 0 || index == mImageViews.length + 1) {
                return;
            }
            // 设置图片滚动指示器背景
            mImageIndex = index;
            index -= 1;

            String text_title = mAdList.get(index).getTitle();
            //设置标题
            title.setText(text_title);

            mImageViews[index].setBackgroundResource(R.drawable.icon_point_pre);
            for (int i = 0; i < mImageViews.length; i++) {
                if (index != i) {
                    mImageViews[i].setBackgroundResource(R.drawable.icon_point);
                }
            }

        }

    }

    private class ImageCycleAdapter extends PagerAdapter {

        /**
         * 图片视图缓存列表
         */
        private ArrayList<ImageView> mImageViewCacheList;

        /**
         * 广告图片点击监听器
         */
//        private ImageCycleViewListener mImageCycleViewListener;

        private Context mContext;

        public ImageCycleAdapter(Context context, ArrayList<LunboBean> adList) {
            mContext = context;
            mAdList = adList;
//            mImageCycleViewListener = imageCycleViewListener;
            mImageViewCacheList = new ArrayList<>();
        }

        @Override
        public int getCount() {
            return mAdList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object obj) {
            return view == obj;
        }

        @Override
        public Object instantiateItem(ViewGroup container, final int position) {

            String imageUrl = mAdList.get(position).getPictureUrl();
            final String url = mAdList.get(position).getUrl();
            ImageView imageView = null;
            if (mImageViewCacheList.isEmpty()) {
                imageView = new ImageView(mContext);
                imageView.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);

            } else {
                imageView = mImageViewCacheList.remove(0);
            }
            // 设置图片点击监听
            imageView.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, TestActivity.class);
                    if (url != null) {
                        intent.putExtra("url", url);
                    }
                    mContext.startActivity(intent);
                }
            });
            imageView.setTag(imageUrl);
            container.addView(imageView);

            //异步加载图片
            ImageLoader.getInstance().displayImage(imageUrl, imageView);

//            //设置标题
//            title.setText(text_title);//血的教训，不能在这里设置标题，标题不属于viewpager的内容，并没有放置在viewpager中，预加载会被多次设置内容

            return imageView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            ImageView view = (ImageView) object;
            container.removeView(view);
            mImageViewCacheList.add(view);
        }

    }

    /**
     * 轮播控件的监听事件
     *
     * @author minking
     */
    public static interface ImageCycleViewListener {

        /**
         * 加载图片资源
         *
         * @param imageURL
         * @param imageView
         */
//        public void displayImage(String imageURL, ImageView imageView);

        /**
         * 单击图片事件
         */
//        public void onImageClick(String url, View v);
    }

}
