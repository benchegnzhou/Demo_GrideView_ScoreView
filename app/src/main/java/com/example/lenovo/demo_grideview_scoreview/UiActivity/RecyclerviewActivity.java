package com.example.lenovo.demo_grideview_scoreview.UiActivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.example.lenovo.demo_grideview_scoreview.DataSever.DataSever;
import com.example.lenovo.demo_grideview_scoreview.R;
import com.example.lenovo.demo_grideview_scoreview.Utils.LogUtil;
import com.example.lenovo.demo_grideview_scoreview.adapter.BaseMultiple;



public class RecyclerviewActivity extends AppCompatActivity {
    private RecyclerView rvRecyclerview;
    private BaseMultiple baseMultipleAdapter;


//    @BindView(R.id.rv_recyclerview)
//    RecyclerView rvRecyclerview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview);
//        ButterKnife.bind(this);
        rvRecyclerview = (RecyclerView) findViewById(R.id.rv_recyclerview);
        initData();
        initAdapter();
        initClicklistener();
    }


    /**
     * 数据的初始化
     */
    private void initData() {

    }

    /**
     * 适配器的初始化
     */
    private void initAdapter() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rvRecyclerview.setLayoutManager(linearLayoutManager);
        baseMultipleAdapter = new BaseMultiple(DataSever.getMultipleItemData());
        //动画添加
        baseMultipleAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_BOTTOM);
        //是否只在第一次加载的时候显示
        baseMultipleAdapter.isFirstOnly(false);
        rvRecyclerview.setAdapter(baseMultipleAdapter);
        rvRecyclerview.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void SimpleOnItemClick(BaseQuickAdapter adapter, View view, int position) {

            }

            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                super.onItemClick(adapter, view, position);
            }

            @Override
            public void onItemLongClick(BaseQuickAdapter adapter, View view, int position) {
                super.onItemLongClick(adapter, view, position);
            }

            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                super.onItemChildClick(adapter, view, position);
            }

            @Override
            public void onItemChildLongClick(BaseQuickAdapter adapter, View view, int position) {
                super.onItemChildLongClick(adapter, view, position);
            }
        });
    }
    /**
     * 点击事件的初始化
     */
    private void initClicklistener() {
        baseMultipleAdapter.addData(DataSever.getMultipleMoreData());

    }


}
