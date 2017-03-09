package com.example.administrator.yzzf.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.administrator.yzzf.R;
import com.example.administrator.yzzf.ShowDialog.Show_ZNX_HUIFU_Dialog;

/**
 * Created by Administrator on 2017/3/2 0002.
 */

public class ZNX_YKG_Fragment extends BaseFragment implements View.OnClickListener {
    private Show_ZNX_HUIFU_Dialog mShowZnx_huifu_dialog = null;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater,container,savedInstanceState);
        View view = LayoutInflater.from(mAppCompatActivity).inflate(R.layout.fragment_znx_ykg, container, false);
        view.findViewById(R.id.znx_duihua01).setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.znx_duihua01:
                if (mShowZnx_huifu_dialog == null) {
                    mShowZnx_huifu_dialog = new Show_ZNX_HUIFU_Dialog(mAppCompatActivity);
                }
                mShowZnx_huifu_dialog.showDialog(R.layout.custom_znx_dialog);
                break;
        }
    }
}
