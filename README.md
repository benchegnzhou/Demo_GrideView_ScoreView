# Demo_GrideView_ScoreView


> * gridView示例
 
> * 声音播放的示例
 
> * pulltorefresh 下拉刷新上拉加载更多的示例


转自：
(原文地址：)http://m.blog.csdn.net/article/details?id=52020340
http://blog.csdn.net/lmj623565791/article/details/38238749，本文出自：【张鸿洋的博客】


# 1、ListView下拉刷新快速入门 #
pull-to-refresh对ListView进行了封装，叫做：PullToRefreshListView，用法和listview没什么区别，下面看demo.
布局文件：
 

	<RelativeLayoutxmlns:android="http://schemas.android.com/apk/res/android"
		xmlns:tools="http://schemas.android.com/tools"
		android:layout_width="match_parent"
		android:layout_height="match_parent">
		<com.handmark.pulltorefresh.library.PullToRefreshListView
		xmlns:ptr="http://schemas.android.com/apk/res-auto"
		android:id="@+id/pull_refresh_list"
		android:layout_width="fill_parent"
		android:layout_height="fill_parent"
		android:cacheColorHint="#00000000"
		android:divider="#19000000"
		android:dividerHeight="4dp"
		android:fadingEdge="none"
		android:fastScrollEnabled="false"
		android:footerDividersEnabled="false"
		android:headerDividersEnabled="false"
		android:smoothScrollbar="true">
	com.handmark.pulltorefresh.library.PullToRefreshListView>
	RelativeLayout>

声明了一个PullToRefreshListView，里面所有的属性都是ListView的，没有任何其他属性，当然了PullToRefreshListView也提供了很多配置的属性，后面会详细介绍。
Activity的代码:
 

	package com.example.zhy_pulltorefreash_chenyoca;  
	import java.util.LinkedList;  
	import android.app.Activity;  
	import android.os.AsyncTask;  
	import android.os.Bundle;  
	import android.text.format.DateUtils;  
	import android.widget.ArrayAdapter;  
	import android.widget.ListView;  
	import com.handmark.pulltorefresh.library.PullToRefreshBase;  
	import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;  
	import com.handmark.pulltorefresh.library.PullToRefreshListView;  
	publicclass PullToRefreshListActivity extends Activity {  
	private LinkedList mListItems;  
	/**
     * 上拉刷新的控件
     */
	private PullToRefreshListView mPullRefreshListView;  
	private ArrayAdapter mAdapter;  
	privateint mItemCount = 9;  

	@Override
	protectedvoid onCreate(Bundle savedInstanceState)  
    {  
		super.onCreate(savedInstanceState);  
        setContentView(R.layout.activity_main);  
		// 得到控件
        mPullRefreshListView = (PullToRefreshListView) findViewById(R.id.pull_refresh_list);  
		//初始化数据
        initDatas();  
		//设置适配器
        mAdapter = new ArrayAdapter(this,  
                android.R.layout.simple_list_item_1, mListItems);  
        mPullRefreshListView.setAdapter(mAdapter);  
		// 设置监听事件
        mPullRefreshListView  
                .setOnRefreshListener(new OnRefreshListener()  
                {  
	@Override
	publicvoid onRefresh(PullToRefreshBase refreshView) {  
                        String label = DateUtils.formatDateTime(  
                                getApplicationContext(),  
                                System.currentTimeMillis(),  
                                DateUtils.FORMAT_SHOW_TIME  
                                        | DateUtils.FORMAT_SHOW_DATE  
                                        | DateUtils.FORMAT_ABBREV_ALL);  
	// 显示最后更新的时间
    refreshView.getLoadingLayoutProxy().setLastUpdatedLabel(label);  
	// 模拟加载任务
	new GetDataTask().execute();  
      }  
     });  
    }  


	privatevoid initDatas(){  
	// 初始化数据和数据源
	mListItems = new LinkedList();  
	for (int i = 0; i < mItemCount; i++) {  
	     mListItems.add("" + i);  
        }  
    }  
	privateclass GetDataTask extends AsyncTask {  
	@Override
	protected String doInBackground(Void... params) {  
	try{  
	    Thread.sleep(2000);  
	    } catch (InterruptedException e) { }  

	return"" + (mItemCount++);  
	    }  

	@Override
	protectedvoid onPostExecute(String result) {  
	            mListItems.add(result);  
	            mAdapter.notifyDataSetChanged();  
	// Call onRefreshComplete when the list has been refreshed.
	            mPullRefreshListView.onRefreshComplete();  
	        }  
	    }  
	}  

