package com.example.baselib.bean;

import java.util.List;

public class IndentBean {

    /**
     * success : 1
     * msg : 操作成功
     * data : {"page":"1","perpage":"20","count":"5","list":[{"id":"6","user_id":"1","order_id":"6130701918045788","order_time":"1545019180","pay_time":"0","shipping_time":"0","product_type":"1","product_sku_id":"7","product_amount":"2","product":{"id":"7","product_id":"1","product_spu_id":"1","attr_ids":"1,2","attr_value_ids":"1,61","sales_sum":"30","stock_sum":"50","price":"766.00","type_price":"300.00","is_show":"1","product_sku_id":"7","product_cart_id":"","product_name":"测试1","cover":"","is_postage":"1","postage_price":"5.00","splice_content":"测试:测试，伊玲绿:lyyfik","product_amount":"1","status":"1"},"address":{"id":"1","user_id":"1","fullname":"陈鹏","telephone":"18114923920","area_code":"888888","province_id":"1387","city_id":"1388","area_id":"1389","detail":"333333","is_default":"0","add_time":"1543803925","add_date":"2018-12-03 10:25:25","user_address_id":"1","address":"江苏省南京市玄武区333333"},"total_price":"1532.00","total_type_price":"600.00","total_money":"932.00","note":"","status":"1","remark":"","product_order_id":"6","product_type_name":"美宝","status_name":"等待付款"},{"id":"5","user_id":"1","order_id":"6347201918014325","order_time":"1545019180","pay_time":"0","shipping_time":"0","product_type":"1","product_sku_id":"2","product_amount":"7","product":{"id":"2","product_id":"1","product_spu_id":"1","attr_ids":"1,2","attr_value_ids":"11,61","sales_sum":"50","stock_sum":"100","price":"888.00","type_price":"300.00","is_show":"1","product_sku_id":"2","product_cart_id":"","product_name":"测试1","cover":"","is_postage":"1","postage_price":"5.00","splice_content":"测试:rpygoc，伊玲绿:lyyfik","product_amount":"1","status":"1"},"address":{"id":"1","user_id":"1","fullname":"陈鹏","telephone":"18114923920","area_code":"888888","province_id":"1387","city_id":"1388","area_id":"1389","detail":"333333","is_default":"0","add_time":"1543803925","add_date":"2018-12-03 10:25:25","user_address_id":"1","address":"江苏省南京市玄武区333333"},"total_price":"6216.00","total_type_price":"2100.00","total_money":"4116.00","note":"","status":"1","remark":"","product_order_id":"5","product_type_name":"美宝","status_name":"等待付款"},{"id":"4","user_id":"1","order_id":"6362201903992616","order_time":"1545019039","pay_time":"0","shipping_time":"0","product_type":"1","product_sku_id":"2","product_amount":"7","product":{"id":"2","product_id":"1","product_spu_id":"1","attr_ids":"1,2","attr_value_ids":"11,61","sales_sum":"50","stock_sum":"100","price":"888.00","type_price":"300.00","is_show":"1","product_sku_id":"2","product_cart_id":"","product_name":"测试1","cover":"","is_postage":"1","postage_price":"5.00","splice_content":"测试:rpygoc，伊玲绿:lyyfik","product_amount":"1","status":"1"},"address":{"id":"1","user_id":"1","fullname":"陈鹏","telephone":"18114923920","area_code":"888888","province_id":"1387","city_id":"1388","area_id":"1389","detail":"333333","is_default":"0","add_time":"1543803925","add_date":"2018-12-03 10:25:25","user_address_id":"1","address":"江苏省南京市玄武区333333"},"total_price":"6216.00","total_type_price":"2100.00","total_money":"4116.00","note":"","status":"1","remark":"","product_order_id":"4","product_type_name":"美宝","status_name":"等待付款"},{"id":"3","user_id":"1","order_id":"6122101889668556","order_time":"1545018896","pay_time":"0","shipping_time":"0","product_type":"1","product_sku_id":"1","product_amount":"3","product":{"id":"1","product_id":"1","product_spu_id":"1","attr_ids":"1,2","attr_value_ids":"1,50","sales_sum":"38","stock_sum":"48","price":"900.00","type_price":"300.00","is_show":"1","product_sku_id":"1","product_cart_id":"","product_name":"测试1","cover":"","is_postage":"1","postage_price":"5.00","splice_content":"测试:测试，伊玲绿:iazrqb","product_amount":"1","status":"1"},"address":{"id":"1","user_id":"1","fullname":"陈鹏","telephone":"18114923920","area_code":"888888","province_id":"1387","city_id":"1388","area_id":"1389","detail":"333333","is_default":"0","add_time":"1543803925","add_date":"2018-12-03 10:25:25","user_address_id":"1","address":"江苏省南京市玄武区333333"},"total_price":"2700.00","total_type_price":"900.00","total_money":"1800.00","note":"","status":"1","remark":"","product_order_id":"3","product_type_name":"美宝","status_name":"等待付款"},{"id":"2","user_id":"1","order_id":"6937768321088524","order_time":"1544683210","pay_time":"0","shipping_time":"0","product_type":"1","product_sku_id":"7","product_amount":"2","product":{"id":"7","product_id":"1","product_spu_id":"1","attr_ids":"1,2","attr_value_ids":"1,61","sales_sum":"30","stock_sum":"50","price":"766.00","type_price":"300.00","is_show":"1","product_sku_id":"7","product_cart_id":"","product_name":"测试1","cover":"","is_postage":"1","postage_price":"5.00","splice_attr":"测试:测试，伊玲绿:lyyfik","product_amount":"1","status":"1"},"address":{"id":"1","user_id":"1","fullname":"陈鹏","telephone":"18114923920","area_code":"888888","province_id":"1387","city_id":"1388","area_id":"1389","detail":"333333","is_default":"1","add_time":"1543803925","add_date":"2018-12-03 10:25:25","user_address_id":"1","address":"江苏省南京市玄武区333333"},"total_price":"1532.00","total_type_price":"600.00","total_money":"932.00","note":"","status":"1","remark":"","product_order_id":"2","product_type_name":"美宝","status_name":"等待付款"}]}
     * code : 10000
     * request_id : 82fac2ddf94c4fee
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
         * page : 1
         * perpage : 20
         * count : 5
         * list : [{"id":"6","user_id":"1","order_id":"6130701918045788","order_time":"1545019180","pay_time":"0","shipping_time":"0","product_type":"1","product_sku_id":"7","product_amount":"2","product":{"id":"7","product_id":"1","product_spu_id":"1","attr_ids":"1,2","attr_value_ids":"1,61","sales_sum":"30","stock_sum":"50","price":"766.00","type_price":"300.00","is_show":"1","product_sku_id":"7","product_cart_id":"","product_name":"测试1","cover":"","is_postage":"1","postage_price":"5.00","splice_content":"测试:测试，伊玲绿:lyyfik","product_amount":"1","status":"1"},"address":{"id":"1","user_id":"1","fullname":"陈鹏","telephone":"18114923920","area_code":"888888","province_id":"1387","city_id":"1388","area_id":"1389","detail":"333333","is_default":"0","add_time":"1543803925","add_date":"2018-12-03 10:25:25","user_address_id":"1","address":"江苏省南京市玄武区333333"},"total_price":"1532.00","total_type_price":"600.00","total_money":"932.00","note":"","status":"1","remark":"","product_order_id":"6","product_type_name":"美宝","status_name":"等待付款"},{"id":"5","user_id":"1","order_id":"6347201918014325","order_time":"1545019180","pay_time":"0","shipping_time":"0","product_type":"1","product_sku_id":"2","product_amount":"7","product":{"id":"2","product_id":"1","product_spu_id":"1","attr_ids":"1,2","attr_value_ids":"11,61","sales_sum":"50","stock_sum":"100","price":"888.00","type_price":"300.00","is_show":"1","product_sku_id":"2","product_cart_id":"","product_name":"测试1","cover":"","is_postage":"1","postage_price":"5.00","splice_content":"测试:rpygoc，伊玲绿:lyyfik","product_amount":"1","status":"1"},"address":{"id":"1","user_id":"1","fullname":"陈鹏","telephone":"18114923920","area_code":"888888","province_id":"1387","city_id":"1388","area_id":"1389","detail":"333333","is_default":"0","add_time":"1543803925","add_date":"2018-12-03 10:25:25","user_address_id":"1","address":"江苏省南京市玄武区333333"},"total_price":"6216.00","total_type_price":"2100.00","total_money":"4116.00","note":"","status":"1","remark":"","product_order_id":"5","product_type_name":"美宝","status_name":"等待付款"},{"id":"4","user_id":"1","order_id":"6362201903992616","order_time":"1545019039","pay_time":"0","shipping_time":"0","product_type":"1","product_sku_id":"2","product_amount":"7","product":{"id":"2","product_id":"1","product_spu_id":"1","attr_ids":"1,2","attr_value_ids":"11,61","sales_sum":"50","stock_sum":"100","price":"888.00","type_price":"300.00","is_show":"1","product_sku_id":"2","product_cart_id":"","product_name":"测试1","cover":"","is_postage":"1","postage_price":"5.00","splice_content":"测试:rpygoc，伊玲绿:lyyfik","product_amount":"1","status":"1"},"address":{"id":"1","user_id":"1","fullname":"陈鹏","telephone":"18114923920","area_code":"888888","province_id":"1387","city_id":"1388","area_id":"1389","detail":"333333","is_default":"0","add_time":"1543803925","add_date":"2018-12-03 10:25:25","user_address_id":"1","address":"江苏省南京市玄武区333333"},"total_price":"6216.00","total_type_price":"2100.00","total_money":"4116.00","note":"","status":"1","remark":"","product_order_id":"4","product_type_name":"美宝","status_name":"等待付款"},{"id":"3","user_id":"1","order_id":"6122101889668556","order_time":"1545018896","pay_time":"0","shipping_time":"0","product_type":"1","product_sku_id":"1","product_amount":"3","product":{"id":"1","product_id":"1","product_spu_id":"1","attr_ids":"1,2","attr_value_ids":"1,50","sales_sum":"38","stock_sum":"48","price":"900.00","type_price":"300.00","is_show":"1","product_sku_id":"1","product_cart_id":"","product_name":"测试1","cover":"","is_postage":"1","postage_price":"5.00","splice_content":"测试:测试，伊玲绿:iazrqb","product_amount":"1","status":"1"},"address":{"id":"1","user_id":"1","fullname":"陈鹏","telephone":"18114923920","area_code":"888888","province_id":"1387","city_id":"1388","area_id":"1389","detail":"333333","is_default":"0","add_time":"1543803925","add_date":"2018-12-03 10:25:25","user_address_id":"1","address":"江苏省南京市玄武区333333"},"total_price":"2700.00","total_type_price":"900.00","total_money":"1800.00","note":"","status":"1","remark":"","product_order_id":"3","product_type_name":"美宝","status_name":"等待付款"},{"id":"2","user_id":"1","order_id":"6937768321088524","order_time":"1544683210","pay_time":"0","shipping_time":"0","product_type":"1","product_sku_id":"7","product_amount":"2","product":{"id":"7","product_id":"1","product_spu_id":"1","attr_ids":"1,2","attr_value_ids":"1,61","sales_sum":"30","stock_sum":"50","price":"766.00","type_price":"300.00","is_show":"1","product_sku_id":"7","product_cart_id":"","product_name":"测试1","cover":"","is_postage":"1","postage_price":"5.00","splice_attr":"测试:测试，伊玲绿:lyyfik","product_amount":"1","status":"1"},"address":{"id":"1","user_id":"1","fullname":"陈鹏","telephone":"18114923920","area_code":"888888","province_id":"1387","city_id":"1388","area_id":"1389","detail":"333333","is_default":"1","add_time":"1543803925","add_date":"2018-12-03 10:25:25","user_address_id":"1","address":"江苏省南京市玄武区333333"},"total_price":"1532.00","total_type_price":"600.00","total_money":"932.00","note":"","status":"1","remark":"","product_order_id":"2","product_type_name":"美宝","status_name":"等待付款"}]
         */

