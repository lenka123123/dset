package com.wokun.dset.model;

public class Constants {

    public static final String ACC = "account";
    public static final String PWD = "pwd";
    public static final String USERID = "userid";
    public static final String TOKEN = "token";
    public static final String LANGUAGE = "lang";
    public static final String AREA_CODE_KEY = "area_code_key";

    public static double LONGITUDE = 0;//经度
    public static double LATITUDE = 0;//纬度
    public static String PROVICE = "";//省
    public static String CITY = "";//市
    //   public static String AREA = "";//县

    public static final int RESULT_TO_FRAGMENT = 556;
    public static final int RESULT_PHOTO_CODE = 0x0201; // 拍照
    public static final int RESULT_LOAD_CODE = 0x0202;  // 相册
    public static final int RESULT_CROP_CODE = 0x0203;  // 裁剪

    public static final int REQUEST_CODE_SELECT = 100;//相册
    public static final int REQUEST_CODE_PREVIEW = 101;//预览
    public static final int REQUEST_CODE_TAKE_PHOTO = 102;//拍照
    public static final int ALBUM_REQUEST_CODE = 103;

    public static final long PHONE_CODE_TIME = 60; // 短信倒计时时长

    public static final String KEY = "jdkj963214785";

    public static final String LOGIN = "api/loginapi.html";
    public static final String GET_MAIN = "api/usermsg.html";
    public static final String VENSION = "api/versionupdate.html";
    public static final String SIGN = "api/checksign.html";
    public static final String ROLL_OUT = "register/send.html";
    public static final String ROLL_OUT_record = "register/sendoutrecord.html";
    public static final String ROLL_OUT_CHECK = "register/checkaccount.html";
    public static final String NODE_TURNOUT = "register/nodeturnout.html";
    public static final String ROLL_IN = "register/sendin.html";
    public static final String ASSETS_ADD = "register/conver.html";//资产增值页面
    public static final String ASSETS_ADD_CHECK = "register/doconver.html";//资产增值 兑换
    public static final String ASSETS_ADD_RECORD = "register/converrecord.html";//资产增值 记录
    public static final String SHARE_CODE = "site/sharecode.html";//分享二维码 页面
    public static final String SHARE_CODE_RECORD = "site/sharerecordload.html";//分享二维码 记录
    public static final String ADVICE_RECORD = "user/mycomplain.html";//建议 记录
    public static final String ADVICE_RECORD_DETAIL = "user/complaincontent.html";//建议 详情
    public static final String MY_DATA = "user/mycontent.html";//我的 页面
    public static final String MODIFY_NICK = "user/alternick.html";//修改昵称
    public static final String BANK_CARD = "user/addcard.html";//银行卡列表
    public static final String BANK_CARD_DELETE = "user/bank.html";//删除银行卡
    public static final String BANK_CARD_ADD = "user/back.html";//添加银行卡
    public static final String BANK_CARD_UPDATE = "user/updbankdata.html";//修改银行卡
    public static final String POST_PICTURE = "user/comuphoto.html";//上传图片
    public static final String MODIFY_LOGIN_PWD = "user/alterlogpwd.html";//修改登录密码
    public static final String MODIFY_PAY_PWD = "user/alterpaypwd.html";//修改支付密码
    public static final String NOTICE_LIST = "user/notice.html";//公告列表
    public static final String MESSAGE_LIST = "user/mynotify.html";//消息列表
    public static final String MESSAGE_DETAIL = "user/mynotifycontent.html";//消息详情
    public static final String NOTICE_DETAIL = "user/noticecontent.html";//公告详情
    public static final String ABOUT = "user/about.html";//关于
    public static final String REGISTER = "api/registerapi.html";//注册
    public static final String GET_CODE = "api/intsendmsg.html";//获取验证码
    public static final String FORGET_PWD = "api/forgetpasswordapi.html";//忘记密码
    public static final String LB_BUY = "trade/buying.html";//求购页面
    public static final String LB_BUY_CHECK = "trade/tradebuy.html";//求购请求
    public static final String LB_SELL = "trade/sellinglistload.html";//卖出页面
    public static final String LB_SELL_CHECK = "trade/tradeorder.html";//买入
    public static final String BILL_RECORD_LIST = "profile/walletrecordload.html";//记录页面
    public static final String BILL_RECORD_TYPE = "profile/walletrecord.html";//类型
    public static final String ADVICE_REQUEST = "user/comsubmit.html";//申诉建议
    public static final String CHANGE_HEAD = "user/updicon.html";//修改头像
    public static final String GET_MOBILE = "user/showphone.html";//获取手机号码
    public static final String FORGET_PAY_PWD = "user/forgetpaypwd.html";//忘记支付密码
    public static final String LOGOUT = "site/logout.html";//退出登录
    public static final String DIGITAL_ASSETS = "trade/assets.html";//数字资产
    public static final String LKC_ROLL_OUT = "profile/sendcurrency.html";//Lkc转出
    public static final String LKC_ROLL_OUT_CHECK = "profile/checkinputinfo.html";//Lkc转出提交
    public static final String LKC_ROLL_OUT_RECORD = "profile/outrecord.html";//Lkc转出记录
    public static final String LKC_DISPOSE = "profile/freezecare.html";//Lkc定存
    public static final String LKC_DISPOSE_CHECK = "profile/dofreezecare.html";//Lkc定存提交
    public static final String LKC_DISPOSE_RECORD = "profile/freezerecord.html";//Lkc定存记录
    public static final String PURCHASE = "trade/applybuy.html";//申购页面
    public static final String PURCHASE_CHECK = "trade/applypurch.html";//申购请求
    public static final String PURCHASE_RECORD = "trade/myapplylistload.html";//申购记录
    public static final String RAISE_PAGE = "profile/addcare.html";//众筹页面
    public static final String RAISE_BUY = "profile/buycare.html";//众筹购买lkc页面
    public static final String RAISE_BUY_CHECK = "profile/dobuycare.html";//众筹购买lkc提交
    public static final String RAISE_BUY_RECORD = "profile/mycareorderload.html";//众筹lkc购买记录
    public static final String AREA_CODE = "api/areacode.html";//获取区号
    public static final String APPLY_SUPER_NODE = "user/applysupernode.html";//金冠会员申请超级节点
    public static final String MALL_HOME_BOTTOM = "mall/getcategory.html";//商城首页底部
    public static final String MALL_HOME_TOP = "mall/dsytshopindex.html";//商城首页顶部
    public static final String NODETURN_OUT = "register/nodeturnout.html";//通过超级节点转出
    public static final String ADDRESS_LIST = "server/myaddress.html";//收货地址列表
    public static final String DELETE_ADDRESS = "server/delmyaddress.html";//删除收货地址
    public static final String SET_DEFAULT = "server/updatedefault.html";//设为默认地址
    public static final String ADD_ADDRESS = "server/addmyaddress.html";//添加收货地址
    public static final String UPDATE_ADDRESS = "server/updatemyaddress.html";//编辑收货地址
    public static final String BUY_CARE = "profile/buycare.html";//众筹购买页面
    public static final String DO_BUY_CARE = "profile/dobuycare.html";//众筹购买提交
    public static final String UP_LEVEL = "user/upgrade.html";//手动会员升级

