package com.example.administrator.yzzf.Bean;

import static android.R.attr.type;

/**
 * Created by Administrator on 2017/3/9 0009.
 */

public class NewsItemBean {
    private int id;
    private int newsType;
    private String imageUrl;
    private String stringUrl;
    private String date;
    private String num;
    private String title;

    public void setId(int id) {
        this.id = id;
    }

    public void setNewsType(int newsType) {
        this.newsType = newsType;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setStringUrl(String stringUrl) {
        this.stringUrl = stringUrl;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public int getNewsType() {
        return newsType;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getStringUrl() {
        return stringUrl;
    }

    public String getDate() {
        return date;
    }

    public String getNum() {
        return num;
    }

    public String getTitle() {
        return title;
    }

    public String toString() {
        return "NewsItem [id=" + this.id + ", title=" + this.title + ", date=" + this.date + ", num=" + this.num + ", newsType=" + this.newsType + "]";
    }
}
