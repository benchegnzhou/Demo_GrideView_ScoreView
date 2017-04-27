package com.example.demomore.application.network.net;

import android.text.TextUtils;

import com.example.demomore.application.MApplication;
import com.example.demomore.eventbusevent.eventbusanyevent.ZBCAnyEventType;
import com.example.demomore.utils.LogUtil;


import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static java.lang.String.valueOf;

/**
 * Created by benchengzhou on 2017/3/25 22:40 .
 * 作者邮箱： mappstore@163.com
 * 功能描述： ok执行post请求
 * 类    名： AsyncPostUtils
 * 备    注：
 */
public class AsyncPostUtils {
    public static final MediaType MediaType_JSON = MediaType.parse("application/json; charset=utf-8");
    public static final MediaType MediaType_MARK_DOWN = MediaType.parse("text/x-markdown; charset=utf-8");
    public static final MediaType MediaType_OCTET_STREAM = MediaType.parse("application/octet-stream; charset=utf-8");
    public static final MediaType MediaType_JPEG = MediaType.parse("image/jpeg");
    public static final MediaType MediaType_PNG = MediaType.parse("image/png");
    public static final MediaType MediaType_GIF = MediaType.parse("image/gif");


    /**
     * 使用键值对的形式执行请求
     *
     * @param urlPath
     * @param params
     * @param ZBCAnyEventType
     */
    public static void doAsyncPostKeyValue(String urlPath, Map<String, String> params, final ZBCAnyEventType ZBCAnyEventType) {

        //step 1: 同样的需要创建一个OkHttpClick对象
        //OkHttpClient okHttpClient = new OkHttpClient();

        //step 2: 创建  FormBody.Builder
        FormBody.Builder builder = new FormBody.Builder();

        Iterator iter = params.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            String key = (String) entry.getKey();
            String val = (String) entry.getValue();
            builder.add(key, val);

        }
        FormBody formBody = builder.build();


        //step 3: 创建请求
        Request request = new Request.Builder().url(urlPath)
                .post(formBody)
                .build();

        //step 4： 建立联系 创建Call对象

        MApplication.getSOkHttpClient().newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                // TODO: 17-1-4  请求失败
                LogUtil.e(e.toString());
                ZBCAnyEventType.setFailCode();
                ZBCAnyEventType.setResult("");
                EventBus.getDefault().post(ZBCAnyEventType);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                // TODO: 17-1-4 请求成功
                String StringResult = response.body().string();

                ZBCAnyEventType.setSuccessCode();
                ZBCAnyEventType.setResult(StringResult);
                EventBus.getDefault().post(ZBCAnyEventType);

            }
        });
    }

    /**
     * 这种方式目前仅支持一级层次结构的请求
     *
     * @param urlPath
     * @param params
     * @param ZBCAnyEventType
     */
    public static void doAsyncPostJson(String urlPath, Map<String, String> params, final ZBCAnyEventType ZBCAnyEventType) {
        // 1.创建OkHttpClient
        if (TextUtils.isEmpty(urlPath) || params == null || params.size() == 0) {
            LogUtil.e("请求地址获取请求参数为空");
            return;
        }
        // 2.创建请求参数，注意，此处有多种方式
        FormBody.Builder builder = new FormBody.Builder();
        Iterator iter = params.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            String key = (String) entry.getKey();
            String val = (String) entry.getValue();
            builder.add(key, val);
        }
        FormBody requestBody = builder.build();//执行构建
