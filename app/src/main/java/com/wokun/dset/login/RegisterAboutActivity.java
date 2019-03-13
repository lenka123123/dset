package com.wokun.dset.login;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.itheima.view.BridgeWebView;
import com.shantoo.widget.toolbar.WidgetBar;
import com.wokun.dset.R;
import com.wokun.dset.base.BaseBindingActivity;

import butterknife.BindView;

public class RegisterAboutActivity extends BaseBindingActivity {
   @BindView(R.id.web)
   WebView webView;
    @Override
    public int createView() {
        return R.layout.activity_register_about;
    }

    @Override
    public WidgetBar createToolBar() {
        return mWidgetBar.setWidgetBarTitle("注册协议");
    }

    @Override
    public void init() {

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
        //    Log.e("webview加载的url",data.getContent());

        webView.loadUrl("http://api.dasether.com/user/xieyi.html");

    }
}
