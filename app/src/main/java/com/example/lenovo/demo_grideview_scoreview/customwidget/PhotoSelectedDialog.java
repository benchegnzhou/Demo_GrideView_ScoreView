package com.example.lenovo.demo_grideview_scoreview.customwidget;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;


import com.example.lenovo.demo_grideview_scoreview.R;
import com.example.lenovo.demo_grideview_scoreview.UiActivity.MultiplePictureActivity;
import com.example.lenovo.demo_grideview_scoreview.Utils.GraphicUtils;
import com.example.lenovo.demo_grideview_scoreview.Utils.ToastUtils;

import java.io.File;

import constant.ConstantValue;

/**
 * 选择获取照片方式
 *
 * @author wr
 */
public class PhotoSelectedDialog extends Dialog {
    private Context mContext;


    /**
     * 摄像的权限标志： true 有  false: 没有
     */
    private static boolean sHadCammerPermission;

    public PhotoSelectedDialog(Context context) {
        super(context);
        mContext = context;
    }

    public PhotoSelectedDialog(Context context, int theme) {
        super(context, theme);
        mContext = context;
    }

    /**
     * 选择获取照片方式
     *
     * @author wr
     */
    public static class Builder {
        private Context context;
        private OnClickListener photographButtonClickListener;
        private OnClickListener photoAlbumButtonClickListener;
        private OnClickListener cancelButtonClickListener;
        private final PhotoSelectedDialog dialog;

        public Builder(Context context) {
            this.context = context;
            dialog = new PhotoSelectedDialog(context, R.style.Dialog);
        }

        public Builder setContentView(View v) {
            return this;
        }

        public PhotoSelectedDialog getDialog() {
            return dialog;
        }

        /**
         * 拍照
         *
         * @return
         */
        public Builder setPhotographButton(
                OnClickListener listener) {
            this.photographButtonClickListener = listener;
            return this;
        }

        /**
         * 相册
         *
         * @return
         */
        public Builder setPhotoAlbumButton(
                OnClickListener listener) {
            this.photoAlbumButtonClickListener = listener;
            return this;
        }

        /**
         * 取消
         *
         * @return
         */
        public Builder setCancelButton(OnClickListener listener) {
            this.cancelButtonClickListener = listener;
            return this;
        }


        public PhotoSelectedDialog create() {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            // instantiate the dialog with the custom Theme

            View layout = inflater.inflate(
                    R.layout.dialog_selecting_photograph, null);
            dialog.setContentView(layout);
//            dialog.addContentView(layout, new LayoutParams(
//                    LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
            Window window = dialog.getWindow();
            WindowManager.LayoutParams attributes = window.getAttributes();
            attributes.width = GraphicUtils.getScreenWidth(context);
            attributes.height = WindowManager.LayoutParams.WRAP_CONTENT;
            window.setGravity(Gravity.BOTTOM);
            window.setAttributes(attributes);
            // 拍照
            if (photographButtonClickListener != null) {
                layout.findViewById(R.id.bt_photograph)
                        .setOnClickListener(new View.OnClickListener() {
                            public void onClick(View v) {
                                photographButtonClickListener.onClick(dialog,
                                        DialogInterface.BUTTON_POSITIVE);
                            }
                        });
            }
            // 相册
            if (photoAlbumButtonClickListener != null) {
                layout.findViewById(R.id.bt_photoalbum)
                        .setOnClickListener(new View.OnClickListener() {
                            public void onClick(View v) {
                                photoAlbumButtonClickListener.onClick(dialog, DialogInterface.BUTTON_POSITIVE);
                            }
                        });
            }
            // 取消
            if (cancelButtonClickListener != null) {
                layout.findViewById(R.id.bt_cancel)
                        .setOnClickListener(new View.OnClickListener() {
                            public void onClick(View v) {
                                cancelButtonClickListener.onClick(dialog,
                                        DialogInterface.BUTTON_POSITIVE);
                            }
                        });
            }

//            dialog.setContentView(layout);
            return dialog;
        }
    }

    /**
     * @param activity
     * @param filePath
     * @param hasCount
     */
    public static void showMultiSelectDialog(final Activity activity, final String filePath, final int hasCount) {
        Builder choosePictures = new Builder(activity);
        choosePictures.getDialog().getWindow().setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL);


        //拍照
        choosePictures.setPhotographButton(new OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.dismiss();
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(filePath)));
                activity.startActivityForResult(intent, ConstantValue.REQUEST_CODE_PHOTOGRAPH);
            }
        });
        //本地
        choosePictures.setPhotoAlbumButton(new OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                Intent intent = new Intent(activity, MultiplePictureActivity.class);
                intent.putExtra("hasCount", hasCount);
                activity.startActivityForResult(intent, ConstantValue.REQUEST_CODE_MULTISELECT_ALBUM);
            }
        });
        //取消
        choosePictures.setCancelButton(new OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        choosePictures.create().show();
    }

    /**
     * 单选图片
     *
     * @param activity
     * @param filePath
     */
    public static void showSingleSelectDialog(final Activity activity, final String filePath, final boolean hadPermission) {
        sHadCammerPermission = hadPermission;
        Builder choosePictures = new Builder(activity);
        choosePictures.getDialog().getWindow().setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL);
        //拍照
        choosePictures.setPhotographButton(new OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {//点击之后启动相机拍照
                dialog.dismiss();


                Uri uri = Uri.fromFile(new File(filePath));
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);    //存储拍摄后的图片
                if(sHadCammerPermission){
                    activity.startActivityForResult(intent, ConstantValue.REQUEST_CODE_PHOTOGRAPH);
                }else{
                    ToastUtils.showToast("gridview已被禁止权限；调用摄像头，请在设置中心修改 ");
                }
            }
        });
        //本地
        choosePictures.setPhotoAlbumButton(new OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");   //从媒体库筛选图片格式的文件
                activity.startActivityForResult(intent, ConstantValue.REQUEST_CODE_ALBUM);
            }
        });
        //取消
        choosePictures.setCancelButton(new OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        choosePictures.create().show();
    }


    /**
     * 摄像头权限状态同步方法
     * @param hadCammerPermission
     */
    public  static void setCammerPermission(boolean hadCammerPermission){
        sHadCammerPermission =hadCammerPermission;
    }




}
