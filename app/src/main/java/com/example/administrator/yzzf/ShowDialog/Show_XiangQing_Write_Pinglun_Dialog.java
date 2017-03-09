package com.example.administrator.yzzf.ShowDialog;

import android.app.Activity;
import android.view.View;

import com.example.administrator.yzzf.R;

/**
 * Created by Administrator on 2017/2/27 0027.
 */

public class Show_XiangQing_Write_Pinglun_Dialog extends BaseShowDialog implements View.OnClickListener {

    public Show_XiangQing_Write_Pinglun_Dialog(Activity activity) {
        super(activity);
    }

    public void showDialog(int layoutId) {
        super.showDialog(layoutId,0);
        mAlertDialog.findViewById(R.id.xiangqing_pinglun_dialog_quxiao).setOnClickListener(this);
        mAlertDialog.findViewById(R.id.xiangqing_pinglun_dialog_fasong).setOnClickListener(this);
        mAlertDialog.findViewById(R.id.xiangqing_pinglun_dialog_content).setOnClickListener(this);
        mAlertDialog.findViewById(R.id.xiangqing_pinglun_dialog_face).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.xiangqing_pinglun_dialog_quxiao:
                dismiss();
                break;
            case R.id.xiangqing_pinglun_dialog_fasong:
                break;
            case R.id.xiangqing_pinglun_dialog_content:
                break;
            case R.id.xiangqing_pinglun_dialog_face:
                break;
        }
    }
}
