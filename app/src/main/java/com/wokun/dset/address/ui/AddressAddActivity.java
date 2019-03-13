package com.wokun.dset.address.ui;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.shantoo.widget.toolbar.WidgetBar;
import com.wokun.dset.R;
import com.wokun.dset.address.AddressMgr;
import com.wokun.dset.base.BaseLevelThreeLinkageActivity;


import butterknife.BindString;
import butterknife.BindView;
import butterknife.OnClick;

//添加用户地址
public class AddressAddActivity extends BaseLevelThreeLinkageActivity implements BaseLevelThreeLinkageActivity.OnAddressSelectedListener {

    @BindString(R.string.tysl_user_add_address) String title;

    @BindView(R.id.toolbar)
    WidgetBar widgetBar;

    @BindView(R.id.et_contacts) EditText etContacts;
    @BindView(R.id.et_mobile) EditText etMobile;
    @BindView(R.id.et_address) EditText etAddress;

    @BindView(R.id.area) TextView tvArea;

    private String mProvinceCode= "北京";
    private String mCityCode="北京市";
    private String mDistrictCode="东城区";
    @Override
    public int createView() {
        return R.layout.activity_address_add;
    }

    @Override
    public WidgetBar createToolBar() {
        return widgetBar.setWidgetBarTitle(title);
    }

    @Override
    public void init() {
         setOnAddressSelectedListener(this);
    }

    @OnClick({R.id.action_submit, R.id.action_selector_area})
    public void action(View v) {
        if(v.getId() == R.id.action_submit){

            String contacts = etContacts.getText().toString().trim();
            String mobile = etMobile.getText().toString().trim();
            String address = etAddress.getText().toString().trim();
            AddressMgr.getInstance().addAddress(this,contacts,mobile,mProvinceCode,mCityCode,mDistrictCode,address);

        }else if(v.getId() == R.id.action_selector_area){

            showPickerView();
        }
    }

    @Override
    public void onAddressSelected(String address, String provinceCode, String cityCode, String districtCode) {
        tvArea.setText(address);
        mProvinceCode = provinceCode;
        mCityCode = cityCode;
        mDistrictCode = districtCode;
    }
}