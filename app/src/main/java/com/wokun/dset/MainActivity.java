package com.wokun.dset;

import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gyf.barlibrary.ImmersionBar;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.shantoo.widget.toast.RxToast;
import com.wokun.dset.mainfragment.EcologyFragment;
import com.wokun.dset.mainfragment.HomeFragment;
import com.wokun.dset.mainfragment.MineFragment;
import com.wokun.dset.mainfragment.ShopCartFragment;
import com.wokun.dset.model.Constants;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindColor;
import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements
        ViewPager.OnPageChangeListener, View.OnClickListener {
    private String TAG = MainActivity.class.getSimpleName();

/* @BindColor(R.color.color77)int colorUnSelect;
    @BindColor(R.color.colorWhite)int colorSelect;*/

    @BindViews({R.id.tab_img_home, R.id.tab_img_zb, R.id.tab_img_shop_cart, R.id.tab_img_ucenter})
    ImageView[] mImageViews;

    @BindViews({R.id.tab_text_home, R.id.tab_text_zb, R.id.tab_text_shop_cart, R.id.tab_text_ucenter, R.id.tv_shop_cart_point})//购物车小红点
            TextView[] mTextViews;

    @BindViews({R.id.main_home, R.id.main_zb, R.id.main_shop_cart, R.id.main_ucenter})
    RelativeLayout mRelativeLayout[];

    @BindView(R.id.view_pager)
    ViewPager mViewPager;

    @BindView(R.id.main_tab)
    LinearLayout mainTab;

    // 定义一个变量，来标识是否退出
    private static boolean isExit = false;

    private List<Fragment> mFragment = new ArrayList<>();

    private static Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            isExit = false;
        }
    };
    private String joinAct;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View root = LayoutInflater.from(this).inflate(R.layout.activity_main, null);
        setContentView(root);
        ButterKnife.bind(this);
        //StatusBarUtil.setColor(this, colorSelect);
        ImmersionBar.with(this).init();
        root.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                if (bottom - oldBottom < -1) {
                    //软键盘弹上去了,动态设置高度为0
                    mainTab.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 0));
                } else if (bottom - oldBottom > 1) {
                    mainTab.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                }
            }
        });
        initMainViewPager();


    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);

        intent = getIntent();
        joinAct = intent.getStringExtra("main");
        Log.i(TAG, "onCreate: " + joinAct);
        if (joinAct != null && joinAct.equals("joincart")) {
            mViewPager.setCurrentItem(Constants.TAB_POSITION_SHOP_CART, false);
        } else {
            mViewPager.setCurrentItem(Constants.TAB_POSITION_HOME, false);
        }
    }

    @Override
    public void onClick(View v) {
        if (R.id.main_home == v.getId()) {
            mViewPager.setCurrentItem(Constants.TAB_POSITION_HOME, false);
        } else if (R.id.main_zb == v.getId()) {
            mViewPager.setCurrentItem(Constants.TAB_POSITION_ZB, false);
        } else if (R.id.main_shop_cart == v.getId()) {
            mViewPager.setCurrentItem(Constants.TAB_POSITION_SHOP_CART, false);
        } else if (R.id.main_ucenter == v.getId()) {
            mViewPager.setCurrentItem(Constants.TAB_POSITION_UCENTER, false);
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        //    Logger.e(TAG,Constants.TAB_POSITION + AppCache.getTabPosition());
//        if (DsetApp.getInstance().isRefreshShopCart()) {
//            changeTextViewColor();
//            changeSelectedTabState(DsetApp.getInstance().getTabPosition());
//            mViewPager.setCurrentItem(DsetApp.getInstance().getTabPosition(), false);
//        } else if (-1 != AppCache.getTabPosition()) {
//            changeTextViewColor();
//            changeSelectedTabState(AppCache.getTabPosition());
//            mViewPager.setCurrentItem(AppCache.getTabPosition(), false);
//        }
    }


    private void initMainViewPager() {
        mRelativeLayout[Constants.TAB_POSITION_HOME].setOnClickListener(this);
        mRelativeLayout[Constants.TAB_POSITION_ZB].setOnClickListener(this);
        mRelativeLayout[Constants.TAB_POSITION_SHOP_CART].setOnClickListener(this);
        mRelativeLayout[Constants.TAB_POSITION_UCENTER].setOnClickListener(this);

        mFragment.add(new HomeFragment());
        mFragment.add(new EcologyFragment());
        mFragment.add(new ShopCartFragment());
        mFragment.add(new MineFragment());

        mViewPager.setAdapter(fragmentPagerAdapter);
        mViewPager.setOffscreenPageLimit(4);
        mViewPager.addOnPageChangeListener(this);
        changeTextViewColor();
        changeSelectedTabState(getIntent().getIntExtra(Constants.TAB_POSITION, 0));
//        mViewPager.setCurrentItem(getIntent().getIntExtra(Constants.TAB_POSITION, 0), false);

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                ImmersionBar immersionBar = ImmersionBar.with(MainActivity.this);
                switch (position) {
                    case 0:
                        immersionBar.statusBarDarkFont(false)
                                .transparentStatusBar()
                                .init();
                        break;
                    case 1:
                        immersionBar.statusBarDarkFont(false)
                                .transparentStatusBar()
                                .init();
                        break;
                    case 2:
                        immersionBar.statusBarDarkFont(false)
                                .transparentStatusBar()
                                .init();
                        break;
                    case 3:
                        immersionBar.statusBarDarkFont(false)
                                .transparentStatusBar()
                                .init();
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ImmersionBar.with(this).destroy();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Constants.LOGIN_RESULT_CODE) {
            //    initUserInfo();
        }
    }






/*    private void initUserInfo() {
        OkGo.<BaseResponse<ServiceMessageResponse>>post(Constants.BASE_URL + Constants.UCENTER_GET_SERVICE_NOTICE_URL)
                .tag(this)
                .execute(new JsonCallback<BaseResponse<ServiceMessageResponse>>(Constants.WITH_TOKEN,Constants.UCENTER_GET_SERVICE_NOTICE_URL) {
                    @Override
                    public void onSuccess(Response<BaseResponse<ServiceMessageResponse>> response) {
                        BaseResponse body = response.body();
                        if(body==null)return;
                        if(body.isState()){
                            ServiceMessageResponse data = (ServiceMessageResponse) body.getData();
                            if(data == null){return;}
                            User user = ProjectApp.getInstance().getUser();
                            UserInfo userInfo = ProjectApp.getInstance().getUserInfo();
                            userIdList = data.getServiceList();
                            if (userIdList != null)
                                userIdList.add(new ServiceNoticeBean(user.getUserId(), userInfo.getUserName(), userInfo.getAvatar()));

                        }
                    }
                });
    }*/
/*private long mExitTime;

    //双击退出app
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            // TODO: 2016/8/19  二次返回退出
            if ((System.currentTimeMillis() - mExitTime) > 2000) {
                RxToast.showShort("再按一次退出程序");
                mExitTime = System.currentTimeMillis();
            } else {
//                SharedPrefsUtil.clearSharedPreference2(context);//清空通用URL
                AppManager.getAppManager().finishAllActivity();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }*/

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exit();
            return false;
        }

        return super.onKeyDown(keyCode, event);
    }


    private void exit() {
        if (!isExit) {
            isExit = true;
            RxToast.showShort("再按一次退出程序");
            // 利用handler延迟发送更改状态信息
            mHandler.sendEmptyMessageDelayed(0, 2000);
        } else {

            finish();
        }
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onPageSelected(int position) {
        AppCache.setTabPosition(position);
        // Logger.e(TAG,Constants.TAB_POSITION + AppCache.getTabPosition());
        changeTextViewColor();
        changeSelectedTabState(position);
    }

    private void changeTextViewColor() {
        mImageViews[0].setBackgroundResource(R.drawable.main_dibu_first);
        mTextViews[0].setTextColor(Color.parseColor("#777777"));

        mImageViews[1].setBackgroundResource(R.drawable.main_linshou);
        mTextViews[1].setTextColor(Color.parseColor("#777777"));

        mImageViews[2].setBackgroundResource(R.drawable.main_dibu_shop);
        mTextViews[2].setTextColor(Color.parseColor("#777777"));

        mImageViews[3].setBackgroundResource(R.drawable.main_dibu_my);
        mTextViews[3].setTextColor(Color.parseColor("#777777"));
    }

    private void changeSelectedTabState(int position) {
        if (position == 0) {
            mTextViews[0].setTextColor(Color.parseColor("#ffffff"));
            mImageViews[0].setBackgroundResource(R.drawable.select_mian_first);
        } else if (position == 1) {
            mTextViews[1].setTextColor(Color.parseColor("#ffffff"));
            mImageViews[1].setBackgroundResource(R.drawable.select_mian_linshou);
        } else if (position == 2) {
            mTextViews[2].setTextColor(Color.parseColor("#ffffff"));
            mImageViews[2].setBackgroundResource(R.drawable.select_mian_shop);
        } else if (position == 3) {
            mTextViews[3].setTextColor(Color.parseColor("#ffffff"));
            mImageViews[3].setBackgroundResource(R.drawable.select_mian_my);
        }
    }

    private FragmentPagerAdapter fragmentPagerAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
        @Override
        public Fragment getItem(int position) {
            return mFragment.get(position);
        }

        @Override
        public int getCount() {
            return mFragment.size();
        }
    };
}
