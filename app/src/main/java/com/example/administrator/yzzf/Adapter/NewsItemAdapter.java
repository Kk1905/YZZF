package com.example.administrator.yzzf.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.yzzf.Bean.NewsItemBean;
import com.example.administrator.yzzf.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/3/9 0009.
 */

public class NewsItemAdapter extends BaseAdapter {
    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private ArrayList<NewsItemBean> mDatas;

    private ImageLoader mImageLoader = ImageLoader.getInstance();
    private DisplayImageOptions mOptions;

    public NewsItemAdapter(Context context, ArrayList<NewsItemBean> mDatas) {
        mContext=context;
        mLayoutInflater = LayoutInflater.from(context);
        mImageLoader.init(ImageLoaderConfiguration.createDefault(context));
        this.mDatas = mDatas;
        mOptions = new DisplayImageOptions.Builder().showStubImage(R.drawable.yangzi)
                .showImageForEmptyUri(R.drawable.yangzi).showImageOnFail(R.drawable.yangzi).cacheInMemory()
                .cacheOnDisc().displayer(new RoundedBitmapDisplayer(20)).displayer(new FadeInBitmapDisplayer(300))
                .build();

    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public Object getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = new ViewHolder();
        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.item_news, null);
            viewHolder.imageView = (ImageView) convertView.findViewById(R.id.newsItem_imageview);
            viewHolder.title_textView = (TextView) convertView.findViewById(R.id.newsItem_title);
            viewHolder.time_textView = (TextView) convertView.findViewById(R.id.newsItem_time);
            viewHolder.num_textView = (TextView) convertView.findViewById(R.id.newsItem_num);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        NewsItemBean itemBean = mDatas.get(position);
        viewHolder.title_textView.setText(itemBean.getTitle());
        viewHolder.time_textView.setText(itemBean.getDate());
        viewHolder.num_textView.setText(itemBean.getNum());
        //判断是否有图片，如果有，就用imageLoader加载图片在imageView上
        if (itemBean.getImageUrl() != null) {
            viewHolder.imageView.setVisibility(View.VISIBLE);
            mImageLoader.displayImage(itemBean.getImageUrl(), viewHolder.imageView, mOptions);
        } else {
            viewHolder.imageView.setVisibility(View.GONE);
        }
        return convertView;
    }

    private class ViewHolder {
        ImageView imageView;
        TextView title_textView;
        TextView time_textView;
        TextView num_textView;
    }
}
