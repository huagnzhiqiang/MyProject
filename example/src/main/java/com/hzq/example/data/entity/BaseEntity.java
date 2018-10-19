package com.hzq.example.data.entity;

/**
 * @author 小强
 * @time 2018/10/19  16:38
 * @desc
 */
public class BaseEntity {


    /**
     * code : 200
     * data : null
     * message : 关注成功
     * remark :
     */

    private String code;
    private Object data;
    private String message;
    private String remark;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
