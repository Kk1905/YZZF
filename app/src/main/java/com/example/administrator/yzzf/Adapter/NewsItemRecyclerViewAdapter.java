package com.example.administrator.yzzf.Adapter;

import android.content.Context;
import android.support.v4.util.SparseArrayCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.yzzf.Bean.LunboBean;
import com.example.administrator.yzzf.Bean.NewsItemBean;
import com.example.administrator.yzzf.CustomView.ImageCycleView;
import com.example.administrator.yzzf.R;
import com.google.gson.Gson;
import com.nostra13.universalimageloader.core.ImageLoader;

import org.json.JSONObject;

import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 可以添加多个Header
 * Created by Administrator on 2017/3/27 0027.
 */

public class NewsItemRecyclerViewAdapter extends RecyclerView.Adapter implements View.OnClickListener {

    private Context mContext;
    private List<NewsItemBean> mDatas = new ArrayList<>();
    private SparseArrayCompat<View> headerViews;

    private static final int ZUO_YOU = 0;
    private static final int SHANG_XIA = 1;
    private static final int GUANG_GAO = 2;
    private CallBack mCallBack;


    public NewsItemRecyclerViewAdapter(Context context, List<NewsItemBean> datas, CallBack callBack) {
        mContext = context;
        mDatas = datas;
        mCallBack = callBack;
    }

    public void setHeaderViews(SparseArrayCompat<View> headerViews) {
        this.headerViews = headerViews;
        notifyItemChanged(0);
    }

    private int getHeaderViewCount() {
        if (headerViews != null) {
            return headerViews.size();
        }
        return 0;
    }

//    public View getHeaderView() {
//        return mHeaderView;
//    }

    //清除原有数据，添加新的数据
    public void replaceDatas(ArrayList<NewsItemBean> datas) {
        if (datas != null && datas.size() != 0) {
            mDatas.clear();
            mDatas.addAll(datas);
            notifyDataSetChanged();
        }
    }

    //不删除原有数据，添加数据
    public void addDatas(ArrayList<NewsItemBean> datas) {
        if (mDatas != null) {
            int start = mDatas.size() + getHeaderViewCount();
            mDatas.addAll(datas);
            notifyItemRangeChanged(start, datas.size());
        }
    }

    //在操作数据时的真实位置，是和集合的index对应的
    public int getRealPostion(RecyclerView.ViewHolder holder) {
        int position = holder.getLayoutPosition();
        return position - getHeaderViewCount();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (headerViews != null && headerViews.get(viewType) != null) {
            return new HeaderViewHolder(headerViews.get(viewType));
        } else {
            switch (viewType) {
                case ZUO_YOU:
                    View view = LayoutInflater.from(mContext).inflate(R.layout.item_zhuye_news, parent, false);
                    view.setOnClickListener(this);
                    return new NormalViewHolder_ZuoYou(view);

                case SHANG_XIA:
                    View view1 = LayoutInflater.from(mContext).inflate(R.layout.item_zhuye_news02, parent, false);
                    view1.setOnClickListener(this);
                    return new NormalViewHolder_ShangXia(view1);

                case GUANG_GAO:
                    View view2 = LayoutInflater.from(mContext).inflate(R.layout.item_zhuye_news02, parent, false);
                    view2.setOnClickListener(this);
                    return new NormalViewHolder_ShangXia(view2);

            }
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (position < getHeaderViewCount()) {
            if (position == 1) {
                holder.itemView.setContentDescription("hasSticky");//用来标记是否要吸顶
            }
            return;
        }
        int index = getRealPostion(holder);
        NewsItemBean newsItemBean = mDatas.get(index);
        String source = newsItemBean.getSource();
        if (source.equals("") || source.equals("null")) {
            source = "社区头条";
        }
        String picture1 = newsItemBean.getPicture1();
//        if (picture1.equals("")) {
//
////            Log.e("kkkboy", "ZHUYE--------->" + picture1 + "------------>" + index);
//        }
        holder.itemView.setContentDescription("hasSticky");//用来标记是否要吸顶
        holder.itemView.setTag(newsItemBean);//用来传递新闻数据，newsItemBean
        switch (getItemViewType(position)) {
            case ZUO_YOU:
                if (picture1.equals("")) {
//                    Log.e("kkkboy", "ZHUYE--------->" + picture1 + "------------>" + index);
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
        return mDatas.size() + getHeaderViewCount();
    }

    @Override
    public int getItemViewType(int position) {
        if (position < getHeaderViewCount()) {

            return headerViews.keyAt(position);
        } else {
            position = position - getHeaderViewCount();

            switch (mDatas.get(position).getStates()) {
                case "0":
                    return ZUO_YOU;//左右
                case "1":
                    return SHANG_XIA;//上下
                case "2":
                    return GUANG_GAO;//广告
                default:
                    return ZUO_YOU;//默认返回左右排版
            }
        }
    }

    private class HeaderViewHolder extends RecyclerView.ViewHolder {
        public HeaderViewHolder(View itemView) {
            super(itemView);
        }
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

    @Override
    public void onClick(View v) {
        mCallBack.onItemClick(v, (NewsItemBean) v.getTag());
    }

    public interface CallBack {
        void onItemClick(View v, NewsItemBean newsItemBean);
    }
}
