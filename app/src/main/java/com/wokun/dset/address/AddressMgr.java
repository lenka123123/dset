package com.wokun.dset.address;

import android.app.Activity;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.shantoo.widget.toast.RxToast;
import com.wokun.dset.DsetApp;
import com.wokun.dset.address.bean.AddressListBean;
import com.wokun.dset.callback.JsonCallback;
import com.wokun.dset.login.LoginMgr;
import com.wokun.dset.model.Constants;
import com.wokun.dset.response.BaseResponse;
import com.wokun.dset.utils.StringUtil;

import java.util.HashMap;
import java.util.Map;

import static com.wokun.dset.utils.MD5.ParameterUtils.removeEmptyData;
import static com.wokun.dset.utils.MD5.ParameterUtils.sortMapByKey;


public class AddressMgr {

    private AddressMgr(){

    }

    private static class AddressMgrHolder{
        private static AddressMgr instance = new AddressMgr();
    }

    public static AddressMgr getInstance(){
        return AddressMgrHolder.instance;
    }

    /**
     * 加载收货地址列表
     * @param adapter BaseQuickAdapter
     *                ADDRESS_LIST
     *                AddressListBean
     */
    public void loadAddressList(final BaseQuickAdapter adapter) {
        Map params = new HashMap();
        params.put("timestamp", StringUtil.getCurrentTime());
        Map<String, String> removeMap = removeEmptyData(params);
        Map<String, String> resultMap = sortMapByKey(removeMap);
        String sign = LoginMgr.getInstance().getSign(removeMap, resultMap, params);
        OkGo.<BaseResponse<AddressListBean>>post(Constants.BASE_URL + Constants.ADDRESS_LIST)
                .tag(this)
                .params("sign", sign)
                .params("timestamp", StringUtil.getCurrentTime())
                .execute(new JsonCallback<BaseResponse<AddressListBean>>() {
                    @Override
                    public void onSuccess(Response<BaseResponse<AddressListBean>> response) {
                        BaseResponse body = response.body();
                        if (body == null) return;
                        Log.e("地址加载", "进来了2!!!!");
                        if (body.getStatus().equals("0001")) {
                            RxToast.showShort(body.getMessage());
                            AddressListBean data = (AddressListBean) body.getData();
                            adapter.setNewData(data.getMyaddress());

                        } else if (body.getStatus().equals("0002")) {
                            RxToast.showShort(body.getMessage());

                        } else if (body.getStatus().equals("0003")) {
                            RxToast.showShort(body.getMessage());

                        }
                    }

                    @Override
                    public void onError(Response response) {
                        super.onError(response);
                        Log.e("首页3",response+"!!!!");
                        // DsetApp.getInstance().setRefreshShopCart(false);
                    }
                });
    }

