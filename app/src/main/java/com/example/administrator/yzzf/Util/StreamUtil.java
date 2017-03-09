package com.example.administrator.yzzf.Util;

import android.content.Context;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import static com.sina.weibo.sdk.openapi.legacy.CommonAPI.CAPITAL.w;

/**
 * 可以直接将InputStream输入流，转换为String值，可以理解为从网络就获取到json的字符串
 * Created by Administrator on 2017/3/9 0009.
 * @author Likeke
 */

public class StreamUtil {
    public static String convertStream(InputStream inputStream) {
        String result = "";
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int i=0;
        try {
            while ((i = inputStream.read(buffer)) != -1) {
                bos.write(buffer, 0, i);
                bos.flush();
            }
            result = new String(bos.toByteArray(), "utf-8");
            result = bos.toString();
            bos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
