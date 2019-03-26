package com.wokun.dset.store.bean;


import java.util.List;

public class DStoreGoodesList {


    /**
     * status : 0001
     * msg : 成功
     * data : {"goodsList":[{"goods_id":"35","goods_name":"三九纤体酵素原液  排毒瘦身佳品","price":"168.00","shop_id":"0","shop_name":"官方旗舰店","pic_cover_mid":"http://dsytcdn.chinafjjdkj.cn/upload/goods/20190122/e85b5be8fe92e87a8beef8372df308bf2.jpg","pic_cover_small":"http://dsytcdn.chinafjjdkj.cn/upload/goods/20190122/e85b5be8fe92e87a8beef8372df308bf3.jpg"},{"goods_id":"56","goods_name":"2018新款 米白色镂空水溶蕾丝连衣裙","price":"345.00","shop_id":"0","shop_name":"官方旗舰店","pic_cover_mid":"http://store.dasether.com/upload/goods/20190314/445a9fa4dd6b27cc2d0f6921aa4408da2.jpg","pic_cover_small":"http://store.dasether.com/upload/goods/20190314/445a9fa4dd6b27cc2d0f6921aa4408da3.jpg"}]}
     */

    private String status;
    private String msg;
    private DataBean data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private List<GoodsListBean> goodsList;

        public List<GoodsListBean> getGoodsList() {
            return goodsList;
        }

        public void setGoodsList(List<GoodsListBean> goodsList) {
            this.goodsList = goodsList;
        }

        public static class GoodsListBean {
            /**
             * goods_id : 35
             * goods_name : 三九纤体酵素原液  排毒瘦身佳品
             * price : 168.00
             * shop_id : 0
             * shop_name : 官方旗舰店
             * pic_cover_mid : http://dsytcdn.chinafjjdkj.cn/upload/goods/20190122/e85b5be8fe92e87a8beef8372df308bf2.jpg
             * pic_cover_small : http://dsytcdn.chinafjjdkj.cn/upload/goods/20190122/e85b5be8fe92e87a8beef8372df308bf3.jpg
             */

            private String goods_id;
            private String goods_name;
            private String price;
            private String market_price;
            private String shop_id;
            private String shop_name;
            private String pic_cover_mid;
            private String pic_cover_small;

            public String getMarket_price() {
                return market_price;
            }

            public void setMarket_price(String market_price) {
                this.market_price = market_price;
            }

            public String getGoods_id() {
                return goods_id;
            }

            public void setGoods_id(String goods_id) {
                this.goods_id = goods_id;
            }

            public String getGoods_name() {
                return goods_name;
            }

            public void setGoods_name(String goods_name) {
                this.goods_name = goods_name;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public String getShop_id() {
                return shop_id;
            }

            public void setShop_id(String shop_id) {
                this.shop_id = shop_id;
            }

            public String getShop_name() {
                return shop_name;
            }

            public void setShop_name(String shop_name) {
                this.shop_name = shop_name;
            }

            public String getPic_cover_mid() {
                return pic_cover_mid;
            }

            public void setPic_cover_mid(String pic_cover_mid) {
                this.pic_cover_mid = pic_cover_mid;
            }

            public String getPic_cover_small() {
                return pic_cover_small;
            }

            public void setPic_cover_small(String pic_cover_small) {
                this.pic_cover_small = pic_cover_small;
            }
        }
    }
}
