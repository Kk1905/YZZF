package com.example.administrator.yzzf.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.administrator.yzzf.Activity.WDZLActivity;
import com.example.administrator.yzzf.R;
import com.example.administrator.yzzf.ShowDialog.CityPickerDialog;
import com.example.administrator.yzzf.Util.Constant;
import com.example.administrator.yzzf.Util.FragmentFactory;
import com.example.administrator.yzzf.Util.Json2NewsUtil;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * Created by Administrator on 2017/4/11 0011.
 */

public class WDZL_BJ_Fragment extends BaseFragment implements WDZLActivity.ReplaceFragment, View.OnClickListener {
    private EditText mEditText_name;
    private EditText mEditText_nickname;
    private EditText mEditText_city;
    private EditText mEditText_address;
    private RadioButton man;
    private RadioButton woman;

    private String name;
    private String nickname;
    private String sex;
    private String address;
    private String city;
    private String complete_address;
    private int userId;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = LayoutInflater.from(mAppCompatActivity).inflate(R.layout.fragment_wdzl_bianji, container, false);

        mEditText_name = (EditText) view.findViewById(R.id.wdzl_bianji_name_edittext);
        mEditText_nickname = (EditText) view.findViewById(R.id.wdzl_bianji_nickname_edittext);
        mEditText_city = (EditText) view.findViewById(R.id.wdzl_bianji_city_edittext);
        mEditText_city.setOnClickListener(this);
        mEditText_address = (EditText) view.findViewById(R.id.wdzl_bianji_address_edittext);
        man = (RadioButton) view.findViewById(R.id.wdzl_bianji_sex_man);
        woman = (RadioButton) view.findViewById(R.id.wdzl_bianji_sex_woman);

        Bundle bundle = getArguments();

        if (bundle != null) {
            name = bundle.getString("name");
            nickname = bundle.getString("nickname");
            sex = bundle.getString("sex");
            userId = bundle.getInt("userId");
            complete_address = bundle.getString("address");

            mEditText_name.setText(name);
            mEditText_nickname.setText(nickname);
            sex = "男";//给sex一个默认值
            switch (sex) {
                case "男":
                    man.setChecked(true);
                    break;
                case "女":
                    woman.setChecked(true);
                    break;
                default:
                    man.setChecked(true);
            }
        }

        return view;
    }

    @Override
    public void replaceFragment() {
        Fragment fragment = new WDZL_WC_Fragment();
        name = String.valueOf(mEditText_name.getText());
        nickname = String.valueOf(mEditText_nickname.getText());
        sex = man.isChecked() ? "男" : "女";
        city = String.valueOf(mEditText_city.getText());
        address = String.valueOf(mEditText_address.getText());
        complete_address = city + " " + address;

        Bundle bundle = new Bundle();
        bundle.putString("name", name);//姓名
        bundle.putString("nickname", nickname);//昵称
        bundle.putString("sex", sex);//性别
        bundle.putString("address", complete_address);//详细住址

        //将数据提交给后台保存,post提交
        final String url = Constant.USER_XG;
        final String post = "id=" + userId + "&realname=" + name + "&nickname=" + nickname + "&address=" + complete_address + "&sex=" + sex;
        String result = "";
        //根据返回结果给用户提示
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
//        Log.e("kkkboy", "resule----------------->" + result + "userId------------>" + userId);
        if (result.equals("1")) {
            fragment.setArguments(bundle);//保存成功才更新数据
            Toast.makeText(getActivity(), "数据保存成功", Toast.LENGTH_SHORT).show();
            FragmentFactory.replaceFragment((AppCompatActivity) getActivity(), fragment, R.id.wdzl_fragment_container);
        } else {
            Toast.makeText(getActivity(), "数据提交错误", Toast.LENGTH_SHORT).show();
            //未对数据做任何修改，就回退fragment
            mAppCompatActivity.getSupportFragmentManager().popBackStack();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.wdzl_bianji_city_edittext:
                CityPickerDialog mDialog = new CityPickerDialog(getActivity(), new CityPickerDialog.ChangeCityPickerDialogListener() {

                    @Override
                    public void setContent(String string) {
                        mEditText_city.setText(string);
                    }
                });
                mDialog.show();
                break;
        }
    }
}
