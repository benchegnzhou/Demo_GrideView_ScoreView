package com.example.demomore;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;


import java.util.zip.Inflater;

import butterknife.ButterKnife;
import pub.devrel.easypermissions.BasePermissionActivity;

/**
 * Created by benchengzhou on 2017/4/8.
 * 作者邮箱：mappstore@163.com
 * 功能描述：
 * 备    注：
 */

public abstract class BaseActivity extends BasePermissionActivity implements View.OnClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(getContentView());
        ViewGroup inflate = (ViewGroup) LayoutInflater.from(this).inflate(getContentView(), null);

        setContentView(inflate);
        ButterKnife.bind(this);
        initListener();
        initData();

    }

    protected  void addErroView(){
        WindowManager.LayoutParams wl = new WindowManager.LayoutParams();
        wl.height = 400;
        wl.width = WindowManager.LayoutParams.MATCH_PARENT;
                               //WindowManager.LayoutParams.TYPE_SYSTEM_ALERT //需要后面的view获得焦点时：
//        wl.flags=WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
        View view = LayoutInflater.from(this).inflate(R.layout.activity_base, null);

//        inflate.addView(view);
        getWindowManager().addView(view, wl);
    }


    protected  void displayErroView(){

    }






    /**
     * 由子类实现并且返回对应的完成监听操作和控件adapter的设置
     */
    protected abstract void initListener();

    /**
     * 用于子类的数据的初始化
     */
    protected abstract void initData();

    /**
     * 由子类实现并且返回对应的view
     *
     * @return
     */
    public abstract int getContentView();


    @Override
    protected void onDestroy() {
        super.onDestroy();
//        ButterKnife.unbind(this);//解除绑定，官方文档只对fragment做了解绑
    }


    /**
     * 添加fragment
     * 添加fragment：开启一个事物,替换了当前layout容器中的由getFragmentContentId()标识的fragment。
     * 通过调用 addToBackStack(String tag), replace事务被保存到back stack,
     * 因此用户可以回退事务,并通过按下BACK按键带回前一个fragment，
     * 如果没有调用 addToBackStack(String tag), 那么当事务提交后, 那个fragment会被销毁,
     * 并且用户不能导航回到它。其中参数tag将作为本次加入BackStack的Transaction的标志。
     * commitAllowingStateLoss()，这种提交是允许发生异常时状态值丢失的情况下也能正常提交事物。
     */

    protected void addFragment(BaseFragment fragment) {
        if (fragment != null) {
            getFragmentManager().beginTransaction()
                    .replace(getFragmentContentId(), fragment, fragment.getClass().getSimpleName())
                    .addToBackStack(fragment.getClass().getSimpleName())
                    .commitAllowingStateLoss();
        }
    }

    /**
     * 获取需要添加的fragment的布局文件
     *
     * @return
     */
    protected abstract int getFragmentContentId();

    /**
     * 移除fragment
     * 移除fragment：与addToBackStack()相对应的接口方法是popBackStack()，
     * 调用该方法后会将事务操作插入到FragmentManager的操作队列，轮询到该事务时开始执行。
     * 这里进行了一下判断，获取回退栈中所有事务数量，大于1的时候，执行回退操作，
     * 等于1的时候，代表当前Activity只剩下一个Fragment，直接finish()当前Activity即可
     */

    protected void removeFragment() {
        if (getFragmentManager().getBackStackEntryCount() > 1) {
            getFragmentManager().popBackStack();
        } else {
            finish();
        }
    }

    //返回键返回事件
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (KeyEvent.KEYCODE_BACK == keyCode) {
            if (getSupportFragmentManager().getBackStackEntryCount() == 1) {
                finish();
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    /**
     * 沉浸式状态栏实现
     * ViewCompat.setFitsSystemWindows(mChildView, false)中的第二个参数设置为false就是全屏模式，
     * 而设置成true。像上述实例中， ViewCompat.setFitsSystemWindows(mChildView, false)
     * 就是说mChildView可以直接延伸到phoneWindow的顶部，相当于小米天气的那种效果。
     */
    public void setTransparentWindow() {
        //4.4版本设置透明状态栏
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT && Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            // 设置状态栏透明
            this.getWindow()
                    .setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            Window window = this.getWindow();
            //设置透明状态栏,这样才能让 ContentView 向上
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //需要设置这个 flag 才能调用 setStatusBarColor 来设置状态栏颜色
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            //设置状态栏颜色
            int statusColor = 0xffff0000;
            window.setStatusBarColor(statusColor);
            //为了设置全屏
            ViewGroup mContentView = (ViewGroup) this.findViewById(Window.ID_ANDROID_CONTENT);
            View mChildView = mContentView.getChildAt(0);
            if (mChildView != null) {
                //注意不是设置 ContentView 的 FitsSystemWindows, 而是设置 ContentView 的第一个子 View . 使其不为系统 View 预留空间.
                ViewCompat.setFitsSystemWindows(mChildView, false);


            }
        }
    }


}
