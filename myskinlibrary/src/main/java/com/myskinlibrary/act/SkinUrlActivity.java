package com.myskinlibrary.act;


import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;

import com.myskinlibrary.R;
import com.myskinlibrary.utils.util.PrefUtils;

import static com.myskinlibrary.act.SuperActivity.EXIT_BROADCAST;

/**
 * 基础普通activity
 */
public abstract class SkinUrlActivity extends Activity {
    // 皮肤标识
    public static final String SKIN_KEY = "skin";
    public final static String CHANGE_BG_BROADCAST = "com.carmanager.change.bg";//切换皮肤广播
    public Resources resources;

    //背景view
    protected View bgView;

    Context context;
    private OnReciverLinsener onReciverLinsener;//父类监听广播回调监听器
    //退出程序的广播接收器
    private BroadcastReceiver superReciver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction() != null && intent.getAction().equals(EXIT_BROADCAST)) {
                finish();
            } else if (intent.getAction() != null && intent.getAction().equals(CHANGE_BG_BROADCAST)) {
                changeBg();
            } else if (onReciverLinsener != null) {
                onReciverLinsener.onReciver(context, intent);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//		//判断当前SDK版本号，如果是4.4以上，就是支持沉浸式状态栏的
//		if (VERSION.SDK_INT >= VERSION_CODES.KITKAT) {
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
////		            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
//        }
        context = this;
        resources = getResources();
        //添加广播监听器
        IntentFilter filter = new IntentFilter();
        filter.addAction(EXIT_BROADCAST);
        filter.addAction(CHANGE_BG_BROADCAST);
        registerReceiver(superReciver, filter);
    }

    @Override
    protected void onResume() {
        if(bgView == null)
        {
            //实例化背景组件
            try {
                bgView = findViewById(R.id.bg_view);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        changeBg();
        super.onResume();
    }

    /**
     * 切换背景
     */
    public void changeBg() {
        if (bgView != null) {
            int defaultSkinRes = PrefUtils.getInt(this,SKIN_KEY,R.drawable.background);
            bgView.setBackgroundResource(defaultSkinRes);
        }
    }

    /**
     * 监听广播方法回调
     *
     * @author jinzufan
     */
    protected interface OnReciverLinsener {
        public void onReciver(Context context, Intent intent);
    }
}
