package com.example.administrator.yzzf.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.yzzf.Activity.HaoYouZiLiaoActivity;
import com.example.administrator.yzzf.CustomView.Custom_ZuHe_GRZX;
import com.example.administrator.yzzf.R;
import com.example.administrator.yzzf.Util.Show_ZNX_Xiexin_Dialog;

/**
 * Created by Administrator on 2017/3/2 0002.
 */

public class WDHY_WDHY_Fragment extends BaseFragment implements View.OnClickListener {
    Show_ZNX_Xiexin_Dialog mShow_znx_xiexin_dialog = null;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = LayoutInflater.from(mAppCompatActivity).inflate(R.layout.fragment_wdhy_wdhy, container, false);
        Custom_ZuHe_GRZX custom_zuHe_grzx01 = (Custom_ZuHe_GRZX) view.findViewById(R.id.custom_grzx_zuhe01);
        custom_zuHe_grzx01.setOnClickListener(custom_zuHe_grzx01.getButton(), this);
        custom_zuHe_grzx01.setOnClickListener(custom_zuHe_grzx01.getImageView(), this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.custom_grzx_zuhe_image:
                Intent intent01 = new Intent(mAppCompatActivity, HaoYouZiLiaoActivity.class);
                startActivity(intent01);
                break;
            case R.id.custom_grzx_zuhe_button:
                if (mShow_znx_xiexin_dialog == null) {
                    mShow_znx_xiexin_dialog = new Show_ZNX_Xiexin_Dialog(mAppCompatActivity);
                }
                mShow_znx_xiexin_dialog.showDialog(R.layout.custom_znx_xiexin_dialog);
                break;
        }
    }
}