    public static final String MY_BAOJIA = "trade/my-offer.html";//我要报价
    public static final String BUY_ta = "trade/buy-ta.html";//买他的报价
    public static final String MY_BAOJIAJL = "trade/offer-record.html";//报价记录
    public static final String TRADEORDER = "trade/tradeorderlistload.html";//达事币交易
    public static final String CANCEL_DINDAN = "trade/cancel.html";//取消订单
    public static final String BUYCONFIRM = "trade/buyconfirm.html";//订单列表确认付款（请求）
    public static final String SELLCONFIRM = "trade/sellconfirm.html";//订单列表确认收款（请求）
    public static final String SINGLEORDRT = "trade/singleorderload.html";//订单列表详情
    public static final String USER_UPGRADE = "user/user-upgrade.html";    //  会员等级升级页面接口
    public static final String USER_GRADE = "user/grade-info.html";     //会员说明
    public static final String BUSINESS = "business/class.html";     //商城标题
    public static final String BUSINESS_LIST = "business/list.html";     //商城
    public static final String BUSINESS_NEW = "business/new-retail.html";
    public static final String BANK_TYPE = "user/bank-type.html";//银行卡
    public static final String BUSINESS_DETAIL = "business/detail.html";//
    public static final String APP_ID = "wxd670b2e2b07f5175";
    public static String BASE_URL = "http://api.dasether.com/";

    //public static String BASE_STORE_URL = "http://api.dset.com/";
    public static String STORE_HOME_URL = "shop/index.html"; //商城首页
    public static String STORE_GOODS_LIST = "shop-goods/goods-list.html"; //商城搜索列表
    public static String STORE_GOODS_DETAIL = "shop-goods/goods-detail.html"; //商城商品详情


