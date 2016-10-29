package com.example.lenovo.demo_grideview_scoreview.UiActivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.example.lenovo.demo_grideview_scoreview.R;
import com.example.lenovo.demo_grideview_scoreview.Utils.LogUtil;
import com.example.lenovo.demo_grideview_scoreview.Utils.ToastUtils;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 光标定位到文本最后的测试
 */
public class EditTextActivity extends AppCompatActivity implements View.OnClickListener {

    @Bind(R.id.et_event_name)
    EditText etEventName;
    @Bind(R.id.btn_add)
    Button btnAdd;
    @Bind(R.id.btn_sub)
    Button btnSub;
    @Bind(R.id.et_event_phone)
    EditText etEventPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);     //这句话可以
        setContentView(R.layout.activity_edittext);
        ButterKnife.bind(this);

        initListener();
        initData();


    }

    private void initData() {
        etEventName.requestFocus();   //editText获取焦点
        etEventName.setText("欢迎背景");
        etEventName.setSelection("欢迎背景".length());//将光标追踪到内容的最后
    }

    private void initListener() {
        btnAdd.setOnClickListener(this );
        btnSub.setOnClickListener(this );

    }


    @Override
    public void onClick(View v) {
        int selectionStart = etEventName.getSelectionStart();
        LogUtil.e("当前光标的位置;"+selectionStart);
        switch (v.getId()){

            case R.id.btn_add:
                selectionStart++;
                if(selectionStart>etEventName.getText().toString().length()){
                    selectionStart=etEventName.getText().toString().length();
                }
                LogUtil.e("右移后光标的位置;"+selectionStart);
                etEventName.requestFocus();   //editText获取焦点
                etEventName.setSelection(selectionStart);//将光标追踪到内容的最后
                break;
            case R.id.btn_sub:
                selectionStart--;
                if(selectionStart<0){
                    selectionStart=0;
                }
                etEventName.requestFocus();   //editText获取焦点
                LogUtil.e("左移后光标的位置;"+selectionStart);
                etEventName.setSelection(selectionStart);//将光标追踪到内容的最后
                break;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        switch (keyCode){
            case  KeyEvent.KEYCODE_DEL:   //删除键的按下

                LogUtil.e("我是退格键");
                //这里可对监听到的退格键监听到的逻辑操作
                ToastUtils.showToast("退格中");
                break;
        }
        LogUtil.e("按键监听"+keyCode);
        return super.onKeyDown(keyCode, event);
    }
}
