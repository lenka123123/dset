package com.wokun.dset.store.bean;


import java.io.Serializable;
import java.util.List;

public class CartList {

    /**
     * status : 0001
     * msg : 成功
     * data : {"cartListInfo":[{"shop_id":"0","shop_name":"官方旗舰店","goods_item":[{"cart_id":"13","goods_id":"2","goods_name":"牛排","sku_id":"6","sku_name":"100g 云南 ","price":"130.00","num":"3","stock":"8","state":"1","goods_picture":"http://www.mall.com/upload/goods/20190215/eb12a1237db6a59df9d03f699796c6123.jpg"},{"cart_id":"14","goods_id":"2","goods_name":"牛排","sku_id":"7","sku_name":"200g 澳洲 ","price":"220.00","num":"3","stock":"3","state":"1","goods_picture":"http://www.mall.com/upload/goods/20190215/eb12a1237db6a59df9d03f699796c6123.jpg"},{"cart_id":"16","goods_id":"2","goods_name":"牛排","sku_id":"4","sku_name":"100g 澳洲 ","price":"120.00","num":"1","stock":"6","state":"1","goods_picture":"http://www.mall.com/upload/goods/20190215/eb12a1237db6a59df9d03f699796c6123.jpg"},{"cart_id":"17","goods_id":"1","goods_name":"小米空气净化器2","sku_id":"1","sku_name":"","price":"799.00","num":"1","stock":"6","state":"1","goods_picture":"http://www.mall.com/upload/goods/20190213/31dd1d70196217931f7ba0bddcbfe5e53.png"}]}]}
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
        private List<CartListInfoBean> cartListInfo;

        public List<CartListInfoBean> getCartListInfo() {
            return cartListInfo;
        }

        public void setCartListInfo(List<CartListInfoBean> cartListInfo) {
            this.cartListInfo = cartListInfo;
        }

        public static class CartListInfoBean {
            /**
             * shop_id : 0
             * shop_name : 官方旗舰店
             * goods_item : [{"cart_id":"13","goods_id":"2","goods_name":"牛排","sku_id":"6","sku_name":"100g 云南 ","price":"130.00","num":"3","stock":"8","state":"1","goods_picture":"http://www.mall.com/upload/goods/20190215/eb12a1237db6a59df9d03f699796c6123.jpg"},{"cart_id":"14","goods_id":"2","goods_name":"牛排","sku_id":"7","sku_name":"200g 澳洲 ","price":"220.00","num":"3","stock":"3","state":"1","goods_picture":"http://www.mall.com/upload/goods/20190215/eb12a1237db6a59df9d03f699796c6123.jpg"},{"cart_id":"16","goods_id":"2","goods_name":"牛排","sku_id":"4","sku_name":"100g 澳洲 ","price":"120.00","num":"1","stock":"6","state":"1","goods_picture":"http://www.mall.com/upload/goods/20190215/eb12a1237db6a59df9d03f699796c6123.jpg"},{"cart_id":"17","goods_id":"1","goods_name":"小米空气净化器2","sku_id":"1","sku_name":"","price":"799.00","num":"1","stock":"6","state":"1","goods_picture":"http://www.mall.com/upload/goods/20190213/31dd1d70196217931f7ba0bddcbfe5e53.png"}]
             */

            private String shop_id;
            private String shop_name;
            private List<GoodsItemBean> goods_item;

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

            public List<GoodsItemBean> getGoods_item() {
                return goods_item;
            }

            public void setGoods_item(List<GoodsItemBean> goods_item) {
                this.goods_item = goods_item;
            }

            public static class GoodsItemBean  implements Serializable {

                public boolean isSelect() {
                    return isSelect;
                }

                public void setSelect(boolean select) {
                    isSelect = select;
                }

                public boolean isShopSelect() {
                    return isShopSelect;
                }

                public void setShopSelect(boolean shopSelect) {
                    isShopSelect = shopSelect;
                }

                public int getIsFirst() {
                    return isFirst;
                }

                public void setIsFirst(int isFirst) {
                    this.isFirst = isFirst;
                }

                public String getStore_name() {
                    return store_name;
                }

                public void setStore_name(String store_name) {
                    this.store_name = store_name;
                }

                private String store_name = "";
                private boolean isSelect = false;
                private boolean isShopSelect = false;
                private int isFirst = 2;



                /**
                 * cart_id : 13
                 * goods_id : 2
                 * goods_name : 牛排
                 * sku_id : 6
                 * sku_name : 100g 云南
                 * price : 130.00
                 * num : 3
                 * stock : 8
                 * state : 1
                 * goods_picture : http://www.mall.com/upload/goods/20190215/eb12a1237db6a59df9d03f699796c6123.jpg
                 */

                private String cart_id;
                private String goods_id;
                private String goods_name;
                private String sku_id;
                private String sku_name;
                private String price;
                private String num;
                private String stock;
                private String state;
                private String goods_picture;

                public String getCart_id() {
                    return cart_id;
                }

                public void setCart_id(String cart_id) {
                    this.cart_id = cart_id;
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

                public String getStock() {
                    return stock;
                }

                public void setStock(String stock) {
                    this.stock = stock;
                }

                public String getState() {
                    return state;
                }

                public void setState(String state) {
                    this.state = state;
                }

                public String getGoods_picture() {
                    return goods_picture;
                }

                public void setGoods_picture(String goods_picture) {
                    this.goods_picture = goods_picture;
                }
            }
        }
    }
}
