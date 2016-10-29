package com.example.lenovo.demo_grideview_scoreview.UiActivity;

import android.app.ActivityManager;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.os.StatFs;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.lenovo.demo_grideview_scoreview.R;
import com.example.lenovo.demo_grideview_scoreview.Utils.LogUtil;

import butterknife.Bind;
import butterknife.ButterKnife;

public class Animatio4nActivity extends AppCompatActivity implements View.OnClickListener {


    @Bind(R.id.btn_override_pendingtransition)
    Button btnOverridePendingtransition;
    @Bind(R.id.btn_makescenetransition_animation)
    Button btnMakescenetransitionAnimation;
    @Bind(R.id.btn_overridepending)
    Button btnOverridepending;
    @Bind(R.id.btn_query)
    Button btnQuery;
    @Bind(R.id.tv_state)
    TextView tvState;
    private StringBuffer stringBuffer = new StringBuffer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animatio4n);
        ButterKnife.bind(this);
        initClick();
        initData();


    }

    private void initData() {

    }


    private void initClick() {
        btnOverridePendingtransition.setOnClickListener(this);
        btnMakescenetransitionAnimation.setOnClickListener(this);
        btnQuery.setOnClickListener(this);


    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //通过overridePendingTransition的形式定义Activity的切换动画
            case R.id.btn_override_pendingtransition:
                /**
                 * 在调用了startActivity方法之后立即调用overridePendingTransition方法
                 */
                Intent intent = new Intent(Animatio4nActivity.this, ActivityAnimation.class);
                startActivity(intent);
                //重写Activity原有的动画，参数传入0表示使用默认动画，参数1当前Activity进入的动画，参数2： 当前Activity退出时的动画
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_in_right);
                break;


            //通过overridePendingTransition的形式定义Activity的切换动画
            case R.id.btn_makescenetransition_animation:
                /**
                 * 在调用了startActivity方法之后立即调用overridePendingTransition方法
                 */
                Intent makeSceneIntent = new Intent(Animatio4nActivity.this, ActivityAnimation2.class);
                startActivity(makeSceneIntent);
                break;

            //通过overridePendingTransition的形式定义Activity的切换动画
            case R.id.btn_query:
                /**
                 * 在调用了startActivity方法之后立即调用overridePendingTransition方法
                 */
                displayBriefMemory();
                break;


        }
    }

    //内存相关
    private void displayBriefMemory() {
        final ActivityManager activityManager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
        ActivityManager.MemoryInfo info = new ActivityManager.MemoryInfo();
        activityManager.getMemoryInfo(info);
        LogUtil.e("System.out", "系统剩余内存:" + (info.availMem >> 20) + "MB");
        LogUtil.e("System.out", "系统是否处于低内存运行：" + info.lowMemory);
        LogUtil.e("System.out", "当系统剩余内存低于" + (info.threshold >> 20) + "MB时就看成低内存运行");
        LogUtil.e("System.out", "当前SD卡的状态" + checkSDCard() + "");
        LogUtil.e("System.out", "打印SD卡的路径" + getExternalStoragePath() + "");
        stringBuffer.append("系统剩余内存:" + (info.availMem >> 20) + "MB");
        stringBuffer.append("\n");
        stringBuffer.append("系统是否处于低内存运行：" + info.lowMemory);
        stringBuffer.append("\n");
        stringBuffer.append("当系统剩余内存低于" + (info.threshold >> 20) + "MB时就看成低内存运行");
        stringBuffer.append("\n");
        stringBuffer.append("当前SD卡的状态" + checkSDCard());
        stringBuffer.append("\n");
        stringBuffer.append("打印SD卡的路径" + getExternalStoragePath());
        stringBuffer.append("\n");
        stringBuffer.append("打印SD卡的状态————————" + Environment.getExternalStorageState());
        stringBuffer.append("\n");
        tvState.setText(stringBuffer.toString());
    }

    // 检查SD卡状态
    private boolean checkSDCard() {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            return true;
        }
        return false;
    }

    // 获取SD卡路径  
    public static String getExternalStoragePath() {
        // 获取SdCard状态  
        String state = Environment.getExternalStorageState();

        // 判断SdCard是否存在并且是可用的  
        LogUtil.e("System.out", "打印SD卡的状态————————" + state + "");
        if (Environment.MEDIA_MOUNTED.equals(state)) {

            if (Environment.getExternalStorageDirectory().canWrite()) {

                return Environment.getExternalStorageDirectory()
                        .getPath();

            }

        }

        return null;

    }

    /**
     *  
     *      * 　　* 获取存储卡的剩余容量，单位为字节 
     *      *  
     *      * 　　* @param filePath 
     *      *  
     *      * 　　* @return availableSpare 
     *      *  
     *      * 　　 
     *      
     */
    public static long getAvailableStore(String filePath) {

        // 取得sdcard文件路径  

        StatFs statFs = new StatFs(filePath);

        // 获取block的SIZE  

        long blocSize = statFs.getBlockSize();

        // 获取BLOCK数量  

        // long totalBlocks = statFs.getBlockCount();  

        // 可使用的Block的数量  

        long availaBlock = statFs.getAvailableBlocks();

        // long total = totalBlocks * blocSize;  

        long availableSpare = availaBlock * blocSize;

        return availableSpare;
    }

}
