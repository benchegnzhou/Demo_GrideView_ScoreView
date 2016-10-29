package com.example.lenovo.demo_grideview_scoreview.UiActivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.lenovo.demo_grideview_scoreview.R;

public class ActivityAnimation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_animation);

    }

    /**
     * 第一个参数是需要打开的Activity进入时的动画，第二个参数是需要关闭的Activity离开时的动画。这样我们执行了这段代码之后在跳转Activity的时候就展示了动画效果
     */
    @Override
    public void onBackPressed() {
        finish();
        /**
         * 在调用了startActivity方法之后立即调用overridePendingTransition方法
         */
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_in_right);
    }
}
