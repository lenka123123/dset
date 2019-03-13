package com.wokun.dset.utils;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.wokun.dset.R;


/**
 * Created by Administrator on 2018/9/12.
 */

public class PwdDialog extends Dialog {

    private Context context;
    View view;


    public PwdDialog(@NonNull Context context) {
        super(context,R.style.MyDialogStyle);
        this.context = context;
        init();
    }

    public PwdDialog(@NonNull Context context, @StyleRes int themeResId) {
        super(context, themeResId);
        this.context = context;
        init();
    }

    private void init() {
        this.setCanceledOnTouchOutside(true);
        this.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
         view = LayoutInflater.from(context).inflate(R.layout.dialog_input_pwd, null);
        final PswInputView pswInputView = (PswInputView) view.findViewById(R.id.psw_input);
        ImageView close = (ImageView) view.findViewById(R.id.iv_dialog_close);
        /** 3.自动弹出软键盘 **/
        this.setOnShowListener(new DialogInterface.OnShowListener() {
            public void onShow(DialogInterface dialog) {
                SoftInputUtil.showSoftInput((Activity) context);
            }
        });
        this.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                SoftInputUtil.hideSoftInput((Activity) context);
            }
        });
        this.getWindow().setContentView(view);//自定义布局应该在这里添加，要在dialog.show()的后面
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        pswInputView.setInputCallBack(new PswInputView.InputCallBack() {
            @Override
            public void onInputFinish(String result) {
                dismiss();
                if (pwdDialogInterface!=null){
                    pwdDialogInterface.onInputFinish(result);
                }
            }
        });
    }

    public void showDialog(){
        // 将对话框的大小按屏幕大小的百分比设置
        WindowManager windowManager = getWindow().getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        WindowManager.LayoutParams lp = this.getWindow().getAttributes();
        lp.width = (int)(display.getWidth() * 0.8); //设置宽度
        this.getWindow().setAttributes(lp);
        this.show();
    }


    public interface PwdDialogInterface{
        void onInputFinish(String result);
    }

    public void setPwdDialogInterface(PwdDialogInterface pwdDialogInterface) {
        this.pwdDialogInterface = pwdDialogInterface;
    }

    private PwdDialogInterface pwdDialogInterface;



}
