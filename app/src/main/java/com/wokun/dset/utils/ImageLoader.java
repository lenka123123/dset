package com.wokun.dset.utils;

import android.widget.ImageView;

import com.bumptech.glide.Glide;

import com.wokun.dset.DsetApp;
import com.wokun.dset.R;


public class ImageLoader {

    public static void loadBanner(String imgUrl, ImageView imageView){
        Glide.with(DsetApp.getContext())
                .load(imgUrl)
                //.placeholder(R.mipmap.ic_placeholder)
                .into(imageView);
    }

    public static void loadImageWithPlaceholder(String imgUrl, ImageView imageView) {
        Glide.with(DsetApp.getContext())
                .load(imgUrl)
                .fitCenter()
                .placeholder(R.drawable.ic_back)
                .into(imageView);
    }

    public static void loadImageWP(String imgUrl, ImageView imageView) {
        Glide.with(DsetApp.getContext())
                .load(imgUrl)
                .placeholder(R.drawable.ic_back)
                .into(imageView);
    }

    public static void loadImage(String imgUrl, ImageView imageView) {
        Glide.with(DsetApp.getContext())
                .load(imgUrl)
                .into(imageView);
    }
}
