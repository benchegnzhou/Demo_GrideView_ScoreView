package com.example.lenovo.demo_grideview_scoreview.Utils;

import android.annotation.SuppressLint;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.location.LocationManager;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.MediaStore.Images.Thumbnails;
import android.provider.Settings;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.ImageSpan;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;



import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Util {
    public static final String DATETIME = "yyyyMMddHHmmss";
    private static Pattern p;
    private static Matcher m;


    public static boolean StrIsNull(String str) {
        return str == null || str.trim().equals("");
    }

    public static String getVersion(Context mContext) {
        PackageManager packageManager = mContext.getPackageManager();
        PackageInfo packInfo = null;
        try {
            packInfo = packageManager.getPackageInfo(mContext.getPackageName(),
                    0);
        } catch (NameNotFoundException e) {
            e.printStackTrace();
            return null;
        }
        String version = packInfo.versionName;
        if (!StrIsNull(version))
            return version;
        else
            return "1.0";
    }

    public static Bitmap getBitmapFromView(View view) {
        view.destroyDrawingCache();
        view.measure(MeasureSpec.makeMeasureSpec(0,
                MeasureSpec.UNSPECIFIED), MeasureSpec
                .makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
        view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
        view.setDrawingCacheEnabled(true);
        Bitmap bitmap = view.getDrawingCache(true);
        return bitmap;
    }

    public static byte[] BitmaptoBytes(Bitmap bm) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.JPEG, 90, baos);
        return baos.toByteArray();
    }


    public static String getDate(String date) {
        long time1 = Long.valueOf(date);
        long time2 = Calendar.getInstance().getTimeInMillis() / 1000;
        long time = time2 - time1;
        int i = (int) (time / (60 * 60 * 24));
        Date vDate = new Date(time1 * 1000);
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm",
                Locale.getDefault()); // 打印年份
        String t = timeFormat.format(vDate);
        if (i == 0) {
            return "今天 " + t;
        }
        if (i == 1) {
            return "昨天 " + t;
        }
        if (i == 2) {
            return "前天 " + t;
        }
        if (i > 2) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault()); // 打印年份
            return dateFormat.format(vDate);
        }
        return "-";
    }

    /**
     * 获取指定时间到当前时间的时间差，如果 时间差大于24小时 显示天， 小于24小时，显示小时，小于60分钟，显示分钟
     *
     * @param : yyyy-MM-dd HH:mm
     * @return
     */
    public static String getDateDifference(String date) {
        try {
            Calendar c = Calendar.getInstance();
            c.setTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(date
                    + ":" + "00"));
            long time = c.getTimeInMillis();

            long time2 = System.currentTimeMillis();
            long day = (time - time2) / (1000 * 60 * 60 * 24);
            long hours = (time - time2) / (1000 * 60 * 60);
            long minutes = (time - time2) / (1000 * 60);

            if (day > 0) {
                return "还剩 " + day + " 天";
            } else if (hours > 0) {
                return "还剩 " + hours + "小时";
            } else if (minutes > 0) {
                return "还剩 " + minutes + "分钟";
            }
            return "您输入的时间有误";

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * gps是否开启
     *
     * @param pContext
     * @return
     */
    public static boolean isGpsEnable(Context pContext) {
        LocationManager locationManager = ((LocationManager) pContext.getSystemService(Context.LOCATION_SERVICE));
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
    }

    /**
     * 开启gps
     *
     * @param pContext
     */
    public static void OpenGpsSetting(Context pContext) {
        Intent intent = new Intent();
        intent.setAction(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        try {
            pContext.startActivity(intent);
        } catch (ActivityNotFoundException ex) {
            intent.setAction(Settings.ACTION_SETTINGS);
            try {
                pContext.startActivity(intent);
            } catch (Exception e) {
            }
        }
    }

    /**
     * 写入文件
     *
     * @param pContext
     * @param fileName
     * @param message
     */
    public static void writeFileSdcard(Context pContext, String fileName,
                                       String message) {
        try {
            File vFile = new File(getSDCardPath(pContext));
            if (!vFile.exists()) {
                vFile.mkdir();
            }
            FileOutputStream fout = new FileOutputStream(getSDCardPath(pContext) + "/" + fileName);
            byte[] bytes = message.getBytes();
            fout.write(bytes);
            fout.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 读取文件
     *
     * @param pContext
     * @param fileName
     * @return
     */
    public static String readFileSdcard(Context pContext, String fileName) {
        String res = "";
        try {
            FileInputStream fin = new FileInputStream(getSDCardPath(pContext) + "/" + fileName);
            int length = fin.available();
            byte[] buffer = new byte[length];
            fin.read(buffer);
//            res = EncodingUtils.getString(buffer, "UTF-8");UTF-8
            res=new String(buffer ,"UTF-8");    //将字节数组转换成字符串并做utf-8转码
            fin.close();
        } catch (Exception e) {

            e.printStackTrace();
        }
        return res;

    }

    public static String Getdistance(String pLat, String pLng, String pMap) {
        if (StrIsNull(pMap) || StrIsNull(pLat) || StrIsNull(pLng)) {
            return "未知";
        }

        String[] distance = pMap.split(",");
        double lng = Double.valueOf(distance[0]);
        double lat = Double.valueOf(distance[1]);
        double dis = GetDistance(lat, lng, Double.valueOf(pLat),
                Double.valueOf(pLng));
        return String.format("%.2f", dis) + "km";
    }

    private static double GetDistance(double lat1, double lon1, double lat2,
                                      double lon2) {
        double R = 6371;
        double distance = 0.0;
        double dLat = (lat2 - lat1) * Math.PI / 180;
        double dLon = (lon2 - lon1) * Math.PI / 180;
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
                + Math.cos(lat1 * Math.PI / 180)
                * Math.cos(lat2 * Math.PI / 180) * Math.sin(dLon / 2)
                * Math.sin(dLon / 2);
        distance = (2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a))) * R;
        return distance;
    }


    public static Bitmap getBitmapByUrl(Context pContext, String uri) {

        URLConnection url;
        Bitmap bitmap = null;
        String newuri;
        String iconFile = downloadFile(pContext, uri);

        if (iconFile == null)
            return null;

        if (iconFile.length() > 0) {
            newuri = Uri.fromFile(new File(getSDCardPath(pContext) + "/" + iconFile)).toString();
        } else {
            newuri = uri;
        }
        try {
            url = new URL(newuri).openConnection();
            InputStream picStream = url.getInputStream();
            bitmap = BitmapFactory.decodeStream(picStream);
        } catch (MalformedURLException e) {
            // e.printStackTrace();
        } catch (IOException e) {
            // e.printStackTrace();
        }

        return bitmap;
    }

    public static String downloadFile(Context pContext, String url) {

        if (TextUtils.isEmpty(url))
            return null;

        String filename = url.substring(url.lastIndexOf("/") + 1);

        try {
            File downDir = new File(getSDCardPath(pContext));
            downDir.mkdirs();

            File tryFile = new File(downDir, filename);
            if (tryFile.exists()) {
                if (tryFile.length() != 0) {
                    return filename;
                } else {
                    tryFile.delete();
                }
            }

            URL myURL = new URL(url);
            URLConnection conn = myURL.openConnection();
            conn.connect();
            InputStream is = conn.getInputStream();
            int fileSize = conn.getContentLength();

            if (fileSize <= 0)
                throw new RuntimeException("");

            File outputFile = new File(downDir, filename);
            FileOutputStream fos = new FileOutputStream(outputFile);

            if (is == null)
                throw new RuntimeException("stream is null");

            byte buf[] = new byte[1024];

            do {
                int numread = is.read(buf);
                if (numread == -1) {
                    break;
                }
                fos.write(buf, 0, numread);

            } while (true);

        } catch (Exception ex) {
            // ex.printStackTrace();
            return "";

        }
        return filename;
    }

    /**
     * 将本地图片转化bimap
     *
     * @param c    上下文
     * @param file 图片绝对路径
     * @return bitmap
     */
    @SuppressLint({"NewApi", "InlinedApi"})
    public static Bitmap getxtsldraw(Context c, String file) {
        File f = new File(file);
        Bitmap drawable = null;
        if (f.length() / 1024 < 200) {
            drawable = getbitmap(file, 720 * 1280);
//            drawable = BitmapFactory.decodeFile(file);
        } else {
            Cursor cursor = c.getContentResolver().query(
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                    new String[]{MediaStore.Images.Media._ID},
                    MediaStore.Images.Media.DATA + " like ?",
                    new String[]{"%" + file}, null);
            if (cursor == null || cursor.getCount() == 0) {
                drawable = getbitmap(file, 720 * 1280);
            } else {
                if (cursor.moveToFirst()) {
                    BitmapFactory.Options options = new BitmapFactory.Options();
                    options.inPurgeable = true;
                    options.inInputShareable = true;
                    options.inPreferredConfig = Bitmap.Config.ARGB_8888;
                    String videoId = cursor.getString(cursor
                            .getColumnIndex(MediaStore.Images.Media._ID));
                    long videoIdLong = Long.parseLong(videoId);
                    Bitmap bitmap = Thumbnails.getThumbnail(
                            c.getContentResolver(), videoIdLong,
                            Thumbnails.MINI_KIND, options);
                    return bitmap;
                } else {
                    // drawable = BitmapFactory.decodeResource(c.getResources(),
                    // R.drawable.ic_doctor);
                }
            }
        }
        int degree = 0;
        ExifInterface exifInterface;
        try {
            exifInterface = new ExifInterface(file);

            int orientation = exifInterface.getAttributeInt(
                    ExifInterface.TAG_ORIENTATION,
                    ExifInterface.ORIENTATION_NORMAL);
            switch (orientation) {
                case ExifInterface.ORIENTATION_ROTATE_90:
                    degree = 90;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    degree = 180;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_270:
                    degree = 270;
                    break;
            }
            if (degree != 0 && drawable != null) {
                Matrix m = new Matrix();
                m.setRotate(degree, (float) drawable.getWidth() / 2,
                        (float) drawable.getHeight() / 2);
                drawable = Bitmap.createBitmap(drawable, 0, 0,
                        drawable.getWidth(), drawable.getHeight(), m, true);
            }
        } catch (OutOfMemoryError e) {
            // Toast.makeText(c, "牌照出错，请重新牌照", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return drawable;
    }

    public static Bitmap getbitmap(String imageFile, int length) {
        BitmapFactory.Options opts = new BitmapFactory.Options();
        opts.inPreferredConfig = Bitmap.Config.ARGB_8888;

        opts.inJustDecodeBounds = true;

        BitmapFactory.decodeFile(imageFile, opts);
        int ins = computeSampleSize(opts, -1, length);
        opts.inSampleSize = ins;
        opts.inPurgeable = true;
        opts.inInputShareable = true;
        opts.inJustDecodeBounds = false;
        try {
            Bitmap bmp = BitmapFactory.decodeFile(imageFile, opts);
            return bmp;
        } catch (OutOfMemoryError err) {
            err.printStackTrace();
        }
        return null;
    }

    /**
     * 二次采样，减小返回的bitmap内存占用量
     *
     * @param context
     * @param uri
     * @param size    宽高最大像素值
     * @return
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static Bitmap getThumbnail(Context context, Uri uri, int size) throws IOException {
        InputStream input = context.getContentResolver().openInputStream(uri);
        BitmapFactory.Options onlyBoundsOptions = new BitmapFactory.Options();
        onlyBoundsOptions.inJustDecodeBounds = true;
        onlyBoundsOptions.inDither = true;//optional
        onlyBoundsOptions.inPreferredConfig = Bitmap.Config.ARGB_8888;//optional
        BitmapFactory.decodeStream(input, null, onlyBoundsOptions);
        input.close();
        if ((onlyBoundsOptions.outWidth == -1) || (onlyBoundsOptions.outHeight == -1))
            return null;
        int originalSize = (onlyBoundsOptions.outHeight > onlyBoundsOptions.outWidth) ? onlyBoundsOptions.outHeight : onlyBoundsOptions.outWidth;
        double ratio = (originalSize > size) ? (originalSize / size) : 1.0;
        BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
        bitmapOptions.inSampleSize = getPowerOfTwoForSampleRatio(ratio);
        bitmapOptions.inDither = true;//optional
        bitmapOptions.inPreferredConfig = Bitmap.Config.ARGB_8888;//optional
        input = context.getContentResolver().openInputStream(uri);
        Bitmap bitmap = BitmapFactory.decodeStream(input, null, bitmapOptions);
        input.close();
        return bitmap;
    }

    private static int getPowerOfTwoForSampleRatio(double ratio) {
        int k = Integer.highestOneBit((int) Math.floor(ratio));
        if (k == 0) return 1;
        else return k;
    }

    public static Bitmap getbitmapWidth(String imageFile, int width) {
        BitmapFactory.Options opts = new BitmapFactory.Options();
        opts.inPreferredConfig = Bitmap.Config.ARGB_8888;

        opts.inJustDecodeBounds = true;

        BitmapFactory.decodeFile(imageFile, opts);
        int ins = computeSampleSize(opts, width, 1);
        opts.inSampleSize = ins;
        opts.inPurgeable = true;
        opts.inInputShareable = true;
        opts.inJustDecodeBounds = false;
        try {
            Bitmap bmp = BitmapFactory.decodeFile(imageFile, opts);
            return bmp;
        } catch (OutOfMemoryError err) {
            err.printStackTrace();
        }
        return null;
    }

    public static int computeSampleSize(BitmapFactory.Options options,
                                        int minSideLength, int maxNumOfPixels) {
        int initialSize = computeInitialSampleSize(options, minSideLength,
                maxNumOfPixels);

        int roundedSize;
        if (initialSize <= 8) {
            roundedSize = 1;
            while (roundedSize < initialSize) {
                roundedSize <<= 1;
            }
        } else {
            roundedSize = (initialSize + 7) / 8 * 8;
        }

        return roundedSize;
    }

    private static int computeInitialSampleSize(BitmapFactory.Options options,
                                                int minSideLength, int maxNumOfPixels) {
        double w = options.outWidth;
        double h = options.outHeight;

        int lowerBound = (maxNumOfPixels == -1) ? 1 : (int) Math.ceil(Math.sqrt(w * h / maxNumOfPixels));
        int upperBound = (minSideLength == -1) ? 128 : (int) Math.min(
                Math.floor(w / minSideLength), Math.floor(h / minSideLength));

        if (upperBound < lowerBound) {
            return lowerBound;
        }

        if ((maxNumOfPixels == -1) && (minSideLength == -1)) {
            return 1;
        } else if (minSideLength == -1) {
            return lowerBound;
        } else {
            return upperBound;
        }
    }

    /**
     * 将bitmap 存储到本地
     *
     * @param pContext 上下问
     * @param bm
     * @param filename
     * @return
     */
    public static String creatfile(Context pContext, Bitmap bm, String filename) {
        if (bm == null) {
            return "";
        }
        File f = new File(getSDCardPath(pContext) + "/" + filename + ".JPEG");
        try {
            FileOutputStream out = new FileOutputStream(f);
            if (bm.compress(Bitmap.CompressFormat.PNG, 100, out)) {
                out.flush();
                out.close();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return f.getAbsolutePath();
    }

    public static Bitmap getViewBitmap(View view) {
        int spec = MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED);
        view.measure(spec, spec);
        view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
        Bitmap b = Bitmap.createBitmap(view.getWidth(), view.getHeight(),
                Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(b);
        c.translate(-view.getScrollX(), -view.getScrollY());
        view.draw(c);
        view.setDrawingCacheEnabled(true);
        Bitmap cacheBmp = view.getDrawingCache();
        Bitmap viewBmp = cacheBmp.copy(Bitmap.Config.ARGB_8888, true);
        view.destroyDrawingCache();

        return viewBmp;
    }

    /**
     * 本地上传图片--本机
     *
     * @param pContext
     * @return
     */
    public static String getSDCardPath(Context pContext) {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/yirenbang/yirenbangImages";
            File PathDir = new File(path);
            if (!PathDir.exists()) {
                PathDir.mkdirs();
            }
            return path;
        } else {
            return pContext.getCacheDir().getAbsolutePath();
        }
    }

    /**
     * 保存下载的图片（针对机构的）
     *
     * @param pContext
     * @return
     */
    public static String getSDCardPathOrganization(Context pContext) {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/yirenbang/yirenbangOrganization";
            File PathDir = new File(path);
            if (!PathDir.exists()) {
                PathDir.mkdirs();
            }
            return path;
        } else {
            return pContext.getCacheDir().getAbsolutePath();
        }
    }

    /**
     * 删除单个文件
     * @param   filePath    被删除文件的文件名
     * @return 文件删除成功返回true，否则返回false
     */
    public static boolean deleteFile(String filePath) {
        File file = new File(filePath);
        if (file.isFile() && file.exists()) {
            return file.delete();
        }
        return false;
    }

    /**
     * 删除文件夹以及目录下的文件
     * @param   filePath 被删除目录的文件路径
     * @return  目录删除成功返回true，否则返回false
     */
    public static boolean deleteDirectory(String filePath) {
        boolean flag = false;
        //如果filePath不以文件分隔符结尾，自动添加文件分隔符
        if (!filePath.endsWith(File.separator)) {
            filePath = filePath + File.separator;
        }
        File dirFile = new File(filePath);
        if (!dirFile.exists() || !dirFile.isDirectory()) {
            return false;
        }
        flag = true;
        File[] files = dirFile.listFiles();
        //遍历删除文件夹下的所有文件(包括子目录)
        for (int i = 0; i < files.length; i++) {
            if (files[i].isFile()) {
                //删除子文件
                flag = deleteFile(files[i].getAbsolutePath());
                if (!flag) break;
            } else {
                //删除子目录
                flag = deleteDirectory(files[i].getAbsolutePath());
                if (!flag) break;
            }
        }
        if (!flag) return false;
        //删除当前空目录
        return dirFile.delete();
    }

    /**
     *  根据路径删除指定的目录或文件，无论存在与否
     *@param filePath  要删除的目录或文件
     *@return 删除成功返回 true，否则返回 false。
     */
    public static boolean DeleteFolder(String filePath) {
        File file = new File(filePath);
        if (!file.exists()) {
            return false;
        } else {
            if (file.isFile()) {
                // 为文件时调用删除文件方法
                return deleteFile(filePath);
            } else {
                // 为目录时调用删除目录方法
                return deleteDirectory(filePath);
            }
        }
    }

    /**
     * 保存图片下载，图文并茂的图片
     *
     * @param pContext
     * @return
     */
    public static String getSDCardImageText(Context pContext) {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/yirenbang/imageText/";
            File PathDir = new File(path);
            if (!PathDir.exists()) {
                PathDir.mkdirs();
            }
            return path;
        } else {
            return pContext.getCacheDir().getAbsolutePath();
        }
    }


    /**
     * 滑动查看图片的缓存地址
     *
     * @param pContext
     * @return
     */
    public static String getSDCardPathCaChe(Context pContext) {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/yirenbang/cechaDir";
            File PathDir = new File(path);
            if (!PathDir.exists()) {
                PathDir.mkdirs();
            }
            return path;
        } else {
            return pContext.getCacheDir().getAbsolutePath();
        }
    }

    /**
     * 本地下载保存路径--本机
     *
     * @param pContext
     * @return
     */
    public static String getDownloadSDCardPath(Context pContext) {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/yirenbang/yirenbangDownload";
            File PathDir = new File(path);
            if (!PathDir.exists()) {
                PathDir.mkdirs();
            }
            return path;
        } else {
            return pContext.getCacheDir().getAbsolutePath();
        }
    }

    /**
     * 判断是否为邮箱
     */
    public static boolean checkEmail(String email) {
        p = Pattern.compile("^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$");
        m = p.matcher(email);
        return m.matches();
//        return true;

    }

    /**
     * 判断是否为手机号码
     */
    public static boolean checkPhone(String phone) {
//        p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
//        p = Pattern.compile("^((1[0-9]))\\d{9}$");
//        m = p.matcher(phone);
//
//
//        return m.matches();
        return true;

    }

    /**
     * 判断是不是电话号码
     *
     * @param
     * @return
     */
    public static boolean checkTelePhone(String telePhone) {
//		p = Pattern.compile("0\\d{2,3}\\d{7,8}");
//		 m = p.matcher(telePhone);
//		return m.matches();
        return true;
    }

