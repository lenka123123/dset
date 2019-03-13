package com.wokun.dset.ucenter.addcards;

import android.graphics.Color;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.shantoo.common.utils.UIUtil;
import com.shantoo.widget.toast.RxToast;
import com.shantoo.widget.toolbar.WidgetBar;
import com.wokun.dset.R;
import com.wokun.dset.base.BaseBindingActivity;
import com.wokun.dset.callback.DialogCallback;
import com.wokun.dset.login.LoginMgr;
import com.wokun.dset.model.Constants;
import com.wokun.dset.response.BaseResponse;
import com.wokun.dset.response.BaseResponse2;
import com.wokun.dset.ucenter.adapter.SwipAdapter;
import com.wokun.dset.ucenter.bean.BankCardBean;
import com.wokun.dset.utils.ScreenUtils;
import com.wokun.dset.utils.StringUtil;
import com.yanzhenjie.recyclerview.swipe.SwipeMenu;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuBridge;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuCreator;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuItem;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuItemClickListener;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindString;
import butterknife.BindView;

import static com.wokun.dset.DsetApp.getContext;
import static com.wokun.dset.utils.MD5.ParameterUtils.removeEmptyData;
import static com.wokun.dset.utils.MD5.ParameterUtils.sortMapByKey;

/**
 * Created by Administrator on 2018/7/26/026.
 * 银行卡管理
 */

public class TixianMoneyActivity2 extends BaseBindingActivity {
    @BindString(R.string.tysl_user_bank)String title;

    @BindView(R.id.recyclerView)
    SwipeMenuRecyclerView recycler_view;

    @BindView(R.id.cd_addcard)
    TextView cd_addcard;
    private SwipAdapter swipAdapter;
    private  CardListadapter mCardListadapter;
    @BindView(R.id.swipeRefreshLayout)SwipeRefreshLayout swipeRefreshLayout;
    private      List<BankCardBean> cardlist;
    @Override
    public int createView() {
        return R.layout.activity_merchant_account_huiyuan2;
    }

