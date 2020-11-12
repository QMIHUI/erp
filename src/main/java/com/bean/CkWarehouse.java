package com.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * ck_warehouse
 * @author 
 */
public class CkWarehouse implements Serializable {
    /**
     * 出库id（自增）
     */
    private Integer id;

    /**
     * 订单编号
     */
    private Orders order;

    /**
     * 仓库管理表的id
     */
    private Warehouse warehouse;

    /**
     * 出库时间
     */
    private Date ckDate;

    /**
     * 出库人（登录人的id）
     */
    private Users outUser;

    /**
     * 状态(1未发货,2已发货,3已回款,4取消订单,5已退货)
     */
    private Integer state;

    private static final long serialVersionUID = 1L;

    public CkWarehouse() {
    }

    public CkWarehouse(Integer id, Orders order, Warehouse warehouse, Date ckDate, Users outUser, Integer state) {
        this.id = id;
        this.order = order;
        this.warehouse = warehouse;
        this.ckDate = ckDate;
        this.outUser = outUser;
        this.state = state;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Orders getOrder() {
        return order;
    }

    public void setOrder(Orders order) {
        this.order = order;
    }

    public Warehouse getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    public Date getCkDate() {
        return ckDate;
    }

    public void setCkDate(Date ckDate) {
        this.ckDate = ckDate;
    }

    public Users getOutUser() {
        return outUser;
    }

    public void setOutUser(Users outUser) {
        this.outUser = outUser;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}