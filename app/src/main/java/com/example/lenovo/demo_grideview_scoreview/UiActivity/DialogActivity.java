package com.example.lenovo.demo_grideview_scoreview.UiActivity;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.example.lenovo.demo_grideview_scoreview.R;
import com.example.lenovo.demo_grideview_scoreview.Utils.ToastUtils;
import com.example.lenovo.demo_grideview_scoreview.customwidget.Custom_Dialog;

public class DialogActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);
        Button button = (Button) findViewById(R.id.bt_dialog);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Custom_Dialog custom_dialog = new Custom_Dialog(DialogActivity.this);
//                custom_dialog.show();

//                AlertDialog alertDialog = new AlertDialog.Builder(DialogActivity.this).create();
//                alertDialog.show();
                //               Window window = alertDialog.getWindow();
//                window.setContentView(R.layout.dialog_confirmation);
//                TextView tv_title = (TextView) window.findViewById(R.id.custom_confirmation);
//                tv_title.setText("详细信息");
//                TextView tv_message = (TextView) window.findViewById(R.id.custom_cancel);
//                tv_message.setText("曲线");
//                alertDialog.setMessage("我是主要的信息");
//                tv_title.setOnClickListener(this);
//                tv_message.setOnClickListener(this);
                Custom_Dialog custom_dialog = new Custom_Dialog(DialogActivity.this, "我是主要的信息");
                custom_dialog.SetOnRegisterCancelClickListener(new Custom_Dialog.OnClickListener_RegisterCancel() {
                    @Override
                    public void onRegisterCancelClick(String text) {
                        ToastUtils.showToast(text);
                    }
                });

                custom_dialog.show();
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.custom_confirmation:
                ToastUtils.showToast("确定");
                break;
            case R.id.custom_cancel:
                ToastUtils.showToast("取消");
                break;
        }
    }
}
