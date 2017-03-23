package com.example.administrator.yzzf.Adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.yzzf.Bean.NewsItemBean;
import com.example.administrator.yzzf.Fragment.LunboFragment;
import com.example.administrator.yzzf.Fragment.WDDDFragment;
import com.example.administrator.yzzf.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.viewpagerindicator.CirclePageIndicator;
import com.viewpagerindicator.TabPageIndicator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/3/9 0009.
 */

public class NewsItemAdapter extends BaseAdapter implements View.OnClickListener {
    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private List<NewsItemBean> mDatas;
    private CallBack mCallBack;

    private ImageLoader mImageLoader = ImageLoader.getInstance();
    private DisplayImageOptions mOptions;
    private List<Fragment> mLunboFragments;
    private ArrayList<String> lunboImageUrls;
    private ArrayList<String> lunboUrls;
    private ArrayList<String> lunboTitles;
    private LunboFragmentAdapter mLunboFragmentAdapter;

    private static final int ZUO_YOU = 0;
    private static final int SHANG_XIA = 1;
    private static final int GUANG_GAO = 2;

    public NewsItemAdapter(Context context, List<NewsItemBean> mDatas, ArrayList<String> pictureUrl, ArrayList<String> urls, ArrayList<String> titles, CallBack callBack) {
        mCallBack = callBack;
        mContext = context;
        lunboImageUrls = pictureUrl;
        lunboUrls = urls;
        lunboTitles = titles;
        mLayoutInflater = LayoutInflater.from(context);
        mImageLoader.init(ImageLoaderConfiguration.createDefault(context));
        this.mDatas = mDatas;
        mOptions = new DisplayImageOptions.Builder().showStubImage(R.drawable.zhuye_kongbai)
                .showImageForEmptyUri(R.drawable.zhuye_kongbai).showImageOnFail(R.drawable.zhuye_kongbai).cacheInMemory()
                .cacheOnDisc().displayer(new RoundedBitmapDisplayer(20)).displayer(new FadeInBitmapDisplayer(300))
                .build();

        mLunboFragments = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
//            mLunboFragments.add(LunboFragment.newInstance(lunboUrls.get(i), lunboImageUrls.get(i)));
            mLunboFragments.add(new WDDDFragment());
        }
        mLunboFragmentAdapter = new LunboFragmentAdapter(((AppCompatActivity) context).getSupportFragmentManager(), mLunboFragments);

    }

    //不将原数据删除，添加新的数据
    public void addAll(List<NewsItemBean> datas) {
        mDatas.addAll(datas);
    }

    //将原数据删除，全部改为新的数据
    public void setDatas(List<NewsItemBean> datas) {
        mDatas.clear();
        mDatas.addAll(datas);
    }

    @Override
    public int getCount() {
        return mDatas.size() + 10;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        if (mDatas.size() > 0) {
            switch (mDatas.get(position).getTypeid()) {
                case ZUO_YOU:
                    return ZUO_YOU;
                case SHANG_XIA:
                    return SHANG_XIA;
                case GUANG_GAO:
                    return GUANG_GAO;
                default:
                    return -1;
            }
        }
        return -1;
    }

    @Override
    public int getViewTypeCount() {
        return 3;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //如果是列表第一行
        if (position == 0) {
            convertView = mLayoutInflater.inflate(R.layout.item_zhuye_head02, null);

            ViewPager viewPager = (ViewPager) convertView.findViewById(R.id.item_zhuye_head_viewpager);
            CirclePageIndicator indicator = (CirclePageIndicator) convertView.findViewById(R.id.item_zhuye_head_indicator);
            TextView title = (TextView) convertView.findViewById(R.id.item_zhuye_head_title);
            if (lunboTitles.size() > 0) {
                title.setText(lunboTitles.get(position));
            }

            convertView.findViewById(R.id.item_zhuye_head_tuijian).setOnClickListener(this);
            convertView.findViewById(R.id.item_zhuye_head_remen).setOnClickListener(this);
            convertView.findViewById(R.id.item_zhuye_head_junshi).setOnClickListener(this);
            convertView.findViewById(R.id.item_zhuye_head_caijing).setOnClickListener(this);
            convertView.findViewById(R.id.item_zhuye_head_wenti).setOnClickListener(this);
            convertView.findViewById(R.id.item_zhuye_head_gengduo).setOnClickListener(this);

            viewPager.setAdapter(mLunboFragmentAdapter);
            indicator.setViewPager(viewPager);
            indicator.setCurrentItem(0);
            indicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                }

                @Override
                public void onPageSelected(int position) {

                }

                @Override
                public void onPageScrollStateChanged(int state) {

                }
            });

            return convertView;
        }
//        //最后一行
//        else if (position == getCount() - 1) {
//            convertView = mLayoutInflater.inflate(R.layout.item_zhuye_foot, null);
//            convertView.findViewById(R.id.htjl_tiaozhuan_imageview).setOnClickListener(this);
//            convertView.findViewById(R.id.xiangqing_tiaozhuan_zhuye).setOnClickListener(this);
//            return convertView;
//        }
        //普通列表项,排版方式根据获取到的新闻typeId的不同而不同
