package com.hzq.example.constants;

/**
 * @author 小强
 * @time 2018/8/3  11:26
 * @desc 常用量类
 */
public class Constant {

    public static String PAGE_COUNT = "12"; //请求数量--> 每次请求12条

    public static int PAGE_SIZE = 6; //少于6条就不显示加载更多


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

}
