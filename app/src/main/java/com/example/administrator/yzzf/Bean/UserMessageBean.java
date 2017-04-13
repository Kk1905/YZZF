package com.example.administrator.yzzf.Bean;

/**
 * Created by Administrator on 2017/3/14 0014.
 */

public class UserMessageBean {
    /**
     * "address":null,   ------------->用户的地址
     * "id":47, ----------->用户的id
     * "flag":"1",
     * "displayAdddate":null,
     * "displayBirthday":null,
     * "money":0.0,
     * "loginpwd":"812240",   ----------->密码
     * "adddate":1491408000000,
     * "mobile":"13862930224",   ----------------->手机号
     * "score":123.0,       -------------->积分
     * "levels":0,
     * "nickname":null,   -------------->昵称
     * "realname":null,    ------------->真名
     * "sex":null,
     * "districtid":null,     ------------->城市的id号，暂时不用了
     * "birthday":null
     */
    private int id;
    private String address;
    private double score;
    private String nickName;
    private String realName;
    private int districtId;
    private String mobile;
    private double money;
    private String sex;
    private String loginPWD;
    private String displayBirthday;
    private String flag;
    private String displayAdddate;
    private int levels;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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


    public String getFlag() {
        return flag;
    }

    public String getDisplayAdddate() {
        return displayAdddate;
    }

    public int getLevels() {
        return levels;
    }

    public double getDistrictId() {
        return districtId;
    }

    public String getMobile() {
        return mobile;
    }

    public double getMoney() {
        return money;
    }

    public String getSex() {
        return sex;
    }

    public String getLoginPWD() {
        return loginPWD;
    }

    public String getDisplayBirthday() {
        return displayBirthday;
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


    public void setDistrictId(int districtId) {
        this.districtId = districtId;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setLoginPWD(String loginPWD) {
        this.loginPWD = loginPWD;
    }

    public void setDisplayBirthday(String diplayBirthday) {
        this.displayBirthday = diplayBirthday;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }


    public void setDisplayAdddate(String displayAdddate) {
        this.displayAdddate = displayAdddate;
    }

    public void setLevels(int levels) {
        this.levels = levels;
    }

    @Override
    public String toString() {
        return address + flag + loginPWD + realName + levels + displayBirthday + displayAdddate + nickName + money + sex + score + mobile + districtId;
    }
}
