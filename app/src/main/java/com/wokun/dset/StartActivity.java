package com.wokun.dset;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ImageView;

import com.shantoo.widget.toolbar.WidgetBar;
import com.wokun.dset.base.BaseBindingActivity;
import com.wokun.dset.login.LoginActivity;


import butterknife.BindView;

public class StartActivity extends BaseBindingActivity {

    @BindView(R.id.iv_intro)
    ImageView ivIntro;

    @Override
    public int createView() {
        return R.layout.activity_intro1;
    }

    @Override
    public WidgetBar createToolBar() {
        mWidgetBar.setVisibility(View.GONE);
        return mWidgetBar;
    }

    @Override
    public void init() {
        ivIntro.setImageResource(R.mipmap.ic_lanch);
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                //  Initialize SharedPreferences
                SharedPreferences getPrefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
                //  Create a new boolean and preference and set it to true
                boolean isFirstStart = getPrefs.getBoolean("firstStart", true);

                //  If the activity has never started before...
                if (isFirstStart) {
                    //  Launch app intro
                    final Intent i = new Intent(StartActivity.this, LoginActivity.class);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            StartActivity.this.startActivity(i);
                            StartActivity.this.finish();
                        }
                    });

                    //  Make a new preferences editor
                    SharedPreferences.Editor e = getPrefs.edit();
                    //  Edit preference to make it false because we don't want this to run again
                    e.putBoolean("firstStart", false);
                    //  Apply changes
                    e.apply();
                } else {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                Thread.sleep(800);
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        startActivity(new Intent(StartActivity.this, LoginActivity.class));
                                        finish();
                                    }
                                });
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();
                }
            }
        });
        // Start the thread
        t.start();
    }
}