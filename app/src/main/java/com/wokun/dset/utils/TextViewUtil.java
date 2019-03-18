package com.wokun.dset.utils;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;
import android.text.style.ScaleXSpan;
import android.text.style.StrikethroughSpan;
import android.text.style.StyleSpan;
import android.text.style.SubscriptSpan;
import android.text.style.TypefaceSpan;

import com.wokun.dset.R;

public class TextViewUtil {


    //设置圆角背景 和字体颜色
    public static SpannableString setSpanBgAndTvColor(String str, int start, int end, String tvColor, String bgColor) {
        SpannableString ss = new SpannableString(str); // 用颜色标记文本

        ss.setSpan(new RoundBackgroundColorSpan(Color.parseColor(bgColor), Color.parseColor(tvColor)), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return ss;
    }


    //设置字体颜色
    public static SpannableString setSpanColor(Context context, String str, int start, int end, String tvColor, String bgColor) {
        SpannableString ss = new SpannableString(str); // 用颜色标记文本
        // 字体颜色
        if (tvColor != null)
            ss.setSpan(new ForegroundColorSpan(Color.parseColor(tvColor)), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        if (bgColor != null)
            ss.setSpan(new BackgroundColorSpan(Color.parseColor(bgColor)), 0, 2, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);


        return ss;
    }

    /**
     * https://blog.csdn.net/cheyiliu/article/details/44955989
     * 设置字体大小，用sp
     *
     * @param context
     * @param str     目标字符串
     * @param start   开始位置
     * @param end     结束位置
     * @param spSize  sp大小
     * @return
     */
    public static SpannableString getSizeSpanSpToPx(Context context, String str, int start, int end, int spSize) {
        SpannableString ss = new SpannableString(str);
        ss.setSpan(new AbsoluteSizeSpan((int) ScreenUtils.sp2px(context, spSize)), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        return ss;
    }

    /**
     * 设置背景色
     *
     * @param context
     * @param str     目标字符串
     * @param start   开始位置
     * @param end     结束位置
     * @param color   颜色值 如：#CCCCCC
     * @return
     */
    public static SpannableString getBackGroundColorSpan(Context context, String str, int start, int end, String color) {
        SpannableString ss = new SpannableString(str);
        ss.setSpan(new BackgroundColorSpan(Color.parseColor(color)), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return ss;
    }

    /**
     * 设置字体删除线
     *
     * @param context
     * @param str     目标字符串
     * @param start   开始位置
     * @param end     结束位置
     * @return
     */
    public SpannableString getDeleteLineSpan(Context context, String str, int start, int end) {
        SpannableString ss = new SpannableString(str);
        ss.setSpan(new StrikethroughSpan(), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return ss;
    }

    /**
     * 设置放大系数
     *
     * @param context
     * @param str     目标字符串
     * @param start   开始位置
     * @param end     结束位置
     * @param scale   放大多少倍，x轴方向，y轴不变 如：0.5f， 2.0f
     * @return
     */
    public SpannableString getScaleSpan(Context context, String str, int start, int end, float scale) {
        SpannableString ss = new SpannableString(str);
        ss.setSpan(new ScaleXSpan(scale), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return ss;
    }

    /**
     * 设置下标
     *
     * @param context
     * @param str     目标字符串
     * @param start   开始位置
     * @param end     结束位置
     * @return
     */
    public SpannableString getSubscriptSpan(Context context, String str, int start, int end) {
        SpannableString ss = new SpannableString(str);
        ss.setSpan(new SubscriptSpan(), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return ss;
    }

    /**
     * 设置字体
     *
     * @param context
     * @param str      目标字符串
     * @param start    开始位置
     * @param end      结束位置
     * @param typeface 字体类型 如：default，efault-bold,monospace,serif,sans-serif
     * @return
     */
    public SpannableString getTypeFaceSpan(Context context, String str, int start, int end, String typeface) {
        SpannableString ss = new SpannableString(str);
        ss.setSpan(new TypefaceSpan(typeface), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return ss;
    }

    /**
     * 设置字体形体
     *
     * @param context
     * @param str     目标字符串
     * @param start   开始位置
     * @param end     结束位置
     * @param style   字体类型 如： Typeface.NORMAL正常 Typeface.BOLD粗体 Typeface.ITALIC斜体
     *                Typeface.BOLD_ITALIC粗斜体
     * @return
     */
    public SpannableString getStyleSpan(Context context, String str, int start, int end, int style) {
        SpannableString ss = new SpannableString(str);
        ss.setSpan(new StyleSpan(style), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return ss;
    }

}
