package com.hzq.example.data.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * @author 小强
 * @time 2018/7/13  15:18
 * @desc 我的作品实体类
 */
public class MyProductEntity implements Parcelable {

    /**
     * total : 62
     * rows : [{"id":1034,"user_id":17,"buy_user_id":0,"parent_product_id":0,"product_type":1,"product_tags":"","title":"新建未命名H5","cover_img":"","description":"","url":"https://www.i-top.cn/pages/h5/view.html?appId=1034","price":0,"check_status":2,"show":false,"praise_count":0,"collection_count":0,"comment_count":0,"browse_count":0,"sale_count":0,"commend":false,"importance":0,"share_img":"","share_title":"新建未命名H5","share_description":"新建未命名H5","share_url":"https://www.i-top.cn/pages/h5/view.html?appId=1034","create_datetime":"2018-10-29 14:36:15","update_datetime":"2018-10-29 14:36:38","publish_datetime":"2018-10-29 14:36:15","commend_datetime":"1900-01-01 00:00:00","logo_off":0,"product_json":"http://192.168.7.100:8028/files/product/20181029/201810291436157852.json","is_activity":0,"activity_id":0,"activity_production_type":0,"font_area":0,"video_area":0,"image_area":0,"animation_area":0,"other_area":0,"original_price":0,"operational_content":"{\"photo\":9}"},{"id":1025,"user_id":17,"buy_user_id":0,"parent_product_id":0,"product_type":1,"product_tags":"67,96,110,122","title":"我是作品-16-04","cover_img":"http://192.168.7.100:8028/files/img/20180604/201806041714268096.jpg","description":"策划：中秋时节，人们在赏月，而嫦娥却在月桂树下赏地球。当地球越来再次靠近就融合成了月饼，切开月饼即可收获祝福语，适用于中秋节营销； 视觉：卡通和写实相结合，动画衔接自然流畅； 应用：最后一页logo/可一键替换成任意品牌图片，实现中秋节日活动的引流。","url":"http://192.168.7.100:8029/html5/20180108shadiao%E6%B2%99%E9%9B%95/index.html","price":6,"check_status":1,"show":false,"praise_count":0,"collection_count":0,"comment_count":0,"browse_count":0,"sale_count":0,"commend":false,"importance":0,"share_img":"http://192.168.7.100:8028/files/img/20180209/201802091506373867.png","share_title":"邀请函-科技大会","share_description":"翻转地球","share_url":"http://192.168.7.100:8029/html5/20180108shadiao%E6%B2%99%E9%9B%95/index.html","create_datetime":"2018-10-25 15:50:30","update_datetime":"2018-10-25 15:50:30","publish_datetime":"2018-06-02 10:55:10","commend_datetime":"2018-06-04 17:16:16","logo_off":0,"product_json":"http://localhost:19582/files/product/20180602/201806021055102003.json","is_activity":0,"activity_id":0,"activity_production_type":0,"font_area":0,"video_area":0,"image_area":0,"animation_area":0,"other_area":0,"original_price":0,"operational_content":"{\"photo\":5, \"sound\":2, \"button\":1, \"shape\":3, \"spirit\":3, \"text\":5, \"music\":5, \"video\":1}"},{"id":996,"user_id":17,"buy_user_id":0,"parent_product_id":0,"product_type":1,"product_tags":"","title":"翻转地球","cover_img":"http://192.168.7.100:8028/files/img/20180209/201802091506517419.png","description":"策划：中秋时节，人们在赏月，而嫦娥却在月桂树下赏地球。当地球越来再次靠近就融合成了月饼，切开月饼即可收获祝福语，适用于中秋节营销； 视觉：卡通和写实相结合，动画衔接自然流畅； 应用：最后一页logo/可一键替换成任意品牌图片，实现中秋节日活动的引流。","url":"http://192.168.7.100:8029/html5/20171223earth%E5%9C%B0%E7%90%83/index.html","price":0,"check_status":2,"show":false,"praise_count":0,"collection_count":0,"comment_count":0,"browse_count":0,"sale_count":0,"commend":false,"importance":0,"share_img":"http://192.168.7.100:8028/files/img/20180209/201802091506547180.png","share_title":"翻转地球","share_description":"翻转地球","share_url":"https://192.168.7.100:8029/html5/20171223earth%E5%9C%B0%E7%90%83/index.html","create_datetime":"2018-10-18 17:10:09","update_datetime":"2018-10-18 17:10:09","publish_datetime":"2018-02-07 14:09:29","commend_datetime":"2018-02-08 02:09:29","logo_off":0,"product_json":"http://192.168.7.100:8028/files/product/20181018/201810181710092464.json","is_activity":0,"activity_id":0,"activity_production_type":0,"font_area":0,"video_area":0,"image_area":0,"animation_area":0,"other_area":0,"original_price":0,"operational_content":"{\"photo\":5, \"sound\":2, \"button\":1, \"shape\":3, \"spirit\":3, \"text\":5, \"music\":5, \"video\":1}"}]
     */

