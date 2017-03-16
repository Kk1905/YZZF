package com.example.administrator.yzzf.Dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Administrator on 2017/3/10 0010.
 */

public class DBHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "yzzf_app_db";
    private Context mContext;

    public DBHelper(Context context) {

        super(context, DB_NAME, null, 8);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //创建新闻条目表
        String sql = "create table tb_NewsItem(" +
                "_id integer primary key autoincrement," +
                "title text," +
                "newsType integer," +
                "imageUrl text," +
                "stringUrl text," +
                "num text," +
                "date text," +
                "type text)";
        //创建新闻交互表
        String sql1 = "create table tb_NewsView(" +
                "_id integer primary key autoincrement," +
                "newsType integer," +
                "title text," +
                "content text," +
                "userImage text," +
                "userName text," +
                "viewNum text," +
                "pinglunNum text)";

        //创建用户信息的表
        String sql2 = "create table tb_UserMessage(" +
                "_id integer primary key autoincrement," +
                "address text," +
                "flag integer," +
                "loginpwd text," +
                "realname text," +
                "levels integer," +
                "displayBirthday text," +
                "displayAdddate text," +
                "nickname text," +
                "money real," +
                "sex text," +
                "score real," +
                "mobile text," +
                "districtid integer)";
        db.execSQL(sql);
        db.execSQL(sql2);
        Log.e("kkkboy", "创建数据库");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("drop table if exist tb_NewsItem");
        db.execSQL("drop table if exist tb_UserMessage");
        onCreate(db);
        Log.e("kkkboy", "更新数据库");
    }
}
