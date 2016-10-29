package com.example.lenovo.demo_grideview_scoreview.adapter;

import android.app.Activity;
import android.graphics.Bitmap;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lenovo.demo_grideview_scoreview.R;
import com.example.lenovo.demo_grideview_scoreview.Utils.BitmapCache;
import com.example.lenovo.demo_grideview_scoreview.been.ImageBucket;
import com.example.lenovo.demo_grideview_scoreview.been.SquareImage;

import java.util.List;

public class ImageBucketAdapter extends BaseAdapter {
    final String TAG = getClass().getSimpleName();
    Activity act;
    /**
     * 图片集列表
     */
    List<ImageBucket> dataList;
    private String thumbPath;
    private String sourcePath;
    private final BitmapCache cache;
    BitmapCache.ImageCallback callback = new BitmapCache.ImageCallback() {
        @Override
        public void imageLoad(ImageView imageView, Bitmap bitmap,
                              Object... params) {
            if (imageView != null && bitmap != null) {
                String url = (String) params[0];
                if (url != null && url.equals(imageView.getTag())) {
                    imageView.setImageBitmap(bitmap);
                } else {

                }
            } else {

            }
        }
    };


    public ImageBucketAdapter(Activity act, List<ImageBucket> list) {
        this.act = act;
        cache = new BitmapCache();
        dataList = list;
    }

    @Override
    public int getCount() {

        int count = 0;
        if (dataList != null) {
            count = dataList.size();
        }
        return count;
    }

    @Override
    public Object getItem(int arg0) {

        return null;
    }

    @Override
    public long getItemId(int arg0) {

        return arg0;
    }

    class Holder {
        private SquareImage iv;
        private TextView name;
        private TextView count;
    }

    @Override
    public View getView(int arg0, View arg1, ViewGroup arg2) {

        Holder holder;
        if (arg1 == null) {
            holder = new Holder();
            arg1 = View.inflate(act, R.layout.item_image_bucket, null);
            holder.iv = (SquareImage) arg1.findViewById(R.id.image);
            holder.name = (TextView) arg1.findViewById(R.id.name);
            holder.count = (TextView) arg1.findViewById(R.id.count);
            arg1.setTag(holder);
        } else {
            holder = (Holder) arg1.getTag();
        }
        ImageBucket item = dataList.get(arg0);
        holder.count.setText("(" + item.count + ")");
        holder.name.setText(item.bucketName);
        if (item.imageList != null && item.imageList.size() > 0) {
            thumbPath = item.imageList.get(0).thumbnailPath;
            sourcePath = item.imageList.get(0).imagePath;
            holder.iv.setTag(sourcePath);
            cache.displayBmp(holder.iv, thumbPath, sourcePath, callback);
        } else {
            holder.iv.setImageBitmap(null);
        }
        return arg1;
    }

}
