package com.example.administrator.yzzf.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.yzzf.Bean.WdddItemBean;
import com.example.administrator.yzzf.Fragment.BaseFragment;
import com.example.administrator.yzzf.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

import java.util.ArrayList;
import java.util.List;

import static com.example.administrator.yzzf.R.id.item_wodedingdan_danjia;
import static com.example.administrator.yzzf.R.id.item_wodedingdan_shuliang;

/**
 * Created by Administrator on 2017/3/21 0021.
 */

public class WdddItemAdapter extends BaseAdapter implements View.OnClickListener {
    private List<WdddItemBean> mDatas;
    private Context mContext;
    private LayoutInflater inflater;
    private ImageLoader mImageLoader;
    private DisplayImageOptions mOptions;
    private OnViewChangeListener mOnViewChangeListener;

    public WdddItemAdapter(Context context, List<WdddItemBean> datas, OnViewChangeListener onViewChangeListener) {
        mContext = context;
        mDatas = datas;
        mOnViewChangeListener = onViewChangeListener;
        inflater = LayoutInflater.from(mContext);
        mImageLoader = ImageLoader.getInstance();
        mOptions = new DisplayImageOptions.Builder().showStubImage(R.drawable.zhuye_kongbai)
                .showImageForEmptyUri(R.drawable.zhuye_kongbai).showImageOnFail(R.drawable.zhuye_kongbai).cacheInMemory()
                .cacheOnDisc().displayer(new RoundedBitmapDisplayer(20)).displayer(new FadeInBitmapDisplayer(300))
                .build();
    }

    //添加一条商品信息
    public void addItem(WdddItemBean itemBean) {
        mDatas.add(itemBean);
    }

    //删除一条商品信息
    public void removeItem(int position) {
        mDatas.remove(position);
    }

    //不删除原来的，再添加一串商品信息
    public void addAll(ArrayList<WdddItemBean> datas) {
        mDatas.addAll(datas);
    }

    //删除原来的，添加新的一串商品信息
    public void addNew(ArrayList<WdddItemBean> datas) {
        mDatas.clear();
        mDatas.addAll(datas);
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
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.item_wodedingdan, null);
            holder.imageView = (ImageView) convertView.findViewById(R.id.item_wodedingdan_image_view);
            holder.title = (TextView) convertView.findViewById(R.id.item_wodedingdan_title);
            holder.danjia = (TextView) convertView.findViewById(item_wodedingdan_danjia);
            holder.shuliang = (TextView) convertView.findViewById(item_wodedingdan_shuliang);
            holder.heji = (TextView) convertView.findViewById(R.id.item_wodedingdan_heji);
            holder.imageButton = (ImageView) convertView.findViewById(R.id.item_wodedingdan_image_button);
            holder.quxiao = (TextView) convertView.findViewById(R.id.item_wodedingdan_quxiao);

            holder.imageButton.setOnClickListener(this);
            holder.quxiao.setOnClickListener(this);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        WdddItemBean wdddItemBean = mDatas.get(position);
        holder.title.setText(wdddItemBean.getTitle());
        holder.danjia.setText("¥" + wdddItemBean.getDanjia());
        holder.shuliang.setText("订单数量：" + wdddItemBean.getShuliang());
        holder.heji.setText("合计：" + wdddItemBean.getShuliang());
        switch (wdddItemBean.getFukuanState()) {
            case "0":
                holder.imageButton.setImageDrawable(mContext.getResources().getDrawable(R.drawable.wddd_qufukuan));
                holder.quxiao.setText(R.string.quxiaodingdan);
                break;
            case "1":
                holder.imageButton.setImageDrawable(mContext.getResources().getDrawable(R.drawable.wddd_yifukuan));
                holder.quxiao.setText(R.string.shenqingtuikuan);
                break;
            case "2":
                holder.imageButton.setImageDrawable(mContext.getResources().getDrawable(R.drawable.wddd_daishouhuo));
                holder.quxiao.setText(R.string.shenqingtuikuan);
                break;
            case "3":
                holder.imageButton.setImageDrawable(mContext.getResources().getDrawable(R.drawable.wddd_yiwancheng));
                holder.quxiao.setText(R.string.shanchu);
                break;
            default:
                break;
        }
        if (wdddItemBean.getPicture() != null) {
            holder.imageView.setVisibility(View.VISIBLE);
            mImageLoader.displayImage(wdddItemBean.getPicture(), holder.imageView, mOptions);
            return null;
        } else {
            holder.imageView.setVisibility(View.GONE);
        }
        return convertView;
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public void onClick(View v) {
        mOnViewChangeListener.setOnViewChange(v);
    }

    private class ViewHolder {
        ImageView imageView;
        TextView title;
        TextView danjia;
        TextView shuliang;
        TextView heji;
        ImageView imageButton;
        TextView quxiao;
    }

    public interface OnViewChangeListener {
        void setOnViewChange(View v);
    }
}
