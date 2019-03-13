package com.example.baselib.bean;

import java.io.Serializable;
import java.util.List;

public class MbHomeBean implements Serializable {


    /**
     * success : 1
     * msg : 操作成功
     * data : {"adList":[{"type":"2","cover_url":"http://yun-attachment.oss-cn-hangzhou.aliyuncs.com/njyb_logo/a1feb7a072bbbf5611a2bca8d2e7d94c.png","plate":"8","title":"\u201c互联网+\u201d 投票","type_id":"0","is_need_login":"1","url":"http://newwap.891jyb.com/?app=opus&amp;act=index","share_url":""}],"cateList":[{"id":"42","name":"","intro":"","parent_id":"0","type":"1","sort_order":"50","is_show":"1","image":"","category_id":"42"},{"id":"40","name":"iivema","intro":"","parent_id":"0","type":"1","sort_order":"50","is_show":"1","image":"","category_id":"40"},{"id":"39","name":"lcthax","intro":"","parent_id":"0","type":"1","sort_order":"50","is_show":"1","image":"","category_id":"39"},{"id":"38","name":"lumbkl","intro":"","parent_id":"0","type":"1","sort_order":"50","is_show":"1","image":"","category_id":"38"},{"id":"37","name":"pynxbp","intro":"","parent_id":"0","type":"1","sort_order":"50","is_show":"1","image":"","category_id":"37"},{"id":"36","name":"azzwvu","intro":"","parent_id":"0","type":"1","sort_order":"50","is_show":"1","image":"","category_id":"36"},{"id":"35","name":"kffops","intro":"","parent_id":"0","type":"1","sort_order":"50","is_show":"1","image":"","category_id":"35"},{"id":"34","name":"uhtrcy","intro":"","parent_id":"0","type":"1","sort_order":"50","is_show":"1","image":"","category_id":"34"},{"id":"33","name":"ibzapw","intro":"","parent_id":"0","type":"1","sort_order":"50","is_show":"1","image":"","category_id":"33"}],"homeCate":[{"id":"34","name":"uhtrcy","intro":"","parent_id":"0","type":"1","sort_order":"50","is_show":"1","image":"","category_id":"34"},{"id":"33","name":"ibzapw","intro":"","parent_id":"0","type":"1","sort_order":"50","is_show":"1","image":"","category_id":"33","product_list":[{"id":"30","category_id":"33","type":"1","product_name":"师仁","sales_sum":"557","stock_sum":"819","score":"5.0","click_count":"4041","comment_count":"708","price":"1000.00","type_price":"300.10","images":"","intro":"如果我们想交朋友，就要先为别人做些事\u2014\u2014那些需要花时间、体力、体贴、奉献才能做到的事。\u2014\u2014卡耐基","is_show":"1","is_postage":"1","postage_price":"5.19","is_recommend":"0","is_stock_limit":"0","time_limit_start":"0","time_limit_end":"0","mini_amount":"20","max_amount":"24","supplier_id":"0","add_user":"0","add_time":"1543982421","upd_user":"0","upd_time":"0","mark":"1","type_name":"美宝","cover":""}]},{"id":"41","name":"ydcizo","intro":"","parent_id":"0","type":"1","sort_order":"50","is_show":"1","image":"","category_id":"41","product_list":[{"id":"27","category_id":"41","type":"1","product_name":"司马士","sales_sum":"554","stock_sum":"816","score":"5.0","click_count":"4038","comment_count":"705","price":"1000.00","type_price":"300.10","images":"","intro":"心灵纯洁的人，生活充满甜蜜和喜悦。\u2014\u2014列夫·托尔斯泰","is_show":"1","is_postage":"1","postage_price":"5.16","is_recommend":"1","is_stock_limit":"0","time_limit_start":"0","time_limit_end":"0","mini_amount":"17","max_amount":"21","supplier_id":"0","add_user":"0","add_time":"1543982418","upd_user":"0","upd_time":"0","mark":"1","type_name":"美宝","cover":""},{"id":"24","category_id":"41","type":"1","product_name":"太叔娜霄","sales_sum":"551","stock_sum":"813","score":"5.0","click_count":"4035","comment_count":"702","price":"1000.00","type_price":"300.10","images":"","intro":"心灵纯洁的人，生活充满甜蜜和喜悦。\u2014\u2014列夫·托尔斯泰","is_show":"1","is_postage":"1","postage_price":"5.13","is_recommend":"1","is_stock_limit":"0","time_limit_start":"0","time_limit_end":"0","mini_amount":"14","max_amount":"18","supplier_id":"0","add_user":"0","add_time":"1543982415","upd_user":"0","upd_time":"0","mark":"1","type_name":"美宝","cover":""}]},{"id":"35","name":"kffops","intro":"","parent_id":"0","type":"1","sort_order":"50","is_show":"1","image":"","category_id":"35"},{"id":"35","name":"kffops","intro":"","parent_id":"0","type":"1","sort_order":"50","is_show":"1","image":"","category_id":"35"}],"middleList":[{"type":"2","cover_url":"http://yun-attachment.oss-cn-hangzhou.aliyuncs.com/njyb_logo/a1feb7a072bbbf5611a2bca8d2e7d94c.png","plate":"8","title":"\u201c互联网+\u201d 投票","type_id":"0","is_need_login":"1","url":"http://newwap.891jyb.com/?app=opus&amp;act=index","share_url":""}],"recCate":[{"cond_id":"2","cond_name":"推荐","cond_introduce":"猜你喜欢"},{"cond_id":"3","cond_name":"经典","cond_introduce":"热门兑换"},{"cond_id":"4","cond_name":"包邮","cond_introduce":"福利钜惠"}]}
     * code : 10000
     * request_id : 6fae3a57c8589728
     */