    private int total;
    /**
     * id : 1034
     * user_id : 17
     * buy_user_id : 0
     * parent_product_id : 0
     * product_type : 1
     * product_tags :
     * title : 新建未命名H5
     * cover_img :
     * description :
     * url : https://www.i-top.cn/pages/h5/view.html?appId=1034
     * price : 0
     * check_status : 2
     * show : false
     * praise_count : 0
     * collection_count : 0
     * comment_count : 0
     * browse_count : 0
     * sale_count : 0
     * commend : false
     * importance : 0
     * share_img :
     * share_title : 新建未命名H5
     * share_description : 新建未命名H5
     * share_url : https://www.i-top.cn/pages/h5/view.html?appId=1034
     * create_datetime : 2018-10-29 14:36:15
     * update_datetime : 2018-10-29 14:36:38
     * publish_datetime : 2018-10-29 14:36:15
     * commend_datetime : 1900-01-01 00:00:00
     * logo_off : 0
     * product_json : http://192.168.7.100:8028/files/product/20181029/201810291436157852.json
     * is_activity : 0
     * activity_id : 0
     * activity_production_type : 0
     * font_area : 0
     * video_area : 0
     * image_area : 0
     * animation_area : 0
     * other_area : 0
     * original_price : 0
     * operational_content : {"photo":9}
     */

    private List<RowsBean> rows;

    protected MyProductEntity(Parcel in) {
        total = in.readInt();
        rows = in.createTypedArrayList(RowsBean.CREATOR);
    }

