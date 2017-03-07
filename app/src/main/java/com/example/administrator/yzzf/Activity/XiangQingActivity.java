package com.example.administrator.yzzf.Activity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.yzzf.CustomView.Custom_RMPL_XiangQing;
import com.example.administrator.yzzf.Fragment.XiangQing_RMPL_Fragment;
import com.example.administrator.yzzf.R;
import com.example.administrator.yzzf.Tencent.BaseIUIListener;
import com.example.administrator.yzzf.Tencent.TencentShareManager;
import com.example.administrator.yzzf.Util.AndroidBug5497Workaround;
import com.example.administrator.yzzf.Util.Show_FenXiang_Dialog;
import com.example.administrator.yzzf.Util.Show_XiangQing_Write_Pinglun_Dialog;
import com.example.administrator.yzzf.WeChat.WeChatShareManager;
import com.example.administrator.yzzf.WeiBo.WeiBoShareManager;
import com.sina.weibo.sdk.api.share.BaseResponse;
import com.sina.weibo.sdk.api.share.IWeiboHandler;
import com.sina.weibo.sdk.api.share.IWeiboShareAPI;
import com.sina.weibo.sdk.constant.WBConstants;
import com.tencent.connect.common.Constants;
import com.tencent.connect.share.QQShare;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.tauth.Tencent;

import static android.R.attr.fragment;

/**
 * Created by Administrator on 2017/2/19 0019.
 */

public class XiangQingActivity extends AppCompatActivity implements View.OnClickListener, IWeiboHandler.Response {

    private BaseIUIListener mBaseIUIListener;
    private Show_FenXiang_Dialog mShow_fenXiang_dialog = null;
    private Show_XiangQing_Write_Pinglun_Dialog mShow_xiangQing_write_pinglun_dialog = null;
    private EditText editText;
    private Fragment fragment;
    private FragmentManager fm;
    private IWXAPI mIWXAPI;
    private IWeiboShareAPI mIWeiboShareAPI;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xiangqing);

        fm = getSupportFragmentManager();
        fragment = fm.findFragmentById(R.id.xiangqing_fragment_container);

        initView();
        editText = (EditText) findViewById(R.id.woyao_edittext);
        editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    if (mShow_xiangQing_write_pinglun_dialog == null) {
                        mShow_xiangQing_write_pinglun_dialog =
                                new Show_XiangQing_Write_Pinglun_Dialog(XiangQingActivity.this);
                    }
                    mShow_xiangQing_write_pinglun_dialog.showDialog(R.layout.custom_xiangqing_pinglun_dialog);
                    editText.clearFocus();
                }
            }
        });
        mIWXAPI = WeChatShareManager.getInstance(this).getIWXAPI();
        mIWeiboShareAPI = WeiBoShareManager.getWeiBoShareManager(this).getIWeiboShareAPI();
        if (savedInstanceState != null) {
            //保存微博返回的数据，如果操作成功，返回true，会回调onResponse方法
            mIWeiboShareAPI.handleWeiboResponse(getIntent(), this);
        }
        findViewById(R.id.pinglun_fenxiang).setOnClickListener(this);
        findViewById(R.id.xiangqing_rootview).setOnClickListener(this);

        mBaseIUIListener = TencentShareManager.getTencentShareManager(this).getBaseIUIListener();
    }

    private void initView() {
        if (fragment == null) {
            fragment = new XiangQing_RMPL_Fragment();
            fm.beginTransaction()
                    .add(R.id.xiangqing_fragment_container, fragment)
                    .commit();
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
//            case R.id.xiangqing_rootview:
//                InputMethodManager imm = (InputMethodManager)
//                        getSystemService(Context.INPUT_METHOD_SERVICE);
//                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
//                break;
            case R.id.pinglun_fenxiang:
                if (mShow_fenXiang_dialog == null) {
                    mShow_fenXiang_dialog = new Show_FenXiang_Dialog(XiangQingActivity.this);
                }
                mShow_fenXiang_dialog.showDialog(R.layout.custom_xiangqing_fenxiang_dialog);
                break;

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //QQ分享的返回响应处理,下面两种实现方法差不多
        Tencent.onActivityResultData(requestCode, requestCode, data, mBaseIUIListener);
//        if (resultCode == Constants.REQUEST_QQ_SHARE || resultCode == Constants.REQUEST_QZONE_SHARE ||
//                resultCode == Constants.REQUEST_OLD_SHARE) {
//            Tencent.handleResultData(data, mBaseIUIListener);
//        }


    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        setIntent(intent);
        mIWeiboShareAPI.handleWeiboResponse(intent, this);//触发微博的响应的回调
    }


    @Override
    public void onResponse(BaseResponse baseResponse) {
        //返回微博的响应
        switch (baseResponse.errCode) {
            case WBConstants.ErrorCode.ERR_OK:
                Toast.makeText(this, R.string.weibosdk_toast_share_success, Toast.LENGTH_LONG).show();
                break;
            case WBConstants.ErrorCode.ERR_CANCEL:
                Toast.makeText(this, R.string.weibosdk_toast_share_canceled, Toast.LENGTH_LONG).show();
                break;
            case WBConstants.ErrorCode.ERR_FAIL:
                Toast.makeText(this, getString(R.string.weibosdk_toast_share_failed) + " failMessage:" + baseResponse.errMsg, Toast.LENGTH_LONG).show();
                break;
        }
    }
}