    /**
     * 获取默认收货地址
     * @param contacts TextView
     * @param orderPlacerTel TextView
     * @param orderPlacerAddress TextView
     */
   /* public void getDefaultAddress(final TextView contacts, final TextView orderPlacerTel, final TextView orderPlacerAddress) {
        OkGo.<BaseResponse<AddressBean>>post(Constants.BASE_URL + Constants.ADDRESS_GET_DEFAULT_URL)
                .execute(new JsonCallback<BaseResponse<AddressBean>>(Constants.WITH_TOKEN,Constants.ADDRESS_GET_DEFAULT_URL) {
                    @Override
                    public void onSuccess(Response<BaseResponse<AddressBean>> response) {
                        BaseResponse body = response.body();
                        if(body == null)return;
                     *//*   if (body.isState()) {
                            AddressBean bean = (AddressBean) body.getData();
                            contacts.setText(bean.getContacts());
                            orderPlacerTel.setText(bean.getTel());
                            String province = bean.getProvince()==null?"":bean.getProvince();
                            String city = bean.getCity()==null?"":bean.getCity();
                            String district = bean.getDistrict()==null?"":bean.getDistrict();
                            String address = bean.getAddress()==null?"":bean.getAddress();
                            String str = province + city + district + address;
                            orderPlacerAddress.setText(str);
                        }*//*
                    }
                });
    }
*/
    /**
     * 设置默认收货地址
     * @param  addressId
     */
    public void setDefaultAddress(final BaseQuickAdapter adapter,String addressId) {
        Map params = new HashMap();
        params.put("timestamp", StringUtil.getCurrentTime());
        params.put("id", addressId);
        Map<String, String> removeMap = removeEmptyData(params);
        Map<String, String> resultMap = sortMapByKey(removeMap);
        String sign = LoginMgr.getInstance().getSign(removeMap, resultMap, params);
        OkGo.<BaseResponse<Object>>post(Constants.BASE_URL + Constants.SET_DEFAULT)
                .tag(this)
                .params("sign", sign)
                .params("timestamp", StringUtil.getCurrentTime())
                .params("id", addressId)
                .execute(new JsonCallback<BaseResponse<Object>>() {
                    @Override
                    public void onSuccess(Response<BaseResponse<Object>> response) {
                        BaseResponse body = response.body();
                        if (body == null) return;
                        Log.e("设置默认地址", "进来了2!!!!");
                        if (body.getStatus().equals("0001")) {
                            loadAddressList(adapter);
                            RxToast.showShort(body.getMessage());
                        } else if (body.getStatus().equals("0002")) {
                            RxToast.showShort(body.getMessage());

                        } else if (body.getStatus().equals("0003")) {
                            RxToast.showShort(body.getMessage());

                        }
                    }

                    @Override
                    public void onError(Response response) {
                        super.onError(response);
                        Log.e("首页3",response+"!!!!");
                        // DsetApp.getInstance().setRefreshShopCart(false);
                    }
                });
    }

    /**
     * 删除收货地址
     * @param addressId 地址ID
     */
   public void deleteAddress(final BaseQuickAdapter adapter, String addressId) {
       Map params = new HashMap();
       params.put("timestamp", StringUtil.getCurrentTime());
       params.put("id", addressId);
       Map<String, String> removeMap = removeEmptyData(params);
       Map<String, String> resultMap = sortMapByKey(removeMap);
       String sign = LoginMgr.getInstance().getSign(removeMap, resultMap, params);
       OkGo.<BaseResponse<Object>>post(Constants.BASE_URL + Constants.DELETE_ADDRESS)
               .tag(this)
               .params("sign", sign)
               .params("timestamp", StringUtil.getCurrentTime())
               .params("id", addressId)
               .execute(new JsonCallback<BaseResponse<Object>>() {
                   @Override
                   public void onSuccess(Response<BaseResponse<Object>> response) {
                       BaseResponse body = response.body();
                       if (body == null) return;
                       Log.e("删除地址", "进来了2!!!!");
                       if (body.getStatus().equals("0001")) {
                           RxToast.showShort(body.getMessage());
                           loadAddressList(adapter);
                       } else if (body.getStatus().equals("0002")) {
                           RxToast.showShort(body.getMessage());

                       } else if (body.getStatus().equals("0003")) {
                           RxToast.showShort(body.getMessage());

                       }
                   }

                   @Override
                   public void onError(Response response) {
                       super.onError(response);
                       Log.e("首页3",response+"!!!!");
                       // DsetApp.getInstance().setRefreshShopCart(false);
                   }
               });

    }

