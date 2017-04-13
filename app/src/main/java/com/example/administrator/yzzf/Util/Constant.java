package com.example.administrator.yzzf.Util;

/**
 * 储存一些常量值
 * Created by Administrator on 2017/3/10 0010.
 *
 * @author Likeke
 */

public interface Constant {
    String YZZF_APP = "yzzf_app";
    String ZY_FRAGMENT_TYPE = "zhueye_fragment_type";
    String PREVIOUS_FRAGMENT_FLAG = "previous_fragment_flag";

    //刷新新闻的url
    String NEWS_REFRESH = "http://192.168.0.168:8080/Spring/article/Index_news1(0)";
    //加载新闻的url
    String NEWS_LOAD = "http://192.168.0.168:8080/Spring/article/loading01";
    //刷新评论的url
    String PING_LUN_REFRESH = "http://192.168.0.168:8080/Spring/reply/showReplyforAndroid";
    //加载评论的url
    String PING_LUN_LOAD = "http://192.168.0.168:8080/Spring/reply/loadingforAndroid";
    //分享的链接url
    String SHARE_URL = "http://192.168.0.100:8080/web/Rendering.html";
    //选择广告
    String CHOCE_ADV = "http://192.168.0.168:8080/Spring/personalsign/personalsignInfo01";
    //写评论后与后台交互
    String ADD_REPLY = "http://192.168.0.168:8080/Spring/reply/insertReplyforAndroid";

    //通知后台文章被查看过一次
    String ARTICLE_ADD = "http://192.168.0.168:8080/Spring/article/hitsforAndroid";//参数：文章Id articleId 成功返回1
    //登陆
    String LOGIN = "http://192.168.0.168:8080/Spring/users/LoginforAndroid";//参数：手机 mobile ，密码 loginpwd
    //注册
    String ZHU_CE = "http://192.168.0.168:8080/Spring/users/usersRegisterforAndroid";//参数：手机 mobile ，密码 loginpwd  成功返回1，为空返回0，已存在返回2
    //查看用户资料
    String USER_ZL = "http://192.168.0.168:8080/Spring/users/userSelectByIdforAndroid";//参数：userId，成功返回1
    //修改用户资料
    String USER_XG = "http://192.168.0.168:8080/Spring/users/userUpdateforAndroid";//参数： id,成功返回1 其他修改的属性值，失败返回0

}
