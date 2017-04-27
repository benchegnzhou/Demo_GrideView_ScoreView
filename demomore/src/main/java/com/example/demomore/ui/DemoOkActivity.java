package com.example.demomore.ui;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.example.demomore.R;
import com.example.demomore.application.MApplication;
import com.example.demomore.utils.LogUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * okhttp 的使用参考链接 http://www.open-open.com/lib/view/open1484554445349.html
 */
public class DemoOkActivity extends AppCompatActivity implements View.OnClickListener {

    @Bind(R.id.btn_Synchronization_get)
    Button btnSynchronizationGet;
    @Bind(R.id.btn_asynchronization_get)
    Button btnAsynchronizationGet;
    @Bind(R.id.btn_post_keyvalue)
    Button btnPostKeyvalue;
    @Bind(R.id.btn_post_uploadfiles)
    Button btnPostUploadfiles;
    @Bind(R.id.btn_downfiles)
    Button btnDownfiles;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_ok);
        ButterKnife.bind(this);
        initListener();
        initData();
    }

    private void initListener() {
        btnSynchronizationGet.setOnClickListener(this);
        btnAsynchronizationGet.setOnClickListener(this);
        btnPostKeyvalue.setOnClickListener(this);
        btnPostUploadfiles.setOnClickListener(this);
        btnDownfiles.setOnClickListener(this);
    }

    private void initData() {
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_Synchronization_get:
                getSynchronization();
                break;
            case R.id.btn_asynchronization_get:
                getAsynchronization();
                break;
            case R.id.btn_post_keyvalue:
                postKeyValue();
                break;
            case R.id.btn_post_uploadfiles:
                postUpLoadFile();
                break;
            case R.id.btn_downfiles:
                downFile();
                break;
        }
    }

    /**
     * Post异步上传文件
     * 第一步与之前都相同，但是从第二步开始就用了一定的差异了。在step2 中我们需要通过 MediaType.parse("text/plain; charset=utf-8") 为上传文件设置一定类型（MIME）在这里我们上传的纯文本文件所以选择 "text/plain 类型，而编码格式为 utf-8 。下面为大家列出常见的文件类型，方便使用。
     参数
     说明
     text/html
     HTML格式
     text/plain
     纯文本格式
     text/xml
     XML格式
     image/gif
     gif图片格式
     image/jpeg
     jpg图片格式
     image/png
     png图片格式
     application/xhtml+xml
     XHTML格式
     application/xml
     XML数据格式
     application/atom+xml
     Atom XML聚合格式
     application/json
     JSON数据格式
     application/pdf
     pdf格式
     application/msword
     Word文档格式
     application/octet-stream
     二进制流数据
     */
    private void postUpLoadFile() {

        // step 1: 创建 OkHttpClient 对象
//        OkHttpClient okHttpClient = new OkHttpClient();

        //step 2:创建 RequestBody 以及所需的参数
        //2.1 获取文件
        File file = new File(Environment.getExternalStorageDirectory() + "test.txt");
        //2.2 创建 MediaType 设置上传文件类型
        MediaType MEDIATYPE = MediaType.parse("text/plain; charset=utf-8");
        //2.3 获取请求体
        RequestBody requestBody = RequestBody.create(MEDIATYPE, file);

        //step 3：创建请求
        Request request = new Request.Builder().url("http://www.baidu.com")
                .post(requestBody)
                .build();

        //step 4 建立联系
        MApplication.getSOkHttpClient().newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                // TODO: 17-1-4  请求失败
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                // TODO: 17-1-4 请求成功
            }
        });
    }

    /**
     * 使用post的方式提交键值对
     */
    private void postKeyValue() {

        //step 1: 同样的需要创建一个OkHttpClick对象
//        OkHttpClient okHttpClient = new OkHttpClient();

        //step 2: 创建  FormBody.Builder
        FormBody formBody = new FormBody.Builder()
                .add("name", "dsd")
                .build();

        //step 3: 创建请求
        Request request = new Request.Builder().url("http://www.baidu.com")
                .post(formBody)
                .build();

        //step 4： 建立联系 创建Call对象
        MApplication.getSOkHttpClient().newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                // TODO: 17-1-4  请求失败
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                // TODO: 17-1-4 请求成功
            }
        });
    }

    /**
     * 文件下载
     */
    private void downFile() {
        //step 1: 不变的第一步创建 OkHttpClick
//        OkHttpClient okHttpClient = new OkHttpClient();

        //step 2: 创建Requset
        Request request = new Request.Builder()
                .url("http://www.ssyer.com/uploads/org_2017010593503_775.jpg")
                .build();

        //step 3:建立联系，创建Call
        MApplication.getSOkHttpClient().newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }

            @Override
            public void onResponse(Call call, Response response) {
                InputStream inputStream = response.body().byteStream();
                FileOutputStream fileOutputStream = null;
                try {
                    File file = new File(Environment.getExternalStorageDirectory() + "大狮子.jpg");
                    fileOutputStream = new FileOutputStream(file);
                    byte[] buffer = new byte[2048];
                    int len = 0;
                    while ((len = inputStream.read(buffer)) != -1) {
                        fileOutputStream.write(buffer, 0, len);
                    }
                    fileOutputStream.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                LogUtil.e("downloadAsynFile", "文件下载成功");
            }
        });
    }

    /**
     *
     */
    private void getAsynchronization() {



    }


    /**
     *  在Http请求中最常见的就是get方法了，在大多数的使用场景中，
     *  我们使用的都是异步的Get请求，下面我们就是用OkHttp的异步Get去请求一下百度的首页。

     * @return
     */
    public void getSynchronization() {
       // step 1: 创建 OkHttpClient 对象
//        OkHttpClient okHttpClient = new OkHttpClient();

        // step 2： 创建一个请求，不指定请求方法时默认是GET。
        Request.Builder requestBuilder = new Request.Builder().url("http://www.baidu.com");
        //可以省略，默认是GET请求
        requestBuilder.method("GET",null);

        // step 3：创建 Call 对象
        Call call = MApplication.getSOkHttpClient().newCall(requestBuilder.build());

        //step 4: 开始异步请求
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                // TODO: 17-1-4  请求失败
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                // TODO: 17-1-4 请求成功
                //获得返回体
                ResponseBody body = response.body();
            }
        });


    }
}
