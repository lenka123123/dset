//package com.example.baselib.utils;
//
//
//import android.app.Activity;
//import android.content.Context;
//import android.widget.ImageView;
//
//import com.bumptech.glide.Glide;
//import com.youth.banner.loader.ImageLoader;
//
//public class PicassoImageLoader extends ImageLoader implements com.lzy.imagepicker.loader.ImageLoader {
//    @Override
//    public void displayImage(Context context, Object path, ImageView imageView) {
//        Glide.with(context).load((String) path).into(imageView);
//    }
//
//    @Override
//    public void displayImage(Activity activity, String path, ImageView imageView, int width, int height) {
//        Glide.with(activity).load((String) path).into(imageView);
//    }
//
//    @Override
//    public void displayImagePreview(Activity activity, String path, ImageView imageView, int width, int height) {
//        Glide.with(activity).load((String) path).into(imageView);
//    }
//
//    @Override
//    public void clearMemoryCache() {
//
//    }
//
//}
