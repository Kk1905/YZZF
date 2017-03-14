package com.example.administrator.yzzf.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.administrator.yzzf.R;
import com.example.administrator.yzzf.Util.FragmentFactory;

/**
 * Created by Administrator on 2017/3/14 0014.
 */

public class LoginFragment extends BaseFragment implements View.OnClickListener {
    private FragmentBackListener mListener;
    private EditText zhanghaoEditText;
    private EditText mimaEditText;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = LayoutInflater.from(mAppCompatActivity).inflate(R.layout.fragment_login, container, false);
        zhanghaoEditText = (EditText) view.findViewById(R.id.edit_text_zhanghao);
        mimaEditText = (EditText) view.findViewById(R.id.edit_text_mima);
        view.findViewById(R.id.text_view_zhuce).setOnClickListener(this);
        view.findViewById(R.id.text_view_zhaohui).setOnClickListener(this);
        view.findViewById(R.id.image_view_denglu).setOnClickListener(this);
        mToolbar = (Toolbar) view.findViewById(R.id.toolbar_login);
        mAppCompatActivity.setSupportActionBar(mToolbar);
        ActionBar actionBar = mAppCompatActivity.getSupportActionBar();
        if (actionBar != null) {

            actionBar.setDisplayShowTitleEnabled(false);
        }
        if (mAppCompatActivity instanceof FragmentBackListener) {
            mListener = (FragmentBackListener) mAppCompatActivity;
        } else {
            throw new ClassCastException(mAppCompatActivity.toString() + " must implement FragmentBackListener");
        }
        TextView title = (TextView) view.findViewById(R.id.login_toolbar_title);
        title.setText(R.string.login);
        view.findViewById(R.id.toolbar_up).setOnClickListener(this);

        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.toolbar_up:
                mListener.onActivityFinish();
                break;
            case R.id.text_view_zhuce:
                FragmentFactory.replaceFragment(mAppCompatActivity, new ZhuCeFragment(), R.id.login_container);
                break;
            case R.id.text_view_zhaohui:
                FragmentFactory.replaceFragment(mAppCompatActivity, new ZhaoHuiMiMaFragment(), R.id.login_container);
                break;
            case R.id.image_view_denglu:
                break;
        }
    }

}
