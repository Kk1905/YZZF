package com.example.administrator.yzzf.Bean;

/**
 * Created by Administrator on 2017/3/20 0020.
 */

public class WdddItemBean {
    private String picture;
    private String title;
    private double danjia;
    private int shuliang;
    private double heji;
    private String fukuanState;
    private String quxiaoState;

    public String getPicture() {
        return picture;
    }

    public String getTitle() {
        return title;
    }

    public double getDanjia() {
        return danjia;
    }

    public int getShuliang() {
        return shuliang;
    }

    public double getHeji() {
        return heji;
    }

    public String getFukuanState() {
        return fukuanState;
    }

    public String getQuxiaoState() {
        return quxiaoState;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDanjia(double danjia) {
        this.danjia = danjia;
    }

    public void setShuliang(int shuliang) {
        this.shuliang = shuliang;
    }

    public double setHeji() {
        return getDanjia() * getShuliang();

    }

    public void setFukuanState(String fukuanState) {
        this.fukuanState = fukuanState;
    }

    public void setQuxiaoState(String quxiaoState) {
        this.quxiaoState = quxiaoState;
    }
}
