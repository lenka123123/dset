package com.example.baselib.bean;

import java.util.List;

public class ProdutInfoBean {


    /**
     * success : 1
     * msg : 操作成功
     * data : {"productInfo":{"id":"1","category_id":"36","type":"1","product_name":"测试1","sales_sum":"538","stock_sum":"800","score":"5.0","click_count":"4139","comment_count":"689","price":"888.00","type_price":"300.00","images":[],"intro":"222","is_show":"1","is_postage":"1","postage_price":"5.00","is_recommend":"1","is_stock_limit":"0","time_limit_start":"0","time_limit_end":"0","mini_amount":"1","max_amount":"5","supplier_id":"0","add_user":"0","add_time":"1543982402","upd_user":"0","upd_time":"1545962058","add_date":"2018-12-05 12:00:02","product_id":"1","type_name":"美宝","is_collect":"0"},"commentList":{"list":[{"id":"19","user_id":"7","type":"1","type_id":"1","score":"5","images":[],"content":"对人不尊敬，首先就是对自己的不尊敬。\u2014\u2014惠特曼","add_time":"21天前","add_date":"2018-12-06 15:28:41","comment_id":"19","user_name":"mjmk6743","user_avatar":""},{"id":"32","user_id":"7","type":"1","type_id":"1","score":"3","images":[],"content":"如果我们想交朋友，就要先为别人做些事\u2014\u2014那些需要花时间、体力、体贴、奉献才能做到的事。\u2014\u2014卡耐基","add_time":"21天前","add_date":"2018-12-06 15:28:54","comment_id":"32","user_name":"mjmk6743","user_avatar":""}],"count":"3"},"spuList":[{"id":"8","attr_name":"品牌","sort":"99","attr_id":"8","values":{"id":"","attr_id":"","attr_value":"","attr_value_id":""}},{"id":"9","attr_name":"尺寸","sort":"99","attr_id":"9","values":{"id":"35","attr_id":"9","attr_value":"13.3","attr_value_id":"35"}}],"skuList":[{"id":"1","attr_name":"测试","sort":"99","attr_id":"1","values":[{"id":"28","attr_id":"1","attr_value":"kmlerx","attr_value_id":"28"},{"id":"11","attr_id":"1","attr_value":"rpygoc","attr_value_id":"11"},{"id":"1","attr_id":"1","attr_value":"测试","attr_value_id":"1"}]},{"id":"2","attr_name":"伊玲绿","sort":"99","attr_id":"2","values":[{"id":"61","attr_id":"2","attr_value":"lyyfik","attr_value_id":"61"},{"id":"52","attr_id":"2","attr_value":"ntuznb","attr_value_id":"52"},{"id":"50","attr_id":"2","attr_value":"iazrqb","attr_value_id":"50"}]}],"productSkuList":[{"id":"1","product_id":"1","product_spu_id":"1","attr_ids":"1,2","attr_value_ids":"1,50","sales_sum":"38","stock_sum":"48","price":"900.00","type_price":"300.00","is_show":"1","product_sku_id":"1"},{"id":"2","product_id":"1","product_spu_id":"1","attr_ids":"1,2","attr_value_ids":"11,61","sales_sum":"50","stock_sum":"100","price":"888.00","type_price":"300.00","is_show":"1","product_sku_id":"2"},{"id":"3","product_id":"1","product_spu_id":"1","attr_ids":"1,2","attr_value_ids":"28,52","sales_sum":"50","stock_sum":"100","price":"788.00","type_price":"300.00","is_show":"1","product_sku_id":"3"},{"id":"7","product_id":"1","product_spu_id":"1","attr_ids":"1,2","attr_value_ids":"1,61","sales_sum":"30","stock_sum":"50","price":"766.00","type_price":"300.00","is_show":"1","product_sku_id":"7"},{"id":"8","product_id":"1","product_spu_id":"1","attr_ids":"1,2","attr_value_ids":"1,52","sales_sum":"50","stock_sum":"50","price":"777.00","type_price":"300.00","is_show":"1","product_sku_id":"8"},{"id":"9","product_id":"1","product_spu_id":"1","attr_ids":"1,2","attr_value_ids":"11,50","sales_sum":"150","stock_sum":"200","price":"866.00","type_price":"250.00","is_show":"1","product_sku_id":"9"},{"id":"10","product_id":"1","product_spu_id":"1","attr_ids":"1,2","attr_value_ids":"11,52","sales_sum":"70","stock_sum":"100","price":"876.00","type_price":"250.00","is_show":"1","product_sku_id":"10"},{"id":"11","product_id":"1","product_spu_id":"1","attr_ids":"1,2","attr_value_ids":"28,50","sales_sum":"100","stock_sum":"100","price":"877.0","type_price":"300.00","is_show":"1","product_sku_id":"11"}]}
     * code : 10000
     * request_id : 9381bf04efe79ed9
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
        /**
         * productInfo : {"id":"1","category_id":"36","type":"1","product_name":"测试1","sales_sum":"538","stock_sum":"800","score":"5.0","click_count":"4139","comment_count":"689","price":"888.00","type_price":"300.00","images":[],"intro":"222","is_show":"1","is_postage":"1","postage_price":"5.00","is_recommend":"1","is_stock_limit":"0","time_limit_start":"0","time_limit_end":"0","mini_amount":"1","max_amount":"5","supplier_id":"0","add_user":"0","add_time":"1543982402","upd_user":"0","upd_time":"1545962058","add_date":"2018-12-05 12:00:02","product_id":"1","type_name":"美宝","is_collect":"0"}
         * commentList : {"list":[{"id":"19","user_id":"7","type":"1","type_id":"1","score":"5","images":[],"content":"对人不尊敬，首先就是对自己的不尊敬。\u2014\u2014惠特曼","add_time":"21天前","add_date":"2018-12-06 15:28:41","comment_id":"19","user_name":"mjmk6743","user_avatar":""},{"id":"32","user_id":"7","type":"1","type_id":"1","score":"3","images":[],"content":"如果我们想交朋友，就要先为别人做些事\u2014\u2014那些需要花时间、体力、体贴、奉献才能做到的事。\u2014\u2014卡耐基","add_time":"21天前","add_date":"2018-12-06 15:28:54","comment_id":"32","user_name":"mjmk6743","user_avatar":""}],"count":"3"}
         * spuList : [{"id":"8","attr_name":"品牌","sort":"99","attr_id":"8","values":{"id":"","attr_id":"","attr_value":"","attr_value_id":""}},{"id":"9","attr_name":"尺寸","sort":"99","attr_id":"9","values":{"id":"35","attr_id":"9","attr_value":"13.3","attr_value_id":"35"}}]
         * skuList : [{"id":"1","attr_name":"测试","sort":"99","attr_id":"1","values":[{"id":"28","attr_id":"1","attr_value":"kmlerx","attr_value_id":"28"},{"id":"11","attr_id":"1","attr_value":"rpygoc","attr_value_id":"11"},{"id":"1","attr_id":"1","attr_value":"测试","attr_value_id":"1"}]},{"id":"2","attr_name":"伊玲绿","sort":"99","attr_id":"2","values":[{"id":"61","attr_id":"2","attr_value":"lyyfik","attr_value_id":"61"},{"id":"52","attr_id":"2","attr_value":"ntuznb","attr_value_id":"52"},{"id":"50","attr_id":"2","attr_value":"iazrqb","attr_value_id":"50"}]}]
         * productSkuList : [{"id":"1","product_id":"1","product_spu_id":"1","attr_ids":"1,2","attr_value_ids":"1,50","sales_sum":"38","stock_sum":"48","price":"900.00","type_price":"300.00","is_show":"1","product_sku_id":"1"},{"id":"2","product_id":"1","product_spu_id":"1","attr_ids":"1,2","attr_value_ids":"11,61","sales_sum":"50","stock_sum":"100","price":"888.00","type_price":"300.00","is_show":"1","product_sku_id":"2"},{"id":"3","product_id":"1","product_spu_id":"1","attr_ids":"1,2","attr_value_ids":"28,52","sales_sum":"50","stock_sum":"100","price":"788.00","type_price":"300.00","is_show":"1","product_sku_id":"3"},{"id":"7","product_id":"1","product_spu_id":"1","attr_ids":"1,2","attr_value_ids":"1,61","sales_sum":"30","stock_sum":"50","price":"766.00","type_price":"300.00","is_show":"1","product_sku_id":"7"},{"id":"8","product_id":"1","product_spu_id":"1","attr_ids":"1,2","attr_value_ids":"1,52","sales_sum":"50","stock_sum":"50","price":"777.00","type_price":"300.00","is_show":"1","product_sku_id":"8"},{"id":"9","product_id":"1","product_spu_id":"1","attr_ids":"1,2","attr_value_ids":"11,50","sales_sum":"150","stock_sum":"200","price":"866.00","type_price":"250.00","is_show":"1","product_sku_id":"9"},{"id":"10","product_id":"1","product_spu_id":"1","attr_ids":"1,2","attr_value_ids":"11,52","sales_sum":"70","stock_sum":"100","price":"876.00","type_price":"250.00","is_show":"1","product_sku_id":"10"},{"id":"11","product_id":"1","product_spu_id":"1","attr_ids":"1,2","attr_value_ids":"28,50","sales_sum":"100","stock_sum":"100","price":"877.0","type_price":"300.00","is_show":"1","product_sku_id":"11"}]
         */

