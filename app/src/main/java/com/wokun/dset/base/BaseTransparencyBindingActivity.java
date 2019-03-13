package com.wokun.dset.base;


import com.shantoo.common.utils.StatusBarUtil;
import com.shantoo.widget.utils.UITool;
import com.wokun.dset.R;


public abstract class BaseTransparencyBindingActivity extends BaseBindingActivity {

    @Override
    public void init() {
        StatusBarUtil.setColor(this, UITool.getColor(R.color.colorWhite));
        StatusBarUtil.setTransparent(this);
    }
}
