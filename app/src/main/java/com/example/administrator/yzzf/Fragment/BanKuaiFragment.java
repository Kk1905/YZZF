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

import com.example.administrator.yzzf.Activity.BKSYActivity;
import com.example.administrator.yzzf.Activity.MeiRiJingXuanActivity;
import com.example.administrator.yzzf.Activity.ShangPinActivity;
import com.example.administrator.yzzf.R;


/**
 * Created by Administrator on 2017/2/20 0020.
 */

public class BanKuaiFragment extends BaseFragment implements View.OnClickListener {


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = LayoutInflater.from(mAppCompatActivity).inflate(R.layout.fragment_bankuai, container, false);
        mToolbar = (Toolbar) view.findViewById(R.id.toolbar_bankuai);
        mAppCompatActivity.setSupportActionBar(mToolbar);
        ActionBar actionBar = mAppCompatActivity.getSupportActionBar();
        if (actionBar != null) {

            actionBar.setDisplayShowTitleEnabled(false);
        }
        view.findViewById(R.id.bankuai_htjl).setOnClickListener(this);
        view.findViewById(R.id.bankuai_meirijingxuan).setOnClickListener(this);
        view.findViewById(R.id.bankuai_shequgonggao).setOnClickListener(this);
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
//            case R.id.bankuai_back:
//                mListener.onFragmentBack();
            case R.id.bankuai_htjl:
                Intent intent01 = new Intent(mAppCompatActivity, BKSYActivity.class);
                mAppCompatActivity.startActivity(intent01);
                break;
            case R.id.bankuai_meirijingxuan:
                Log.e("kkkboy", "meirijingxuan");
                Intent intent02 = new Intent(mAppCompatActivity, MeiRiJingXuanActivity.class);
                mAppCompatActivity.startActivity(intent02);
                break;
            case R.id.bankuai_zulingzhihuan:
                break;
            case R.id.bankuai_wuyechangshi:
                break;
            case R.id.bankuai_shequgonggao:
                Log.e("kkkboy", "shequgonggao");
                Intent intent = new Intent(mAppCompatActivity, ShangPinActivity.class);
                mAppCompatActivity.startActivity(intent);
                break;
        }
    }
}
