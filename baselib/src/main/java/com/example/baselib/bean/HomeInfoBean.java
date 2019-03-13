package com.example.baselib.bean;

import java.util.List;

public class HomeInfoBean {


    /**
     * success : 1
     * msg : 操作成功
     * data : {"adList":[{"type":"2","cover_url":"http://yun-attachment.oss-cn-hangzhou.aliyuncs.com/njyb_logo/a1feb7a072bbbf5611a2bca8d2e7d94c.png","plate":"8","title":"\u201c互联网+\u201d 投票","type_id":"0","is_need_login":"1","url":"http://newwap.891jyb.com/?app=opus&amp;act=index","share_url":""}],"newsList":[{"id":"18","cate_id":"2","title":"测试18","short_info":"测试，测试","cover":"","content":"123123","look_num":"11","praise_num":"11","add_time":"1543288407","add_user":"1","mark":"1"},{"id":"17","cate_id":"2","title":"测试17","short_info":"测试，测试","cover":"","content":"123123","look_num":"11","praise_num":"11","add_time":"1543288407","add_user":"1","mark":"1"},{"id":"16","cate_id":"2","title":"测试16","short_info":"测试，测试","cover":"","content":"123123","look_num":"11","praise_num":"11","add_time":"1543288407","add_user":"1","mark":"1"},{"id":"15","cate_id":"2","title":"测试15","short_info":"测试，测试","cover":"","content":"123123","look_num":"11","praise_num":"11","add_time":"1543288407","add_user":"1","mark":"1"},{"id":"14","cate_id":"2","title":"测试14","short_info":"测试，测试","cover":"","content":"123123","look_num":"11","praise_num":"11","add_time":"1543288407","add_user":"1","mark":"1"},{"id":"13","cate_id":"2","title":"测试13","short_info":"测试，测试","cover":"","content":"123123","look_num":"11","praise_num":"11","add_time":"1543288407","add_user":"1","mark":"1"}],"cateList":[{"category_id":"0","name":"全部","intro":"商家推荐"},{"id":"11","name":"pzezyr","intro":"","parent_id":"0","type":"2","sort_order":"40","is_show":"1","image":"","category_id":"11"},{"id":"10","name":"pbzdeh","intro":"","parent_id":"0","type":"2","sort_order":"41","is_show":"1","image":"","category_id":"10"},{"id":"9","name":"obnsju","intro":"","parent_id":"0","type":"2","sort_order":"42","is_show":"1","image":"","category_id":"9"},{"id":"8","name":"juvqld","intro":"","parent_id":"0","type":"2","sort_order":"43","is_show":"1","image":"","category_id":"8"}],"middleList":[{"type":"2","cover_url":"http://yun-attachment.oss-cn-hangzhou.aliyuncs.com/njyb_logo/a1feb7a072bbbf5611a2bca8d2e7d94c.png","plate":"8","title":"\u201c互联网+\u201d 投票","type_id":"0","is_need_login":"1","url":"http://newwap.891jyb.com/?app=opus&amp;act=index","share_url":""}]}
     * code : 10000
     * request_id : 3104f86110ae8e32
     */

    public String success;
    public String msg;
    public DataBean data;
    public String code;
    public String request_id;

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
        public List<AdListBean> adList;
        public List<NewsListBean> newsList;
        public List<CateListBean> cateList;
        public List<MiddleListBean> middleList;

        public List<AdListBean> getAdList() {
            return adList;
        }

        public void setAdList(List<AdListBean> adList) {
            this.adList = adList;
        }

        public List<NewsListBean> getNewsList() {
            return newsList;
        }

        public void setNewsList(List<NewsListBean> newsList) {
            this.newsList = newsList;
        }

        public List<CateListBean> getCateList() {
            return cateList;
        }

        public void setCateList(List<CateListBean> cateList) {
            this.cateList = cateList;
        }

