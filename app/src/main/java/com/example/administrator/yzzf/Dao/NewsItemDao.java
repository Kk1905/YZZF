package com.example.administrator.yzzf.Dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.administrator.yzzf.Bean.NewsItemBean;

import java.util.ArrayList;

/**
 * 对新闻条目的表操作的数据访问层
 * Created by Administrator on 2017/3/10 0010.
 *
 * @author Likeke
 */

public class NewsItemDao {
    private DBHelper mDBHelper;

    public NewsItemDao(Context context) {
        mDBHelper = new DBHelper(context);
    }

    //添加一项新闻数据
    public void add(NewsItemBean newsItemBean) {
        String sql = "insert into tb_NewsItem (typeid,picture,displayAdddate,states,isindex,hits,title,forumid,reprint,usersid,id)" +
                "values(?,?,?,?,?,?,?,?,?,?,?);";
        SQLiteDatabase db = mDBHelper.getWritableDatabase();
        db.execSQL(sql,
                new Object[]{newsItemBean.getTypeid(), newsItemBean.getPicture(), newsItemBean.getDisplayAdddate(),
                        newsItemBean.getStates(), newsItemBean.getIsindex(), newsItemBean.getHits(), newsItemBean.getTitle(), newsItemBean.getForumId(),
                        newsItemBean.getReprint(), newsItemBean.getUsersId(), newsItemBean.getId()});
        db.close();
        Log.e("kkkboy", "添加一条主页新闻数据成功");
    }

    //添加一打新闻数据
    public void add(ArrayList<NewsItemBean> datas) {
        for (int i = 0; i < datas.size(); i++) {
            add(datas.get(i));
        }
        Log.e("kkkboy", "添加一打主页新闻数据成功");
    }

    //移除所有满足条件的数据,显示在首页上的新闻，isindex=1
    public void removeAll(String isindex) {
        String sql = "delete from tb_NewsItem where isindex=?";
        SQLiteDatabase db = mDBHelper.getWritableDatabase();
        db.execSQL(sql, new Object[]{isindex});
        db.close();
        Log.e("kkkboy", "删除主页新闻数据成功");
    }

    //查询表中的数据，组装成ArrayList返回
    public ArrayList<NewsItemBean> list(String isindex) {
        ArrayList<NewsItemBean> datas = new ArrayList<>();
        String sql = "select * from tb_NewsItem where isindex=?";
        SQLiteDatabase db = mDBHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, new String[]{isindex});

        if (cursor.moveToFirst()) {
            do {
                NewsItemBean newsItemBean = new NewsItemBean();
                newsItemBean.setTitle(cursor.getString(cursor.getColumnIndex("title")));
                newsItemBean.setPicture(cursor.getString(cursor.getColumnIndex("picture")));
                newsItemBean.setDisplayAdddate(cursor.getString(cursor.getColumnIndex("displayAdddate")));
                newsItemBean.setId(cursor.getInt(cursor.getColumnIndex("id")));
                newsItemBean.setHits(cursor.getInt(cursor.getColumnIndex("hits")));
                datas.add(newsItemBean);
            } while (cursor.moveToNext());
        }
        Log.e("kkkboy", "查询主页新闻数据成功");
        db.close();
        cursor.close();
        return datas;
    }
}