    private String success;
    private String msg;
    private DataBean data;
    private String code;
    private String request_id;

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getRequest_id() {
        return request_id;
    }

    public void setRequest_id(String request_id) {
        this.request_id = request_id;
    }

    public static class DataBean {
        //广告
        private List<AdListBean> adList;
        //分类
        private List<CateListBean> cateList;
        //中间数据
        private List<HomeCateBean> homeCate;
        //
        private List<MiddleListBean> middleList;
        //底部tab
        private List<RecCateBean> recCate;

        public List<AdListBean> getAdList() {
            return adList;
        }

        public void setAdList(List<AdListBean> adList) {
            this.adList = adList;
        }

        public List<CateListBean> getCateList() {
            return cateList;
        }

        public void setCateList(List<CateListBean> cateList) {
            this.cateList = cateList;
        }

        public List<HomeCateBean> getHomeCate() {
            return homeCate;
        }

        public void setHomeCate(List<HomeCateBean> homeCate) {
            this.homeCate = homeCate;
        }

        public List<MiddleListBean> getMiddleList() {
            return middleList;
        }

        public void setMiddleList(List<MiddleListBean> middleList) {
            this.middleList = middleList;
        }

        public List<RecCateBean> getRecCate() {
            return recCate;
        }

        public void setRecCate(List<RecCateBean> recCate) {
            this.recCate = recCate;
        }

        public static class AdListBean {
            /**
             * type : 2
             * cover_url : http://yun-attachment.oss-cn-hangzhou.aliyuncs.com/njyb_logo/a1feb7a072bbbf5611a2bca8d2e7d94c.png
             * plate : 8
             * title : “互联网+” 投票
             * type_id : 0
             * is_need_login : 1
             * url : http://newwap.891jyb.com/?app=opus&amp;act=index
             * share_url :
             */

            private String type;
            private String cover_url;
            private String plate;
            private String title;
            private String type_id;
            private String is_need_login;
            private String url;
            private String share_url;

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getCover_url() {
                return cover_url;
            }

