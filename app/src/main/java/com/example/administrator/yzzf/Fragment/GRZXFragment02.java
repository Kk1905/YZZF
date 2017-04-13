package com.example.administrator.yzzf.Fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.example.administrator.yzzf.Activity.WDDDActivity;
import com.example.administrator.yzzf.Activity.WDTZActivity;
import com.example.administrator.yzzf.Activity.WDZLActivity;
import com.example.administrator.yzzf.Activity.XTXXActivity;
import com.example.administrator.yzzf.Bean.UserMessageBean;
import com.example.administrator.yzzf.R;
import com.example.administrator.yzzf.Util.Constant;
import com.example.administrator.yzzf.Util.Json2NewsUtil;
import com.example.administrator.yzzf.Util.NetUtil;

import cn.bingoogolapple.badgeview.BGABadgeImageView;

import static android.content.Context.MODE_PRIVATE;
import static com.sina.weibo.sdk.openapi.legacy.CommonAPI.CAPITAL.m;
import static com.sina.weibo.sdk.openapi.legacy.CommonAPI.CAPITAL.u;

/**
 * Created by Administrator on 2017/3/17 0017.
 */

public class GRZXFragment02 extends BaseFragment implements BottomNavigationBar.OnTabSelectedListener, View.OnClickListener {

    //    private BGABadgeImageView mBGABadgeImageView01, mBGABadgeImageView02, mBGABadgeImageView03, mBGABadgeImageView04;
    private int userId;//用户id
    private boolean hasNetWork;
    private TextView mTextView_phone;
    private TextView mTextView_jifenzhi;
    private String phone;
    private double score;
//    private String realname;
//    private String nickname;
//    private String sex;
//    private String address;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        Bundle bundle = getArguments();
//        if (bundle != null) {
//            userId = bundle.getInt("userId");
//            phone = bundle.getString("phone");
//        }

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = LayoutInflater.from(mAppCompatActivity).inflate(R.layout.fragment_grzx02, container, false);
        mToolbar = (Toolbar) view.findViewById(R.id.toolbar_grzx);
        mAppCompatActivity.setSupportActionBar(mToolbar);
        ActionBar actionBar = mAppCompatActivity.getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowTitleEnabled(false);
        }
        //检查网络
        if (NetUtil.checkNet(mAppCompatActivity)) {
            hasNetWork = true;
        } else {
            hasNetWork = false;
        }

        SharedPreferences preferences = mAppCompatActivity.getApplicationContext().getSharedPreferences(Constant.YZZF_APP, MODE_PRIVATE);
        userId = Integer.parseInt(preferences.getString("userId", "-1"));
        phone = preferences.getString("mobile", "");

        mTextView_phone = (TextView) view.findViewById(R.id.grzx_phone);
        mTextView_jifenzhi = (TextView) view.findViewById(R.id.grzx_jifenzhi_textview);
        mTextView_phone.setText(phone);

        view.findViewById(R.id.grzx_chakanquanbu).setOnClickListener(this);
        view.findViewById(R.id.my_ziliao).setOnClickListener(this);
        view.findViewById(R.id.grzx_jifen_textview_duihuan).setOnClickListener(this);
        view.findViewById(R.id.xitong).setOnClickListener(this);

        if (userId == -1) {
            Toast.makeText(mAppCompatActivity, R.string.grzx_wei_login, Toast.LENGTH_SHORT).show();
        } else {
            //在登录的前提下，每一次onCreateView都更新一次最新数据
            String url = Constant.USER_ZL + "?userId=" + userId;
//        Log.e("kkkboy", url + "");
            UserMessageBean userMessage = new UserMessageBean();
            if (hasNetWork) {
                //有网络
                try {
                    userMessage = Json2NewsUtil.getUserMessages(mAppCompatActivity, url);

//                    realname = userMessage.getRealName();
//                    nickname = userMessage.getNickName();
//                    sex = userMessage.getSex();
//                    address = userMessage.getAddress();
                    score = userMessage.getScore();

                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(mAppCompatActivity, "服务器错误", Toast.LENGTH_SHORT).show();
                }
            } else {
                //无网络
                Toast.makeText(mAppCompatActivity, R.string.grzx_no_network, Toast.LENGTH_SHORT).show();
            }
        }
        mTextView_jifenzhi.setText(score + "");

//        mBGABadgeImageView01 = (BGABadgeImageView) view.findViewById(R.id.grzx_daifukuan);
//        mBGABadgeImageView02 = (BGABadgeImageView) view.findViewById(R.id.grzx_daishouhuo);
//        mBGABadgeImageView03 = (BGABadgeImageView) view.findViewById(R.id.grzx_daifahuo);
//        mBGABadgeImageView04 = (BGABadgeImageView) view.findViewById(R.id.grzx_tuikuan);
//        mBGABadgeImageView01.showTextBadge("9");
//        mBGABadgeImageView02.showTextBadge("5");
//        mBGABadgeImageView03.showTextBadge("4");
//        mBGABadgeImageView04.showTextBadge("1");
//        mBGABadgeImageView01.setOnClickListener(this);
//        mBGABadgeImageView02.setOnClickListener(this);
//        mBGABadgeImageView03.setOnClickListener(this);
//        mBGABadgeImageView04.setOnClickListener(this);
        return view;
    }


    @Override
    public void onTabSelected(int position) {

    }

    @Override
    public void onTabUnselected(int position) {

    }

    @Override
    public void onTabReselected(int position) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.my_ziliao:
                if (userId == -1) {
                    Toast.makeText(mAppCompatActivity, R.string.grzx_wei_login, Toast.LENGTH_SHORT).show();
                }
                else if (hasNetWork) {
                    Intent intent = new Intent(mAppCompatActivity, WDZLActivity.class);
                    intent.putExtra("userId", userId);
//                    intent.putExtra("realname", realname);
//                    intent.putExtra("nickname", nickname);
//                    intent.putExtra("sex", sex);
//                    intent.putExtra("address", address);
                    mAppCompatActivity.startActivity(intent);
                } else {
                    Toast.makeText(mAppCompatActivity, R.string.grzx_no_network, Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.xitong:
                Intent intent05 = new Intent(mAppCompatActivity, XTXXActivity.class);
                mAppCompatActivity.startActivity(intent05);
                break;
            case R.id.grzx_chakanquanbu:
                Intent intent03 = new Intent(mAppCompatActivity, WDDDActivity.class);
                mAppCompatActivity.startActivity(intent03);
                break;
            case R.id.grzx_jifen_textview_duihuan:
                Toast.makeText(mAppCompatActivity, R.string.grzx_jifenduihuan, Toast.LENGTH_SHORT).show();
//            case R.id.grzx_daifukuan:
//                Intent intent1 = new Intent(mAppCompatActivity, WDDDActivity.class);
//                mAppCompatActivity.startActivity(intent1);
//                break;
        }
    }
}
