package com.ztsc.home.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.PixelFormat;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

/**
 * Created by benchengzhou on 2017/8/27 23:25 .
 * 作者邮箱：mappstore@163.com
 * 功能描述：
 * 类    名： BitmapUtils
 * 备    注： bitmap获取的工具类
 */

public class BitmapUtils {


    private static BitmapUtils tools = new BitmapUtils();

    /**
     * 单例模式
     *
     * @return
     */
    public static BitmapUtils getInstance() {
        if (tools == null) {
            tools = new BitmapUtils();
            return tools;
        }
        return tools;
    }

    /**
     * 将byte[]转换成InputStream
     */

    public InputStream Byte2InputStream(byte[] b) {
        ByteArrayInputStream bais = new ByteArrayInputStream(b);
        return bais;
    }

    /**
     * 将InputStream转换成byte[]
     */

    public byte[] InputStream2Bytes(InputStream is) {
        String str = "";
        byte[] readByte = new byte[1024];
        int readCount = -1;
        try {
            while ((readCount = is.read(readByte, 0, 1024)) != -1) {
                str += new String(readByte).trim();
            }
            return str.getBytes();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将Bitmap转换成InputStream
     */

    public InputStream Bitmap2InputStream(Bitmap bm) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        InputStream is = new ByteArrayInputStream(baos.toByteArray());
        return is;
    }

    /**
     * 将Bitmap转换成InputStream
     */

    public InputStream Bitmap2InputStream(Bitmap bm, int quality) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.PNG, quality, baos);
        InputStream is = new ByteArrayInputStream(baos.toByteArray());
        return is;
    }

    /**
     * 将InputStream转换成Bitmap
     */
    public Bitmap InputStream2Bitmap(InputStream is) {
        return BitmapFactory.decodeStream(is);
    }

    /**
     * Drawable转换成InputStream
     */
    public InputStream Drawable2InputStream(Drawable d) {
        Bitmap bitmap = this.drawable2Bitmap(d);
        return this.Bitmap2InputStream(bitmap);
    }

    /**
     * InputStream转换成Drawable
     */
    public Drawable InputStream2Drawable(InputStream is) {
        Bitmap bitmap = this.InputStream2Bitmap(is);
        return this.bitmap2Drawable(bitmap);
    }

    /**
     * Drawable转换成byte[]
     */
    public byte[] Drawable2Bytes(Drawable d) {
        Bitmap bitmap = this.drawable2Bitmap(d);
        return this.Bitmap2Bytes(bitmap);
    }

    /**
     * byte[]转换成Drawable
     */
    public Drawable Bytes2Drawable(byte[] b) {
        Bitmap bitmap = this.Bytes2Bitmap(b);
        return this.bitmap2Drawable(bitmap);
    }

    /**
     * Bitmap转换成byte[]
     */
    public byte[] Bitmap2Bytes(Bitmap bm) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.PNG, 100, baos);
        return baos.toByteArray();
    }

    /**
     * byte[]转换成Bitmap
     */
    public Bitmap Bytes2Bitmap(byte[] b) {
        if (b.length != 0) {
            return BitmapFactory.decodeByteArray(b, 0, b.length);
        }
        return null;
    }

    /**
     * Drawable转换成Bitmap
     */
    public Bitmap drawable2Bitmap(Drawable drawable) {
        Bitmap bitmap = Bitmap
                .createBitmap(
                        drawable.getIntrinsicWidth(),
                        drawable.getIntrinsicHeight(),
                        drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888
                                : Bitmap.Config.RGB_565);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(),
                drawable.getIntrinsicHeight());
        drawable.draw(canvas);
        return bitmap;
    }

    /**
     * Bitmap转换成Drawable
     */
    public Drawable bitmap2Drawable(Bitmap bitmap) {
        BitmapDrawable bd = new BitmapDrawable(bitmap);
        Drawable d = (Drawable) bd;
        return d;
    }



    /**
     * 保存view副本到本地，给定一个保存的路径，
     * @param view 需要保存的view
     * @param path 目标路径
     * @return
     */
    public static String  saveViewToFile( View view, String path) {
        try {
            Bitmap mBitmap = copyViewToBitmap(view);
            FileOutputStream out = new FileOutputStream(new File(path));
            mBitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
            out.flush();
            out.close();
            return path;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }



    /**
     * 传入需要拷贝的原有的布局
     * 这里最好是在这个基础之上
     * 这种方式不会改变原有view 的位置和大小
     * @param view
     */
    public static Bitmap copyViewToBitmap(View view) {
        //保存信息的类
        final InfoBean bean = new InfoBean();
        bean.statusBarHeight = bean.originRect.top;
        //get Origin View's rect
        view.getGlobalVisibleRect(bean.originRect);
        bean.originWidth = bean.originRect.right - bean.originRect.left;
        bean.originHeight = bean.originRect.bottom - bean.originRect.top;
        LogUtil.e("originWidth" + bean.originWidth + "originHeight" + bean.originHeight);

        bean.bitmap = createBitmap(view, bean.originWidth, bean.originHeight);
        return bean.bitmap;
    }



    /**
     * 通过catch获取拷贝图的方法
     *
     * @param view
     * @param width
     * @param height
     * @return
     */
    private static Bitmap createBitmap(View view, int width, int height) {
        view.setDrawingCacheEnabled(true);     //获取它的cache先要通过setDrawingCacheEnable方法把cache开启
        view.measure(View.MeasureSpec.makeMeasureSpec(width, View.MeasureSpec.EXACTLY),
                View.MeasureSpec.makeMeasureSpec(height, View.MeasureSpec.EXACTLY));
        view.buildDrawingCache();
        return view.getDrawingCache();
    }



    private Bitmap loadBitmapFromView(View v) {
        int w = v.getWidth();
        int h = v.getHeight();

        Bitmap bmp = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(bmp);

        c.drawColor(Color.WHITE);
        /** 如果不设置canvas画布为白色，则生成透明 */

        v.layout(0, 0, w, h);
        v.draw(c);

        return bmp;


       /* v.setDrawingCacheEnabled(true);

        v.measure(
                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
        v.layout(0, 0,
                v.getMeasuredWidth(),
                v.getMeasuredHeight());

        v.buildDrawingCache();
        Bitmap cacheBitmap = v.getDrawingCache();
        Bitmap bitmap = Bitmap.createBitmap(cacheBitmap);

        return bitmap;*/
    }





}
