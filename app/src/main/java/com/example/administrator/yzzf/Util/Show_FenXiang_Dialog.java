package com.example.administrator.yzzf.Util;

import android.app.Activity;
import android.view.View;

import com.example.administrator.yzzf.BaseShowDialog;
import com.example.administrator.yzzf.R;

/**
 * Created by Administrator on 2017/2/27 0027.
 */

public class Show_FenXiang_Dialog extends BaseShowDialog implements View.OnClickListener {
    public Show_FenXiang_Dialog(Activity activity) {
        super(activity);
    }

    public void showDialog(int layoutId) {
        super.showDialog(layoutId);
        mAlertDialog.findViewById(R.id.xiangqing_dialog_weixin_pengyouquan).setOnClickListener(this);
        mAlertDialog.findViewById(R.id.xiangqing_dialog_weixin).setOnClickListener(this);
        mAlertDialog.findViewById(R.id.xiangqing_dialog_qq).setOnClickListener(this);
        mAlertDialog.findViewById(R.id.xiangqing_dialog_weixin_pengyouquan).setOnClickListener(this);
        mAlertDialog.findViewById(R.id.xiangqing_dialog_weibo).setOnClickListener(this);
        mAlertDialog.findViewById(R.id.xiangqing_dialog_shuaxin).setOnClickListener(this);
        mAlertDialog.findViewById(R.id.xiangqing_dialog_fuzhilianjie).setOnClickListener(this);
        mAlertDialog.findViewById(R.id.xiangqing_dialog_jubao).setOnClickListener(this);
        mAlertDialog.findViewById(R.id.xiangqing_dialog_quxiao).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.xiangqing_dialog_quxiao:
                dismiss();
                break;
            case R.id.xiangqing_dialog_weixin_pengyouquan:

                break;
            case R.id.xiangqing_dialog_weixin:
                break;
            case R.id.xiangqing_dialog_qq:
                break;
            case R.id.xiangqing_dialog_qqkongjian:
                break;
            case R.id.xiangqing_dialog_weibo:
                break;
            case R.id.xiangqing_dialog_shuaxin:
                break;
            case R.id.xiangqing_dialog_fuzhilianjie:
                break;
            case R.id.xiangqing_dialog_jubao:
                break;

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
