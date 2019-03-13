package com.wokun.dset.store.bean;

import java.util.List;

public class DStoreHome {


    /**
     * status : 0001
     * msg : 成功
     * data : {"ad":[{"pic_url":"http://dsytcdn.chinafjjdkj.cn/upload/goods/20181226/40ed0263a20395beee0e7ca54fd4be71.jpeg","goods_id":"2","url":"http://www.baidu.com"},{"pic_url":"http://dsytcdn.chinafjjdkj.cn/upload/goods/20190121/f86746e20fa3f0c920343821ddb4a85d.jpg","goods_id":"1","url":"http://www.baidu.com"}],"promotionInfo":{"discount_id":"3","start_time":"1552270036","end_time":"1552529239","promotionGoods":[{"discount_goods_id":"5","discount_id":"3","goods_id":"9","discount":"8.00","goods_name":"Swisse蔓越莓泡腾片 60片","price":"188.00","promotion_price":"150.40","pic_cover_big":"http://dsytcdn.chinafjjdkj.cn/upload/goods/20190121/098dd267d8f37111b0b42bacfb11d9e91.jpg","pic_cover_mid":"http://dsytcdn.chinafjjdkj.cn/upload/goods/20190121/098dd267d8f37111b0b42bacfb11d9e92.jpg","pic_cover_small":"http://dsytcdn.chinafjjdkj.cn/upload/goods/20190121/098dd267d8f37111b0b42bacfb11d9e93.jpg"},{"discount_goods_id":"6","discount_id":"3","goods_id":"10","discount":"8.00","goods_name":"澳洲进口 Swisse乳清蛋白粉450g 香草味","price":"449.00","promotion_price":"359.20","pic_cover_big":"http://dsytcdn.chinafjjdkj.cn/upload/goods/20190121/11b36146a6b2d0f03ae03739e5ea96011.jpg","pic_cover_mid":"http://dsytcdn.chinafjjdkj.cn/upload/goods/20190121/11b36146a6b2d0f03ae03739e5ea96012.jpg","pic_cover_small":"http://dsytcdn.chinafjjdkj.cn/upload/goods/20190121/11b36146a6b2d0f03ae03739e5ea96013.jpg"},{"discount_goods_id":"7","discount_id":"3","goods_id":"11","discount":"8.00","goods_name":"Swisse深海鱼油胶囊 400粒","price":"438.00","promotion_price":"350.40","pic_cover_big":"http://dsytcdn.chinafjjdkj.cn/upload/goods/20190121/9f738370013fa665c5a4df856d2faded1.jpg","pic_cover_mid":"http://dsytcdn.chinafjjdkj.cn/upload/goods/20190121/9f738370013fa665c5a4df856d2faded2.jpg","pic_cover_small":"http://dsytcdn.chinafjjdkj.cn/upload/goods/20190121/9f738370013fa665c5a4df856d2faded3.jpg"}]},"top6":[{"pic_url":"http://api.dasether.com/img/zhd_1.png","goods_id":"2","url":"http://www.baidu.com"},{"pic_url":"http://api.dasether.com/img/zhd_2.png","goods_id":"1","url":"http://www.baidu.com"},{"pic_url":"http://api.dasether.com/img/zhd_3.png","goods_id":"1","url":"http://www.baidu.com"},{"pic_url":"http://api.dasether.com/img/zhd_4.png","goods_id":"2","url":"http://www.baidu.com"},{"pic_url":"http://api.dasether.com/img/zhd_5.png","goods_id":"1","url":"http://www.baidu.com"},{"pic_url":"http://api.dasether.com/img/zhd_6.png","goods_id":"1","url":"http://www.baidu.com"}],"jingxuan":{"ad":{"pic_url":"http://api.dasether.com/img/ad.png","goods_id":"2","url":"http://www.baidu.com"},"goods":[{"goods_id":"37","goods_name":"清风/心相印卷纸1提10卷750g 家用无香卫生纸","introduction":"","shop_id":"0","pic_cover_small":"http://store.dasether.com/upload/goods/20190130/e9f7786d0f856a50ab0864e3221972433.jpg"},{"goods_id":"36","goods_name":"维达抽纸 超韧3层130抽24包整箱装面巾纸","introduction":"","shop_id":"0","pic_cover_small":"http://store.dasether.com/upload/goods/20190130/0230b620452408888b7d81ad693ae57b3.jpg"},{"goods_id":"35","goods_name":"三九纤体酵素原液  排毒瘦身佳品","introduction":"排毒瘦身佳品","shop_id":"0","pic_cover_small":"http://store.dasether.com/http://dsytcdn.chinafjjdkj.cn/upload/goods/20190122/e85b5be8fe92e87a8beef8372df308bf3.jpg"}]},"tuijian":[{"goods_id":"40","goods_name":"妙洁保鲜膜3包组合装 3包30米 送3包15米","price":"36.90","shop_id":"1","pic_cover_big":"http://store.dasether.com/upload/goods/20190130/9dc3ecba091e85167165f406c298db971.jpg"},{"goods_id":"39","goods_name":"妙洁神奇抹布5片装*3包 吸水不易掉毛不易沾油洗碗布","price":"44.70","shop_id":"0","pic_cover_big":"http://store.dasether.com/upload/goods/20190130/442b67fb1a1c586f3b5ed200826b2c551.jpg"},{"goods_id":"38","goods_name":"家用新一代去污钢丝球清洁球 10片装","price":"16.80","shop_id":"0","pic_cover_big":"http://store.dasether.com/upload/goods/20190130/b43ddba236408937a7bebfb3a0b78bc51.jpg"}]}
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
         * ad : [{"pic_url":"http://dsytcdn.chinafjjdkj.cn/upload/goods/20181226/40ed0263a20395beee0e7ca54fd4be71.jpeg","goods_id":"2","url":"http://www.baidu.com"},{"pic_url":"http://dsytcdn.chinafjjdkj.cn/upload/goods/20190121/f86746e20fa3f0c920343821ddb4a85d.jpg","goods_id":"1","url":"http://www.baidu.com"}]
         * promotionInfo : {"discount_id":"3","start_time":"1552270036","end_time":"1552529239","promotionGoods":[{"discount_goods_id":"5","discount_id":"3","goods_id":"9","discount":"8.00","goods_name":"Swisse蔓越莓泡腾片 60片","price":"188.00","promotion_price":"150.40","pic_cover_big":"http://dsytcdn.chinafjjdkj.cn/upload/goods/20190121/098dd267d8f37111b0b42bacfb11d9e91.jpg","pic_cover_mid":"http://dsytcdn.chinafjjdkj.cn/upload/goods/20190121/098dd267d8f37111b0b42bacfb11d9e92.jpg","pic_cover_small":"http://dsytcdn.chinafjjdkj.cn/upload/goods/20190121/098dd267d8f37111b0b42bacfb11d9e93.jpg"},{"discount_goods_id":"6","discount_id":"3","goods_id":"10","discount":"8.00","goods_name":"澳洲进口 Swisse乳清蛋白粉450g 香草味","price":"449.00","promotion_price":"359.20","pic_cover_big":"http://dsytcdn.chinafjjdkj.cn/upload/goods/20190121/11b36146a6b2d0f03ae03739e5ea96011.jpg","pic_cover_mid":"http://dsytcdn.chinafjjdkj.cn/upload/goods/20190121/11b36146a6b2d0f03ae03739e5ea96012.jpg","pic_cover_small":"http://dsytcdn.chinafjjdkj.cn/upload/goods/20190121/11b36146a6b2d0f03ae03739e5ea96013.jpg"},{"discount_goods_id":"7","discount_id":"3","goods_id":"11","discount":"8.00","goods_name":"Swisse深海鱼油胶囊 400粒","price":"438.00","promotion_price":"350.40","pic_cover_big":"http://dsytcdn.chinafjjdkj.cn/upload/goods/20190121/9f738370013fa665c5a4df856d2faded1.jpg","pic_cover_mid":"http://dsytcdn.chinafjjdkj.cn/upload/goods/20190121/9f738370013fa665c5a4df856d2faded2.jpg","pic_cover_small":"http://dsytcdn.chinafjjdkj.cn/upload/goods/20190121/9f738370013fa665c5a4df856d2faded3.jpg"}]}
         * top6 : [{"pic_url":"http://api.dasether.com/img/zhd_1.png","goods_id":"2","url":"http://www.baidu.com"},{"pic_url":"http://api.dasether.com/img/zhd_2.png","goods_id":"1","url":"http://www.baidu.com"},{"pic_url":"http://api.dasether.com/img/zhd_3.png","goods_id":"1","url":"http://www.baidu.com"},{"pic_url":"http://api.dasether.com/img/zhd_4.png","goods_id":"2","url":"http://www.baidu.com"},{"pic_url":"http://api.dasether.com/img/zhd_5.png","goods_id":"1","url":"http://www.baidu.com"},{"pic_url":"http://api.dasether.com/img/zhd_6.png","goods_id":"1","url":"http://www.baidu.com"}]
         * jingxuan : {"ad":{"pic_url":"http://api.dasether.com/img/ad.png","goods_id":"2","url":"http://www.baidu.com"},"goods":[{"goods_id":"37","goods_name":"清风/心相印卷纸1提10卷750g 家用无香卫生纸","introduction":"","shop_id":"0","pic_cover_small":"http://store.dasether.com/upload/goods/20190130/e9f7786d0f856a50ab0864e3221972433.jpg"},{"goods_id":"36","goods_name":"维达抽纸 超韧3层130抽24包整箱装面巾纸","introduction":"","shop_id":"0","pic_cover_small":"http://store.dasether.com/upload/goods/20190130/0230b620452408888b7d81ad693ae57b3.jpg"},{"goods_id":"35","goods_name":"三九纤体酵素原液  排毒瘦身佳品","introduction":"排毒瘦身佳品","shop_id":"0","pic_cover_small":"http://store.dasether.com/http://dsytcdn.chinafjjdkj.cn/upload/goods/20190122/e85b5be8fe92e87a8beef8372df308bf3.jpg"}]}
         * tuijian : [{"goods_id":"40","goods_name":"妙洁保鲜膜3包组合装 3包30米 送3包15米","price":"36.90","shop_id":"1","pic_cover_big":"http://store.dasether.com/upload/goods/20190130/9dc3ecba091e85167165f406c298db971.jpg"},{"goods_id":"39","goods_name":"妙洁神奇抹布5片装*3包 吸水不易掉毛不易沾油洗碗布","price":"44.70","shop_id":"0","pic_cover_big":"http://store.dasether.com/upload/goods/20190130/442b67fb1a1c586f3b5ed200826b2c551.jpg"},{"goods_id":"38","goods_name":"家用新一代去污钢丝球清洁球 10片装","price":"16.80","shop_id":"0","pic_cover_big":"http://store.dasether.com/upload/goods/20190130/b43ddba236408937a7bebfb3a0b78bc51.jpg"}]
         */

