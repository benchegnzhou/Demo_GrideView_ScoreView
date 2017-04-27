package com.example.demomore.utils;

import android.app.Application;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.View;


import com.example.demomore.application.MApplication;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Created by lenovo on 2016/10/12.
 * 图片处理的工具类
 *
 * 主要完成将一个布局转换成bitmap的操作
 */
public class PictureUtils {

    public static String mPath;

    /**
     *     * 加载本地图片
     *     * @param url   本地图片的路径 ; /storage/emulated/0/yirenbang/pic/20161012160145.png
     *     * @return  返回解压好的bitmip文件
     *    
     */
    public static Bitmap getLoacalBitmap(String url) {
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(url);
            return BitmapFactory.decodeStream(fis); ///把流转化为Bitmap图片        
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                if (fis != null) {
                    fis.close();    //及时的关流
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * 获取图片随机存储的地址
     *
     * @return
     */
    public static String getSavePath() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String time = dateFormat.format(new Date());

        File dir = new File(FileUtils.getSDCardPath(MApplication.sAppContext));
        if (!dir.exists()) {    //文件不存在创建文件
            dir.mkdirs();
        }

        //换成自己的图片保存路径  
        mPath = FileUtils.getSDCardPath(MApplication.sAppContext)  + ".png";
        return mPath;
    }


    /**
     * 将传入的view 转换成bitmap对象
     *
     * @param view 可以是一个view也可以是一个填充好的布局
     * @return
     */
    public static Bitmap viewToBitmap(View view) {
        final Bitmap bmp = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
        view.draw(new Canvas(bmp));    //这里可以将一个view转换成bitmap文件
        return bmp;
    }


    /**
     * 布局文件保存到指定的路径
     *
     * @param viewOrLayout view对象或者是布局
     * @param savePath     保存到路径
     */
    public static void saveViewLayout(View viewOrLayout, final String savePath) {

        final Bitmap bmp = viewToBitmap(viewOrLayout);
        final File file = new File(savePath);

        new Thread(new Runnable() {
            @Override
            public void run() {
                boolean bitMapOk = false;
                try {
                    bitMapOk = bmp.compress(Bitmap.CompressFormat.PNG, 100, new FileOutputStream(file));
                    if (bitMapOk) {
                        LogUtil.e("saveViewLayout:  文件存储成功，存储地址：" + savePath);
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }


}
