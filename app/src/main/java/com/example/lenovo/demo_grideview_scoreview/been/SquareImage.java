package com.example.lenovo.demo_grideview_scoreview.been;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.facebook.drawee.view.SimpleDraweeView;

/**
 * Created by jash on 15-3-3.
 * 使用到com.facebook.fresco对本地的图片进行裁剪
 */
public class SquareImage extends SimpleDraweeView {
    public SquareImage(Context context) {
        super(context);
    }

    public SquareImage(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SquareImage(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        switch (MeasureSpec.getMode(widthMeasureSpec)) {
            case MeasureSpec.AT_MOST:
                MeasureSpec.getSize(widthMeasureSpec);
                break;
            case MeasureSpec.EXACTLY:
                break;
            case MeasureSpec.UNSPECIFIED:
                break;
        }

        if (getDrawable() != null) {
            float width = MeasureSpec.getSize(widthMeasureSpec);
//            int height = (int) (width / getDrawable().getIntrinsicWidth() * getDrawable().getIntrinsicHeight());
            int height = (int) width;
            heightMeasureSpec = MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY);
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
