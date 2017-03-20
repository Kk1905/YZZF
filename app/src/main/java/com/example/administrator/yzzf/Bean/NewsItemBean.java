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
    //新闻的类型（军事啥的）
    private int typeid;
    //配图的url
    private String picture;
    //跳转的url,跟id唯一相关
    private String stringUrl;
    //发布的时间
    private String displayAdddate;
    //点赞数
    private int states;
    //是否首页显示
    private String isindex;
    //点击数
    private int hits;
    //标题
    private String title;
    //所属的板块号
    private int forumId;
    //被转载的次数
    private int reprint;
    //发布用户的id
    private int usersId;
    //多久之前发布，需要计算
    private String hourAgo;
    //文章的id
    private int id;

    public int getId() {
        return id;
    }

    @TargetApi(Build.VERSION_CODES.N)
    public String getHourAgo() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//        HH:mm:ss
        //之间相差的毫秒数
        long time = 0;
        try {
            time = new Date().getTime() - format.parse(getDisplayAdddate()).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //转换成小时
        int time_hour = (int) (time / (1000 * 60 * 60));
        //转换成天
        int time_day = (int) (time / (1000 * 60 * 60 * 24));
        //转换成月
        int time_month = time_day / 30;
        //转换成年
        int time_year = time_day / 365;

        String result = "";
        if (time_hour < 24) {
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

    public String getPicture() {
        return picture;
    }

    public String getStringUrl() {
        return "http://news.sina.com.cn/o/2017-03-09/doc-ifychavf2203014.shtml";
    }

    public String getDisplayAdddate() {
        return displayAdddate;
    }

    public int getStates() {
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


    public void setTypeid(int typeid) {
        this.typeid = typeid;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }


    public void setDisplayAdddate(String displayAdddate) {
        this.displayAdddate = displayAdddate;
    }

    public void setStates(int states) {
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

    public String toString() {
        return "NewsItem " + "[title=" + this.title + ", date=" + this.displayAdddate + ", num=" + this.states + ", newsType=" + "]";
    }

    public void setId(int id) {
        this.id = id;
    }
}
