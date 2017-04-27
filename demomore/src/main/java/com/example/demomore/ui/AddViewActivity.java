package com.example.demomore.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.demomore.BaseActivity;
import com.example.demomore.R;
import com.example.demomore.customview.ErrorDialog;

import butterknife.Bind;

public class AddViewActivity extends BaseActivity {

    @Bind(R.id.activity_add_view)
    RelativeLayout activityAddView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void initListener() {
        activityAddView.setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    public int getContentView() {
        return R.layout.activity_add_view;
    }

    @Override
    protected int getFragmentContentId() {
        return 0;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.activity_add_view:
                ErrorDialog errorDialog = new ErrorDialog(this, "");

                errorDialog.show();
                break;
        }
    }
}
