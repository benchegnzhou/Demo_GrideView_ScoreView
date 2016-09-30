package com.example.lenovo.demo_grideview_scoreview.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.lenovo.demo_grideview_scoreview.R;
import com.example.lenovo.demo_grideview_scoreview.mApplication;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 2016/9/21.
 */


public class MovieFragment extends Fragment {

    private ListView mlistview;
    private List<String> mListItems;
    private int mItemCount = 9;
    private ArrayAdapter<String> mAdapter;
    protected WeakReference<View> mRootView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//        if(veiw==null){
//            veiw = inflater.inflate(R.layout.fragment_movie, null);
//        }
//        if(mlistview==null){
//            mlistview = (ListView) veiw.findViewById(R.id.movie_lv);
//        }

        if (mRootView == null || mRootView.get() == null) {
            View  veiw = inflater.inflate(R.layout.fragment_movie, null);
            mRootView = new WeakReference<View>(veiw);
        } else {
            ViewGroup parent = (ViewGroup) mRootView.get().getParent();
            if (parent != null) {
                parent.removeView(mRootView.get());
            }
        }
        mlistview = (ListView) mRootView.get().findViewById(R.id.movie_lv);
            initData();
        initView();
        return mRootView.get();
    }

    /**
     * 初始化网络数据
     */
    private void initData() {
        // 初始化数据和数据源
        mListItems = new ArrayList<>();

        for (int i = 0; i < mItemCount; i++) {
            mListItems.add("武打片" + i);
        }
    }

    /**
     * 初始化view
     */
    private void initView() {
        mAdapter = new ArrayAdapter<String>(mApplication.mContext,
                android.R.layout.simple_list_item_1, mListItems);
        mlistview.setAdapter(mAdapter);
    }

    /**
     * 网络请求的接口
     */
    public void requestData(){
        int preSize =mListItems.size();
        for (int i = preSize; i <preSize+ mItemCount; i++) {
            mListItems.add("武打片" + i);
        }
        mAdapter.notifyDataSetChanged();
    }
}


