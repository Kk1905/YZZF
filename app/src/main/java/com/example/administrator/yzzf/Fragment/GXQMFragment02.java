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
import android.widget.FrameLayout;

import com.example.administrator.yzzf.Activity.LoginActivity;
import com.example.administrator.yzzf.R;
import com.example.administrator.yzzf.Util.Constant;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Administrator on 2017/4/11 0011.
 */

public class GXQMFragment02 extends BaseFragment implements View.OnClickListener {
    private FrameLayout mFrameLayout;
    private int userId;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        Bundle bundle = getArguments();
//        if (bundle != null) {
//            hasLogin = bundle.getBoolean("hasLogin");
//        }

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = LayoutInflater.from(mAppCompatActivity).inflate(R.layout.fragment_gxqm02, container, false);
        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar_gxqm);
        mAppCompatActivity.setSupportActionBar(toolbar);
        ActionBar actionBar = mAppCompatActivity.getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowTitleEnabled(false);
        }

        SharedPreferences preferences = mAppCompatActivity.getApplicationContext().getSharedPreferences(Constant.YZZF_APP, MODE_PRIVATE);
        userId = Integer.parseInt(preferences.getString("userId", "-1"));

        mFrameLayout = (FrameLayout) view.findViewById(R.id.gxqm_framelayout);
        mFrameLayout.setOnClickListener(this);
        view.findViewById(R.id.gxqm_zhuce).setOnClickListener(this);

        if (userId == -1) {
            mFrameLayout.setVisibility(View.VISIBLE);
        } else {
            mFrameLayout.setVisibility(View.GONE);
        }
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.gxqm_zhuce:
                Intent intent = new Intent(mAppCompatActivity, LoginActivity.class);
                mAppCompatActivity.startActivity(intent);
                break;
        }
    }
}
