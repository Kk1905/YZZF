package com.example.administrator.yzzf.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.yzzf.Bean.NewsItemBean;
import com.example.administrator.yzzf.R;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import static com.sina.weibo.sdk.openapi.legacy.CommonAPI.CAPITAL.s;

/**
 * Created by Administrator on 2017/3/24 0024.
 */

public class BKSYItemAdapter extends RecyclerView.Adapter implements View.OnClickListener {
    private List<NewsItemBean> mDatas = new ArrayList<>();
    private LayoutInflater mLayoutInflater;
    private Context mContext;
    private static final int ZUO_YOU = 0;
    private static final int SHANG_XIA = 1;
    private static final int GUANG_GAO = 2;
    private NewsItemRecyclerViewAdapter.CallBack mCallBack;

    public BKSYItemAdapter(Context context, List<NewsItemBean> datas, NewsItemRecyclerViewAdapter.CallBack callBack) {
        mContext = context;
        mDatas = datas;
        mCallBack = callBack;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    //清除原有数据，添加新的数据
    public void replaceDatas(ArrayList<NewsItemBean> datas) {
        if (datas != null && datas.size() > 0) {
            mDatas.clear();
            mDatas.addAll(datas);
            notifyDataSetChanged();
        }
    }

    //不删除原有数据，添加数据
    public void addDatas(ArrayList<NewsItemBean> datas) {
        int start = mDatas.size();
        mDatas.addAll(datas);
        notifyItemRangeChanged(start, datas.size());
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case ZUO_YOU:
                View view = mLayoutInflater.inflate(R.layout.item_zhuye_news, parent, false);
                view.setOnClickListener(this);
                return new NormalViewHolder_ZuoYou(view);

            case SHANG_XIA:
                View view02 = mLayoutInflater.inflate(R.layout.item_zhuye_news02, parent, false);
                view02.setOnClickListener(this);
                return new NormalViewHolder_ShangXia(view02);

            case GUANG_GAO:
                View view03 = mLayoutInflater.inflate(R.layout.item_zhuye_news02, parent, false);
                view03.setOnClickListener(this);
                return new NormalViewHolder_ShangXia(view03);

        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        NewsItemBean newsItemBean = mDatas.get(position);
        String source = newsItemBean.getSource();
        if (source.equals("") || source.equals("null")) {
            source = "社区头条";
        }
        holder.itemView.setTag(newsItemBean);
        String picture1 = newsItemBean.getPicture1();
//        Log.e("kkkboy", "BKSY--------->"+picture1);

        switch (getItemViewType(position)) {
            case ZUO_YOU:
                if (picture1.equals("")) {
//                    Log.e("kkkboy", "BKSY--------->" + picture1 + "positin------------>" + position + newsItemBean.getTitle());
                    ((NormalViewHolder_ZuoYou) holder).imageView01.setVisibility(View.GONE);
                } else {
                    ((NormalViewHolder_ZuoYou) holder).imageView01.setVisibility(View.VISIBLE);
                    ImageLoader.getInstance().displayImage(picture1, ((NormalViewHolder_ZuoYou) holder).imageView01);
                }
                ((NormalViewHolder_ZuoYou) holder).title_textView.setText(newsItemBean.getTitle());
                ((NormalViewHolder_ZuoYou) holder).time_textView.setText(newsItemBean.getHourAgo());
                ((NormalViewHolder_ZuoYou) holder).fabu_textView.setText(source);
                ((NormalViewHolder_ZuoYou) holder).num_textView.setText(newsItemBean.getHits() + "");

                break;
            case SHANG_XIA:
                ImageLoader.getInstance().displayImage(newsItemBean.getPicture1(), ((NormalViewHolder_ShangXia) holder).imageView01);
                ImageLoader.getInstance().displayImage(newsItemBean.getPicture2(), ((NormalViewHolder_ShangXia) holder).imageView02);
                ImageLoader.getInstance().displayImage(newsItemBean.getPicture3(), ((NormalViewHolder_ShangXia) holder).imageView03);
                ((NormalViewHolder_ShangXia) holder).title_textView.setText(newsItemBean.getTitle());
                ((NormalViewHolder_ShangXia) holder).time_textView.setText(newsItemBean.getHourAgo());
                ((NormalViewHolder_ShangXia) holder).fabu_textView.setText(source);
                ((NormalViewHolder_ShangXia) holder).num_textView.setText(newsItemBean.getHits() + "");

                break;
            case GUANG_GAO:
                ImageLoader.getInstance().displayImage(newsItemBean.getPicture1(), ((NormalViewHolder_ShangXia) holder).imageView01);
                ImageLoader.getInstance().displayImage(newsItemBean.getPicture2(), ((NormalViewHolder_ShangXia) holder).imageView02);
                ImageLoader.getInstance().displayImage(newsItemBean.getPicture3(), ((NormalViewHolder_ShangXia) holder).imageView03);
                ((NormalViewHolder_ShangXia) holder).title_textView.setText(newsItemBean.getTitle());
                ((NormalViewHolder_ShangXia) holder).time_textView.setText(newsItemBean.getHourAgo());
                ((NormalViewHolder_ShangXia) holder).fabu_textView.setText(source);
                ((NormalViewHolder_ShangXia) holder).num_textView.setText(newsItemBean.getHits() + "");
                ((NormalViewHolder_ShangXia) holder).setVisibale(true);

                break;
        }
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    @Override
    public int getItemViewType(int position) {
        switch (mDatas.get(position).getStates()) {
            case "0":
                return ZUO_YOU;
            case "1":
                return SHANG_XIA;
            case "2":
                return GUANG_GAO;
        }

        return -1;
    }

    @Override
    public void onClick(View v) {
        mCallBack.onItemClick(v, (NewsItemBean) v.getTag());
    }

    private class NormalViewHolder_ZuoYou extends RecyclerView.ViewHolder {

        private ImageView imageView01;
        private TextView title_textView;
        private TextView time_textView;
        private TextView fabu_textView;
        private TextView num_textView;

        public NormalViewHolder_ZuoYou(View itemView) {
            super(itemView);

            imageView01 = (ImageView) itemView.findViewById(R.id.item_zhuye_news_imageview);
            title_textView = (TextView) itemView.findViewById(R.id.item_zhuye_news_title);
            num_textView = (TextView) itemView.findViewById(R.id.item_zhuye_news_hits);
            time_textView = (TextView) itemView.findViewById(R.id.item_zhuye_news_ago);
            fabu_textView = (TextView) itemView.findViewById(R.id.item_zhuye_news_fabu);
        }
    }

    private class NormalViewHolder_ShangXia extends RecyclerView.ViewHolder {

        private ImageView imageView01;
        private ImageView imageView02;
        private ImageView imageView03;
        private TextView title_textView;
        private TextView time_textView;
        private TextView fabu_textView;
        private TextView num_textView;
        private ImageView guanggaoImage;

        public NormalViewHolder_ShangXia(View itemView) {
            super(itemView);
            imageView01 = (ImageView) itemView.findViewById(R.id.item_zhuye_news02_image01);
            imageView02 = (ImageView) itemView.findViewById(R.id.item_zhuye_news02_image02);
            imageView03 = (ImageView) itemView.findViewById(R.id.item_zhuye_news02_image03);
            title_textView = (TextView) itemView.findViewById(R.id.item_zhuye_news02_title);
            num_textView = (TextView) itemView.findViewById(R.id.item_zhuye_news02_hits);
            time_textView = (TextView) itemView.findViewById(R.id.item_zhuye_news02_ago);
            fabu_textView = (TextView) itemView.findViewById(R.id.item_zhuye_news02_fabu);
            guanggaoImage = (ImageView) itemView.findViewById(R.id.item_zhuye_news02_image_fabu);
            //默认是没有广告图片的
            setVisibale(false);
        }

        private void setVisibale(boolean isVisibale) {
            if (!isVisibale) {
                guanggaoImage.setVisibility(View.GONE);
            } else {
                guanggaoImage.setVisibility(View.VISIBLE);
            }
        }
    }
}
