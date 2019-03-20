package com.wokun.dset.store.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shantoo.widget.imageview.SelectorImageView;
import com.wokun.dset.R;
import com.wokun.dset.mainfragment.ShopCartFragment;
import com.wokun.dset.store.bean.CartList;
import com.wokun.dset.utils.ImageLoader;

import org.xml.sax.helpers.LocatorImpl;

import java.util.ArrayList;
import java.util.List;

public class ShopCartAdapter extends BaseQuickAdapter<CartList.DataBean.CartListInfoBean.GoodsItemBean, BaseViewHolder> {

    private static List<Integer> cartList = new ArrayList<>();
    private OnRefreshListener mOnRefreshListener;
    private OnEditClickListener mOnEditClickListener;
    private OnItemClickListener mOnItemClickListener;
    private OnDeleteClickListener mOnDeleteClickListener;
    private ImageView actionMinus;
    private ImageView actionAdd;
    List<TextView> deleteList = new ArrayList<>();
    private TextView actionDelete;

    public ShopCartAdapter(@LayoutRes int layoutResId, @Nullable List<CartList.DataBean.CartListInfoBean.GoodsItemBean> data) {
        super(layoutResId, data);
    }

    public List<TextView> getDeleteList() {
        return deleteList;
    }

    @Override
    protected void convert(BaseViewHolder helper, CartList.DataBean.CartListInfoBean.GoodsItemBean item) {

        Context context = helper.itemView.getContext();
        final int position = helper.getAdapterPosition();
        RelativeLayout shopCartHeaderRoot = helper.getView(R.id.shop_cart_header_root);

        SelectorImageView parentSelector = helper.getView(R.id.action_parent_selector);
        ImageView childSelector = helper.getView(R.id.action_child_selector);

        EditText shopCartNum = helper.getView(R.id.shop_cart_num);
        shopCartNum.setText(String.valueOf(item.getNum()));

        actionDelete = helper.getView(R.id.action_delete);
        deleteList.add(actionDelete);
        actionMinus = helper.getView(R.id.action_minus);
        actionAdd = helper.getView(R.id.action_add);
        Log.i(TAG, position + "外面  " + mData.get(position).getGoods_id());
        if (position > 0) {
            Log.i(TAG, position + "convert=====  " + mData.get(position).getGoods_id());
            Log.i(TAG, position + "convconvert=====ert: " + mData.get(position - 1).getGoods_id());

            if (mData.get(position).getGoods_id().equals(mData.get(position - 1).getGoods_id())) {
                shopCartHeaderRoot.setVisibility(View.GONE);
            } else {
                shopCartHeaderRoot.setVisibility(View.VISIBLE);
            }
        } else {
            shopCartHeaderRoot.setVisibility(View.VISIBLE);
        }

        ImageLoader.loadImage(item.getGoods_picture(), (ImageView) helper.getView(R.id.goods_image));
        helper.setText(R.id.store_name, item.getStore_name())
                .setText(R.id.goods_name, item.getGoods_name())
                .setText(R.id.goods_price, "¥" + item.getPrice());
        switch (Integer.valueOf(item.getState())) {
            case 0:
                helper.setText(R.id.goods_state, "待审核");
                helper.getView(R.id.goods_state).setVisibility(View.VISIBLE);
                break;
            case 1:
                //helper.setText(goods_state,"正常");
                helper.getView(R.id.goods_state).setVisibility(View.GONE);
                break;
            case 2:
                helper.setText(R.id.goods_state, "下架");
                helper.getView(R.id.goods_state).setVisibility(View.VISIBLE);
                break;
            case 3:
                helper.setText(R.id.goods_state, "删除");
                helper.getView(R.id.goods_state).setVisibility(View.VISIBLE);
                break;
        }
        if (mOnRefreshListener != null) {
            boolean isSelect = false;
            for (int i = 0; i < mData.size(); i++) {
                if (!mData.get(i).isSelect()) {
                    isSelect = false;
                    break;
                } else {
                    isSelect = true;
                }
            }
            mOnRefreshListener.onRefresh(isSelect);
        }
        if ((Integer.valueOf(mData.get(position).getStock()) < Integer.valueOf(mData.get(position).getNum()))) {
            childSelector.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_shop_cart_state));
        }
        initAsSelector(context, position, parentSelector, childSelector);
        initListener(item, context, position);
    }

    private void initListener(final CartList.DataBean.CartListInfoBean.GoodsItemBean item, final Context context, final int position) {
        actionMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Integer.valueOf(mData.get(position).getNum()) > 1) {
                    int count = Integer.valueOf(mData.get(position).getNum()) - 1;
                    if (mOnEditClickListener != null) {
                        mOnEditClickListener.onEditClick(item, count);
                    }
                    mData.get(position).setNum(count + "");
                    notifyDataSetChanged();
                }
            }
        });
        actionAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Integer.valueOf(mData.get(position).getStock()) > Integer.valueOf(mData.get(position).getNum())) {
                    int count = Integer.valueOf(mData.get(position).getNum()) + 1;
                    if (mOnEditClickListener != null) {
                        mOnEditClickListener.onEditClick(item, count);
                    }
                    mData.get(position).setNum("" + count);
                    notifyDataSetChanged();
                } else {
                    Toast.makeText(context, "库存不足", Toast.LENGTH_SHORT).show();
                }
            }
        });
        actionDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(v, position);
            }
        });
    }

    private void showDialog(final View view, final int position) {
        //调用删除某个规格商品的接口
        if (mOnDeleteClickListener != null) {
            mOnDeleteClickListener.onDeleteClick(view, position, Integer.valueOf(mData.get(position).getCart_id()));
        }
        mData.remove(position);
        //重新排序，标记所有商品不同商铺第一个的商品位置
        ShopCartFragment.isSelectFirst(mData);
        notifyDataSetChanged();
    }

    public static List<Integer> getCartList() {
        return cartList;
    }

    private void initAsSelector(Context context, final int position, SelectorImageView parentSelector, ImageView childSelector) {
        if (mData.get(position).isSelect()) {
            //childSelector.toggle(true);
            childSelector.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_selected));
        } else {
            //childSelector.toggle(false);
            childSelector.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_un_selected));
        }

        if (mData.get(position).isShopSelect()) {
            parentSelector.toggle(true);
            //parentSelector.setImageDrawable(context.getResources().getDrawable(R.drawable.shopcart_selected));
        } else {
            parentSelector.toggle(false);
            // parentSelector.setImageDrawable(context.getResources().getDrawable(R.drawable.shopcart_unselected));
        }

        childSelector.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*if (mData.get(position).isSelect()) {
                    cartList.add(mData.get(position).getCart_id());
                } else {
                    cartList.remove(mData.get(position).getCart_id());
                }*/

                mData.get(position).setSelect(!mData.get(position).isSelect());
                //通过循环找出不同商铺的第一个商品的位置
                for (int i = 0; i < mData.size(); i++) {
                    if (mData.get(i).getIsFirst() == 1) {
                        //遍历去找出同一家商铺的所有商品的勾选情况
                        for (int j = 0; j < mData.size(); j++) {
                            //如果是同一家商铺的商品，并且其中一个商品是未选中，那么商铺的全选勾选取消
                            if (mData.get(j).getCart_id() == mData.get(i).getCart_id() && !mData.get(j).isSelect()) {
                                mData.get(i).setShopSelect(false);
                                break;
                            } else {
                                //如果是同一家商铺的商品，并且所有商品是选中，那么商铺的选中全选勾选
                                mData.get(i).setShopSelect(true);
                            }
                        }
                    }
                }
                notifyDataSetChanged();
            }
        });

        parentSelector.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mData.get(position).getIsFirst() == 1) {
                    mData.get(position).setShopSelect(!mData.get(position).isShopSelect());
                    for (int i = 0; i < mData.size(); i++) {
                        if (mData.get(i).getGoods_id().equals(mData.get(position).getGoods_id())) {
                            mData.get(i).setSelect(mData.get(position).isShopSelect());
                        }
                    }
                    notifyDataSetChanged();
                }
            }
        });
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public interface OnDeleteClickListener {
        void onDeleteClick(View v, int position, int cartId);
    }

    public interface OnEditClickListener {
        void onEditClick(CartList.DataBean.CartListInfoBean.GoodsItemBean item, int count);
    }

    public interface OnRefreshListener {
        void onRefresh(boolean isSelect);
    }

    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    public void setOnDeleteClickListener(OnDeleteClickListener mOnDeleteClickListener) {
        this.mOnDeleteClickListener = mOnDeleteClickListener;
    }

    public void setOnEditClickListener(OnEditClickListener mOnEditClickListener) {
        this.mOnEditClickListener = mOnEditClickListener;
    }

    public void setRefreshListener(OnRefreshListener listener) {
        this.mOnRefreshListener = listener;
    }


}