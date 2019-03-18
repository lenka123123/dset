package com.wokun.dset.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.wokun.dset.R;

public class ImageLoaderUtils {


    private static final String TAG = "ImageLoaderUtils";

    /**
     * 圆角图片加载
     *
     * @param context      上下文
     * @param imageView    图片显示控件
     * @param url          图片链接
     * @param radius       图片圆角半径
     * @return
     * @author leibing
     * @createTime 2016/8/15
     * @lastModify 2016/8/15
     */
    public static void load(Context context, ImageView imageView, String url,   int radius) {
        RequestOptions options = new RequestOptions()
                .priority(Priority.NORMAL) //指定加载的优先级，优先级越高越优先加载，
                .placeholder(R.drawable.default_img)
                .error(R.drawable.no_data)
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE).centerCrop().transform(new CornersTranform(context, radius));

        Glide.with(context).load(url).apply(options).transition(new DrawableTransitionOptions().crossFade()).into(imageView);

    }

    /**
     * 加载resoures下的文件
     *
     * @param context
     * @param imageView
     * @param url
     */
    public static void loadImgId(Context context, final ImageView imageView, int url,  int radius) {
        RequestOptions options = new RequestOptions()
                .priority(Priority.NORMAL) //指定加载的优先级，优先级越高越优先加载，
                .placeholder(R.drawable.default_img)
                .error(R.drawable.no_data)

                .diskCacheStrategy(DiskCacheStrategy.RESOURCE).centerCrop().transform(new CornersTranform(context, radius));

        Glide.with(context).load(url).apply(options).transition(new DrawableTransitionOptions().crossFade()).into(imageView);
    }

    /**
     * 加载圆形头像
     *
     * @param context
     * @param imageView
     * @param url
     */

    public static void loadCircle(Context context, final ImageView imageView, String url) {
        RequestOptions options = new RequestOptions()

                .priority(Priority.NORMAL) //指定加载的优先级，优先级越高越优先加载，
                .dontAnimate() //防止设置placeholder导致第一次不显示网络图片,只显示默认图片的问题
                .placeholder(R.drawable.default_img)
                .error(R.drawable.no_data)

                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                .centerCrop()
                .transform(new GlideCircleTransform(context));
        Glide.with(context).load(url).apply(options).transition(new DrawableTransitionOptions().crossFade()).into(new SimpleTarget<Drawable>() {
            @Override
            public void onResourceReady(Drawable resource, Transition<? super Drawable> transition) {
                imageView.setImageDrawable(resource);
            }
        });
    }


}
















