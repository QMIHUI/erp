package com.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * c_brand
 * @author 
 */
public class Brand implements Serializable {
    /**
     * 品牌id
     */
    private Integer brandId;

    /**
     * 品牌名称
     */
    private String brandName;

    /**
     * 商品状态，1可用 2不可用
     */
    private Integer brandStatus;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 创建人
     */
    private Users creater;
    private Integer createid;

    private static final long serialVersionUID = 1L;

    public Brand() {
    }

    public Brand(Integer brandId, String brandName, Integer brandStatus, Date createTime, Users creater) {
        this.brandId = brandId;
        this.brandName = brandName;
        this.brandStatus = brandStatus;
        this.createTime = createTime;
        this.creater = creater;
    }

    public Integer getBrandId() {
        return brandId;
    }

    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public Integer getBrandStatus() {
        return brandStatus;
    }

    public void setBrandStatus(Integer brandStatus) {
        this.brandStatus = brandStatus;
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

    public Integer getCreateid() {
        return createid;
    }

    public void setCreateid(Integer createid) {
        this.createid = createid;
    }
}