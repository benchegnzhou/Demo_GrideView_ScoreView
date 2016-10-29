package com.example.lenovo.demo_grideview_scoreview.adapter;

import android.app.Activity;
import android.graphics.Bitmap;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.lenovo.demo_grideview_scoreview.R;
import com.example.lenovo.demo_grideview_scoreview.Utils.BitmapCache;
import com.example.lenovo.demo_grideview_scoreview.Utils.ToastUtils;
import com.example.lenovo.demo_grideview_scoreview.been.ImageItem;
import com.example.lenovo.demo_grideview_scoreview.been.SquareImage;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by dyyy on 2015/6/23.
 */
public class MultiplePictureImageAdapter extends BaseAdapter {

    private BitmapCache cache;
    private Activity activity;
    private List<ImageItem> imageItems;
    private LayoutInflater inflater;
    private ImageItem imageItem;
    private boolean aBoolean;
    private List<String> imageUrls;
    private int hasCount;
    BitmapCache.ImageCallback callback = new BitmapCache.ImageCallback() {
        @Override
        public void imageLoad(ImageView imageView, Bitmap bitmap,
                              Object... params) {
            if (imageView != null && bitmap != null) {
                String url = (String) params[0];
                if (url != null && url.equals(imageView.getTag(R.id.tag_first))) {
                    imageView.setImageBitmap(bitmap);
                } else {

                }
            } else {

            }
        }
    };

    public MultiplePictureImageAdapter(Activity activity, List<ImageItem> imageItems, int hasCount) {
        super();
        this.activity = activity;
        this.imageItems = imageItems;
        this.hasCount = hasCount;
        cache = new BitmapCache();
        this.imageUrls = new ArrayList<>();
        inflater = LayoutInflater.from(activity);
    }


    @Override
    public int getCount() {
        if (imageItems != null && imageItems.size() > 0) {
            return imageItems.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int i) {
        return imageItems.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        final ViewHolder viewHolder;
        if (view == null) {
            view = inflater.inflate(R.layout.item_multiple_picture_image_adapter, null);
            viewHolder = new ViewHolder();
            viewHolder.imageView = (SquareImage) view.findViewById(R.id.item_multiple_picture_image_adapter_imageView);
            viewHolder.pitchOn = (ImageView) view.findViewById(R.id.item_multiple_picture_image_adapter_pitchOn);

            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        imageItem = imageItems.get(i);
        if (imageItem.isSelected) {
            viewHolder.pitchOn.setVisibility(View.VISIBLE);
        } else {
            viewHolder.pitchOn.setVisibility(View.GONE);
        }
        viewHolder.imageView.setTag(R.id.tag_first, imageItem.imagePath);
        cache.displayBmp(viewHolder.imageView, imageItem.thumbnailPath, imageItem.imagePath, callback);
        viewHolder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int tag = (Integer) view.getTag(R.id.tag_second);
                ImageItem imageItem = imageItems.get(tag);
                aBoolean = imageItem.isSelected;
                if (aBoolean) {
                    imageItem.isSelected = false;
                    imageUrls.remove(imageItem.imagePath);
                    viewHolder.pitchOn.setVisibility(View.GONE);
                } else {
                    if (imageUrls.size() < 9 - hasCount) {
                        imageItem.isSelected = !aBoolean;
                        imageUrls.add(imageItem.imagePath);
                        viewHolder.pitchOn.setVisibility(View.VISIBLE);

                    } else {

                        ToastUtils.showToast("hint_only_upload_9_photo"+ "在类MultiplePictureImageAdapter中");
                    }
                }
            }
        });
        viewHolder.imageView.setTag(R.id.tag_second, i);
        return view;
    }

    public List<String> getImageUrls() {
        return imageUrls;
    }

    static class ViewHolder {
        SquareImage imageView;
        ImageView pitchOn;
    }
}
