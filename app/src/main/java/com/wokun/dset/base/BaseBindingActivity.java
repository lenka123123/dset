package com.wokun.dset.base;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.classic.common.MultipleStatusView;

import com.r0adkll.slidr.Slidr;
import com.shantoo.common.utils.AppManager;
import com.shantoo.widget.dialog.loaddialog.LoadDialog;
import com.shantoo.widget.toast.RxToast;
import com.shantoo.widget.toolbar.WidgetBar;
import com.wokun.dset.DsetApp;
import com.wokun.dset.R;
import com.wokun.dset.login.LoginActivity;
import com.wokun.dset.model.Constants;

import butterknife.BindColor;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseBindingActivity extends AppCompatActivity {

    //   @BindColor(R.color.colorToolBarText)int color;

    private Unbinder unbinder;
    private LoadDialog mLoadDialog;

    protected WidgetBar mWidgetBar;
    protected MultipleStatusView mMultipleStatusView;

    protected String TAG = this.getClass().getSimpleName();
    private View root;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AppManager.getAppManager().addActivity(this);
        root = LayoutInflater.from(this).inflate(R.layout.activity_base, null);
        View contentView = LayoutInflater.from(this).inflate(createView(), null);
        setContentView(root);
        Slidr.attach(this);
        unbinder = ButterKnife.bind(this, contentView);
        mWidgetBar = (WidgetBar) findViewById(R.id.toolbar);
        mWidgetBar.getBackground().setAlpha(250);
        mMultipleStatusView = (MultipleStatusView) findViewById(R.id.multipleStatusView);
        mMultipleStatusView.addView(contentView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        mMultipleStatusView.setOnRetryClickListener(mRetryClickListener);
        mLoadDialog = new LoadDialog(this, true, "", R.color.colorPrimary);

        initToolBar(createToolBar());
        init();
    }

    public void setmWidgetBarShow(boolean show) {
        unbinder.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    public abstract int createView();

    public abstract WidgetBar createToolBar();

    public abstract void init();

    final View.OnClickListener mRetryClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            RxToast.showShort("您点击了重试视图");
        }
    };
    static final int DELAY = 5000;

    void loading() {
        mMultipleStatusView.showLoading();
        mMultipleStatusView.postDelayed(new Runnable() {
            @Override
            public void run() {
                mMultipleStatusView.showContent();
            }
        }, DELAY);
    }

    public void initToolBar(WidgetBar toolBar) {
        if (toolBar == null) {
            return;
        }
        toolBar
                .setWidgetBarTitleTextSize(18)
                .setWidgetBarTitleTextColor(Color.parseColor("#ffffff"))
                .setWidgetBarNavigation(R.drawable.ic_back_white)
                .setOnNavigationClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                });
    }

    public void jumpToActivityAndClearTask(Class activity) {
        Intent intent = new Intent(this, activity);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }

    public void jumpToActivityAndClearTop(Class activity) {
        Intent intent = new Intent(this, activity);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    public void startActivity(Class<?> cls) {
        startActivity(new Intent(this, cls));
    }
/*
    //打开一个Activity时需要携带数据
    public void startActivity(Intent intent, boolean isNeedLogin) {
        if (isNeedLogin) {
            User user = ProjectApp.getInstance().getUser();
            boolean hasLogin = ProjectApp.getInstance().isLogin();
            if (user != null && hasLogin) { //已登录
                startActivity(intent);
            } else { //未登录
                intent.setClass(this, LoginActivity.class);
                startActivityForResult(intent, Constants.LOGIN_REQUEST_CODE);
            }
        } else {
            startActivity(intent);
        }
    }*/

    //打开一个Activity时不需要携带数据
    public void startActivity(Class<?> clazz, boolean isNeedLogin) {
        if (isNeedLogin) {//需要登录
            Intent intent = new Intent();
            intent.setClass(this, LoginActivity.class);
            startActivityForResult(intent, Constants.LOGIN_REQUEST_CODE);
        } else {//不需要登录
            startActivity(clazz);
        }
    }

    public void startActivityForResult(Intent intent, int requestCode, boolean isNeedLogin) {
        if (isNeedLogin) {
            boolean hasLogin = DsetApp.getInstance().isLogin();
            if (!hasLogin) {
                intent.setClass(this, LoginActivity.class);
                startActivityForResult(intent, Constants.LOGIN_REQUEST_CODE);
                return;
            }
        }
        startActivity(intent);
    }

    protected void showLP() {
        mLoadDialog.show();
    }

    protected void dismissLP() {
        mLoadDialog.dismiss();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
        if (mLoadDialog != null) {
            mLoadDialog.dismiss();
            mLoadDialog = null;
        }
    }

}