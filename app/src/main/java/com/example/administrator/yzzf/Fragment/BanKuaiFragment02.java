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

import com.example.administrator.yzzf.Activity.BKSYActivity02;
import com.example.administrator.yzzf.Activity.ShangPinActivity;
import com.example.administrator.yzzf.R;
import com.example.administrator.yzzf.Util.Constant;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Administrator on 2017/3/24 0024.
 */

public class BanKuaiFragment02 extends BaseFragment implements View.OnClickListener {
    private int userId;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = LayoutInflater.from(mAppCompatActivity).inflate(R.layout.fragment_bankuai02, container, false);
        mToolbar = (Toolbar) view.findViewById(R.id.toolbar_bankuai);
        mAppCompatActivity.setSupportActionBar(mToolbar);
        ActionBar actionBar = mAppCompatActivity.getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowTitleEnabled(false);
        }

        SharedPreferences preferences = mAppCompatActivity.getApplicationContext().getSharedPreferences(Constant.YZZF_APP, MODE_PRIVATE);
        userId = Integer.parseInt(preferences.getString("userId", "-1"));

        view.findViewById(R.id.fragment_bankuai_01).setOnClickListener(this);
        view.findViewById(R.id.fragment_bankuai_02).setOnClickListener(this);
        view.findViewById(R.id.fragment_bankuai_03).setOnClickListener(this);
        view.findViewById(R.id.fragment_bankuai_04).setOnClickListener(this);
        view.findViewById(R.id.fragment_bankuai_05).setOnClickListener(this);
        view.findViewById(R.id.fragment_bankuai_06).setOnClickListener(this);
        view.findViewById(R.id.fragment_bankuai_07).setOnClickListener(this);
        view.findViewById(R.id.fragment_bankuai_08).setOnClickListener(this);
        view.findViewById(R.id.fragment_bankuai_09).setOnClickListener(this);
        view.findViewById(R.id.fragment_bankuai_10).setOnClickListener(this);
        view.findViewById(R.id.fragment_bankuai_11).setOnClickListener(this);
        view.findViewById(R.id.fragment_bankuai_12).setOnClickListener(this);
        view.findViewById(R.id.fragment_bankuai_13).setOnClickListener(this);
        view.findViewById(R.id.fragment_bankuai_14).setOnClickListener(this);
        view.findViewById(R.id.fragment_bankuai_15).setOnClickListener(this);
        view.findViewById(R.id.fragment_bankuai_16).setOnClickListener(this);
        view.findViewById(R.id.fragment_bankuai_17).setOnClickListener(this);
        view.findViewById(R.id.fragment_bankuai_18).setOnClickListener(this);
        view.findViewById(R.id.fragment_bankuai_19).setOnClickListener(this);
        view.findViewById(R.id.fragment_bankuai_20).setOnClickListener(this);
        view.findViewById(R.id.fragment_bankuai_21).setOnClickListener(this);


        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        Bundle bundle = getArguments();
//        if (bundle != null) {
//            userId = bundle.getInt("userId");
//        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fragment_bankuai_01:
                choseClick("推荐");
                break;
            case R.id.fragment_bankuai_02:
                choseClick("热点");
                break;
            case R.id.fragment_bankuai_03:
                choseClick("社会");
                break;
            case R.id.fragment_bankuai_04:
                choseClick("娱乐");
                break;
            case R.id.fragment_bankuai_05:
                choseClick("科技");
                break;
            case R.id.fragment_bankuai_06:
                choseClick("体育");
                break;
            case R.id.fragment_bankuai_07:
                choseClick("汽车");
                break;
            case R.id.fragment_bankuai_08:
                choseClick("财经");
                break;
            case R.id.fragment_bankuai_09:
                choseClick("搞笑");
                break;
            case R.id.fragment_bankuai_10:
                choseClick("军事");
                break;
            case R.id.fragment_bankuai_11:
                choseClick("国际");
                break;
            case R.id.fragment_bankuai_12:
                choseClick("时尚");
                break;
            case R.id.fragment_bankuai_13:
                choseClick("旅游");
                break;
            case R.id.fragment_bankuai_14:
                choseClick("探索");
                break;
            case R.id.fragment_bankuai_15:
                choseClick("育儿");
                break;
            case R.id.fragment_bankuai_16:
                choseClick("养生");
                break;
            case R.id.fragment_bankuai_17:
                choseClick("故事");
                break;
            case R.id.fragment_bankuai_18:
                choseClick("美文");
                break;
            case R.id.fragment_bankuai_19:
                choseClick("游戏");
                break;
            case R.id.fragment_bankuai_20:
                choseClick("历史");
                break;
            case R.id.fragment_bankuai_21:
                choseClick("美食");
                break;
        }
    }

    private void choseClick(String forumidString) {
        Intent intent = new Intent(mAppCompatActivity, BKSYActivity02.class);
        intent.putExtra("title", forumidString);
        intent.putExtra("userId", userId);
        mAppCompatActivity.startActivity(intent);
    }
}
