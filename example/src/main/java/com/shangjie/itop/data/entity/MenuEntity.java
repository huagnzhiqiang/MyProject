package com.shangjie.itop.data.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author 小强
 * @time 2018/11/6  11:07
 * @desc 我的菜单实体类
 */
public class MenuEntity implements Parcelable {

    /**
     * id : 70
     * parent_id : 58
     * tag_type : 1
     * name : 产品介绍
     * icon :
     * index : 9
     */

    private int id;
    private int parent_id;
    private int tag_type;
    private String name;
    private String icon;
    private int index;
    private int iconId;

    public MenuEntity() {

    }
   protected MenuEntity(Parcel in) {
        id = in.readInt();
        parent_id = in.readInt();
        tag_type = in.readInt();
        name = in.readString();
        icon = in.readString();
        index = in.readInt();
        iconId = in.readInt();
    }

    public static final Creator<MenuEntity> CREATOR = new Creator<MenuEntity>() {
        @Override
        public MenuEntity createFromParcel(Parcel in) {
            return new MenuEntity(in);
        }

        @Override
        public MenuEntity[] newArray(int size) {
            return new MenuEntity[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getParent_id() {
        return parent_id;
    }

    public void setParent_id(int parent_id) {
        this.parent_id = parent_id;
    }

    public int getTag_type() {
        return tag_type;
    }

    public void setTag_type(int tag_type) {
        this.tag_type = tag_type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getIconId() {
        return iconId;
    }

    public void setIconId(int iconId) {
        this.iconId = iconId;
    }

    @Override
    public String toString() {
        return "MenuBean{" + "id=" + id + ", parent_id=" + parent_id + ", tag_type=" + tag_type + ", name='" + name + '\'' + ", icon='" + icon + '\'' + ", index=" + index + ", iconId=" + iconId + '}';
    }


    @Override
    public int describeContents() {
        return 0;
    }

    /**
     * Flatten this object in to a Parcel.
     *
     * @param dest  The Parcel in which the object should be written.
     * @param flags Additional flags about how the object should be written.
     *              May be 0 or {@link #PARCELABLE_WRITE_RETURN_VALUE}.
     */
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeInt(parent_id);
        dest.writeInt(tag_type);
        dest.writeString(name);
        dest.writeString(icon);
        dest.writeInt(index);
        dest.writeInt(iconId);
    }
}