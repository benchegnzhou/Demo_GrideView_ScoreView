package com.example.demomore.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.demomore.R;
import com.example.demomore.utils.FileSaveUtils;
import com.example.demomore.utils.FileUtils;
import com.example.demomore.utils.LogUtil;
import com.example.demomore.utils.Util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import butterknife.Bind;
import butterknife.ButterKnife;

public class DataFileSaveActivity extends AppCompatActivity implements OnClickListener {

    @Bind(R.id.et_savecontent_test)
    EditText etSavecontentTest;
    @Bind(R.id.bt_saveappend_test)
    Button btSaveappendTest;
    @Bind(R.id.bt_readcontent_test)
    Button btReadcontentTest;
    @Bind(R.id.tv_content_save_test)
    TextView tvContentSaveTest;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_file_save);
        ButterKnife.bind(this);
        initListener();
        initData();
    }

    private void initData() {

    }

    private void initListener() {
        btReadcontentTest.setOnClickListener(this);
        btSaveappendTest.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_readcontent_test:
                String s = FileSaveUtils.ReadTxtFile(Util.getSDCardPath(this) + "/test.text");
                tvContentSaveTest.setText(s);
                break;
            case R.id.bt_saveappend_test:
                FileSaveUtils.method1(Util.getSDCardPath(this)+"/test.text", etSavecontentTest.getText().toString());
                etSavecontentTest.setText("");
                break;

        }
    }


}
