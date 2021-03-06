package com.example.demomore.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.demomore.R;
import com.example.demomore.utils.ToastUtils;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ThirdActivity extends AppCompatActivity  implements View.OnClickListener
{

    @Bind(R.id.btn_print_result)
    Button btnPrintResult;
    @Bind(R.id.btn_go_back)
    Button btnGoBack;
    @Bind(R.id.activity_start_from_fragment)
    LinearLayout activityStartFromFragment;
    private Intent intent;
    private int key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_from_fragment);
        ButterKnife.bind(this);
        initListener();
        initData();
    }


    private void initListener() {
        btnGoBack.setOnClickListener(this);
        btnPrintResult.setOnClickListener(this);
    }

    private void initData() {
        intent = getIntent();
        key = intent.getIntExtra("key", 0);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_print_result:
                ToastUtils.showToastShort("当前收到的结果是-----：key = "+key);
                break;
            case R.id.btn_go_back:
                intent.putExtra("result","---\n这是从第三个activity传回来的参数");
                setResult(100, intent);
                finish();
                break;
        }
    }
}
