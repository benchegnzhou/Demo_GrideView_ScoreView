package com.example.lenovo.demo_grideview_scoreview.UiActivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.lenovo.demo_grideview_scoreview.R;
import com.example.lenovo.demo_grideview_scoreview.Utils.CircleTransform;
import com.squareup.picasso.Picasso;

public class PicassoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picasso);
        ImageView icon = (ImageView) findViewById(R.id.icon_iv_picasso);

        Picasso.with(PicassoActivity.this).
                load("http://i.imgur.com/EykR2bR.jpg").
                transform(new CircleTransform()).
                placeholder(R.mipmap.photo1).
                error(R.mipmap.photo1).
                noFade().
                resize(100, 100).//指定压缩参考的宽高比        .into(image);
                centerCrop().
                into(icon);//设置不需要渐渐显示的动画效果        .resize(120,120)//指定压缩参考的宽高比

    }
}
