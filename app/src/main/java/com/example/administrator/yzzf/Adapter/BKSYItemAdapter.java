package com.example.administrator.yzzf.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.yzzf.Bean.NewsItemBean;
import com.example.administrator.yzzf.R;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * Created by Administrator on 2017/3/24 0024.
 */

public class BKSYItemAdapter extends BaseAdapter {
    private List<NewsItemBean> mDatas;
    private LayoutInflater mLayoutInflater;
    private Context mContext;

    public BKSYItemAdapter(Context context, List<NewsItemBean> datas) {
        mContext = context;
        mDatas = datas;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    public void add(List<NewsItemBean> datas) {
        mDatas.clear();
        mDatas.addAll(datas);
    }

    public void addAll(List<NewsItemBean> datas) {
        mDatas.addAll(datas);
    }


    @Override
    public int getCount() {
        return 12;
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
    public View getView(int position, View convertView, ViewGroup parent) {
//        ViewHolder holder = new ViewHolder();
//        NewsItemBean newsItemBean = mDatas.get(position);
//        if (newsItemBean.getStates().equals("0")) {
//            //左右排版
//            if (convertView == null) {
//                convertView = mLayoutInflater.inflate(R.layout.item_zhuye_news, null);
//                holder.imageView01 = (ImageView) convertView.findViewById(R.id.item_zhuye_news_imageview);
//                holder.title_textView = (TextView) convertView.findViewById(R.id.item_zhuye_news_title);
//                holder.fabu_textView = (TextView) convertView.findViewById(R.id.item_zhuye_news_fabu);
//                holder.time_textView = (TextView) convertView.findViewById(R.id.item_zhuye_news_ago);
//                holder.num_textView = (TextView) convertView.findViewById(R.id.item_zhuye_news_hits);
//                convertView.setTag(holder);
//            } else {
//                holder = (ViewHolder) convertView.getTag();
//            }
//            holder.time_textView.setText(newsItemBean.getTitle());
//            holder.fabu_textView.setText(newsItemBean.getSource());
//            holder.time_textView.setText(newsItemBean.getHourAgo());
//            holder.num_textView.setText(newsItemBean.getHits());
//            if (newsItemBean.getPicture() != null) {
//                ImageLoader.getInstance().displayImage(newsItemBean.getPicture(), holder.imageView01);
//            }
//        } else if (newsItemBean.getStates().equals("1")) {
//            //上下排版
//            if (convertView == null) {
//                convertView = mLayoutInflater.inflate(R.layout.item_zhuye_news02, null);
//                holder.imageView01 = (ImageView) convertView.findViewById(R.id.item_zhuye_news02_image01);
//                holder.imageView02 = (ImageView) convertView.findViewById(R.id.item_zhuye_news02_image02);
//                holder.imageView03 = (ImageView) convertView.findViewById(R.id.item_zhuye_news02_image03);
//                holder.title_textView = (TextView) convertView.findViewById(R.id.item_zhuye_news02_title);
//                holder.fabu_textView = (TextView) convertView.findViewById(R.id.item_zhuye_news02_fabu);
//                holder.time_textView = (TextView) convertView.findViewById(R.id.item_zhuye_news02_ago);
//                holder.num_textView = (TextView) convertView.findViewById(R.id.item_zhuye_news02_hits);
//                convertView.setTag(holder);
//            } else {
//                holder = (ViewHolder) convertView.getTag();
//            }
//            holder.time_textView.setText(newsItemBean.getTitle());
//            holder.fabu_textView.setText(newsItemBean.getSource());
//            holder.time_textView.setText(newsItemBean.getHourAgo());
//            holder.num_textView.setText(newsItemBean.getHits());
//            if (newsItemBean.getPicture() != null) {
//                ImageLoader.getInstance().displayImage(newsItemBean.getPicture(), holder.imageView01);
//            }
//        }

        if (position % 2 == 0) {
            return mLayoutInflater.inflate(R.layout.item_zhuye_news02, null);
        } else return mLayoutInflater.inflate(R.layout.item_zhuye_news, null);
//        return convertView;
    }

    private class ViewHolder {
        ImageView imageView01;
        ImageView imageView02;
        ImageView imageView03;
        TextView title_textView;
        TextView time_textView;
        TextView fabu_textView;
        TextView num_textView;
        ImageView guanggaoImage;
    }
}
