package com.example.demomore.utils;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;


/**
 * 输入log
 *
 * @author zbc
 */
public class LogUtil {
//    public static void log(String str) {
//        if (BuildConfig.LOG_DEBUG) {
//            Log.i("i", str);
//        }
//    }

    private final static String tag = "YI_REN_BANG";
    private final static boolean state = true;

    public static void d(String str) {
        if (state)
            Log.d(tag, str);
    }

    /**
     * 带有类名信息的log
     * @author:zbc
     * @param mTag
     * @param str
     */
    public static void d(String mTag, String str) {
        if (state)
            Log.d(mTag, str);
    }


    /**
     * @author:zbc
     * @param context
     * @param str
     */
    public static void d(Context context, String str) {
        String mTag = context.getClass().getSimpleName();
        mTag = TextUtils.isEmpty(mTag) ? tag : mTag;
        if (state)
            Log.d(mTag, str);
    }
    public static void e(String str) {
        if (state)
            Log.e(tag, str);
    }
    /**
     * 带有类名信息的log
     * @author:zbc
     * @param mTag
     * @param str
     */
    public static void e(String mTag, String str) {
        mTag = TextUtils.isEmpty(mTag) ? tag : mTag;
        if (state)
            Log.e(mTag, str);
    }


    /**
     * @author:zbc
     * @param context
     * @param str
     */
    public static void e(Context context, String str) {
        String mTag = context.getClass().getSimpleName();
        mTag = TextUtils.isEmpty(mTag) ? tag : mTag;
        if (state)
            Log.e(mTag, str);
    }


}
