package com.example.lenovo.demo_grideview_scoreview.UiActivity;

import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.lenovo.demo_grideview_scoreview.R;

/**
 * Created by lenovo on 2016/9/5.
 */
public class TextViewTestActivity extends AppCompatActivity{
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.textveiw_text_activity);
        TextView textView = (TextView) findViewById(R.id.tv_test);
        //textView.setTextScaleX(1.8f);   //水平方向文字的缩放

        textView.setText("传统设置背景颜色使用\n“bgcolor=颜色取值”，\n 而CSS中则“background:”+颜色取值。\n例如：设置背景为黑色，传统Html设置，\n 即在标签内加入“bgcolor=\"#000\"”\n即可实现颜色为黑色背景，\n如果在W3C中即在对应CSS选择器中始终“background:#000”实现。");
    }
}
