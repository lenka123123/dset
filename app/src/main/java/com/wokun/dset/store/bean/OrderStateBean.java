package com.wokun.dset.store.bean;


import java.util.List;

public class OrderStateBean {

    /**
     * status : 0001
     * msg : 成功
     * data : {"orderInfo":[{"order_id":"94","order_no":"190321131643623","shop_name":"官方旗舰店","order_status":"1","order_money":"168.00","orderGoods":[{"order_id":"94","goods_id":"34","goods_name":"三九清体酵素原液300ml 清肠排毒 逆转时光","sku_name":"","price":"168.00","num":"1","goods_picture":"282","pic_cover_small":"http://dsytcdn.chinafjjdkj.cn/upload/goods/20190122/bc993578fd5a10f115f1463ead20e40f3.jpg"}],"goods_nums":1},{"order_id":"95","order_no":"190321132330178","shop_name":"官方旗舰店","order_status":"1","order_money":"668.00","orderGoods":[{"order_id":"95","goods_id":"15","goods_name":"神经酸 脑动力胶囊 美国进口 FDA认证","sku_name":"","price":"668.00","num":"1","goods_picture":"78","pic_cover_small":"http://dsytcdn.chinafjjdkj.cn/upload/goods/20190121/382ee1fb68117eb3c56246c3bed014c03.jpg"}],"goods_nums":1},{"order_id":"108","order_no":"190321141533739","shop_name":"官方旗舰店","order_status":"1","order_money":"80.00","orderGoods":[{"order_id":"108","goods_id":"31","goods_name":"时时养 小麦胚芽 袋装450克","sku_name":"","price":"80.00","num":"1","goods_picture":"247","pic_cover_small":"http://dsytcdn.chinafjjdkj.cn/upload/goods/20190122/d25124b65f25f98f4235d0287a8df68d3.jpg"}],"goods_nums":1},{"order_id":"112","order_no":"190321142323126","shop_name":"官方旗舰店","order_status":"1","order_money":"18.80","orderGoods":[{"order_id":"112","goods_id":"59","goods_name":"老北京特产 宫御坊正宗手工龙须酥糖","sku_name":"","price":"18.80","num":"1","goods_picture":"614","pic_cover_small":"http://store.dasether.com/upload/goods/20190320/c3a4e48c7a03defa5db781b87ffeb2e23.jpg"}],"goods_nums":1},{"order_id":"127","order_no":"190321144443503","shop_name":"官方旗舰店","order_status":"1","order_money":"12.90","orderGoods":[{"order_id":"127","goods_id":"52","goods_name":"黄山特产 梅干菜扣肉酥饼20个","sku_name":"","price":"12.90","num":"1","goods_picture":"530","pic_cover_small":"http://store.dasether.com/upload/goods/20190305/bc46a0bea365a07907b3d549c74da62b3.jpg"}],"goods_nums":1},{"order_id":"133","order_no":"190321162012185","shop_name":"官方旗舰店","order_status":"1","order_money":"18.80","orderGoods":[{"order_id":"133","goods_id":"59","goods_name":"老北京特产 宫御坊正宗手工龙须酥糖","sku_name":"","price":"18.80","num":"1","goods_picture":"614","pic_cover_small":"http://store.dasether.com/upload/goods/20190320/c3a4e48c7a03defa5db781b87ffeb2e23.jpg"}],"goods_nums":1},{"order_id":"135","order_no":"190322151235632","shop_name":"官方旗舰店","order_status":"1","order_money":"79.80","orderGoods":[{"order_id":"135","goods_id":"60","goods_name":"立白青柠洗洁精 大桶家庭装1kg*3桶","sku_name":"","price":"39.90","num":"1","goods_picture":"646","pic_cover_small":"http://store.dasether.com/upload/goods/20190322/d5ef1ced4c684765a1e8dfb9e08f22c03.jpg"},{"order_id":"135","goods_id":"43","goods_name":"海绵滚轮式拖把 家用拖地神器","sku_name":"","price":"39.90","num":"1","goods_picture":"398","pic_cover_small":"http://store.dasether.com/upload/goods/20190131/641ac87fb23cd7ff768e3e815b767a603.jpg"}],"goods_nums":2}]}
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
        private List<OrderInfoBean> orderInfo;

        public List<OrderInfoBean> getOrderInfo() {
            return orderInfo;
        }

        public void setOrderInfo(List<OrderInfoBean> orderInfo) {
            this.orderInfo = orderInfo;
        }

        public static class OrderInfoBean {
            /**
             * order_id : 94
             * order_no : 190321131643623
             * shop_name : 官方旗舰店
             * order_status : 1
             * order_money : 168.00
             * orderGoods : [{"order_id":"94","goods_id":"34","goods_name":"三九清体酵素原液300ml 清肠排毒 逆转时光","sku_name":"","price":"168.00","num":"1","goods_picture":"282","pic_cover_small":"http://dsytcdn.chinafjjdkj.cn/upload/goods/20190122/bc993578fd5a10f115f1463ead20e40f3.jpg"}]
             * goods_nums : 1
             */

            private String order_id;
            private String order_no;
            private String shop_name;
            private String order_status;
            private String order_money;
            private int goods_nums;
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

            public String getShop_name() {
                return shop_name;
            }

            public void setShop_name(String shop_name) {
                this.shop_name = shop_name;
            }

            public String getOrder_status() {
                return order_status;
            }

            public void setOrder_status(String order_status) {
                this.order_status = order_status;
            }

            public String getOrder_money() {
                return order_money;
            }

            public void setOrder_money(String order_money) {
                this.order_money = order_money;
            }

            public int getGoods_nums() {
                return goods_nums;
            }

            public void setGoods_nums(int goods_nums) {
                this.goods_nums = goods_nums;
            }

            public List<OrderGoodsBean> getOrderGoods() {
                return orderGoods;
            }

            public void setOrderGoods(List<OrderGoodsBean> orderGoods) {
                this.orderGoods = orderGoods;
            }

            public static class OrderGoodsBean {
                /**
                 * order_id : 94
                 * goods_id : 34
                 * goods_name : 三九清体酵素原液300ml 清肠排毒 逆转时光
                 * sku_name :
                 * price : 168.00
                 * num : 1
                 * goods_picture : 282
                 * pic_cover_small : http://dsytcdn.chinafjjdkj.cn/upload/goods/20190122/bc993578fd5a10f115f1463ead20e40f3.jpg
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
}