        public List<MiddleListBean> getMiddleList() {
            return middleList;
        }

        public void setMiddleList(List<MiddleListBean> middleList) {
            this.middleList = middleList;
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

            public String type;
            public String cover_url;
            public String plate;
            public String title;
            public String type_id;
            public String is_need_login;
            public String url;
            public String share_url;

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

        public static class NewsListBean {
            /**
             * id : 18
             * cate_id : 2
             * title : 测试18
             * short_info : 测试，测试
             * cover :
             * content : 123123
             * look_num : 11
             * praise_num : 11
             * add_time : 1543288407
             * add_user : 1
             * mark : 1
             */

            public String id;
            public String cate_id;
            public String title;
            public String short_info;
            public String cover;
            public String content;
            public String look_num;
            public String praise_num;
            public String add_time;
            public String add_user;
            public String mark;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getCate_id() {
                return cate_id;
            }

            public void setCate_id(String cate_id) {
                this.cate_id = cate_id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getShort_info() {
                return short_info;
            }

            public void setShort_info(String short_info) {
                this.short_info = short_info;
            }

            public String getCover() {
                return cover;
            }

            public void setCover(String cover) {
                this.cover = cover;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getLook_num() {
                return look_num;
            }

            public void setLook_num(String look_num) {
                this.look_num = look_num;
            }

            public String getPraise_num() {
                return praise_num;
            }

            public void setPraise_num(String praise_num) {
                this.praise_num = praise_num;
            }

            public String getAdd_time() {
                return add_time;
            }

            public void setAdd_time(String add_time) {
                this.add_time = add_time;
            }

            public String getAdd_user() {
                return add_user;
            }

            public void setAdd_user(String add_user) {
                this.add_user = add_user;
            }

            public String getMark() {
                return mark;
            }

            public void setMark(String mark) {
                this.mark = mark;
            }
        }

        public static class CateListBean  {
            /**
             * category_id : 0
             * name : 全部
             * intro : 商家推荐
             * id : 11
             * parent_id : 0
             * type : 2
             * sort_order : 40
             * is_show : 1
             * image :
             */

            public String category_id;
            public String name;
            public String intro;
            public String id;
            public String parent_id;
            public String type;
            public String sort_order;
            public String is_show;
            public String image;
            public int isClick;

//
//            public int getIsClick() {
//                return isClick;
//            }
//
//            public void setIsClick(int isClick) {
//                this.isClick = isClick;
//            }
//
//            public String getCategory_id() {
//                return category_id;
//            }
//
//            public void setCategory_id(String category_id) {
//                this.category_id = category_id;
//            }
//
//            public String getName() {
//                return name;
//            }
//
//            public void setName(String name) {
//                this.name = name;
//            }
//
//            public String getIntro() {
//                return intro;
//            }
//
//            public void setIntro(String intro) {
//                this.intro = intro;
//            }
//
//            public String getId() {
//                return id;
//            }
//
//            public void setId(String id) {
//                this.id = id;
//            }
//
//            public String getParent_id() {
//                return parent_id;
//            }
//
//            public void setParent_id(String parent_id) {
//                this.parent_id = parent_id;
//            }
//
//            public String getType() {
//                return type;
//            }
//
//            public void setType(String type) {
//                this.type = type;
//            }
//
//            public String getSort_order() {
//                return sort_order;
//            }
//
//            public void setSort_order(String sort_order) {
//                this.sort_order = sort_order;
//            }
//
//            public String getIs_show() {
//                return is_show;
//            }
//
//            public void setIs_show(String is_show) {
//                this.is_show = is_show;
//            }
//
//            public String getImage() {
//                return image;
//            }
//
//            public void setImage(String image) {
//                this.image = image;
//            }
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

            public String type;
            public String cover_url;
            public String plate;
            public String title;
            public String type_id;
            public String is_need_login;
            public String url;
            public String share_url;

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
    }
}
