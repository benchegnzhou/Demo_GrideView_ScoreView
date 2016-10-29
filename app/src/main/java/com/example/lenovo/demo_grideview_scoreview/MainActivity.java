package com.example.lenovo.demo_grideview_scoreview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import com.example.lenovo.demo_grideview_scoreview.UiActivity.AdvanceWebViewActivity;
import com.example.lenovo.demo_grideview_scoreview.UiActivity.Animatio4nActivity;
import com.example.lenovo.demo_grideview_scoreview.UiActivity.AudioActivity;
import com.example.lenovo.demo_grideview_scoreview.UiActivity.CammerTailorActivity;
import com.example.lenovo.demo_grideview_scoreview.UiActivity.CardViewActivity;
import com.example.lenovo.demo_grideview_scoreview.UiActivity.ConfigurationChangeActivity;
import com.example.lenovo.demo_grideview_scoreview.UiActivity.CopyAndSavePictureActivity;
import com.example.lenovo.demo_grideview_scoreview.UiActivity.DeviceMessageShowActivity;
import com.example.lenovo.demo_grideview_scoreview.UiActivity.DialogActivity;
import com.example.lenovo.demo_grideview_scoreview.UiActivity.EditTextActivity;
import com.example.lenovo.demo_grideview_scoreview.UiActivity.FragmentChangeActivity;
import com.example.lenovo.demo_grideview_scoreview.UiActivity.PullToRefreshActivity;
import com.example.lenovo.demo_grideview_scoreview.UiActivity.RecyclerviewActivity;
import com.example.lenovo.demo_grideview_scoreview.UiActivity.ScannerActivity;
import com.example.lenovo.demo_grideview_scoreview.UiActivity.ShareSDkActivity;
import com.example.lenovo.demo_grideview_scoreview.UiActivity.ShoppingCartActivity;
import com.example.lenovo.demo_grideview_scoreview.UiActivity.SpinnerActivity;
import com.example.lenovo.demo_grideview_scoreview.UiActivity.TextViewTestActivity;
import com.example.lenovo.demo_grideview_scoreview.UiActivity.VideoViewActivity;
import com.example.lenovo.demo_grideview_scoreview.UiActivity.PicassoActivity;
import com.example.lenovo.demo_grideview_scoreview.Utils.ToastUtils;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
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
    private Button audioPlayer;
    private Button customDialog;
    private Button btn_spinner;
    private Button button_video_view;
    private Button button_pulltorefresh;
    private Button button_changeFragment;
    private Button button_picasso;
    private Button button_recyclerview;
    private Button button_sharesdk;
    private Button button_webview;
    private Button button_cardview;
    private Button button_picture_save;
    private Button button_animation_activity;
    private Button button_onconfiguration_changed;
    private Button button_cammertailor;
    private Button button_edit;
    private Button button_edit_shop_num;
    private Button btn_device_message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //mifalater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        setContentView(R.layout.activity_main);
        final GridView gridViewF = (GridView) findViewById(R.id.gview_first);
        GridView gridViewS = (GridView) findViewById(R.id.gview_second);
        mButton = (Button) findViewById(R.id.button_main);
        codeScanner = (Button) findViewById(R.id.button_code_scanner);
        audioPlayer = (Button) findViewById(R.id.button_audio_play);
        customDialog = (Button) findViewById(R.id.button_dialog_play);
        btn_spinner = (Button) findViewById(R.id.button_spinner);
        button_video_view = (Button) findViewById(R.id.button_video_view);
        button_pulltorefresh = (Button) findViewById(R.id.button_pulltorefresh);
        button_changeFragment = (Button) findViewById(R.id.button_fragment_test);
        button_picasso = (Button) findViewById(R.id.button_picasso);
        button_recyclerview = (Button) findViewById(R.id.button_recyclerview);
        button_sharesdk = (Button) findViewById(R.id.button_sharesdk);
        button_webview = (Button) findViewById(R.id.button_webview);
        button_cardview = (Button) findViewById(R.id.button_cardview);
        button_picture_save = (Button) findViewById(R.id.button_picture_save);
        button_animation_activity = (Button) findViewById(R.id.button_animation_activity);
        button_onconfiguration_changed = (Button) findViewById(R.id.button_onconfiguration_changed);
        button_cammertailor = (Button) findViewById(R.id.button_cammertailor);
        button_edit = (Button) findViewById(R.id.button_edit);
        button_edit_shop_num = (Button) findViewById(R.id.button_edit_shop_num);
        btn_device_message = (Button) findViewById(R.id.btn_device_message);

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
                queryItemSelected(parent, position);
            }
        });

    }

    private void initListener() {
        mButton.setOnClickListener(this);
        codeScanner.setOnClickListener(this);
        audioPlayer.setOnClickListener(this);
        customDialog.setOnClickListener(this);
        btn_spinner.setOnClickListener(this);
        button_video_view.setOnClickListener(this);
        button_pulltorefresh.setOnClickListener(this);
        button_changeFragment.setOnClickListener(this);
        button_picasso.setOnClickListener(this);
        button_recyclerview.setOnClickListener(this);
        button_sharesdk.setOnClickListener(this);
        button_webview.setOnClickListener(this);
        button_cardview.setOnClickListener(this);
        button_picture_save.setOnClickListener(this);
        button_animation_activity.setOnClickListener(this);
        button_onconfiguration_changed.setOnClickListener(this);
        button_cammertailor.setOnClickListener(this);
        button_edit.setOnClickListener(this);
        button_edit_shop_num.setOnClickListener(this);
        btn_device_message.setOnClickListener(this);
    }

    /**
     * 清除多选条目中所有的已经选中的条目
     */
    private void clearAllMultiple() {
        if (list.size() != 0) {
            clearnAllSelected(0xAAEEEEEE);
            list = new ArrayList<>();  //清空当前的集合存放下一次的数据
        }
    }

    /**
     * 清除所有选中的条目
     */
    private void clearnAllSelected(int colorCancel) {
        for (int i = 0; i < list.size(); i++) {
            mParent.getChildAt(list.get(i)).setBackgroundColor(colorCancel);
        }

    }

    /**
     * 查询当前选中的条目是不是已经保存状态
     *
     * @param position
     */
    private void queryItemSelected(AdapterView<?> parent, int position) {
        for (int i = 0; i < list.size(); i++) {
            if (position == list.get(i)) {  //集合已有有这个条目，移除
                list.remove(i);
                parent.getChildAt(position).setBackgroundColor(0xAAEEEEEE);
                return;
            }
        }

        list.add(position);
        parent.getChildAt(position).setBackgroundColor(0x44E73B2F);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //文本间距测试
            case R.id.button_main:
                ToastUtils.showToast("我是详情");
                Intent intent = new Intent(MainActivity.this, TextViewTestActivity.class);
                startActivity(intent);
                break;
            //扫码的界面
            case R.id.button_code_scanner:
                Intent scanIntent = new Intent(MainActivity.this, ScannerActivity.class);
                startActivity(scanIntent);
                break;
            //本地段音频的播放
            case R.id.button_audio_play:
                Intent audioIntent = new Intent(MainActivity.this, AudioActivity.class);
                startActivity(audioIntent);
                break;
            //自定义的对话框
            case R.id.button_dialog_play:
                Intent dialogIntent = new Intent(MainActivity.this, DialogActivity.class);
                startActivity(dialogIntent);
                break;
            //下拉列表
            case R.id.button_spinner:
                Intent spinnerIntent = new Intent(MainActivity.this, SpinnerActivity.class);
                startActivity(spinnerIntent);
                break;

            //网络视频播放的Demo
            case R.id.button_video_view:
                Intent videoviewIntent = new Intent(MainActivity.this, VideoViewActivity.class);
                startActivity(videoviewIntent);
                break;

            //下拉刷新的测试
            case R.id.button_pulltorefresh:
                Intent PullToRefreshIntent = new Intent(MainActivity.this, PullToRefreshActivity.class);
                startActivity(PullToRefreshIntent);
                break;

            //多个fragment切换的Demo
            case R.id.button_fragment_test:
                Intent changeFragmentIntent = new Intent(MainActivity.this, FragmentChangeActivity.class);
                startActivity(changeFragmentIntent);
                break;
            //使用picasso加载圆形头像
            case R.id.button_picasso:
                Intent roundnessIconIntent = new Intent(MainActivity.this, PicassoActivity.class);
                startActivity(roundnessIconIntent);
                break;

            //recyclerview的测试
            case R.id.button_recyclerview:
                Intent recyclerviewIntent = new Intent(MainActivity.this, RecyclerviewActivity.class);
                startActivity(recyclerviewIntent);
                break;

            //shareSDK的测试
            case R.id.button_sharesdk:
                Intent shareSDKIntent = new Intent(MainActivity.this, ShareSDkActivity.class);
                startActivity(shareSDKIntent);
                break;
            //高级webview的测试
            case R.id.button_webview:
                Intent webViewIntent = new Intent(MainActivity.this, AdvanceWebViewActivity.class);
                startActivity(webViewIntent);
                break;

            //cardview的测试
            case R.id.button_cardview:
                Intent cardViewIntent = new Intent(MainActivity.this, CardViewActivity.class);
                startActivity(cardViewIntent);
                break;

            //将一个布局样式用图片的形式保存到本地的方法
            case R.id.button_picture_save:
                Intent savePictureIntent = new Intent(MainActivity.this, CopyAndSavePictureActivity.class);
                startActivity(savePictureIntent);
                break;

            //实现Activity切换动画跳转过度的动画
            case R.id.button_animation_activity:
                Intent activityAimationnIntent = new Intent(MainActivity.this, Animatio4nActivity.class);
                startActivity(activityAimationnIntent);
                break;

            //activity屏幕切换的实验
            case R.id.button_onconfiguration_changed:
                Intent configurationChangeIntent = new Intent(MainActivity.this, ConfigurationChangeActivity.class);
                startActivity(configurationChangeIntent);
                break;

            //摄像头拍照裁剪和本地照片裁剪测试案例
            case R.id.button_cammertailor:
                Intent photoTailorIntent = new Intent(MainActivity.this, CammerTailorActivity.class);
                startActivity(photoTailorIntent);
                break;

            //edittext测试demo
            case R.id.button_edit:
                Intent edittextIntent = new Intent(MainActivity.this, EditTextActivity.class);
                startActivity(edittextIntent);
                break;


            //淘宝购物车逻辑实现测试demo
            case R.id.button_edit_shop_num:
                Intent edit_numIntent = new Intent(MainActivity.this, ShoppingCartActivity.class);
                startActivity(edit_numIntent);
                break;

            //设备信息获取工具类的Demo
            case R.id.btn_device_message:
                Intent deviceMessageIntent = new Intent(MainActivity.this, DeviceMessageShowActivity.class);
                startActivity(deviceMessageIntent);
                break;


        }

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
