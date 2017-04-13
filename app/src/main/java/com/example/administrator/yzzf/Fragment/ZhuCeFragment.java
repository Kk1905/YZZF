package com.example.administrator.yzzf.Fragment;


import android.content.IntentFilter;
import android.os.Bundle;
import android.os.CountDownTimer;
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

import com.example.administrator.yzzf.BroadcastReceiver.SMSBroadcastReceiver;
import com.example.administrator.yzzf.R;
import com.example.administrator.yzzf.Util.Constant;
import com.example.administrator.yzzf.Util.Json2NewsUtil;
import com.example.administrator.yzzf.Util.PhoneFormatCheckUtils;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static android.Manifest.permission_group.SMS;
import static android.R.attr.filter;
import static android.os.Build.VERSION_CODES.M;
import static com.example.administrator.yzzf.Util.PhoneFormatCheckUtils.isChinaPhoneLegal;

/**
 * Created by Administrator on 2017/3/14 0014.
 */

public class ZhuCeFragment extends BaseFragment implements View.OnClickListener, SMSBroadcastReceiver.SMSListener {
    private static final String ACTION = "android.provider.Telephony.SMS_RECEIVED";
    private TextView huoqu_Textview;
    private CountDownTimer mTimer;
    private SMSBroadcastReceiver mReceiver;
    private EditText shoujihaoEditText;
    private EditText yanzhengmaEditText;
    private String mobile;
    private String pwd;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //实例化广播接收器
        mReceiver = new SMSBroadcastReceiver(this);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = LayoutInflater.from(mAppCompatActivity).inflate(R.layout.fragment_zhuce, container, false);
        view.findViewById(R.id.image_view_tijiao).setOnClickListener(this);
        shoujihaoEditText = (EditText) view.findViewById(R.id.zhuce_edit_text_shoujihao);
        yanzhengmaEditText = (EditText) view.findViewById(R.id.zhuce_edit_text_mima);
        mToolbar = (Toolbar) view.findViewById(R.id.toolbar_login);
        mAppCompatActivity.setSupportActionBar(mToolbar);
        ActionBar actionBar = mAppCompatActivity.getSupportActionBar();
        if (actionBar != null) {

            actionBar.setDisplayShowTitleEnabled(false);
        }
        TextView title = (TextView) view.findViewById(R.id.login_toolbar_title);
        title.setText(R.string.zhuce_toolbar);
        view.findViewById(R.id.toolbar_up).setOnClickListener(this);
        huoqu_Textview = (TextView) view.findViewById(R.id.zhuce_get_yanzhengma);

//        mTimer = new CountDownTimer(1000 * 60, 1000 * 1) {
//            @Override
//            public void onTick(long millisUntilFinished) {
//                huoqu_Textview.setClickable(false);
//                huoqu_Textview.setTextColor(R.color.colorPrimaryDark);
//                huoqu_Textview.setText(millisUntilFinished / 1000 + "s后重新获取");
//            }
//
//            @Override
//            public void onFinish() {
//                huoqu_Textview.setClickable(true);
//                huoqu_Textview.setText(R.string.chongxinhuoqu);
//                huoqu_Textview.setTextColor(R.color.tiaokuan);
//            }
//        };

        huoqu_Textview.setOnClickListener(this);
        //过滤器
        IntentFilter filter = new IntentFilter(ACTION);
        filter.setPriority(Integer.MAX_VALUE);//设置最大权限
        //动态注册广播接收器
        mAppCompatActivity.registerReceiver(mReceiver, filter);
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //动态注销广播接收器
        mAppCompatActivity.unregisterReceiver(mReceiver);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.toolbar_up:
                mAppCompatActivity.getSupportFragmentManager().popBackStack();
                break;
            case R.id.image_view_tijiao:
                mobile = String.valueOf(shoujihaoEditText.getText());
                pwd = String.valueOf(yanzhengmaEditText.getText());

                //判断是否是手机号码
                Boolean isPhone = PhoneFormatCheckUtils.isChinaPhoneLegal(String.valueOf(mobile));

                if (pwd.length() < 6) {
//                    Log.e("kkkboy", "pwd长度---------->" + pwd + "" + mobile);
                    Toast.makeText(mAppCompatActivity, R.string.zhuce_xiao, Toast.LENGTH_SHORT).show();
                    yanzhengmaEditText.setText("");
                } else if (!isPhone) {
//                    Log.e("kkkboy", "是否匹配---------->" + isPhone);
                    //正则没有通过的话
                    Toast.makeText(mAppCompatActivity, R.string.zhuce_11, Toast.LENGTH_SHORT).show();
                    shoujihaoEditText.setText("");
                    yanzhengmaEditText.setText("");
                } else {
                    final String url = Constant.ZHU_CE;
                    final String post = "mobile=" + mobile + "&loginpwd=" + pwd;
//                    Log.e("kkkboy", "pwd长度---------->" + pwd + "" + mobile);
                    String result = "";
                    FutureTask<String> futureTask = new FutureTask<String>(new Callable<String>() {
                        @Override
                        public String call() throws Exception {
                            return Json2NewsUtil.post2Service(url, post);
                        }
                    });
                    new Thread(futureTask).start();
                    try {
                        result = futureTask.get();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }
                    switch (result) {
                        case "0":
                            //输入的账号为空
                            Toast.makeText(mAppCompatActivity, R.string.login_kong, Toast.LENGTH_SHORT).show();
                            break;
                        case "1":
                            //表示注册成功
                            Toast.makeText(mAppCompatActivity, R.string.login_success, Toast.LENGTH_SHORT).show();
                            //回到登录界面
                            mAppCompatActivity.getSupportFragmentManager().popBackStack();
                            break;
                        case "2":
                            //表示账号已存在
                            Toast.makeText(mAppCompatActivity, R.string.zhuce_success, Toast.LENGTH_SHORT).show();
                            shoujihaoEditText.setText("");
                            yanzhengmaEditText.setText("");
                            break;
                        case "-1":
                            //服务器异常
                            Toast.makeText(mAppCompatActivity, R.string.login_service, Toast.LENGTH_SHORT).show();
//                            shoujihaoEditText.setText("");
                            yanzhengmaEditText.setText("");
                            break;
                    }
                }

                break;
            case R.id.zhuce_get_yanzhengma:
                mTimer.start();
                break;
        }
    }

    @Override
    public void setText(String content) {
        yanzhengmaEditText.setText(content);
    }
}
