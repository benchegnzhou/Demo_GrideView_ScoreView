package com.example.lenovo.demo_grideview_scoreview.UiActivity;

import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.example.lenovo.demo_grideview_scoreview.R;
import com.example.lenovo.demo_grideview_scoreview.Utils.LogUtil;
import com.example.lenovo.demo_grideview_scoreview.Utils.ToastUtils;

import java.util.HashMap;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;
import cn.sharesdk.onekeyshare.ShareContentCustomizeCallback;

public class ShareSDkActivity extends AppCompatActivity {
private String  title="我分享我快乐！";
private String  url="www.baidu.com";
private String  imageUrl="http://i.imgur.com/foZxPxu.png";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_sdk);
    }

    public void share(View v){
        showShare();
    }

    private void showShare() {
        ShareSDK.initSDK(this);
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();

// 分享时Notification的图标和文字  2.5.9以后的版本不调用此方法
        //oks.setNotification(R.drawable.ic_launcher, getString(R.string.app_name));


        // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
//        oks.setTitle("我就是我，不一样的小苹果！");

        // titleUrl是标题的网络链接，仅在人人网和QQ空间使用
//        oks.setTitleUrl("http://sharesdk.cn");

        // text是分享文本，所有平台都需要这个字段
//        oks.setText("我是分享文本");

        //分享网络图片，新浪微博分享网络图片需要通过审核后申请高级写入接口，否则请注释掉测试新浪微博
//        oks.setImageUrl("http://f1.sharesdk.cn/imgs/2014/02/26/owWpLZo_638x960.jpg");

        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
//        oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片


        // url仅在微信（包括好友和朋友圈）中使用
//        oks.setUrl("http://sharesdk.cn");


        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
//        oks.setComment("我是测试评论文本");


        // site是分享此内容的网站名称，仅在QQ空间使用
//        oks.setSite("亿人帮");


        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
//        oks.setSiteUrl("http://sharesdk.cn");

// 启动分享GUI
//        oks.show(this);

//        Platform qzone = ShareSDK.getPlatform (QZone.NAME);
//        qzone. setPlatformActionListener (paListener); // 设置分享事件回调
        oks.setTitle(title);
        oks.setImageUrl(imageUrl);
        oks.setShareContentCustomizeCallback(new ShareContentCustomizeCallback() {

            @Override
            public void onShare(Platform platform, Platform.ShareParams paramsToShare) {
                int targetSnstype = 0;
                String platformName = platform.getName();
                /**
                 * 参考集成文档对不同的
                 */
                switch (platformName) {
                    case "SinaWeibo":
                        targetSnstype = 2;

                        break;
                    case "Wechat":
                        if (platform.isClientValid()) {
                            targetSnstype = 1;
                            paramsToShare.setTitle(title);
                            paramsToShare.setTitleUrl(url);
                            paramsToShare.setText("我在亿人帮平台的个人主页，快来关注我吧");
                            //分享连接
                            paramsToShare.setShareType(Platform.SHARE_WEBPAGE);
                            paramsToShare.setUrl(url);
                            //分享图片
                            if (!TextUtils.isEmpty(imageUrl)) {
                                paramsToShare.setImageUrl(imageUrl);
                            }
                        } else {
                            Toast.makeText(ShareSDkActivity.this, "目前您的微信版本过低或未安装微信，需要安装微信才能使用", Toast.LENGTH_SHORT).show();
                        }

                        break;
                    case "WechatMoments":
                        if (platform.isClientValid()) {
                            //SHARE_WEBPAGE,要求要title、text、url、thumbPath
                            paramsToShare.setTitle(title);
                            paramsToShare.setTitleUrl(url);
                            paramsToShare.setText("我在亿人帮平台的个人主页，快来关注我吧");
                            //分享连接
                            paramsToShare.setShareType(Platform.SHARE_WEBPAGE);
                            paramsToShare.setUrl(url);
                            //分享图片
                            if (!TextUtils.isEmpty(imageUrl)) {
                                paramsToShare.setImageUrl(imageUrl);
                            }
                        } else {
                            Toast.makeText(ShareSDkActivity.this, "目前您的微信版本过低或未安装微信，需要安装微信才能使用", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case "QQ":
                        paramsToShare.setTitle(title);
                        paramsToShare.setText("我在亿人帮平台的个人主页，快来关注我吧");
                        //分享连接
                        paramsToShare.setShareType(Platform.SHARE_WEBPAGE);
                        paramsToShare.setTitleUrl(url);
                        //分享图片
                        if (!TextUtils.isEmpty(imageUrl)) {
                            paramsToShare.setImageUrl(imageUrl);
                        }
                        break;
                    case "QZone":
                        paramsToShare.setTitle(title);
                        paramsToShare.setTitleUrl(url);
                        paramsToShare.setText("我在亿人帮平台的个人主页，快来关注我吧");
                        //分享连接
                        paramsToShare.setShareType(Platform.SHARE_WEBPAGE);
//                        paramsToShare.setUrl(url);
                        paramsToShare.setSiteUrl(url);
                        //分享图片
                        if (!TextUtils.isEmpty(imageUrl)) {
                            paramsToShare.setImageUrl(imageUrl);
                        }
                        break;
                }
                platform.setPlatformActionListener(new PlatformActionListener() {
                    @Override
                    public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
                        ToastUtils.showToast(""+platform+"");

                    }

                    @Override
                    public void onError(Platform platform, int i, Throwable throwable) {
                        LogUtil.e("-----" + platform + "----" + i + "----" + throwable);
                    }

                    @Override
                    public void onCancel(Platform platform, int i) {
                        LogUtil.e("" + platform + i);
                    }
                });
            }
        });
        oks.show(ShareSDkActivity.this);








    }
}
