package com.shangjie.itop.data.entity;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.List;

/**
 * @author 小强
 * @time 2018/10/27  15:59
 * @desc 自营销推荐页面实体类
 */
public class RecomentEntity {

    /**
     * 资讯 0 H5 1 视频 2 推荐|本地 其他
     */

    public final static int ARTICLE_INFORMATION = 0;//资讯
    public final static int ARTICLE_H5 = 1;//H5
    public final static int ARTICLE_VIDEO = 2;//视频

    /**
     * total : 4
     * rows : [{"id":245,"user_id":17,"head_img":"http://192.168.7.100:8028/files/img/20180824/201808241527088731.jpg","nickname":"小强无限公司","title":"我","cover_img":"http://192.168.7.100:8028/files/img/20180607/201806071345127140.png","url":"http://192.168.7.100:8028/files/file/20180607/201806071345179525.mp4","article_type":2,"price":0,"praise_count":-43,"collection_count":0,"browse_count":161,"comment_count":16,"transmit_count":0,"create_datetime":"2018-06-07 13:45:18","share_title":"我","share_url":"http://192.168.7.100/page/article-details.html?id=245","share_description":"","nesting_url":"http://192.168.7.100:8029/Page/app/article-details.html?id=245","is_collection":false,"is_activity":0,"activity_id":0,"activity_production_type":0},{"id":18,"user_id":23,"head_img":"http://192.168.7.100:8028/files/img/20180209/201802091503260825.jpg","nickname":"普通用户188887","title":"测试文章11111122333","cover_img":"http://192.168.7.100:8029/Lib/images/main/banner1.jpg","url":"<embed height=\"600\" width=\"100%\" quality=\"high\" allowfullscreen=\"true\" type=\"application/x-shockwave-flash\" src=\"//static.hdslb.com/miniloader.swf\" flashvars=\"aid=9207959&page=1\" pluginspage=\"//www.adobe.com/shockwave/download/download.cgi?P1_Prod_Version=ShockwaveFlash\"><\/embed>","article_type":2,"price":0,"praise_count":0,"collection_count":11,"browse_count":16,"comment_count":10,"transmit_count":1,"create_datetime":"2018-01-18 11:46:41","share_title":"测试文章11111122333","share_url":"https://www.i-top.cn/page/article-details.html?id=18","share_description":"测试文章11111122333","nesting_url":"http://192.168.7.100:8029/Page/app/article-details.html?id=18","is_collection":false,"is_activity":0,"activity_id":0,"activity_production_type":0},{"id":17,"user_id":23,"head_img":"http://192.168.7.100:8028/files/img/20180209/201802091503260825.jpg","nickname":"普通用户188887","title":"测试文章11111122333","cover_img":"http://192.168.7.100:8029/Lib/images/main/banner1.jpg","url":"<embed height=\"600\" width=\"100%\" quality=\"high\" allowfullscreen=\"true\" type=\"application/x-shockwave-flash\" src=\"//static.hdslb.com/miniloader.swf\" flashvars=\"aid=9207959&page=1\" pluginspage=\"//www.adobe.com/shockwave/download/download.cgi?P1_Prod_Version=ShockwaveFlash\"><\/embed>","article_type":2,"price":0,"praise_count":0,"collection_count":4,"browse_count":12,"comment_count":12,"transmit_count":1,"create_datetime":"2018-01-18 11:46:41","share_title":"测试文章11111122333","share_url":"https://www.i-top.cn/page/article-details.html?id=17","share_description":"测试文章11111122333","nesting_url":"http://192.168.7.100:8029/Page/app/article-details.html?id=17","is_collection":false,"is_activity":0,"activity_id":0,"activity_production_type":0},{"id":16,"user_id":23,"head_img":"http://192.168.7.100:8028/files/img/20180209/201802091503260825.jpg","nickname":"普通用户188887","title":"测试文章11111122333","cover_img":"http://192.168.7.100:8029/Lib/images/main/banner1.jpg","url":"<embed height=\"600\" width=\"100%\" quality=\"high\" allowfullscreen=\"true\" type=\"application/x-shockwave-flash\" src=\"//static.hdslb.com/miniloader.swf\" flashvars=\"aid=9207959&page=1\" pluginspage=\"//www.adobe.com/shockwave/download/download.cgi?P1_Prod_Version=ShockwaveFlash\"><\/embed>","article_type":2,"price":0,"praise_count":0,"collection_count":3,"browse_count":82762,"comment_count":6,"transmit_count":1,"create_datetime":"2018-01-18 11:46:41","share_title":"测试文章11111122333","share_url":"https://www.i-top.cn/page/article-details.html?id=16","share_description":"测试文章11111122333","nesting_url":"http://192.168.7.100:8029/Page/app/article-details.html?id=16","is_collection":false,"is_activity":0,"activity_id":0,"activity_production_type":0}]
     */

