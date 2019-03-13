package com.wokun.dset.base;


import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;


import com.shantoo.common.utils.Logger;
import com.shantoo.widget.toolbar.WidgetBar;
import com.wokun.dset.R;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;

public abstract class BaseTabActivity extends BaseBindingActivity1 {

     @BindView(R.id.toolbar) public WidgetBar widgetBar;
     @BindView(R.id.tab_layout) public TabLayout mTabLayout;

    protected Fragment mCurrentFragment;

    protected List<String> mTitles;
    protected List<Fragment> mFragments;
     private Boolean isfirest = false;
     private   int state;
    @Override
    public int createView() {
        return R.layout.activity_base_tab;
    }

    @Override
    public WidgetBar createToolBar() {

      return widgetBar.setWidgetBarTitle(initTitle());

    }

    @Override
    protected void onResume() {

        super.onResume();
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
      //  super.onSaveInstanceState(outState, outPersistentState);
    }

    @Override
    public void init() {
       // EventBus.getDefault().register(this);
        mTitles = initTabTitles();
        mFragments = initFragments();
        state = initState();
        for (int i = 0; i < mTitles.size(); i++) {
            mTabLayout.addTab(mTabLayout.newTab().setText(mTitles.get(i)));

        }
         mCurrentFragment = mFragments.get(state);
        setCurrentTab(mCurrentFragment.getClass());
         isfirest  = true;
        mTabLayout.setScrollPosition(state,0,true);
        mTabLayout.addOnTabSelectedListener(new OnTabSelectedListener());
    }

    protected void setCurrentTab(Class<? extends Fragment> clazz){
        state = initState();
        if(!isfirest){
            mCurrentFragment = mFragments.get(state);
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.add(R.id.content, mCurrentFragment, clazz.getSimpleName());
            transaction.show(mCurrentFragment);
            transaction.commit();
            isfirest  = false;
        }else{
        showAndHide(R.id.content, clazz);}
    }

    protected void showAndHide(int contentId, Class<? extends Fragment> clazz) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        Fragment showFragment = getSupportFragmentManager().findFragmentByTag(clazz.getSimpleName());//判断fragment有没有添加过

        if(showFragment == null){
            try {
                Logger.e(TAG,"showFragment 为空");
                //通过无参的 公开的构造函数来创建Fragment实例
                showFragment = clazz.newInstance();
                //当前的Fragment没有添加过 把Fragment添加到manager里面
                transaction.add(contentId, showFragment, clazz.getSimpleName());

                if(mCurrentFragment.getClass() != clazz){
                    //隐藏当前的Fragment
                    transaction.hide(mCurrentFragment);
                    //让记录当前的fragment赋值为显示的fragment
                    Logger.e(TAG,"mCurrentFragment："+ mCurrentFragment.getClass().getSimpleName() +"/n showFragment："+showFragment.getClass().getSimpleName());
                    mCurrentFragment = showFragment;
                }
                transaction.show(mCurrentFragment);
                Logger.e(TAG,"mCurrentFragment："+ mCurrentFragment.getClass().getSimpleName() +"showFragment："+showFragment.getClass().getSimpleName());
                transaction.commit();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else{
           Logger.e(TAG,"showFragment 不为空");
            if(mCurrentFragment.getClass() != clazz){
                //隐藏当前的fragment
                transaction.hide(mCurrentFragment);
                //让记录当前的fragment赋值为显示的fragment
               Logger.e(TAG,"currentFragment："+mCurrentFragment.getClass().getSimpleName()+"showFragment："+showFragment.getClass().getSimpleName());
                mCurrentFragment = showFragment;
            }
         Logger.e(TAG,"currentFragment："+mCurrentFragment.getClass().getSimpleName()+"showFragment："+showFragment.getClass().getSimpleName());
            transaction.show(mCurrentFragment); //显示需要的fragment
            transaction.commit();

    }}

    private class OnTabSelectedListener implements TabLayout.OnTabSelectedListener {
        @Override
        public void onTabSelected(TabLayout.Tab tab) {

             //   setCurrentTab(mCurrentFragment.getClass());选择相应的fragment
            setCurrentTab(mFragments.get(tab.getPosition()).getClass());
        }

        @Override
        public void onTabUnselected(TabLayout.Tab tab) {
        }

        @Override
        public void onTabReselected(TabLayout.Tab tab) {
        }
    }

    protected abstract int initState();

    protected abstract String initTitle();

    protected abstract List<String> initTabTitles();

    protected abstract List<Fragment> initFragments();
}