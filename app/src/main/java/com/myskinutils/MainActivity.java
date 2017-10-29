package com.myskinutils;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.myskinlibrary.act.SuperActivity;
import com.myskinlibrary.utils.ChangSkinUtils;

public class MainActivity extends SuperActivity {

    private TextView textView;
    private TextView textView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.tv);
        new ChangSkinUtils().startSkin(this,textView);
        textView1 = (TextView) findViewById(R.id.tv1);
        textView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,Main2Activity.class));
            }
        });
    }
}