        private ProductInfoBean productInfo;
        private CommentListBean commentList;
        private List<SpuListBean> spuList;
        private List<SkuListBean> skuList;
        private List<ProductSkuListBean> productSkuList;

        public ProductInfoBean getProductInfo() {
            return productInfo;
        }

        public void setProductInfo(ProductInfoBean productInfo) {
            this.productInfo = productInfo;
        }

        public CommentListBean getCommentList() {
            return commentList;
        }

        public void setCommentList(CommentListBean commentList) {
            this.commentList = commentList;
        }

        public List<SpuListBean> getSpuList() {
            return spuList;
        }

        public void setSpuList(List<SpuListBean> spuList) {
            this.spuList = spuList;
        }

        public List<SkuListBean> getSkuList() {
            return skuList;
        }

        public void setSkuList(List<SkuListBean> skuList) {
            this.skuList = skuList;
        }

        public List<ProductSkuListBean> getProductSkuList() {
            return productSkuList;
        }

        public void setProductSkuList(List<ProductSkuListBean> productSkuList) {
            this.productSkuList = productSkuList;
        }

        public static class ProductInfoBean {
            /**
             * id : 1
             * category_id : 36
             * type : 1
             * product_name : 测试1
             * sales_sum : 538
             * stock_sum : 800
             * score : 5.0
             * click_count : 4139
             * comment_count : 689
             * price : 888.00
             * type_price : 300.00
             * images : []
             * intro : 222
             * is_show : 1
             * is_postage : 1
             * postage_price : 5.00
             * is_recommend : 1
             * is_stock_limit : 0
             * time_limit_start : 0
             * time_limit_end : 0
             * mini_amount : 1
             * max_amount : 5
             * supplier_id : 0
             * add_user : 0
             * add_time : 1543982402
             * upd_user : 0
             * upd_time : 1545962058
             * add_date : 2018-12-05 12:00:02
             * product_id : 1
             * type_name : 美宝
             * is_collect : 0
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
            private String add_date;
            private String product_id;
            private String type_name;
            private String is_collect;
            private List<?> images;

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

            public String getAdd_date() {
                return add_date;
            }

            public void setAdd_date(String add_date) {
                this.add_date = add_date;
            }

            public String getProduct_id() {
                return product_id;
            }

            public void setProduct_id(String product_id) {
                this.product_id = product_id;
            }

            public String getType_name() {
                return type_name;
            }

            public void setType_name(String type_name) {
                this.type_name = type_name;
            }

            public String getIs_collect() {
                return is_collect;
            }

            public void setIs_collect(String is_collect) {
                this.is_collect = is_collect;
            }

            public List<?> getImages() {
                return images;
            }

            public void setImages(List<?> images) {
                this.images = images;
            }
        }

        public static class CommentListBean {
            /**
             * list : [{"id":"19","user_id":"7","type":"1","type_id":"1","score":"5","images":[],"content":"对人不尊敬，首先就是对自己的不尊敬。\u2014\u2014惠特曼","add_time":"21天前","add_date":"2018-12-06 15:28:41","comment_id":"19","user_name":"mjmk6743","user_avatar":""},{"id":"32","user_id":"7","type":"1","type_id":"1","score":"3","images":[],"content":"如果我们想交朋友，就要先为别人做些事\u2014\u2014那些需要花时间、体力、体贴、奉献才能做到的事。\u2014\u2014卡耐基","add_time":"21天前","add_date":"2018-12-06 15:28:54","comment_id":"32","user_name":"mjmk6743","user_avatar":""}]
             * count : 3
             */

