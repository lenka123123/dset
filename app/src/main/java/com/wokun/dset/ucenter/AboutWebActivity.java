package com.wokun.dset.ucenter;

import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.itheima.view.BridgeWebView;
import com.shantoo.widget.toolbar.WidgetBar;
import com.wokun.dset.R;
import com.wokun.dset.base.BaseBindingActivity;

import butterknife.BindView;

/**
 * Created by Administrator on 2019\2\21 0021.
 */

public class AboutWebActivity  extends BaseBindingActivity{
    @BindView(R.id.web)
    WebView webView;
    @BindView(R.id.my_name)
    TextView my_name;
    @BindView(R.id.back)
    ImageView back;
    private   String title ;
    @Override
    public int createView() {
        return R.layout.layout_web_activity;
    }

    @Override
    public WidgetBar createToolBar() {
        mWidgetBar.setVisibility(View.GONE);

        return mWidgetBar;
    }

    @Override
    public void init() {

        String url = getIntent().getStringExtra("url");
        title = getIntent().getStringExtra("title");
        my_name.setText(title);
        Log.e("我的title",title);
        WebSettings s = webView.getSettings();
        s.setJavaScriptEnabled(true);
        s.setAllowFileAccess(true);// 设置允许访问文件数据
        s.setSupportZoom(true);
        s.setBuiltInZoomControls(true);
        s.setJavaScriptCanOpenWindowsAutomatically(true);
        s.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        s.setDomStorageEnabled(true);
        s.setDatabaseEnabled(true);
        webView.setWebChromeClient(new WebChromeClient());
        webView.setWebViewClient(new WebViewClient(){//避免调用外部浏览器
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });

        webView.loadUrl(url);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


    }
}
