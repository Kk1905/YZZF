package com.example.administrator.yzzf.Activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.administrator.yzzf.Bean.UserMessageBean;
import com.example.administrator.yzzf.CustomView.Custom_WDZL_GRZX;
import com.example.administrator.yzzf.R;
import com.example.administrator.yzzf.Util.Json2NewsUtil;

import java.util.ArrayList;

import static com.sina.weibo.sdk.openapi.legacy.AccountAPI.CAPITAL.H;

/**
 * Created by Administrator on 2017/2/23 0023.
 */

public class WDZLActivity extends AppCompatActivity implements View.OnClickListener {
    private Custom_WDZL_GRZX realname, nickname, sex, displayBirthday, districtId, address, mobile, loginpwd;
    private Handler mHandler;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wdzl);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_wdzl);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowTitleEnabled(false);
        }

        mHandler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                return true;
            }
        });
        initView();
        realname.setOnClickListener(this);

    }

    private void initView() {
        String url = "http://192.168.0.21:8080/Spring/user/checkName";
        realname = (Custom_WDZL_GRZX) findViewById(R.id.custom_wdzl_xingming);
        nickname = (Custom_WDZL_GRZX) findViewById(R.id.custom_wdzl_nicheng);
        sex = (Custom_WDZL_GRZX) findViewById(R.id.custom_wdzl_xingbie);
        displayBirthday = (Custom_WDZL_GRZX) findViewById(R.id.custom_wdzl_year);
        districtId = (Custom_WDZL_GRZX) findViewById(R.id.custom_wdzl_city);
        address = (Custom_WDZL_GRZX) findViewById(R.id.custom_wdzl_zhuzhi);
        mobile = (Custom_WDZL_GRZX) findViewById(R.id.custom_wdzl_xiugaishouji);
        loginpwd = (Custom_WDZL_GRZX) findViewById(R.id.custom_wdzl_mimafuwu);
        try {
//            ArrayList userMessages = Json2NewsUtil.getUserMessage(this, url);
            ArrayList userMessages = Json2NewsUtil.test02(this);
            UserMessageBean userMessage = (UserMessageBean) userMessages.get(0);
            Log.e("kk", "------------------------------>" + userMessages.size());
            Log.e("kk", "------------------------------>" + userMessage.toString());

            realname.setMessage(userMessage.getRealName());
            nickname.setMessage(userMessage.getNickName());
            sex.setMessage(userMessage.getSex());
            displayBirthday.setMessage(userMessage.getDiplayBirthday());
            districtId.setMessage(userMessage.getDistrictId() + "");
            address.setMessage(userMessage.getAddress());
            mobile.setMessage(userMessage.getMobile());
            loginpwd.setMessage(userMessage.getLoginPWD());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.custom_wdzl_xingming:
                realname.setMessage("李可可");
                break;
        }
    }
}
