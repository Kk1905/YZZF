package com.example.administrator.yzzf.Tencent;

import com.tencent.tauth.IUiListener;
import com.tencent.tauth.UiError;

/**
 * Created by Administrator on 2017/3/6 0006.
 */

public class BaseIUIListener implements IUiListener {
    @Override
    public void onComplete(Object o) {
        //操作成功

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
