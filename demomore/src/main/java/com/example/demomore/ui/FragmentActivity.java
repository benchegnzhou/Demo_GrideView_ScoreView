package com.example.demomore.ui;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.demomore.R;

public class FragmentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);
        initListener();
        initData();
    }

    private void initData() {
        setDefaultFragment();
    }

    private void initListener() {
    }

    private void setDefaultFragment()
    {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        StartActivityFormFragment formFragment = new StartActivityFormFragment();
        if(formFragment!=null){  // 使用当前Fragment的布局替代id_content的控件
            transaction.replace(R.id.fragment_test, formFragment);
        }

        transaction.commit();
    }
}
