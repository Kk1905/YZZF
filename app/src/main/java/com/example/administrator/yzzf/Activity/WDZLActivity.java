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
import com.example.administrator.yzzf.Dao.UserMessageDao;
import com.example.administrator.yzzf.R;
import com.example.administrator.yzzf.Util.Json2NewsUtil;
import com.example.administrator.yzzf.Util.NetUtil;

import java.util.ArrayList;


/**
 * Created by Administrator on 2017/2/23 0023.
 */

public class WDZLActivity extends AppCompatActivity implements View.OnClickListener {
    private static int NO_NEW_WORK = 0;
    private static int ERROR_SERVICE = 1;
    private boolean hasNetwork;
    private boolean isDateFromNetwork;
    private UserMessageDao mUserMessageDao;
    private Custom_WDZL_GRZX realname, nickname, sex, displayBirthday, districtId, address, mobile, loginpwd;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            UserMessageBean userMessage = (UserMessageBean) msg.obj;
            initView(userMessage);
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
        mUserMessageDao = new UserMessageDao(this);
        realname = (Custom_WDZL_GRZX) findViewById(R.id.custom_wdzl_xingming);
        nickname = (Custom_WDZL_GRZX) findViewById(R.id.custom_wdzl_nicheng);
        sex = (Custom_WDZL_GRZX) findViewById(R.id.custom_wdzl_xingbie);
        displayBirthday = (Custom_WDZL_GRZX) findViewById(R.id.custom_wdzl_year);
        districtId = (Custom_WDZL_GRZX) findViewById(R.id.custom_wdzl_city);
        address = (Custom_WDZL_GRZX) findViewById(R.id.custom_wdzl_zhuzhi);
        mobile = (Custom_WDZL_GRZX) findViewById(R.id.custom_wdzl_xiugaishouji);
        loginpwd = (Custom_WDZL_GRZX) findViewById(R.id.custom_wdzl_mimafuwu);
        refreshData();
        realname.setFocusable(true);
        realname.isClickable();
        realname.setOnClickListener(this);

    }

    private void initView(UserMessageBean userMessage) {
        realname.setMessage(userMessage.getRealName());
        nickname.setMessage(userMessage.getNickName());
        sex.setMessage(userMessage.getSex());
        displayBirthday.setMessage(userMessage.getDisplayBirthday());
        districtId.setMessage(userMessage.getDistrictId() + "");
        address.setMessage(userMessage.getAddress());
        mobile.setMessage(userMessage.getMobile());
        loginpwd.setMessage(userMessage.getLoginPWD());
    }

    private void refreshData() {
        if (NetUtil.checkNet(this)) {
            //有网络的情况
            hasNetwork = true;
            isDateFromNetwork = true;
            String url = "http://192.168.0.21:8080/Spring/user/checkName";
            ArrayList<UserMessageBean> userMessages;
            Message message = new Message();
            try {
                userMessages = Json2NewsUtil.getUserMessages(this, url);
                UserMessageBean userMessage = userMessages.get(0);
                Log.e("kkkboy", userMessage.toString());
                message.obj = userMessage;
                mHandler.sendMessage(message);
                //清除手机上的旧数据
                mUserMessageDao.removeAll(userMessage.getRealName());
                //保存新的数据到手机
                mUserMessageDao.addUserMessage(userMessage);
                Toast.makeText(this, "更新数据成功", Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                e.printStackTrace();
                isDateFromNetwork = false;
                Toast.makeText(this, "服务器错误", Toast.LENGTH_SHORT).show();
            }
        } else {
            //没有网络的情况，从数据库取数据
            hasNetwork = false;
            isDateFromNetwork = false;
            UserMessageBean userMessage = mUserMessageDao.getUserMessage("真实姓名1");
            if (userMessage != null) {
                initView(userMessage);
                districtId.setMessage("没有网络的城市");
                Toast.makeText(this, "没有网络连接,可能不是最新数据", Toast.LENGTH_SHORT).show();
            } else {
                try {
                    throw new Exception("您是首次登陆，必须要连接至网络");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }
    }

//    //执行初始化的AsyncTask
//    private class initTask extends AsyncTask<Void, Void, Integer> {
//        @Override
//        protected Integer doInBackground(Void... params) {
//            return initView();
//        }
//
//        @Override
//        protected void onPostExecute(Integer integer) {
//            super.onPostExecute(integer);
//        }
//    }

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
