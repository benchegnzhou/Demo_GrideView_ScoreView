package com.example.lenovo.demo_grideview_scoreview.UiActivity;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.animation.BaseAnimation;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.example.lenovo.demo_grideview_scoreview.DataSever.DataSever;
import com.example.lenovo.demo_grideview_scoreview.R;
import com.example.lenovo.demo_grideview_scoreview.Utils.LogUtil;
import com.example.lenovo.demo_grideview_scoreview.adapter.BaseMultiple;


public class RecyclerviewActivity extends AppCompatActivity {
    private static final int TOTAL_COUNTER = 40;
    private RecyclerView rvRecyclerview;
    private BaseMultiple baseMultipleAdapter;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private View notLoadingView;
    private int mCurrentCounter=0;


//    @BindView(R.id.rv_recyclerview)
//    RecyclerView rvRecyclerview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview);
//        ButterKnife.bind(this);
        rvRecyclerview = (RecyclerView) findViewById(R.id.rv_recyclerview);
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeLayout);
        initData();
        initAdapter();

//        baseMultipleAdapter.setLoadingView(View.inflate(this,R.layout.loadingview_item,null));
        //填充一个view的时候推荐使用下面的这一种形式，可以防止不就得match_parent属性被强转成warp_content的形式
        //参数3：表明的是view和parent有没有添加关系
//        baseMultipleAdapter.setLoadingView(LayoutInflater.from(this).inflate(R.layout.loadingview_item, (ViewGroup) rvRecyclerview.getParent(),false));

        //空布局加载
        addEmptyView();
        //添加头布局
        addHeaderView();


        initListener();
        initClicklistener();
    }


    /**
     * 添加脚布局
     * 添加的脚布局将会被适配器以条目位置的形式添加进去，可以现在添加的脚布局在所有脚布局中的位置
     */
    private void addFootView() {
        View footerView = LayoutInflater.from(this).inflate(R.layout.header_view, null);
        baseMultipleAdapter.addFooterView(footerView);

    }

    /**
     * 添加头布局
     * 添加的头条目将会被适配器以条目位置的形式添加进去，可以现在添加的头条目在所有头条目中的位置
     */
    private void addHeaderView() {
        View header_view = LayoutInflater.from(this).inflate(R.layout.header_view, null);
        baseMultipleAdapter.addHeaderView(header_view, 2);
    }


    /**
     * recyclerview 加载空布局
     */
    private void addEmptyView() {
        /**
         * 通过实验得出结论，没有数据的时候会自动的加载空布局，当然我们也可以手动的添加
         */
        if (DataSever.getMultipleItemData() == null || DataSever.getMultipleItemData().size() == 0) {  //集合中没有数据时候使用空布局

            //添加空布局(没有数据展示时的布局)
            View empty = inflateView(R.layout.empty_view);
            //参数1： 是空布局的同时也是头布局，参数2 ： 是空布局的同时也是脚布局  参数3： 布局的view
            baseMultipleAdapter.setEmptyView(false, false, empty);
        } else {  //集合中有数据的时候展示数据或者刷新操作

        }
    }


    /**
     * 添加空布局
     * 填充一个view的时候推荐使用下面的这一种形式，可以防止不就得match_parent属性被强转成warp_content的形式
     *
     * @param empty_view_id
     * @return
     */
    private View inflateView(int empty_view_id) {
        //填充一个view的时候推荐使用下面的这一种形式，可以防止不就得match_parent属性被强转成warp_content的形式
        //参数3：表明的是view和parent有没有添加关系
        return LayoutInflater.from(this).inflate(empty_view_id, (ViewGroup) rvRecyclerview.getParent(), false);
    }

    private void initListener() {
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                LogUtil.e("我是刷新");
                //数据改变刷新
                // baseMultipleAdapter.setNewData();
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });
        //加载更多的操作进过3步骤
        //1. 打开loadingMore
        baseMultipleAdapter.openLoadMore(DataSever.getMultipleItemData().size());
        //2.添加脚布局
        //添加脚布局  ,显示脚布局，加载条就会消失
