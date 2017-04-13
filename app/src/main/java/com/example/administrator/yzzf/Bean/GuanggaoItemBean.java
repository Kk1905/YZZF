package com.example.administrator.yzzf.Bean;

/**
 * Created by Administrator on 2017/4/6 0006.
 */

public class GuanggaoItemBean {
    //广告编号
    private int id;
    //用户编号
    private int userId;
    //标题
    private String title;
    //图片路径
    private String picture;
    //链接
    private String link;
    //状态
    private String flag;
    //点击数
    private int hits;
    //分享返还积分值
    private double score;
    //是否公开
    private String publish;
    //广告类型
    private String types;
    //余额
    private double balance;

    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public String getTitle() {
        return title;
    }

    public String getPicture() {
        return picture;
    }

    public String getLink() {
        return link;
    }

    public String getFlag() {
        return flag;
    }

    public int getHits() {
        return hits;
    }

    public double getScore() {
        return score;
    }

    public String isPublish() {
        return publish;
    }

    public String getTypes() {
        return types;
    }

    public double getBalance() {
        return balance;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public void setHits(int hits) {
        this.hits = hits;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public void setPublish(String publish) {
        this.publish = publish;
    }

    public void setTypes(String types) {
        this.types = types;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
