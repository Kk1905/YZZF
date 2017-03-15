package com.example.administrator.yzzf.Activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import com.example.administrator.yzzf.Bean.UserMessageBean;
import com.example.administrator.yzzf.CustomView.Custom_WDZL_GRZX;
import com.example.administrator.yzzf.R;
import com.example.administrator.yzzf.Util.Json2NewsUtil;

import java.util.ArrayList;


/**
 * Created by Administrator on 2017/2/23 0023.
 */

public class WDZLActivity extends AppCompatActivity implements View.OnClickListener {
    private Custom_WDZL_GRZX realname, nickname, sex, displayBirthday, districtId, address, mobile, loginpwd;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            UserMessageBean userMessage = (UserMessageBean) msg.obj;
            realname.setMessage(userMessage.getRealName());
            nickname.setMessage(userMessage.getNickName());
            sex.setMessage(userMessage.getSex());
            displayBirthday.setMessage(userMessage.getDiplayBirthday());
            districtId.setMessage(userMessage.getDistrictId() + "");
            address.setMessage(userMessage.getAddress());
            mobile.setMessage(userMessage.getMobile());
            loginpwd.setMessage(userMessage.getLoginPWD());
        }
    };

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
        realname = (Custom_WDZL_GRZX) findViewById(R.id.custom_wdzl_xingming);
        nickname = (Custom_WDZL_GRZX) findViewById(R.id.custom_wdzl_nicheng);
        sex = (Custom_WDZL_GRZX) findViewById(R.id.custom_wdzl_xingbie);
        displayBirthday = (Custom_WDZL_GRZX) findViewById(R.id.custom_wdzl_year);
        districtId = (Custom_WDZL_GRZX) findViewById(R.id.custom_wdzl_city);
        address = (Custom_WDZL_GRZX) findViewById(R.id.custom_wdzl_zhuzhi);
        mobile = (Custom_WDZL_GRZX) findViewById(R.id.custom_wdzl_xiugaishouji);
        loginpwd = (Custom_WDZL_GRZX) findViewById(R.id.custom_wdzl_mimafuwu);
        initView();
        realname.setFocusable(true);
        realname.isClickable();
        realname.setOnClickListener(this);

    }

    private void initView() {
        String url = "http://192.168.0.21:8080/Spring/user/checkName";
        ArrayList<UserMessageBean> userMessages;
        userMessages = Json2NewsUtil.getUserMessages(this, url);
        if (userMessages.size() == 2) {
            UserMessageBean userMessage = userMessages.get(1);
            Message message = new Message();
            message.obj = userMessage;
            mHandler.sendMessage(message);
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
                break;
        }
    }
}