代码极其简单，得到PullToRefreshListView控件，然后像ListView一样设置数据集。当然了，我们有下拉刷新，所以必须设置下拉刷新的回调：
setOnRefreshListener(new OnRefreshListener(){}
我们在回调中模拟了一个异步任务，加载了一个Item。
效果图：

下拉时，执行我们的GetDataTask任务，任务执行完成后在onPostExecute中 调用mPullRefreshListView.onRefreshComplete();完成刷新。
是不是分分钟实现下拉刷新。当然了，你可能会有疑问，下拉刷新的指示器上的文字可以自定义吗？那个图片可以换成箭头吗？说好的上拉加载更多呢？后面会一一添加~


# 2、添加上拉加载更多 #

如过希望实现上拉加载更多，那么首先需要在布局文件的声明属性中添加一个属性，用于指定目前的下拉模式：
view plaincopy在CODE上查看代码片派生到我的代码片

	<RelativeLayoutxmlns:android="http://schemas.android.com/apk/res/android"
	xmlns:tools="http://schemas.android.com/tools"
	android:layout_width="match_parent"
	android:layout_height="match_parent">
	<com.handmark.pulltorefresh.library.PullToRefreshListView
	xmlns:ptr="http://schemas.android.com/apk/res-auto"
	android:id="@+id/pull_refresh_list"
	android:layout_width="fill_parent"
	android:layout_height="fill_parent"
	android:cacheColorHint="#00000000"
	android:divider="#19000000"
	android:dividerHeight="4dp"
	android:fadingEdge="none"
	android:fastScrollEnabled="false"
	android:footerDividersEnabled="false"
	android:headerDividersEnabled="false"
	android:smoothScrollbar="true"
	ptr:ptrMode="both">
	com.handmark.pulltorefresh.library.PullToRefreshListView>
	RelativeLayout>
我们添加了一个属性：ptr:ptrMode="both" ，意思：上拉和下拉都支持。
可选值为：disabled（禁用下拉刷新），pullFromStart（仅支持下拉刷新），pullFromEnd（仅支持上拉刷新），both（二者都支持），manualOnly（只允许手动触发）
当然了，如果你不喜欢在布局文件中指定，完全可以使用代码设置，在onCreate里面写：mPullRefreshListView.setMode(Mode.BOTH);//设置你需要的模式
设置了模式为双向都支持，当然必须为上拉和下拉分别设置回调，请看下面的代码：
[java]view plaincopy在CODE上查看代码片派生到我的代码片

	package com.example.zhy_pulltorefreash_chenyoca;  
	import java.util.LinkedList;  
	import android.app.Activity;  
	import android.os.AsyncTask;  
	import android.os.Bundle;  
	import android.text.format.DateUtils;  
	import android.util.Log;  
	import android.widget.ArrayAdapter;  
	import android.widget.ListView;  
	import com.handmark.pulltorefresh.library.PullToRefreshBase;  
	import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;  
	import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;  
	import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener2;  
	import com.handmark.pulltorefresh.library.PullToRefreshListView;  
	publicclass PullToRefreshListActivity extends Activity  
	{  
	private LinkedList mListItems;  
	/**
     * 上拉刷新的控件
     */
	private PullToRefreshListView mPullRefreshListView;  
	private ArrayAdapter mAdapter;  
	privateint mItemCount = 9;  
	@Override
	protectedvoid onCreate(Bundle savedInstanceState)  
	    {  
	super.onCreate(savedInstanceState);  
        setContentView(R.layout.activity_main);  
	// 得到控件
        mPullRefreshListView = (PullToRefreshListView) findViewById(R.id.pull_refresh_list);  
        mPullRefreshListView.setMode(Mode.BOTH);  
	// 初始化数据
        initDatas();  
	// 设置适配器
        mAdapter = new ArrayAdapter(this,  
                android.R.layout.simple_list_item_1, mListItems);  
        mPullRefreshListView.setAdapter(mAdapter);  
        mPullRefreshListView  
                .setOnRefreshListener(new OnRefreshListener2()  
                {  
	@Override
	publicvoid onPullDownToRefresh(  
                            PullToRefreshBase refreshView)  
                    {  
                        Log.e("TAG", "onPullDownToRefresh");  
	//这里写下拉刷新的任务
	new GetDataTask().execute();  
                    }  
	@Override
	publicvoid onPullUpToRefresh(  
                            PullToRefreshBase refreshView)  
                    {  
                        Log.e("TAG", "onPullUpToRefresh");  
	//这里写上拉加载更多的任务
	new GetDataTask().execute();  
                    }  
                });  
    }  
	privatevoid initDatas()  
    {  
	// 初始化数据和数据源
        mListItems = new LinkedList();  
	for (int i = 0; i < mItemCount; i++)  
        {  
            mListItems.add("" + i);  
        }  
    }  
	privateclass GetDataTask extends AsyncTask  
    {  
	@Override
	protected String doInBackground(Void... params){  
	try { 
         Thread.sleep(2000);  
         } catch (InterruptedException e){ }  
		 return"" + (mItemCount++);  
        }  
	@Override
	protectedvoid onPostExecute(String result)  
        {  
            mListItems.add(result);  
            mAdapter.notifyDataSetChanged();  
	// Call onRefreshComplete when the list has been refreshed.
            mPullRefreshListView.onRefreshComplete();  
        }  
    }  

和第一段的代码只有一个地方有区别，可能很难发现：
mPullRefreshListView.setOnRefreshListener(new OnRefreshListener2(){});注意这里的接口类型是OnRefreshListener2，多了个
2，和上面的不一样，这个接口包含两个方法，一个上拉回调，一个下拉回调。好了，这样我们就成功添加了上拉与下拉，并且分别可以控制其回调代码。
效果图：
咋样，是不是也很简单~注:如果你的上拉和下拉需求是执行一样的代码，那么你可以继续注册OnRefreshListener接口，上拉和下拉都会执行同一个方法。
接下来介绍如何使用带下拉刷新和加载更多的的GridView和自定义样式~
![](http://i.imgur.com/OWRY3iO.gif)


# 3、带下拉和上拉的GridView ( PullToRefreshGridView ) #

同样的pull-to-refresh把GridView封装为：PullToRefreshGridView 。用法和PullToRefreshListView一摸一样~
首先看主布局文件：
[html]view plaincopy在CODE上查看代码片派生到我的代码片
xmlversion="1.0"encoding="utf-8"?>
<LinearLayoutxmlns:android="http://schemas.android.com/apk/res/android"
android:layout_width="fill_parent"
android:layout_height="fill_parent"
android:orientation="vertical">
<com.handmark.pulltorefresh.library.PullToRefreshGridView
xmlns:ptr="http://schemas.android.com/apk/res-auto"
android:id="@+id/pull_refresh_grid"
android:layout_width="fill_parent"
android:layout_height="fill_parent"
android:columnWidth="100dp"
android:gravity="center_horizontal"
android:horizontalSpacing="1dp"
android:numColumns="auto_fit"
android:stretchMode="columnWidth"
android:verticalSpacing="1dp"
ptr:ptrDrawable="@drawable/ic_launcher"
ptr:ptrMode="both"/>
LinearLayout>
PullToRefreshGridView 的item的布局文件：
[html]view plaincopy在CODE上查看代码片派生到我的代码片
xmlversion="1.0"encoding="utf-8"?>
<TextViewxmlns:android="http://schemas.android.com/apk/res/android"
android:id="@+id/id_grid_item_text"
android:layout_width="100dp"
android:gravity="center"
android:textColor="#ffffff"
android:textSize="16sp"
android:background="#000000"
android:layout_height="100dp"/>

接下来就是Activity的代码了：
[java]view plaincopy在CODE上查看代码片派生到我的代码片
publicclass PullToRefreshGridActivity extends Activity  
{  
private LinkedList mListItems;  
private PullToRefreshGridView mPullRefreshListView;  
private ArrayAdapter mAdapter;  
privateint mItemCount = 10;  
@Override
protectedvoid onCreate(Bundle savedInstanceState)  
    {  
super.onCreate(savedInstanceState);  
        setContentView(R.layout.activity_ptr_grid);  
// 得到控件
        mPullRefreshListView = (PullToRefreshGridView) findViewById(R.id.pull_refresh_grid);  
// 初始化数据和数据源
        initDatas();  
        mAdapter = new ArrayAdapter(this, R.layout.grid_item,  
                R.id.id_grid_item_text, mListItems);  
        mPullRefreshListView.setAdapter(mAdapter);  
        mPullRefreshListView  
                .setOnRefreshListener(new OnRefreshListener2()  
                {  
@Override
publicvoid onPullDownToRefresh(  
                            PullToRefreshBase refreshView)  
                    {  
                        Log.e("TAG", "onPullDownToRefresh"); // Do work to
                        String label = DateUtils.formatDateTime(  
                                getApplicationContext(),  
                                System.currentTimeMillis(),  
                                DateUtils.FORMAT_SHOW_TIME  
                                        | DateUtils.FORMAT_SHOW_DATE  
                                        | DateUtils.FORMAT_ABBREV_ALL);  
// Update the LastUpdatedLabel
                        refreshView.getLoadingLayoutProxy()  
                                .setLastUpdatedLabel(label);  
new GetDataTask().execute();  
                    }  
@Override
publicvoid onPullUpToRefresh(  
                            PullToRefreshBase refreshView)  
                    {  
                        Log.e("TAG", "onPullUpToRefresh"); // Do work to refresh
// the list here.
new GetDataTask().execute();  
                    }  
                });  
    }  
privatevoid initDatas()  
    {  
        mListItems = new LinkedList();  
for (int i = 0; i < mItemCount; i++)  
        {  
            mListItems.add(i + "");  
        }  
    }  
privateclass GetDataTask extends AsyncTask  
    {  
@Override
protected Void doInBackground(Void... params)  
        {  
try
            {  
                Thread.sleep(2000);  
            } catch (InterruptedException e)  
            {  
            }  
returnnull;  
        }  
@Override
protectedvoid onPostExecute(Void result)  
        {  
            mListItems.add("" + mItemCount++);  
            mAdapter.notifyDataSetChanged();  
// Call onRefreshComplete when the list has been refreshed.
            mPullRefreshListView.onRefreshComplete();  
        }  
    }  

基本上上例没有任何区别，直接看效果图吧：

效果还是不错的，如果你比较细心会发现，那个下拉刷新的转圈的图片咋变成机器人了，那是因为我在布局文件里面设置了：
[html]view plaincopy在CODE上查看代码片派生到我的代码片
<com.handmark.pulltorefresh.library.PullToRefreshGridView
ptr:ptrDrawable="@drawable/ic_launcher"
       ...  
/>

当然了这是旋转的效果，一般常用的还有，一个箭头倒置的效果，其实也很简单，一个属性：
ptr:ptrAnimationStyle="flip"
去掉 ptr:ptrDrawable="@drawable/ic_launcher"这个属性，如果你希望用下图默认的箭头，你也可以自定义。
添加后，箭头就是这个样子：

ptr:ptrAnimationStyle的取值：flip（翻转动画）， rotate（旋转动画） 。 
ptr:ptrDrawable则就是设置图标了。

# 4、自定义下拉指示器文本内容等效果 #

可以在初始化完成mPullRefreshListView后，通过mPullRefreshListView.getLoadingLayoutProxy()可以得到一个ILoadingLayout对象，这个对象可以设置各种指示器中的样式、文本等。
[java]view plaincopy在CODE上查看代码片派生到我的代码片
ILoadingLayout startLabels = mPullRefreshListView  
                .getLoadingLayoutProxy();  
        startLabels.setPullLabel("你可劲拉，拉...");// 刚下拉时，显示的提示
        startLabels.setRefreshingLabel("好嘞，正在刷新...");// 刷新时
        startLabels.setReleaseLabel("你敢放，我就敢刷新...");// 下来达到一定距离时，显示的提示

如果你比较细心，会发现，前面我们设置上次刷新时间已经用到了：
// Update the LastUpdatedLabel
refreshView.getLoadingLayoutProxy().setLastUpdatedLabel(label);
现在的效果是：



默认是上拉和下拉的字同时改变的，如果我希望单独改变呢？
[java]view plaincopy在CODE上查看代码片派生到我的代码片
privatevoid initIndicator()  
    {  
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

mPullRefreshListView.getLoadingLayoutProxy(true, false);接收两个参数，为true,false返回设置下拉的ILoadingLayout；为false,true返回设置上拉的。

> * 5、常用的一些属性

	当然了，pull-to-refresh在xml中还能定义一些属性：
	ptrMode，ptrDrawable，ptrAnimationStyle这三个上面已经介绍过。
	ptrRefreshableViewBackground 设置整个mPullRefreshListView的背景色
	ptrHeaderBackground 设置下拉Header或者上拉Footer的背景色
	ptrHeaderTextColor 用于设置Header与Footer中文本的颜色
	ptrHeaderSubTextColor 用于设置Header与Footer中上次刷新时间的颜色
	ptrShowIndicator如果为true会在mPullRefreshListView中出现icon，右上角和右下角，挺有意思的。
	ptrHeaderTextAppearance ， ptrSubHeaderTextAppearance分别设置拉Header或者上拉Footer中字体的类型颜色等等。
	ptrRotateDrawableWhilePulling当动画设置为rotate时，下拉是是否旋转。
	ptrScrollingWhileRefreshingEnabled刷新的时候，是否允许ListView或GridView滚动。觉得为true比较好。
	ptrListViewExtrasEnabled 决定了Header，Footer以何种方式加入mPullRefreshListView，true为headView方式加入，就是滚动时刷新头部会一起滚动。
	最后2个其实对于用户体验还是挺重要的，如果设置的时候考虑下~。其他的属性自己选择就好。
	注：上述属性很多都可以代码控制，如果有需要可以直接mPullRefreshListView.set属性名 查看
	以上为pull-to-refresh所有支持的属性~~
	 源码有个类中的
	FloatMath.floor方法过时报错了，替换为Math.floor即可
	
	好了，如果你觉得本篇博客对你有用，就点个赞~留个言吧







