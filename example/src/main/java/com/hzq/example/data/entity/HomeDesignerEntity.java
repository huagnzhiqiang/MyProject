package com.hzq.example.data.entity;

import java.util.List;

/**
 * @author 小强
 * @time 2018/10/18  14:14
 * @desc 首页设计师页面实体类
 */
public class HomeDesignerEntity {

    /**
     * total : 128
     * rows : [{"id":119,"head_img":"https://api.i-top.cn/files/img/20181017/201810171522440120.PNG","nickname":"清风","level":0,"field":"194,200,202","fieldName":"产品介绍,商务科技,清新文艺","isfollow":0,"openservice":0,"product_count":0,"case_count":0,"fans_count":0,"city_name":"广州市","product_case_list":[]},{"id":779,"head_img":"","nickname":"普通用户131892","level":0,"field":"357","fieldName":"动画","isfollow":0,"openservice":0,"product_count":0,"case_count":0,"fans_count":0,"city_name":"广州市","product_case_list":[]},{"id":778,"head_img":"","nickname":"普通用户154696","level":0,"field":"203,355,356","fieldName":"卡通手绘,插画,手绘","isfollow":0,"openservice":0,"product_count":0,"case_count":0,"fans_count":0,"city_name":"北京市","product_case_list":[]},{"id":420,"head_img":"https://api.i-top.cn/files/img/20181016/201810161452238273.PNG","nickname":"YEDONGMEI","level":0,"field":"359","fieldName":"视频","isfollow":0,"openservice":0,"product_count":0,"case_count":0,"fans_count":0,"city_name":"广州市","product_case_list":[]},{"id":237,"head_img":"https://api.i-top.cn/files/img/20181016/201810161203176617.PNG","nickname":"MONKEY-大鱼\u2014飞","level":0,"field":"203,353,354","fieldName":"卡通手绘,平面,UI","isfollow":0,"openservice":0,"product_count":0,"case_count":0,"fans_count":0,"city_name":"广州市","product_case_list":[]},{"id":741,"head_img":"https://api.i-top.cn/files/img/20181016/201810161053416678.jpg","nickname":"18318515507","level":0,"field":"353,354,356","fieldName":"平面,UI,手绘","isfollow":0,"openservice":0,"product_count":0,"case_count":0,"fans_count":0,"city_name":"广州市","product_case_list":[]},{"id":754,"head_img":"https://api.i-top.cn/files/img/20181015/201810151613425757.PNG","nickname":"九木绘设计工作室","level":0,"field":"353,355,358","fieldName":"平面,插画,海报","isfollow":0,"openservice":0,"product_count":0,"case_count":0,"fans_count":0,"city_name":"广州市","product_case_list":[]},{"id":747,"head_img":"https://api.i-top.cn/files/img/20181015/201810151007568091.PNG","nickname":"梁理艺","level":0,"field":"353","fieldName":"平面","isfollow":0,"openservice":0,"product_count":0,"case_count":0,"fans_count":1,"city_name":"广州市","product_case_list":[]},{"id":614,"head_img":"","nickname":"普通用户199069","level":0,"field":"353,355,356","fieldName":"平面,插画,手绘","isfollow":0,"openservice":0,"product_count":0,"case_count":0,"fans_count":1,"city_name":"广州市","product_case_list":[]},{"id":405,"head_img":"https://api.i-top.cn/files/img/20181012/201810121753079343.jpg","nickname":"DX3906","level":0,"field":"192","fieldName":"企业宣传","isfollow":0,"openservice":0,"product_count":0,"case_count":0,"fans_count":0,"city_name":"广州市","product_case_list":[]},{"id":734,"head_img":"https://api.i-top.cn/files/img/20181012/201810121700557724.PNG","nickname":"普通用户166870","level":0,"field":"194,200,354","fieldName":"产品介绍,商务科技,UI","isfollow":0,"openservice":0,"product_count":0,"case_count":0,"fans_count":1,"city_name":"市辖区","product_case_list":[]},{"id":690,"head_img":"","nickname":"普通用户196770","level":0,"field":"195,353,355","fieldName":"活动促销,平面,插画","isfollow":0,"openservice":0,"product_count":0,"case_count":0,"fans_count":1,"city_name":"大连市","product_case_list":[]}]
     */

    private int total;
    /**
     * id : 119
     * head_img : https://api.i-top.cn/files/img/20181017/201810171522440120.PNG
     * nickname : 清风
     * level : 0
     * field : 194,200,202
     * fieldName : 产品介绍,商务科技,清新文艺
     * isfollow : 0
     * openservice : 0
     * product_count : 0
     * case_count : 0
     * fans_count : 0
     * city_name : 广州市
     * product_case_list : []
     */

    private List<RowsBean> rows;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<RowsBean> getRows() {
        return rows;
    }

