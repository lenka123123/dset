package com.wokun.dset.mainfragment;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.JsonObject;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.shantoo.common.utils.JSONUtil;
import com.shantoo.common.utils.Logger;
import com.shantoo.common.utils.ResourceUtil;
import com.shantoo.widget.recyclerview.MItemDecoration;
import com.shantoo.widget.toast.RxToast;
import com.wokun.dset.DsetApp;
import com.wokun.dset.R;
import com.wokun.dset.base.BaseFragment;
import com.wokun.dset.callback.JsonCallback;
import com.wokun.dset.login.LoginMgr;
import com.wokun.dset.model.Constants;
import com.wokun.dset.response.BaseResponse;
import com.wokun.dset.store.adapter.ShopCartAdapter;
import com.wokun.dset.store.bean.CartList;
import com.wokun.dset.store.dstore.immediatelypay.DStoreImmediatelyPayActivity;
import com.wokun.dset.utils.DecimalUtil;
import com.wokun.dset.utils.ImageLoader;
import com.wokun.dset.utils.JosnFrom;
import com.wokun.dset.utils.SpCommonUtils;
import com.wokun.dset.utils.StringUtil;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.OnClick;

import static com.wokun.dset.utils.MD5.ParameterUtils.removeEmptyData;
import static com.wokun.dset.utils.MD5.ParameterUtils.sortMapByKey;

/**
 * Created by Administrator on 2019\1\14 0014.
 */

public class ShopCartFragment extends BaseFragment {

    @BindString(R.string.tysl_shop_cart)
    String title;
    @BindView(R.id.action_edit)
    TextView actionEdit;
    @BindView(R.id.status_bar)
    View statusBar;
    @BindView(R.id.toolbar)
    RelativeLayout mWidgetBar;
    @BindView(R.id.tv_title)
    TextView tvTitle;

    //    @BindView(R.id.img_ad)ImageView img_ad;
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    //    @BindView(R.id.recyclerView) RecyclerView mRecyclerView2;
    @BindView(R.id.tv_total_price)
    TextView tvTotalPrice;//合计总价
    @BindView(R.id.action_select_all)
    TextView actionSelectAll;
    @BindView(R.id.action_settle_accounts)
    TextView actionSettleAccounts;
    @BindView(R.id.ll_bottom)
    LinearLayout llBottom;

    private int action = -1;
    private final static int EDIT = 0;
    private final static int COMPLETE = 1;

