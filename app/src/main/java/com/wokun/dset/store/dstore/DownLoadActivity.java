package com.wokun.dset.store.dstore;
import android.app.Activity;
import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import com.wokun.dset.R;

import java.util.HashMap;
import java.util.Map;

public class DownLoadActivity extends Activity {
    private Button starButton;
    private Button stopButton;
    private Button dirButton;
    private TextView infoText;

    private DownloadManager downloader;
    private Uri uri;
    private long reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_down);

        initView();
    }

    private void initView() {
        starButton = (Button) findViewById(R.id.star);
        stopButton = (Button) findViewById(R.id.stop);
        dirButton = (Button) findViewById(R.id.dir);
        infoText= (TextView) findViewById(R.id.myText);

        starButton.setOnClickListener(new myButtonListener());
        stopButton.setOnClickListener(new myButtonListener());
        dirButton.setOnClickListener(new myButtonListener());
    }

    class myButtonListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.star: {
                    //执行下载任务
                    downLoadManager();
                }
                break;
                case R.id.stop: {
                    //停止下载
                    downloader.remove(reference);
                }
                break;
                case R.id.dir: {
                    //获取存放地址
                    Map sDir=getMyFilesDir();
                    infoText.setText("downid:"+sDir.get("downid")
                            +"title:"+sDir.get("title")
                            +"statuss:"+sDir.get("statuss")
                            +"address:"+sDir.get("address")
                            +"status:"+sDir.get("status")
                    );
                }
                break;
            }
        }
    }

    /**
     * 获取下载的文件存贮路径
     * @return 文件路径
     */
    private Map getMyFilesDir() {
        Map<String, String> map = null;
        //创建查询对象
        DownloadManager.Query query=new DownloadManager.Query();
        //根据任务编号查询下载任务信息
        query.setFilterById(reference);

        Cursor cursor=downloader.query(query);
        while (cursor.moveToNext()){
            String downId= cursor.getString(cursor.getColumnIndex(DownloadManager.COLUMN_ID));    //下载文件的id
            String title = cursor.getString(cursor.getColumnIndex(DownloadManager.COLUMN_TITLE)); //下载文件的题目
            String address = cursor.getString(cursor.getColumnIndex(DownloadManager.COLUMN_LOCAL_URI)); //下载的地址
            String statuss = cursor.getString(cursor.getColumnIndex(DownloadManager.COLUMN_STATUS));   //状态
            String size= cursor.getString(cursor.getColumnIndex(DownloadManager.COLUMN_BYTES_DOWNLOADED_SO_FAR)); //大小
            String sizeTotal = cursor.getString(cursor.getColumnIndex(DownloadManager.COLUMN_TOTAL_SIZE_BYTES)); //总大小

            map = new HashMap<String, String>();
            map.put("downid", downId);
            map.put("title", title);
            map.put("statuss", statuss);
            map.put("address", address);
            map.put("status", sizeTotal+":"+size);
        }
        cursor.close();
        return map;
    }

    /**
     * DownloadManager的使用
     */
    private void downLoadManager() {
        //创建downLoadManager的管理器
        downloader = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
        //创建一个URI
        // uri = Uri.parse("http://biz.10039shop.com/download/leyt/leyt.apk");
        uri = Uri.parse("http://p1.ifengimg.com/a/2017_09/75dd25f878e8715_size66_w600_h363.jpg");
        //封装一个request对象
        DownloadManager.Request request = new DownloadManager.Request(uri);
        //设置文件必须在WIFI下下载
        request.setAllowedNetworkTypes(request.NETWORK_WIFI);

        //用于设置下载时时候在状态栏显示通知信息
        request.setNotificationVisibility(request.VISIBILITY_VISIBLE);
        //设置通知栏标题
        request.setTitle("百度下载");
        //设置Notification的message信息
        request.setDescription("图片正在下载");
        //用于设置漫游状态下是否可以下载
        request.setAllowedOverRoaming(false);
        //设置文件存放目录
        request.setDestinationInExternalFilesDir(this, Environment.DIRECTORY_DOWNLOADS, "myDownLoad");

        //发送request请求并返回一个下载ID(开始下载了)
        reference = downloader.enqueue(request);
        Log.e("开始下载", "下载的id是" + reference);

        //创建一个下载的广播,下载完成之后
        IntentFilter intentFilter = new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE);

        BroadcastReceiver myReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                long referenceTo = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1);
                if (referenceTo == reference) {
                    Log.e("结束下载", "下载完成" + referenceTo);
                    Toast.makeText(getApplicationContext(), "文件下载完成", Toast.LENGTH_SHORT).show();
                }
            }
        };
        //注册广播
        registerReceiver(myReceiver, intentFilter);
    }
}