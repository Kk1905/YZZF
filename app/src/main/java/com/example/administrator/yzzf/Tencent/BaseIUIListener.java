package com.example.administrator.yzzf.Tencent;

import android.content.Context;
import android.widget.Toast;

import com.example.administrator.yzzf.R;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.UiError;


/**
 * Created by Administrator on 2017/3/6 0006.
 */

public class BaseIUIListener implements IUiListener {
    Context mContext;

    public BaseIUIListener(Context context) {
        super();
        mContext = context;
    }

    @Override
    public void onComplete(Object o) {
        //操作成功
        Toast.makeText(mContext, R.string.qqsdk_toast_share_success, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onError(UiError uiError) {
        //分享异常
        Toast.makeText(mContext, R.string.qqsdk_toast_share_failed + uiError.errorMessage, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCancel() {
        //取消分享
        Toast.makeText(mContext, R.string.qqsdk_toast_share_canceled, Toast.LENGTH_SHORT).show();
    }
}
