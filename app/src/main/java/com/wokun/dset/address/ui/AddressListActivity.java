package com.wokun.dset.address.ui;

import android.content.Intent;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.shantoo.widget.recyclerview.MItemDecoration;
import com.shantoo.widget.toolbar.WidgetBar;
import com.wokun.dset.DsetApp;
import com.wokun.dset.R;
import com.wokun.dset.address.AddressMgr;
import com.wokun.dset.address.adapter.AddressAdapter;
import com.wokun.dset.address.bean.AddressListBean;
import com.wokun.dset.base.BaseBindingActivity;
import com.wokun.dset.model.Constants;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.OnClick;

//收货地址
public class AddressListActivity extends BaseBindingActivity {

    @BindString(R.string.tysl_user_address) String title;

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    private AddressAdapter mAdapter;

    private static final int REQUEST_ADD_ADDRESS = 1;
    private static final int REQUEST_EDIT_ADDRESS = 2;

    @Override
    public int createView() {
        return R.layout.activity_ucenter_shipping_address;
    }

    @Override
    public WidgetBar createToolBar() {
        return mWidgetBar.setWidgetBarTitle(title);
    }

    @Override
    public void init() {
        initRecyclerView();
        AddressMgr.getInstance().loadAddressList(mAdapter);
    }

    private void initRecyclerView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addItemDecoration(new MItemDecoration(this, DividerItemDecoration.VERTICAL));

        mAdapter = new AddressAdapter(R.layout.item_shipping_address, null);
        mAdapter.setEmptyView(R.layout.layout_no_address_view,(ViewGroup) mRecyclerView.getParent());

        mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

                AddressListBean.AddressBean data = (AddressListBean.AddressBean) adapter.getData().get(position);

                if(R.id.action_set_normal_address == view.getId()){
                    onSetNormalAddress(data);
                }else if(R.id.action_edit_address == view.getId()){
                    onEditAddress(data);
                }else if(R.id.action_delete_address == view.getId()){
                    onDeleteAddress(data);
                }
            }
        });

        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
               String requestCode = getIntent().getStringExtra(Constants.REQUEST_CODE);
                if(!TextUtils.isEmpty(requestCode)){
                    Intent data = new Intent();
                    AddressListBean.AddressBean address = (AddressListBean.AddressBean) adapter.getData().get(position);
                    data.putExtra(Constants.CONTACTS, address.getName());
                    data.putExtra(Constants.TEL, address.getPhone());
                    data.putExtra(Constants.PROVINCE_ID, address.getProvice());
                    data.putExtra(Constants.ADDRESS, address.getProvice() + address.getCity() + address.getArea()+ address.getAddress());
                    setResult(20, data);
                    finish();
                }
            }
        });
        mRecyclerView.setAdapter(mAdapter);
    }

    @OnClick(R.id.action_add_address)
    public void addAddress(View v) {
        onAddAddress();
    }

    //添加地址
    private void onAddAddress(){
        Intent intent = new Intent();
        intent.setClass(this, AddressAddActivity.class);
        startActivityForResult(intent, REQUEST_ADD_ADDRESS);
      startActivityForResult(intent, REQUEST_ADD_ADDRESS);
    }

    //设置默认地址
    private void onSetNormalAddress(AddressListBean.AddressBean bean) {
        AddressMgr.getInstance().setDefaultAddress(mAdapter,  bean.getId());
    }

    //删除地址
    private void onDeleteAddress(AddressListBean.AddressBean bean) {
        AddressMgr.getInstance().deleteAddress(mAdapter, bean.getId());
    }

    //编辑地址
    private void onEditAddress(AddressListBean.AddressBean bean) {
       Intent intent = new Intent();
        intent.putExtra(Constants.ADDRESS_ID, bean.getId());
        intent.putExtra(Constants.CONTACTS, bean.getName());
        intent.putExtra(Constants.TEL, bean.getPhone());
        intent.putExtra(Constants.AREA, bean.getProvice() + bean.getCity() + bean.getArea());
        intent.putExtra(Constants.PROVINCE_ID, bean.getProvice());
        intent.putExtra(Constants.CITY_ID, bean.getCity());
        intent.putExtra(Constants.DISTRICT_ID, bean.getArea());
        intent.putExtra(Constants.ADDRESS, bean.getAddress());
        intent.setClass(this, AddressEditActivity.class);
        startActivityForResult(intent, REQUEST_EDIT_ADDRESS, !DsetApp.getInstance().isLogin());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_ADD_ADDRESS || requestCode == REQUEST_EDIT_ADDRESS) {
            AddressMgr.getInstance().loadAddressList(mAdapter);
        }
    }
}
