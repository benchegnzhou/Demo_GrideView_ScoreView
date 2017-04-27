package com.example.demomore;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.demomore.ui.FragmentActivity;

import butterknife.ButterKnife;
import pub.devrel.easypermissions.BasePermissionFragment;

/**
 * Created by benchengzhou on 2017/4/10.
 * 作者邮箱：mappstore@163.com
 * 功能描述：
 * 备    注：
 * 将一些共用的东西集中放在base基类中
 * 方便后期修改维护
 * 避免子类代码冗余
 * 消除不必要的重复代码
 * 方便子类的使用
 */

public abstract class BaseFragment extends BasePermissionFragment {


    /**
     * 贴附的activity
     */
    protected FragmentActivity mActivity;
    /**
     * 根view
     */
    private View mRootView;
    /**
     * 是否对用户可见
     */
    protected boolean mIsVisible;
    /**
     * 是否加载完成
     * 当执行完oncreatview,View的初始化方法后方法后即为true
     */
    protected boolean mIsPrepare;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mRootView = inflater.inflate(getContentView(), container, false);

        ButterKnife.bind(this, mRootView);
        mIsPrepare = true;     //界面加载完成
        initListener();
        initData();
//        onLazyLoad();
        return mRootView;
    }

    /**
     * 在fragment中，我们可以通过getActivity()方法获取到当前依附的activity实例。
     * 但是如果在使用的时候直接获取有时候可能会报空指针，
     * 那么我们可以在fragment生命周期的onAttach(Context context)方法中获取到并提升为全局变量。
     * @param context
     */
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (FragmentActivity) getActivity();
    }


    /**设置布局资源文件
     * @return
     */
    protected abstract int getContentView();

    /**
     * 由子类实现完成
     * 各种监听事件和适配器操作的统一设置
     */
    protected abstract void initListener();

    /**
     * 懒加载，仅当用户可见切view初始化结束后才会执行
     * 用于网络和本地数据库数据的加载
     * 懒加载数据，在oncreatview方法中调用可以直接理解为加载数据，方法中可以进行view的操作
     * 何为懒加载，就是view没有与用户交互的话并不会加载，但是他的加载顺序又非常快。
     * 该方法主要在viewpager嵌套fragment中使用得多。
     * 我们都知道，viewpager可以提前加载左右指定数目
     * （当然这个数目可以通过setOffscreenPageLimit(int limit)设置）的fragment，
     * 如果我们使用懒加载，就只会做些view的创建等操作，避免提前执行其他页面的网络请求。
     */
    protected abstract void onLazyLoad();

    /**
     *  数据初始化，主要包括拆包从activity传递过来的参数，适配器初始化，集合初始化等，不可进行view的操作
     */
    protected abstract void initData();



    @Override
    public void setUserVisibleHint(boolean isVisibleToUser)
    {
        super.setUserVisibleHint(isVisibleToUser);

        this.mIsVisible = isVisibleToUser;

        if (isVisibleToUser)
        {
            onVisibleToUser();
        }
    }

    /**
     * 用户可见时执行的操作
     * @author 漆可
     * @date 2016-5-26 下午4:09:39
     */
    protected void onVisibleToUser()
    {
        if (mIsPrepare && mIsVisible)
        {
            onLazyLoad();
        }
    }







}
