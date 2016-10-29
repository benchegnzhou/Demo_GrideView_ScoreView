package com.example.lenovo.demo_grideview_scoreview.UiActivity;

import android.annotation.TargetApi;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.example.lenovo.demo_grideview_scoreview.R;

public class ActivityAnimation2 extends AppCompatActivity {

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 设置contentFeature,可使用切换动画
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        Transition explode = TransitionInflater.from(this).inflateTransition(android.R.transition.explode);
        getWindow().setEnterTransition(explode);




        setContentView(R.layout.activity_activity_animation2);
        initViewAndClick();
    }

    private void initViewAndClick() {
        TextView button = (TextView) findViewById(R.id.btn_activityanimationnext);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(ActivityAnimation2.this,TextViewTestActivity.class) ;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {  //只有5.0以上的版本实现新功能
                    startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(ActivityAnimation2.this).toBundle());
                }
            }
        });
    }

}

