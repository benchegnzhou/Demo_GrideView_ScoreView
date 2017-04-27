package com.example.demomore;

import android.Manifest;
import android.view.View;
import android.widget.Button;

import com.example.demomore.utils.ToastUtils;

import butterknife.Bind;
import pub.devrel.easypermissions.PermissionCallBackM;

public class TestPermissionActivity extends BaseActivity {

    private static final int REQUEST_CODE_CAMERA_PERM = 100;
    private static final int REQUEST_CODE_SD_PERM = 200;
    @Bind(R.id.button_get_permission_camera)
    Button buttonGetPermissionCamera;
    @Bind(R.id.button_get_permission_sdcard)
    Button buttonGetPermissionSdcard;



    @Override
    protected void initListener() {
        buttonGetPermissionCamera.setOnClickListener(this);
        buttonGetPermissionSdcard.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        //状态栏
        setTransparentWindow();
    }

    @Override
    public int getContentView() {
        return R.layout.activity_test_permission;
    }

    @Override
    protected int getFragmentContentId() {
        return 0;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_get_permission_camera:
                cameraTask();
                break;
            case R.id.button_get_permission_sdcard:
                sdWriuteTask();
                break;
        }

    }

    public void cameraTask() {
        requestPermission(REQUEST_CODE_CAMERA_PERM, new String[]{Manifest.permission.CAMERA},
                getString(R.string.rationale_camera), new PermissionCallBackM() {
                    @Override
                    public void onPermissionGrantedM(int requestCode, String... perms) {
                        ToastUtils.showToastShort("TODO: Camera Granted --摄像头权限成功");
                    }

                    @Override
                    public void onPermissionDeniedM(int requestCode, String... perms) {
                        onPermissionDenied(requestCode, perms);
                    }
                });

    }

    public void sdWriuteTask() {
        requestPermission(REQUEST_CODE_SD_PERM, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                getString(R.string.rationale_write_sd), new PermissionCallBackM() {
                    @Override
                    public void onPermissionGrantedM(int requestCode, String... perms) {
                        ToastUtils.showToastShort("TODO: SDCARD Granted --内存卡读写权限成功");
                }

                    @Override
                    public void onPermissionDeniedM(int requestCode, String... perms) {
                        onPermissionDenied(requestCode, perms);
                    }
                });

    }

    public void onPermissionDenied(int requestCode, String... perms) {
        ToastUtils.showToastShort("onPermissionDenied:权限被拒绝，好残忍" + requestCode + ":" +
                "" + perms.length);
    }





}
