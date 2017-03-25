package com.example.administrator.yzzf.ShowDialog;

import android.app.Dialog;
import android.content.Context;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ListView;
import android.widget.TextView;

import com.example.administrator.yzzf.Adapter.DialogItemAdapter;
import com.example.administrator.yzzf.Model.ProvinceModel;
import com.example.administrator.yzzf.R;
import com.example.administrator.yzzf.Util.XmlParserHandler;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 * Created by Administrator on 2017/3/25 0025.
 */

public class CityPickerDialog extends Dialog implements View.OnClickListener, DialogItemAdapter.ItemSelectedListener {
    private LayoutInflater mInflater;
    private Context mContext;
    private TextView title;
    private ChangeCityPickerDialogListener mListener;
    private ListView mListView;
    private DialogItemAdapter mAdapter;
    private String itemSelectedString;
    private List<ProvinceModel> mProvinceModels;
    private int flag;
    private static final int PROVINCE = 0;
    private static final int CITY = 1;
    private static final int DISTRICT = 2;


    public CityPickerDialog(Context context, ChangeCityPickerDialogListener listener) {
        this(context, R.style.custom_city_picker_dialog_style, listener);
    }

    public CityPickerDialog(@NonNull Context context, @StyleRes int themeResId, ChangeCityPickerDialogListener listener) {
        super(context, themeResId);
        mListener = listener;
        mContext = context;
        mInflater = LayoutInflater.from(context);
    }

    @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            initCustomView();
            initDialogSize();
    }

    private void initDialogSize() {
        Window dialogWindow = getWindow();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        WindowManager manager = dialogWindow.getWindowManager();
        manager.getDefaultDisplay().getMetrics(displayMetrics);
//        displayMetrics=mContext.getResources().getDisplayMetrics();
        WindowManager.LayoutParams layoutParams = dialogWindow.getAttributes();
        layoutParams.width = (int) (displayMetrics.widthPixels * 0.8);
        layoutParams.height = (int) (displayMetrics.heightPixels * 0.7);
        layoutParams.gravity = Gravity.CENTER;
        dialogWindow.setAttributes(layoutParams);
    }

    private void initCustomView() {
        View customView = mInflater.inflate(R.layout.custom_dialog_view, null);
        title = (TextView) customView.findViewById(R.id.custom_dialog_title);
        customView.findViewById(R.id.custom_dialog_quxiao).setOnClickListener(this);
        customView.findViewById(R.id.custom_dialog_queding).setOnClickListener(this);
        mListView = (ListView) customView.findViewById(R.id.custom_dialog_listview);

        //设置标题
        title.setText(R.string.xuanze_sheng);
        //初始页面应是选择省的页面
        flag = PROVINCE;
        //初始化数据
        initProvinceDatas();
        //为ListView设置Adapter
        mAdapter = new DialogItemAdapter(mContext, mProvinceModels, this);
        mListView.setAdapter(mAdapter);

        setContentView(customView);
        setCancelable(true);
    }

    private void initProvinceDatas() {
        //获取原始数据资源
        AssetManager assetManager = mContext.getAssets();
        try {
            InputStream inputStream = assetManager.open("province_data.xml");
            //创建一个解析xml的工厂对象
            SAXParserFactory factory = SAXParserFactory.newInstance();
            //获取解析xml的对象
            SAXParser parser = factory.newSAXParser();
            XmlParserHandler handler = new XmlParserHandler();
            parser.parse(inputStream, handler);
            inputStream.close();
            //解析出来的最终数据
            mProvinceModels = handler.getProvinceList();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.custom_dialog_quxiao:
                dismiss();
                break;
            case R.id.custom_dialog_queding:
                mListener.setContent(itemSelectedString);
                dismiss();
                break;
        }
    }

    @Override
    public void itemSelected(String string) {
        itemSelectedString = string;
    }

    //跟Activity交互的接口
    public interface ChangeCityPickerDialogListener {

        void setContent(String string);
    }
}