//        addFootView();

        //3. loadingMore 监听
        baseMultipleAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {

                LogUtil.e("我是加载更多");
                loadMoreData();
            }


        });


    }

    private  long delayMillis=1;    //数据加载时间
    private boolean isErr;
    /**
     * 加载更多数据
     */
    private void loadMoreData() {

        rvRecyclerview.post(new Runnable() {
            @Override
            public void run() {
                if (mCurrentCounter >= TOTAL_COUNTER) {
                    isErr=true;
                    baseMultipleAdapter.loadComplete();
                    if (notLoadingView == null) {
                        notLoadingView = getLayoutInflater().inflate(R.layout.header_view, (ViewGroup) rvRecyclerview.getParent(), false);
                    }
                    //添加脚布局
                    baseMultipleAdapter.addFooterView(notLoadingView);    //这里的脚布局，其实相当于加载更多
                } else {
                    if (isErr) {
                new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                baseMultipleAdapter.addData(DataSever.getMoreMultipleItemData());

                                mCurrentCounter = baseMultipleAdapter.getData().size();   //获取条目的总数
                                baseMultipleAdapter.openLoadMore(mCurrentCounter);
                            }
                        }, delayMillis);
                    } else {
                        isErr = true;
                        Toast.makeText(RecyclerviewActivity.this, R.string.network_err, Toast.LENGTH_LONG).show();
                        baseMultipleAdapter.showLoadMoreFailedView();

                    }
                }
            }
        });
    }


    /**
     * 数据的初始化
     */
    private void initData() {

    }

    /**
     * 适配器的初始化
     * 添加动画效果
     */
    private void initAdapter() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rvRecyclerview.setLayoutManager(linearLayoutManager);
        baseMultipleAdapter = new BaseMultiple(DataSever.getMultipleItemData());
        //动画添加，这种是系统帮助提供的
//        baseMultipleAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_BOTTOM);

        //当然我们还可以自定义，这种方式是支持多种动画并存的，固定的写法，使用的时候直接的copy
        baseMultipleAdapter.openLoadAnimation(new BaseAnimation() {
            @Override
            public Animator[] getAnimators(View view) {
//                view.setScaleX();
                return new Animator[]{
                        ObjectAnimator.ofFloat(view, "alpha", 0, 1f),
                        ObjectAnimator.ofFloat(view, "scaleX", 0, 1.2f, 1),
                        ObjectAnimator.ofFloat(view, "scaleY", 0, 1.2f, 1),
                        ObjectAnimator.ofFloat(view, "translationY", 1f, 0f),
                };
            }
        });

        //是否只在第一次加载的时候显示
        baseMultipleAdapter.isFirstOnly(false);

        rvRecyclerview.addOnItemTouchListener(new OnItemClickListener() {
            //单个条目的点击事件
            @Override
            public void SimpleOnItemClick(BaseQuickAdapter adapter, View view, int position) {
                LogUtil.e("SimpleOnItemClick----------" + position + "----------view" + view);
            }

            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                super.onItemClick(adapter, view, position);
                LogUtil.e("onItemClick----------" + position + "----------view" + view);
            }

            @Override
            public void onItemLongClick(BaseQuickAdapter adapter, View view, int position) {
                super.onItemLongClick(adapter, view, position);
                LogUtil.e("onItemLongClick----------" + position + "----------view" + view);
            }

            //这个在多条目的实现中，子条目的点击事件不起作用
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                super.onItemChildClick(adapter, view, position);
            }

            //这个在多条目的实现中，子条目的点击事件不起作用
            @Override
            public void onItemChildLongClick(BaseQuickAdapter adapter, View view, int position) {
                super.onItemChildLongClick(adapter, view, position);
            }
        });
        //多条目的时候不生效
        rvRecyclerview.addOnItemTouchListener(new OnItemChildClickListener() {
            @Override
            public void SimpleOnItemChildClick(BaseQuickAdapter adapter, View view, int position) {

            }
        });

        rvRecyclerview.setAdapter(baseMultipleAdapter);
    }


    /**
     * 点击事件的初始化
     */
    private void initClicklistener() {
//        baseMultipleAdapter.addData(DataSever.getMultipleMoreData());

    }


}