    private boolean isCreated = false;
    private boolean mSelect;
    private double mTotalPrice = 0;
    private ShopCartAdapter mAdapter;
    private List<Integer> mCartIdList = new ArrayList<>();
    private List<CartList.DataBean.CartListInfoBean.GoodsItemBean> mGoPayList = new ArrayList<>();
    private List<CartList.DataBean.CartListInfoBean.GoodsItemBean> mAllOrderList = new ArrayList<>();

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()) { //界面可见
            if (isCreated) getShopCartList();
        } else {  //界面不可见 相当于 onpause
        }
    }

    @Override
    public int createView() {
        return R.layout.fragment_shopping_cart;
    }

    @Override
    public void initViews() {
        isCreated = true;
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.height = ResourceUtil.getStatusBarHeight(getContext());
        statusBar.setLayoutParams(layoutParams);
        initData();

   /*     if (!TyslApp.getInstance().isLogin()) {
            Intent intent = new Intent();
            intent.setClass(TyslApp.getContext(), LoginActivity.class);
            startActivityForResult(intent, Constants.LOGIN_REQUEST_CODE);
        } else {
            getShopCartList();
        }*/


    }

    /*  @Override
      public void setUserVisibleHint(boolean isVisibleToUser) {
          super.setUserVisibleHint(isVisibleToUser);
          loadData();
      }
  */
    private void initData() {
        getShopCartList();
    }

    @Override
    public void initToolBar() {
        action = EDIT;

 /*      mWidgetBar.setWidgetBarTitle(title)
                .setMenu("编辑",null)
                .setOnMenuClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        actionEdit();
                    }
                },null);*/
    }


    /**
     * 去结算
     */
    @OnClick(R.id.action_settle_accounts)
    public void actionSettleAccounts(View v) {
//        if (!TyslApp.getInstance().isLogin()) {
//            startActivity(LoginActivity.class);
//            return;
//        }
        if (mAllOrderList == null || mAllOrderList.size() == 0) {
            RxToast.showShort("购物车数量为空!");
            return;
        }

        Intent intent = new Intent(getContext(), DStoreImmediatelyPayActivity.class);
        intent.putExtra("list", (Serializable) mGoPayList);
        intent.putExtra("doubleprice", mTotalPrice);
        startActivity(intent);


//        for (CartList.DataBean.CartListInfoBean.GoodsItemBean goods : mGoPayList) {
//            CartList.DataBean.CartListInfoBean cartInfo = new CartList.DataBean.CartListInfoBean();
//            cartInfo.setCart_id(goods.getCartId());
//            cartInfo.setGoods_id(goods.getGoodsId());
//            cartInfo.setGoods_num(goods.getGoodsNum());
//            cartInfoList.add(cartInfo);
//        }
//        if (cartInfoList != null && mTotalPrice > 0) {
//            cartUpdate(cartInfoList, mTotalPrice);
//        }


    }

    @Override
    public void loadData() {


        mAdapter = new ShopCartAdapter(R.layout.item_shop_cart, null);
        mAdapter.setOnEditClickListener(new ShopCartAdapter.OnEditClickListener() {
            @Override
            public void onEditClick(CartList.DataBean.CartListInfoBean.GoodsItemBean itemBean, int count) {
                upDataGoodsNum(itemBean.getCart_id(), itemBean.getSku_id(), count + "");
            }
        });

        // mAdapter.setEmptyView(R.layout.layout_no_data_shop_cart_view, (ViewGroup) mRecyclerView.getParent());
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.addItemDecoration(new MItemDecoration(getContext(), DividerItemDecoration.VERTICAL));

        //删除商品接口
        mAdapter.setOnDeleteClickListener(new ShopCartAdapter.OnDeleteClickListener() {
            @Override
            public void onDeleteClick(View v, int position, int cartId) {
                deleteGoods(cartId + "");

                mCartIdList.add(cartId);
                mAdapter.notifyDataSetChanged();
            }
        });
        //实时监控全选按钮
        mAdapter.setRefreshListener(new ShopCartAdapter.OnRefreshListener() {
            @Override
            public void onRefresh(boolean isSelect) {
                mSelect = isSelect;
                if (isSelect) {
                    Drawable left = getResources().getDrawable(R.drawable.ic_selected);
                    actionSelectAll.setCompoundDrawablesWithIntrinsicBounds(left, null, null, null);
                } else {
                    Drawable left = getResources().getDrawable(R.drawable.ic_un_selected);
                    actionSelectAll.setCompoundDrawablesWithIntrinsicBounds(left, null, null, null);
                }
                mTotalPrice = 0;
                int mTotalNum = 0;
                mGoPayList.clear();
                for (int i = 0; i < mAllOrderList.size(); i++)
                    if (mAllOrderList.get(i).isSelect()) {
                        double v = DecimalUtil.mul(Double.valueOf(mAllOrderList.get(i).getPrice()), Integer.valueOf(mAllOrderList.get(i).getNum()));
                        mTotalPrice = DecimalUtil.add(mTotalPrice, v);
                        mTotalNum += 1;

                        mGoPayList.add(mAllOrderList.get(i));
                    }
                tvTotalPrice.setText("¥ " + mTotalPrice + "");
                actionSettleAccounts.setText("去结算(" + mTotalNum + ")");
            }
        });
        actionSelectAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSelect = !mSelect;
                if (mSelect) {
                    Drawable left = getResources().getDrawable(R.drawable.ic_selected);
                    actionSelectAll.setCompoundDrawablesWithIntrinsicBounds(left, null, null, null);
                    for (int i = 0; i < mAllOrderList.size(); i++) {
                        mAllOrderList.get(i).setSelect(true);
                        mAllOrderList.get(i).setShopSelect(true);
                    }
                } else {
                    Drawable left = getResources().getDrawable(R.drawable.ic_un_selected);
                    actionSelectAll.setCompoundDrawablesWithIntrinsicBounds(left, null, null, null);
                    for (int i = 0; i < mAllOrderList.size(); i++) {
                        mAllOrderList.get(i).setSelect(false);
                        mAllOrderList.get(i).setShopSelect(false);
                    }
                }
                mAdapter.notifyDataSetChanged();
            }
        });


    }


    @Override
    public void onResume() {
        super.onResume();


/*        if (!TyslApp.getInstance().isLogin()) {
            Intent intent = new Intent();
            intent.setClass(TyslApp.getContext(), LoginActivity.class);
            startActivityForResult(intent, Constants.LOGIN_REQUEST_CODE);
        } else {
            getShopCartList();}*/

        llBottom.setVisibility(View.VISIBLE);
        mRecyclerView.setVisibility(View.VISIBLE);
//
//        if (TyslApp.getInstance().isLogin() && TyslApp.getInstance().isRefreshShopCart()) {
//            //Logger.e(TAG,"ShopCart onResume 已登录 并可以刷新");
//            getShopCartList();
//            llBottom.setVisibility(View.VISIBLE);
//            mRecyclerView.setVisibility(View.VISIBLE);
//        } else if (TyslApp.getInstance().isLogin()) {
//            //Logger.e(TAG,"ShopCart onResume 已登录 单不可以刷新");
//            getShopCartList();
//            llBottom.setVisibility(View.VISIBLE);
//            mRecyclerView.setVisibility(View.VISIBLE);
//        } else if (!TyslApp.getInstance().isLogin()) {
//            llBottom.setVisibility(View.GONE);
//            mRecyclerView.setVisibility(View.GONE);
//            TyslApp.getInstance().setRefreshShopCart(false);
//        }
    }

    public static void isSelectFirst(List<CartList.DataBean.CartListInfoBean.GoodsItemBean> list) {
        if (list.size() > 0) {
            list.get(0).setIsFirst(1);
            for (int i = 1; i < list.size(); i++) {
                if (list.get(i).getGoods_id().equals(list.get(i - 1).getGoods_id())) {
                    list.get(i).setIsFirst(2);
                } else {
                    list.get(i).setIsFirst(1);
                }
            }
        }
    }

    /**
     * 购物车编辑功能
     */
    @OnClick(R.id.action_edit)
    public void actionEdit() {
//        if (!TyslApp.getInstance().isLogin()) {
//            startActivity(LoginActivity.class);
//            return;
//        }
        if (action == EDIT) {
            List<TextView> list = mAdapter.getDeleteList();
            if (list == null) return;
            for (TextView t : list) {
                t.setVisibility(View.VISIBLE);
            }
            tvTitle.setText("完成", null);
            action = COMPLETE;
        } else if (action == COMPLETE) {
            if (mCartIdList == null) return;
            int[] arr = new int[mCartIdList.size()];
            if (mCartIdList != null) {
                for (int i = 0; i < mCartIdList.size(); i++) {
                    arr[i] = mCartIdList.get(i);
                }
            }
            deleteGoods(JSONUtil.toJSON(arr));
            List<TextView> list = mAdapter.getDeleteList();
            for (TextView t : list) {
                t.setVisibility(View.GONE);
            }
            tvTitle.setText("编辑", null);
            action = EDIT;
        }
    }

    /**
     * 购物车删除商品
     */
    private void deleteGoods(String cart_id_str) {
        String token = (String) SpCommonUtils.get(getContext(), Constants.TOKEN, "");
        String user_id = (String) SpCommonUtils.get(getContext(), Constants.USERID, "");
        String timestamp = StringUtil.getCurrentTime();
        Map params = new HashMap();
        params.put("user_id", user_id);
        params.put("token", token);
        params.put("timestamp", timestamp);
        params.put("cart_id_str", cart_id_str);

        final Map<String, String> removeMap = removeEmptyData(params);
        Map<String, String> resultMap = sortMapByKey(removeMap);
        String sign = LoginMgr.getInstance().getSign(removeMap, resultMap, params);

        OkGo.<JsonObject>post(Constants.BASE_URL + Constants.TELATE_GOODS)
                .tag(this)
                .params("sign", sign)
                .params("timestamp", timestamp)
                .params("user_id", user_id)
                .params("token", token)
                .params("cart_id_str", cart_id_str)
                .execute(new JsonCallback<JsonObject>() {
                    @Override
                    public void onSuccess(Response<JsonObject> response) {
                        BaseResponse baseResponse = (BaseResponse) JosnFrom.getInstance().getObj(response.body().toString(), BaseResponse.class);
                        if (baseResponse != null && baseResponse.getStatus().equals("0001")) {
                        } else {
                            RxToast.showShort(baseResponse.getMessage());
                        }
                    }

                    @Override
                    public void onError(Response response) {
//                        dismissLP();
                        super.onError(response);
                        Log.e("user", response + "!!!!");
                        DsetApp.getInstance().setRefreshShopCart(false);
                    }
                });
    }

    /**
     * 购物车去结算
     */
