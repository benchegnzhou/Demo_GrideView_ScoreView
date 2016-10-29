package com.example.lenovo.demo_grideview_scoreview.UiActivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.lenovo.demo_grideview_scoreview.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class CardViewActivity extends AppCompatActivity {

    @Bind(R.id.cardview_demo_tv)
    TextView cardviewDemoTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_view);
        ButterKnife.bind(this);

        cardviewDemoTv.setText("cardview本身是继承framlayout的，在使用的方法上和framlayout是一样的，同时他也是5.0后的新控件，具有阴影圆角等功能-->\n" +
                "    <!--app:cardElevation=\"12dp\"  设置阴影的大小 +\n <!--app:cardUseCompatPadding=\"true\"  使用padding值，配合contentPadding使用-->\n" +
                "    <!--app:contentPadding=\"6dp\"   使用padding值，配合cardUseCompatPadding使用-->");
    }


}
