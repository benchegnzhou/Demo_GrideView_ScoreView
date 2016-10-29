package com.example.lenovo.demo_grideview_scoreview.UiActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.example.lenovo.demo_grideview_scoreview.R;
import com.example.lenovo.demo_grideview_scoreview.adapter.ImageBucketAdapter;
import com.example.lenovo.demo_grideview_scoreview.been.ImageBucket;
import com.example.lenovo.demo_grideview_scoreview.helper.AlbumHelper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MultiplePictureActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

    public static final String EXTRA_IMAGE_LIST = "imagelist";
    public static final String EXTRA_IMAGE_NAME = "imageName";
    private LinearLayout llLeft;
    private ImageView ivLeft;
    private TextView tvTitle;
    private AlbumHelper helper;
    private List<ImageBucket> dataList;
    private GridView gridView;
    private Intent intent;
    private String category;
    private int hasCount;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multiple_picture);
        Intent intent = getIntent();
        category = intent.getStringExtra("category");
        hasCount = intent.getIntExtra("hasCount", 0);
        initView();
        initData();
        initListener();
    }

    private void initView() {
        llLeft = (LinearLayout) findViewById(R.id.ll_title_bar_left);
        ivLeft = (ImageView) findViewById(R.id.iv_title_bar_left);
        tvTitle = (TextView) findViewById(R.id.tv_title_bar_title);
        gridView = (GridView) findViewById(R.id.multiple_picture_gridview);
    }

    private void initData() {
        ivLeft.setBackgroundResource(R.drawable.home_back);
        tvTitle.setText("相册");
        helper = AlbumHelper.getHelper();
        helper.init(getApplicationContext());
        dataList = helper.getImagesBucketList(false);
        gridView.setAdapter(new ImageBucketAdapter(MultiplePictureActivity.this, dataList));
    }

    private void initListener() {
        llLeft.setOnClickListener(this);
        gridView.setOnItemClickListener(this);
    }

    @Override
    public void onClick(View view) {
        finish();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        intent = new Intent(MultiplePictureActivity.this, MultiplePictureImageActivity.class);
        intent.putExtra(MultiplePictureActivity.EXTRA_IMAGE_LIST, (Serializable) dataList.get(i).imageList);
        intent.putExtra(MultiplePictureActivity.EXTRA_IMAGE_NAME, (Serializable) dataList.get(i).bucketName);
        intent.putExtra("hasCount", hasCount);
        startActivityForResult(intent, 99);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case 99:
                    ArrayList<String> imageUrls = (ArrayList<String>) data.getSerializableExtra("imageUrls");
                    Intent intent = new Intent();
                    intent.putExtra("imageUrls", imageUrls);
                    setResult(RESULT_OK, intent);
                    finish();
                    break;
            }
        }
    }
}
