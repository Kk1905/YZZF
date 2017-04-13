package com.example.administrator.yzzf.Bean;

import android.annotation.TargetApi;
import android.icu.text.SimpleDateFormat;
import android.os.Build;

import java.text.ParseException;
import java.util.Date;

/**
 * Created by Administrator on 2017/4/9 0009.
 */

public class PinglunItemBean {
    /**
     * "id":6105,
     * "content":"打与不打不是关键，俄罗斯敢独舰闯狼群。这勇气就该赞一个。",
     * "flag":"1",
     * "replyname":"大叔本是关东汉",
     * "articleid":3588,
     * "replyuserid":1,
     * "mobile":"管理员（手机）",
     * "displayAdddate":"2017-04-06 20:06"
     */

    private int id;
    private String content;
    private String flag;
    private String replyname;
    private int articleid;
    private int replyuserid;
    private String mobile;
    private String displayAdddate;
    private String hourAgo;
    private int several;

    public int getSeveral() {
        return several;
    }

    public void setSeveral(int several) {
        this.several = several;
    }

    public int getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public String getFlag() {
        return flag;
    }

    public String getReplyname() {
        return replyname;
    }

    public int getArticleid() {
        return articleid;
    }

    public int getReplyuserid() {
        return replyuserid;
    }

    public String getMobile() {
        return mobile;
    }

    public String getDisplayAdddate() {
        return displayAdddate;
    }


    @TargetApi(Build.VERSION_CODES.N)
    public String getHourAgo() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm");
//        HH:mm:ss
        //之间相差的毫秒数
        long time = 0;
        try {
            time = new Date().getTime() - format.parse(getDisplayAdddate()).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //转换成分钟
        int time_min = (int) (time / (1000 * 60));
        //转换成小时
        int time_hour = (int) (time / (1000 * 60 * 60));
        //转换成天
        int time_day = (int) (time / (1000 * 60 * 60 * 24));
        //转换成月
        int time_month = time_day / 30;
        //转换成年
        int time_year = time_day / 365;

        String result = "";
        if (time_min < 60) {
            result = time_min + "分钟之前";
        } else if (time_hour < 24) {
            //如果小于24小时，则用小时显示
            result = time_hour + "小时之前";
        } else if (time_day < 30) {
            result = time_day + "天之前";
        } else if (time_month < 12) {
            result = time_month + "个月之前";
        } else {
            result = time_year + "年之前";
        }
        return result;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public void setReplyname(String replyname) {
        this.replyname = replyname;
    }

    public void setArticleid(int articleid) {
        this.articleid = articleid;
    }

    public void setReplyuserid(int replyuserid) {
        this.replyuserid = replyuserid;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public void setDisplayAdddate(String displayAdddate) {
        this.displayAdddate = displayAdddate;
    }
}
