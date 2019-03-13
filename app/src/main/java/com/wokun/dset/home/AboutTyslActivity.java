package com.wokun.dset.home;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;

import com.wokun.dset.R;

import butterknife.BindView;

public class AboutTyslActivity extends AppCompatActivity {
/*     @BindView(R.id.back_img)
    ImageView   back_img;*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_tysl);
        loadUrl();
        ImageView back_img =(ImageView) findViewById(R.id.back_img);
        back_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    private void loadUrl() {
        WebView webView = (WebView)findViewById(R.id.web);
        webView.loadUrl("http://www.appchina.com/app/com.wokun.tysl");//加载url

    }
}
