package com.example.lenovo.demo_grideview_scoreview.UiActivity;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.lenovo.demo_grideview_scoreview.R;

/**
 * Created by lenovo on 2016/10/29.
 */
public class ShoppingView extends View {

    private EditText shop_num;
    private int current_shopping_num = 0;
    private int MAX_SHOP_NUM = 214748364;   //最大承载的商品数目

    public ShoppingView(Context context) {
        this(context, null);
    }

    public ShoppingView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ShoppingView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //读取布局文件中给自定义控件使用的属性
        //参数1：布局的命名空间
        //参数2：布局中控件的属性
        //参数3：默认值，如果在读取控件的属性没有读取到时，就返回默认的值

        String nameSpace = "http://schemas.android.com/apk/res/android";
        int shopnum = attrs.getAttributeResourceValue(nameSpace, "shopnum", 0);
        initView();

    }


    /**
     * 初始化布局文件
     */
    private void initView() {
        View view = View.inflate(getContext(), R.layout.shoppingview, null);
        TextView tv_shopname = (TextView) view.findViewById(R.id.tv_shopname);    //当前商品的款号
        TextView tv_shopcolor = (TextView) view.findViewById(R.id.tv_shopcolor); //当前商品的颜色
        ImageView shop_sub = (ImageView) view.findViewById(R.id.shop_sub); //减少商品数目
        ImageView shop_add = (ImageView) view.findViewById(R.id.shop_add); //增加商品数目
        //当前购物车的数目
        shop_num = (EditText) view.findViewById(R.id.shop_num);
//        addView(view);
        setShopNum();
    }

    /**
     * 目前商品数目限定
     */
    private void setShopNum() {
        if (current_shopping_num < 0) {
            current_shopping_num = 0;

        } else if (current_shopping_num > MAX_SHOP_NUM) {
            current_shopping_num = MAX_SHOP_NUM;
        }
        shop_num.setText("" + current_shopping_num);

    }

    /**
     * 设置该商品的最大数目
     */
    private void setMax_Goods_Num(int max_num) {
        MAX_SHOP_NUM=max_num;
    }

    


}
