package com.example.administrator.yzzf.Activity;


import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.yzzf.Bean.UserMessageBean;
import com.example.administrator.yzzf.CustomView.Custom_WDZL_GRZX;
import com.example.administrator.yzzf.Dao.UserMessageDao;
import com.example.administrator.yzzf.Fragment.WDZL_BJ_Fragment;
import com.example.administrator.yzzf.Fragment.WDZL_WC_Fragment;
import com.example.administrator.yzzf.R;
import com.example.administrator.yzzf.Util.ActivityStack;
import com.example.administrator.yzzf.Util.Constant;
import com.example.administrator.yzzf.Util.FragmentFactory;
import com.example.administrator.yzzf.Util.Json2NewsUtil;
import com.example.administrator.yzzf.Util.MyApplication;
import com.example.administrator.yzzf.Util.NetUtil;

import java.util.ArrayList;

import static android.R.attr.fragment;
import static android.R.id.message;
import static com.sina.weibo.sdk.openapi.legacy.AccountAPI.CAPITAL.A;
import static com.sina.weibo.sdk.openapi.legacy.CommonAPI.CAPITAL.t;


/**
 * Created by Administrator on 2017/2/23 0023.
 */

public class WDZLActivity extends AppCompatActivity implements View.OnClickListener {

    private UserMessageDao mUserMessageDao;
    private FrameLayout mFrameLayout;
    private LinearLayout ll_up;
    private TextView bianji_textview;
    private int flag;//0表示已完成的，1表示编辑中的
    private int userId;//用户id，要传给BJ页面提交修改资料

    private String name;
    private String nickname;
    private String sex;
    private String address;
    private ReplaceFragment mCallback;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wdzl);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_wdzl);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowTitleEnabled(false);
        }

        TextView title_textview = (TextView) findViewById(R.id.toolbar_wdzl_title);
        title_textview.setText(R.string.wdzl);
        ll_up = (LinearLayout) findViewById(R.id.toolbar_wdzl_up);
        ll_up.setOnClickListener(this);
        bianji_textview = (TextView) findViewById(R.id.toolbar_wdzl_bianji);
        bianji_textview.setOnClickListener(this);
        mUserMessageDao = new UserMessageDao(this);
        userId = getIntent().getIntExtra("userId", -1);//如果userId为-1，代表没有此用户，说明还没有登录
        initView();

        ActivityStack.getInstance().addActivity(this);
    }

    private void initView() {
        flag = 0;
        bianji_textview.setText(R.string.grzx_bianji);

        //进入activity，第一次获取网络数据
        String url = Constant.USER_ZL + "?userId=" + userId;
//        Log.e("kkkboy", url + "");
        UserMessageBean userMessage = new UserMessageBean();
        try {
            userMessage = Json2NewsUtil.getUserMessages(this, url);
//                Log.e("kkkboy", userMessage.toString());
            name = userMessage.getRealName();
            nickname = userMessage.getNickName();
            sex = userMessage.getSex();
            address = userMessage.getAddress();

        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "服务器错误", Toast.LENGTH_SHORT).show();
        }

        mFrameLayout = (FrameLayout) findViewById(R.id.wdzl_fragment_container);
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.wdzl_fragment_container);
        if (fragment == null) {
            fragment = new WDZL_WC_Fragment();

//            name = getIntent().getStringExtra("realname");
//            nickname = getIntent().getStringExtra("nickname");
//            sex = getIntent().getStringExtra("sex");
//            address = getIntent().getStringExtra("address");

//            Log.e("kkkboy", name + "");
            Bundle bundle = new Bundle();
            bundle.putString("name", name);//姓名
            bundle.putString("nickname", nickname);//昵称
            bundle.putString("sex", sex);//性别
            bundle.putString("address", address);//住址
            fragment.setArguments(bundle);
            FragmentFactory.addFragment(WDZLActivity.this, fragment, R.id.wdzl_fragment_container);
        }
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.toolbar_wdzl_bianji:
                if (flag == 0) {
                    //代表当前在已完成页面，要跳转到编辑页面
                    bianji_textview.setText(R.string.baocun);
                    Fragment fragment = new WDZL_BJ_Fragment();

                    //WDZL_BJ_Fragment必须要实现ReplaceFragment接口
                    if (fragment instanceof ReplaceFragment) {
                        mCallback = (ReplaceFragment) fragment;//给callBack赋值
                    } else {
                        try {
                            throw new Exception("WDZL_WC_Fragment must instanceof ReplaceFragment");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    Bundle bundle = new Bundle();
                    bundle.putString("name", name);//姓名
                    bundle.putString("nickname", nickname);//昵称
                    bundle.putString("sex", sex);//性别
                    bundle.putString("address", address);//住址
                    bundle.putInt("userId", userId);
                    fragment.setArguments(bundle);

                    flag = (flag + 1) % 2;//每点击一次切换一次
                    FragmentFactory.replaceFragment(WDZLActivity.this, fragment, R.id.wdzl_fragment_container);
                } else {
                    //代表当前在编辑页面，要跳转到已完成页面

                    AlertDialog dialog = new AlertDialog.Builder(WDZLActivity.this)
                            .setCancelable(true)
                            .setTitle(R.string.tishi)
                            .setMessage(R.string.grzx_xiugai)
                            .setNegativeButton(R.string.quxiao, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            })
                            .setPositiveButton(R.string.queding, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(final DialogInterface dialog, int which) {
                                    bianji_textview.setText(R.string.grzx_bianji);
                                    mCallback.replaceFragment();//通知编辑页面自己去replace，一切任务交给他
                                    flag = (flag + 1) % 2;//每点击一次切换一次
                                    new Handler().postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            dialog.dismiss();
                                        }
                                    }, 300);
                                }
                            }).create();
                    dialog.show();
                }
                break;
            case R.id.toolbar_wdzl_up:
                if (flag == 0) {
                    finish();
                } else {
                    showBackUpDialog();
                }
                break;
        }
    }

    private void showBackUpDialog() {
        AlertDialog dialog = new AlertDialog.Builder(WDZLActivity.this)
                .setCancelable(true)
                .setTitle(R.string.tishi)
                .setMessage(R.string.grzx_fanhui)
                .setNegativeButton(R.string.quxiao, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setPositiveButton(R.string.queding, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(final DialogInterface dialog, int which) {
                        bianji_textview.setText(R.string.grzx_bianji);
                        flag = (flag + 1) % 2;//每点击一次切换一次
                        WDZLActivity.this.getSupportFragmentManager().popBackStack();
                        dialog.dismiss();
                    }
                }).create();
        dialog.show();
    }

    @Override
    public void onBackPressed() {
        if (flag == 0) {
            finish();
        } else {
            showBackUpDialog();
        }
    }

    public interface ReplaceFragment {
        void replaceFragment();
    }
}
