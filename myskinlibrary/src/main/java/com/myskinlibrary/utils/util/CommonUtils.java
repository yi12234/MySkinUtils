package com.myskinlibrary.utils.util;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;


public class CommonUtils {

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * 计算ListView真实高度，使得ListView能在ScrollView中正常显示
     * <p>Description: The setListViewHeightBasedOnChildren</p>
     *
     * @param listView
     * @author: caie
     * @update: [updatedate] [changer][change description]
     */
    public static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return;
        }
        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
    }

    /**
     * 去小数点后两位并装换成String返回
     *
     * @param value
     * @return
     */
    public static String get2Double(double value) {
        DecimalFormat df = new DecimalFormat("#.##");
        return df.format(value);
    }


    /**
     * 把一个集合中得数据添加到另一个集合中
     *
     * @param data
     * @param dest
     * @return
     */
    public static ArrayList addToArrayList(ArrayList data, ArrayList dest) {
        if (dest == null) {
            dest = new ArrayList<Object>();
        }
        if (data != null && data.size() > 0) {
            int size = data.size();
            for (int i = 0; i < size; i++) {
                dest.add(data.get(i));
            }
        }
        return dest;
    }

    /**
     * 把一个集合中得数据添加到另一个集合中
     *
     * @param data
     * @param dest
     * @return
     */
    public static List addToArrayList2(List data, List dest) {
        if (dest == null) {
            dest = new ArrayList<Object>();
        }
        if (data != null && data.size() > 0) {
            int size = data.size();
            for (int i = 0; i < size; i++) {
                dest.add(data.get(i));
            }
        }
        return dest;
    }




}