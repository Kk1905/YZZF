package com.example.administrator.yzzf.Util;

import android.app.Activity;
import android.view.View;


import com.example.administrator.yzzf.BaseShowDialog;
import com.example.administrator.yzzf.R;

/**
 * Created by Administrator on 2017/2/27 0027.
 */

public class Show_ZNX_HUIFU_Dialog extends BaseShowDialog implements View.OnClickListener {

    public Show_ZNX_HUIFU_Dialog(Activity activity) {
        super(activity);
    }

    public void showDialog(int layoutId) {

        super.showDialog(layoutId,1);
        mAlertDialog.findViewById(R.id.xnx_dialog_huifu).setOnClickListener(this);
        mAlertDialog.findViewById(R.id.xnx_dialog_shanchu).setOnClickListener(this);
        mAlertDialog.findViewById(R.id.xnx_dialog_chakan).setOnClickListener(this);
        mAlertDialog.findViewById(R.id.xnx_dialog_quxiao).setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.xnx_dialog_huifu:
                break;
            case R.id.xnx_dialog_shanchu:
                break;
            case R.id.xnx_dialog_chakan:
                break;
            case R.id.xnx_dialog_quxiao:
                dismiss();
                break;
        }
    }
}
