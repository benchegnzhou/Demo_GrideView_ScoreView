# listView多条目的实现 #

> * listview多种类型条目显示

![](http://i.imgur.com/KoPoJrS.png)

一、整个布局是一个listview实线的，而listview里面是嵌套了三种不同展示类型的item，平常只显示一种listview条目，我们会用到常用的四个方法，但是显示不同种类型item的时候，还需要实现两个重要的API（getViewTypeCount()、getItemViewType()）。 
先看下主要的布局：

	<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
	    xmlns:tools="http://schemas.android.com/tools"
	    android:layout_width="match_parent"
	    android:layout_height="match_parent"
	     >
	
	     <ListView
	        android:id="@+id/lv"
	        android:layout_width="match_parent"
	        android:layout_height="wrap_content"
	         />   
	</RelativeLayout>

紧接着是三个不同类型的小条目的布局 
item1

	<?xml version="1.0" encoding="utf-8"?>
	<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
	    android:layout_width="match_parent"
	    android:layout_height="wrap_content"
	    android:padding="10dp"
	    android:orientation="horizontal"
	     >
	    <TextView 
	        android:id="@+id/tv_1"
	        android:text="1"
	        android:paddingRight="5dp"
	        android:layout_centerVertical="true"
	        android:layout_width="wrap_content"
	        android:layout_height="wrap_content"
	        />
	    <CheckBox 
	        android:id="@+id/cb"
	        android:checked="false"
	        android:focusable="false"
	        android:layout_alignParentRight="true"
	        android:layout_width="wrap_content"
	        android:layout_height="wrap_content"
	        />
	
	</RelativeLayout>


item2


	<?xml version="1.0" encoding="utf-8"?>
	<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
	    android:layout_width="match_parent"
	    android:layout_height="match_parent"
	    android:padding="10dp"
	    android:orientation="vertical" >
	    <TextView 
	        android:id="@+id/tv_2"
	        android:text="2"
	        android:paddingRight="5dp"
	        android:layout_centerVertical="true"
	        android:layout_width="wrap_content"
	        android:layout_height="wrap_content"></TextView>
	</LinearLayout>

item3

		<?xml version="1.0" encoding="utf-8"?>
	<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
	    android:layout_width="wrap_content"
	    android:layout_height="wrap_content"
	    android:padding="10dp"
	    android:orientation="horizontal" >
	    <TextView 
	        android:layout_gravity="center_vertical"
	        android:id="@+id/tv_3"
	        android:text="3"
	        android:layout_marginRight="5dp"
	        android:layout_centerVertical="true"
	        android:layout_width="wrap_content"
	        android:layout_height="wrap_content">
	
	    </TextView>
	
	    <ImageView
	        android:id="@+id/iv_3"
	       android:layout_alignParentRight="true"
	        android:layout_width="wrap_content"
	        android:layout_height="wrap_content"
	        android:src="@drawable/yapei" />

	</RelativeLayout>

下面就是核心的代码实现了：
	
	package com.example.listviewshow;
	
	import java.util.ArrayList;
	import java.util.List;
	
	import android.os.Bundle;
	import android.app.Activity;
	import android.view.Menu;
	import android.view.View;
	import android.view.ViewGroup;
	import android.widget.BaseAdapter;
	import android.widget.CheckBox;
	import android.widget.ImageView;
	import android.widget.ListView;
	import android.widget.TextView;
	
	public class MainActivity extends Activity {
	
	    private ListView mListView;
    private List<String> mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mListView = (ListView) findViewById(R.id.lv);
        mList = new ArrayList<String>();
        for(int i = 0; i< 100; i++){
            mList.add(i+"");

        }
        mListView.setAdapter(new MyAdapter());
    }
    public class MyAdapter extends BaseAdapter{
        /**
         * 三种类型item
         */
        final int TYPE_1 = 0;
        final int TYPE_2 = 1;
        final int TYPE_3 = 2;

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return mList.size();
        }

        @Override
        public Object getItem(int position) {
            // TODO Auto-generated method stub
            return mList.get(position);
        }

        @Override
        public long getItemId(int position) {
            // TODO Auto-generated method stub
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // TODO Auto-generated method stub
            viewHolder holder;
            int type = getItemViewType(position);
            if (convertView == null) {
                holder = new viewHolder();
                switch (type) {
                case TYPE_1:
                    convertView = View.inflate(getApplicationContext(), R.layout.item_1, null);
                    holder.checkBox = (CheckBox) convertView.findViewById(R.id.cb);
                    holder.textView = (TextView) convertView.findViewById(R.id.tv_1);
                    break;
                case TYPE_2:
                    convertView = View.inflate(getApplicationContext(), R.layout.item_2, null);
                    holder.textView = (TextView) convertView.findViewById(R.id.tv_2);
                    break;
                case TYPE_3:
                    convertView = View.inflate(getApplicationContext(), R.layout.item_3, null);
                    holder.textView = (TextView) convertView.findViewById(R.id.tv_3);
                    holder.imageView = (ImageView) convertView.findViewById(R.id.iv_3);
                    break;
                default:
                    break;
                }
                convertView.setTag(holder);
            }else {
                holder = (viewHolder) convertView.getTag();
            }
            holder.textView.setText(Integer.toString(position));

            switch (type) {
            case TYPE_1:
                holder.checkBox.setChecked(true);
                break;
            case TYPE_2:
                break;
            case TYPE_3:
                holder.imageView.setBackgroundResource(R.drawable.yapei);
                break;
            }
            return convertView;
        }
        /**
         * 返回条目的总数量
         */
        @Override
        public int getViewTypeCount() {
            // TODO Auto-generated method stub
            return 3;
        }
        @Override
        public int getItemViewType(int position) {
            // TODO Auto-generated method stub
             int p = position % 6;
                if (p == 0)
                    return TYPE_1;
                else if (p < 3)
                    return TYPE_2;
                else if (p < 6)
                    return TYPE_3;
                else
                    return TYPE_1;
        }

    }
    static class viewHolder{
        CheckBox checkBox;
        TextView textView;
        ImageView imageView;

    }

}
