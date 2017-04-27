package com.example.demomore;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.demomore.ui.AddViewActivity;
import com.example.demomore.ui.DataBaseTestActivity;
import com.example.demomore.ui.DataFileSaveActivity;
import com.example.demomore.ui.DemoOkActivity;
import com.example.demomore.ui.FragmentActivity;
import com.example.demomore.ui.MyAsyncTaskActivity;
import com.example.demomore.ui.PictureCompressTestActivity;
import com.example.demomore.ui.VerticalTextViewActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements OnClickListener {

    @Bind(R.id.button_okhttp_go)
    Button buttonOkhttpGo;
    @Bind(R.id.button_test_transdata)
    Button buttonTestTransdata;
    @Bind(R.id.activity_main)
    LinearLayout activityMain;
    @Bind(R.id.button_save_data_test)
    Button buttonSaveDataTest;
    @Bind(R.id.button_vertical_scroll_textview)
    Button buttonVerticalScrollTextview;
    @Bind(R.id.button_get_permission_test)
    Button buttonGetPermissionTest;
    @Bind(R.id.button_database)
    Button buttonDatabase;
    @Bind(R.id.button_change_status_test)
    Button buttonChangeStatusTest;
    @Bind(R.id.button_asynctask_test)
    Button buttonAsynctaskTest;
    @Bind(R.id.button_picture_compress)
    Button buttonPictureCompress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initListener();
        initData();
    }

    private void initListener() {
        buttonOkhttpGo.setOnClickListener(this);
        buttonTestTransdata.setOnClickListener(this);
        buttonSaveDataTest.setOnClickListener(this);
        buttonVerticalScrollTextview.setOnClickListener(this);
        buttonGetPermissionTest.setOnClickListener(this);
        buttonDatabase.setOnClickListener(this);
        buttonChangeStatusTest.setOnClickListener(this);
        buttonAsynctaskTest.setOnClickListener(this);
        buttonPictureCompress.setOnClickListener(this);


    }

    private void initData() {
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_okhttp_go:
                startActivity(new Intent(this, DemoOkActivity.class));
                break;
            case R.id.button_test_transdata:
                startActivity(new Intent(this, FragmentActivity.class));
                break;
            case R.id.button_save_data_test:
                startActivity(new Intent(this, DataFileSaveActivity.class));
                break;
            case R.id.button_vertical_scroll_textview:
                startActivity(new Intent(this, VerticalTextViewActivity.class));
                break;
            case R.id.button_get_permission_test:
                startActivity(new Intent(this, TestPermissionActivity.class));
                break;
            case R.id.button_database:    //数据库在线调试和数据构建
                startActivity(new Intent(this, DataBaseTestActivity.class));
                break;
            case R.id.button_change_status_test:    //页面中切换不同的加载状态
                startActivity(new Intent(this, AddViewActivity.class));
                break;
            case R.id.button_asynctask_test:    //页面中切换不同的加载状态
                startActivity(new Intent(this, MyAsyncTaskActivity.class));
                break;
            case R.id.button_picture_compress:    //android 中压缩图片测试
                startActivity(new Intent(this, PictureCompressTestActivity.class));
                break;


        }
    }
}
