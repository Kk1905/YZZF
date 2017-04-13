package com.example.administrator.yzzf.Fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.yzzf.Activity.MainActivity;
import com.example.administrator.yzzf.Bean.UserMessageBean;
import com.example.administrator.yzzf.R;
import com.example.administrator.yzzf.Util.Constant;
import com.example.administrator.yzzf.Util.FragmentFactory;
import com.example.administrator.yzzf.Util.Json2NewsUtil;
import com.example.administrator.yzzf.Util.PhoneFormatCheckUtils;

import java.util.Date;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

import static android.content.Context.MODE_PRIVATE;
import static com.sina.weibo.sdk.openapi.legacy.CommonAPI.CAPITAL.u;

/**
 * Created by Administrator on 2017/3/14 0014.
 */

public class LoginFragment extends BaseFragment implements View.OnClickListener {
    private FragmentBackListener mListener;
    private EditText zhanghaoEditText;
    private EditText mimaEditText;
    private String mobile;
    private String pwd;
    private ChangeToolBar mChangeToolBar;
    //一个全局性的userId来源于这里
    private int userId;
    //用户手机号,进行处理，中间四位用*表示
    private String phone;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = LayoutInflater.from(mAppCompatActivity).inflate(R.layout.fragment_login, container, false);
        zhanghaoEditText = (EditText) view.findViewById(R.id.login_edit_text_zhanghao);
        mimaEditText = (EditText) view.findViewById(R.id.login_edit_text_mima);
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
        mChangeToolBar = (ChangeToolBar) new ZhuyeFragment02();
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
                mobile = String.valueOf(zhanghaoEditText.getText());
                pwd = String.valueOf(mimaEditText.getText());

//                zhanghaoEditText.setText("");
//                mimaEditText.setText("");

                final String url = Constant.LOGIN + "?mobile=" + mobile + "&loginpwd=" + pwd;

//                    Log.e("kkkboy", "pwd长度---------->" + pwd + "" + mobile);
                UserMessageBean userMessageBean = new UserMessageBean();
                FutureTask<UserMessageBean> futureTask = new FutureTask<UserMessageBean>(new Callable<UserMessageBean>() {
                    @Override
                    public UserMessageBean call() throws Exception {
                        return Json2NewsUtil.getUserMessages(mAppCompatActivity, url);
                    }
                });
                new Thread(futureTask).start();
                try {
                    userMessageBean = futureTask.get();
                } catch (Exception e) {
                    e.printStackTrace();
                    //服务器错误
                    Toast.makeText(mAppCompatActivity, R.string.login_service, Toast.LENGTH_SHORT).show();
                    return;
                }
                if (userMessageBean == null) {
                    Toast.makeText(mAppCompatActivity, R.string.login_mima_error, Toast.LENGTH_SHORT).show();
                } else {
                    userId = userMessageBean.getId();
                    phone = userMessageBean.getMobile();
                    phone = PhoneFormatCheckUtils.formatPhone(phone);
                    Toast.makeText(mAppCompatActivity, "欢迎，" + phone, Toast.LENGTH_LONG).show();
                    //将userId和phone持久化一下
                    SharedPreferences preferences = mAppCompatActivity.getApplicationContext().getSharedPreferences(Constant.YZZF_APP, MODE_PRIVATE);//只能被本程序读写
                    SharedPreferences.Editor editor = preferences.edit();//获取编写对象
                    //写入数据
                    editor.putString("userId", String.valueOf(userId));
                    editor.putString("mobile", phone);
                    editor.commit();
                    mAppCompatActivity.finish();
//                    Handler handler = new Handler();
//                    handler.postDelayed(new Runnable() {
//                        @Override
//                        public void run() {
//                            mChangeToolBar.changeToolBar();
//                        }
//                    }, 500);
                }
                break;
        }
    }

    public interface ChangeToolBar {
        void changeToolBar();
    }
}