/*
    public static String checkVCode(String uri) {

        try {
            HttpClient client = new DefaultHttpClient();
            URL url = new URL(uri);
            URI uri2 = new URI(url.getProtocol(), url.getHost(), url.getPath(),
                    url.getQuery(), null);
            HttpGet get = new HttpGet(uri2);
            HttpParams params = get.getParams();
            HttpConnectionParams.setConnectionTimeout(params, 50000);
            HttpConnectionParams.setSoTimeout(params, 50000);

            HttpResponse response = client.execute(get);
            int stateCode = response.getStatusLine().getStatusCode();
            if (stateCode == 200) {
                HttpEntity entity = response.getEntity();
                return EntityUtils.toString(entity, "utf-8");
            }
        } catch (Exception e) {
        } finally {
            // if(client != null){
            // client.getConnectionManager().shutdown();
            // }
        }

        return null;
    }
*/

    /**
     * 截取数字
     *
     * @param
     * @return
     */
    public static String getNumbers(String str) {
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(str);
        while (matcher.find()) {
            return matcher.group(0);
        }
        return "";
    }

    /**
     * 截取非数字
     *
     * @param
     * @return
     */
    public static String splitNotNumber(String str) {
        Pattern pattern = Pattern.compile("\\D+");
        Matcher matcher = pattern.matcher(str);
        while (matcher.find()) {
            return matcher.group(0);
        }
        return "";
    }

    /**
     * 判断是否包含数字
     *
     * @param str
     * @return true 包含， false 不包含
     */
    public static boolean isIncludeDigit(String str) {
        boolean flag = false;
        Pattern p = Pattern.compile(".*\\d+.*");
        Matcher m = p.matcher(str);
        if (m.matches())
            flag = true;

        return flag;
    }

    /**
     * 获取当前时间
     *
     * @return yyyy-MM-dd HH:mm:ss
     */
    public static String getDateStr() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return formatter.format(currentTime);
    }

    /**
     * 获取当前时间
     *
     * @return yyyy-MM-dd HH:mm
     */
    public static String getDate() {
//		Date currentTime = new Date();
//		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
//		return formatter.format(currentTime);
        return new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date());
    }

    /**
     * 返回当前时间的毫秒值
     *
     * @return
     */
    public static String getDateMilliscond() {
        return System.currentTimeMillis() + "";
    }

    /***
     * ListView item 重新计算高,解决listview 嵌套问题
     */
    public static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return;

        }
        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
    }

    /**
     * 去除html标签，并返回前60个字
     *
     * @param html
     * @return
     */
    /*public static String dislodgeLabel(String html) {
        Document document = Jsoup.parse(html);
        String content = document.body().text();
        if (content.length() > 60) {
            return content.substring(0, 60);
        } else {
            return content.substring(0, content.length());
        }
    }*/

    /**
     * 设置键盘隐藏
     *
     * @param
     */
    public static void setKeyboard(Context context, View v) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        //隐藏
        imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
    }

    private static String identifyingName;
    private static SpannableStringBuilder ssb;
    private static Drawable drawable;
    private static ImageSpan span;
    private static Editable editable;
    private static int start;

    /**
     * 实现图文混淆
     *
     * @param bitmap
     * @param et
     */
    public static void realizeGraphicConfusion(Bitmap bitmap, EditText et, String photoName) {
        identifyingName = "<img src='" + photoName + ".JPEG'>";
        ssb = new SpannableStringBuilder(identifyingName);
        drawable = convertBitmap2Drawable(bitmap);
        //设置图片的宽高
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        span = new ImageSpan(drawable, ImageSpan.ALIGN_BASELINE);
        ssb.setSpan(span, 0, identifyingName.length(), Spannable.SPAN_INCLUSIVE_EXCLUSIVE);

        editable = et.getText();

        start = et.getSelectionStart();
        editable.insert(start, ssb);
    }

    /**
     * bitmap --> Drawable
     *
     * @param bitmap
     * @return
     */
    public static Drawable convertBitmap2Drawable(Bitmap bitmap) {
        BitmapDrawable bd = new BitmapDrawable(bitmap);
        return bd;
    }



}
