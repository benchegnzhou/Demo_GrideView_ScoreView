package com.example.lenovo.demo_grideview_scoreview.UiActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ZoomButtonsController;

import com.example.lenovo.demo_grideview_scoreview.R;
import com.example.lenovo.demo_grideview_scoreview.Utils.LogUtil;

import butterknife.Bind;
import butterknife.ButterKnife;

public class AdvanceWebViewActivity extends AppCompatActivity implements View.OnClickListener {


    @Bind(R.id.webview)
    WebView webview;
    @Bind(R.id.btn_textchange)
    Button btnTextchange;
    private WebSettings settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advance_web_view);
        ButterKnife.bind(this);
        btnTextchange.setOnClickListener(this);

        initWebView();

    }

    /**
     * webview 的初始化
     */
    private void initWebView() {


        //配置webview
        settings = webview.getSettings();
        settings.setBuiltInZoomControls(true);  //支持缩放的滚动条
        settings.setUseWideViewPort(true);  //支持缩放的滚动条

//        settings.setDefaultFontSize(15);


        //隐藏放大缩小的控件
//        if(系统版本>=11){
//            settings.setDisplayZoomControls(false);
//        }else {
//            ZoomButtonsController zoomButtonsController = new ZoomButtonsController(webview);
//            zoomButtonsController.getZoomControls().setVisibility(View.GONE);
//        }



        settings.setJavaScriptEnabled(true);  //支持javaScript
        //网页加载的监听
        webview.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                //这里面处理网页加载完成后的操作
                super.onPageFinished(view, url);
                LogUtil.e("网页加载成功");
            }
            //监视url跳转，可以获取url
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
//                return super.shouldOverrideUrlLoading(view, request);
                return true;
            }

        });
        webview.loadUrl("http://www.baidu.com");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_textchange:
                showTextSize();
                break;
        }
    }


    private int currentIndex = 2;

    /**
     * 字体改变
     */
    private void showTextSize() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        String[] textSize = new String[]{"超大号字体", "大号字体", "正常字体", "小号字体", "超小号字体"};
        builder.setSingleChoiceItems(textSize, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                currentIndex = which;
            }
        });
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                changeTextSize();
            }


        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.show();

    }

    /**
     * webview字体改变
     */
    private void changeTextSize() {
        switch (currentIndex) {
            case 0:
                settings.setTextSize(WebSettings.TextSize.LARGEST);
                break;
            case 1:
                settings.setTextSize(WebSettings.TextSize.LARGER);
                break;
            case 2:
                settings.setTextSize(WebSettings.TextSize.NORMAL);
                break;
            case 3:
                settings.setTextSize(WebSettings.TextSize.SMALLER);
                break;
            case 4:
                settings.setTextSize(WebSettings.TextSize.SMALLEST);
                break;
        }

    }


}
