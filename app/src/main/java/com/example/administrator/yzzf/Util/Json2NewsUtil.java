package com.example.administrator.yzzf.Util;

import android.content.Context;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import com.example.administrator.yzzf.Bean.NewsItemBean;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * 利用StreamUtil从网上获取json数据，然后解析json数据的工具类
 * 要注意，它是获取并解析两个功能，整个是运行在子线程的
 * Created by Administrator on 2017/3/9 0009.
 *
 * @author Likeke
 */

public class Json2NewsUtil {
    //获取新闻条目
    public static ArrayList<NewsItemBean> getNewsItem(Context context, final String urlString) throws Exception {
        final ArrayList<NewsItemBean> newsItems = new ArrayList<>();
        //用FutureTask来完成网络请求的异步，HttpUrlConnection完成具体的网络请求
        FutureTask<ArrayList<NewsItemBean>> futureTask = new FutureTask<ArrayList<NewsItemBean>>(new Callable<ArrayList<NewsItemBean>>() {
            @Override
            public ArrayList<NewsItemBean> call() throws Exception {
                URL url = new URL(urlString);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.setConnectTimeout(10 * 1000);
                int requestCode = connection.getResponseCode();
                if (requestCode == 200) {
                    //请求数据成功
                    InputStream is = connection.getInputStream();
                    String result = StreamUtil.convertStream(is);//获取请求结果，json字符串
                    Log.i("JSON", result);
                    JSONObject jsonObject = new JSONObject(result);
                    JSONArray jsonArray = jsonObject.getJSONArray("newsItem");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject news_json = jsonArray.getJSONObject(i);
                        NewsItemBean newsItemBean = new NewsItemBean();
                        newsItemBean.setId(news_json.getInt("id"));
                        newsItemBean.setNewsType(news_json.getInt("newsType"));
                        newsItemBean.setDate(news_json.getString("date"));
                        newsItemBean.setImageUrl(news_json.getString("imageUrl"));
                        newsItemBean.setStringUrl(news_json.getString("stringUrl"));
                        newsItemBean.setTitle(news_json.getString("title"));
                        newsItemBean.setNum(news_json.getString("num"));

                        Log.i("NewsItemBean", newsItemBean.toString());
                        newsItems.add(newsItemBean);
                    }
                    //获取到了新的数据，就删除手机上旧的数据
                    //TODO

                    is.close();//关闭输入流
                    return newsItems;
                }
                return null;
            }
        });
        new Thread(futureTask).start();//开启子线程执行futureTask，FutureTask本质是一个Runnable
        return futureTask.get();
    }

    public static ArrayList<NewsItemBean> test(Context context) {
        String url = Environment.getExternalStorageDirectory().getAbsolutePath() + "/news.json";
        File file = new File(url);
        String datas = "";
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                datas = datas + line;
            }
            bufferedReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ArrayList<NewsItemBean> testArray = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(datas);
            JSONArray jsonArray = jsonObject.getJSONArray("newsItem");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject newsItem = jsonArray.getJSONObject(i);
                NewsItemBean bean = new NewsItemBean();
                bean.setId(newsItem.getInt("id"));
                bean.setNewsType(newsItem.getInt("newsType"));
                bean.setTitle(newsItem.getString("title"));
                bean.setNum(newsItem.getString("num"));
                bean.setStringUrl(newsItem.getString("stringUrl"));
                bean.setImageUrl(newsItem.getString("imageUrl"));
                bean.setDate(newsItem.getString("date"));

                testArray.add(bean);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return testArray;
    }

    //获取新闻详情,错了，详情页面最好就用webview来显示，不然Adapter不好适配
//    public static  ArrayList<NewsContent> getNews_xiangqing(Context context, final String urlString) throws Exception{
//        final ArrayList<NewsContent> newsContents = new ArrayList<>();
//        FutureTask<ArrayList<NewsContent>> futureTask=new FutureTask<ArrayList<NewsContent>>(new Callable<ArrayList<NewsContent>>() {
//            @Override
//            public ArrayList<NewsContent> call() throws Exception {
//                URL url = new URL(urlString);
//                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//                connection.setRequestMethod("GET");
//                connection.setConnectTimeout(10 * 1000);
//                if (connection.getResponseCode() == 200) {
//                    InputStream is = connection.getInputStream();
//                    String result = StreamUtil.convertStream(is);
//                    Log.i("JSON", result);
//                    JSONObject jsonObject = new JSONObject(result);
//                    JSONArray jsonArray = jsonObject.getJSONArray("news");
//                    for (int i=0;i<jsonArray.length();i++) {
//                        NewsContent news_xiangqingBean = new NewsContent();
//                        JSONObject news_xiangqing = jsonArray.getJSONObject(i);
//                        news_xiangqingBean.setId(news_xiangqing.getInt("id"));
//                        news_xiangqingBean.setType(news_xiangqing.getInt("type"));
//
//
//                        newsContents.add(news_xiangqingBean);
//                    }
//                    //删除旧数据
//                    //TODO
//
//
//                    is.close();
//                    return newsContents;
//                }
//
//
//                return null;
//            }
//        });
//        new Thread(futureTask).start();
//        return futureTask.get();
//    }
}
