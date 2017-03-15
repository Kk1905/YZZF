package com.example.administrator.yzzf.Fragment;

import android.os.Bundle;
import android.os.CountDownTimer;
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

public class ZhaoHuiMiMaFragment extends BaseFragment implements View.OnClickListener {
    private TextView huoqu_Textview;
    private CountDownTimer mTimer;
    private EditText shoujihaoEditText;
    private EditText yanzhengmaEditText;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = LayoutInflater.from(mAppCompatActivity).inflate(R.layout.fragment_zhaohuimima, container, false);
        shoujihaoEditText = (EditText) view.findViewById(R.id.edit_text_shoujihao);
        yanzhengmaEditText = (EditText) view.findViewById(R.id.edit_text_yanzhengma);
        view.findViewById(R.id.image_view_tijiao).setOnClickListener(this);
        mToolbar = (Toolbar) view.findViewById(R.id.toolbar_login);
        mAppCompatActivity.setSupportActionBar(mToolbar);
        ActionBar actionBar = mAppCompatActivity.getSupportActionBar();
        if (actionBar != null) {

            actionBar.setDisplayShowTitleEnabled(false);
        }
        TextView title = (TextView) view.findViewById(R.id.login_toolbar_title);
        title.setText(R.string.zhaohui);
        view.findViewById(R.id.toolbar_up).setOnClickListener(this);
        mTimer = new CountDownTimer(1000 * 5, 1000 * 1) {
            @Override
            public void onTick(long millisUntilFinished) {
                huoqu_Textview.setClickable(false);
                huoqu_Textview.setTextColor(R.color.colorPrimaryDark);
                huoqu_Textview.setText(millisUntilFinished / 1000 + "s后重新获取");
            }

            @Override
            public void onFinish() {
                huoqu_Textview.setClickable(true);
                huoqu_Textview.setText(R.string.chongxinhuoqu);
                huoqu_Textview.setTextColor(R.color.colorAccent);
            }
        };
        huoqu_Textview = (TextView) view.findViewById(R.id.zhaohui_get_yanzhengma);
        huoqu_Textview.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.image_view_tijiao:
                FragmentFactory.replaceFragment(mAppCompatActivity, new XinmimaFragment(), R.id.login_container);
                break;
            case R.id.toolbar_up:
                mAppCompatActivity.getSupportFragmentManager().popBackStack();
                break;
            case R.id.zhaohui_get_yanzhengma:
                //TODO获取验证码
                mTimer.start();
                break;
        }
    }
}
