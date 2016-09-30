package com.example.lenovo.demo_grideview_scoreview.UiActivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.lenovo.demo_grideview_scoreview.R;
import com.example.lenovo.demo_grideview_scoreview.Utils.LogUtil;
import com.handmark.pulltorefresh.library.ILoadingLayout;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;

import javax.security.auth.login.LoginException;

/**
 * 这个是根据网上张鸿洋的博客做的，详细的属性可以根据这个去修改
 * 链接： http://m.blog.csdn.net/article/details?id=52020340
 * ptrMode，ptrDrawable，ptrAnimationStyle这三个上面已经介绍过。
 * ptrRefreshableViewBackground 设置整个mPullRefreshListView的背景色
 * ptrHeaderBackground 设置下拉Header或者上拉Footer的背景色
 * ptrHeaderTextColor 用于设置Header与Footer中文本的颜色
 * ptrHeaderSubTextColor 用于设置Header与Footer中上次刷新时间的颜色
 * ptrShowIndicator如果为true会在mPullRefreshListView中出现icon，右上角和右下角，挺有意思的。
 * ptrHeaderTextAppearance ， ptrSubHeaderTextAppearance分别设置拉Header或者上拉Footer中字体的类型颜色等等。
 * ptrRotateDrawableWhilePulling当动画设置为rotate时，下拉是是否旋转。
 * ptrScrollingWhileRefreshingEnabled刷新的时候，是否允许ListView或GridView滚动。觉得为true比较好。
 * ptrLivstViewExtrasEnabled 决定了Header，Footer以何种方式加入mPullRefreshListView，true为headView方式加入，就是滚动时刷新头部会一起滚动。
 * 最后2个其实对于用户体验还是挺重要的，如果设置的时候考虑下~。其他的属性自己选择就好。
 */
public class PullToRefreshActivity extends AppCompatActivity {

    private PullToRefreshListView mPullRefreshListView;
    private LinkedList<String> mListItems;
    /**
     * 数据填充数目控制演示用，一次加载9条
     */
    private int mItemCount = 9;
    private ArrayAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pull_to_refresh);
        // 1. 得到控件
        mPullRefreshListView = (PullToRefreshListView) findViewById(R.id.pull_refresh_list);
        // 2. 设置刷新的模式
        mPullRefreshListView.setMode(PullToRefreshBase.Mode.BOTH);
        // 3. 初始化数据
        initDatas();
        // 4. 设置适配器
        mAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, mListItems);
        mPullRefreshListView.setAdapter(mAdapter);
        // 5. 设置监听事件,实现下拉加载和上拉加载更多
        mPullRefreshListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                LogUtil.e("下拉加载");
                //给下拉刷新设置当当前的系统时间
                setPulltorefreshLable(refreshView);

                //自定义下拉刷新的样式
                initIndicator();

                reqestDataformNet(false);
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                LogUtil.e("上拉加载更多");
                reqestDataformNet(true);
            }
        });
        mPullRefreshListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mPullRefreshListView.onRefreshComplete();
            }
        });

        //代码设置
        mPullRefreshListView.setScrollingWhileRefreshingEnabled(true);

    }

    /**
     * 自定义的刷新的样式。
     * 可以添加下拉刷新的提示语和上拉加载更多的提示语
     */
    private void initIndicator() {
        ILoadingLayout startLabels = mPullRefreshListView
                .getLoadingLayoutProxy(true, false);
        startLabels.setPullLabel("你可劲拉，拉...");// 刚下拉时，显示的提示
        startLabels.setRefreshingLabel("好嘞，正在刷新...");// 刷新时
        startLabels.setReleaseLabel("你敢放，我就敢刷新...");// 下来达到一定距离时，显示的提示

        ILoadingLayout endLabels = mPullRefreshListView.getLoadingLayoutProxy(
                false, true);
        endLabels.setPullLabel("你可劲拉，拉2...");// 刚下拉时，显示的提示
        endLabels.setRefreshingLabel("好嘞，正在刷新2...");// 刷新时
        endLabels.setReleaseLabel("你敢放，我就敢刷新2...");// 下来达到一定距离时，显示的提示
    }

    /**
     * 下拉的时间标签的设置
     *
     * @param refreshView
     */
    private void setPulltorefreshLable(PullToRefreshBase<ListView> refreshView) {
        //                String label = DateUtils.formatDateTime(
//                        getApplicationContext(),
//                        System.currentTimeMillis(),
//                        DateUtils.FORMAT_SHOW_TIME
//                                | DateUtils.FORMAT_SHOW_DATE
//                                | DateUtils.FORMAT_ABBREV_ALL);
        //获取系统的时间并显示
        SimpleDateFormat format2 = new SimpleDateFormat("yy-MM-dd H:m:s");
        Date curDate = new Date(System.currentTimeMillis());//获取当前时间
        String label = format2.format(curDate);

        // Update the LastUpdatedLabel
        refreshView.getLoadingLayoutProxy()
                .setLastUpdatedLabel(label);
    }


    /**
     * 加载数据
     */
    public void reqestDataformNet(boolean isLoadMore) {
        final boolean mIsLoadMore = isLoadMore;
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    if (!mIsLoadMore) {
                        mListItems = new LinkedList<String>();
                    }
                    int listsize = mListItems.size();
                    for (int i = mListItems.size(); i < listsize + mItemCount; i++) {
                        mListItems.add("我是更新的数据  " + i);
                    }

                    Thread.sleep(2000);
                    LogUtil.e("我在子线程执行");
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            LogUtil.e("刷新完成刷新UI");
                            mAdapter.notifyDataSetChanged();
                            mPullRefreshListView.onRefreshComplete();  //加载完成，恢复加载的状态

                        }
                    });

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }).start();
    }

    /**
     * 这一个模拟网络数据的加载
     */
    private void initDatas() {
        // 初始化数据和数据源
        mListItems = new LinkedList<>();

        for (int i = 0; i < mItemCount; i++) {
            mListItems.add("" + i);
        }
    }
}