//        ViewHolder viewHolder = new ViewHolder();
//        NewsItemBean itemBean = mDatas.get(position - 1);
//        if (getItemViewType(position) == ZUO_YOU) {
//            if (convertView == null || convertView.getTag() == null) {
//                convertView = mLayoutInflater.inflate(R.layout.item_zhuye_news, null);
//                viewHolder.imageView01 = (ImageView) convertView.findViewById(R.id.newsItem_imageview);
//                viewHolder.title_textView = (TextView) convertView.findViewById(R.id.newsItem_title);
//                viewHolder.num_textView = (TextView) convertView.findViewById(R.id.newsItem_hits);
//                viewHolder.time_textView = (TextView) convertView.findViewById(R.id.newsItem_time);
//                viewHolder.guanggaoImage = (ImageView) convertView.findViewById(R.id.item_zhuye_news_image_fabu);
//                convertView.setTag(viewHolder);
//            } else {
//                viewHolder = (ViewHolder) convertView.getTag();
//            }
//            viewHolder.num_textView.setText(String.valueOf(itemBean.getHits()));
//            viewHolder.title_textView.setText(itemBean.getTitle());
//            viewHolder.time_textView.setText(itemBean.getHourAgo());
//            viewHolder.guanggaoImage.setVisibility(View.GONE);
//            //判断是否有图片，如果有，就用imageLoader加载图片在imageView上
//            if (itemBean.getPicture() != null) {
//                viewHolder.imageView01.setVisibility(View.VISIBLE);
//                mImageLoader.displayImage(itemBean.getPicture(), viewHolder.imageView01, mOptions);
//            } else {
//                viewHolder.imageView01.setVisibility(View.GONE);
//            }
//        } else if (getItemViewType(position) == SHANG_XIA) {
//            if (convertView == null || convertView.getTag() == null) {
//                convertView = mLayoutInflater.inflate(R.layout.item_zhuye_news02, null);
//                viewHolder.imageView01 = (ImageView) convertView.findViewById(R.id.item_zhuye_news02_image01);
//                viewHolder.imageView02 = (ImageView) convertView.findViewById(R.id.item_zhuye_news02_image02);
//                viewHolder.imageView03 = (ImageView) convertView.findViewById(R.id.item_zhuye_news02_image03);
//                viewHolder.title_textView = (TextView) convertView.findViewById(R.id.item_zhuye_news02_title);
//                viewHolder.num_textView = (TextView) convertView.findViewById(R.id.item_zhuye_news02_hits);
//                viewHolder.time_textView = (TextView) convertView.findViewById(R.id.item_zhuye_news02_ago);
//                viewHolder.guanggaoImage = (ImageView) convertView.findViewById(R.id.item_zhuye_news02_image_fabu);
//                convertView.setTag(viewHolder);
//            } else {
//                viewHolder = (ViewHolder) convertView.getTag();
//            }
//            viewHolder.num_textView.setText(String.valueOf(itemBean.getHits()));
//            viewHolder.title_textView.setText(itemBean.getTitle());
//            viewHolder.time_textView.setText(itemBean.getHourAgo());
//            viewHolder.guanggaoImage.setVisibility(View.GONE);
//            //判断是否有图片，如果有，就用imageLoader加载图片在imageView上
//            if (itemBean.getPicture() != null) {
//                viewHolder.imageView01.setVisibility(View.VISIBLE);
//                mImageLoader.displayImage(itemBean.getPicture(), viewHolder.imageView01, mOptions);
//            } else {
//                viewHolder.imageView01.setVisibility(View.GONE);
//            }
//            //TODO
//        } else if (getItemViewType(position) == GUANG_GAO) {
//            if (convertView == null || convertView.getTag() == null) {
//                convertView = mLayoutInflater.inflate(R.layout.item_zhuye_news02, null);
//                viewHolder.imageView01 = (ImageView) convertView.findViewById(R.id.item_zhuye_news02_image01);
//                viewHolder.imageView02 = (ImageView) convertView.findViewById(R.id.item_zhuye_news02_image02);
//                viewHolder.imageView03 = (ImageView) convertView.findViewById(R.id.item_zhuye_news02_image03);
//                viewHolder.title_textView = (TextView) convertView.findViewById(R.id.item_zhuye_news02_title);
//                viewHolder.num_textView = (TextView) convertView.findViewById(R.id.item_zhuye_news02_hits);
//                viewHolder.time_textView = (TextView) convertView.findViewById(R.id.item_zhuye_news02_ago);
//                viewHolder.guanggaoImage = (ImageView) convertView.findViewById(R.id.item_zhuye_news02_image_fabu);
//                convertView.setTag(viewHolder);
//            } else {
//                viewHolder = (ViewHolder) convertView.getTag();
//            }
//            viewHolder.num_textView.setText(String.valueOf(itemBean.getHits()));
//            viewHolder.title_textView.setText(itemBean.getTitle());
//            viewHolder.time_textView.setText(itemBean.getHourAgo());
//            viewHolder.guanggaoImage.setVisibility(View.VISIBLE);
//            //判断是否有图片，如果有，就用imageLoader加载图片在imageView上
//            if (itemBean.getPicture() != null) {
//                viewHolder.imageView01.setVisibility(View.VISIBLE);
//                mImageLoader.displayImage(itemBean.getPicture(), viewHolder.imageView01, mOptions);
//            } else {
//                viewHolder.imageView01.setVisibility(View.GONE);
//            }
//            //TODO
//        }
//        return convertView;
        if (position % 2 == 0) {
            return mLayoutInflater.inflate(R.layout.item_zhuye_news02, null);
        }
        else return mLayoutInflater.inflate(R.layout.item_zhuye_news, null);
    }

    @Override
    public void onClick(View v) {
        mCallBack.xListViewItemClick(v);
    }

    private class ViewHolder {
        ImageView imageView01;
        ImageView imageView02;
        ImageView imageView03;
        TextView title_textView;
        TextView time_textView;
        TextView num_textView;
        ImageView guanggaoImage;
    }

    public interface CallBack {
        void xListViewItemClick(View v);
    }
}
