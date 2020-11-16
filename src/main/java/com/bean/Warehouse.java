package com.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

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
    private String name;

    /**
     * 仓库地址
     */
    private String cAddress;

    /**
     * 仓库区域（省）id
     */
    private Integer provinceId;

    /**
     * 负责人id(财务部的员工)
     */
    private Integer usreId;
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
    private Integer usersId;
    private Users creater;

    /**
     * 创建时间
     */
    private String creationTime;

    /**
     * 市id
     */
    private Integer cityId;

    //多个一(仓库对管理员)
    private Users users;

    //多对一(仓库对省)
    private Province province;
    //多对一(仓库对市)
    private City city;
    //出、入库集合
    private List<CkWarehouse> ckWarehouseList;
    private List<RkWarehouse> rkWarehouseList;

    public Warehouse(){}
    public Warehouse(Integer id, Integer state) {
        this.id = id;
        this.state = state;
    }

    public Warehouse(Integer id, String name, String cAddress, Users principal, String phone, String details, Integer state, Users creater, String creationTime, Province province, City city) {
        this.id = id;
        this.name = name;
        this.cAddress = cAddress;
        this.principal = principal;
        this.phone = phone;
        this.details = details;
        this.state = state;
        this.creater = creater;
        this.creationTime = creationTime;
        this.province = province;
        this.city = city;
    }

    public Warehouse(Integer id, String name, String cAddress, Users principal, String phone, String details, Integer state, Users creater, String creationTime, Province province, City city, List<CkWarehouse> ckWarehouseList, List<RkWarehouse> rkWarehouseList) {
        this.id = id;
        this.name = name;
        this.cAddress = cAddress;
        this.principal = principal;
        this.phone = phone;
        this.details = details;
        this.state = state;
        this.creater = creater;
        this.creationTime = creationTime;
        this.province = province;
        this.city = city;
        this.ckWarehouseList = ckWarehouseList;
        this.rkWarehouseList = rkWarehouseList;
    }
    public Warehouse(String name, Integer provinceId, Integer cityId) {
        this.name = name;
        this.provinceId = provinceId;
        this.cityId = cityId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getcAddress() {
        return cAddress;
    }

    public void setcAddress(String cAddress) {
        this.cAddress = cAddress;
    }

    public Integer getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Integer provinceId) {
        this.provinceId = provinceId;
    }

    public Integer getUsreId() {
        return usreId;
    }

    public void setUsreId(Integer usreId) {
        this.usreId = usreId;
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

    public Integer getUsersId() {
        return usersId;
    }

    public void setUsersId(Integer usersId) {
        this.usersId = usersId;
    }

    public String getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(String  creationTime) {
        this.creationTime = creationTime;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public Province getProvince() {
        return province;
    }

    public void setProvince(Province province) {
        this.province = province;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Users getPrincipal() {
        return principal;
    }

    public void setPrincipal(Users principal) {
        this.principal = principal;
    }

    public Users getCreater() {
        return creater;
    }

    public void setCreater(Users creater) {
        this.creater = creater;
    }

    public List<CkWarehouse> getCkWarehouseList() {
        return ckWarehouseList;
    }

    public void setCkWarehouseList(List<CkWarehouse> ckWarehouseList) {
        this.ckWarehouseList = ckWarehouseList;
    }

    public List<RkWarehouse> getRkWarehouseList() {
        return rkWarehouseList;
    }

    public void setRkWarehouseList(List<RkWarehouse> rkWarehouseList) {
        this.rkWarehouseList = rkWarehouseList;
    }

    @Override
    public String toString() {
        return "Warehouse{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", cAddress='" + cAddress + '\'' +
                ", provinceId=" + provinceId +
                ", usreId=" + usreId +
                ", phone='" + phone + '\'' +
                ", details='" + details + '\'' +
                ", state=" + state +
                ", usersId=" + usersId +
                ", creationTime=" + creationTime +
                ", cityId=" + cityId +
                ", users=" + users +
                ", province=" + province +
                ", city=" + city +
                '}';
    }

    private static final long serialVersionUID = 1L;
}