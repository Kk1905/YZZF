package com.example.administrator.yzzf;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import com.example.administrator.yzzf.Util.Custom_Dialog;

/**
 * Created by Administrator on 2017/2/27 0027.
 */

public class BaseShowDialog {
    protected Custom_Dialog mAlertDialog;
    protected Activity mActivity;
    private static final int EDIT_TEXT_DIALOG = 0;
    private static final int NORMAL_TEXT_DIALOG = 1;

    public BaseShowDialog(Activity activity) {
        mActivity = activity;
    }

    public void showDialog(int layoutId, int witchDialog) {
        View view = LayoutInflater.from(mActivity).inflate(layoutId, null);
        if (mAlertDialog == null) {
            switch (witchDialog) {
                case EDIT_TEXT_DIALOG:
                    mAlertDialog = new Custom_Dialog(mActivity, R.style.custom_dialog_editText);
                    mAlertDialog.setCanceledOnTouchOutside(true);
                    mAlertDialog.setContentView(view);
                    break;
                case NORMAL_TEXT_DIALOG:
                    mAlertDialog = new Custom_Dialog(mActivity, R.style.custom_dialog_normal);
                    mAlertDialog.setCanceledOnTouchOutside(true);
                    mAlertDialog.setContentView(view);
            }
        }
        mAlertDialog.show();
    }

    public void dismiss() {
        if (mAlertDialog != null && mAlertDialog.isShowing()) {
            mAlertDialog.dismiss();
        }
    }
}
