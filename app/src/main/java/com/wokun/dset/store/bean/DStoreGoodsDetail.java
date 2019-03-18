package com.wokun.dset.store.bean;


import java.util.List;

public class DStoreGoodsDetail {

    /**
     * status : 0001
     * msg : 成功
     * data : {"goods_id":"51","goods_name":"技术测试商品","shop_id":"0","promotion_type":"0","promote_id":"0","market_price":"420.00","price":"300.00","promotion_price":"300.00","give_point":"0","shipping_fee":"0.00","stock":"23","sales":"0","star":"0","evaluates":"0","img_id_array":"508","sort":"0","goods_spec_format":"[{\"spec_name\":\"内存大小\",\"spec_id\":7,\"value\":[{\"spec_value_name\":\"16G内存\",\"spec_name\":\"内存大小\",\"spec_id\":7,\"spec_value_id\":10,\"spec_show_type\":1,\"spec_value_data\":\"\"},{\"spec_value_name\":\"32G内存\",\"spec_name\":\"内存大小\",\"spec_id\":7,\"spec_value_id\":11,\"spec_show_type\":1,\"spec_value_data\":\"\"},{\"spec_value_name\":\"8G内存\",\"spec_name\":\"内存大小\",\"spec_id\":7,\"spec_value_id\":9,\"spec_show_type\":1,\"spec_value_data\":\"\"}]},{\"spec_name\":\"显卡\",\"spec_id\":9,\"value\":[{\"spec_value_name\":\"4G独显\",\"spec_name\":\"显卡\",\"spec_id\":9,\"spec_value_id\":14,\"spec_show_type\":1,\"spec_value_data\":\"\"},{\"spec_value_name\":\"6G独显\",\"spec_name\":\"显卡\",\"spec_id\":9,\"spec_value_id\":15,\"spec_show_type\":1,\"spec_value_data\":\"\"}]}]","shop":{"shop_id":"0","shop_name":"官方旗舰店","shop_logo":"http://api.dasether.com/upload/common/1536918022.jpg","shop_desccredit":"1","shop_servicecredit":"1","shop_deliverycredit":"1"},"goods_picture_id":"508","show_img":["http://store.dasether.com/upload/goods/20190305/03abd0e5b2b08e94b47c9fffbe3f5ad41.jpg"],"spec_list":[{"spec_name":"内存大小","spec_id":7,"value":[{"spec_value_name":"16G内存","spec_name":"内存大小","spec_id":7,"spec_value_id":10,"spec_show_type":1,"spec_value_data":""},{"spec_value_name":"32G内存","spec_name":"内存大小","spec_id":7,"spec_value_id":11,"spec_show_type":1,"spec_value_data":""},{"spec_value_name":"8G内存","spec_name":"内存大小","spec_id":7,"spec_value_id":9,"spec_show_type":1,"spec_value_data":""}]},{"spec_name":"显卡","spec_id":9,"value":[{"spec_value_name":"4G独显","spec_name":"显卡","spec_id":9,"spec_value_id":14,"spec_show_type":1,"spec_value_data":""},{"spec_value_name":"6G独显","spec_name":"显卡","spec_id":9,"spec_value_id":15,"spec_show_type":1,"spec_value_data":""}]}],"goods_sku":[{"sku_id":"58","sku_name":"8G内存 4G独显 ","attr_value_items":"7:9;9:14","attr_value_items_format":"7:9;9:14","price":"300.00","promote_price":"300.00","stock":"5"},{"sku_id":"59","sku_name":"8G内存 6G独显 ","attr_value_items":"7:9;9:15","attr_value_items_format":"7:9;9:15","price":"400.00","promote_price":"400.00","stock":"0"},{"sku_id":"60","sku_name":"16G内存 4G独显 ","attr_value_items":"7:10;9:14","attr_value_items_format":"7:10;9:14","price":"350.00","promote_price":"350.00","stock":"0"},{"sku_id":"61","sku_name":"16G内存 6G独显 ","attr_value_items":"7:10;9:15","attr_value_items_format":"7:10;9:15","price":"450.00","promote_price":"450.00","stock":"6"},{"sku_id":"62","sku_name":"32G内存 4G独显 ","attr_value_items":"7:11;9:14","attr_value_items_format":"7:11;9:14","price":"500.00","promote_price":"500.00","stock":"2"},{"sku_id":"63","sku_name":"32G内存 6G独显 ","attr_value_items":"7:11;9:15","attr_value_items_format":"7:11;9:15","price":"550.00","promote_price":"550.00","stock":"10"}],"promotion":{},"freight":"0.00","description_url":"http://api.dasether.com/shop-goods/goods-content.html?goods_id=51"}
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
        /**
         * goods_id : 51
         * goods_name : 技术测试商品
         * shop_id : 0
         * promotion_type : 0
         * promote_id : 0
         * market_price : 420.00
         * price : 300.00
         * promotion_price : 300.00
         * give_point : 0
         * shipping_fee : 0.00
         * stock : 23
         * sales : 0
         * star : 0
         * evaluates : 0
         * img_id_array : 508
         * sort : 0
         * goods_spec_format : [{"spec_name":"内存大小","spec_id":7,"value":[{"spec_value_name":"16G内存","spec_name":"内存大小","spec_id":7,"spec_value_id":10,"spec_show_type":1,"spec_value_data":""},{"spec_value_name":"32G内存","spec_name":"内存大小","spec_id":7,"spec_value_id":11,"spec_show_type":1,"spec_value_data":""},{"spec_value_name":"8G内存","spec_name":"内存大小","spec_id":7,"spec_value_id":9,"spec_show_type":1,"spec_value_data":""}]},{"spec_name":"显卡","spec_id":9,"value":[{"spec_value_name":"4G独显","spec_name":"显卡","spec_id":9,"spec_value_id":14,"spec_show_type":1,"spec_value_data":""},{"spec_value_name":"6G独显","spec_name":"显卡","spec_id":9,"spec_value_id":15,"spec_show_type":1,"spec_value_data":""}]}]
         * shop : {"shop_id":"0","shop_name":"官方旗舰店","shop_logo":"http://api.dasether.com/upload/common/1536918022.jpg","shop_desccredit":"1","shop_servicecredit":"1","shop_deliverycredit":"1"}
         * goods_picture_id : 508
         * show_img : ["http://store.dasether.com/upload/goods/20190305/03abd0e5b2b08e94b47c9fffbe3f5ad41.jpg"]
         * spec_list : [{"spec_name":"内存大小","spec_id":7,"value":[{"spec_value_name":"16G内存","spec_name":"内存大小","spec_id":7,"spec_value_id":10,"spec_show_type":1,"spec_value_data":""},{"spec_value_name":"32G内存","spec_name":"内存大小","spec_id":7,"spec_value_id":11,"spec_show_type":1,"spec_value_data":""},{"spec_value_name":"8G内存","spec_name":"内存大小","spec_id":7,"spec_value_id":9,"spec_show_type":1,"spec_value_data":""}]},{"spec_name":"显卡","spec_id":9,"value":[{"spec_value_name":"4G独显","spec_name":"显卡","spec_id":9,"spec_value_id":14,"spec_show_type":1,"spec_value_data":""},{"spec_value_name":"6G独显","spec_name":"显卡","spec_id":9,"spec_value_id":15,"spec_show_type":1,"spec_value_data":""}]}]
         * goods_sku : [{"sku_id":"58","sku_name":"8G内存 4G独显 ","attr_value_items":"7:9;9:14","attr_value_items_format":"7:9;9:14","price":"300.00","promote_price":"300.00","stock":"5"},{"sku_id":"59","sku_name":"8G内存 6G独显 ","attr_value_items":"7:9;9:15","attr_value_items_format":"7:9;9:15","price":"400.00","promote_price":"400.00","stock":"0"},{"sku_id":"60","sku_name":"16G内存 4G独显 ","attr_value_items":"7:10;9:14","attr_value_items_format":"7:10;9:14","price":"350.00","promote_price":"350.00","stock":"0"},{"sku_id":"61","sku_name":"16G内存 6G独显 ","attr_value_items":"7:10;9:15","attr_value_items_format":"7:10;9:15","price":"450.00","promote_price":"450.00","stock":"6"},{"sku_id":"62","sku_name":"32G内存 4G独显 ","attr_value_items":"7:11;9:14","attr_value_items_format":"7:11;9:14","price":"500.00","promote_price":"500.00","stock":"2"},{"sku_id":"63","sku_name":"32G内存 6G独显 ","attr_value_items":"7:11;9:15","attr_value_items_format":"7:11;9:15","price":"550.00","promote_price":"550.00","stock":"10"}]
         * promotion : {}
         * freight : 0.00
         * description_url : http://api.dasether.com/shop-goods/goods-content.html?goods_id=51
         */