        private PromotionInfoBean promotionInfo;
        private JingxuanBean jingxuan;
        private List<AdBeanX> ad;
        private List<Top6Bean> top6;
        private List<TuijianBean> tuijian;

        public PromotionInfoBean getPromotionInfo() {
            return promotionInfo;
        }

        public void setPromotionInfo(PromotionInfoBean promotionInfo) {
            this.promotionInfo = promotionInfo;
        }

        public JingxuanBean getJingxuan() {
            return jingxuan;
        }

        public void setJingxuan(JingxuanBean jingxuan) {
            this.jingxuan = jingxuan;
        }

        public List<AdBeanX> getAd() {
            return ad;
        }

        public void setAd(List<AdBeanX> ad) {
            this.ad = ad;
        }

        public List<Top6Bean> getTop6() {
            return top6;
        }

        public void setTop6(List<Top6Bean> top6) {
            this.top6 = top6;
        }

        public List<TuijianBean> getTuijian() {
            return tuijian;
        }

        public void setTuijian(List<TuijianBean> tuijian) {
            this.tuijian = tuijian;
        }

        public static class PromotionInfoBean {
            /**
             * discount_id : 3
             * start_time : 1552270036
             * end_time : 1552529239
             * promotionGoods : [{"discount_goods_id":"5","discount_id":"3","goods_id":"9","discount":"8.00","goods_name":"Swisse蔓越莓泡腾片 60片","price":"188.00","promotion_price":"150.40","pic_cover_big":"http://dsytcdn.chinafjjdkj.cn/upload/goods/20190121/098dd267d8f37111b0b42bacfb11d9e91.jpg","pic_cover_mid":"http://dsytcdn.chinafjjdkj.cn/upload/goods/20190121/098dd267d8f37111b0b42bacfb11d9e92.jpg","pic_cover_small":"http://dsytcdn.chinafjjdkj.cn/upload/goods/20190121/098dd267d8f37111b0b42bacfb11d9e93.jpg"},{"discount_goods_id":"6","discount_id":"3","goods_id":"10","discount":"8.00","goods_name":"澳洲进口 Swisse乳清蛋白粉450g 香草味","price":"449.00","promotion_price":"359.20","pic_cover_big":"http://dsytcdn.chinafjjdkj.cn/upload/goods/20190121/11b36146a6b2d0f03ae03739e5ea96011.jpg","pic_cover_mid":"http://dsytcdn.chinafjjdkj.cn/upload/goods/20190121/11b36146a6b2d0f03ae03739e5ea96012.jpg","pic_cover_small":"http://dsytcdn.chinafjjdkj.cn/upload/goods/20190121/11b36146a6b2d0f03ae03739e5ea96013.jpg"},{"discount_goods_id":"7","discount_id":"3","goods_id":"11","discount":"8.00","goods_name":"Swisse深海鱼油胶囊 400粒","price":"438.00","promotion_price":"350.40","pic_cover_big":"http://dsytcdn.chinafjjdkj.cn/upload/goods/20190121/9f738370013fa665c5a4df856d2faded1.jpg","pic_cover_mid":"http://dsytcdn.chinafjjdkj.cn/upload/goods/20190121/9f738370013fa665c5a4df856d2faded2.jpg","pic_cover_small":"http://dsytcdn.chinafjjdkj.cn/upload/goods/20190121/9f738370013fa665c5a4df856d2faded3.jpg"}]
             */