            private String count;
            private List<ListBean> list;

            public String getCount() {
                return count;
            }

            public void setCount(String count) {
                this.count = count;
            }

            public List<ListBean> getList() {
                return list;
            }

            public void setList(List<ListBean> list) {
                this.list = list;
            }

            public static class ListBean {
                /**
                 * id : 19
                 * user_id : 7
                 * type : 1
                 * type_id : 1
                 * score : 5
                 * images : []
                 * content : 对人不尊敬，首先就是对自己的不尊敬。——惠特曼
                 * add_time : 21天前
                 * add_date : 2018-12-06 15:28:41
                 * comment_id : 19
                 * user_name : mjmk6743
                 * user_avatar :
                 */

                private String id;
                private String user_id;
                private String type;
                private String type_id;
                private String score;
                private String content;
                private String add_time;
                private String add_date;
                private String comment_id;
                private String user_name;
                private String user_avatar;
                private List<?> images;

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getUser_id() {
                    return user_id;
                }

                public void setUser_id(String user_id) {
                    this.user_id = user_id;
                }

                public String getType() {
                    return type;
                }

                public void setType(String type) {
                    this.type = type;
                }

                public String getType_id() {
                    return type_id;
                }

                public void setType_id(String type_id) {
                    this.type_id = type_id;
                }

