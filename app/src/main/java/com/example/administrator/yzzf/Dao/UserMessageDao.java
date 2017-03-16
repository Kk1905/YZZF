package com.example.administrator.yzzf.Dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import com.example.administrator.yzzf.Bean.UserMessageBean;
import java.util.ArrayList;


/**
 * 对用户信息表操作
 * Created by Administrator on 2017/3/15 0015.
 */

public class UserMessageDao {
    private DBHelper mDBHelper;

    public UserMessageDao(Context context) {
        mDBHelper = new DBHelper(context);
    }

    //添加一条用户信息
    public void addUserMessage(UserMessageBean userMessage) {
        String sql = "insert into tb_UserMessage(address,flag,loginpwd,realname,levels,displayBirthday,displayAdddate,nickname,money,sex,score,mobile,districtid)" +
                "values(?,?,?,?,?,?,?,?,?,?,?,?,?);";
        SQLiteDatabase mDatabase = mDBHelper.getWritableDatabase();
        mDatabase.execSQL(sql, new Object[]{userMessage.getAddress(), userMessage.getFlag(), userMessage.getLoginPWD(), userMessage.getRealName(),
                userMessage.getLevels(), userMessage.getDisplayBirthday(), userMessage.getDisplayAdddate(), userMessage.getNickName(),
                userMessage.getMoney(), userMessage.getSex(), userMessage.getScore(), userMessage.getMobile(), userMessage.getDistrictId()});
        mDatabase.close();
        Log.e("kkkboy", "添加用户信息成功");
    }

    //添加N多条用户信息
    public void addAll(ArrayList<UserMessageBean> datas) {
        for (UserMessageBean userMessage : datas) {
            addUserMessage(userMessage);
        }
        Log.e("kkkboy", "添加多条用户信息成功");
    }

    //删除用户信息
    public void removeAll(String realname) {
        String sql = "delete from tb_UserMessage where realname=?;";
        SQLiteDatabase mDatabase = mDBHelper.getWritableDatabase();
        mDatabase.execSQL(sql, new Object[]{realname});
        mDatabase.close();
        Log.e("kkkboy", "删除用户信息成功");
    }

    //查询用户信息
    public UserMessageBean getUserMessage(String realname) {
        try {
            String sql = "select * from tb_UserMessage where realname=?;";
            SQLiteDatabase mDatabase = mDBHelper.getReadableDatabase();
            Cursor cursor = mDatabase.rawQuery(sql, new String[]{realname});
            UserMessageBean userMessage = new UserMessageBean();
            //此处只是模拟查询第一条满足条件的
            if (cursor.moveToFirst()) {
                userMessage.setAddress(cursor.getString(cursor.getColumnIndex("address")));
                userMessage.setFlag(cursor.getString(cursor.getColumnIndex("flag")));
                userMessage.setLoginPWD(cursor.getString(cursor.getColumnIndex("loginpwd")));
                userMessage.setRealName(cursor.getString(cursor.getColumnIndex("realname")));
                userMessage.setLevels(cursor.getInt(cursor.getColumnIndex("levels")));
                userMessage.setDisplayBirthday(cursor.getString(cursor.getColumnIndex("displayBirthday")));
                userMessage.setDisplayAdddate(cursor.getString(cursor.getColumnIndex("displayAdddate")));
                userMessage.setNickName(cursor.getString(cursor.getColumnIndex("nickname")));
                userMessage.setMoney(cursor.getDouble(cursor.getColumnIndex("money")));
                userMessage.setSex(cursor.getString(cursor.getColumnIndex("sex")));
                userMessage.setScore(cursor.getDouble(cursor.getColumnIndex("score")));
                userMessage.setMobile(cursor.getString(cursor.getColumnIndex("mobile")));
                userMessage.setDistrictId(cursor.getInt(cursor.getColumnIndex("districtid")));

                return userMessage;

            }
            cursor.close();
            mDatabase.close();
            Log.e("kkkboy", "查询用户信息成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
