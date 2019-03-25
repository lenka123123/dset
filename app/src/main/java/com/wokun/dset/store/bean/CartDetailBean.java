package com.wokun.dset.store.bean;


import java.util.List;

public class CartDetailBean {

    /**
     * status : 0001
     * msg : 成功
     * data : {"order_id":"9","order_no":"201903080000001","payment_type":"0","shipping_type":"1","receiver_name":"杨兵兵","receiver_mobile":"18502503216","receiver_province":"江苏省","receiver_city":"南京市","receiver_district":"浦口区","receiver_address":"旭日上城一区","shop_name":"官方旗舰店","goods_money":"110.00","order_money":"110.00","shipping_money":"0.00","order_status":"2","create_time":"1552032759","shipping_status":"1","orderGoods":[{"order_id":"9","goods_id":"2","goods_name":"牛排","sku_name":"100g 内蒙古 ","price":"110.00","num":"1","goods_picture":"3","pic_cover_small":"http://www.mall.com/upload/goods/20190215/eb12a1237db6a59df9d03f699796c6123.jpg"}],"shippingInfo":{"express_company":"圆通快递","express_no":"946513213213"}}
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
         * order_id : 9
         * order_no : 201903080000001
         * payment_type : 0
         * shipping_type : 1
         * receiver_name : 杨兵兵
         * receiver_mobile : 18502503216
         * receiver_province : 江苏省
         * receiver_city : 南京市
         * receiver_district : 浦口区
         * receiver_address : 旭日上城一区
         * shop_name : 官方旗舰店
         * goods_money : 110.00
         * order_money : 110.00
         * shipping_money : 0.00
         * order_status : 2
         * create_time : 1552032759
         * shipping_status : 1
         * orderGoods : [{"order_id":"9","goods_id":"2","goods_name":"牛排","sku_name":"100g 内蒙古 ","price":"110.00","num":"1","goods_picture":"3","pic_cover_small":"http://www.mall.com/upload/goods/20190215/eb12a1237db6a59df9d03f699796c6123.jpg"}]
         * shippingInfo : {"express_company":"圆通快递","express_no":"946513213213"}
         */

        private String order_id;
        private String order_no;
        private String payment_type;
        private String shipping_type;
        private String receiver_name;
        private String receiver_mobile;
        private String receiver_province;
        private String receiver_city;
        private String receiver_district;
        private String receiver_address;
        private String shop_name;
        private String goods_money;
        private String order_money;
        private String shipping_money;
        private String order_status;
        private String create_time;
        private String shipping_status;
        private ShippingInfoBean shippingInfo;
        private List<OrderGoodsBean> orderGoods;

        public String getOrder_id() {
            return order_id;
        }

        public void setOrder_id(String order_id) {
            this.order_id = order_id;
        }

        public String getOrder_no() {
            return order_no;
        }

        public void setOrder_no(String order_no) {
            this.order_no = order_no;
        }

        public String getPayment_type() {
            return payment_type;
        }

        public void setPayment_type(String payment_type) {
            this.payment_type = payment_type;
        }

        public String getShipping_type() {
            return shipping_type;
        }

        public void setShipping_type(String shipping_type) {
            this.shipping_type = shipping_type;
        }

        public String getReceiver_name() {
            return receiver_name;
        }

        public void setReceiver_name(String receiver_name) {
            this.receiver_name = receiver_name;
        }

        public String getReceiver_mobile() {
            return receiver_mobile;
        }

        public void setReceiver_mobile(String receiver_mobile) {
            this.receiver_mobile = receiver_mobile;
        }

        public String getReceiver_province() {
            return receiver_province;
        }

        public void setReceiver_province(String receiver_province) {
            this.receiver_province = receiver_province;
        }

        public String getReceiver_city() {
            return receiver_city;
        }

        public void setReceiver_city(String receiver_city) {
            this.receiver_city = receiver_city;
        }

        public String getReceiver_district() {
            return receiver_district;
        }

        public void setReceiver_district(String receiver_district) {
            this.receiver_district = receiver_district;
        }

        public String getReceiver_address() {
            return receiver_address;
        }

        public void setReceiver_address(String receiver_address) {
            this.receiver_address = receiver_address;
        }

        public String getShop_name() {
            return shop_name;
        }

        public void setShop_name(String shop_name) {
            this.shop_name = shop_name;
        }

        public String getGoods_money() {
            return goods_money;
        }

        public void setGoods_money(String goods_money) {
            this.goods_money = goods_money;
        }

        public String getOrder_money() {
            return order_money;
        }

        public void setOrder_money(String order_money) {
            this.order_money = order_money;
        }

        public String getShipping_money() {
            return shipping_money;
        }

        public void setShipping_money(String shipping_money) {
            this.shipping_money = shipping_money;
        }

        public String getOrder_status() {
            return order_status;
        }

        public void setOrder_status(String order_status) {
            this.order_status = order_status;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public String getShipping_status() {
            return shipping_status;
        }

        public void setShipping_status(String shipping_status) {
            this.shipping_status = shipping_status;
        }

        public ShippingInfoBean getShippingInfo() {
            return shippingInfo;
        }

        public void setShippingInfo(ShippingInfoBean shippingInfo) {
            this.shippingInfo = shippingInfo;
        }

        public List<OrderGoodsBean> getOrderGoods() {
            return orderGoods;
        }

        public void setOrderGoods(List<OrderGoodsBean> orderGoods) {
            this.orderGoods = orderGoods;
        }

        public static class ShippingInfoBean {
            /**
             * express_company : 圆通快递
             * express_no : 946513213213
             */

            private String express_company;
            private String express_no;

            public String getExpress_company() {
                return express_company;
            }

            public void setExpress_company(String express_company) {
                this.express_company = express_company;
            }

            public String getExpress_no() {
                return express_no;
            }

            public void setExpress_no(String express_no) {
                this.express_no = express_no;
            }
        }

        public static class OrderGoodsBean {
            /**
             * order_id : 9
             * goods_id : 2
             * goods_name : 牛排
             * sku_name : 100g 内蒙古
             * price : 110.00
             * num : 1
             * goods_picture : 3
             * pic_cover_small : http://www.mall.com/upload/goods/20190215/eb12a1237db6a59df9d03f699796c6123.jpg
             */

            private String order_id;
            private String goods_id;
            private String goods_name;
            private String sku_name;
            private String price;
            private String num;
            private String goods_picture;
            private String pic_cover_small;

            public String getOrder_id() {
                return order_id;
            }

            public void setOrder_id(String order_id) {
                this.order_id = order_id;
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

            public String getSku_name() {
                return sku_name;
            }

            public void setSku_name(String sku_name) {
                this.sku_name = sku_name;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public String getNum() {
                return num;
            }

            public void setNum(String num) {
                this.num = num;
            }

            public String getGoods_picture() {
                return goods_picture;
            }

            public void setGoods_picture(String goods_picture) {
                this.goods_picture = goods_picture;
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
