package com.example.demomore.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.demomore.BaseActivity;
import com.example.demomore.R;

import butterknife.Bind;

public class PictureCompressTestActivity extends BaseActivity {

    @Bind(R.id.btn_chose_pic)
    Button btnChosePic;
    @Bind(R.id.btn_compress_start)
    Button btnCompressStart;
    @Bind(R.id.activity_picture_compress_test)
    LinearLayout activityPictureCompressTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {

    }

    @Override
    public int getContentView() {
        return R.layout.activity_picture_compress_test;
    }

    @Override
    protected int getFragmentContentId() {
        return 0;
    }

    @Override
    public void onClick(View view) {

    }
}
