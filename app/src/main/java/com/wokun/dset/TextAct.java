package com.wokun.dset;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

import com.allenliu.versionchecklib.v2.AllenVersionChecker;
import com.allenliu.versionchecklib.v2.builder.DownloadBuilder;
import com.allenliu.versionchecklib.v2.builder.NotificationBuilder;
import com.allenliu.versionchecklib.v2.builder.UIData;
import com.allenliu.versionchecklib.v2.callback.RequestVersionListener;
import com.lijunhuayc.downloader.downloader.DownloadProgressListener;
import com.lijunhuayc.downloader.downloader.DownloaderConfig;
import com.lijunhuayc.downloader.downloader.FileDownloader;
import com.lijunhuayc.downloader.downloader.HistoryCallback;
import com.maning.updatelibrary.InstallUtils;
import com.wokun.dset.utils.AlertDialogUtil;

import java.io.File;


public class TextAct extends Activity implements AlertDialogUtil.OnClickDialogListener, RequestVersionListener {
    private static final int apkDownload = 100;
    private static final int apkUpdate = 101;
    private static final String TAG = "";

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AlertDialogUtil.getInstance().showCustomDialogFlag(this,
                apkDownload, "应用更新提示", "您好检测到新版本", TextAct.this);
    }

    String apkUrl = "http://api.dasether.com/apk/app-release.apk";

    @Override
    public void OnOnClickDialogListener(boolean isUpdata, int flag) {
        Log.i(TAG, "OnOnClickDialogListener: " + isUpdata + "====00===" + flag);
        if (flag == apkDownload && isUpdata) {
            //路径
            String fileDir = getCacheDir().getAbsolutePath() + "/dsyt.apk";

            DownloadBuilder builder = AllenVersionChecker
                    .getInstance()
                    .requestVersion()
                    .request(this);

            AllenVersionChecker.getInstance().cancelAllMission(this);
            builder.setSilentDownload(true);
        }
    }


    @Nullable
    @Override
    public UIData onRequestVersionSuccess(String result) {
        return null;
    }

    @Override
    public void onRequestVersionFailure(String message) {

    }
}
