package com.example.administrator.yzzf.wxapi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Toast;


import com.example.administrator.yzzf.R;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;


/**
 * Created by Administrator on 2017/3/7 0007.
 */

public class WXEntryActivity extends Activity implements IWXAPIEventHandler {
    private IWXAPI mIWXAPI;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mIWXAPI = WXAPIFactory.createWXAPI(this, "wxa9114b008700020e");
        mIWXAPI.handleIntent(getIntent(), this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        mIWXAPI.handleIntent(intent, this);//触发微信的请求与响应的回调
    }

    @Override
    public void onReq(BaseReq baseReq) {
        //微信向你发送请求时会回调（ps:你这个屌丝，人家微信向你发送毛请求啊，暂时就不要写了,而且还要一个入口activity，界面布局都不知道）

    }

    @Override
    public void onResp(BaseResp baseResp) {
        //返回你向微信发送请求后，微信的响应
        int result = 0;

        switch (baseResp.errCode) {
            case BaseResp.ErrCode.ERR_OK:
                result = R.string.wxsdk_toast_share_success;
                break;
            case BaseResp.ErrCode.ERR_USER_CANCEL:
                result = R.string.wxsdk_toast_share_canceled;
                break;
            case BaseResp.ErrCode.ERR_AUTH_DENIED:
                result = R.string.weibosdk_toast_share_failed;
                break;
        }

        Toast.makeText(this, result, Toast.LENGTH_LONG).show();
        finish();
    }
}
