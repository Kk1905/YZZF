package com.example.administrator.yzzf.Bean;


import android.annotation.TargetApi;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.util.Log;

import java.text.ParseException;
import java.util.Date;


/**
 * Created by Administrator on 2017/3/9 0009.
 */

public class NewsItemBean {
    //内容
    private String content;
    //类别号（暂时未知是干啥的）
    private int typeid;
    //配图的url
    private String picture1;
    private String picture2;
    private String picture3;
    //跳转的url,跟id唯一相关
    private String stringUrl;
    //发布的时间
    private String displayAdddate;
    //判断排版方式，"0"是左右排版，"1"是上下排版，"2"是广告代言
    private String states;
    //是否首页显示
    private String isindex;
    //点击数
    private int hits;
    //标题
    private String title;
    //所属的板块号（军事啥的）
    private int forumId;
    //被转载的次数
    private int reprint;
    //发布用户的id
    private int usersId;
    //多久之前发布，需要计算
    private String hourAgo;
    //文章的id
    private int id;
    //新闻的来源
    private String source;

    public String getContent() {
        return content;
    }

    public int getId() {
        return id;
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

    public int getTypeid() {
        return typeid;
    }

    public String getPicture1() {
        return picture1;
    }

    public String getPicture2() {
        return picture2;
    }

    public String getPicture3() {
        return picture3;
    }

    public String getStringUrl() {
        return "http://news.sina.com.cn/o/2017-03-29/doc-ifycspxp0254935.shtml";
    }

    public String getDisplayAdddate() {
        return displayAdddate;
    }

    public String getStates() {
        return states;
    }

    public String getIsindex() {
        return isindex;
    }

    public int getHits() {
        return hits;
    }

    public String getTitle() {
        return title;
    }

    public int getForumId() {
        return forumId;
    }

    public int getReprint() {
        return reprint;
    }

    public int getUsersId() {
        return usersId;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSource() {
        return source;
    }

    public void setTypeid(int typeid) {
        this.typeid = typeid;
    }

    public void setPicture1(String picture1) {
        this.picture1 = picture1;
    }

    public void setPicture2(String picture2) {
        this.picture2 = picture2;
    }

    public void setPicture3(String picture3) {
        this.picture3 = picture3;
    }

    public void setDisplayAdddate(String displayAdddate) {
        this.displayAdddate = displayAdddate;
    }

    public void setStates(String states) {
        this.states = states;
    }

    public void setIsindex(String isindex) {
        this.isindex = isindex;
    }

    public void setHits(int hits) {
        this.hits = hits;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setForumId(int forumId) {
        this.forumId = forumId;
    }

    public void setReprint(int reprint) {
        this.reprint = reprint;
    }

    public void setUsersId(int usersId) {
        this.usersId = usersId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String toString() {
        return "NewsItem " + "[title=" + this.title + ", date=" + this.displayAdddate + ", num=" + this.states + ", newsType=" + "]";
    }
}