    public static final Creator<MyProductEntity> CREATOR = new Creator<MyProductEntity>() {
        @Override
        public MyProductEntity createFromParcel(Parcel in) {
            return new MyProductEntity(in);
        }

        @Override
        public MyProductEntity[] newArray(int size) {
            return new MyProductEntity[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }


    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(total);
        dest.writeTypedList(rows);
    }

    public static class RowsBean implements Parcelable{
        private int id;
        private int user_id;
        private int buy_user_id;
        private int parent_product_id;
        private int product_type;
        private String product_tags;
        private String title;
        private String cover_img;
        private String description;
        private String url;
        private double price;
        private int check_status;
        private boolean show;
        private int praise_count;
        private int collection_count;
        private int comment_count;
        private int browse_count;
        private int sale_count;
        private boolean commend;
        private int importance;
        private String share_img;
        private String share_title;
        private String share_description;
        private String share_url;
        private String create_datetime;
        private String update_datetime;
        private String publish_datetime;
        private String commend_datetime;
        private int logo_off;
        private String product_json;
        private int is_activity;
        private int activity_id;
        private int activity_production_type;
        private int font_area;
        private int video_area;
        private int image_area;
        private int animation_area;
        private int other_area;
        private int original_price;
        private String operational_content;

        protected RowsBean(Parcel in) {
            id = in.readInt();
            user_id = in.readInt();
            buy_user_id = in.readInt();
            parent_product_id = in.readInt();
            product_type = in.readInt();
            product_tags = in.readString();
            title = in.readString();
            cover_img = in.readString();
            description = in.readString();
            url = in.readString();
            price = in.readDouble();
            check_status = in.readInt();
            show = in.readByte() != 0;
            praise_count = in.readInt();
            collection_count = in.readInt();
            comment_count = in.readInt();
            browse_count = in.readInt();
            sale_count = in.readInt();
            commend = in.readByte() != 0;
            importance = in.readInt();
            share_img = in.readString();
            share_title = in.readString();
            share_description = in.readString();
            share_url = in.readString();
            create_datetime = in.readString();
            update_datetime = in.readString();
            publish_datetime = in.readString();
            commend_datetime = in.readString();
            logo_off = in.readInt();
            product_json = in.readString();
            is_activity = in.readInt();
            activity_id = in.readInt();
            activity_production_type = in.readInt();
            font_area = in.readInt();
            video_area = in.readInt();
            image_area = in.readInt();
            animation_area = in.readInt();
            other_area = in.readInt();
            original_price = in.readInt();
            operational_content = in.readString();
        }

        public static final Creator<RowsBean> CREATOR = new Creator<RowsBean>() {
            @Override
            public RowsBean createFromParcel(Parcel in) {
                return new RowsBean(in);
            }

            @Override
            public RowsBean[] newArray(int size) {
                return new RowsBean[size];
            }
        };

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

        public int getBuy_user_id() {
            return buy_user_id;
        }

        public void setBuy_user_id(int buy_user_id) {
            this.buy_user_id = buy_user_id;
        }

        public int getParent_product_id() {
            return parent_product_id;
        }

        public void setParent_product_id(int parent_product_id) {
            this.parent_product_id = parent_product_id;
        }

        public int getProduct_type() {
            return product_type;
        }

        public void setProduct_type(int product_type) {
            this.product_type = product_type;
        }

        public String getProduct_tags() {
            return product_tags;
        }

        public void setProduct_tags(String product_tags) {
            this.product_tags = product_tags;
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

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public int getCheck_status() {
            return check_status;
        }

        public void setCheck_status(int check_status) {
            this.check_status = check_status;
        }

        public boolean isShow() {
            return show;
        }

        public void setShow(boolean show) {
            this.show = show;
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

        public int getComment_count() {
            return comment_count;
        }

        public void setComment_count(int comment_count) {
            this.comment_count = comment_count;
        }

        public int getBrowse_count() {
            return browse_count;
        }

        public void setBrowse_count(int browse_count) {
            this.browse_count = browse_count;
        }

        public int getSale_count() {
            return sale_count;
        }

        public void setSale_count(int sale_count) {
            this.sale_count = sale_count;
        }

        public boolean isCommend() {
            return commend;
        }

        public void setCommend(boolean commend) {
            this.commend = commend;
        }

        public int getImportance() {
            return importance;
        }

        public void setImportance(int importance) {
            this.importance = importance;
        }

        public String getShare_img() {
            return share_img;
        }

        public void setShare_img(String share_img) {
            this.share_img = share_img;
        }

        public String getShare_title() {
            return share_title;
        }

        public void setShare_title(String share_title) {
            this.share_title = share_title;
        }

        public String getShare_description() {
            return share_description;
        }

        public void setShare_description(String share_description) {
            this.share_description = share_description;
        }

        public String getShare_url() {
            return share_url;
        }

        public void setShare_url(String share_url) {
            this.share_url = share_url;
        }

        public String getCreate_datetime() {
            return create_datetime;
        }

        public void setCreate_datetime(String create_datetime) {
            this.create_datetime = create_datetime;
        }

        public String getUpdate_datetime() {
            return update_datetime;
        }

        public void setUpdate_datetime(String update_datetime) {
            this.update_datetime = update_datetime;
        }

        public String getPublish_datetime() {
            return publish_datetime;
        }

        public void setPublish_datetime(String publish_datetime) {
            this.publish_datetime = publish_datetime;
        }

        public String getCommend_datetime() {
            return commend_datetime;
        }

        public void setCommend_datetime(String commend_datetime) {
            this.commend_datetime = commend_datetime;
        }

        public int getLogo_off() {
            return logo_off;
        }

        public void setLogo_off(int logo_off) {
            this.logo_off = logo_off;
        }

        public String getProduct_json() {
            return product_json;
        }

        public void setProduct_json(String product_json) {
            this.product_json = product_json;
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

        public int getFont_area() {
            return font_area;
        }

        public void setFont_area(int font_area) {
            this.font_area = font_area;
        }

        public int getVideo_area() {
            return video_area;
        }

        public void setVideo_area(int video_area) {
            this.video_area = video_area;
        }

        public int getImage_area() {
            return image_area;
        }

        public void setImage_area(int image_area) {
            this.image_area = image_area;
        }

        public int getAnimation_area() {
            return animation_area;
        }

        public void setAnimation_area(int animation_area) {
            this.animation_area = animation_area;
        }

        public int getOther_area() {
            return other_area;
        }

        public void setOther_area(int other_area) {
            this.other_area = other_area;
        }

        public int getOriginal_price() {
            return original_price;
        }

        public void setOriginal_price(int original_price) {
            this.original_price = original_price;
        }

        public String getOperational_content() {
            return operational_content;
        }

        public void setOperational_content(String operational_content) {
            this.operational_content = operational_content;
        }


        @Override
        public int describeContents() {
            return 0;
        }


        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(id);
            dest.writeInt(user_id);
            dest.writeInt(buy_user_id);
            dest.writeInt(parent_product_id);
            dest.writeInt(product_type);
            dest.writeString(product_tags);
            dest.writeString(title);
            dest.writeString(cover_img);
            dest.writeString(description);
            dest.writeString(url);
            dest.writeDouble(price);
            dest.writeInt(check_status);
            dest.writeByte((byte) (show ? 1 : 0));
            dest.writeInt(praise_count);
            dest.writeInt(collection_count);
            dest.writeInt(comment_count);
            dest.writeInt(browse_count);
            dest.writeInt(sale_count);
            dest.writeByte((byte) (commend ? 1 : 0));
            dest.writeInt(importance);
            dest.writeString(share_img);
            dest.writeString(share_title);
            dest.writeString(share_description);
            dest.writeString(share_url);
            dest.writeString(create_datetime);
            dest.writeString(update_datetime);
            dest.writeString(publish_datetime);
            dest.writeString(commend_datetime);
            dest.writeInt(logo_off);
            dest.writeString(product_json);
            dest.writeInt(is_activity);
            dest.writeInt(activity_id);
            dest.writeInt(activity_production_type);
            dest.writeInt(font_area);
            dest.writeInt(video_area);
            dest.writeInt(image_area);
            dest.writeInt(animation_area);
            dest.writeInt(other_area);
            dest.writeInt(original_price);
            dest.writeString(operational_content);
        }
    }
}
