package com.example.administrator.yzzf.Tencent;

import android.content.Context;
import android.widget.Toast;

import com.tencent.tauth.IUiListener;
import com.tencent.tauth.UiError;

import static com.sina.weibo.sdk.openapi.legacy.CommonAPI.CAPITAL.o;

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
        Toast.makeText(mContext, "onComplete: "+o.toString(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onError(UiError uiError) {
        //分享异常
        Toast.makeText(mContext, "onError:  "+uiError.errorMessage, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCancel() {
        //取消分享

    }
}
