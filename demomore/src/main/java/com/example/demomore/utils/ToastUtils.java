package com.example.demomore.utils;

import android.widget.Toast;

import com.example.lenovo.demo_grideview_scoreview.mApplication;

/**
 * Created by lenovo on 2016/8/31.
 * 全局的toast类
 */
public class ToastUtils {

    /*
     * 定义的土司工具类,实现单例的可以连续的弹出内容的土司
     */

        public static Toast toast;

        public static void showToast(String text) {
            // 如果toast为空就创建
            if (toast == null) {
                toast = Toast.makeText(mApplication.mContext, text, Toast.LENGTH_SHORT);
            } else {
                toast.setText(text); // 如果不为空就修改显示的内容
            }

            // 显示土司

            toast.show();

        }

    }