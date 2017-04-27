package com.example.demomore.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.RelativeLayout;

import com.example.demomore.R;
import com.example.demomore.customview.VerticalScrollTextView;
import com.example.demomore.entity.ADEnity;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class VerticalTextViewActivity extends AppCompatActivity {

    @Bind(R.id.vertical_textview)
    VerticalScrollTextView verticalTextview;
    private ArrayList<ADEnity> adEnities;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vertical_text_view);
        ButterKnife.bind(this);
        initListener();
        initData();
    }


    private void initListener() {

    }

    private void initData() {
        adEnities = new ArrayList<>();
        adEnities.add(new ADEnity("推广","我是推广的内容1","www.baidu.com"));
        adEnities.add(new ADEnity("推广","我是推广的内容2","www.baidu.com"));
        adEnities.add(new ADEnity("推广","我是推广的内容3","www.baidu.com"));
        adEnities.add(new ADEnity("推广","我是推广的内容4","www.baidu.com"));

        verticalTextview.setmTexts(adEnities);
        verticalTextview.setFrontColor(0xFFff1010);
        verticalTextview.setBackColor(0xFF101010);
    }

}
