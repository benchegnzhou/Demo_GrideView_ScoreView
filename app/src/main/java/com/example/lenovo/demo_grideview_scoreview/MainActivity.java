package com.example.lenovo.demo_grideview_scoreview;

import android.content.Context;
import android.content.Intent;
import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.lenovo.demo_grideview_scoreview.UiActivity.ScannerActivity;
import com.example.lenovo.demo_grideview_scoreview.UiActivity.TextViewTestActivity;
import com.example.lenovo.demo_grideview_scoreview.Utils.ToastUtils;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class MainActivity extends AppCompatActivity {
    private String[] iconName = {"通讯录", "日历", "照相机", "时钟", "游戏", "短信", "铃声",
            "设置", "语音", "天气", "浏览器", "视频", "热门"};
    /**
     * 记录单选条目中当前被选中的条目
     */
    private int SingleSelectionItem = -1;

    /**
     * 记录被选中的条目的索引
     */
    private ArrayList<Integer> list = new ArrayList<>();
    private AdapterView<?> mParent;
    private Button mButton;
    private Button codeScanner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //mifalater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        setContentView(R.layout.activity_main);
        final GridView gridViewF = (GridView) findViewById(R.id.gview_first);
        GridView gridViewS = (GridView) findViewById(R.id.gview_second);
        mButton = (Button) findViewById(R.id.button_main);
        codeScanner = (Button) findViewById(R.id.button_code_scanner);
        initListener();
        final mAdapter adapter = new mAdapter();
        gridViewF.setAdapter(adapter);
        gridViewS.setAdapter(new mAdapter());
        gridViewF.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ToastUtils.showToast(iconName[position]);
                if (SingleSelectionItem != position) {  //选中的条目改变
//                    gridViewF.getItemAtPosition(SingleSelectionItem).SingleSelectionItem
                    if (SingleSelectionItem != -1) {
                        parent.getChildAt(SingleSelectionItem).findViewById(R.id.cardview_fristgridviewitem).setBackgroundColor(0xAAEEEEEE);
                    }
                    view.findViewById(R.id.cardview_fristgridviewitem).setBackgroundColor(0x44E73B2F);
                    SingleSelectionItem = position;
                    clearAllMultiple();
                    //list.remove(true);  //清空当前的集合存放下一次的数据
                } else {
                    view.findViewById(R.id.cardview_fristgridviewitem).setBackgroundColor(0xAAEEEEEE);   //点击已经选中的按钮，选中取消
                    SingleSelectionItem = -1;
                    clearAllMultiple();
                }

            }
        });
        /**
         * 下面的gridView条目的点击事件
         */
        gridViewS.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mParent = parent;
                ToastUtils.showToast(iconName[position]);
                queryItemSelected(parent,position);
            }
        });

    }

    private void initListener() {
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.showToast("我是详情");
                Intent intent = new Intent(MainActivity.this, TextViewTestActivity.class);
                startActivity(intent);
            }
        });

        codeScanner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent scanIntent =new Intent(MainActivity.this, ScannerActivity.class);
                startActivity(scanIntent);
            }
        });
    }

    /**
     * 清除多选条目中所有的已经选中的条目
     */
    private void clearAllMultiple() {
        if(list.size()!=0){
            clearnAllSelected(0xAAEEEEEE);
            list=new ArrayList<>();  //清空当前的集合存放下一次的数据
        }
    }
    /**
     * 清除所有选中的条目
     */
    private void clearnAllSelected(int colorCancel) {
        for (int i = 0; i <list.size() ; i++) {
            mParent.getChildAt(list.get(i)).setBackgroundColor(colorCancel);
        }
        
    }
    /**
     * 查询当前选中的条目是不是已经保存状态
     * @param position
     */
    private void queryItemSelected(AdapterView<?> parent,int position) {
        for (int i = 0; i < list.size(); i++) {
            if(position==list.get(i)){  //集合已有有这个条目，移除
                list.remove(i);
                parent.getChildAt(position).setBackgroundColor(0xAAEEEEEE);
                return;
            }
        }

            list.add(position);
            parent.getChildAt(position).setBackgroundColor(0x44E73B2F);

    }




    class mAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return iconName.length;
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            TextView tv = null;
            if (convertView == null) {

                //convertView = mifalater.inflate(R.layout.item_gridview, null);
                convertView = LayoutInflater.from(MainActivity.this).inflate(R.layout.item_gridview, null);
                // convertView = View.inflate(MainActivity.this, R.layout.item_gridview, null);
//                tv = (TextView) convertView.findViewById(R.id.text_gridview);

            }
            convertView.findViewById(R.id.cardview_fristgridviewitem).setBackgroundColor(0xAAEEEEEE);
            tv = (TextView) convertView.findViewById(R.id.text_gridview);
            tv.setText(iconName[position]);
            return convertView;

        }
    }


}
