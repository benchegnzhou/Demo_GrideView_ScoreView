package com.example.demomore.utils;

import android.content.Context;
import android.os.Environment;

import com.example.demomore.application.MApplication;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


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
    public static String iconDir = sAbsolutePath + "/Zhoubencheng_Demo/pic";                  //创建公司表示的文件夹层级


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
            //String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/zhengtushuchuang_dir/pic";
            String path = Environment.getExternalStorageDirectory().getPath().toString() + "/zhoubencheng_dir/Pictures";
            File PathDir = new File(path);
            if (!PathDir.exists()) {
                PathDir.mkdirs();
            }
            return path;
        } else {
            return pContext.getCacheDir().getAbsolutePath();
        }
    }


    /***
     * 将大文件拷贝到sd卡目录
     * 待测试
     * @param strOutFileName
     * @throws IOException
     */
    private void copyBigDataToSD(String strOutFileName) throws IOException
    {
        InputStream myInput;
        OutputStream myOutput = new FileOutputStream(strOutFileName);
        myInput = MApplication.sAppContext.getAssets().open("RobotoCondensed-Regular.ttf");
        byte[] buffer = new byte[1024];
        int length = myInput.read(buffer);
        while(length > 0)
        {
            myOutput.write(buffer, 0, length);
            length = myInput.read(buffer);
        }

        myOutput.flush();
        myInput.close();
        myOutput.close();
    }



}
