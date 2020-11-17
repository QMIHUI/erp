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
    private String indent;
    private Orders order;

    /**
     * 仓库管理表的id
     */
    private Integer warehouseId;

    /**
     * 出库时间
     */
    private String cDate;
    private Date ckDate;

    /**
     * 出库人（登录人的id）
     */
    private Integer userId;
    private Users outUser;

    /**
     * 状态(1未发货,2已发货,3已回款,4取消订单,5已退货)
     */
    private Integer state;

    private Orders orders;//多次出库对应一个订单
    private Warehouse warehouse;//多次出库订单对应一个仓库
    private Users users;//多次出库订单对应一个用户
    private Custom custom;//多个订单对应一个客户
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

    public CkWarehouse(Integer id, Integer state) {
        this.id = id;
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

    public String getIndent() {
        return indent;
    }

    public void setIndent(String indent) {
        this.indent = indent;
    }

    public Integer getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(Integer warehouseId) {
        this.warehouseId = warehouseId;
    }

    public String getcDate() {
        return cDate;
    }

    public void setcDate(String cDate) {
        this.cDate = cDate;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Orders getOrders() {
        return orders;
    }

    public void setOrders(Orders orders) {
        this.orders = orders;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public Custom getCustom() {
        return custom;
    }

    public void setCustom(Custom custom) {
        this.custom = custom;
    }
}