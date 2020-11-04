package com.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * c_firm
 * @author 
 */
public class Firm implements Serializable {
    /**
     * 商品厂商id，主键
     */
    private Integer firmId;

    /**
     * 厂商名称
     */
    private String firmName;

    /**
     * 联系电话
     */
    private String firmTel;

    /**
     * 联系地址
     */
    private String firmAddress;

    /**
     * 厂商描述
     */
    private String firmContent;

    /**
     * 所属区域
     */
    private City city;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 创建人
     */
    private Users user;

    /**
     * 商品状态，1可用 2不可用
     */
    private Integer status;

    /**
     * 厂商负责人名字
     */
    private String firmFounder;

    private static final long serialVersionUID = 1L;

    public Firm() {
    }

    public Firm(Integer firmId, String firmName, String firmTel, String firmAddress, String firmContent, City city, Date createTime, Users user, Integer status, String firmFounder) {
        this.firmId = firmId;
        this.firmName = firmName;
        this.firmTel = firmTel;
        this.firmAddress = firmAddress;
        this.firmContent = firmContent;
        this.city = city;
        this.createTime = createTime;
        this.user = user;
        this.status = status;
        this.firmFounder = firmFounder;
    }

    public Integer getFirmId() {
        return firmId;
    }

    public void setFirmId(Integer firmId) {
        this.firmId = firmId;
    }

    public String getFirmName() {
        return firmName;
    }

    public void setFirmName(String firmName) {
        this.firmName = firmName;
    }

    public String getFirmTel() {
        return firmTel;
    }

    public void setFirmTel(String firmTel) {
        this.firmTel = firmTel;
    }

    public String getFirmAddress() {
        return firmAddress;
    }

    public void setFirmAddress(String firmAddress) {
        this.firmAddress = firmAddress;
    }

    public String getFirmContent() {
        return firmContent;
    }

    public void setFirmContent(String firmContent) {
        this.firmContent = firmContent;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getFirmFounder() {
        return firmFounder;
    }

    public void setFirmFounder(String firmFounder) {
        this.firmFounder = firmFounder;
    }
}