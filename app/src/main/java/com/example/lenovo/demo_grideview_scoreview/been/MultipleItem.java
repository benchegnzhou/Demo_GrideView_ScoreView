package com.example.lenovo.demo_grideview_scoreview.been;

import android.content.Context;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.ArrayList;

/**
 * Created by lenovo on 2016/9/26.
 * 这个是多条目的been类
 */
public class MultipleItem implements MultiItemEntity {
    public static final int oneBigImage = 1;
    public static final int twnBigImage = 2;
    public static final int threeBigImage = 3;
    public static final int text = 4;
    public Context mContext;
    private int mType;

    private ArrayList<String> list;

    private String  context;

    /**
     * 不区分类型的构造方法
     *
     */
    public MultipleItem() {

    }


    /**
     * 条目一类型的
     *
     * @param type
     * @param context
     */
    public MultipleItem(int type, String context) {
        mType = type;
        this.context = context;
    }

    /**
     * 带有图片的数据类型
     */
    public MultipleItem(int type,ArrayList<String> list) {
        this.list=list;
    }


    public void setContext(Context context) {
        mContext = context;
    }

    public Context setContext() {
        return mContext;

    }


    @Override
    public int getItemType() {
        return mType;
    }


    public void setItemType(int mType) {
        this.mType = mType;
    }

    public ArrayList<String> getList() {
        return list;
    }

    public void setList(ArrayList<String> list) {
        this.list = list;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }
}
