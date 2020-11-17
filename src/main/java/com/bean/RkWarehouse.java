package com.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * rk_warehouse
 * @author 
 */
public class RkWarehouse implements Serializable {
    /**
     * 入库信息的id（自增）
     */
    private Integer id;

    /**
     * 采购单编号
     */
    private Purchase purchase;
    private String rkIndent;

    /**
     * 仓库管理表的id
     */
    private Warehouse warehouse;
    private Integer warehouseId;

    /**
     * 入库时间
     */
    private Date rkdate;
    private String rkDate;

    /**
     * 入库人（登录人的id）
     */
    private Users intoUser;
    private Integer userId;

    /**
     * 状态(1未入库,2已入库)
     */
    private Integer state;

    //多次入库采购单对应一个仓库
    private Users users;//多次入库采购单对应一个用户

    private static final long serialVersionUID = 1L;

    public RkWarehouse() {
    }

    public RkWarehouse(Integer id, Integer state) {
        this.id = id;
        this.state = state;
    }

    public RkWarehouse(Integer id, Purchase purchase, Warehouse warehouse, Date rkdate, Users intoUser, Integer state) {
        this.id = id;
        this.purchase = purchase;
        this.warehouse = warehouse;
        this.rkdate = rkdate;
        this.intoUser = intoUser;
        this.state = state;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Purchase getPurchase() {
        return purchase;
    }

    public void setPurchase(Purchase purchase) {
        this.purchase = purchase;
    }

    public Users getIntoUser() {
        return intoUser;
    }

    public void setIntoUser(Users intoUser) {
        this.intoUser = intoUser;
    }

    public Warehouse getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
    }





    public Users getintoUser() {
        return intoUser;
    }

    public void setintoUser(Users intoUser) {
        this.intoUser = intoUser;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getRkIndent() {
        return rkIndent;
    }

    public void setRkIndent(String rkIndent) {
        this.rkIndent = rkIndent;
    }

    public Integer getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(Integer warehouseId) {
        this.warehouseId = warehouseId;
    }


    public Date getRkdate() {
        return rkdate;
    }

    public void setRkdate(Date rkdate) {
        this.rkdate = rkdate;
    }

    public String getRkDate() {
        return rkDate;
    }

    public void setRkDate(String rkDate) {
        this.rkDate = rkDate;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }
}