            public void setCover_url(String cover_url) {
                this.cover_url = cover_url;
            }

            public String getPlate() {
                return plate;
            }

            public void setPlate(String plate) {
                this.plate = plate;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getType_id() {
                return type_id;
            }

            public void setType_id(String type_id) {
                this.type_id = type_id;
            }

            public String getIs_need_login() {
                return is_need_login;
            }

            public void setIs_need_login(String is_need_login) {
                this.is_need_login = is_need_login;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getShare_url() {
                return share_url;
            }

            public void setShare_url(String share_url) {
                this.share_url = share_url;
            }
        }

        public static class CateListBean implements Serializable {
            /**
             * id : 42
             * name :
             * intro :
             * parent_id : 0
             * type : 1
             * sort_order : 50
             * is_show : 1
             * image :
             * category_id : 42
             */

            private String id;
            private String name;
            private String intro;
            private String parent_id;
            private String type;
            private String sort_order;
            private String is_show;
            private String image;
            private String category_id;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getIntro() {
                return intro;
            }

            public void setIntro(String intro) {
                this.intro = intro;
            }

            public String getParent_id() {
                return parent_id;
            }

            public void setParent_id(String parent_id) {
                this.parent_id = parent_id;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getSort_order() {
                return sort_order;
            }

            public void setSort_order(String sort_order) {
                this.sort_order = sort_order;
            }

            public String getIs_show() {
                return is_show;
            }

            public void setIs_show(String is_show) {
                this.is_show = is_show;
            }

            public String getImage() {
                return image;
            }

            public void setImage(String image) {
                this.image = image;
            }

            public String getCategory_id() {
                return category_id;
            }

            public void setCategory_id(String category_id) {
                this.category_id = category_id;
            }
        }

        public static class HomeCateBean {
            /**
             * id : 34
             * name : uhtrcy
             * intro :
             * parent_id : 0
             * type : 1
             * sort_order : 50
             * is_show : 1
             * image :
             * category_id : 34
             * product_list : [{"id":"30","category_id":"33","type":"1","product_name":"师仁","sales_sum":"557","stock_sum":"819","score":"5.0","click_count":"4041","comment_count":"708","price":"1000.00","type_price":"300.10","images":"","intro":"如果我们想交朋友，就要先为别人做些事\u2014\u2014那些需要花时间、体力、体贴、奉献才能做到的事。\u2014\u2014卡耐基","is_show":"1","is_postage":"1","postage_price":"5.19","is_recommend":"0","is_stock_limit":"0","time_limit_start":"0","time_limit_end":"0","mini_amount":"20","max_amount":"24","supplier_id":"0","add_user":"0","add_time":"1543982421","upd_user":"0","upd_time":"0","mark":"1","type_name":"美宝","cover":""}]
             */

            private String id;
            private String name;
            private String intro;
            private String parent_id;
            private String type;
            private String sort_order;
            private String is_show;
            private String image;
            private String category_id;
            private List<ProductListBean> product_list;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getIntro() {
                return intro;
            }

            public void setIntro(String intro) {
                this.intro = intro;
            }

            public String getParent_id() {
                return parent_id;
            }

            public void setParent_id(String parent_id) {
                this.parent_id = parent_id;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getSort_order() {
                return sort_order;
            }

            public void setSort_order(String sort_order) {
                this.sort_order = sort_order;
            }

            public String getIs_show() {
                return is_show;
            }

            public void setIs_show(String is_show) {
                this.is_show = is_show;
            }

            public String getImage() {
                return image;
            }

            public void setImage(String image) {
                this.image = image;
            }

            public String getCategory_id() {
                return category_id;
            }

            public void setCategory_id(String category_id) {
                this.category_id = category_id;
            }

            public List<ProductListBean> getProduct_list() {
                return product_list;
            }

            public void setProduct_list(List<ProductListBean> product_list) {
                this.product_list = product_list;
            }

            public static class ProductListBean {
                /**
                 * id : 30
                 * category_id : 33
                 * type : 1
                 * product_name : 师仁
                 * sales_sum : 557
                 * stock_sum : 819
                 * score : 5.0
                 * click_count : 4041
                 * comment_count : 708
                 * price : 1000.00
                 * type_price : 300.10
                 * images :
                 * intro : 如果我们想交朋友，就要先为别人做些事——那些需要花时间、体力、体贴、奉献才能做到的事。——卡耐基
                 * is_show : 1
                 * is_postage : 1
                 * postage_price : 5.19
                 * is_recommend : 0
                 * is_stock_limit : 0
                 * time_limit_start : 0
                 * time_limit_end : 0
                 * mini_amount : 20
                 * max_amount : 24
                 * supplier_id : 0
                 * add_user : 0
                 * add_time : 1543982421
                 * upd_user : 0
                 * upd_time : 0
                 * mark : 1
                 * type_name : 美宝
                 * cover :
                 */

                private String id;
                private String category_id;
                private String type;
                private String product_name;
                private String sales_sum;
                private String stock_sum;
                private String score;
                private String click_count;
                private String comment_count;
                private String price;
                private String type_price;
                private String images;
                private String intro;
                private String is_show;
                private String is_postage;
                private String postage_price;
                private String is_recommend;
                private String is_stock_limit;
                private String time_limit_start;
                private String time_limit_end;
                private String mini_amount;
                private String max_amount;
                private String supplier_id;
                private String add_user;
                private String add_time;
                private String upd_user;
                private String upd_time;
                private String mark;
                private String type_name;
                private String cover;

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getCategory_id() {
                    return category_id;
                }

                public void setCategory_id(String category_id) {
                    this.category_id = category_id;
                }

                public String getType() {
                    return type;
                }

                public void setType(String type) {
                    this.type = type;
                }

                public String getProduct_name() {
                    return product_name;
                }

                public void setProduct_name(String product_name) {
                    this.product_name = product_name;
                }

                public String getSales_sum() {
                    return sales_sum;
                }

                public void setSales_sum(String sales_sum) {
                    this.sales_sum = sales_sum;
                }

                public String getStock_sum() {
                    return stock_sum;
                }

                public void setStock_sum(String stock_sum) {
                    this.stock_sum = stock_sum;
                }

                public String getScore() {
                    return score;
                }

                public void setScore(String score) {
                    this.score = score;
                }

                public String getClick_count() {
                    return click_count;
                }

                public void setClick_count(String click_count) {
                    this.click_count = click_count;
                }

                public String getComment_count() {
                    return comment_count;
                }

                public void setComment_count(String comment_count) {
                    this.comment_count = comment_count;
                }

                public String getPrice() {
                    return price;
                }

                public void setPrice(String price) {
                    this.price = price;
                }

                public String getType_price() {
                    return type_price;
                }

                public void setType_price(String type_price) {
                    this.type_price = type_price;
                }

                public String getImages() {
                    return images;
                }

                public void setImages(String images) {
                    this.images = images;
                }

                public String getIntro() {
                    return intro;
                }

                public void setIntro(String intro) {
                    this.intro = intro;
                }

                public String getIs_show() {
                    return is_show;
                }

                public void setIs_show(String is_show) {
                    this.is_show = is_show;
                }

                public String getIs_postage() {
                    return is_postage;
                }

                public void setIs_postage(String is_postage) {
                    this.is_postage = is_postage;
                }

                public String getPostage_price() {
                    return postage_price;
                }

                public void setPostage_price(String postage_price) {
                    this.postage_price = postage_price;
                }

                public String getIs_recommend() {
                    return is_recommend;
                }

                public void setIs_recommend(String is_recommend) {
                    this.is_recommend = is_recommend;
                }

                public String getIs_stock_limit() {
                    return is_stock_limit;
                }

                public void setIs_stock_limit(String is_stock_limit) {
                    this.is_stock_limit = is_stock_limit;
                }

                public String getTime_limit_start() {
                    return time_limit_start;
                }

                public void setTime_limit_start(String time_limit_start) {
                    this.time_limit_start = time_limit_start;
                }

                public String getTime_limit_end() {
                    return time_limit_end;
                }

                public void setTime_limit_end(String time_limit_end) {
                    this.time_limit_end = time_limit_end;
                }

                public String getMini_amount() {
                    return mini_amount;
                }

                public void setMini_amount(String mini_amount) {
                    this.mini_amount = mini_amount;
                }

                public String getMax_amount() {
                    return max_amount;
                }

                public void setMax_amount(String max_amount) {
                    this.max_amount = max_amount;
                }

                public String getSupplier_id() {
                    return supplier_id;
                }

                public void setSupplier_id(String supplier_id) {
                    this.supplier_id = supplier_id;
                }

                public String getAdd_user() {
                    return add_user;
                }

                public void setAdd_user(String add_user) {
                    this.add_user = add_user;
                }

                public String getAdd_time() {
                    return add_time;
                }

                public void setAdd_time(String add_time) {
                    this.add_time = add_time;
                }

                public String getUpd_user() {
                    return upd_user;
                }

                public void setUpd_user(String upd_user) {
                    this.upd_user = upd_user;
                }

                public String getUpd_time() {
                    return upd_time;
                }

                public void setUpd_time(String upd_time) {
                    this.upd_time = upd_time;
                }

                public String getMark() {
                    return mark;
                }

                public void setMark(String mark) {
                    this.mark = mark;
                }

                public String getType_name() {
                    return type_name;
                }

                public void setType_name(String type_name) {
                    this.type_name = type_name;
                }

                public String getCover() {
                    return cover;
                }

                public void setCover(String cover) {
                    this.cover = cover;
                }
            }
        }

        public static class MiddleListBean {
            /**
             * type : 2
             * cover_url : http://yun-attachment.oss-cn-hangzhou.aliyuncs.com/njyb_logo/a1feb7a072bbbf5611a2bca8d2e7d94c.png
             * plate : 8
             * title : “互联网+” 投票
             * type_id : 0
             * is_need_login : 1
             * url : http://newwap.891jyb.com/?app=opus&amp;act=index
             * share_url :
             */

            private String type;
            private String cover_url;
            private String plate;
            private String title;
            private String type_id;
            private String is_need_login;
            private String url;
            private String share_url;

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getCover_url() {
                return cover_url;
            }

            public void setCover_url(String cover_url) {
                this.cover_url = cover_url;
            }

            public String getPlate() {
                return plate;
            }

            public void setPlate(String plate) {
                this.plate = plate;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getType_id() {
                return type_id;
            }

            public void setType_id(String type_id) {
                this.type_id = type_id;
            }

            public String getIs_need_login() {
                return is_need_login;
            }

            public void setIs_need_login(String is_need_login) {
                this.is_need_login = is_need_login;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getShare_url() {
                return share_url;
            }

            public void setShare_url(String share_url) {
                this.share_url = share_url;
            }
        }

        public static class RecCateBean {
            /**
             * cond_id : 2
             * cond_name : 推荐
             * cond_introduce : 猜你喜欢
             */

            private String cond_id;
            private String cond_name;
            private String cond_introduce;

            public String getCond_id() {
                return cond_id;
            }

            public void setCond_id(String cond_id) {
                this.cond_id = cond_id;
            }

            public String getCond_name() {
                return cond_name;
            }

            public void setCond_name(String cond_name) {
                this.cond_name = cond_name;
            }

            public String getCond_introduce() {
                return cond_introduce;
            }

            public void setCond_introduce(String cond_introduce) {
                this.cond_introduce = cond_introduce;
            }
        }
    }
}
