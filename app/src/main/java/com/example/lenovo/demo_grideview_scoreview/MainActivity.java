package com.example.lenovo.demo_grideview_scoreview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lenovo.demo_grideview_scoreview.UiActivity.AdvanceWebViewActivity;
import com.example.lenovo.demo_grideview_scoreview.UiActivity.Animatio4nActivity;
import com.example.lenovo.demo_grideview_scoreview.UiActivity.AudioActivity;
import com.example.lenovo.demo_grideview_scoreview.UiActivity.CammerTailorActivity;
import com.example.lenovo.demo_grideview_scoreview.UiActivity.CardViewActivity;
import com.example.lenovo.demo_grideview_scoreview.UiActivity.CircleImageViewActivity;
import com.example.lenovo.demo_grideview_scoreview.UiActivity.ConfigurationChangeActivity;
import com.example.lenovo.demo_grideview_scoreview.UiActivity.CopyAndSavePictureActivity;
import com.example.lenovo.demo_grideview_scoreview.UiActivity.DeviceMessageShowActivity;
import com.example.lenovo.demo_grideview_scoreview.UiActivity.DialogActivity;
import com.example.lenovo.demo_grideview_scoreview.UiActivity.EditTextActivity;
import com.example.lenovo.demo_grideview_scoreview.UiActivity.EncryptionActivity;
import com.example.lenovo.demo_grideview_scoreview.UiActivity.FragmentChangeActivity;
import com.example.lenovo.demo_grideview_scoreview.UiActivity.NoScrollGridView;
import com.example.lenovo.demo_grideview_scoreview.UiActivity.PicassoActivity;
import com.example.lenovo.demo_grideview_scoreview.UiActivity.PullToRefreshActivity;
import com.example.lenovo.demo_grideview_scoreview.UiActivity.RecyclerviewActivity;
import com.example.lenovo.demo_grideview_scoreview.UiActivity.ScannerActivity;
import com.example.lenovo.demo_grideview_scoreview.UiActivity.ShareSDkActivity;
import com.example.lenovo.demo_grideview_scoreview.UiActivity.ShoppingCartActivity;
import com.example.lenovo.demo_grideview_scoreview.UiActivity.SpinnerActivity;
import com.example.lenovo.demo_grideview_scoreview.UiActivity.TextViewTestActivity;
import com.example.lenovo.demo_grideview_scoreview.UiActivity.VideoViewActivity;
import com.example.lenovo.demo_grideview_scoreview.Utils.ToastUtils;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Bind(R.id.gview_second)
    NoScrollGridView gviewSecond;
    @Bind(R.id.button_main)
    Button buttonMain;
    @Bind(R.id.button_code_scanner)
    Button buttonCodeScanner;
    @Bind(R.id.button_audio_play)
    Button buttonAudioPlay;
    @Bind(R.id.button_dialog_play)
    Button buttonDialogPlay;
    @Bind(R.id.button_spinner)
    Button buttonSpinner;
    @Bind(R.id.button_video_view)
    Button buttonVideoView;
    @Bind(R.id.button_pulltorefresh)
    Button buttonPulltorefresh;
    @Bind(R.id.button_fragment_test)
    Button buttonFragmentTest;
    @Bind(R.id.button_picasso)
    Button buttonPicasso;
    @Bind(R.id.button_recyclerview)
    Button buttonRecyclerview;
    @Bind(R.id.button_sharesdk)
    Button buttonSharesdk;
    @Bind(R.id.button_webview)
    Button buttonWebview;
    @Bind(R.id.button_cardview)
    Button buttonCardview;
    @Bind(R.id.button_picture_save)
    Button buttonPictureSave;
    @Bind(R.id.button_animation_activity)
    Button buttonAnimationActivity;
    @Bind(R.id.button_onconfiguration_changed)
    Button buttonOnconfigurationChanged;
    @Bind(R.id.button_cammertailor)
    Button buttonCammertailor;
    @Bind(R.id.button_edit)
    Button buttonEdit;
    @Bind(R.id.btn_device_message)
    Button btnDeviceMessage;
    @Bind(R.id.button_edit_shop_num)
    Button buttonEditShopNum;
    @Bind(R.id.btn_encryption)
    Button btnEncryption;
    @Bind(R.id.button_circle_imageview)
    Button buttonCircleImageview;
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //mifalater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        final GridView gridViewF = (GridView) findViewById(R.id.gview_first);
        GridView gridViewS = (GridView) findViewById(R.id.gview_second);


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
        buttonMain.setOnClickListener(this);
        buttonCodeScanner.setOnClickListener(this);
        buttonAudioPlay.setOnClickListener(this);
        buttonDialogPlay.setOnClickListener(this);
        buttonSpinner.setOnClickListener(this);
        buttonVideoView.setOnClickListener(this);
        buttonPulltorefresh.setOnClickListener(this);
        buttonFragmentTest.setOnClickListener(this);
        buttonPicasso.setOnClickListener(this);
        buttonRecyclerview.setOnClickListener(this);
        buttonSharesdk.setOnClickListener(this);
        buttonWebview.setOnClickListener(this);
        buttonCardview.setOnClickListener(this);
        buttonPictureSave.setOnClickListener(this);
        buttonAnimationActivity.setOnClickListener(this);
        buttonOnconfigurationChanged.setOnClickListener(this);
        buttonCammertailor.setOnClickListener(this);
        buttonEdit.setOnClickListener(this);
        buttonEditShopNum.setOnClickListener(this);
        btnDeviceMessage.setOnClickListener(this);
        btnEncryption.setOnClickListener(this);
        buttonCircleImageview.setOnClickListener(this);
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

            //使用AES加密测试
            case R.id.btn_encryption:
                Intent encryptionIntent = new Intent(MainActivity.this, EncryptionActivity.class);
                startActivity(encryptionIntent);
                break;

            //圆角图片加载测试
            case R.id.button_circle_imageview:
                Intent circleImageViewIntent = new Intent(MainActivity.this, CircleImageViewActivity.class);
                startActivity(circleImageViewIntent);
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