    /**
     * 地址管理
     */
    //获取用户地址列表
    public static String ADDRESS_INDEX_URL = "v1/address/index";
    //获取省市区
    public static String ADDRESS_GET_AREA_URL = "v1/address/get-area";
    //设置用户默认地址
    public static String ADDRESS_SET_DEFAULT_URL = "v1/address/set-default";
    //删除用户地址
    public static String ADDRESS_DELETE_ADDRESS_URL = "v1/address/delete-address";
    //添加用户地址
    public static String ADDRESS_ADD_ADDRESS_URL = "v1/address/add-address";
    //修改用戶地址
    public static String ADDRESS_ALTER_ADDRESS_URL = "v1/address/alter-address";
    //上传位置信息（经纬度）
    public static String ADDRESS_GET_POSITION_URL = "v1/address/get-position";
    //获取用户默认地址
    public static String ADDRESS_GET_DEFAULT_URL = "v1/address/get-default";
    //订单确认地址选择接口（自提、快递）
    public static String ORDER_CONFIRM = "v1/retail/order-confirm";


    /**
     * 购物车
     */
    //商品加入购物车
    public static String CART_ADD_GOODS_URL = "v1/cart/add-goods";
    //购物车页面
    public static String CART_LIST_URL = "v1/cart/list";
    //删除商品
    public static String CART_DELETE_GOODS_URL = "v1/cart/delete-goods";
    //去结算
    public static String CART_UPDATE_CART_URL = "v1/cart/update-cart";
    //商品订单确认页面数据显示接口
    public static String CART_ORDER_SHOW_URL = "v1/cart/order-show";
    //通过地址获取运费
    public static String CART_GET_FREIGHT_PRICE_URL = "v1/cart/get-freight-price";
    //商品订单确认页面数据显示接口
    public static String GO_CART_ORDER_SHOW = "v1/order/go-order-show";

    /**
     * 订单（营养师）
     */
    //确认务（接受用户申请）
    public static String DIETITIAN_ACCEPT_SERVICE_URL = "v1/dietitian/accept-service";
    //查看服务订单评价
    public static String DIETITIAN_LOOK_EVAL_URL = "v1/dietitian/look-eval";
    //回复评价（服务订单）
    public static String DIETITIAN_REPLY_EVAL_URL = "v1/dietitian/reply-eval";
    //服务订单退款详情
    public static String DIETITIAN_REFUND_DETAIL_URL = "v1/dietitian/refund-detail";

    /**
     * 融云即时聊天
     */
    public static String IM_GET_TOKEN_URL = "v1/im/get-token";


    //智慧零售入住
    public static String JOIN_RETAIL = "v1/join/retail";


    /**
     * 消息通知
     */
    //系统通知
    public static String UCENTER_GET_MY_NOTICE_URL = "v1/ucenter/get-my-notice";
    //服务消息
    public static String UCENTER_GET_SERVICE_NOTICE_URL = "v1/ucenter/get-service-notice";

    /**
     * 社交资产
     */
    //交易市场数据（买进卖出）
    public static String ASSETS_RELEASE_LIST_URL = "v1/assets/release-list";
    //我的交易记录
    public static String ASSETS_MY_DEAL_URL = "v1/assets/my-deal";
    //我的发布记录数据
    public static String ASSETS_MY_RELEASE_RECORD_URL = "v1/assets/my-release-record";
    //发布买卖需求页面所需的数据
    public static String ASSETS_RELEASE_URL = "v1/assets/release";
    //处理发布买卖社交资产的需求
    public static String ASSETS_DO_RELEASE_URL = "v1/assets/do-release";
    //我要买入操作
    public static String ASSETS_DO_MY_BUY_URL = "v1/assets/do-my-buy";
    //我要出售操作
    public static String ASSETS_DO_MY_SELL_URL = "v1/assets/do-my-sell";
    //编辑发布记录
    public static String ASSETS_EDIT_URL = "v1/assets/edit";
    //删除发布记录


    /**
     * 字符串常量
     */
    //  public static final String KEY = "ysd3w6ei2asd8h423jsa2sdf"; //秘钥
    public static final String ANDROID = "ANDROID";
    // public static final String TOKEN = "token";
    public static final String USER_JSON = "user_json";
    public static final String USER_INFO_JSON = "userinfo_json";
    public static final String DIETITIAN_INFO_JSON = "dietitianinfo_json";
    public static final String USER = "user";
    public static final String CART_INFO = "cart_info";
    public static final String TOP_CATEGORY_JSON = "top_category_json";
    public static final String SUB_CATEGORY_JSON = "sub_category_json";
    public static final String GOODS_LIST_JSON = "goods_list_json";
    public static final String PAGE = "page";
    public static final String PAGE_SIZE = "page_size";

