package com.example.demomore.ui;


import android.app.Fragment;
import android.content.Intent;
import android.icu.text.BreakIterator;
import android.os.Bundle;
import android.support.annotation.Nullable;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.demomore.R;
import com.example.demomore.utils.ToastUtils;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by benchengzhou on 2017/4/3.
 * 作者邮箱：mappstore@163.com
 * 功能描述：
 * 备    注：
 */

public class StartActivityFormFragment extends Fragment implements View.OnClickListener {

    @Bind(R.id.tv_testfragment)
    TextView tvTestfragment;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_test, container, false);


        ButterKnife.bind(this, view);
        initListener();
        initData();
        return view;
    }


    private void initListener() {
        tvTestfragment.setOnClickListener(this);
    }


    private void initData() {


    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 200:

                ToastUtils.showToastShort("resultCode" + resultCode);
                if (resultCode == 200) {
                    ToastUtils.showToastShort("这个结果是通过fragment启动一个activity然后将他们的结果返回的，结果是" + data.getStringExtra("result"));
                }

                if (resultCode == 0) {
                    ToastUtils.showToastShort("这个结果是通过fragment启动第二个activity然后将他们的结果返回的，结果是" + data.getStringExtra("result"));
                }
                break;

        }
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.tv_testfragment:
                Intent intent = new Intent(getActivity(), StartFromFragmentActivity.class);
                intent.putExtra("key", 123);
                startActivityForResult(intent, 200);
                break;
        }
    }
}