    /**
     * 新增用戶地址
     *
     * @param contacts   联系人
     * @param mobile     电话
     * @param provinceId 省份ID
     * @param cityId     城市ID
     * @param districtId 区ID
     * @param address    详细地址
     */
  public void addAddress(final Activity activity, String contacts, String mobile, String provinceId, String cityId, String districtId, String address) {
        if (TextUtils.isEmpty(contacts) || TextUtils.isEmpty(mobile) || TextUtils.isEmpty(address)) {
            Toast.makeText(DsetApp.getContext(), "填写信息不完整", Toast.LENGTH_SHORT).show();
            return;
        }
      Map params = new HashMap();
      params.put("timestamp", StringUtil.getCurrentTime());
      params.put("name", contacts);
      params.put("phone", mobile);
      params.put("provice", provinceId);
      params.put("city", cityId);
      params.put("area", districtId);
      params.put("address", address);
      params.put("is_default", "0");
      Map<String, String> removeMap = removeEmptyData(params);
      Map<String, String> resultMap = sortMapByKey(removeMap);
      String sign = LoginMgr.getInstance().getSign(removeMap, resultMap, params);
      //   Log.e("修改忘记密码信息","修改密码信息："+phoneNum+mobilecode+newpwdagain);
      OkGo.<BaseResponse<Object>>post(Constants.BASE_URL + Constants.ADD_ADDRESS)
              .tag(this)
              .params("sign", sign)
              .params("timestamp", StringUtil.getCurrentTime())
              .params("name", contacts)
              .params("phone", mobile)
              .params("provice", provinceId)
              .params("city", cityId)
              .params("area", districtId)
              .params("address", address)
              .params("is_default", "0")
              .execute(new JsonCallback<BaseResponse<Object>>() {
                  @Override
                  public void onSuccess(Response<BaseResponse<Object>> response) {
                      BaseResponse body = response.body();
                      if (body == null) return;
                      Log.e("地址添加", "进来了2!!!!");
                      if (body.getStatus().equals("0001")) {
                          RxToast.showShort(body.getMessage());
                          activity.finish();
                      }
                  }

                  @Override
                  public void onError(Response response) {
                      super.onError(response);
                      Log.e("首页3", response + "!!!!");
                      // DsetApp.getInstance().setRefreshShopCart(false);
                  }
              });
    }

    /**
     * 修改用戶地址  updatemyaddress
     * @param activity Activity
     * @param addressId 地址ID
     * @param contacts 联系人
     * @param mobile 电话
     * @param provinceId 省份ID
     * @param cityId 城市ID
     * @param districtId 区ID
     * @param address 详细地址
     */
   public void editAddress(final Activity activity, String addressId, String contacts, String mobile, String provinceId, String cityId, String districtId, String address) {
       if (TextUtils.isEmpty(addressId) || TextUtils.isEmpty(contacts) || TextUtils.isEmpty(mobile) || TextUtils.isEmpty(address) ) {
           Toast.makeText(DsetApp.getContext(), "填写信息不完整", Toast.LENGTH_SHORT).show();
           return;
       }
       Map params = new HashMap();
       params.put("timestamp", StringUtil.getCurrentTime());
       params.put("id", addressId);
       params.put("name", contacts);
       params.put("phone", mobile);
       params.put("provice", provinceId);
       params.put("city", cityId);
       params.put("area", districtId);
       params.put("address", address);
       params.put("is_default", "0");
       Map<String, String> removeMap = removeEmptyData(params);
       Map<String, String> resultMap = sortMapByKey(removeMap);
       String sign = LoginMgr.getInstance().getSign(removeMap, resultMap, params);
       //   Log.e("修改忘记密码信息","修改密码信息："+phoneNum+mobilecode+newpwdagain);
       OkGo.<BaseResponse<Object>>post(Constants.BASE_URL + Constants.UPDATE_ADDRESS)
               .tag(this)
               .params("sign", sign)
               .params("timestamp", StringUtil.getCurrentTime())
               .params("id", addressId)
               .params("name", contacts)
               .params("phone", mobile)
               .params("provice", provinceId)
               .params("city", cityId)
               .params("area", districtId)
               .params("address", address)
               .params("is_default", "0")
               .execute(new JsonCallback<BaseResponse<Object>>() {
                   @Override
                   public void onSuccess(Response<BaseResponse<Object>> response) {
                       BaseResponse body = response.body();
                       if (body == null) return;
                       Log.e("地址添加", "进来了2!!!!");
                       if (body.getStatus().equals("0001")) {
                           RxToast.showShort(body.getMessage());
                           activity.finish();
                       }
                   }

                   @Override
                   public void onError(Response response) {
                       super.onError(response);
                       Log.e("首页3", response + "!!!!");
                       // DsetApp.getInstance().setRefreshShopCart(false);
                   }
               });

   }

}