    public static final String BANNER = "banner";
    public static final String Notice = "notice";
    public static final String ADDRESS_JSON = "address.json";
    public static final String ORDER_ID = "order_id";
    public static final String STATE = "state";
    public static final String TYPE = "type";
    public static final String TITLE = "title";
    public static final String FIELD_ID = "field_id";
    public static final String TYPE_NAME = "type_name";
    public static final String NA_ID = "na_id";
    public static final String NS_ID = "ns_id";
    public static final String PROFILE = "profile";
    public static final String DIETITIAN_ID = "dietitian_id";
    public static final String DIETITIAN_USER_ID = "dietitian_user_id";
    public static final String NUMBER = "number";
    public static final String PAY_PRICE = "pay_price";
    public static final String ONLINE_PAYMENT_URL = "online_payment_url";
    public static final String STORE_ID = "store_id";
    public static final String ACCOUNT_ID = "account_id";
    public static final String ACCOUNT_TYPE_NAME = "account_type_name";
    public static final String ACCOUNT_TYPE = "account_type";
    public static final String DATA = "data";
    public static final String CONTACTS = "contacts";
    public static final String TEL = "tel";
    public static final String PROVINCE_ID = "province_id";
    public static final String ADDRESS = "address";
    public static final String LONG = "long";
    public static final String LAT = "lat";

    public static final String LinkMan = "linkman";
    public static final String Store_Picture = "storepicture";
    public static final String Store_Name = "storename";
    public static final String MOBILE = "mobile";
    public static final String PASSWORD = "password";
    public static final String CODE = "code";
    public static final String MOBILE_CODE = "mobile_code";
    public static final String SMS_CODE = "smscode";

    public static final String ADDRESS_ID = "address_id";
    public static final String AREA = "area";
    public static final String CITY_ID = "city_id";
    public static final String DISTRICT_ID = "district_id";
    public static final String DISCOUNT_ID = "discount_id";
    public static final String GOODS_IMAGE = "goods_image";
    public static final String GOODS_NAME = "goods_name";
    public static final String GOODS_ID = "goods_id";
    public static final String PAY_INTEGRAL = "pay_integral";
    public static final String NUMS = "nums";
    public static final String REQUEST_CODE = "request_code";
    public static final String HEAD_LOGO = "head_logo";
    public static final String KEYWORDS = "keywords";
    public static final String SOURCE_ID = "source_id";
    public static final String USER_TYPE = "user_type";
    public static final String SOURCE_TYPE = "source_type";
    public static final String URL = "url";
    public static final String DQR = "dqr";
    public static final String YJJ = "yjj";
    public static final String FWZ = "fwz";
    public static final String YGQ = "ygq";
    public static final String R_ID = "r_id";
    public static final String NUMBERS = "numbers";

    public static final String ORDER_NUMBER = "order_number";
    public static final String SERVICE_PRICE = "service_price";
    public static final String SERVICE_TIME = "service_time";
    public static final String SERVICE_TOTAL_PRICE = "service_total_price";
    public static final String PAY_TYPE = "pay_type";
    public static final String TAB_POSITION = "tab_position";
    public static final String ORDER_STATE = "order_state";
    public static final String STATUS = "status";

    public static final String ACCOUNT_NAME = "account_name";
    public static final String ACCOUNT_CODE = "account_code";
    public static final String WITHDRAW_CASH = "withdraw_cash";
    public static final String NEW_MOBILE = "new_mobile";
    public static final String SERVICE_DAYS = "service_days";
    public static final String SERVICE_FEE = "service_fee";
    public static final String PAY_MONEY = "pay_money";
    public static final String LOGIN_TYPE = "login_type";
    public static final String INVITE_CODE = "invite_code";
    public static final String CONTENT = "content";
    public static final String OLD_PASSWORD = "old_password";
    public static final String NEW_PASSWORD = "new_password";
    public static final String CART_ID_STR = "cart_id_str";
    public static final String ORDER_TOTAL = "order_total";
    public static final String ORDER = "order";
    public static final String N = "n";
    public static final String PD = "pd";
    public static final String PU = "pu";
    public static final String S = "s";
    public static final String Z = "z";
    public static final String USER_ID = "user_id";
    public static final String TIME_STAMP = "time_stamp";
    // public static final String SIGN = "sign";
    public static final String SGC_ID = "sgc_id";
    public static final String GOOD_CLASS_ID = "good_class_id";
    public static final String CAN_USE = "can_use";
    public static final String UNIT_PRICE = "unit_price";

