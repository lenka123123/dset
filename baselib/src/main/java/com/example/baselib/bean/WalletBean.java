package com.example.baselib.bean;

import java.util.List;

public class WalletBean {


    /**
     * success : 1
     * msg : 操作成功
     * data : {"page":"1","perpage":"10","count":"1","list":[{"id":"2","user_id":"1","sign":"1","money_type":"1","trade_type":"1","before":"4000.00","after":"8000.00","money":"4000.00","remain":"794.99","order_id":"8","shop_id":"1","spend_money":"0.00","add_time":"2018-12-27","title":"","extra":"","add_date":"2018-12-27 15:42:38","user_capital_id":"2","money_type_name":"美宝","name":"测试店铺"}],"total":"794.99"}
     * code : 10000
     * request_id : 717fc993f8a54464
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
         * perpage : 10
         * count : 1
         * list : [{"id":"2","user_id":"1","sign":"1","money_type":"1","trade_type":"1","before":"4000.00","after":"8000.00","money":"4000.00","remain":"794.99","order_id":"8","shop_id":"1","spend_money":"0.00","add_time":"2018-12-27","title":"","extra":"","add_date":"2018-12-27 15:42:38","user_capital_id":"2","money_type_name":"美宝","name":"测试店铺"}]
         * total : 794.99
         */

        private int page;
        private int perpage;
        private int count;
        private String total;
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

        public String getTotal() {
            return total;
        }

        public void setTotal(String total) {
            this.total = total;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * id : 2
             * user_id : 1
             * sign : 1
             * money_type : 1
             * trade_type : 1
             * before : 4000.00
             * after : 8000.00
             * money : 4000.00
             * remain : 794.99
             * order_id : 8
             * shop_id : 1
             * spend_money : 0.00
             * add_time : 2018-12-27
             * title :
             * extra :
             * add_date : 2018-12-27 15:42:38
             * user_capital_id : 2
             * money_type_name : 美宝
             * name : 测试店铺
             */

            private String id;
            private String user_id;
            private String sign;
            private String money_type;
            private String trade_type;
            private String before;
            private String after;
            private float money;
            private String remain;
            private String order_id;
            private String shop_id;
            private String spend_money;
            private String add_time;
            private String title;
            private String extra;
            private String add_date;
            private String user_capital_id;
            private String money_type_name;
            private String name;

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

            public String getSign() {
                return sign;
            }

            public void setSign(String sign) {
                this.sign = sign;
            }

            public String getMoney_type() {
                return money_type;
            }

            public void setMoney_type(String money_type) {
                this.money_type = money_type;
            }

            public String getTrade_type() {
                return trade_type;
            }

            public void setTrade_type(String trade_type) {
                this.trade_type = trade_type;
            }

            public String getBefore() {
                return before;
            }

            public void setBefore(String before) {
                this.before = before;
            }

            public String getAfter() {
                return after;
            }

            public void setAfter(String after) {
                this.after = after;
            }

            public float getMoney() {
                return money;
            }

            public void setMoney(float money) {
                this.money = money;
            }

            public String getRemain() {
                return remain;
            }

            public void setRemain(String remain) {
                this.remain = remain;
            }

            public String getOrder_id() {
                return order_id;
            }

            public void setOrder_id(String order_id) {
                this.order_id = order_id;
            }

            public String getShop_id() {
                return shop_id;
            }

            public void setShop_id(String shop_id) {
                this.shop_id = shop_id;
            }

            public String getSpend_money() {
                return spend_money;
            }

            public void setSpend_money(String spend_money) {
                this.spend_money = spend_money;
            }

            public String getAdd_time() {
                return add_time;
            }

            public void setAdd_time(String add_time) {
                this.add_time = add_time;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getExtra() {
                return extra;
            }

            public void setExtra(String extra) {
                this.extra = extra;
            }

            public String getAdd_date() {
                return add_date;
            }

            public void setAdd_date(String add_date) {
                this.add_date = add_date;
            }

            public String getUser_capital_id() {
                return user_capital_id;
            }

            public void setUser_capital_id(String user_capital_id) {
                this.user_capital_id = user_capital_id;
            }

            public String getMoney_type_name() {
                return money_type_name;
            }

            public void setMoney_type_name(String money_type_name) {
                this.money_type_name = money_type_name;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }
    }
}
