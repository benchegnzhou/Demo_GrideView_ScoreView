package com.example.demomore.application;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import android.util.Log;


import com.example.demomore.utils.FileUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;

/**
 * 全局静态方法和变量的初始化类，用于初始全局的上下文，volley 的实体对象呢
 * 包过多的解决办法： 在这里  http://blog.csdn.net/gulihui890411/article/details/48551551
 */
public class MApplication extends Application {
    public static MApplication sApplication;
    public static Context sAppContext;
    public static OkHttpClient sOkHttpClient;



    /**
     * 创建全局单例的对象
     *
     * @return
     */
    public static MApplication getInstance() {
        if (sApplication == null) {
            sApplication = new MApplication();
        }
        return sApplication;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        MApplication.getInstance();                //获取全局 MApplication 实体对象
        sAppContext = this;                             //获取全局上下文

        SDKINIT();
    }

    /**
     * 创建全局OkHttpClient对象
     * <p>
     * OkHttpClient 用于管理所有的请求，内部支持并发，
     * 所以我们不必每次请求都创建一个 OkHttpClient 对象，这是非常耗费资源的。接下来就是创建一个 Request 对象了
     *
     * @return
     */
    public static OkHttpClient getSOkHttpClient( ) {
        //创建okhttp的请求对象 参考地址  http://www.jcodecraeer.com/a/anzhuokaifa/androidkaifa/2015/0106/2275.html


        if (sOkHttpClient == null) {
            sOkHttpClient =
                    new OkHttpClient.Builder()
                            .readTimeout(10, TimeUnit.SECONDS)      //设置读取超时时间
                            .writeTimeout(10,TimeUnit.SECONDS)      //设置写的超时时间
                            .connectTimeout(10,TimeUnit.SECONDS)    //设置连接超时时间
                            .retryOnConnectionFailure( true)        //设置链接出错从新链接
                            .cache(new Cache(new File(FileUtils.getSDCardPath(sAppContext)),20*1024))          //设置缓存
                            .build();
        }
        return sOkHttpClient;
    }

    /**
     * 三方sdk初始化
     */
    private void SDKINIT() {
        //初始化shareSDK
        //SMSSDK.initSDK(this, "1b97e6b5a9dd8", "7275bae6b80f13e34c00e0eeb439c870", false);


        //日志调试工具的初始化
//        SmartToolCore.getInstance().init(this);


        //无论推送是否开启都需要调用此方法   友盟推送的初始化
       /* PushAgent mPushAgent = PushAgent.getInstance(this);
        //注册推送服务，每次调用register方法都会回调该接口
        mPushAgent.register(new IUmengRegisterCallback() {

            @Override       //通过设备的唯一标示识别用户身份   device token
            public void onSuccess(String deviceToken) {
                //注册成功会返回device token
                LogUtil.e("myToken:----"+deviceToken);
            }

            @Override
            public void onFailure(String s, String s1) {
                LogUtil.e("myToken获取失败："+s + "---"+s1);
            }
        });*/


    }


    /**
     * 获取全局上下文
     *
     * @return
     */
    public Context getAppContext() {
        if (sAppContext == null) {
            sAppContext = this;
        }
        return sAppContext;
    }


    private String getAppName(int pID) {
        String processName = null;
        ActivityManager am = (ActivityManager) this.getSystemService(ACTIVITY_SERVICE);
        List l = am.getRunningAppProcesses();
        Iterator i = l.iterator();
        PackageManager pm = this.getPackageManager();
        while (i.hasNext()) {
            ActivityManager.RunningAppProcessInfo info = (ActivityManager.RunningAppProcessInfo) (i.next());
            try {
                if (info.pid == pID) {
                    processName = info.processName;
                    return processName;
                }
            } catch (Exception e) {
                // Log.d("Process", "Error>> :"+ e.toString());
            }
        }
        return processName;
    }


}
