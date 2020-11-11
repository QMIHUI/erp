package com.bean;

import java.io.Serializable;

/**
 * order
 * @author
 */
public class Orders implements Serializable {
    private String orderId;

    private Integer customid;

    private String ordertime;

    private Double ordermoney;

    private Users operatorid;

    private String dstatus;

    private Users checkid;

    private String chectime;

    private String opinion;

    private Integer warehouseid;

    private Integer ddState;

    private Custom custom;
    private Warehouse warehouse;

    private static final long serialVersionUID = 1L;

    public Custom getCustom() {
        return custom;
    }

    public void setCustom(Custom custom) {
        this.custom = custom;
    }



    public Warehouse getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Users getCheckid() {
        return checkid;
    }

    public Integer getCustomid() {
        return customid;
    }

    public void setCustomid(Integer customid) {
        this.customid = customid;
    }

    public String getOrdertime() {
        return ordertime;
    }

    public void setOrdertime(String ordertime) {
        this.ordertime = ordertime;
    }

    public Double getOrdermoney() {
        return ordermoney;
    }

    public void setOrdermoney(Double ordermoney) {
        this.ordermoney = ordermoney;
    }

    public Users getOperatorid() {
        return operatorid;
    }

    public void setOperatorid(Users operatorid) {
        this.operatorid = operatorid;
    }

    public void setCheckid(Users checkid) {
        this.checkid = checkid;
    }

    public String getDstatus() {
        return dstatus;
    }

    public void setDstatus(String dstatus) {
        this.dstatus = dstatus;
    }


    public String getChectime() {
        return chectime;
    }

    public void setChectime(String chectime) {
        this.chectime = chectime;
    }

    public String getOpinion() {
        return opinion;
    }

    public void setOpinion(String opinion) {
        this.opinion = opinion;
    }

    public Integer getWarehouseid() {
        return warehouseid;
    }

    public void setWarehouseid(Integer warehouseid) {
        this.warehouseid = warehouseid;
    }

    public Integer getDdState() {
        return ddState;
    }

    public void setDdState(Integer ddState) {
        this.ddState = ddState;
    }
}