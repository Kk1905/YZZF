package com.example.administrator.yzzf.Dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by Administrator on 2017/3/10 0010.
 */

public class DBHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "yzzf_app_db";
    private Context mContext;

    public DBHelper(Context context) {

        super(context, DB_NAME, null, 3);
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

        Toast.makeText(mContext, "db_create", Toast.LENGTH_SHORT).show();
        db.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //TODO 数据库的升级工作
        db.execSQL("drop table if exist tb_NewsItem");
        onCreate(db);
    }
}
