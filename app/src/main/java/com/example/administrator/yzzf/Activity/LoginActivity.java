package com.example.administrator.yzzf.Activity;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.administrator.yzzf.Fragment.BaseFragment;
import com.example.administrator.yzzf.Fragment.LoginFragment;
import com.example.administrator.yzzf.R;


/**
 * Created by Administrator on 2017/3/13 0013.
 */

public class LoginActivity extends AppCompatActivity implements View.OnClickListener, BaseFragment.FragmentBackListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_login);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowTitleEnabled(false);
        }

        initView();
    }

    private void initView() {
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.login_container);
        if (fragment == null) {
            getSupportFragmentManager().beginTransaction()
                    .addToBackStack(null)
                    .add(R.id.login_container, new LoginFragment())
                    .commit();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.image_view_denglu:
                break;
        }
    }

    @Override
    public void onActivityFinish() {
        finish();
    }
}
