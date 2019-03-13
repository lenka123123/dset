package com.example.baselib.bean;

public class BeaseGoodsInfoBean {


    public String product_cart_id;//int
    public String product_id;//int
    public String product_type;//int
    public String product_type_name;//int
    public String product_spu_id;//int
    public String sales_sum;//int
    public String stock_sum;//int
    public String price;//int
    public String type_price;//int
    public String product_sku_id;//int
    public String product_name;//string
    public String cover;//string
    public String is_postage;//int
    public String postage_price;//int
    public String splice_content;//string
    public String product_amount;//int


    public String id;
    public boolean choosed;

    public boolean isChoosed() {
        return choosed;
    }

    public void setChoosed(boolean id) {
        this.choosed = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
