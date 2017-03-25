package com.example.administrator.yzzf.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.administrator.yzzf.Adapter.DialogItemAdapter;
import com.example.administrator.yzzf.R;
import com.example.administrator.yzzf.ShowDialog.CityPickerDialog;

/**
 * Created by Administrator on 2017/3/24 0024.
 */

public class TiJiaoDingDanActivity extends AppCompatActivity implements View.OnClickListener {
    private int num;
    private int danjia;
    private int yunfei;
    private TextView mTextView_danjia;
    private TextView mTextView_num;
    private TextView mTextView_yunfei;
    private TextView mTextView_heji;
    private EditText mEditText_sheng;
    private EditText mEditText_shi;
    private EditText mEditText_qu;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tijiao_dingdan);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_login);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowTitleEnabled(false);
        }
        TextView title = (TextView) findViewById(R.id.login_toolbar_title);
        title.setText(R.string.tijiaodingdan);
        findViewById(R.id.toolbar_up).setOnClickListener(this);
        findViewById(R.id.tijiaodingdan_tijiao).setOnClickListener(this);
        mTextView_danjia = (TextView) findViewById(R.id.tijiaodingdan_danjia);
        mTextView_num = (TextView) findViewById(R.id.tijiaodingdan_num);
        mTextView_yunfei = (TextView) findViewById(R.id.tijiaodingdan_yunfei);
        mTextView_heji = (TextView) findViewById(R.id.tijiaodingdan_heji);

        mEditText_sheng = (EditText) findViewById(R.id.tijiaodingdan_sheng);
        mEditText_shi = (EditText) findViewById(R.id.tijiaodingdan_shi);
        mEditText_qu = (EditText) findViewById(R.id.tijiaodingdan_qu);
        mEditText_sheng.setOnClickListener(this);
        mEditText_shi.setOnClickListener(this);
        mEditText_qu.setOnClickListener(this);


        Bundle bundle = getIntent().getBundleExtra("info");
        num = bundle.getInt("num");
        danjia = bundle.getInt("danjia");
        yunfei = bundle.getInt("yunfei");

        mTextView_danjia.setText("¥" + danjia);
        mTextView_num.setText(num + "");
        mTextView_yunfei.setText(yunfei + "");
        mTextView_heji.setText("¥" + (danjia * num + yunfei) * 1.0);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.toolbar_up:
                finish();
                break;
            case R.id.tijiaodingdan_tijiao:

                break;
            case R.id.tijiaodingdan_sheng:
                CityPickerDialog mDialog01 = new CityPickerDialog(this, new CityPickerDialog.ChangeCityPickerDialogListener() {
                    @Override
                    public void changeTitle(TextView textView) {
                        textView.setText("选择省");
                    }

                    @Override
                    public void setContent(String string) {
                        mEditText_sheng.setText(string);
                    }
                }, 0);
                mDialog01.show();
                break;
            case R.id.tijiaodingdan_shi:
                CityPickerDialog mDialog02 = new CityPickerDialog(this, new CityPickerDialog.ChangeCityPickerDialogListener() {
                    @Override
                    public void changeTitle(TextView textView) {
                        textView.setText("选择市");
                    }

                    @Override
                    public void setContent(String string) {
                        mEditText_shi.setText(string);
                    }
                }, 1);
                mDialog02.show();
                break;
            case R.id.tijiaodingdan_qu:
                CityPickerDialog mDialog03 = new CityPickerDialog(this, new CityPickerDialog.ChangeCityPickerDialogListener() {
                    @Override
                    public void changeTitle(TextView textView) {
                        textView.setText("选择区");
                    }

                    @Override
                    public void setContent(String string) {
                        mEditText_qu.setText(string);
                    }
                }, 2);
                mDialog03.show();
                break;
        }
    }

}