    @Override
    public WidgetBar createToolBar() {
        return mWidgetBar.setWidgetBarTitle(title)
                .setMenu(R.drawable.ic_jiahao,0)
                .setMenuTextColor(UIUtil.getColor(R.color.colorPrimary))
                .setOnMenuClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //actionRichScan();
                        actionRichScanNew();
                    }
                },null);
    }

    private void actionRichScanNew() {

        startActivity(BankTestActivity2.class);
    }

    @Override
    public void init() {
        initList();
        loadDataListCard();
        cd_addcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(BankTestActivity2.class);
            }
        });

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //    RxToast.showShort("我刷新了");
                loadDataListCard();
                swipeRefreshLayout.setRefreshing(false);
            }
        });

    }
    private void initList() {

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recycler_view.setLayoutManager(linearLayoutManager);
        mCardListadapter = new CardListadapter();
        swipAdapter = new SwipAdapter(getContext(),cardlist);
        recycler_view.setSwipeMenuCreator(menuCreator);
        recycler_view.setAdapter(mCardListadapter);
        recycler_view.setSwipeMenuItemClickListener(new SwipeMenuItemClickListener() {
            @Override
            public void onItemClick(SwipeMenuBridge menuBridge) {//侧滑点击
                menuBridge.closeMenu();
             //   int position = menuBridge.getPosition();
             /*    mCardListadapter.setOnItemclickListener(new OnItemClickListener() {
                    @Override
                    public void onItemClick(View v, int position) {

                    }
                });*/

                int adapterPosition = menuBridge.getAdapterPosition();
                String cardid = cardlist.get(adapterPosition).getId();
                deleteBank(cardid);
                swipAdapter.delposition(adapterPosition);
              /*  if (position == 0) {
                  //  Toast.makeText(getContext(), "删除数据啦~", Toast.LENGTH_SHORT).show();

                    deleteBank();

                } else {
                    int adapterPosition = menuBridge.getAdapterPosition();
                    swipAdapter.delposition(adapterPosition);
                }*/
            }
        });

    }

    private void deleteBank(String id) {
        Map params = new HashMap();
        params.put("timestamp", StringUtil.getCurrentTime());
        params.put("id", id);
        Map<String, String> removeMap = removeEmptyData(params);
        Map<String, String> resultMap = sortMapByKey(removeMap);
        String sign = LoginMgr.getInstance().getSign(removeMap, resultMap ,params);
        Log.e("进来来dsa2211","进来来dsa22");
        OkGo.<BaseResponse<Object>>post(Constants.BASE_URL + Constants.BANK_CARD_DELETE)
                .tag(this)
                .params("sign", sign)
                .params("timestamp", StringUtil.getCurrentTime())
                .params("id", id)
                .execute(new DialogCallback<BaseResponse<Object>>(this) {
                    @Override
                    public void onSuccess(Response<BaseResponse<Object>> response) {
                        BaseResponse body = response.body();
                        if(body == null)return;
                        if (body.getStatus().equals("0001")) {
                            RxToast.showShort(body.getMessage());
                            loadDataListCard();
                        }
                    }
                });





    }

    private SwipeMenuCreator menuCreator = new SwipeMenuCreator() {
        @Override
        public void onCreateMenu(SwipeMenu swipeLeftMenu, SwipeMenu swipeRightMenu, int viewType) {

         /*   SwipeMenuItem add = new SwipeMenuItem(getContext())
                    .setBackgroundColor(Color.rgb(0xF9, 0x3F, 0x25))
                    .setText("添加")
                    .setTextSize((int) ScreenUtils.sp2px(getContext(), 5))
                    .setTextColor(Color.WHITE)
                    .setHeight(ViewGroup.LayoutParams.MATCH_PARENT)
                    .setWidth((int) ScreenUtils.dp2px(getContext(), 70));
            swipeRightMenu.addMenuItem(add);// 添加一个按钮到右侧侧菜单。*/

            SwipeMenuItem delete = new SwipeMenuItem(getContext())
                    .setBackgroundColor(Color.rgb(0x49, 0x13, 0x81))
                    .setText("删除")
                    .setTextSize((int) ScreenUtils.sp2px(getContext(), 5))
                    .setTextColor(Color.WHITE)
                    .setHeight(ViewGroup.LayoutParams.MATCH_PARENT)
                    .setWidth((int) ScreenUtils.dp2px(getContext(), 70));
                   swipeRightMenu.addMenuItem(delete);// 添加一个按钮到右侧侧菜单。

        }
    };







    private void loadDataListCard() {
        Map params = new HashMap();
        params.put("timestamp", StringUtil.getCurrentTime());
        params.put("page", "1");
        Map<String, String> removeMap = removeEmptyData(params);
        Map<String, String> resultMap = sortMapByKey(removeMap);
        String sign = LoginMgr.getInstance().getSign(removeMap, resultMap ,params);
        Log.e("进来来dsa2211","进来来dsa22");
        OkGo.<BaseResponse2<BankCardBean>>post(Constants.BASE_URL + Constants.BANK_CARD)
                .tag(this)
                .params("sign", sign)
                .params("timestamp", StringUtil.getCurrentTime())
                .params("page", "1")
                .execute(new DialogCallback<BaseResponse2<BankCardBean>>(this) {
                    @Override
                    public void onSuccess(Response<BaseResponse2<BankCardBean>> response) {
                        BaseResponse2 body = response.body();
                        if(body == null)return;
                        if (body.getStatus().equals("0001")) {
                            RxToast.showShort(body.getMessage());
                           cardlist= (List<BankCardBean>) body.getData();
                            mCardListadapter.setData(cardlist);
                            Log.e("进来来dsa22","进来来dsa22");
                        }
                    }
                });
    }
  /*  @OnClick(R.id.zhihui_card_bangdin)
    public void CardBangdi() {
        startActivity(BankTestActivity2.class);
    }*/
}
