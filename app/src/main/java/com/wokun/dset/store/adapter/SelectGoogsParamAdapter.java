package com.wokun.dset.store.adapter;

import android.util.Log;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wokun.dset.R;
import com.wokun.dset.store.bean.DStoreGoodsDetail;
import com.wokun.dset.store.bean.GoodsSKUList;
import com.wokun.dset.store.dstore.dstoredetail.DStoreDetailActivity;
import com.wokun.dset.utils.JosnFrom;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.List;


public class SelectGoogsParamAdapter extends BaseQuickAdapter<DStoreGoodsDetail.DataBean.SpecListBean, BaseViewHolder> {

    private DStoreDetailActivity context;
    private List<GoodsSKUList> goodsSKUListList;


    public SelectGoogsParamAdapter(DStoreDetailActivity context, List<GoodsSKUList> goodsSKUListList) {
        super(R.layout.item_googes_param, null);
        this.context = context;
        this.goodsSKUListList = goodsSKUListList;
    }

    @Override
    protected void convert(BaseViewHolder helper, final DStoreGoodsDetail.DataBean.SpecListBean item) {

        helper.setText(R.id.name, item.getSpec_name());

        TagFlowLayout tagFlowLayout = helper.getView(R.id.data);


        GoodsSizeAdapter sizeAdapter = new GoodsSizeAdapter(context, tagFlowLayout, ((GoodsSKUList) goodsSKUListList.get(getData().indexOf(item))).getValue());
        tagFlowLayout.setAdapter(sizeAdapter);
        tagFlowLayout.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {
                context.setArray(item.getSpec_id(), item.getValue().get(position).getSpec_value_id());
                return false;
            }
        });

//        popColor.setAdapter(colorAdapter);

//        helper.getView(R.id.root_view).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                context.startActivity(new Intent(context, StoreInfoActivity.class));
//            }
//        });

    }
}
