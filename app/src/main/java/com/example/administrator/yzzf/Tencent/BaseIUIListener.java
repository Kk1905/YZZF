package com.example.administrator.yzzf.Tencent;

import android.content.Context;
import android.widget.Toast;

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
        Toast.makeText(mContext, "分享完成", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onError(UiError uiError) {
        //分享异常
    }

    @Override
    public void onCancel() {
        //取消分享
    }
}
