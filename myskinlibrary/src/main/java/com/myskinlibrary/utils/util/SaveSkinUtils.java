package com.myskinlibrary.utils.util;

import android.graphics.Bitmap;
import android.os.Environment;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Administrator on 2017/10/14.
 */

public class SaveSkinUtils {
    private static String path = "/sdcard/mySkin/";

    public SaveSkinUtils() {
    }

    public static void setPicToView(String name,Bitmap mBitmap) {
        String sdStatus = Environment.getExternalStorageState();
        if(sdStatus.equals("mounted")) {
            FileOutputStream b = null;
            File file = new File(path);
            file.mkdirs();
            String fileName = path + name+".jpg";

            try {
                b = new FileOutputStream(fileName);
                mBitmap.compress(Bitmap.CompressFormat.JPEG, 100, b);
            } catch (FileNotFoundException var14) {
                var14.printStackTrace();
            } finally {
                try {
                    b.flush();
                    b.close();
                } catch (IOException var13) {
                    var13.printStackTrace();
                }
            }
        }
    }
}