//    public void cartUpdate(List<CartListInfoBean> list, final double order_total) {
//        CartListInfoBean[] arr = new CartListInfoBean[list.size()];
//        for (int i = 0; i < list.size(); i++) {
//            arr[i] = list.get(i);
//        }
//        cartInfoList.clear();
//        Log.e("shangping订单", JSONUtil.toJSON(arr) + "!!!" + order_total);
//        OkGo.<BaseResponse<SingleParam>>post(Constants.BASE_URL + Constants.CART_UPDATE_CART_URL)
//                .tag(this)
//                .params(Constants.CART_INFO, JSONUtil.toJSON(arr))
//                .params(Constants.ORDER_TOTAL, order_total)
//                .execute(new DialogCallback<BaseResponse<SingleParam>>(getActivity(), Constants.WITH_TOKEN, Constants.CART_UPDATE_CART_URL) {
//                    @Override
//                    public void onSuccess(Response<BaseResponse<SingleParam>> response) {
//                        BaseResponse body = response.body();
//                        Log.e("shangping订单", body + "");
//                        if (body == null) return;
//                        mTotalPrice = 0;
//                        if (body.isState()) {
//                            SingleParam data = (SingleParam) body.getData();
//                            Intent intent = new Intent();
//                            intent.putExtra(Constants.CART_ID_STR, data.getCart_id_str());
//                            intent.putExtra(Constants.ORDER_TOTAL, order_total);
//                            Log.e("订单", data.getCart_id_str() + "//%%" + order_total);
//
//                            intent.setClass(getContext(), GoodsOrderConfirmationActivity.class);
//                            startActivity(intent);
//                        }
//                        RxToast.showShort(body.getMsg());
//                    }
//
//                    @Override
//                    public void onError(Response<BaseResponse<SingleParam>> response) {
//                        super.onError(response);
//                        Log.e("订单", response.message() + "");
//
//                    }
//                });
//    }
    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            //StatusBarCompat.setStatusBarColor(getActivity(), ContextCompat.getColor(getContext(), R.color.colorAccent));
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        OkGo.getInstance().cancelTag(this);
    }


    //获取购物车列表
    private void getShopCartList() {
        String token = (String) SpCommonUtils.get(getContext(), Constants.TOKEN, "");
        String user_id = (String) SpCommonUtils.get(getContext(), Constants.USERID, "");
        String timestamp = StringUtil.getCurrentTime();
        Map params = new HashMap();

        params.put("user_id", user_id);
        params.put("token", token);
        params.put("timestamp", timestamp);

        final Map<String, String> removeMap = removeEmptyData(params);
        Map<String, String> resultMap = sortMapByKey(removeMap);
        String sign = LoginMgr.getInstance().getSign(removeMap, resultMap, params);

        OkGo.<JsonObject>post(Constants.BASE_URL + Constants.GOODS_CART_LIST)
                .tag(this)
                .params("sign", sign)
                .params("timestamp", timestamp)
                .params("user_id", user_id)
                .params("token", token)
                .execute(new JsonCallback<JsonObject>() {
                    @Override
                    public void onSuccess(Response<JsonObject> response) {
                        CartList cartList = (CartList) JosnFrom.getInstance().getObj(response.body().toString(), CartList.class);
                        if (cartList != null && cartList.getStatus().equals("0001")) {
                            List<CartList.DataBean.CartListInfoBean.GoodsItemBean> list1 = new ArrayList<>();
                            List<CartList.DataBean.CartListInfoBean.GoodsItemBean> list2 = new ArrayList<>();
                            if (cartList.getData().getCartListInfo() != null) {
                                for (CartList.DataBean.CartListInfoBean store : cartList.getData().getCartListInfo()) {
                                    if (store.getGoods_item() != null) {
                                        for (CartList.DataBean.CartListInfoBean.GoodsItemBean goods : store.getGoods_item()) {
                                            goods.setStore_name(store.getShop_name());
                                            list2.add(goods);
                                        }
                                    }
                                    list1.addAll(list2);
                                }
                                mAllOrderList = list1;
                                mAdapter.setNewData(mAllOrderList);
                                mRecyclerView.setVisibility(View.VISIBLE);
                            }
                            isSelectFirst(list1);

                        }
                    }

                    @Override
                    public void onError(Response response) {
//                        dismissLP();
                        super.onError(response);
                        Log.e("user", response + "!!!!");
                        DsetApp.getInstance().setRefreshShopCart(false);
                    }
                });
    }

    //修改购物车商品数量
    private void upDataGoodsNum(String cart_id, String sku_id, String nums) {
        String token = (String) SpCommonUtils.get(getContext(), Constants.TOKEN, "");
        String user_id = (String) SpCommonUtils.get(getContext(), Constants.USERID, "");
        String timestamp = StringUtil.getCurrentTime();
        Map params = new HashMap();

        params.put("user_id", user_id);
        params.put("token", token);
        params.put("timestamp", timestamp);
        params.put("cart_id", cart_id);
        params.put("sku_id", sku_id);
        params.put("nums", nums);

        final Map<String, String> removeMap = removeEmptyData(params);
        Map<String, String> resultMap = sortMapByKey(removeMap);
        String sign = LoginMgr.getInstance().getSign(removeMap, resultMap, params);

        OkGo.<JsonObject>post(Constants.BASE_URL + Constants.UPDATA_GOODS_NUM)
                .tag(this)
                .params("sign", sign)
                .params("timestamp", timestamp)
                .params("user_id", user_id)
                .params("token", token)

                .params("cart_id", cart_id)
                .params("sku_id", sku_id)
                .params("nums", nums)

                .execute(new JsonCallback<JsonObject>() {
                    @Override
                    public void onSuccess(Response<JsonObject> response) {
                        BaseResponse baseResponse = (BaseResponse) JosnFrom.getInstance().getObj(response.body().toString(), BaseResponse.class);
                        if (baseResponse != null && baseResponse.getStatus().equals("0001")) {
                        } else {
                            RxToast.showShort(baseResponse.getMessage());
                        }
                    }

                    @Override
                    public void onError(Response response) {
//                        dismissLP();
                        super.onError(response);
                        Log.e("user", response + "!!!!");
                        DsetApp.getInstance().setRefreshShopCart(false);
                    }
                });
    }


    /**
     * 获取购物车数据列表
     */
