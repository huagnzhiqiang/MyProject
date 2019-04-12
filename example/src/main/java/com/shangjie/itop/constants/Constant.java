package com.shangjie.itop.constants;

/**
 * @author 小强
 * @time 2018/8/3  11:26
 * @desc 常用量类
 */
public class Constant {

    public static String PAGE_COUNT = "12"; //请求数量--> 每次请求12条

    public static int PAGE_SIZE = 12; //少于0条就不显示加载更多

    public static String NO_LOAD_MORE = "数据已加载完毕"; //加载更多没有数据的提示

    /**
     * 定向/竞标订单状态
     **/
    public static class CustomOrderStatus {

        public final static int UNACCEPT = 0;//待接单

        public final static int ACCEPT = 1;// 已接单

        public final static int REFUSE = 2;// 已拒绝

        public final static int BID = 3;// 竞标中

        public final static int BID_SUCESS = 4;//竞标成功

        public final static int BID_FAIL = 5;//竞标失败

        public final static int BID_CANCEL = 6;//竞标取消

        public final static int SUCESS = 7;//验收完成

        public final static int FAIL = 8;// 合作失败  逾期合作失败8.17

        public final static int COMPLETION = 9;//已完成

        public final static int PENDING = 10;//审核中

        public final static int NOT_PASS = 11;//审核不通过

        public final static int CANCELED = 12;//合作取消

        public final static int OFF = 13;//已下架

        public final static int INTERVENTION = 14;//平台介入

        public final static int OUTOF_DATE = 15;//已过期

        public final static int COMPLETE_REFUND = 16;//退款完成

        public final static int REFUNDS = 17;//退款中

    }


    /**
     * 热点文章类型articleType
     * 0:资讯  1:H5  2:视频  推荐|本地 其他
     */

    public static class articleType {

        public final static int ARTICLE_DEFAULT = -1;//默认推荐

        public final static int ARTICLE_INFORMATION = 0;//资讯

        public final static int ARTICLE_H5 = 1;//H5

        public final static int ARTICLE_VIDEO = 2;//视频

        public final static int ARTICLE_OTHER = 3;

    }

    /**
     * 用户类型UserType
     * 0:一般用户 1:设计师 2:企业 3:自营销
     */
    public static class UserType {

        public final static int USER_TYPE_0 = 0;//一般用户

        public final static int USER_TYPE_1 = 1;//设计师1

        public final static int USER_TYPE_2 = 2;//企业2

        public final static int USER_TYPE_3 = 3;//自营销3
    }

    /**
     * 会员等级
     * 0:普通用户 1:粉钻版 2:蓝钻版 3:黑钻版
     */
    public static class VipType {

        public final static int VIP_0 = 0;//普通用户

        public final static int VIP_1 = 1;//粉钻版

        public final static int VIP_2 = 2;//蓝钻版

        public final static int VIP_3 = 3;//黑钻版
    }
}
