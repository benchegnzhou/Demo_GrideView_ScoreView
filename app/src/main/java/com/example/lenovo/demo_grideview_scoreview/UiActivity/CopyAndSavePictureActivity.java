package com.example.lenovo.demo_grideview_scoreview.UiActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.lenovo.demo_grideview_scoreview.R;
import com.example.lenovo.demo_grideview_scoreview.Utils.FileUtils;
import com.example.lenovo.demo_grideview_scoreview.Utils.LogUtil;
import com.example.lenovo.demo_grideview_scoreview.Utils.PictureUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CopyAndSavePictureActivity extends AppCompatActivity implements View.OnClickListener {

    private RelativeLayout copyPicture;
    private String mPhotoUrl;
    private ImageView mPicture;
    private Button button_save;
    private Button button_read;
    private String photoUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_copy_and_save_picture);
        copyPicture = (RelativeLayout) findViewById(R.id.demo_copy_and_save_picture);   //将哟拷贝备份的布局或者图片
        //将哟拷贝备份的布局或者图片
        mPicture = (ImageView) findViewById(R.id.iv_pic);
        button_save = (Button) findViewById(R.id.btn_save);
        button_read = (Button) findViewById(R.id.btn_read);
        mPicture.setOnClickListener(this);
        button_save.setOnClickListener(this);
        button_read.setOnClickListener(this);

    }




    /**
     * 布局文件本地存储图片保存方法
     *//*

    public String saveBitmap(Bitmap bm) {
        Log.e("aaa", "保存图片");
        File f = new File(FileUtils.getIconDir(), "img.png");
        if (f.exists()) {  //文件存在先删除
            f.delete();
        }
        try {
            FileOutputStream out = new FileOutputStream(f);
            bm.compress(Bitmap.CompressFormat.PNG, 90, out);
            out.flush();
            out.close();
            Log.i("aaa", "已经保存");
            return f.getAbsolutePath();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }*/


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_save:   //保存
                LogUtil.e("布局文件保存中");
                PictureUtils.saveViewLayout(copyPicture,PictureUtils.getSavePath());
                break;


            case R.id.btn_read:   //图片回显
                LogUtil.e("图片文件读取中。。。。。");
                LogUtil.e("文件的路径：" + PictureUtils.mPath);
                if (!TextUtils.isEmpty(PictureUtils.mPath)) {
                    mPicture.setVisibility(View.VISIBLE);

                    Bitmap bitmap =  PictureUtils.getLoacalBitmap(PictureUtils.mPath); //从本地取图片(在cdcard中获取)  //
                    copyPicture.setVisibility(View.GONE);
                    mPicture.setImageBitmap(bitmap);//设置Bitmap

                }
                break;


            case R.id.iv_pic:   //点击图片隐藏回显
                mPicture.setVisibility(View.GONE);
                copyPicture.setVisibility(View.VISIBLE);
                break;

        }
    }


}
