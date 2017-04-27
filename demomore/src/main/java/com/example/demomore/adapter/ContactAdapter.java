package com.example.demomore.adapter;

import android.animation.Animator;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.demomore.R;
import com.example.demomore.entity.ContactEnity;

import java.util.List;

/**
 * Created by benchengzhou on 2017/4/23.
 * 作者邮箱：mappstore@163.com
 * 功能描述：
 * 备    注：
 */

public class ContactAdapter extends BaseQuickAdapter<ContactEnity> {


    public ContactAdapter(int layoutResId, List<ContactEnity> data) {
        super(layoutResId, data);
    }

    @Override
    public void isFirstOnly(boolean firstOnly) {
        super.isFirstOnly(false);
    }

    /**
     * 通过动画延时来做出动画依次执行的效果
     *
     * @param anim  动画的类型
     * @param index 屏幕可以显示5条记录，动画执行到第5条
     */
    @Override
    protected void startAnim(Animator anim, int index) {
        super.startAnim(anim, index);
        if (index < 5)
            anim.setStartDelay(index * 150);
    }


    @Override
    protected void convert(BaseViewHolder helper, ContactEnity item) {
        helper.setText(R.id.tv_id, item.getId() + "").
                setText(R.id.tv_name, item.getName()).
                setText(R.id.tv_phone, item.getPhone()).
                setText(R.id.tv_email, item.getEmail()).
                setText(R.id.tv_street, item.getStreet()).
                setText(R.id.tv_place, item.getPlace());

    }
}
