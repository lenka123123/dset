package com.wokun.dset.utils;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;


import com.wokun.dset.R;
import com.wokun.dset.login.LoginActivity;

import java.io.Serializable;

/**
 * Alert Dialog
 *
 * @author 陈白衣
 * @time 2018/3/7 10:25
 */
@SuppressWarnings("unchecked")
public class AlertDialogUtil implements Serializable {

    private static class AlertDialogHolder {
        /**
         * 单例对象实例
         */
        static final AlertDialogUtil INSTANCE = new AlertDialogUtil();
    }

    public static AlertDialogUtil getInstance() {
        return AlertDialogHolder.INSTANCE;
    }

    /**
     * private 的构造方法，避免外界直接使用new 来初始化对象
     */
    private AlertDialogUtil() {
    }

    /**
     * 应对单例对象被序列化时候
     *
     * @return
     */
    private Object readResolve() {
        return getInstance();
    }


    public void showDialog(Context mContext,
                           String title, String content,
                           final OnClickYesListener yesListener,
                           boolean cancelable) {
        showDialog(mContext, title, content, null, yesListener, cancelable);
    }

    public void showDialog(Context mContext,
                           String title,
                           String content,
                           String yes,
                           final OnClickYesListener yesListener,
                           boolean cancelable) {
        showDialog(mContext, title, content, yes, yesListener, null, null, cancelable);
    }

    public void showDialog(Context mContext,
                           String title, String content,
                           final OnClickYesListener yesListener,
                           final OnClickNoListener noListener,
                           boolean cancelable) {
        showDialog(mContext, title, content, null, yesListener, null, noListener, cancelable);
    }

    public void showDialog(Context mContext,
                           String title, String content,
                           String yes,
                           final OnClickYesListener yesListener,
                           String no,
                           final OnClickNoListener noListener,
                           boolean cancelable) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.window_alert_dialog, null);
        TextView dialogTitle = (TextView) view.findViewById(R.id.dialogTitle);
        TextView dialogContent = (TextView) view.findViewById(R.id.dialogContent);
        TextView dialogCertain = (TextView) view.findViewById(R.id.dialogCertain);
        TextView dialogCancel = (TextView) view.findViewById(R.id.dialogCancel);

        dialogContent.setMovementMethod(ScrollingMovementMethod.getInstance());
        dialogContent.setScrollbarFadingEnabled(false);

        // 实例化 AlertDialog
        final AlertDialog alertDialog = new AlertDialog.Builder(mContext).create();
        // 获取 AlertDialog 的窗体
        Window window = alertDialog.getWindow();
        // 设置背景透明
        window.setBackgroundDrawableResource(android.R.color.transparent);

        // 标题
        if (title == null || "".equals(title)) {
            dialogTitle.setVisibility(View.GONE);
            dialogContent.setTextSize(14);
        } else {
            dialogTitle.setText(title);
        }
        if (!(yes == null || yes.isEmpty())) {
            dialogCertain.setText(yes);
        }
        if (!(no == null || no.isEmpty())) {
            dialogCancel.setText(no);
        }
        if (noListener == null) {
            dialogCancel.setVisibility(View.GONE);
        }
        // 内容
        dialogContent.setText(content);
        // 设置点击系统返回可以关闭（默认）
        alertDialog.setCancelable(cancelable);
        // 显示
        alertDialog.show();
        // 加载布局，必须在 show 之后
        window.setContentView(view);

        // 确定
        dialogCertain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                yesListener.onClickYes();
                alertDialog.dismiss();
            }
        });

        // 取消
        dialogCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                noListener.onClickNo();
                alertDialog.dismiss();
            }
        });
    }

    /**
     * 确认
     */
    public interface OnClickYesListener {
        void onClickYes();
    }

    /**
     * 取消
     */
    public interface OnClickNoListener {
        void onClickNo();
    }

    /**
     * 全局提示框
     */
    public static void showDialog(final Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        AlertDialog dialog = builder.setMessage(R.string.tip_logout)
                .setPositiveButton(R.string.ensure, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(context.getApplicationContext(), LoginActivity.class);
                        context.startActivity(intent);
                    }
                }).create();
        dialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
        dialog.setCanceledOnTouchOutside(false);//点击屏幕不消失
        dialog.show();
    }


    public OnClickDialogListener mOnClickListener;

    public interface OnClickDialogListener {
        void OnOnClickDialogListener(boolean isUpdata, int flag);
    }

    public void showCustomDialogFlag(Context mContext, final int flag, String mTitle, String mContent, final OnClickDialogListener onClickDialogListener) {
        AlertDialog.Builder localBuilder = new AlertDialog.Builder(mContext);
        localBuilder.setTitle(mTitle);  // "应用更新提示"
        //localBuilder.setIcon(R.mipmap.ic_launcher);
        localBuilder.setMessage(mContent); //"您好检测到新版本"
        localBuilder.setPositiveButton(flag < 10 ? "立即更新" : "确定", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt) {
                onClickDialogListener.OnOnClickDialogListener(true, flag);
            }
        });
        localBuilder.setNegativeButton(flag < 10 ? "以后再说" : "取消", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt) {
                onClickDialogListener.OnOnClickDialogListener(false, flag);
            }
        });
        //设置点击返回键不会消失
        localBuilder.setCancelable(false).create();
        localBuilder.show();
    }


}