        private int page;
        private int perpage;
        private int count;
        private List<ListBean> list;

        public int getPage() {
            return page;
        }

        public void setPage(int page) {
            this.page = page;
        }

        public int getPerpage() {
            return perpage;
        }

        public void setPerpage(int perpage) {
            this.perpage = perpage;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
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
             * id : 6
             * user_id : 1
             * order_id : 6130701918045788
             * order_time : 1545019180
             * pay_time : 0
             * shipping_time : 0
             * product_type : 1
             * product_sku_id : 7
             * product_amount : 2
             * product : {"id":"7","product_id":"1","product_spu_id":"1","attr_ids":"1,2","attr_value_ids":"1,61","sales_sum":"30","stock_sum":"50","price":"766.00","type_price":"300.00","is_show":"1","product_sku_id":"7","product_cart_id":"","product_name":"测试1","cover":"","is_postage":"1","postage_price":"5.00","splice_content":"测试:测试，伊玲绿:lyyfik","product_amount":"1","status":"1"}
             * address : {"id":"1","user_id":"1","fullname":"陈鹏","telephone":"18114923920","area_code":"888888","province_id":"1387","city_id":"1388","area_id":"1389","detail":"333333","is_default":"0","add_time":"1543803925","add_date":"2018-12-03 10:25:25","user_address_id":"1","address":"江苏省南京市玄武区333333"}
             * total_price : 1532.00
             * total_type_price : 600.00
             * total_money : 932.00
             * note :
             * status : 1
             * remark :
             * product_order_id : 6
             * product_type_name : 美宝
             * status_name : 等待付款
             */

            private String id;
            private String user_id;
            private String order_id;
            private String order_time;
            private String pay_time;
            private String shipping_time;
            private String product_type;
            private String product_sku_id;
            private String product_amount;
            private ProductBean product;
            private AddressBean address;
            private String total_price;
            private String total_type_price;
            private String total_money;
            private String note;
            private String status;
            private String remark;
            private String product_order_id;
            private String product_type_name;
            private String status_name;

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

            public String getOrder_id() {
                return order_id;
            }

            public void setOrder_id(String order_id) {
                this.order_id = order_id;
            }

            public String getOrder_time() {
                return order_time;
            }

            public void setOrder_time(String order_time) {
                this.order_time = order_time;
            }

            public String getPay_time() {
                return pay_time;
            }

            public void setPay_time(String pay_time) {
                this.pay_time = pay_time;
            }

            public String getShipping_time() {
                return shipping_time;
            }

            public void setShipping_time(String shipping_time) {
                this.shipping_time = shipping_time;
            }

            public String getProduct_type() {
                return product_type;
            }

            public void setProduct_type(String product_type) {
                this.product_type = product_type;
            }

            public String getProduct_sku_id() {
                return product_sku_id;
            }

            public void setProduct_sku_id(String product_sku_id) {
                this.product_sku_id = product_sku_id;
            }

            public String getProduct_amount() {
                return product_amount;
            }

            public void setProduct_amount(String product_amount) {
                this.product_amount = product_amount;
            }

            public ProductBean getProduct() {
                return product;
            }

            public void setProduct(ProductBean product) {
                this.product = product;
            }

            public AddressBean getAddress() {
                return address;
            }

            public void setAddress(AddressBean address) {
                this.address = address;
            }

            public String getTotal_price() {
                return total_price;
            }

            public void setTotal_price(String total_price) {
                this.total_price = total_price;
            }

            public String getTotal_type_price() {
                return total_type_price;
            }

            public void setTotal_type_price(String total_type_price) {
                this.total_type_price = total_type_price;
            }

            public String getTotal_money() {
                return total_money;
            }

            public void setTotal_money(String total_money) {
                this.total_money = total_money;
            }

            public String getNote() {
                return note;
            }

            public void setNote(String note) {
                this.note = note;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getRemark() {
                return remark;
            }

            public void setRemark(String remark) {
                this.remark = remark;
            }

            public String getProduct_order_id() {
                return product_order_id;
            }

            public void setProduct_order_id(String product_order_id) {
                this.product_order_id = product_order_id;
            }

            public String getProduct_type_name() {
                return product_type_name;
            }

            public void setProduct_type_name(String product_type_name) {
                this.product_type_name = product_type_name;
            }

            public String getStatus_name() {
                return status_name;
            }

            public void setStatus_name(String status_name) {
                this.status_name = status_name;
            }

            public static class ProductBean {
                /**
                 * id : 7
                 * product_id : 1
                 * product_spu_id : 1
                 * attr_ids : 1,2
                 * attr_value_ids : 1,61
                 * sales_sum : 30
                 * stock_sum : 50
                 * price : 766.00
                 * type_price : 300.00
                 * is_show : 1
                 * product_sku_id : 7
                 * product_cart_id :
                 * product_name : 测试1
                 * cover :
                 * is_postage : 1
                 * postage_price : 5.00
                 * splice_content : 测试:测试，伊玲绿:lyyfik
                 * product_amount : 1
                 * status : 1
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
                private String product_cart_id;
                private String product_name;
                private String cover;
                private String is_postage;
                private String postage_price;
                private String splice_content;
                private String product_amount;
                private String status;

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

                public String getProduct_cart_id() {
                    return product_cart_id;
                }

                public void setProduct_cart_id(String product_cart_id) {
                    this.product_cart_id = product_cart_id;
                }

                public String getProduct_name() {
                    return product_name;
                }

                public void setProduct_name(String product_name) {
                    this.product_name = product_name;
                }

                public String getCover() {
                    return cover;
                }

                public void setCover(String cover) {
                    this.cover = cover;
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

                public String getSplice_content() {
                    return splice_content;
                }

                public void setSplice_content(String splice_content) {
                    this.splice_content = splice_content;
                }

                public String getProduct_amount() {
                    return product_amount;
                }

                public void setProduct_amount(String product_amount) {
                    this.product_amount = product_amount;
                }

                public String getStatus() {
                    return status;
                }

                public void setStatus(String status) {
                    this.status = status;
                }
            }

            public static class AddressBean {
                /**
                 * id : 1
                 * user_id : 1
                 * fullname : 陈鹏
                 * telephone : 18114923920
                 * area_code : 888888
                 * province_id : 1387
                 * city_id : 1388
                 * area_id : 1389
                 * detail : 333333
                 * is_default : 0
                 * add_time : 1543803925
                 * add_date : 2018-12-03 10:25:25
                 * user_address_id : 1
                 * address : 江苏省南京市玄武区333333
                 */

                private String id;
                private String user_id;
                private String fullname;
                private String telephone;
                private String area_code;
                private String province_id;
                private String city_id;
                private String area_id;
                private String detail;
                private String is_default;
                private String add_time;
                private String add_date;
                private String user_address_id;
                private String address;

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

                public String getFullname() {
                    return fullname;
                }

                public void setFullname(String fullname) {
                    this.fullname = fullname;
                }

                public String getTelephone() {
                    return telephone;
                }

                public void setTelephone(String telephone) {
                    this.telephone = telephone;
                }

                public String getArea_code() {
                    return area_code;
                }

                public void setArea_code(String area_code) {
                    this.area_code = area_code;
                }

                public String getProvince_id() {
                    return province_id;
                }

                public void setProvince_id(String province_id) {
                    this.province_id = province_id;
                }

                public String getCity_id() {
                    return city_id;
                }

                public void setCity_id(String city_id) {
                    this.city_id = city_id;
                }

                public String getArea_id() {
                    return area_id;
                }

                public void setArea_id(String area_id) {
                    this.area_id = area_id;
                }

                public String getDetail() {
                    return detail;
                }

                public void setDetail(String detail) {
                    this.detail = detail;
                }

                public String getIs_default() {
                    return is_default;
                }

                public void setIs_default(String is_default) {
                    this.is_default = is_default;
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

                public String getUser_address_id() {
                    return user_address_id;
                }

                public void setUser_address_id(String user_address_id) {
                    this.user_address_id = user_address_id;
                }

                public String getAddress() {
                    return address;
                }

                public void setAddress(String address) {
                    this.address = address;
                }
            }
        }
    }
}
