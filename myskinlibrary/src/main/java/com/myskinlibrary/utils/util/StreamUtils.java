package com.myskinlibrary.utils.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Administrator on 2017/5/9 0009.
 */
public class StreamUtils {
    //输入流转化为字符串
    public static String streamToString(InputStream in){
        String result ="";
        try{
            //创建一个字节数组写入流
            ByteArrayOutputStream out = new ByteArrayOutputStream();

            byte[] buffer = new byte[1024];
            int length = 0;
            while (  (length =  in.read(buffer)) !=-1) {
                out.write(buffer, 0, length);
                out.flush();
            }

            result = out.toString();//将字节流转换成string

            out.close();
        }catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }
    //将图片转化为bitmap类型
    public static Bitmap streamBitmip(String path) throws Exception {
        URL url = new URL(path);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setConnectTimeout(5000);
        int max = conn.getContentLength();
        InputStream is = conn.getInputStream();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len;

        while ((len = is.read(buffer)) != -1) {
            baos.write(buffer, 0, len);
        }
        byte[] result = baos.toByteArray();
        return BitmapFactory.decodeByteArray(result, 0, result.length);
    }
}
