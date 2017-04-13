package com.example.administrator.yzzf.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.yzzf.Bean.PinglunItemBean;
import com.example.administrator.yzzf.R;

import java.util.ArrayList;
import java.util.List;

import static com.sina.weibo.sdk.openapi.legacy.CommonAPI.CAPITAL.p;

/**
 * Created by Administrator on 2017/4/9 0009.
 */

public class AllPinglunItemAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private List<PinglunItemBean> mDatas = new ArrayList<>();
//    private boolean hasDatas;

    public AllPinglunItemAdapter(Context context, List<PinglunItemBean> datas) {
        mContext = context;
        mDatas = datas;

    }

    public void addAll(List<PinglunItemBean> datas) {
        if (mDatas != null && datas.size() > 0) {
            int start = mDatas.size();
            mDatas.addAll(datas);
//            hasDatas = true;
            notifyItemRangeChanged(start, datas.size());//提高效率
        }
    }

    public void replace(List<PinglunItemBean> datas) {
        if (mDatas != null) {
            mDatas.clear();
            mDatas.addAll(datas);
//            hasDatas = mDatas.size() > 0 ? true : false;
            notifyDataSetChanged();
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.custom_item_rmpl, parent, false);
//            Log.e("kkkboy", "评论的个数----在adapter---------02-----" + mDatas.size() + "");
        return new PinglunViewHolder(view);

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
//            Log.e("kkkboy", "--------02--------->" + position);
        PinglunItemBean pinglunItemBean = mDatas.get(position);
        ((PinglunViewHolder) holder).mTextView_username.setText(pinglunItemBean.getReplyname());
        ((PinglunViewHolder) holder).mTextView_hourago.setText(pinglunItemBean.getHourAgo());
        ((PinglunViewHolder) holder).mTextView_content.setText(pinglunItemBean.getContent());
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    private class PinglunViewHolder extends RecyclerView.ViewHolder {
        private TextView mTextView_username;
        private TextView mTextView_hourago;
        private TextView mTextView_content;


        public PinglunViewHolder(View itemView) {
            super(itemView);
            if (itemView.getTag() != null) {
                return;
            }
            mTextView_username = (TextView) itemView.findViewById(R.id.custom_rmpl_username);
            mTextView_hourago = (TextView) itemView.findViewById(R.id.custom_rmpl_time01);
            mTextView_content = (TextView) itemView.findViewById(R.id.custom_rmpl_content);
        }
    }
}
