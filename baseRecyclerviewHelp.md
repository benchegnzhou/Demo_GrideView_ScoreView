# baseRecyclerviewHelp一个你不得不知的简易库 #


项目地址： [https://github.com/benchegnzhou/BaseRecyclerViewAdapterHelper.git](https://github.com/CymChad/BaseRecyclerViewAdapterHelper)

## 介绍： ##
* 基于base-adapter-helper开源库的原理，提供了更多的功能。可以让RecyclerView adapter的使用更简单。 
## 运行效果： ##

使用说明：

* 下载 apk

1. 优化Adapter代码（减少百分之70%代码）
1. 添加点击item点击、长按事件、以及item子控件的点击事件
1. 添加加载动画（一行代码轻松切换5种默认动画）
1. 添加头部、尾部、下拉刷新、上拉加载（感觉又回到ListView时代）
1. 设置自定义的加载更多布局
1. 添加分组（随心定义分组头部）
1. 自定义不同的item类型（简单配置、无需重写额外方法）
1. 设置空布局（比Listview的setEmptyView还要好用！）
1. 添加拖拽item



## 快速使用 ##

先在 build.gradle 的 repositories 添加:

	    allprojects {
	        repositories {
	            ...
	            maven { url "https://jitpack.io" }
	        }
	    }
然后在dependencies添加:

	    dependencies {
	            compile 'com.github.CymChad:BaseRecyclerViewAdapterHelper:v1.8.5'
	    }




---------------------我是华丽的分割线----------

案例一. Recycleview展示 展示List 

* 1.创建Activity或者Fragment
[文件]com.chad.baserecyclerviewadapterhelper.Demo1AdapterActivity


![](http://i.imgur.com/6J7HfhW.png)


* 2.布局Rv界面
[文件]com.chad.baserecyclerviewadapterhelper.R.layout  的activity_simpel_base_quick_adapter


		<?xml version="1.0" encoding="utf-8"?>
			 <LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
		    xmlns:app="http://schemas.android.com/apk/res-auto"
		    android:layout_width="match_parent"
		    android:layout_height="match_parent"
		    android:orientation="vertical">
		    <android.support.v7.widget.RecyclerView
	        android:id="@+id/rv_list"
	        android:layout_width="match_parent"
	        android:layout_height="match_parent"
	        android:fadingEdge="none" />
		</LinearLayout>


* 3.布局item视图 [文件]layout/tweet.xml


![](http://i.imgur.com/P6nkMG2.png)




## 案例三. 点击事件:条目与子元素 ##

> OnRecyclerViewItemClickListener:监听条目点击监听器 类似ListView.OnItemClickListener


[文件]com.chad.baserecyclerviewadapterhelper.DemoListener1Activity

 	mQuickAdapter.setOnRecyclerViewItemClickListener(new BaseQuickAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                //打印条目的name
                Status s = (Status) mQuickAdapter.getData().get(position);
                System.out.println(s.mName);
            }
    });





> OnItemChildClickListener:条目上子元素的点击监听器

* a.在适配器中设置
> [文件]com.chad.baserecyclerviewadapterhelper.adapter.QuickAdapter
 	
	@Override
    protected void convert(BaseViewHolder helper, Status item) {
        helper.set...省略
                .setOnClickListener(R.id.tweetAvatar, new OnItemChildClickListener(){
                    @Override
                    public void onClick(View v) {
                        super.onClick(v);
                        System.out.println(v);
                    }
                });
            //加载图片    }



* b.在Activity中设置
> 可以根据控件的id来编写switch语句区分每个子控件。
 	
	mQuickAdapter.setOnRecyclerViewItemChildClickListener(new BaseQuickAdapter.OnRecyclerViewItemChildClickListener() {

            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                System.out.println(view);
            }
       、});
