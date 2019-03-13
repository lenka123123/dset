package com.wokun.dset.ucenter.bean;

import java.io.Serializable;
import java.lang.ref.PhantomReference;
import java.util.List;

/**
 * Created by Administrator on 2019\1\30 0030.
 */

public class DsytDindanBean implements Serializable {
        private List<TradeOrderListBean> tradeOrderList;

    public List<TradeOrderListBean> getTradeOrderList() {
        return tradeOrderList;
    }

    public void setTradeOrderList(List<TradeOrderListBean> tradeOrderList) {
        this.tradeOrderList = tradeOrderList;
    }
}
