package com.wokun.dset.store.bean;


import java.util.List;

public class DStoreGoodsDetail {


    /**
     * data : {"description_url":"http://api.dasether.com/shop-goods/goods-content.html?goods_id=35","evaluates":"0","give_point":"0","goods_id":"35","goods_name":"三九纤体酵素原液  排毒瘦身佳品","goods_picture_id":"296","goods_sku":[{"attr_value_items":"","attr_value_items_format":"","price":"168.00","promote_price":"168.00","sku_id":"36","sku_name":"","stock":"50"}],"goods_spec_format":"[]","img_id_array":"296","market_price":"0.00","price":"168.00","promote_id":"0","promotion":{},"promotion_price":"168.00","promotion_type":"0","sales":"0","shipping_fee":"0.00","shop":{"shop_deliverycredit":"1","shop_desccredit":"1","shop_id":"0","shop_logo":"http://api.dasether.com/upload/common/1536918022.jpg","shop_name":"官方旗舰店","shop_servicecredit":"1"},"shop_id":"0","show_img":["http://dsytcdn.chinafjjdkj.cn/upload/goods/20190122/e85b5be8fe92e87a8beef8372df308bf1.jpg"],"sort":"1","spec_list":[],"star":"0","stock":"50"}
     * msg : 成功
     * status : 0001
     */

