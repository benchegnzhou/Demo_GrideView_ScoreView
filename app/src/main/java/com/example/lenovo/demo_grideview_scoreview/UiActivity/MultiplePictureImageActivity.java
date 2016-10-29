package com.example.lenovo.demo_grideview_scoreview.UiActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.example.lenovo.demo_grideview_scoreview.R;
import com.example.lenovo.demo_grideview_scoreview.adapter.MultiplePictureImageAdapter;
import com.example.lenovo.demo_grideview_scoreview.been.ImageItem;
import com.example.lenovo.demo_grideview_scoreview.helper.AlbumHelper;


import java.util.ArrayList;
import java.util.List;

public class MultiplePictureImageActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String EXTRA_IMAGE_LIST = "imagelist";
    public static final String EXTRA_IMAGE_NAME = "imageName";
    private LinearLayout llLeft;
    private ImageView ivLeft;
    private TextView tvTitle;
    private GridView gridView;
    private Button button;
    private int id;
    private AlbumHelper helper;
    private List<ImageItem> dataList;
    private MultiplePictureImageAdapter adapter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multiple_picture_image);
        initView();
        initData();
        initListener();
    }

    private void initView() {
        llLeft = (LinearLayout) findViewById(R.id.ll_title_bar_left);
        ivLeft = (ImageView) findViewById(R.id.iv_title_bar_left);
        tvTitle = (TextView) findViewById(R.id.tv_title_bar_title);
        gridView = (GridView) findViewById(R.id.multiple_picture_image_gridview);
        button = (Button) findViewById(R.id.multiple_picture_image_button);
    }

    private void initListener() {
        llLeft.setOnClickListener(this);
        button.setOnClickListener(this);
    }

    private void initData() {
        helper = AlbumHelper.getHelper();
        helper.init(getApplicationContext());
        Intent intent = getIntent();
        dataList = (List<ImageItem>) intent.getSerializableExtra(EXTRA_IMAGE_LIST);
        int hasCount = intent.getIntExtra("hasCount", 0);
        ivLeft.setBackgroundResource(R.drawable.home_back);
        String bucketName = getIntent().getStringExtra(EXTRA_IMAGE_NAME);
        if (!TextUtils.isEmpty(bucketName)) {
            tvTitle.setText(bucketName);
        } else {
            tvTitle.setText("选择图片");
        }
        adapter = new MultiplePictureImageAdapter(this, dataList, hasCount);
        gridView.setAdapter(adapter);
    }

    @Override
    public void onClick(View view) {
        id = view.getId();
        Intent intent = null;
        switch (id) {
            case R.id.ll_title_bar_left:
                finish();
                break;

            case R.id.multiple_picture_image_button:
                List<String> imageUrls = adapter.getImageUrls();
                intent = new Intent();
                intent.putExtra("imageUrls", (ArrayList<String>) imageUrls);
                setResult(RESULT_OK, intent);
                finish();
                break;
        }
    }
}
