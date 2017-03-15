package com.example.administrator.yzzf.BroadcastReceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.icu.text.DateFormat;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.telephony.SmsMessage;

import java.util.Date;

import static com.sina.weibo.sdk.openapi.legacy.AccountAPI.CAPITAL.S;
import static okhttp3.internal.http.HttpDate.format;

/**
 * 监听短信的广播接收器
 * Created by Administrator on 2017/3/15 0015.
 */

public class SMSBroadcastReceiver extends BroadcastReceiver {
    private SMSListener mListener;
    private static final String SMS_RECEIVED_ACTION = "android.provider.Telephony.SMS_RECEIVED";
    private String content;
    private String time;
    public SMSBroadcastReceiver(SMSListener listener) {
        super();
        mListener = listener;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onReceive(Context context, Intent intent) {
        //判断如果是获取到短信
        if (intent.getAction().equals(SMS_RECEIVED_ACTION)) {
            Object[] pdus = (Object[]) intent.getExtras().get("pdus");
            for (Object pdu : pdus) {

                SmsMessage smsMessage = SmsMessage.createFromPdu((byte[]) pdu);
                //获取短信的发送者
                String sender = smsMessage.getDisplayOriginatingAddress();
                //获取短信的主题内容
                content = smsMessage.getDisplayMessageBody();
                //获取发送时间
                long date = smsMessage.getTimestampMillis();
                Date tiemDate = new Date(date);
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                time = dateFormat.format(tiemDate);

                //过滤短信的发送号码
                if ("+8615162864397".equals(sender)) {
                    mListener.setText(content);
                    abortBroadcast();
                }
            }
        }
    }

    public interface SMSListener {
        void setText(String content);
    }
}
