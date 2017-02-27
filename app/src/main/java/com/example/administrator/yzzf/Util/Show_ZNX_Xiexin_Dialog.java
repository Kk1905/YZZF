package com.example.administrator.yzzf.Util;

import android.app.Activity;
import android.view.View;

import com.example.administrator.yzzf.BaseShowDialog;
import com.example.administrator.yzzf.R;

/**
 * Created by Administrator on 2017/2/27 0027.
 */

public class Show_ZNX_Xiexin_Dialog extends BaseShowDialog implements View.OnClickListener {

    public Show_ZNX_Xiexin_Dialog(Activity activity) {
        super(activity);
    }
    public void showDialog(int layoutId) {
        super.showDialog(layoutId);
        mAlertDialog.findViewById(R.id.znx_xiexin_dialog_content).setOnClickListener(this);
        mAlertDialog.findViewById(R.id.znx_xiexin_dialog_quxiao).setOnClickListener(this);
        mAlertDialog.findViewById(R.id.znx_xiexin_dialog_fasong).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.znx_xiexin_dialog_content:
                break;
            case R.id.znx_xiexin_dialog_quxiao:
                dismiss();
                break;
            case R.id.znx_xiexin_dialog_fasong:
                break;
        }
    }
}
