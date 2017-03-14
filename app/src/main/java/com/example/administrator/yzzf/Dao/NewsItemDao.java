package com.example.administrator.yzzf.Dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

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
        String sql = "insert into tb_NewsItem (title,newsType,imageUrl,stringUrl,num,date,from),value(?,?,?,?,?,?,?);";
        SQLiteDatabase db = mDBHelper.getWritableDatabase();
        db.execSQL(sql,
                new Object[]{newsItemBean.getTitle(), newsItemBean.getNewsType(), newsItemBean.getImageUrl(), newsItemBean.getStringUrl(),
                        newsItemBean.getNum(), newsItemBean.getDate(), newsItemBean.getType()});
        db.close();
    }

    //添加一打新闻数据
    public void add(ArrayList<NewsItemBean> datas) {
        for (int i = 0; i < datas.size(); i++) {
            add(datas.get(i));
        }
    }

    //移除所有满足条件的数据
    public void removeAll(String whereFrom) {
        String sql = "delete from tb_NewsItem where from=?";
        SQLiteDatabase db = mDBHelper.getWritableDatabase();
        db.execSQL(sql, new Object[]{whereFrom});
        db.close();
    }

    //查询表中的数据，组装成ArrayList返回
    public ArrayList<NewsItemBean> list() {
        ArrayList<NewsItemBean> datas = new ArrayList<>();
//        String sql = "select title,newsType,imageUrl,stringUrl,num,date from tb_NewsItem";
        SQLiteDatabase db = mDBHelper.getReadableDatabase();
        Cursor cursor = db.query("tb_NewsItem", null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                NewsItemBean newsItemBean = new NewsItemBean();
                newsItemBean.setTitle(cursor.getString(cursor.getColumnIndex("title")));
                newsItemBean.setImageUrl(cursor.getString(cursor.getColumnIndex("imageUrl")));
                newsItemBean.setDate(cursor.getString(cursor.getColumnIndex("date")));
                newsItemBean.setNum(cursor.getString(cursor.getColumnIndex("num")));
                datas.add(newsItemBean);
            } while (cursor.moveToNext());
        }
        db.close();
        return datas;
    }
}
