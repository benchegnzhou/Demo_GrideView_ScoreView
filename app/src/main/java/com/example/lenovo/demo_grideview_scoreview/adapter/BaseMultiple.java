package com.example.lenovo.demo_grideview_scoreview.adapter;

import android.view.View;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;

import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.listener.OnItemChildLongClickListener;
import com.example.lenovo.demo_grideview_scoreview.R;
import com.example.lenovo.demo_grideview_scoreview.Utils.LogUtil;
import com.example.lenovo.demo_grideview_scoreview.Utils.ToastUtils;
import com.example.lenovo.demo_grideview_scoreview.been.MultipleItem;

import java.util.List;

/**
 * Created by lenovo on 2016/9/26.
 * recyclerview 多条目的实现
 */
public class BaseMultiple extends BaseMultiItemQuickAdapter<MultipleItem> {
    /**
     * 将多条目的布局写在这里
     * 这里可以设定布局的类型
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public BaseMultiple(List<MultipleItem> data) {
        super(data);
        addItemType(MultipleItem.oneBigImage, R.layout.item_rv_oneimage);
        addItemType(MultipleItem.twnBigImage, R.layout.item_rv_twoimage);
        addItemType(MultipleItem.text, R.layout.item_rv_textview);
        addItemType(MultipleItem.threeBigImage, R.layout.item_rv_threeimage);

    }

    @Override
    protected void convert(BaseViewHolder helper, MultipleItem item) {
        //根据不同的类型进行展示
        switch (helper.getItemViewType()) {
            case MultipleItem.oneBigImage:
                helper.setImageResource(R.id.one_iv,R.drawable.asd);
                break;
            case MultipleItem.twnBigImage:
                helper.setImageResource(R.id.two_iv_1,R.drawable.asd).setImageResource(R.id.two_iv_2,R.drawable.asd);;
                break;
            case MultipleItem.threeBigImage:
                //可以在这里直接对条目的子控件实现监听
                helper.setImageResource(R.id.three_iv_1,R.drawable.asd).setOnClickListener(R.id.three_iv_1,new mOnChildClickListener("three_iv_1")).
                        setImageResource(R.id.three_iv_2,R.drawable.asd).setOnClickListener(R.id.three_iv_2,new mOnChildClickListener("three_iv_2")).
                        setImageResource(R.id.three_iv_3,R.drawable.asd).setOnClickListener(R.id.three_iv_3,new mOnChildClickListener("three_iv_3"));

                break;
            case MultipleItem.text:
                //直接以链接的形式表现控件
                helper.setText(R.id.rv_text,item.getContext()).linkify(R.id.rv_text);
                break;
        }
    }

    class mOnChildClickListener implements View.OnClickListener {
        private  String mContent;
        public mOnChildClickListener(String content){
            mContent=content;
        }
        @Override
        public void onClick(View v) {
            LogUtil.e(mContent+" 被点击");
            ToastUtils.showToast(mContent+" 被点击");
        }

    }

}
