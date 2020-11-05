package com.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * warehouse
 * @author 
 */
public class Warehouse implements Serializable {
    /**
     * 仓库id
     */
    private Integer id;

    /**
     * 仓库名称
     */
    private String cName;

    /**
     * 仓库地址
     */
    private String cAddress;

    /**
     * 仓库区域（省）id
     */
    private Province province;

    /**
     * 负责人id(财务部的员工)
     */
    private Users principal;

    /**
     * 联系方式
     */
    private String phone;

    /**
     * 描述
     */
    private String details;

    /**
     * 状态（1启用，0禁用）
     */
    private Integer state;

    /**
     * 创建人id
     */
    private Users creater;

    /**
     * 创建时间
     */
    private Date creationTime;

    /**
     * 市id
     */
    private City city;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    public String getcAddress() {
        return cAddress;
    }

    public void setcAddress(String cAddress) {
        this.cAddress = cAddress;
    }

    public Province getProvince() {
        return province;
    }

    public void setProvince(Province province) {
        this.province = province;
    }

    public Users getPrincipal() {
        return principal;
    }

    public void setPrincipal(Users principal) {
        this.principal = principal;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Users getCreater() {
        return creater;
    }

    public void setCreater(Users creater) {
        this.creater = creater;
    }

    public Date getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Warehouse() {
    }

    public Warehouse(Integer id, String cName, String cAddress, Province province, Users principal, String phone, String details, Integer state, Users creater, Date creationTime, City city) {
        this.id = id;
        this.cName = cName;
        this.cAddress = cAddress;
        this.province = province;
        this.principal = principal;
        this.phone = phone;
        this.details = details;
        this.state = state;
        this.creater = creater;
        this.creationTime = creationTime;
        this.city = city;
    }
}