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
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
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

    public NewsItemAdapter(Context context, List<NewsItemBean> mDatas,CallBack callBack) {
        mCallBack = callBack;
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
        mImageLoader.init(ImageLoaderConfiguration.createDefault(context));
        this.mDatas = mDatas;
        mOptions = new DisplayImageOptions.Builder().showStubImage(R.drawable.yangzi)
                .showImageForEmptyUri(R.drawable.yangzi).showImageOnFail(R.drawable.yangzi).cacheInMemory()
                .cacheOnDisc().displayer(new RoundedBitmapDisplayer(20)).displayer(new FadeInBitmapDisplayer(300))
                .build();

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
        return mDatas.size() + 2;
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
        //如果是列表第一行
        if (position == 0) {
            convertView = mLayoutInflater.inflate(R.layout.item_zhuye_head, null);

            convertView.findViewById(R.id.meiri_jingxuan_imageView).setOnClickListener(this);
            convertView.findViewById(R.id.shequ_gonggao_imageView).setOnClickListener(this);
            convertView.findViewById(R.id.huatijiaoliu_imageView).setOnClickListener(this);
            convertView.findViewById(R.id.yezhuchangshi_imageView).setOnClickListener(this);
            convertView.findViewById(R.id.zulinzhihuan_imageView).setOnClickListener(this);
            return convertView;
        }
        //最后一行
        else if (position == getCount() - 1) {
            convertView = mLayoutInflater.inflate(R.layout.item_zhuye_foot, null);
            convertView.findViewById(R.id.htjl_tiaozhuan_imageview).setOnClickListener(this);
            convertView.findViewById(R.id.xiangqing_tiaozhuan_zhuye).setOnClickListener(this);
            return convertView;
        }
        //普通列表项
        ViewHolder viewHolder = new ViewHolder();
        if (convertView == null || convertView.getTag() == null) {
            convertView = mLayoutInflater.inflate(R.layout.item_zhuye_news, null);
            viewHolder.imageView = (ImageView) convertView.findViewById(R.id.newsItem_imageview);
            viewHolder.title_textView = (TextView) convertView.findViewById(R.id.newsItem_title);
            viewHolder.time_textView = (TextView) convertView.findViewById(R.id.newsItem_time);
            viewHolder.num_textView = (TextView) convertView.findViewById(R.id.newsItem_num);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        NewsItemBean itemBean = mDatas.get(position - 1);
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

    @Override
    public void onClick(View v) {
        mCallBack.xListViewItemClick(v);
    }

    private class ViewHolder {
        ImageView imageView;
        TextView title_textView;
        TextView time_textView;
        TextView num_textView;
    }

    public interface CallBack {
        void xListViewItemClick(View v);
    }
}