            private String discount_id;
            private String start_time;
            private String end_time;
            private List<PromotionGoodsBean> promotionGoods;

            public String getDiscount_id() {
                return discount_id;
            }

            public void setDiscount_id(String discount_id) {
                this.discount_id = discount_id;
            }

            public String getStart_time() {
                return start_time;
            }

            public void setStart_time(String start_time) {
                this.start_time = start_time;
            }

            public String getEnd_time() {
                return end_time;
            }

            public void setEnd_time(String end_time) {
                this.end_time = end_time;
            }

            public List<PromotionGoodsBean> getPromotionGoods() {
                return promotionGoods;
            }

            public void setPromotionGoods(List<PromotionGoodsBean> promotionGoods) {
                this.promotionGoods = promotionGoods;
            }

            public static class PromotionGoodsBean {
                /**
                 * discount_goods_id : 5
                 * discount_id : 3
                 * goods_id : 9
                 * discount : 8.00
                 * goods_name : Swisse蔓越莓泡腾片 60片
                 * price : 188.00
                 * promotion_price : 150.40
                 * pic_cover_big : http://dsytcdn.chinafjjdkj.cn/upload/goods/20190121/098dd267d8f37111b0b42bacfb11d9e91.jpg
                 * pic_cover_mid : http://dsytcdn.chinafjjdkj.cn/upload/goods/20190121/098dd267d8f37111b0b42bacfb11d9e92.jpg
                 * pic_cover_small : http://dsytcdn.chinafjjdkj.cn/upload/goods/20190121/098dd267d8f37111b0b42bacfb11d9e93.jpg
                 */