                public String getScore() {
                    return score;
                }

                public void setScore(String score) {
                    this.score = score;
                }

                public String getContent() {
                    return content;
                }

                public void setContent(String content) {
                    this.content = content;
                }

                public String getAdd_time() {
                    return add_time;
                }

                public void setAdd_time(String add_time) {
                    this.add_time = add_time;
                }

                public String getAdd_date() {
                    return add_date;
                }

                public void setAdd_date(String add_date) {
                    this.add_date = add_date;
                }

                public String getComment_id() {
                    return comment_id;
                }

                public void setComment_id(String comment_id) {
                    this.comment_id = comment_id;
                }

                public String getUser_name() {
                    return user_name;
                }

                public void setUser_name(String user_name) {
                    this.user_name = user_name;
                }

                public String getUser_avatar() {
                    return user_avatar;
                }

                public void setUser_avatar(String user_avatar) {
                    this.user_avatar = user_avatar;
                }

                public List<?> getImages() {
                    return images;
                }

                public void setImages(List<?> images) {
                    this.images = images;
                }
            }
        }

        public static class SpuListBean {
            /**
             * id : 8
             * attr_name : 品牌
             * sort : 99
             * attr_id : 8
             * values : {"id":"","attr_id":"","attr_value":"","attr_value_id":""}
             */

            private String id;
            private String attr_name;
            private String sort;
            private String attr_id;
            private ValuesBean values;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getAttr_name() {
                return attr_name;
            }

            public void setAttr_name(String attr_name) {
                this.attr_name = attr_name;
            }

            public String getSort() {
                return sort;
            }

            public void setSort(String sort) {
                this.sort = sort;
            }

            public String getAttr_id() {
                return attr_id;
            }

            public void setAttr_id(String attr_id) {
                this.attr_id = attr_id;
            }

            public ValuesBean getValues() {
                return values;
            }

            public void setValues(ValuesBean values) {
                this.values = values;
            }

            public static class ValuesBean {
                /**
                 * id :
                 * attr_id :
                 * attr_value :
                 * attr_value_id :
                 */

