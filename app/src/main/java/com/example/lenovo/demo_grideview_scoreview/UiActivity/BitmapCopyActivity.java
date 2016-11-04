package com.example.lenovo.demo_grideview_scoreview.UiActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.lenovo.demo_grideview_scoreview.R;
import com.example.lenovo.demo_grideview_scoreview.Utils.LogUtil;
import com.example.lenovo.demo_grideview_scoreview.been.InfoBean;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 拷贝原有的布局，生成对应的bitmap文件
 */
public class BitmapCopyActivity extends AppCompatActivity implements View.OnClickListener{

    @Bind(R.id.iv_primary)
    ImageView ivPrimary;
    @Bind(R.id.iv_copy)
    ImageView ivCopy;
    @Bind(R.id.but_copy_imageview)
    Button butCopyImageview;
    @Bind(R.id.but_clear_imageview)
    Button butClearImageview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bitmap_copy);
        ButterKnife.bind(this);
        butCopyImageview.setOnClickListener(this);
        butClearImageview.setOnClickListener(this);

    }


    //保存信息的类
    final InfoBean bean = new InfoBean();

    /**
     * 传入需要拷贝的原有的布局
     * 这里最好是在这个基础之上
     * @param view
     */
    private void copyView(View view) {
        bean.statusBarHeight = bean.originRect.top;
        //get Origin View's rect
        view.getGlobalVisibleRect(bean.originRect);
        bean.originWidth = bean.originRect.right - bean.originRect.left;
        bean.originHeight = bean.originRect.bottom - bean.originRect.top;
        LogUtil.e("originWidth"+bean.originWidth+"originHeight"+bean.originHeight);
        bean.bitmap = createBitmap(view, bean.originWidth, bean.originHeight);
    }


    /**
     * 通过catch获取拷贝图的方法
     *
     * @param view
     * @param width
     * @param height
     * @return
     */
    private static Bitmap createBitmap(View view, int width, int height) {
        view.setDrawingCacheEnabled(true);     //获取它的cache先要通过setDrawingCacheEnable方法把cache开启
        view.measure(View.MeasureSpec.makeMeasureSpec(width, View.MeasureSpec.EXACTLY),
                View.MeasureSpec.makeMeasureSpec(height, View.MeasureSpec.EXACTLY));
        view.buildDrawingCache();
        return view.getDrawingCache();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.but_copy_imageview:
                copyView(ivPrimary);
                ivCopy.setImageBitmap(bean.bitmap);
                break;
            case R.id.but_clear_imageview:
                ivCopy.setImageResource(R.drawable.fail_empty_view);
                break;
        }
    }
}