    private int total;
    /**
     * id : 245
     * user_id : 17
     * head_img : http://192.168.7.100:8028/files/img/20180824/201808241527088731.jpg
     * nickname : 小强无限公司
     * title : 我
     * cover_img : http://192.168.7.100:8028/files/img/20180607/201806071345127140.png
     * url : http://192.168.7.100:8028/files/file/20180607/201806071345179525.mp4
     * article_type : 2
     * price : 0
     * praise_count : -43
     * collection_count : 0
     * browse_count : 161
     * comment_count : 16
     * transmit_count : 0
     * create_datetime : 2018-06-07 13:45:18
     * share_title : 我
     * share_url : http://192.168.7.100/page/article-details.html?id=245
     * share_description :
     * nesting_url : http://192.168.7.100:8029/Page/app/article-details.html?id=245
     * is_collection : false
     * is_activity : 0
     * activity_id : 0
     * activity_production_type : 0
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


    public static class RowsBean implements MultiItemEntity {
        private int id;
        private int user_id;
        private String head_img;
        private String nickname;
        private String title;
        private String cover_img;
        private String url;
        private int article_type;
        private double price;
        private int praise_count;
        private int collection_count;
        private int browse_count;
        private int comment_count;
        private int transmit_count;
        private String create_datetime;
        private String share_title;
        private String share_url;
        private String share_description;
        private String nesting_url;
        private boolean is_collection;
        private int is_activity;
        private int activity_id;
        private int activity_production_type;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getUser_id() {
            return user_id;
        }

        public void setUser_id(int user_id) {
            this.user_id = user_id;
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

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getCover_img() {
            return cover_img;
        }

        public void setCover_img(String cover_img) {
            this.cover_img = cover_img;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public int getArticle_type() {
            return article_type;
        }

        public void setArticle_type(int article_type) {
            this.article_type = article_type;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public int getPraise_count() {
            return praise_count;
        }

        public void setPraise_count(int praise_count) {
            this.praise_count = praise_count;
        }

        public int getCollection_count() {
            return collection_count;
        }

        public void setCollection_count(int collection_count) {
            this.collection_count = collection_count;
        }

        public int getBrowse_count() {
            return browse_count;
        }

        public void setBrowse_count(int browse_count) {
            this.browse_count = browse_count;
        }

        public int getComment_count() {
            return comment_count;
        }

        public void setComment_count(int comment_count) {
            this.comment_count = comment_count;
        }

        public int getTransmit_count() {
            return transmit_count;
        }

        public void setTransmit_count(int transmit_count) {
            this.transmit_count = transmit_count;
        }

        public String getCreate_datetime() {
            return create_datetime;
        }

        public void setCreate_datetime(String create_datetime) {
            this.create_datetime = create_datetime;
        }

        public String getShare_title() {
            return share_title;
        }

        public void setShare_title(String share_title) {
            this.share_title = share_title;
        }

        public String getShare_url() {
            return share_url;
        }

        public void setShare_url(String share_url) {
            this.share_url = share_url;
        }

        public String getShare_description() {
            return share_description;
        }

        public void setShare_description(String share_description) {
            this.share_description = share_description;
        }

        public String getNesting_url() {
            return nesting_url;
        }

        public void setNesting_url(String nesting_url) {
            this.nesting_url = nesting_url;
        }

        public boolean isIs_collection() {
            return is_collection;
        }

        public void setIs_collection(boolean is_collection) {
            this.is_collection = is_collection;
        }

        public int getIs_activity() {
            return is_activity;
        }

        public void setIs_activity(int is_activity) {
            this.is_activity = is_activity;
        }

        public int getActivity_id() {
            return activity_id;
        }

        public void setActivity_id(int activity_id) {
            this.activity_id = activity_id;
        }

        public int getActivity_production_type() {
            return activity_production_type;
        }

        public void setActivity_production_type(int activity_production_type) {
            this.activity_production_type = activity_production_type;
        }

        @Override
        public String toString() {
            return "RowsBean{" + "id=" + id + ", user_id=" + user_id + ", head_img='" + head_img + '\'' + ", nickname='" + nickname + '\'' + ", title='" + title + '\'' + ", cover_img='" + cover_img + '\'' + ", url='" + url + '\'' + ", article_type=" + article_type + ", price=" + price + ", praise_count=" + praise_count + ", collection_count=" + collection_count + ", browse_count=" + browse_count + ", comment_count=" + comment_count + ", transmit_count=" + transmit_count + ", create_datetime='" + create_datetime + '\'' + ", share_title='" + share_title + '\'' + ", share_url='" + share_url + '\'' + ", share_description='" + share_description + '\'' + ", nesting_url='" + nesting_url + '\'' + ", is_collection=" + is_collection + ", is_activity=" + is_activity + ", activity_id=" + activity_id + ", activity_production_type=" + activity_production_type + '}';
        }

        @Override
        public int getItemType() {
            return article_type;
        }
    }

    @Override
    public String toString() {
        return "RecomentEntity{" + "total=" + total + ", rows=" + rows + '}';
    }
}
