package com.example.administrator.yzzf.Adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.yzzf.Bean.GuanggaoItemBean;
import com.example.administrator.yzzf.CustomView.CustomGuangGao;
import com.example.administrator.yzzf.R;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/4/6 0006.
 */

public class GuanggaoItemAdapter extends RecyclerView.Adapter implements View.OnClickListener {
    private GuanggaoSelected mListener;
    private Context mContext;
    private List<GuanggaoItemBean> mDatas;
    private List<GuanggaoItemBean> tuiguang = new ArrayList<>();
    private List<GuanggaoItemBean> shangpin = new ArrayList<>();

    private static final int TUI_GUANG = 0;
    private static final int SHANG_PIN = 1;

    private static final int TUI_GUANG_TOP = 2;
    private static final int SHANG_PIN_TOP = 3;

    public GuanggaoItemAdapter(Context context, List<GuanggaoItemBean> datas, GuanggaoSelected listener) {
        mContext = context;
        mDatas = datas;
        mListener = listener;
        //分开装
        for (GuanggaoItemBean guanggaoItemBean : datas) {
            if (guanggaoItemBean.getTypes().equals("推广")) {
                tuiguang.add(guanggaoItemBean);
            } else if (guanggaoItemBean.getTypes().equals("商品")) {
                shangpin.add(guanggaoItemBean);
            }
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case TUI_GUANG_TOP:
                View view = LayoutInflater.from(mContext).inflate(R.layout.activity_fenxiang_item_guanggao, parent, false);
                if (tuiguang.size() == 0) {
                    TextView textView = (TextView) view.findViewById(R.id.guanggao_no_data);
                    textView.setVisibility(View.VISIBLE);
                }
                view.setTag("1");//表示跳过
                return new GuanggaoViewHolder(view);
            case TUI_GUANG:
                View view1 = LayoutInflater.from(mContext).inflate(R.layout.custom_radion_button, parent, false);
                view1.setTag("2");//表示不跳过
                return new GuanggaoViewHolder(view1);
            case SHANG_PIN_TOP:
                View view2 = LayoutInflater.from(mContext).inflate(R.layout.activity_fenxiang_item_shangpin, parent, false);
                //如果没有任何商品，就显示暂无任何信息
                if (shangpin.size() == 0) {
                    TextView textView = (TextView) view2.findViewById(R.id.shangpin_no_data);
                    textView.setVisibility(View.VISIBLE);
                }
                view2.setTag("1");
                return new GuanggaoViewHolder(view2);
            case SHANG_PIN:
                View view3 = LayoutInflater.from(mContext).inflate(R.layout.custom_radion_button, parent, false);
                view3.setTag("2");//表示不跳过
                return new GuanggaoViewHolder(view3);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder.itemView.getTag().equals("1")) {
            return;
        }
        int index;
        switch (getItemViewType(position)) {
            case TUI_GUANG:
                index = getRealTuiGuangPosition(position);
                GuanggaoItemBean guanggaoItemBean = tuiguang.get(index);
                ((GuanggaoViewHolder) holder).mImageView_dianji.setImageResource(R.drawable.dianjifenxiang02);
                ((GuanggaoViewHolder) holder).mTextView_jifen.setText(guanggaoItemBean.getScore() + "");
                ((GuanggaoViewHolder) holder).mTextView_yifenxiang.setText(guanggaoItemBean.getHits() + "");
                //ImageLoader缓存加载图片
                ImageLoader.getInstance().displayImage(guanggaoItemBean.getPicture(), ((GuanggaoViewHolder) holder).mImageView_guanggao);
                ((GuanggaoViewHolder) holder).mImageView_dianji.setTag(guanggaoItemBean);//第一个参数传积分值
//                holder.itemView.setTag(1, guanggaoItemBean.getHits());//第二个参数传已分享次数
                //给按钮添加监听事件
                ((GuanggaoViewHolder) holder).mImageView_dianji.setOnClickListener(this);
                break;
            case SHANG_PIN:
                index = getRealShangPinPosition(position);
                GuanggaoItemBean guanggaoItemBean1 = shangpin.get(index);
                ((GuanggaoViewHolder) holder).mImageView_dianji.setImageResource(R.drawable.dianjifenxiang01);
                ((GuanggaoViewHolder) holder).mTextView_jifen.setText(guanggaoItemBean1.getScore() + "");
                ((GuanggaoViewHolder) holder).mTextView_yifenxiang.setText(guanggaoItemBean1.getHits() + "");
                //ImageLoader缓存加载图片
                ImageLoader.getInstance().displayImage(guanggaoItemBean1.getPicture(), ((GuanggaoViewHolder) holder).mImageView_guanggao);
                ((GuanggaoViewHolder) holder).mImageView_dianji.setTag(guanggaoItemBean1);//第一个参数传积分值
//                holder.itemView.setTag(1, guanggaoItemBean1.getHits());//第二个参数传已分享次数
                //给按钮添加监听事件
                ((GuanggaoViewHolder) holder).mImageView_dianji.setOnClickListener(this);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return mDatas.size() + 2;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TUI_GUANG_TOP;
        } else if (position <= tuiguang.size()) {
            return TUI_GUANG;
        } else if (position == tuiguang.size() + 1) {
            return SHANG_PIN_TOP;
        } else {
            return SHANG_PIN;
        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
        if (manager instanceof GridLayoutManager) {
            //如果是网格布局
            final GridLayoutManager gridLayoutManager = (GridLayoutManager) manager;//强转
            //这是关键的方法，该方法决定了item占几个单元格
            gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                //返回值就是单元格数
                @Override
                public int getSpanSize(int position) {
                    if (getItemViewType(position) == TUI_GUANG_TOP || getItemViewType(position) == SHANG_PIN_TOP) {
                        //这种情况下，就占满一行
                        return gridLayoutManager.getSpanCount();
                    } else {
                        //否则就占一个单元格
                        return 1;
                    }
                }
            });
        }
    }

    private int getRealTuiGuangPosition(int position) {
        return position - 1;
    }

    private int getRealShangPinPosition(int position) {
        return position - 2 - tuiguang.size();
    }

    @Override
    public void onClick(View v) {
        GuanggaoItemBean guanggaoItemBean = (GuanggaoItemBean) v.getTag();
        if (guanggaoItemBean != null) {
            Log.e("kkkboy", "guanggao------------->url---->" + guanggaoItemBean.getLink());
            mListener.onGuanggaoSelected(v, guanggaoItemBean.getScore() + "", guanggaoItemBean.getHits() + "", guanggaoItemBean.getId());
        }
    }

    public interface GuanggaoSelected {
        void onGuanggaoSelected(View v, String jifen, String yifenxiang_num, int id);
    }

    private class GuanggaoViewHolder extends RecyclerView.ViewHolder {
        private ImageView mImageView_guanggao;
        private ImageView mImageView_dianji;
        private TextView mTextView_jifen;
        private TextView mTextView_yifenxiang;

        public GuanggaoViewHolder(View itemView) {
            super(itemView);
            if (itemView.getTag().equals("1")) {
                return;
            }
            mImageView_guanggao = (ImageView) itemView.findViewById(R.id.custom_radio_button_image);
            mImageView_dianji = (ImageView) itemView.findViewById(R.id.custom_radio_button_imageview_dianji);
            mTextView_jifen = (TextView) itemView.findViewById(R.id.jifenzhi);
            mTextView_yifenxiang = (TextView) itemView.findViewById(R.id.yifenxiang_num);
        }
    }
}
