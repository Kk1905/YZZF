package com.example.administrator.yzzf.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RadioButton;

import com.example.administrator.yzzf.Model.CityModel;
import com.example.administrator.yzzf.Model.DistrictModel;
import com.example.administrator.yzzf.Model.Model;
import com.example.administrator.yzzf.Model.ProvinceModel;
import com.example.administrator.yzzf.R;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/3/25 0025.
 */

public class DialogItemAdapter extends BaseAdapter {
    //    private List<ProvinceModel> mProvinceModelList;
//    private List<CityModel> mCityModelList;
//    private List<DistrictModel> mDistrictModelList;
    private List<Model> mDatas;
    private Context mContext;
    private LayoutInflater mInflater;
    //Map集合用来保存每一个item的选中状态
    private Map<Integer, Boolean> checkedMap;
    private ItemSelectedListener mListener;

    private int flag;
    private static final int SHENG = 0;
    private static final int SHI = 1;
    private static final int QU = 2;

    public DialogItemAdapter(Context context, List<Model> datas, ItemSelectedListener listener, int flag) {
        mListener = listener;
        mContext = context;
        this.flag = flag;
        mInflater = LayoutInflater.from(context);
        mDatas = datas;
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
        if (flag == SHENG) {
            ProvinceModel provinceModel = (ProvinceModel) mDatas.get(position);
            holder.mRadioButton.setText(provinceModel.getName());
        } else if (flag == SHI) {
            CityModel cityModel = (CityModel) mDatas.get(position);
            holder.mRadioButton.setText(cityModel.getName());
        } else if (flag == QU) {
            DistrictModel districtModel = (DistrictModel) mDatas.get(position);
            holder.mRadioButton.setText(districtModel.getName());
        }
        holder.mRadioButton.setChecked(checkedMap.get(position));
        holder.mRadioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearCheckedMap();
                //将当前的设置为选中状态
                checkedMap.put(position, holder.mRadioButton.isChecked());
                //通知Adapter更新ListView
                DialogItemAdapter.this.notifyDataSetChanged();
//                String checkedItem = (String) holder.mRadioButton.getText();
                //通过接口传递被选中的position
                mListener.itemSelected(position);
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
        void itemSelected(int position);
    }
}
