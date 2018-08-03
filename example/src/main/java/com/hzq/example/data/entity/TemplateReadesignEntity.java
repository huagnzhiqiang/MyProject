package com.hzq.example.data.entity;

import java.util.List;

/**
 * @author 小强
 * @time 2018/8/2  16:47
 * @desc 首页案例/模板实体类
 */
public class TemplateReadesignEntity {


    /**
     * total : 70
     * rows : [{"id":88,"case_type":0,"title":"古人赏月习俗","cover_img":"https://api.i-top.cn/files/img/20180724/201807241021129253.png","url":"https://www.i-top.cn/html5/baidu-moon-c/index.html",
     * "price":55000,"designer_head_img":"https://api.i-top.cn/files/img/20180702/201807021021025955.jpg","designer_user_id":214,"check_status":2,"designer_name":"云设计工作室",
     * "create_datetime":"2018-07-24 10:26:43","commend":0,"show":1,"demand_count":0,"browse_count":33,"description":"作品用360度全景模式展示，中国风的插画，讲述古人的一些赏月习俗，每一个情景都有闪烁的红心可点击查看，用图文和对联来描述对应的情景。",
     * "customer_name":"百度手机助手"}]
     */

    private int total;
    /**
     * id : 88
     * case_type : 0
     * title : 古人赏月习俗
     * cover_img : https://api.i-top.cn/files/img/20180724/201807241021129253.png
     * url : https://www.i-top.cn/html5/baidu-moon-c/index.html
     * price : 55000
     * designer_head_img : https://api.i-top.cn/files/img/20180702/201807021021025955.jpg
     * designer_user_id : 214
     * check_status : 2
     * designer_name : 云设计工作室
     * create_datetime : 2018-07-24 10:26:43
     * commend : 0
     * show : 1
     * demand_count : 0
     * browse_count : 33
     * description : 作品用360度全景模式展示，中国风的插画，讲述古人的一些赏月习俗，每一个情景都有闪烁的红心可点击查看，用图文和对联来描述对应的情景。
     * customer_name : 百度手机助手
     */

    private List<RowsBean> rows;

    public int getTotal() { return total;}

    public void setTotal(int total) { this.total = total;}

    public List<RowsBean> getRows() { return rows;}

    public void setRows(List<RowsBean> rows) { this.rows = rows;}

    public static class RowsBean {
        private int id;
        private int case_type;
        private String title;
        private String cover_img;
        private String url;
        private int price;
        private String designer_head_img;
        private int designer_user_id;
        private int check_status;
        private String designer_name;
        private String create_datetime;
        private int commend;
        private int show;
        private int demand_count;
        private int browse_count;
        private String description;
        private String customer_name;

        public int getId() { return id;}

        public void setId(int id) { this.id = id;}

        public int getCase_type() { return case_type;}

        public void setCase_type(int case_type) { this.case_type = case_type;}

        public String getTitle() { return title;}

        public void setTitle(String title) { this.title = title;}

        public String getCover_img() { return cover_img;}

        public void setCover_img(String cover_img) { this.cover_img = cover_img;}

        public String getUrl() { return url;}

        public void setUrl(String url) { this.url = url;}

        public int getPrice() { return price;}

        public void setPrice(int price) { this.price = price;}

        public String getDesigner_head_img() { return designer_head_img;}

        public void setDesigner_head_img(String designer_head_img) { this.designer_head_img = designer_head_img;}

        public int getDesigner_user_id() { return designer_user_id;}

        public void setDesigner_user_id(int designer_user_id) { this.designer_user_id = designer_user_id;}

        public int getCheck_status() { return check_status;}

        public void setCheck_status(int check_status) { this.check_status = check_status;}

        public String getDesigner_name() { return designer_name;}

        public void setDesigner_name(String designer_name) { this.designer_name = designer_name;}

        public String getCreate_datetime() { return create_datetime;}

        public void setCreate_datetime(String create_datetime) { this.create_datetime = create_datetime;}

        public int getCommend() { return commend;}

        public void setCommend(int commend) { this.commend = commend;}

        public int getShow() { return show;}

        public void setShow(int show) { this.show = show;}

        public int getDemand_count() { return demand_count;}

        public void setDemand_count(int demand_count) { this.demand_count = demand_count;}

        public int getBrowse_count() { return browse_count;}

        public void setBrowse_count(int browse_count) { this.browse_count = browse_count;}

        public String getDescription() { return description;}

        public void setDescription(String description) { this.description = description;}

        public String getCustomer_name() { return customer_name;}

        public void setCustomer_name(String customer_name) { this.customer_name = customer_name;}

        @Override
        public String toString() {
            return "RowsBean{" + "id=" + id + ", case_type=" + case_type + ", title='" + title + '\'' + ", cover_img='" + cover_img + '\'' + ", url='" + url + '\'' + ", price=" + price + ", " +
                    "designer_head_img='" + designer_head_img + '\'' + ", designer_user_id=" + designer_user_id + ", check_status=" + check_status + ", designer_name='" + designer_name + '\'' + ", " +
                    "" + "" + "create_datetime='" + create_datetime + '\'' + ", commend=" + commend + ", show=" + show + ", demand_count=" + demand_count + ", browse_count=" + browse_count + ", " +
                    "description='" + description + '\'' + ", customer_name='" + customer_name + '\'' + '}';
        }
    }

    @Override
    public String toString() {
        return "TemplateReadesignEntity{" + "total=" + total + ", rows=" + rows + '}';
    }
}
