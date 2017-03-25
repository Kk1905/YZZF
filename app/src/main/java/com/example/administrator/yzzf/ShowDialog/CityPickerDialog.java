package com.example.administrator.yzzf.ShowDialog;

import android.app.Dialog;
import android.content.Context;
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
import com.example.administrator.yzzf.R;

/**
 * Created by Administrator on 2017/3/25 0025.
 */

public class CityPickerDialog extends Dialog implements View.OnClickListener, DialogItemAdapter.ItemSelectedListener {
    private LayoutInflater mInflater;
    private Context mContext;
    private TextView title;
    private ChangeCityPickerDialogListener mListener;
    private ListView mListView;
    private int flag;
    private static final int SHENG = 0;
    private static final int SHI = 1;
    private static final int QU = 2;
    private DialogItemAdapter mAdapter;
    private String itemSelectedString;


    public CityPickerDialog(Context context, ChangeCityPickerDialogListener listener, int flag) {
        this(context, R.style.custom_city_picker_dialog_style, listener, flag);
    }

    public CityPickerDialog(@NonNull Context context, @StyleRes int themeResId, ChangeCityPickerDialogListener listener, int flag) {
        super(context, themeResId);
        mListener = listener;
        mContext = context;
        mInflater = LayoutInflater.from(context);
        this.flag = flag;
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

        //为ListView设置Adapter
        switch (flag) {
            case SHENG:
                mAdapter = new DialogItemAdapter(mContext, null, this);
                mListView.setAdapter(mAdapter);
                break;
            case SHI:
                mAdapter = new DialogItemAdapter(mContext, null, this);
                mListView.setAdapter(mAdapter);
                break;
            case QU:
                mAdapter = new DialogItemAdapter(mContext, null, this);
                mListView.setAdapter(mAdapter);
                break;
        }
        //为title设置标题
        mListener.changeTitle(title);
        setContentView(customView);
        setCancelable(true);
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
        void changeTitle(TextView textView);

        void setContent(String string);
    }
}
