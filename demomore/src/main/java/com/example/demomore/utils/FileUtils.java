package com.example.demomore.utils;

import android.content.Context;
import android.os.Environment;

import java.io.File;

/**
 * Created by lenovo on 2016/10/10.
 * 本地文件操作的工具类
 */
public class FileUtils {
    /**
     * Environment.getExternalStorageDirectory()getRootDirectory()//获取手机根目录
     * Environment.getExternalStorageDirectory()getExternalStorageDirectory()//获取SD卡根目录
     */
    public static String sAbsolutePath =Environment.getExternalStorageDirectory().getPath();  //获取内置存储卡的路径
    public static String iconDir = sAbsolutePath + "/gridviewDemo/pic/";


    /**
     * 本地上传图片--本机
     * 优先使用sd卡的存储路径，当sd卡未就绪的情况下使用手机内存空间
     * 但是实际测试的时候sd卡路径在没有sd 的时候使用的是手机内存虚拟出来的内存
     *
     * @param pContext
     * @return
     */
    public static String getSDCardPath(Context pContext) {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/gridviewDemo/pic/";
            File PathDir = new File(path);
            if (!PathDir.exists()) {
                PathDir.mkdirs();
            }
            return path;
        } else {
            return pContext.getCacheDir().getAbsolutePath();
        }
    }
}
