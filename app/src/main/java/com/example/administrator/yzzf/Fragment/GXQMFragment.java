package com.example.administrator.yzzf.Fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.administrator.yzzf.Activity.XiangQingActivity;
import com.example.administrator.yzzf.R;
import com.example.administrator.yzzf.Util.Constant;
import com.example.administrator.yzzf.Util.FragmentFactory;

/**
 * Created by Administrator on 2017/2/24 0024.
 */

public class GXQMFragment extends BaseFragment implements View.OnClickListener {
    private Button mButton;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = LayoutInflater.from(mAppCompatActivity).inflate(R.layout.fragment_gxqm, container, false);
        mToolbar = (Toolbar) view.findViewById(R.id.toolbar_gxqm);
        mAppCompatActivity.setSupportActionBar(mToolbar);

        ActionBar actionBar = mAppCompatActivity.getSupportActionBar();
        if (actionBar != null) {

            actionBar.setDisplayShowTitleEnabled(false);
        }

        mButton = (Button) view.findViewById(R.id.gxqm_zhuce);
        mButton.setOnClickListener(this);

        return view;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
//            case R.id.gxqm_back:
//                mListener.onFragmentBack();
            case R.id.gxqm_zhuce:
                break;
        }
    }
}
