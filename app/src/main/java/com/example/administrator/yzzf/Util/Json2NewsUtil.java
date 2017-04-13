package com.example.administrator.yzzf.Util;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.os.Environment;
import android.util.Log;

import com.example.administrator.yzzf.Bean.GuanggaoItemBean;
import com.example.administrator.yzzf.Bean.NewsItemBean;
import com.example.administrator.yzzf.Bean.PinglunItemBean;
import com.example.administrator.yzzf.Bean.UserMessageBean;
import com.example.administrator.yzzf.Bean.WdddItemBean;

import org.apache.http.params.HttpConnectionParams;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

import static android.R.attr.name;
import static com.sina.weibo.sdk.openapi.legacy.AccountAPI.CAPITAL.J;
import static com.sina.weibo.sdk.openapi.legacy.AccountAPI.CAPITAL.S;

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
            @TargetApi(Build.VERSION_CODES.N)
            @Override
            public ArrayList<NewsItemBean> call() throws Exception {
                URL url = new URL(urlString);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.setConnectTimeout(1500);
                int requestCode = connection.getResponseCode();
                if (requestCode == 200) {
                    //请求数据成功
                    InputStream is = connection.getInputStream();
                    String result = StreamUtil.convertStream(is);//获取请求结果，json字符串

                    JSONArray jsonArray = new JSONArray(result);

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject news_json = jsonArray.getJSONObject(i);
                        NewsItemBean newsItemBean = new NewsItemBean();
                        newsItemBean.setTitle(news_json.getString("title"));
                        newsItemBean.setTypeid(news_json.getInt("typeid"));
                        newsItemBean.setId(news_json.getInt("id"));
                        newsItemBean.setDisplayAdddate(news_json.getString("displayAdddate"));
                        newsItemBean.setForumId(news_json.getInt("forumid"));
                        newsItemBean.setHits(news_json.getInt("hits"));
                        newsItemBean.setIsindex(news_json.getString("isindex"));
                        newsItemBean.setPicture1(news_json.getString("picture1"));
                        newsItemBean.setPicture2(news_json.getString("picture2"));
                        newsItemBean.setPicture3(news_json.getString("picture3"));
                        newsItemBean.setReprint(news_json.getInt("reprint"));
                        newsItemBean.setStates(news_json.getString("states"));
                        newsItemBean.setUsersId(news_json.getInt("usersid"));
                        newsItemBean.setSource((news_json.getString("source")));
                        newsItemBean.setContent(news_json.getString("content"));

                        newsItems.add(newsItemBean);
                    }
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

    //获取广告
    public static ArrayList<GuanggaoItemBean> getGuanggaoItem(Context context, final String urlString) throws Exception {
        final ArrayList<GuanggaoItemBean> guanggaoItemBeans = new ArrayList<>();
        //用FutureTask来完成网络请求的异步，HttpUrlConnection完成具体的网络请求
        FutureTask<ArrayList<GuanggaoItemBean>> futureTask = new FutureTask<ArrayList<GuanggaoItemBean>>(new Callable<ArrayList<GuanggaoItemBean>>() {
            @TargetApi(Build.VERSION_CODES.N)
            @Override
            public ArrayList<GuanggaoItemBean> call() throws Exception {
                URL url = new URL(urlString);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.setConnectTimeout(2 * 1000);
                int requestCode = connection.getResponseCode();
                if (requestCode == 200) {
                    //请求数据成功
                    InputStream is = connection.getInputStream();
                    String result = StreamUtil.convertStream(is);//获取请求结果，json字符串
                    JSONArray jsonArray = new JSONArray(result);

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        GuanggaoItemBean guanggaoItemBean = new GuanggaoItemBean();
                        guanggaoItemBean.setId(jsonObject.getInt("id"));
                        guanggaoItemBean.setUserId(jsonObject.getInt("userid"));
                        guanggaoItemBean.setTitle(jsonObject.getString("title"));
                        guanggaoItemBean.setPicture(jsonObject.getString("picture"));
                        guanggaoItemBean.setLink(jsonObject.getString("link"));
                        guanggaoItemBean.setFlag(jsonObject.getString("flag"));
                        guanggaoItemBean.setHits(jsonObject.getInt("hits"));
                        guanggaoItemBean.setScore(jsonObject.getDouble("score"));
                        guanggaoItemBean.setPublish(jsonObject.getString("publish"));
                        guanggaoItemBean.setTypes(jsonObject.getString("types"));
//                        guanggaoItemBean.setBalance(jsonObject.getDouble("balance"));

                        guanggaoItemBeans.add(guanggaoItemBean);
                    }
                    is.close();//关闭输入流
                    connection.disconnect();
                    return guanggaoItemBeans;
                }
                return null;
            }
        });
        new Thread(futureTask).start();//开启子线程执行futureTask，FutureTask本质是一个Runnable
        return futureTask.get();
    }

    public static String getString(Context context) {
        String url = Environment.getExternalStorageDirectory().getAbsolutePath() + "/content03.html";
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
//        ArrayList<NewsItemBean> testArray = new ArrayList<>();
//        try {
//            JSONObject jsonObject = new JSONObject(datas);
//            JSONArray jsonArray = jsonObject.getJSONArray("newsItem");
//            for (int i = 0; i < jsonArray.length(); i++) {
//                JSONObject newsItem = jsonArray.getJSONObject(i);
//                NewsItemBean bean = new NewsItemBean();
//
//
//                testArray.add(bean);
//            }
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
        return datas;
    }

    //获取用户信息
    public static UserMessageBean getUserMessages(Context context, final String urlString) throws Exception {
//        final ArrayList<UserMessageBean> userMessages = new ArrayList<>();
        //用FutureTask来完成网络请求的异步，HttpUrlConnection完成具体的网络请求
        FutureTask<UserMessageBean> futureTask = new FutureTask<>(new Callable<UserMessageBean>() {
            @Override
            public UserMessageBean call() throws Exception {
                URL url = new URL(urlString);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.setConnectTimeout(2 * 1000);
                int requestCode = connection.getResponseCode();
                if (requestCode == 200) {
                    //请求数据成功
                    InputStream is = connection.getInputStream();
                    String result = StreamUtil.convertStream(is);//获取请求结果，json字符串

                    //在这边判断，因为服务器会返回空给我
                    if (result == null || result.equals("") || result.equals("null")) {
                        return null;
                    } else {
                        JSONObject jsonObject = new JSONObject(result);

                        UserMessageBean userMessage = new UserMessageBean();

                        String realname = jsonObject.getString("realname");
                        String nickname = jsonObject.getString("nickname");
                        String sex = jsonObject.getString("sex");
                        String address = jsonObject.getString("address");
//                    Log.e("kkkboy", "realname-------------->" + realname.toString());
                        userMessage.setId(jsonObject.getInt("id"));
                        if (realname.equals("null") || realname == null || realname.equals("")) {
                            realname = "您还未填写姓名";
                        }
                        userMessage.setRealName(realname);
                        if (nickname.equals("null") || nickname == null || nickname.equals("")) {
                            nickname = "您还未填写昵称";
                        }
                        userMessage.setNickName(nickname);
                        if (sex.equals("null") || sex == null || sex.equals("")) {
                            sex = "您还未填写性别";
                        }
                        userMessage.setSex(sex);
                        userMessage.setDisplayBirthday(jsonObject.getString("displayBirthday"));
                        if (address.equals("null") || address == null || address.equals("")) {
                            address = "您还未填写住址";
                        }
                        userMessage.setAddress(address);
                        userMessage.setMobile(jsonObject.getString("mobile"));
//                        userMessage.setDistrictId(users_json.getInt("districtid"));
                        userMessage.setLoginPWD(jsonObject.getString("loginpwd"));
                        userMessage.setFlag(jsonObject.getString("flag"));
//                        userMessage.setLevels(users_json.getInt("levels"));
                        userMessage.setScore(jsonObject.getDouble("score"));
                        userMessage.setDisplayAdddate(jsonObject.getString("displayAdddate"));
                        userMessage.setMoney(jsonObject.getDouble("money"));

                        is.close();//关闭输入流
                        connection.disconnect();
                        return userMessage;
                    }
                }
                return null;
            }
        });
        new Thread(futureTask).start();//开启子线程执行futureTask，FutureTask本质是一个Runnable
        return futureTask.get();

    }

    //获取商品信息
    public static ArrayList<WdddItemBean> getWdddMessages(Context context, final String urlString) throws Exception {
        final ArrayList<WdddItemBean> wdddMessages = new ArrayList<>();
        //用FutureTask来完成网络请求的异步，HttpUrlConnection完成具体的网络请求
        FutureTask<ArrayList<WdddItemBean>> futureTask = new FutureTask<>(new Callable<ArrayList<WdddItemBean>>() {
            @Override
            public ArrayList<WdddItemBean> call() throws Exception {
                URL url = new URL(urlString);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.setConnectTimeout(2 * 1000);
                int requestCode = connection.getResponseCode();
                if (requestCode == 200) {
                    //请求数据成功
                    InputStream is = connection.getInputStream();
                    String result = StreamUtil.convertStream(is);//获取请求结果，json字符串
                    JSONArray jsonArray = new JSONArray(result);
                    for (int i = 0; i < 1; i++) {
                        JSONObject wddds_json = jsonArray.getJSONObject(i);
                        WdddItemBean wdddMessage = new WdddItemBean();
                        wdddMessage.setPicture(wddds_json.getString("picture"));
                        wdddMessage.setTitle(wddds_json.getString("title"));
                        wdddMessage.setDanjia(wddds_json.getDouble("danjia"));
                        wdddMessage.setShuliang(wddds_json.getInt("shuangliang"));
                        wdddMessage.setFukuanState(wddds_json.getString("fukuanstate"));

                        wdddMessages.add(wdddMessage);
                    }
                    is.close();//关闭输入流
                    connection.disconnect();
                    return wdddMessages;
                }
                return null;
            }
        });
        new Thread(futureTask).start();//开启子线程执行futureTask，FutureTask本质是一个Runnable
        return futureTask.get();

    }

    //获取评论信息
    public static ArrayList<PinglunItemBean> getPinglunItem(Context context, final String urlString) throws Exception {
        ArrayList<PinglunItemBean> pinglunItemBeen = new ArrayList<>();
        URL url = new URL(urlString);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setConnectTimeout(2 * 1000);
        int requestCode = connection.getResponseCode();
        if (requestCode == 200) {
            //请求数据成功
            InputStream is = connection.getInputStream();
            String result = StreamUtil.convertStream(is);//获取请求结果，json字符串
            JSONArray jsonArray = new JSONArray(result);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                PinglunItemBean pinglunItemBean = new PinglunItemBean();
                pinglunItemBean.setArticleid(jsonObject.getInt("articleid"));
                pinglunItemBean.setId(jsonObject.getInt("id"));
                pinglunItemBean.setContent(jsonObject.getString("content"));
                pinglunItemBean.setFlag(jsonObject.getString("flag"));
                pinglunItemBean.setReplyname(jsonObject.getString("replyname"));
                pinglunItemBean.setReplyuserid(jsonObject.getInt("replyuserid"));
                pinglunItemBean.setDisplayAdddate(jsonObject.getString("displayAdddate"));
                if (i == 0) {
                    pinglunItemBean.setSeveral(jsonObject.getInt("several"));
//                    Log.e("kkkboy", jsonObject.getInt("several") + "");
                }

                pinglunItemBeen.add(pinglunItemBean);
            }
            is.close();//关闭输入流
            connection.disconnect();
            return pinglunItemBeen;
        }
        return null;
    }

    //从网络获取图片
    public static Bitmap getBmp(String url) {
        Bitmap bitmap = null;
        try {
            URL pictureUrl = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) pictureUrl.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(2000);
            connection.setReadTimeout(2000);
            int responseCode = connection.getResponseCode();
            if (responseCode == 200) {
                InputStream is = connection.getInputStream();
                byte[] bytes = new byte[1024];
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                int count = 0;
                while ((count = is.read(bytes)) != -1) {

                    bos.write(bytes, 0, count);
                }
                byte[] byteArray = bos.toByteArray();
                bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
                is.close();
                connection.disconnect();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    /**
     * 把网络资源图片转化成bitmap
     *
     * @param url 网络资源图片
     * @return Bitmap
     */
    public static Bitmap GetLocalOrNetBitmap(String url) {
        Bitmap bitmap = null;
        InputStream in = null;
        BufferedOutputStream out = null;
        try {
            in = new BufferedInputStream(new URL(url).openStream(), 1024);
            final ByteArrayOutputStream dataStream = new ByteArrayOutputStream();
            out = new BufferedOutputStream(dataStream, 1024);
            copy(in, out);
            out.flush();
            byte[] data = dataStream.toByteArray();
            bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
            data = null;
            return bitmap;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static void copy(InputStream in, OutputStream out)
            throws IOException {
        byte[] b = new byte[1024];
        int read;
        while ((read = in.read(b)) != -1) {
            out.write(b, 0, read);
        }
    }

    //post方法提交数据
    public static String post2Service(String stringUrl, String post) {
        try {
            URL url = new URL(stringUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setConnectTimeout(2 * 1000);
            connection.setReadTimeout(2 * 1000);
            // 发送POST请求必须设置如下两行
            connection.setDoOutput(true);
            connection.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            PrintWriter printWriter = new PrintWriter(connection.getOutputStream());
            // 发送请求参数
            printWriter.write(post);//post的参数 xx=xx&yy=yy
            //flush输出流的缓冲
            printWriter.flush();
            //关闭输出流
            printWriter.close();
//            Log.e("kkkboy", "post2Service------提交------->");
            if (connection.getResponseCode() == 200) {
                InputStream is = connection.getInputStream();
                String result = StreamUtil.convertStream(is);
                Log.e("kkkboy", "post2Service------result------->" + result);
                is.close();//关闭输入流
                return result;
            } else {
                //提交失败返回-1
                return -1 + "";
            }
        } catch (Exception e) {
            e.printStackTrace();
            //提交失败返回-1
            return -1 + "";
        }
    }
}


