package com.example.demomore.utils;

import android.content.Context;
import android.widget.Toast;

import com.example.demomore.BuildConfig;

import java.lang.reflect.Method;

/**
 * Created by benchengzhou on 2017/4/23.
 * 作者邮箱：mappstore@163.com
 * 功能描述：
 * 备    注： 数据库工具类
 */

public class DataBaseUtils {
    /**
     * 打印数据库调试地址的方法
     */
    public static void PrintDebugDBAddressLog( ) {
        if (BuildConfig.LOG_DEBUG) {
            try {
                Class<?> debugDB = Class.forName("com.amitshekhar.DebugDB");
                Method getAddressLog = debugDB.getMethod("getAddressLog");
                Object value = getAddressLog.invoke(null);
                ToastUtils.showToastLong((String) value);
                LogUtil.e("数据库的网络调试地址----------" + (String) value);
            } catch (Exception ignore) {

            }
        }
    }


}
