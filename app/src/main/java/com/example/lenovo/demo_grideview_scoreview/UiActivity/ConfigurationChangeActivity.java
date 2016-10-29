package com.example.lenovo.demo_grideview_scoreview.UiActivity;

import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.example.lenovo.demo_grideview_scoreview.R;
import com.example.lenovo.demo_grideview_scoreview.Utils.LogUtil;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 屏幕切换实验的Activity
 */
public class ConfigurationChangeActivity extends AppCompatActivity implements View.OnClickListener {

    @Bind(R.id.btn_activity_change)
    Button btnActivityChange;
    @Bind(R.id.tv_activity_new)
    TextView tvActivityNew;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuration_change);
        changebar();
        ButterKnife.bind(this);
        initViewAndClick();
    }

    private void changebar() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = getWindow();
            // Translucent status bar
            window.setFlags(
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            // Translucent navigation bar
            window.setFlags(
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION,
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
    }

    private void initViewAndClick() {
        btnActivityChange.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_activity_change:
                /**
                 * 只有代码设置方向或者已经制定方向以后才能够获取到屏幕的方向，否则是-1，表示没有写死
                 *  android:screenOrientation="portrait"
                 */
                int oritentation = getRequestedOrientation();
                if (oritentation == ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                } else if (oritentation == ActivityInfo.SCREEN_ORIENTATION_PORTRAIT) {
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                }
                break;
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {

        super.onConfigurationChanged(newConfig);
        LogUtil.e("横竖屏切换 onConfigurationChanged", "onConfigurationChanged");
        LogUtil.e("横竖屏切换 onConfigurationChanged", ""+newConfig.orientation);
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            tvActivityNew.setText("当前为横屏");
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            tvActivityNew.setText("当前为竖屏");
        }
    }
}