        private String goods_id;
        private String goods_name;
        private String shop_id;
        private String promotion_type;
        private String promote_id;
        private String market_price;
        private String price;
        private String promotion_price;
        private String give_point;
        private String shipping_fee;
        private String stock;
        private String sales;
        private String star;
        private String evaluates;
        private String img_id_array;
        private String sort;
        private String goods_spec_format;
        private ShopBean shop;
        private String goods_picture_id;
        private PromotionBean promotion;
        private String freight;
        private String description_url;
        private List<String> show_img;
        private List<SpecListBean> spec_list;
        private List<GoodsSkuBean> goods_sku;

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

        public String getShop_id() {
            return shop_id;
        }

        public void setShop_id(String shop_id) {
            this.shop_id = shop_id;
        }

        public String getPromotion_type() {
            return promotion_type;
        }

        public void setPromotion_type(String promotion_type) {
            this.promotion_type = promotion_type;
        }

        public String getPromote_id() {
            return promote_id;
        }

        public void setPromote_id(String promote_id) {
            this.promote_id = promote_id;
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

        public String getPromotion_price() {
            return promotion_price;
        }

        public void setPromotion_price(String promotion_price) {
            this.promotion_price = promotion_price;
        }

        public String getGive_point() {
            return give_point;
        }

        public void setGive_point(String give_point) {
            this.give_point = give_point;
        }

        public String getShipping_fee() {
            return shipping_fee;
        }

        public void setShipping_fee(String shipping_fee) {
            this.shipping_fee = shipping_fee;
        }

        public String getStock() {
            return stock;
        }

        public void setStock(String stock) {
            this.stock = stock;
        }

        public String getSales() {
            return sales;
        }

        public void setSales(String sales) {
            this.sales = sales;
        }

        public String getStar() {
            return star;
        }

        public void setStar(String star) {
            this.star = star;
        }

        public String getEvaluates() {
            return evaluates;
        }

        public void setEvaluates(String evaluates) {
            this.evaluates = evaluates;
        }

        public String getImg_id_array() {
            return img_id_array;
        }

        public void setImg_id_array(String img_id_array) {
            this.img_id_array = img_id_array;
        }

        public String getSort() {
            return sort;
        }

        public void setSort(String sort) {
            this.sort = sort;
        }

        public String getGoods_spec_format() {
            return goods_spec_format;
        }

        public void setGoods_spec_format(String goods_spec_format) {
            this.goods_spec_format = goods_spec_format;
        }

        public ShopBean getShop() {
            return shop;
        }

        public void setShop(ShopBean shop) {
            this.shop = shop;
        }

        public String getGoods_picture_id() {
            return goods_picture_id;
        }

        public void setGoods_picture_id(String goods_picture_id) {
            this.goods_picture_id = goods_picture_id;
        }

        public PromotionBean getPromotion() {
            return promotion;
        }

        public void setPromotion(PromotionBean promotion) {
            this.promotion = promotion;
        }

        public String getFreight() {
            return freight;
        }

        public void setFreight(String freight) {
            this.freight = freight;
        }

        public String getDescription_url() {
            return description_url;
        }

        public void setDescription_url(String description_url) {
            this.description_url = description_url;
        }

        public List<String> getShow_img() {
            return show_img;
        }

        public void setShow_img(List<String> show_img) {
            this.show_img = show_img;
        }

        public List<SpecListBean> getSpec_list() {
            return spec_list;
        }

        public void setSpec_list(List<SpecListBean> spec_list) {
            this.spec_list = spec_list;
        }

        public List<GoodsSkuBean> getGoods_sku() {
            return goods_sku;
        }

        public void setGoods_sku(List<GoodsSkuBean> goods_sku) {
            this.goods_sku = goods_sku;
        }

        public static class ShopBean {

            private String shop_deliverycredit;
            private String shop_desccredit;
            private String shop_id;
            private String shop_logo;

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

            private String shop_name;
            private String shop_servicecredit;

        }

        public static class PromotionBean {
        }

        public static class SpecListBean {
            /**
             * spec_name : 内存大小
             * spec_id : 7
             * value : [{"spec_value_name":"16G内存","spec_name":"内存大小","spec_id":7,"spec_value_id":10,"spec_show_type":1,"spec_value_data":""},{"spec_value_name":"32G内存","spec_name":"内存大小","spec_id":7,"spec_value_id":11,"spec_show_type":1,"spec_value_data":""},{"spec_value_name":"8G内存","spec_name":"内存大小","spec_id":7,"spec_value_id":9,"spec_show_type":1,"spec_value_data":""}]
             */

            private String spec_name;
            private int spec_id;
            private List<ValueBean> value;

            public String getSpec_name() {
                return spec_name;
            }

            public void setSpec_name(String spec_name) {
                this.spec_name = spec_name;
            }

            public int getSpec_id() {
                return spec_id;
            }

            public void setSpec_id(int spec_id) {
                this.spec_id = spec_id;
            }

            public List<ValueBean> getValue() {
                return value;
            }

            public void setValue(List<ValueBean> value) {
                this.value = value;
            }

            public static class ValueBean {
                /**
                 * spec_value_name : 16G内存
                 * spec_name : 内存大小
                 * spec_id : 7
                 * spec_value_id : 10
                 * spec_show_type : 1
                 * spec_value_data :
                 */

                private String spec_value_name;
                private String spec_name;
                private int spec_id;
                private int spec_value_id;
                private int spec_show_type;
                private String spec_value_data;

                public String getSpec_value_name() {
                    return spec_value_name;
                }

                public void setSpec_value_name(String spec_value_name) {
                    this.spec_value_name = spec_value_name;
                }

                public String getSpec_name() {
                    return spec_name;
                }

                public void setSpec_name(String spec_name) {
                    this.spec_name = spec_name;
                }

                public int getSpec_id() {
                    return spec_id;
                }

                public void setSpec_id(int spec_id) {
                    this.spec_id = spec_id;
                }

                public int getSpec_value_id() {
                    return spec_value_id;
                }

                public void setSpec_value_id(int spec_value_id) {
                    this.spec_value_id = spec_value_id;
                }

                public int getSpec_show_type() {
                    return spec_show_type;
                }

                public void setSpec_show_type(int spec_show_type) {
                    this.spec_show_type = spec_show_type;
                }

                public String getSpec_value_data() {
                    return spec_value_data;
                }

                public void setSpec_value_data(String spec_value_data) {
                    this.spec_value_data = spec_value_data;
                }
            }
        }

        public static class GoodsSkuBean {
            /**
             * sku_id : 58
             * sku_name : 8G内存 4G独显
             * attr_value_items : 7:9;9:14
             * attr_value_items_format : 7:9;9:14
             * price : 300.00
             * promote_price : 300.00
             * stock : 5
             */

            private String sku_id;
            private String sku_name;
            private String attr_value_items;
            private String attr_value_items_format;
            private String price;
            private String promote_price;
            private String stock;

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

            public String getStock() {
                return stock;
            }

            public void setStock(String stock) {
                this.stock = stock;
            }
        }
    }
}