                private String id;
                private String attr_id;
                private String attr_value;
                private String attr_value_id;

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getAttr_id() {
                    return attr_id;
                }

                public void setAttr_id(String attr_id) {
                    this.attr_id = attr_id;
                }

                public String getAttr_value() {
                    return attr_value;
                }

                public void setAttr_value(String attr_value) {
                    this.attr_value = attr_value;
                }

                public String getAttr_value_id() {
                    return attr_value_id;
                }

                public void setAttr_value_id(String attr_value_id) {
                    this.attr_value_id = attr_value_id;
                }
            }
        }

        public static class SkuListBean {
            /**
             * id : 1
             * attr_name : 测试
             * sort : 99
             * attr_id : 1
             * values : [{"id":"28","attr_id":"1","attr_value":"kmlerx","attr_value_id":"28"},{"id":"11","attr_id":"1","attr_value":"rpygoc","attr_value_id":"11"},{"id":"1","attr_id":"1","attr_value":"测试","attr_value_id":"1"}]
             */

            private String id;
            private String attr_name;
            private String sort;
            private String attr_id;
            private List<ValuesBeanX> values;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getAttr_name() {
                return attr_name;
            }

            public void setAttr_name(String attr_name) {
                this.attr_name = attr_name;
            }

            public String getSort() {
                return sort;
            }

            public void setSort(String sort) {
                this.sort = sort;
            }

            public String getAttr_id() {
                return attr_id;
            }

            public void setAttr_id(String attr_id) {
                this.attr_id = attr_id;
            }

            public List<ValuesBeanX> getValues() {
                return values;
            }

            public void setValues(List<ValuesBeanX> values) {
                this.values = values;
            }

            public static class ValuesBeanX {
                /**
                 * id : 28
                 * attr_id : 1
                 * attr_value : kmlerx
                 * attr_value_id : 28
                 */

                private String id;
                private String attr_id;
                private String attr_value;
                private String attr_value_id;

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getAttr_id() {
                    return attr_id;
                }

                public void setAttr_id(String attr_id) {
                    this.attr_id = attr_id;
                }

                public String getAttr_value() {
                    return attr_value;
                }

                public void setAttr_value(String attr_value) {
                    this.attr_value = attr_value;
                }

                public String getAttr_value_id() {
                    return attr_value_id;
                }

                public void setAttr_value_id(String attr_value_id) {
                    this.attr_value_id = attr_value_id;
                }
            }
        }

        public static class ProductSkuListBean {
            /**
             * id : 1
             * product_id : 1
             * product_spu_id : 1
             * attr_ids : 1,2
             * attr_value_ids : 1,50
             * sales_sum : 38
             * stock_sum : 48
             * price : 900.00
             * type_price : 300.00
             * is_show : 1
             * product_sku_id : 1
             */

            private String id;
            private String product_id;
            private String product_spu_id;
            private String attr_ids;
            private String attr_value_ids;
            private String sales_sum;
            private String stock_sum;
            private String price;
            private String type_price;
            private String is_show;
            private String product_sku_id;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getProduct_id() {
                return product_id;
            }

            public void setProduct_id(String product_id) {
                this.product_id = product_id;
            }

            public String getProduct_spu_id() {
                return product_spu_id;
            }

            public void setProduct_spu_id(String product_spu_id) {
                this.product_spu_id = product_spu_id;
            }

            public String getAttr_ids() {
                return attr_ids;
            }

            public void setAttr_ids(String attr_ids) {
                this.attr_ids = attr_ids;
            }

            public String getAttr_value_ids() {
                return attr_value_ids;
            }

            public void setAttr_value_ids(String attr_value_ids) {
                this.attr_value_ids = attr_value_ids;
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

            public String getIs_show() {
                return is_show;
            }

            public void setIs_show(String is_show) {
                this.is_show = is_show;
            }

            public String getProduct_sku_id() {
                return product_sku_id;
            }

            public void setProduct_sku_id(String product_sku_id) {
                this.product_sku_id = product_sku_id;
            }
        }
    }
}