// 3.创建请求request
        Request request = new Request.Builder()
                .url(urlPath)
                .post(requestBody)
                .build();
        // 4.发起请求，此处使用的是异步请求，按需要选择同步或异步
        MApplication.sOkHttpClient.newCall(request)
                .enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        // TODO: 17-1-4  请求失败
                        ZBCAnyEventType.setFailCode();
                        ZBCAnyEventType.setResult("");
                        EventBus.getDefault().post(ZBCAnyEventType);
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        // TODO: 17-1-4 请求成功
                        String StringResult = response.body().string();

                        ZBCAnyEventType.setSuccessCode();
                        ZBCAnyEventType.setResult(StringResult);
                        EventBus.getDefault().post(ZBCAnyEventType);
                    }
                });


    }


    /**
     * 这种方式可以将一个json格式的字符串提交到服务端
     *
     * @param urlPath
     * @param json
     * @param ZBCAnyEventType 参考链接  http://www.open-open.com/lib/view/open1484554445349.html
     *                        第一步与之前都相同，但是从第二步开始就用了一定的差异了。在step2 中我们需要通过 MediaType.parse("text/plain; charset=utf-8") 为上传文件设置一定类型（MIME）在这里我们上传的纯文本文件所以选择 "text/plain 类型，而编码格式为 utf-8 。下面为大家列出常见的文件类型，方便使用。
     *                        参数                          说明
     *                        text/html                   HTML格式
     *                        text/plain                  纯文本格式
     *                        text/xml                    XML格式
     *                        image/gif                   gif图片格式
     *                        image/jpeg                  jpg图片格式
     *                        image/png                   png图片格式
     *                        application/xhtml+xml       XHTML格式
     *                        application/xml             XML数据格式
     *                        application/atom+xml        Atom XML聚合格式
     *                        application/json            JSON数据格式
     *                        application/pdf             pdf格式
     *                        application/msword          Word文档格式
     *                        application/octet-stream    二进制流数据
     */
    public static void doAsyncPostJson(String urlPath, String json, final ZBCAnyEventType ZBCAnyEventType) {


        // 1.创建OkHttpClient
        if (TextUtils.isEmpty(urlPath) || TextUtils.isEmpty(json)) {
            return;
        }
        // 2.创建请求参数，注意，此处有多种方式
        RequestBody requestBody = RequestBody.create(MediaType_JSON, json);
// 3.创建请求request
        Request request = new Request.Builder()
                .url(urlPath)
                .post(requestBody)
                .build();
        // 4.发起请求，此处使用的是异步请求，按需要选择同步或异步
        MApplication.sOkHttpClient.newCall(request)
                .enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        // TODO: 17-1-4  请求失败
                        ZBCAnyEventType.setFailCode();
                        ZBCAnyEventType.setResult("");
                        EventBus.getDefault().post(ZBCAnyEventType);
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        // TODO: 17-1-4 请求成功
                        String StringResult = response.body().string();

                        ZBCAnyEventType.setSuccessCode();
                        ZBCAnyEventType.setResult(StringResult);
                        EventBus.getDefault().post(ZBCAnyEventType);
                    }
                });


    }

    /**
     * 使用 post异步提交单个文件
     *
     * @param urlPath
     * @param file
     * @param ZBCAnyEventType 参考链接  http://www.open-open.com/lib/view/open1484554445349.html
     *                        参数                          说明
     *                        text/html                   HTML格式
     *                        text/plain                  纯文本格式
     *                        text/xml                    XML格式
     *                        image/gif                   gif图片格式
     *                        image/jpeg                  jpg图片格式
     *                        image/png                   png图片格式
     *                        application/xhtml+xml       XHTML格式
     *                        application/xml             XML数据格式
     *                        application/atom+xml        Atom XML聚合格式
     *                        application/json            JSON数据格式
     *                        application/pdf             pdf格式
     *                        application/msword          Word文档格式
     *                        application/octet-stream    二进制流数据
     */
    public static void doAsyncPostFile(String urlPath, File file, MediaType mediaType, final ZBCAnyEventType ZBCAnyEventType) {

        // 2.创建请求参数，设置对应的参数类型即可
        RequestBody requestBody = RequestBody.create(mediaType, file);
        // 3.创建请求request
        Request request = new Request.Builder()
                .url(urlPath)
                .post(requestBody)
                .build();
        // 4.发起请求
        MApplication.getSOkHttpClient().newCall(request)
                .enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        // TODO: 17-1-4  请求失败
                        ZBCAnyEventType.setFailCode();
                        ZBCAnyEventType.setResult("");
                        EventBus.getDefault().post(ZBCAnyEventType);
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        // TODO: 17-1-4 请求成功
                        String StringResult = response.body().string();

                        ZBCAnyEventType.setSuccessCode();
                        ZBCAnyEventType.setResult(StringResult);
                        EventBus.getDefault().post(ZBCAnyEventType);
                    }
                });
    }


    /**
     * 网络参考链接：  http://blog.csdn.net/lv_fq/article/details/52313622?ref=myread
     *
     * @param url
     * @param map
     * @param file
     */
    protected void doAsyncPostMultipartBody(final String url, final Map<String, Object> map, Map<String, File> fileMap, File file,MediaType fileMediaType) {

        // form 表单形式上传
        MultipartBody.Builder requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM);
        if (fileMap != null) {
            // MediaType.parse() 里面是上传的文件类型。
            RequestBody body = RequestBody.create(MediaType.parse("image/png"), file);
            String filename = file.getName();
            // 参数分别为， 请求key ，文件名称 ， RequestBody
            requestBody.addFormDataPart("headImage", file.getName(), body);

        }
        if (map != null) {
            // map 里面是请求中所需要的 key 和 value
            for (Map.Entry entry : map.entrySet()) {
                requestBody.addFormDataPart(valueOf(entry.getKey()), valueOf(entry.getValue()));
            }
        }

        Request request = new Request.Builder().url(url).post(requestBody.build()).tag("asdasd").build();
        // readTimeout("请求超时时间" , 时间单位);
        MApplication.getSOkHttpClient().newBuilder().readTimeout(5000, TimeUnit.MILLISECONDS).build().newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                LogUtil.e("lfq", "onFailure");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    String str = response.body().string();
                    LogUtil.e("zbc", response.message() + " , body " + str);

                } else {
                    LogUtil.e("zbc", response.message() + " error : body " + response.body().string());
                }
            }
        });
    }


}
