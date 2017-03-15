package com.example.administrator.yzzf.Bean;

/**
 * Created by Administrator on 2017/3/14 0014.
 */

public class UserMessageBean {
    private String address;
    private double score;
    private String nickName;
    private String realName;
    private String birthday;
    private int districtId;
    private String mobile;
    private String money;
    private String sex;
    private String loginPWD;
    private String diplayBirthday;

    public String getAddress() {
        return address;
    }

    public double getScore() {
        return score;
    }

    public String getNickName() {
        return nickName;
    }

    public String getRealName() {
        return realName;
    }

    public String getBirthday() {
        return birthday;
    }

    public double getDistrictId() {
        return districtId;
    }

    public String getMobile() {
        return mobile;
    }

    public String getMoney() {
        return money;
    }

    public String getSex() {
        return sex;
    }

    public String getLoginPWD() {
        return loginPWD;
    }

    public String getDiplayBirthday() {
        return diplayBirthday;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public void setDistrictId(int districtId) {
        this.districtId = districtId;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setLoginPWD(String loginPWD) {
        this.loginPWD = loginPWD;
    }

    public void setDiplayBirthday(String diplayBirthday) {
        this.diplayBirthday = diplayBirthday;
    }

    @Override
    public String toString() {
        return "姓名"+realName+" 昵称"+nickName+" 性别"+sex+" 生日"+birthday+" 所在城市"+districtId+" 住址"+address+" 手机"+mobile+" 密码"+loginPWD;
    }
}
