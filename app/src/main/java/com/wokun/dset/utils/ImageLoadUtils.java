package com.wokun.dset.utils;


import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.youth.banner.loader.ImageLoader;

public class ImageLoadUtils extends ImageLoader {
    @Override
    public void displayImage(final Context context, Object path, final ImageView imageView) {
        Glide.with(context).load((String) path).into(imageView);
    }

}
