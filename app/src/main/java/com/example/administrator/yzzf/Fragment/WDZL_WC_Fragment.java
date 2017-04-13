package com.example.administrator.yzzf.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.yzzf.R;

/**
 * Created by Administrator on 2017/4/11 0011.
 */

public class WDZL_WC_Fragment extends BaseFragment {
    private String name;
    private String nickname;
    private String sex;
    private String address;

    private TextView mTextView_name;
    private TextView mTextView_nickname;
    private TextView mTextView_sex;
    private TextView mTextView_address;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = LayoutInflater.from(mAppCompatActivity).inflate(R.layout.fragment_wdzl_wancheng, container, false);

        mTextView_name = (TextView) view.findViewById(R.id.wdzl_wancheng_name_textview);
        mTextView_nickname = (TextView) view.findViewById(R.id.wdzl_wancheng_nickname_textview);
        mTextView_sex = (TextView) view.findViewById(R.id.wdzl_wancheng_sex_textview);
        mTextView_address = (TextView) view.findViewById(R.id.wdzl_wancheng_address_textview);

        Bundle bundle = getArguments();
        if (bundle != null) {
            name = bundle.getString("name");
            nickname = bundle.getString("nickname");
            sex = bundle.getString("sex");
            address = bundle.getString("address");

            mTextView_name.setText(name);
            mTextView_nickname.setText(nickname);
            mTextView_sex.setText(sex);
            mTextView_address.setText(address);
        }

        return view;
    }
}
