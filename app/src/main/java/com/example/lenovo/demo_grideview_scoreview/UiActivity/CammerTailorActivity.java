package com.example.lenovo.demo_grideview_scoreview.UiActivity;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.lenovo.demo_grideview_scoreview.R;
import com.example.lenovo.demo_grideview_scoreview.Utils.FileUtils;
import com.example.lenovo.demo_grideview_scoreview.Utils.LogUtil;
import com.example.lenovo.demo_grideview_scoreview.Utils.PictureUtils;
import com.example.lenovo.demo_grideview_scoreview.Utils.Util;
import com.example.lenovo.demo_grideview_scoreview.customwidget.PhotoSelectedDialog;

import java.io.File;

import butterknife.Bind;
import butterknife.ButterKnife;
import constant.ConstantValue;

public class CammerTailorActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int REQUEST_CODE_PHOTO_RESULT = 200;
    @Bind(R.id.btn_get_picture)
    Button btnGetPicture;
    private boolean hadPermission;
    private String filePath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cammer_tailor);
        ButterKnife.bind(this);
        btnGetPicture.setOnClickListener(this);

    }


    /**
     * 启用相册对图片进行裁剪
     *
     * @param uri
     */
    private void startPhotoZoom(Uri uri) {
        try {
            Intent intent = new Intent("com.android.camera.action.CROP", null);
            intent.setDataAndType(uri, "image/*");
            intent.putExtra("crop", "true");
            intent.putExtra("aspectX", 1);
            intent.putExtra("aspectY", 1);
            intent.putExtra("outputX", 150);
            intent.putExtra("outputY", 150);
            intent.putExtra("scale", true);
            intent.putExtra("return-data", false);
//            String filePath = Util.getSDCardPath(CammerTailorActivity.this) + "/" + System.currentTimeMillis() + ".png";
            String filePath = FileUtils.getSDCardPath(this) + System.currentTimeMillis() + ".png";
            LogUtil.e("文件存储路径" + filePath);
            File mCropTempFile = new File(filePath);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(mCropTempFile));
            intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
            intent.putExtra("noFaceDetection", true);
            startActivityForResult(intent, REQUEST_CODE_PHOTO_RESULT);
        } catch (ActivityNotFoundException e) {
//            Toast.makeText(this, R.string.account_avatar_nickname_no_camera, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_get_picture:
//                String filePath = Util.getSDCardPath(CammerTailorActivity.this) + "/" + System.currentTimeMillis() + ".png";
                filePath = FileUtils.getSDCardPath(this) + System.currentTimeMillis() + ".png";
                File file = new File(FileUtils.getSDCardPath(this));
                LogUtil.e("gridview文件路径是否存在", file.exists()+"");
                LogUtil.e("gridview文件缓存路径", FileUtils.getSDCardPath(this));

                requestPermission();    //动态权限请求

                PhotoSelectedDialog.showSingleSelectDialog(this, filePath, hadPermission);


                break;


        }
    }


    /**
     * 图片裁剪请求回调
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case ConstantValue.REQUEST_CODE_PHOTOGRAPH:
                    File file = new File(filePath);
                    if (file.exists()) {
                        Uri photoUri = Uri.fromFile(file);
                        startPhotoZoom(photoUri);
                    }
                    break;
                case ConstantValue.REQUEST_CODE_ALBUM:
                    if (data != null && data.getData() != null) {
                        Uri photoUri = data.getData();
                        startPhotoZoom(photoUri);
                    }
                    break;
                /*case ConstantValue.REQUEST_CODE_SET_USER_ABOUT:
                    String about = data.getStringExtra("about");
                    tvAbout.setText(about);
                    break;
                case ConstantValue.REQUEST_CODE_PHOTO_RESULT:
                    if (mCropTempFile != null && mCropTempFile.exists()) {
                        llUploading.setVisibility(View.VISIBLE);
                        imageView.setImageURI(Uri.fromFile(new File(mCropTempFile.getAbsolutePath())));
                        new UploadHeadHandler(this, bid, mCropTempFile).execute();
                    }
                    break;*/
            }
        }
    }

    /**
     * 6.0动态网络权限请求
     */
    public void requestPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            //申请摄像头的权限
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA},
                    ConstantValue.REQUEST_CODE_PHOTOGRAPH);
            hadPermission = false;
        } else {
            hadPermission = true;
        }

    }


    /**
     * 6.0新权限的注册回调
     *
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        //doNext(requestCode,grantResults);
        if (requestCode == ConstantValue.REQUEST_CODE_PHOTOGRAPH && ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {       //是摄像头权限申请的回调，并且没有成功
        } else if (requestCode == ConstantValue.REQUEST_CODE_PHOTOGRAPH) {
            hadPermission = true;
            PhotoSelectedDialog.setCammerPermission(true);
        }
    }
}
