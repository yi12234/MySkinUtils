package com.myskinlibrary.utils.util;

import android.app.Activity;
import android.content.Context;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Environment;
import android.os.StatFs;

/**
 * 获取设备相关信息的类
 *
 * @author jinzufan
 * @time 2013-08-20
 */
public class DeviceUtil {
    /**
     * 检查是否有网络
     *
     * @param context
     * @return
     */
    public static boolean checkNetWorkReady(Context context) {
        ConnectivityManager conMan = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        android.net.NetworkInfo mobileInfo = conMan.getNetworkInfo(
                ConnectivityManager.TYPE_MOBILE);
        NetworkInfo.State mobile = null;
        if (mobileInfo != null) {
            mobile = mobileInfo.getState();
        }
        if (mobile != null && mobile == NetworkInfo.State.CONNECTED) {
            return true;
        } else {
            mobileInfo = conMan.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            if (mobileInfo != null) {
                mobile = mobileInfo.getState();
            }
            if (mobile != null && mobile == NetworkInfo.State.CONNECTED) {
                return true;
            }
        }
        return false;
    }

    /**
     * 检查是否有SDCard
     *
     * @return
     */
    public static boolean checkSDCardReady() {
        if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 获取SDCard的状态
     *
     * @return SDCard 可用的状态
     */
    public static boolean isSDCardUsable() {
        boolean SDCardMounted = false;
        String sDStateString = Environment.getExternalStorageState();
        if (sDStateString.equals(Environment.MEDIA_MOUNTED)) {
            SDCardMounted = true;
        }

        // 是否正在检测SD卡
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_CHECKING)
                || Environment.getExternalStorageState().equals(Environment.MEDIA_NOFS)) {
            SDCardMounted = false;
        }

        // 检测是否插有SD卡
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_REMOVED)
                || Environment.getExternalStorageState().equals(Environment.MEDIA_UNMOUNTED)) {
            SDCardMounted = false;
        }

        // 检测SD卡是否连接电脑共享
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_SHARED)) {
            SDCardMounted = false;
        }

        return SDCardMounted;
    }

    /**
     * 获取SDCard剩下的大小
     *
     * @return SDCard剩下的大小
     * @since V1.0
     */
    public static long getSDCardRemainSize() {
        StatFs statfs = new StatFs(Environment.getExternalStorageDirectory().getPath());
        long blockSize = statfs.getBlockSize();
        long availableBlocks = statfs.getAvailableBlocks();
        return availableBlocks * blockSize;
    }
    /**
     * 检查GPS是否可见
     *
     * @return
     */
    public static boolean checkGPSEnable(Context context) {
        LocationManager lm = (LocationManager) context
                .getSystemService(Context.LOCATION_SERVICE);
        return lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
    }

    /**
     * 获取设备屏幕宽度
     * <p>Description: The getWindowW</p>
     *
     * @param context
     * @return
     * @author: jinzufan
     * @update: [updatedate] [changer][change description]
     */
    public static int getWindowW(Context context) {
        if (context instanceof Activity) {
            return ((Activity) context).getWindow().getWindowManager().getDefaultDisplay().getWidth();
        } else {
            return -1;
        }
    }

    /**
     * 获取设备屏幕高度
     * <p>Description: The getWindowH</p>
     *
     * @param context
     * @return
     * @author: jinzufan
     * @update: [updatedate] [changer][change description]
     */
    public static int getWindowH(Context context) {
        if (context instanceof Activity) {
            return ((Activity) context).getWindow().getWindowManager().getDefaultDisplay().getHeight();
        } else {
            return -1;
        }
    }


}
