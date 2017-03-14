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

/**
 * Created by Administrator on 2017/3/14 0014.
 */

public class ZhuCeFragment extends BaseFragment implements View.OnClickListener {
    private EditText shoujihaoEditText;
    private EditText yanzhengmaEditText;
    private String shoujihao;
    private String yanzhengma;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = LayoutInflater.from(mAppCompatActivity).inflate(R.layout.fragment_zhuce, container, false);
        view.findViewById(R.id.image_view_tijiao).setOnClickListener(this);
        shoujihaoEditText = (EditText) view.findViewById(R.id.edit_text_shoujihao);
        yanzhengmaEditText = (EditText) view.findViewById(R.id.edit_text_yanzhengma);
        mToolbar = (Toolbar) view.findViewById(R.id.toolbar_login);
        mAppCompatActivity.setSupportActionBar(mToolbar);
        ActionBar actionBar = mAppCompatActivity.getSupportActionBar();
        if (actionBar != null) {

            actionBar.setDisplayShowTitleEnabled(false);
        }
        TextView title = (TextView) view.findViewById(R.id.login_toolbar_title);
        title.setText(R.string.zhuce_toolbar);
        view.findViewById(R.id.toolbar_up).setOnClickListener(this);
        return view;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.toolbar_up:
                mAppCompatActivity.getSupportFragmentManager().popBackStack();
                break;
            case R.id.image_view_tijiao:

                break;

        }
    }
}
