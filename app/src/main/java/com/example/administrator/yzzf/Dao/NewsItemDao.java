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
    //数据库里面缓存的新闻总共有多少条，限制200条
    private int sum = 0;

    public int getSum() {
        String sql = "select * from tb_NewsItem";
        SQLiteDatabase db = mDBHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        return cursor.getCount();
    }

    public NewsItemDao(Context context) {
        mDBHelper = new DBHelper(context);
        sum = getSum();
    }

    //添加一项新闻数据
    public void add(NewsItemBean newsItemBean) {
        String sql = "insert into tb_NewsItem (typeid,picture1,picture2,picture3,displayAdddate,states,isindex,hits,title,forumid,reprint,usersid,id,source)" +
                "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        SQLiteDatabase db = mDBHelper.getWritableDatabase();
//        sum += 1;
//        if (getSum() > 300) {
//            //缓存数据超过300条，就不加一条最新的，然后删除第一个最旧的，也就是_id=0
//            db.execSQL(sql,
//                    new Object[]{newsItemBean.getTypeid(), newsItemBean.getPicture1(), newsItemBean.getPicture2(), newsItemBean.getPicture3(), newsItemBean.getDisplayAdddate(),
//                            newsItemBean.getStates(), newsItemBean.getIsindex(), newsItemBean.getHits(), newsItemBean.getTitle(), newsItemBean.getForumId(),
//                            newsItemBean.getReprint(), newsItemBean.getUsersId(), newsItemBean.getId(), newsItemBean.getSource()});
//            removeOne();
//        } else if (getSum() > 500) {
//            //大于500条缓存，就清楚所有缓存
//            removeAll();
//        } else {
            //否则就一直往里添加数据就好了
            db.execSQL(sql,
                    new Object[]{newsItemBean.getTypeid(), newsItemBean.getPicture1(), newsItemBean.getPicture2(), newsItemBean.getPicture3(), newsItemBean.getDisplayAdddate(),
                            newsItemBean.getStates(), newsItemBean.getIsindex(), newsItemBean.getHits(), newsItemBean.getTitle(), newsItemBean.getForumId(),
                            newsItemBean.getReprint(), newsItemBean.getUsersId(), newsItemBean.getId(), newsItemBean.getSource()});
//        }
        db.close();
        Log.e("kkkboy", "添加一条主页新闻数据成功");
    }

    //添加一打新闻数据
    public void add(ArrayList<NewsItemBean> datas) {

        if (getSum() > 500) {
            //大于500条缓存，就清楚所有缓存
            removeAll();
        }
        for (int i = 0; i < datas.size(); i++) {
            add(datas.get(i));
        }
        Log.e("kkkboy", "添加一打主页新闻数据成功" + "---新闻总数-->" + getSum());
    }

    //移除显示在首页上的新闻，isindex=1
    public void removeAllisIndex(String isindex) {
        String sql = "delete from tb_NewsItem where isindex=?";
        SQLiteDatabase db = mDBHelper.getWritableDatabase();
        db.execSQL(sql, new Object[]{isindex});
        db.close();
        Log.e("kkkboy", "删除主页新闻数据成功" + "---新闻总数-->" + getSum());
    }

    //移除特定版块的新闻，forumid=?
    public void removeAllforumid(int forumid) {
        String sql = "delete from tb_NewsItem where forumid=?";
        SQLiteDatabase db = mDBHelper.getWritableDatabase();
        db.execSQL(sql, new Object[]{forumid});
        db.close();
        Log.e("kkkboy", "删除版块" + forumid + "新闻数据成功" + "---新闻总数-->" + getSum());
    }

    //移除数据表第一条数据
    public void removeOne() {
        String sql = "delete from tb_NewsItem where _id=0";
        SQLiteDatabase db = mDBHelper.getWritableDatabase();
        db.execSQL(sql);
        db.close();
        Log.e("kkkboy", "删除新闻数据表第一个数据成功" + "---新闻总数-->" + getSum());
    }

    //移除数据表中的所有新闻缓存
    public void removeAll() {
        String sql = "truncate table tb_NewsItem";
        SQLiteDatabase db = mDBHelper.getWritableDatabase();
        db.execSQL(sql);
        db.close();
        Log.e("kkkboy", "删除新闻数据表所有缓存数据成功" + "---新闻总数-->" + getSum());
    }

    //查询表中的数据，组装成ArrayList返回
    public ArrayList<NewsItemBean> list(String isindex, int currentPage) {
        ArrayList<NewsItemBean> datas = new ArrayList<>();
        int offset = 10 * currentPage;
        String sql = "select * from tb_NewsItem where isindex=? limit ?,?";//限定每次加载10个新闻条目
        SQLiteDatabase db = mDBHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, new String[]{isindex, offset + "", offset + 10 + ""});

        if (cursor.moveToFirst()) {
            do {
                NewsItemBean newsItemBean = new NewsItemBean();
                newsItemBean.setTitle(cursor.getString(cursor.getColumnIndex("title")));
                newsItemBean.setPicture1(cursor.getString(cursor.getColumnIndex("picture1")));
                newsItemBean.setPicture2(cursor.getString(cursor.getColumnIndex("picture2")));
                newsItemBean.setPicture3(cursor.getString(cursor.getColumnIndex("picture3")));
                newsItemBean.setDisplayAdddate(cursor.getString(cursor.getColumnIndex("displayAdddate")));
                newsItemBean.setId(cursor.getInt(cursor.getColumnIndex("id")));//跳转的url是根据id来的
                newsItemBean.setHits(cursor.getInt(cursor.getColumnIndex("hits")));
                newsItemBean.setStates(cursor.getString(cursor.getColumnIndex("states")));
                newsItemBean.setSource(cursor.getString(cursor.getColumnIndex("source")));
                datas.add(newsItemBean);
            } while (cursor.moveToNext());
        }
        Log.e("kkkboy", "查询主页新闻数据成功" + "sum-------------->" + getSum() + "------------>" + currentPage);
        db.close();
        cursor.close();
        return datas;
    }

    //查询表中的数据，组装成ArrayList返回
    public ArrayList<NewsItemBean> listByForumid(int forumid, int currentPage) {
        ArrayList<NewsItemBean> datas = new ArrayList<>();
        int offset = 10 * currentPage;
        String sql = "select * from tb_NewsItem where forumid=? limit ?,?";//限定每次加载10个新闻条目
        SQLiteDatabase db = mDBHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, new String[]{forumid + "", offset + "", offset + 10 + ""});

        if (cursor.moveToFirst()) {
            do {
                NewsItemBean newsItemBean = new NewsItemBean();
                newsItemBean.setTitle(cursor.getString(cursor.getColumnIndex("title")));
                newsItemBean.setPicture1(cursor.getString(cursor.getColumnIndex("picture1")));
                newsItemBean.setPicture2(cursor.getString(cursor.getColumnIndex("picture2")));
                newsItemBean.setPicture3(cursor.getString(cursor.getColumnIndex("picture3")));
                newsItemBean.setDisplayAdddate(cursor.getString(cursor.getColumnIndex("displayAdddate")));
                newsItemBean.setId(cursor.getInt(cursor.getColumnIndex("id")));//跳转的url是根据id来的
                newsItemBean.setHits(cursor.getInt(cursor.getColumnIndex("hits")));
                newsItemBean.setStates(cursor.getString(cursor.getColumnIndex("states")));
                newsItemBean.setSource(cursor.getString(cursor.getColumnIndex("source")));
                datas.add(newsItemBean);
            } while (cursor.moveToNext());
        }
        Log.e("kkkboy", "查询版块新闻数据成功" + "sum-------------->" + getSum() + "------------>" + currentPage);
        db.close();
        cursor.close();
        return datas;
    }
}
