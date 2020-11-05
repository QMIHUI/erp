package com.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * c_type
 * @author 
 */
public class Type implements Serializable {
    /**
     * 商品编号
     */
    private Integer typeId;

    /**
     * 商品类型名称(手机,电脑)
     */
    private String typeName;

    /**
     * 商品状态，1可用 2不可用
     */
    private Integer typeStatus;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 创建人
     */
    private Users creater;

    /**
     * 品牌id、外键
     */
    private Brand brand;

    private static final long serialVersionUID = 1L;

    public Type() {
    }

    public Type(Integer typeId, String typeName, Integer typeStatus, Date createTime, Users creater, Brand brand) {
        this.typeId = typeId;
        this.typeName = typeName;
        this.typeStatus = typeStatus;
        this.createTime = createTime;
        this.creater = creater;
        this.brand = brand;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public Integer getTypeStatus() {
        return typeStatus;
    }

    public void setTypeStatus(Integer typeStatus) {
        this.typeStatus = typeStatus;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Users getCreater() {
        return creater;
    }

    public void setCreater(Users creater) {
        this.creater = creater;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }
}