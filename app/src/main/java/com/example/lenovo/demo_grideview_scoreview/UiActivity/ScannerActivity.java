package com.example.lenovo.demo_grideview_scoreview.UiActivity;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Vibrator;
import android.provider.MediaStore;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.lenovo.demo_grideview_scoreview.R;
import com.example.lenovo.demo_grideview_scoreview.Utils.ToastUtils;

import java.io.File;

import cn.bingoogolapple.qrcode.core.QRCodeView;
import cn.bingoogolapple.qrcode.zxing.QRCodeDecoder;
import cn.bingoogolapple.qrcode.zxing.ZXingView;

public class ScannerActivity extends AppCompatActivity implements QRCodeView.Delegate {
    private QRCodeView mQRCodeView;
    private static final int REQUEST_CODE_CHOOSE_QRCODE_FROM_GALLERY = 666;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scanner);
        ActionBar supportActionBar = getSupportActionBar();
        mQRCodeView = (ZXingView) findViewById(R.id.zxingview);
        mQRCodeView.setDelegate(this);

        supportActionBar.setCustomView(R.layout.item_scanner_actionbar);
        supportActionBar.setIcon(R.mipmap.ic_launcher);
        supportActionBar.setDisplayHomeAsUpEnabled(true);
        supportActionBar.setDisplayShowCustomEnabled(true);

        supportActionBar.setDefaultDisplayHomeAsUpEnabled(true);


    }

    /**
     * 开启扫描
     */
    @Override
    protected void onStart() {
        super.onStart();
        mQRCodeView.startCamera(); //开启摄像头
        mQRCodeView.startSpot();  //开启扫描
//        mQRCodeView.startCamera(Camera.CameraInfo.CAMERA_FACING_FRONT);   //设置启用前置摄像头，默认是启用后置摄像头的
    }

    /**
     * 关闭扫描
     */
    @Override
    protected void onStop() {
        mQRCodeView.stopSpot();  //停止扫描
        mQRCodeView.stopCamera();  //关闭摄像头

        super.onStop();
    }

    /**
     *
     */
    @Override
    protected void onDestroy() {
        mQRCodeView.onDestroy();
        super.onDestroy();
    }

    /**
     * 扫描成功的调用
     *
     * @param result
     */
    @Override
    public void onScanQRCodeSuccess(String result) {

        ToastUtils.showToast(result);
        vibrate();  //震动提示
        mQRCodeView.startSpot();
    }

    @Override
    public void onScanQRCodeOpenCameraError() {
        vibrate();  //震动提示
        ToastUtils.showToast("打开相机出错了，亲！");
    }

    /**
     * 启用系统的震动。
     */
    private void vibrate() {
        Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        vibrator.vibrate(200);  //设置震动的时间
    }


    /**
     * 启用回掉，当开启本地图片扫描的时候调用
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        mQRCodeView.showScanRect();

        if (requestCode == REQUEST_CODE_CHOOSE_QRCODE_FROM_GALLERY && resultCode == Activity.RESULT_OK && null != data) {
            String picturePath;
            try {
                Uri selectedImage = data.getData();
                String[] filePathColumns = {MediaStore.Images.Media.DATA};
                Cursor c = getContentResolver().query(selectedImage, filePathColumns, null, null, null);
                c.moveToFirst();
                int columnIndex = c.getColumnIndex(filePathColumns[0]);
                picturePath = c.getString(columnIndex);
                c.close();
            } catch (Exception e) {
                picturePath = data.getData().getPath();
            }

            if (new File(picturePath).exists()) {
                QRCodeDecoder.decodeQRCode(BitmapFactory.decodeFile(picturePath), new QRCodeDecoder.Delegate() {
                    @Override
                    public void onDecodeQRCodeSuccess(String result) {
                        ToastUtils.showToast(result);
                    }

                    @Override
                    public void onDecodeQRCodeFailure() {
                        ToastUtils.showToast("未发现二维码");
                    }
                });
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
