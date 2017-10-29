package com.myskinlibrary.utils;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.myskinlibrary.R;
import com.myskinlibrary.act.SuperActivity;
import com.myskinlibrary.utils.util.CommonUtils;
import com.myskinlibrary.utils.util.DeviceUtil;
import com.myskinlibrary.utils.util.PrefUtils;
import com.myskinlibrary.view.MyDialog;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 2017/10/13.
 */

public class ChangSkinUtils extends SuperActivity {
    // 皮肤标识
    public static final String SKIN_KEY = "skin";
    private ArrayList<ImageView> skinsView;
    ArrayList<HashMap<String, Integer>> data;
    private int  defaultSkinRes;
    Context context;

    /***///使用固定布局
    public void startSkin(Context context, Button button){
        this.context=context;
        initSkinData();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showdialog();
            }
        });
    }
    /***///使用固定布局
    public void startSkin(Context context, TextView tv){
        this.context=context;
        initSkinData();
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showdialog();
            }
        });
    }

    /***///使用固定布局 自定义数列
    public void startSkinlist(Context context, List<Integer> listSmail,List<Integer> listBig, TextView tv){
        this.context=context;
        data = new ArrayList<HashMap<String,Integer>>();
        initData(listSmail,listBig);

        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showdialog();
            }
        });
    }


    /***/ //使用固定布局 自定义数列
    public void startSkinlist(Context context, List<Integer> listSmail,List<Integer> listBig, Button btn){
        this.context=context;
        data = new ArrayList<HashMap<String,Integer>>();
        initData(listSmail,listBig);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showdialog();
            }
        });
    }


    /***/ //使用固定布局 自定义数列
    public void startSkinArr(Context context, Integer[] ArrSmail,Integer[] ArrBig, TextView tv){
        this.context=context;
        data = new ArrayList<HashMap<String,Integer>>();
        initData(listArray(ArrSmail),listArray(ArrBig));

        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showdialog();
            }
        });
    }


    /***///使用固定布局 自定义数列
    public void startSkinArr(Context context, Integer[] ArrSmail,Integer[] ArrBig, Button btn){
        this.context=context;
        data = new ArrayList<HashMap<String,Integer>>();

        initData(listArray(ArrSmail),listArray(ArrBig));
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showdialog();
            }
        });
    }
    //集合转数列
    private List<Integer> listArray(Integer Arry[]) {
        List<Integer> list=new ArrayList<>();
        list= Arrays.asList(Arry);
        return  list;
    }

    /**
     * 初始化皮肤数据
     */
    private void initData(List<Integer> listSmail, List<Integer> listBig) {
        for (int i = 0; i <listSmail.size() ; i++) {
            HashMap<String, Integer> mapi = new HashMap<String, Integer>();
            mapi.put("icon",listSmail.get(i));
            mapi.put("picture",listBig.get(i));
            data.add(mapi);
        }
    }

    private void initSkinData() {

        data = new ArrayList<HashMap<String,Integer>>();
        HashMap<String, Integer> map1 = new HashMap<String, Integer>();
        map1.put("icon", R.drawable.pf);
        map1.put("picture", R.drawable.background);
        data.add(map1);

        HashMap<String, Integer> map2 = new HashMap<String, Integer>();
        map2.put("icon", R.drawable.pf_00);
        map2.put("picture", R.drawable.pic_bg_10);
        data.add(map2);

        HashMap<String, Integer> map3 = new HashMap<String, Integer>();
        map3.put("icon", R.drawable.pf_01);
        map3.put("picture", R.drawable.pic_bg_11);
        data.add(map3);

        HashMap<String, Integer> map4 = new HashMap<String, Integer>();
        map4.put("icon", R.drawable.pf_02);
        map4.put("picture", R.drawable.pic_bg_12);
        data.add(map4);

        HashMap<String, Integer> map5 = new HashMap<String, Integer>();
        map5.put("icon", R.drawable.pf_03);
        map5.put("picture", R.drawable.pic_bg_13);
        data.add(map5);

        HashMap<String, Integer> map6 = new HashMap<String, Integer>();
        map6.put("icon", R.drawable.pf_04);
        map6.put("picture", R.drawable.pic_bg_14);
        data.add(map6);

        HashMap<String, Integer> map7 = new HashMap<String, Integer>();
        map7.put("icon", R.drawable.pf_05);
        map7.put("picture", R.drawable.pic_bg_15);
        data.add(map7);
    }

    private void showdialog() {
        defaultSkinRes = PrefUtils.getInt(context,SKIN_KEY, R.drawable.background);
        LayoutInflater li = LayoutInflater.from(context);
        final MyDialog addDialog = new MyDialog(context, R.style.dialog);
        View view = li.inflate(R.layout.dialog_changeskin, null);
        addDialog.setContentView(view);
        addDialog.setCancelable(true);
        Window window = addDialog.getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        int width = DeviceUtil.getWindowW(context);
        int height = DeviceUtil.getWindowH(context);
        width = width < height ? width : height;
        lp.width = width;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        LinearLayout items = (LinearLayout) addDialog.findViewById(R.id.skin_items);
        skinsView = new ArrayList<ImageView>();
        for (int i = 0; i < data.size(); i++) {
            View v = li.inflate(R.layout.item_changeskin, null);
            LinearLayout.LayoutParams llp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            llp.setMargins(0, 0, CommonUtils.dip2px(context, 20), 0);
            v.setLayoutParams(llp);
            ImageView image = (ImageView) v.findViewById(R.id.skin_item_image);
            ImageView ok = (ImageView) v.findViewById(R.id.skin_item_ok);
            skinsView.add(ok);
            image.setBackgroundResource(data.get(i).get("icon"));
            if (defaultSkinRes == data.get(i).get("picture")) {
                ok.setVisibility(View.VISIBLE);
            } else {
                ok.setVisibility(View.GONE);
            }
            final int index = i;
            v.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View arg0) {
                    if(defaultSkinRes != data.get(index).get("picture")) {
                        addDialog.dismiss();
                        //保存皮肤数据
                        PrefUtils.setInt(context,SKIN_KEY,data.get(index).get("picture"));
                        //发送换肤广播
                        Intent intent=new Intent(CHANGE_BG_BROADCAST);
                        context.sendBroadcast(intent);
                    }
                }
            });
            items.addView(v);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View arg0) {
                    addDialog.dismiss();
                }
            });
            addDialog.show();
        }

    }
    private void setUrl() {
        defaultSkinRes = PrefUtils.getInt(context,SKIN_KEY, R.drawable.background);


    }

}
