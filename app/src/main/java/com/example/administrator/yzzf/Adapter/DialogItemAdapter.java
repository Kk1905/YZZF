package com.example.administrator.yzzf.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RadioButton;

import com.example.administrator.yzzf.Model.ProvinceModel;
import com.example.administrator.yzzf.R;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/3/25 0025.
 */

public class DialogItemAdapter extends BaseAdapter {
    private List<ProvinceModel> mDatas;
    private Context mContext;
    private LayoutInflater mInflater;
    //Map集合用来保存每一个item的选中状态
    private Map<Integer, Boolean> checkedMap;
    private ItemSelectedListener mListener;

    public DialogItemAdapter(Context context, List<ProvinceModel> datas, ItemSelectedListener listener) {
        mListener = listener;
        mContext = context;
        mDatas = datas;
        mInflater = LayoutInflater.from(context);
        initCheckedMap();
    }

    @Override
    public int getCount() {
        return mDatas.size();
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.custom_dialog_item, null);
            holder.mRadioButton = (RadioButton) convertView.findViewById(R.id.custom_dialog_item_radiobutton);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        ProvinceModel provinceModel = mDatas.get(position);
        holder.mRadioButton.setText(provinceModel.getName());
        holder.mRadioButton.setChecked(checkedMap.get(position));
        holder.mRadioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearCheckedMap();
                //将当前的设置为选中状态
                checkedMap.put(position, holder.mRadioButton.isChecked());
                //通知Adapter更新ListView
                DialogItemAdapter.this.notifyDataSetChanged();
                String checkedItem = (String) holder.mRadioButton.getText();
                //通过接口传递被选中的字符串
                mListener.itemSelected(checkedItem);
            }
        });
        return convertView;
    }

    private void clearCheckedMap() {
        //重置Map集合，都为false
        for (Integer key : checkedMap.keySet()) {
            checkedMap.put(key, false);
        }
    }

    //初始化checkedMap
    private void initCheckedMap() {
        checkedMap = new HashMap<>();
        for (int i = 0; i < getCount(); i++) {
            checkedMap.put(i, false);
        }
    }


    private class ViewHolder {
        RadioButton mRadioButton;
    }

    //跟Dialog交互的接口
    public interface ItemSelectedListener {
        void itemSelected(String string);
    }
}
