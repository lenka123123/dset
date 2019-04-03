package com.wokun.dset;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.itheima.roundedimageview.RoundedImageView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.maning.updatelibrary.InstallUtils;
import com.shantoo.widget.toast.RxToast;
import com.sunfusheng.marqueeview.MarqueeView;
import com.wokun.dset.base.BaseFragment;
import com.wokun.dset.callback.JsonCallback;
import com.wokun.dset.home.AboutTyslActivity;
import com.wokun.dset.home.DShopHomeActivity;
import com.wokun.dset.home.HuiyuanlevelActivity;
import com.wokun.dset.home.NoticeWebActivity;
import com.wokun.dset.home.SignBean;
import com.wokun.dset.hudongshop.ChangeshopActivity;
import com.wokun.dset.login.LoginActivity;
import com.wokun.dset.login.LoginMgr;
import com.wokun.dset.model.Constants;
import com.wokun.dset.model.UserBean;
import com.wokun.dset.response.BaseResponse;
import com.wokun.dset.store.bean.VersionBean;
import com.wokun.dset.ucenter.DsytYaoqingActivity;
import com.wokun.dset.ucenter.SaoyisaoActivity;
import com.wokun.dset.utils.AlertDialogUtil;
import com.wokun.dset.utils.ImageLoader;
import com.wokun.dset.utils.JosnFrom;
import com.wokun.dset.utils.SpCommonUtils;
import com.wokun.dset.utils.StringUtil;
import com.wokun.dset.utils.VsersionUtil;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

import static com.shantoo.widget.utils.ManagerTool.getPackageManager;
import static com.wokun.dset.utils.MD5.ParameterUtils.removeEmptyData;
import static com.wokun.dset.utils.MD5.ParameterUtils.sortMapByKey;


public class TextAct extends Activity implements AlertDialogUtil.OnClickDialogListener {
    private static final int apkDownload = 100;
    private static final int apkUpdate = 101;
    private static final String TAG = "";
    private String fileDir;
    private NotificationCompat.Builder builder;
    private Notification notification;
    private NotificationManager notificationManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AlertDialogUtil.getInstance().showCustomDialogFlag(this,
                apkDownload, "应用更新提示", "您好检测到新版本", TextAct.this);
    }


//    @Override
//    public void OnOnClickDialogListener(boolean isUpdata, int flag) {
//        Log.i(TAG, "OnOnClickDialogListener: " + isUpdata + "====00===" + flag);
//        if (flag == apkDownload && isUpdata) {
//            //路径
//            fileDir = getCacheDir().getAbsolutePath() + "/dsyt.apk";
//            initNotification();
//            downLoadManager("http://api.dasether.com/apk/app-release.apk");
//        }
//
//        if (flag == apkUpdate) {
//        }
//
//    }


    @Override
    public void OnOnClickDialogListener(boolean isUpdata, int flag) {
        Log.i(TAG, "OnOnClickDialogListener: " + isUpdata + "====00===" + flag);
        if (flag == apkDownload && isUpdata) {
            //路径
            fileDir = getCacheDir().getAbsolutePath() + "/dsyt.apk";
            initNotification();
            downLoadManager("http://api.dasether.com/apk/app-release.apk");
        }

        if (flag == apkUpdate) {
        }

    }

    private void downLoadManager(String apkDownloadUrl) {

        //下载APK
        InstallUtils.with(this)
                //必须-下载地址
                .setApkUrl(apkDownloadUrl)
                //非必须-下载保存的文件的完整路径+/name.apk，使用自定义路径需要获取读写权限
                .setApkPath(fileDir)
                //非必须-下载回调

                .startDownload();
    }

    //初始化通知
    private void initNotification() {
        notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        builder = new NotificationCompat.Builder(this);
        builder.setContentTitle("正在更新...") //设置通知标题
                .setSmallIcon(R.mipmap.ic_log)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_log)) //设置通知的大图标
                .setDefaults(Notification.DEFAULT_LIGHTS) //设置通知的提醒方式： 呼吸灯
                .setPriority(NotificationCompat.PRIORITY_MAX) //设置通知的优先级：最大
                .setAutoCancel(false)//设置通知被点击一次是否自动取消
                .setContentText("下载进度:" + "0%")
                .setProgress(100, 0, false);
        //构建通知对象
        notification = builder.build();
    }


    public void installApk() {
        //安装APK
        InstallUtils.installAPK(this, fileDir, new InstallUtils.InstallCallBack() {
            @Override
            public void onSuccess() {
                //onSuccess：表示系统的安装界面被打开
                //防止用户取消安装，在这里可以关闭当前应用，以免出现安装被取消
                Toast.makeText(TextAct.this, "正在安装程序", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFail(Exception e) {
                //安装出现异常，这里可以提示用用去用浏览器下载安装
            }
        });
    }


    @Override
    public void onStart() {
        super.onStart();
    }

}
