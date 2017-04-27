package com.example.demomore.utils;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;

/**
 * Created by Administrator on 2017/2/13.
 */

public class UtilityUtil {
    /**
     * 手动设置listview的高度
     *
     * @param listView
     * @return void
     * @author Doraemon
     * @time 2014年4月17日上午11:33:35
     */
    public static void setListViewHeightBasedOnChildren(ListView listView, int maxHeight) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return;
        }

        int totalHeight = listView.getPaddingTop() + listView.getPaddingBottom();
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            /**
             * listItem.measure(0,0) will throw a NPE if listItem is a ViewGroup
             * instance
             */
            if (listItem instanceof ViewGroup) {
                listItem.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            }
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        // listView.getDividerHeight()获取子项间分隔符占用的高度
        // ZTHouseHttpClient.height最后得到整个ListView完整显示需要的高度
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        if(params.height > maxHeight)
            params.height = maxHeight;
        listView.setLayoutParams(params);
    }
}