//    private void getShopCartList() {
//        //mMultipleStatusView.showLoading();
//        OkGo.<BaseResponse<CartList>>post(Constants.BASE_URL + Constants.CART_LIST_URL)
//                .tag(this)
//                .execute(new JsonCallback<BaseResponse<CartList>>(Constants.WITH_TOKEN, Constants.CART_LIST_URL) {
//                    @Override
//                    public void onSuccess(Response<BaseResponse<CartList>> response) {
//                        TyslApp.getInstance().setRefreshShopCart(false);
//                        BaseResponse body = response.body();
//                        if (body == null) return;
//                        if (body.isState()) {
//                            //mMultipleStatusView.showContent();
//                            CartList data = (CartList) body.getData();
//                            if (data == null) {
//                                return;
//                            }
//                            ImageLoader.loadImage(data.getAd(), img_ad);
//
//                            if (data.getCartList() == null) {
//                                //mMultipleStatusView.showEmpty();
//                            }
//                            List<CartList.DataBean.CartListInfoBean.GoodsItemBean> list1 = new ArrayList<>();
//                            List<CartList.DataBean.CartListInfoBean.GoodsItemBean> list2 = new ArrayList<>();
//                            if (data.getCartList() != null) {
//                                for (Store store : data.getCartList()) {
//                                    if (store.getGoods_list() != null) {
//                                        for (CartList.DataBean.CartListInfoBean.GoodsItemBean goods : store.getGoods_list()) {
//                                            goods.setStoreName(store.getStore_name());
//                                            list2.add(goods);
//                                        }
//                                    }
//                                    list1.addAll(list2);
//                                }
//                                mAllOrderList = list1;
//                                mAdapter.setNewData(mAllOrderList);
//                                mRecyclerView.setVisibility(View.VISIBLE);
//                            }
//                            isSelectFirst(list1);
//                        }
//                    }
//
//                    @Override
//                    public void onError(Response<BaseResponse<CartList>> response) {
//                        super.onError(response);
//                        if (ShopCartFragment.this.isVisible()) {
//                            dismissLoadingProgress();
//                        }
//                        TyslApp.getInstance().setRefreshShopCart(false);
//                    }
//                });
//    }

}
