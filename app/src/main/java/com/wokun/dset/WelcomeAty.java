package com.wokun.dset;

import android.os.Handler;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.shantoo.widget.toolbar.WidgetBar;
import com.wokun.dset.base.BaseBindingActivity;
import com.wokun.dset.login.LoginActivity;

import butterknife.BindView;


/**
 * 欢迎页
 *
 * @author 陈白衣
 * @time 2018/2/27 10:20
 */
public class WelcomeAty extends BaseBindingActivity {
    @BindView(R.id.ivWelcome)
    ImageView ivWelcome;
    private boolean isFirst = true;


    @Override
    public int createView() {
        return R.layout.activity_welcome;
    }

    @Override
    public WidgetBar createToolBar() {
        mWidgetBar.setVisibility(View.GONE);
        return mWidgetBar;
    }

    @Override
    public void init() {
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(LoginActivity.class);
                finish();
            }
        }, 1500);
    }
}
