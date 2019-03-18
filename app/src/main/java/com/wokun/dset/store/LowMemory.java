package com.wokun.dset.store;

import android.content.ComponentCallbacks;
import android.content.res.Configuration;

import com.wokun.dset.login.LoginActivity;
import com.wokun.dset.utils.ToastUtil;

import cn.qqtheme.framework.util.LogUtils;

public class LowMemory implements ComponentCallbacks {


    @Override
    public void onConfigurationChanged(Configuration configuration) {
        LogUtils.debug("LowMemory========" + configuration.colorMode);


    }

    @Override
    public void onLowMemory() {
        LogUtils.debug("LowMemory========onLowMemory");
    }

}