    public static final String STORE_CODE = "store_code";
    public static final String STORE_LIST = "store-list";

    public static final String GOODS_SORT = "goods_sort";
    public static final String GID = "gid";
    public static final String GPD = "gpd";
    public static final String GPA = "gpa";
    public static final String GCD = "gcd";
    public static final String GCA = "gca";

    public static final String FAVORITES_TYPE = "favorites_type";
    public static final String ALIPAY = "alipay";
    public static final String WXPAY = "wxpay";

    public static final String UPLOAD_TYPE_EVAL_GOODS = "eval_goods";     //多图片上传类型-评价商品
    public static final String UPLOAD_TYPE_EVAL_SERVICE = "eval_service"; //多图片上传类型-评价营养师
    public static final String UPLOAD_TYPE_ENCLOSURE = "enclosure";       //多图片上传类型-认证资料上传
    public static final String UPLOAD_FEEDBACK = "feedback";
    public static final String STORE_NAME = "store_name";
    public static final String STORE_ADDRESS = "store_address";
    public static final String UPLOAD_TYPE_AVATAR = "avatar";             //单图片上传类型-头像
    public static final String UPLOAD_TYPE_ID_CARD = "id_card";           //单图片上传类型-身份证
    public static final String ALL = "all";

    public static final String SHOP = "shop";
    public static final String MANAGE = "manage";
    public static final String BIND = "bind";

    public static final String UPLOAD_FILE = "UploadForm[file]";

    public static final String ID = "id";
    public static final String TYPE_ID = "type_id";
    public static final String NAME = "name";
    public static final String SEX = "sex";
    public static final String BIRTHDAY = "birthday";
    public static final String IDC_NO = "idc_no";
    public static final String FIELD_IDS = "field_ids";
    public static final String WORK_LIFE = "work_life";
    public static final String CREDENTIALS = "credentials";
    public static final String HEADIMG = "headimg";
    public static final String IDC_FRONT = "idc_front";
    public static final String IDC_BACK = "idc_back";
    public static final String IDC_PIC = "idc_pic";
    public static final String AREA_INFO = "area_info";
    public static final String NULL_MOBILE_MESSAGE = "手机号码不能为空";
    public static final String NULL_PWD_MESSAGE = "密码不能为空";
    public static final String NULL_CODE_MESSAGE = "验证码不能为空";
    public static final String LOGIN_FAILURE_MESSAGE = "登录失败";
    public static final String NET_WORK_ERROR = "网络请求出错，请检查您的网络";

    /**
     * 数值常量
     */
    public static final int TAB_POSITION_HOME = 0;           //首页位置
    public static final int TAB_POSITION_ZB = 1;             //直播室位置
    public static final int TAB_POSITION_SHOP_CART = 2;      //购物车位置
    public static final int TAB_POSITION_UCENTER = 3;        //个人中心位置

    public static final int FAVORITES_TYPE_GOODS = 0;         //商品收藏
    public static final int FAVORITES_TYPE_STORE = 1;         //店铺收藏
    public static final int FAVORITES_TYPE_DIETICIAN = 2;     //营养师收藏
    public static final int FAVORITES_TYPE_ARTICLE = 3;       //文章收藏

    //requestCode
    public static final int LOGIN_REQUEST_CODE = 13353;       //登录请求码
    public static final int SETTING_REQUEST_CODE = 13354;     //设置请求码
    public static final int SIGN_IN_REQUEST_CODE = 13355;     //签到请求码
    public static final int ASSET_INDEX_REQUEST_CODE = 13356;     //社交资产码

    public static final int NO_SIGN = 1;                     //请求状态，不带Token
    public static final int WITH_SIGN = 2;                    //请求状态，需要携带Token
    public static final int LOGIN_WITH_SIGN = 3;             //请求状态，如果为登录状态则携带Token

    //resultCode
    public static final int LOGIN_RESULT_CODE = 13391;        //登录响应码

    public static final int SETTING_RESULT_CODE = 13390;      //设置响应码

    public static final int PG_SIZE = 20;
}