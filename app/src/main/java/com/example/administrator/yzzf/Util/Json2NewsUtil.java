package com.example.administrator.yzzf.Util;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import com.example.administrator.yzzf.Bean.NewsItemBean;
import com.example.administrator.yzzf.Bean.UserMessageBean;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

import static com.sina.weibo.sdk.openapi.legacy.AccountAPI.CAPITAL.J;

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
                        newsItemBean.setNewsType(news_json.getInt("newsType"));
                        newsItemBean.setDate(news_json.getString("date"));
                        newsItemBean.setImageUrl(news_json.getString("imageUrl"));
                        newsItemBean.setStringUrl(news_json.getString("stringUrl"));
                        newsItemBean.setTitle(news_json.getString("title"));
                        newsItemBean.setNum(news_json.getString("num"));
                        newsItemBean.setType(news_json.getString("type"));

                        Log.i("NewsItemBean", newsItemBean.toString());
                        newsItems.add(newsItemBean);
                    }
                    //获取到了新的数据，就删除手机上旧的数据
                    //TODO

                    is.close();//关闭输入流
                    connection.disconnect();
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

    //获取用户信息
    public static ArrayList<UserMessageBean> getUserMessages(Context context, final String urlString) throws Exception {
        final ArrayList<UserMessageBean> userMessages = new ArrayList<>();
        //用FutureTask来完成网络请求的异步，HttpUrlConnection完成具体的网络请求
        FutureTask<ArrayList<UserMessageBean>> futureTask = new FutureTask<>(new Callable<ArrayList<UserMessageBean>>() {
            @Override
            public ArrayList<UserMessageBean> call() throws Exception {
                URL url = new URL(urlString);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.setConnectTimeout(10 * 1000);
                int requestCode = connection.getResponseCode();
                if (requestCode == 200) {
                    //请求数据成功
                    InputStream is = connection.getInputStream();
                    String result = StreamUtil.convertStream(is);//获取请求结果，json字符串
                    JSONArray jsonArray = new JSONArray(result);
                    for (int i = 0; i < 1; i++) {
                        JSONObject users_json = jsonArray.getJSONObject(i);
                        UserMessageBean userMessage = new UserMessageBean();
                        userMessage.setRealName(users_json.getString("realname"));
                        userMessage.setNickName((users_json.getString("nickname")));
                        userMessage.setSex(users_json.getString("sex"));
                        userMessage.setDisplayBirthday(users_json.getString("displayBirthday"));
                        userMessage.setAddress(users_json.getString("address"));
                        userMessage.setMobile(users_json.getString("mobile"));
                        userMessage.setDistrictId(users_json.getInt("districtid"));
                        userMessage.setLoginPWD(users_json.getString("loginpwd"));
                        userMessage.setFlag(users_json.getString("flag"));
                        userMessage.setLevels(users_json.getInt("levels"));
                        userMessage.setScore(users_json.getDouble("score"));
                        userMessage.setDisplayAdddate(users_json.getString("displayAdddate"));
                        userMessage.setMoney(users_json.getDouble("money"));
                        userMessages.add(userMessage);
                    }
                    is.close();//关闭输入流
                    connection.disconnect();
                    return userMessages;
                }
                return null;
            }
        });
        new Thread(futureTask).start();//开启子线程执行futureTask，FutureTask本质是一个Runnable
        return futureTask.get();

    }

}
