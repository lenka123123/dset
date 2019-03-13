package com.example.baselib.bean;

import java.util.List;

public class ShoppingListBean {

    public String success;
    public String msg;
    public DataBean data;
    public String code;
    public String request_id;

    public static class DataBean {
        public List<BeaseGoodsInfoBean> coinList;
        public List<BeaseGoodsInfoBean> diamondList;
        public List<BeaseGoodsInfoBean> ticketList;
//        public List<AbolishListBean> abolishList;

        public static class AbolishListBean extends BeaseGoodsInfoBean {
            public String id;
            public String product_id;
            public String product_spu_id;
            public String attr_ids;
            public String attr_value_ids;
            public String sales_sum;
            public String stock_sum;
            public String price;
            public String type_price;
            public String is_show;
            public String product_sku_id;
            public String product_cart_id;
            public String product_name;
            public String product_type;
            public String product_type_name;
            public String cover;
            public String is_postage;
            public String postage_price;
            public String splice_content;
            public String product_amount;
            public String status;
        }
    }
}