                private String discount_goods_id;
                private String discount_id;
                private String goods_id;
                private String discount;
                private String goods_name;
                private String price;
                private String promotion_price;
                private String pic_cover_big;
                private String pic_cover_mid;
                private String pic_cover_small;

                public String getDiscount_goods_id() {
                    return discount_goods_id;
                }

                public void setDiscount_goods_id(String discount_goods_id) {
                    this.discount_goods_id = discount_goods_id;
                }

                public String getDiscount_id() {
                    return discount_id;
                }

                public void setDiscount_id(String discount_id) {
                    this.discount_id = discount_id;
                }

                public String getGoods_id() {
                    return goods_id;
                }

                public void setGoods_id(String goods_id) {
                    this.goods_id = goods_id;
                }

                public String getDiscount() {
                    return discount;
                }

                public void setDiscount(String discount) {
                    this.discount = discount;
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

                public String getPromotion_price() {
                    return promotion_price;
                }

                public void setPromotion_price(String promotion_price) {
                    this.promotion_price = promotion_price;
                }

                public String getPic_cover_big() {
                    return pic_cover_big;
                }

                public void setPic_cover_big(String pic_cover_big) {
                    this.pic_cover_big = pic_cover_big;
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

        public static class JingxuanBean {
            /**
             * ad : {"pic_url":"http://api.dasether.com/img/ad.png","goods_id":"2","url":"http://www.baidu.com"}
             * goods : [{"goods_id":"37","goods_name":"清风/心相印卷纸1提10卷750g 家用无香卫生纸","introduction":"","shop_id":"0","pic_cover_small":"http://store.dasether.com/upload/goods/20190130/e9f7786d0f856a50ab0864e3221972433.jpg"},{"goods_id":"36","goods_name":"维达抽纸 超韧3层130抽24包整箱装面巾纸","introduction":"","shop_id":"0","pic_cover_small":"http://store.dasether.com/upload/goods/20190130/0230b620452408888b7d81ad693ae57b3.jpg"},{"goods_id":"35","goods_name":"三九纤体酵素原液  排毒瘦身佳品","introduction":"排毒瘦身佳品","shop_id":"0","pic_cover_small":"http://store.dasether.com/http://dsytcdn.chinafjjdkj.cn/upload/goods/20190122/e85b5be8fe92e87a8beef8372df308bf3.jpg"}]
             */

            private AdBean ad;
            private List<GoodsBean> goods;

            public AdBean getAd() {
                return ad;
            }

            public void setAd(AdBean ad) {
                this.ad = ad;
            }

            public List<GoodsBean> getGoods() {
                return goods;
            }

            public void setGoods(List<GoodsBean> goods) {
                this.goods = goods;
            }

            public static class AdBean {
                /**
                 * pic_url : http://api.dasether.com/img/ad.png
                 * goods_id : 2
                 * url : http://www.baidu.com
                 */

                private String pic_url;
                private String goods_id;
                private String url;

                public String getPic_url() {
                    return pic_url;
                }

                public void setPic_url(String pic_url) {
                    this.pic_url = pic_url;
                }

                public String getGoods_id() {
                    return goods_id;
                }

                public void setGoods_id(String goods_id) {
                    this.goods_id = goods_id;
                }

                public String getUrl() {
                    return url;
                }

                public void setUrl(String url) {
                    this.url = url;
                }
            }

            public static class GoodsBean {
                /**
                 * goods_id : 37
                 * goods_name : 清风/心相印卷纸1提10卷750g 家用无香卫生纸
                 * introduction :
                 * shop_id : 0
                 * pic_cover_small : http://store.dasether.com/upload/goods/20190130/e9f7786d0f856a50ab0864e3221972433.jpg
                 */

                private String goods_id;
                private String goods_name;
                private String introduction;
                private String shop_id;
                private String pic_cover_small;

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

                public String getIntroduction() {
                    return introduction;
                }

                public void setIntroduction(String introduction) {
                    this.introduction = introduction;
                }

                public String getShop_id() {
                    return shop_id;
                }

                public void setShop_id(String shop_id) {
                    this.shop_id = shop_id;
                }

                public String getPic_cover_small() {
                    return pic_cover_small;
                }

                public void setPic_cover_small(String pic_cover_small) {
                    this.pic_cover_small = pic_cover_small;
                }
            }
        }

        public static class AdBeanX {
            /**
             * pic_url : http://dsytcdn.chinafjjdkj.cn/upload/goods/20181226/40ed0263a20395beee0e7ca54fd4be71.jpeg
             * goods_id : 2
             * url : http://www.baidu.com
             */

            private String pic_url;
            private String goods_id;
            private String url;

            public String getPic_url() {
                return pic_url;
            }

            public void setPic_url(String pic_url) {
                this.pic_url = pic_url;
            }

            public String getGoods_id() {
                return goods_id;
            }

            public void setGoods_id(String goods_id) {
                this.goods_id = goods_id;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }
        }

        public static class Top6Bean {
            /**
             * pic_url : http://api.dasether.com/img/zhd_1.png
             * goods_id : 2
             * url : http://www.baidu.com
             */

            private String pic_url;
            private String goods_id;
            private String url;

            public String getPic_url() {
                return pic_url;
            }

            public void setPic_url(String pic_url) {
                this.pic_url = pic_url;
            }

            public String getGoods_id() {
                return goods_id;
            }

            public void setGoods_id(String goods_id) {
                this.goods_id = goods_id;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }
        }

        public static class TuijianBean {
            /**
             * goods_id : 40
             * goods_name : 妙洁保鲜膜3包组合装 3包30米 送3包15米
             * price : 36.90
             * shop_id : 1
             * pic_cover_big : http://store.dasether.com/upload/goods/20190130/9dc3ecba091e85167165f406c298db971.jpg
             */

            private String goods_id;
            private String goods_name;
            private String price;
            private String shop_id;
            private String pic_cover_big;

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

            public String getPic_cover_big() {
                return pic_cover_big;
            }

            public void setPic_cover_big(String pic_cover_big) {
                this.pic_cover_big = pic_cover_big;
            }
        }
    }
}
