package com.example.lenovo.demo_grideview_scoreview.UiActivity;

import android.content.pm.PackageInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.lenovo.demo_grideview_scoreview.R;
import com.example.lenovo.demo_grideview_scoreview.Utils.DeviceMessageUtils;

import butterknife.Bind;
import butterknife.ButterKnife;

public class DeviceMessageShowActivity extends AppCompatActivity {

    @Bind(R.id.cardview_demo_tv)
    TextView cardviewDemoTv;
    private StringBuffer StringBuffer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device_message_show);
        ButterKnife.bind(this);

        initData();


    }

    /**
     * 获取设备的信息
     */
    private void initData() {
        if (StringBuffer == null) {
            StringBuffer=new StringBuffer("");
        }
        String imei = DeviceMessageUtils.getIMEI(this);
        String mobileInfo = DeviceMessageUtils.getMobileInfo(this);
        String ipAddress = DeviceMessageUtils.getIPAddress();
        String mobileNumber = DeviceMessageUtils.getMobileNumber(this);
        PackageInfo packageInfo = DeviceMessageUtils.getPackageInfo(this);
        int screenWidth = DeviceMessageUtils.getScreenWidth(this);
        int sdkVersion = DeviceMessageUtils.getSDKVersion();
        int statusBarHeight = DeviceMessageUtils.getStatusBarHeight(this);

        StringBuffer.append("设备的IMEI是： "+ imei);
        StringBuffer.append("\n");
        StringBuffer.append("打印设备的信息：： "+ mobileInfo);
        StringBuffer.append("\n");
        StringBuffer.append("打印设备的网址：： "+ ipAddress);
        StringBuffer.append("\n");
        StringBuffer.append("当前设备的手机号码是： "+ mobileNumber);
        StringBuffer.append("\n");
        StringBuffer.append("当前软件安装包的信息是： 包名"+ packageInfo.packageName+"版本号："+packageInfo.versionCode);
        StringBuffer.append("\n");
        StringBuffer.append("当前设备最小支持的sdk版本是： "+ sdkVersion);
        StringBuffer.append("\n");
        StringBuffer.append("当前设备屏幕宽度是： "+ screenWidth);
        StringBuffer.append("\n");
        StringBuffer.append("当前设备状态栏的高度是： "+ statusBarHeight);
        StringBuffer.append("\n");
        cardviewDemoTv.setText(StringBuffer.toString());


    }
}