    public void setRows(List<RowsBean> rows) {
        this.rows = rows;
    }

    public static class RowsBean {
        /**
         * id : 26
         * head_img :
         * nickname : 普通用户117717
         * level : 0
         * field : 企业宣传,企业招聘,产品介绍,促销活动
         */

        private int id;
        private String head_img;
        private String nickname;
        private int level;
        private String field;
        private String fieldName;
        private int follow_user_id;
        private int user_type;
        private String create_datetime;
        private String ProvinceName;
        private String city_name;

        public String getProvinceName() {
            return ProvinceName;
        }

        public void setProvinceName(String provinceName) {
            ProvinceName = provinceName;
        }

        public String getCityName() {
            return city_name;
        }

        public void setCityName(String cityName) {
            city_name = cityName;
        }

        private int follow;
        private int openservice;
        private int isfollow;
        private int fans_count;
        private int product_count;
        private int case_count;

        public String getFieldName() {
            return fieldName;
        }

        public void setFieldName(String fieldName) {
            this.fieldName = fieldName;
        }

        public int getIsfollow() {
            return isfollow;
        }

        public int getFans_count() {
            return fans_count;
        }

        public void setFans_count(int fans_count) {
            this.fans_count = fans_count;
        }

        public int getProduct_count() {
            return product_count;
        }

        public void setProduct_count(int product_count) {
            this.product_count = product_count;
        }

        public int getCase_count() {
            return case_count;
        }

        public void setCase_count(int case_count) {
            this.case_count = case_count;
        }

        public List<ProductCasesBean> getProduct_case_list() {
            return product_case_list;
        }

        public void setProduct_case_list(List<ProductCasesBean> product_case_list) {
            this.product_case_list = product_case_list;
        }

        private List<ProductCasesBean> product_case_list;




        public int isIsfollow() {
            return isfollow;
        }

        public void setIsfollow(int isfollow) {
            this.isfollow = isfollow;
        }

        public int getFollow() {
            return follow;
        }

        public void setFollow(int follow) {
            this.follow = follow;
        }

        public int getFollow_user_id() {
            return follow_user_id;
        }

        public void setFollow_user_id(int follow_user_id) {
            this.follow_user_id = follow_user_id;
        }

        public int getUser_type() {
            return user_type;
        }

        public void setUser_type(int user_type) {
            this.user_type = user_type;
        }

        public String getCreate_datetime() {
            return create_datetime;
        }

        public void setCreate_datetime(String create_datetime) {
            this.create_datetime = create_datetime;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getHead_img() {
            return head_img;
        }

        public void setHead_img(String head_img) {
            this.head_img = head_img;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public int getLevel() {
            return level;
        }

        public void setLevel(int level) {
            this.level = level;
        }

        public String getField() {
            return field;
        }

        public void setField(String field) {
            this.field = field;
        }

        public int getOpenservice() {
            return openservice;
        }

        public void setOpenservice(int openservice) {
            this.openservice = openservice;
        }


        public class ProductCasesBean{
            private String data_type;
            private int id;
            private int user_id;
            private String cover_img;
            private String create_datetime;

            public int getUser_id() {
                return user_id;
            }

            public void setUser_id(int user_id) {
                this.user_id = user_id;
            }

            public String getData_type() {
                return data_type;
            }

            public void setData_type(String data_type) {
                this.data_type = data_type;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getCreate_datetime() {
                return create_datetime;
            }

            public void setCreate_datetime(String create_datetime) {
                this.create_datetime = create_datetime;
            }

            public String getCover_img() {
                return cover_img;
            }

            public void setCover_img(String cover_img) {
                this.cover_img = cover_img;
            }

            @Override
            public String toString() {
                return "ProductCasesBean{" + "data_type='" + data_type + '\'' + ", id=" + id + ", user_id=" + user_id + ", cover_img='" + cover_img + '\'' + ", create_datetime='" + create_datetime + '\'' + '}';
            }

        }

        @Override
        public String toString() {
            return "UseDesignerListBean{" + "id=" + id + ", head_img='" + head_img + '\'' + ", nickname='" + nickname + '\'' + ", level=" + level + ", field='" + field + '\'' + ", fieldName='" +
                    fieldName + '\'' + ", follow_user_id=" + follow_user_id + ", user_type=" + user_type + ", create_datetime='" + create_datetime + '\'' + ", follow=" + follow + ", openservice=" +
                    openservice + ", isfollow=" + isfollow + ", fans_count=" + fans_count + ", product_count=" + product_count + ", case_count=" + case_count + ", product_case_list=" + product_case_list + '}';
        }
    }

    @Override
    public String toString() {
        return "HomeDesignerEntity{" + "total=" + total + ", rows=" + rows + '}';
    }
}
