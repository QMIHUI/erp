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

    /**
     * 仓库管理表的id
     */
    private Warehouse warehouse;

    /**
     * 入库时间
     */
    private Date rkDate;

    /**
     * 出库人（登录人的id）
     */
    private Users intoUser;

    /**
     * 状态(1未入库,2已入库)
     */
    private Integer state;

    private static final long serialVersionUID = 1L;

    public RkWarehouse() {
    }

    public RkWarehouse(Integer id, Purchase purchase, Warehouse warehouse, Date rkDate, Users intoUser, Integer state) {
        this.id = id;
        this.purchase = purchase;
        this.warehouse = warehouse;
        this.rkDate = rkDate;
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

    public Date getRkDate() {
        return rkDate;
    }

    public void setRkDate(Date rkDate) {
        this.rkDate = rkDate;
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
}