package com.example.administrator.yzzf.ShowDialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.yzzf.Activity.TestActivity;
import com.example.administrator.yzzf.R;
import com.example.administrator.yzzf.Util.Constant;
import com.example.administrator.yzzf.Util.Json2NewsUtil;

import static com.example.administrator.yzzf.Util.Json2NewsUtil.post2Service;

/**
 * Created by Administrator on 2017/2/27 0027.
 */

public class Show_XiangQing_Write_Pinglun_Dialog extends BaseShowDialog implements View.OnClickListener {
    private EditText mEditText_content;
    private AddPinglun mPinglun;
    private int articleid;
    private int replyuserid;
    private Context mContext;
    private Handler mHandler;

    public Show_XiangQing_Write_Pinglun_Dialog(Activity activity, AddPinglun pinglun, Handler handler, int articleid, int replyuserid) {
        super(activity);
        mContext = activity;
        mPinglun = pinglun;
        mHandler = handler;
        this.articleid = articleid;
        this.replyuserid = replyuserid;
    }

    public void showDialog(int layoutId) {
        super.showDialog(layoutId, 0);
        mAlertDialog.findViewById(R.id.xiangqing_pinglun_dialog_quxiao).setOnClickListener(this);
        mAlertDialog.findViewById(R.id.xiangqing_pinglun_dialog_fasong).setOnClickListener(this);
        mEditText_content = (EditText) mAlertDialog.findViewById(R.id.xiangqing_pinglun_dialog_content);
        mEditText_content.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.xiangqing_pinglun_dialog_quxiao:
                dismiss();
                break;
            case R.id.xiangqing_pinglun_dialog_fasong:
                final String content = String.valueOf(mEditText_content.getText());//获取评论内容
                final String url = Constant.ADD_REPLY;
                final String post = "articleid=" + articleid + "&replyuserid=" + replyuserid + "&content=" + content;//post请求参数
//                Log.e("kkkboy", url + "?" + post);
                //将评论内容提交给后台
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        final String result = Json2NewsUtil.post2Service(url, post);
                        Log.e("kkkboy", "提交结果" + result + "content" + content);//1代表成功，-1代表评论失败
                        mHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                switch (result) {
                                    case "1":
                                        Toast.makeText(mContext, R.string.xinwen_pinglun_success, Toast.LENGTH_SHORT).show();
                                        break;
                                    case "-1":
                                        Toast.makeText(mContext, R.string.xinwen_pinglun_shibai, Toast.LENGTH_LONG).show();
                                        break;
                                }
                            }
                        });
                    }
                }).start();
                mPinglun.add_pinglun(content, Show_XiangQing_Write_Pinglun_Dialog.this, mEditText_content);//刷新任务通过回调交给activity完成,刷新评论界面

//
                break;
            case R.id.xiangqing_pinglun_dialog_content:
                break;

        }
    }

    public interface AddPinglun {
        void add_pinglun(String content, Show_XiangQing_Write_Pinglun_Dialog dialog, EditText editText);
    }
}