    private DataBean data;
    private String msg;
    private String status;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public static class DataBean {
        /**
         * description_url : http://api.dasether.com/shop-goods/goods-content.html?goods_id=35
         * evaluates : 0
         * give_point : 0
         * goods_id : 35
         * goods_name : 三九纤体酵素原液  排毒瘦身佳品
         * goods_picture_id : 296
         * goods_sku : [{"attr_value_items":"","attr_value_items_format":"","price":"168.00","promote_price":"168.00","sku_id":"36","sku_name":"","stock":"50"}]
         * goods_spec_format : []
         * img_id_array : 296
         * market_price : 0.00
         * price : 168.00
         * promote_id : 0
         * promotion : {}
         * promotion_price : 168.00
         * promotion_type : 0
         * sales : 0
         * shipping_fee : 0.00
         * shop : {"shop_deliverycredit":"1","shop_desccredit":"1","shop_id":"0","shop_logo":"http://api.dasether.com/upload/common/1536918022.jpg","shop_name":"官方旗舰店","shop_servicecredit":"1"}
         * shop_id : 0
         * show_img : ["http://dsytcdn.chinafjjdkj.cn/upload/goods/20190122/e85b5be8fe92e87a8beef8372df308bf1.jpg"]
         * sort : 1
         * spec_list : []
         * star : 0
         * stock : 50
         */

        private String description_url;
        private String evaluates;
        private String give_point;
        private String goods_id;
        private String goods_name;
        private String goods_picture_id;
        private String goods_spec_format;
        private String img_id_array;
        private String market_price;
        private String price;
        private String promote_id;
        private PromotionBean promotion;
        private String promotion_price;
        private String promotion_type;
        private String sales;
        private String shipping_fee;
        private ShopBean shop;
        private String shop_id;
        private String sort;
        private String star;
        private String stock;
        private List<GoodsSkuBean> goods_sku;
        private List<String> show_img;
        private List<?> spec_list;

        public String getDescription_url() {
            return description_url;
        }

        public void setDescription_url(String description_url) {
            this.description_url = description_url;
        }

        public String getEvaluates() {
            return evaluates;
        }

        public void setEvaluates(String evaluates) {
            this.evaluates = evaluates;
        }

        public String getGive_point() {
            return give_point;
        }

        public void setGive_point(String give_point) {
            this.give_point = give_point;
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

        public String getGoods_picture_id() {
            return goods_picture_id;
        }

        public void setGoods_picture_id(String goods_picture_id) {
            this.goods_picture_id = goods_picture_id;
        }

        public String getGoods_spec_format() {
            return goods_spec_format;
        }

        public void setGoods_spec_format(String goods_spec_format) {
            this.goods_spec_format = goods_spec_format;
        }

        public String getImg_id_array() {
            return img_id_array;
        }

        public void setImg_id_array(String img_id_array) {
            this.img_id_array = img_id_array;
        }

        public String getMarket_price() {
            return market_price;
        }

        public void setMarket_price(String market_price) {
            this.market_price = market_price;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getPromote_id() {
            return promote_id;
        }

        public void setPromote_id(String promote_id) {
            this.promote_id = promote_id;
        }

        public PromotionBean getPromotion() {
            return promotion;
        }

        public void setPromotion(PromotionBean promotion) {
            this.promotion = promotion;
        }

        public String getPromotion_price() {
            return promotion_price;
        }

        public void setPromotion_price(String promotion_price) {
            this.promotion_price = promotion_price;
        }

        public String getPromotion_type() {
            return promotion_type;
        }

        public void setPromotion_type(String promotion_type) {
            this.promotion_type = promotion_type;
        }

        public String getSales() {
            return sales;
        }

        public void setSales(String sales) {
            this.sales = sales;
        }

        public String getShipping_fee() {
            return shipping_fee;
        }

        public void setShipping_fee(String shipping_fee) {
            this.shipping_fee = shipping_fee;
        }

        public ShopBean getShop() {
            return shop;
        }

        public void setShop(ShopBean shop) {
            this.shop = shop;
        }

        public String getShop_id() {
            return shop_id;
        }

        public void setShop_id(String shop_id) {
            this.shop_id = shop_id;
        }

        public String getSort() {
            return sort;
        }

        public void setSort(String sort) {
            this.sort = sort;
        }

        public String getStar() {
            return star;
        }

        public void setStar(String star) {
            this.star = star;
        }

        public String getStock() {
            return stock;
        }

        public void setStock(String stock) {
            this.stock = stock;
        }

        public List<GoodsSkuBean> getGoods_sku() {
            return goods_sku;
        }

        public void setGoods_sku(List<GoodsSkuBean> goods_sku) {
            this.goods_sku = goods_sku;
        }

        public List<String> getShow_img() {
            return show_img;
        }

        public void setShow_img(List<String> show_img) {
            this.show_img = show_img;
        }

        public List<?> getSpec_list() {
            return spec_list;
        }

        public void setSpec_list(List<?> spec_list) {
            this.spec_list = spec_list;
        }

        public static class PromotionBean {
        }

        public static class ShopBean {
            /**
             * shop_deliverycredit : 1
             * shop_desccredit : 1
             * shop_id : 0
             * shop_logo : http://api.dasether.com/upload/common/1536918022.jpg
             * shop_name : 官方旗舰店
             * shop_servicecredit : 1
             */

            private String shop_deliverycredit;
            private String shop_desccredit;
            private String shop_id;
            private String shop_logo;
            private String shop_name;
            private String shop_servicecredit;

            public String getShop_deliverycredit() {
                return shop_deliverycredit;
            }

            public void setShop_deliverycredit(String shop_deliverycredit) {
                this.shop_deliverycredit = shop_deliverycredit;
            }

            public String getShop_desccredit() {
                return shop_desccredit;
            }

            public void setShop_desccredit(String shop_desccredit) {
                this.shop_desccredit = shop_desccredit;
            }

            public String getShop_id() {
                return shop_id;
            }

            public void setShop_id(String shop_id) {
                this.shop_id = shop_id;
            }

            public String getShop_logo() {
                return shop_logo;
            }

            public void setShop_logo(String shop_logo) {
                this.shop_logo = shop_logo;
            }

            public String getShop_name() {
                return shop_name;
            }

            public void setShop_name(String shop_name) {
                this.shop_name = shop_name;
            }

            public String getShop_servicecredit() {
                return shop_servicecredit;
            }

            public void setShop_servicecredit(String shop_servicecredit) {
                this.shop_servicecredit = shop_servicecredit;
            }
        }

        public static class GoodsSkuBean {
            /**
             * attr_value_items :
             * attr_value_items_format :
             * price : 168.00
             * promote_price : 168.00
             * sku_id : 36
             * sku_name :
             * stock : 50
             */

            private String attr_value_items;
            private String attr_value_items_format;
            private String price;
            private String promote_price;
            private String sku_id;
            private String sku_name;
            private String stock;

            public String getAttr_value_items() {
                return attr_value_items;
            }

            public void setAttr_value_items(String attr_value_items) {
                this.attr_value_items = attr_value_items;
            }

            public String getAttr_value_items_format() {
                return attr_value_items_format;
            }

            public void setAttr_value_items_format(String attr_value_items_format) {
                this.attr_value_items_format = attr_value_items_format;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public String getPromote_price() {
                return promote_price;
            }

            public void setPromote_price(String promote_price) {
                this.promote_price = promote_price;
            }

            public String getSku_id() {
                return sku_id;
            }

            public void setSku_id(String sku_id) {
                this.sku_id = sku_id;
            }

            public String getSku_name() {
                return sku_name;
            }

            public void setSku_name(String sku_name) {
                this.sku_name = sku_name;
            }

            public String getStock() {
                return stock;
            }

            public void setStock(String stock) {
                this.stock = stock;
            }
        }
    }
}
