package com.bean;

import java.io.Serializable;
import java.util.List;

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
    private Integer operatorId;

    private int dstatus;

    private Users checkid;
    private int checkId;

    private String chectime;

    private String opinion;

    private Integer warehouseid;

    private Integer ddState;

    private Custom custom;
    private Warehouse warehouse;
    private List<Orderdetails> orderdetailsList;

    private static final long serialVersionUID = 1L;

    public Orders() {
    }

    public Orders(String orderId, Double ordermoney) {
        this.orderId = orderId;
        this.ordermoney = ordermoney;
    }

    public Orders(String orderId, Integer customid, String ordertime, Double ordermoney, Users operatorid, int dstatus, Users checkid, String chectime, String opinion, Integer warehouseid, Integer ddState, Custom custom, Warehouse warehouse, List<Orderdetails> orderdetailsList) {
        this.orderId = orderId;
        this.customid = customid;
        this.ordertime = ordertime;
        this.ordermoney = ordermoney;
        this.operatorid = operatorid;
        this.dstatus = dstatus;
        this.checkid = checkid;
        this.chectime = chectime;
        this.opinion = opinion;
        this.warehouseid = warehouseid;
        this.ddState = ddState;
        this.custom = custom;
        this.warehouse = warehouse;
        this.orderdetailsList = orderdetailsList;
    }

    public Integer getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(Integer operatorId) {
        this.operatorId = operatorId;
    }

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

    public int getDstatus() {
        return dstatus;
    }

    public void setDstatus(int dstatus) {
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

    public int getCheckId() {
        return checkId;
    }

    public void setCheckId(int checkId) {
        this.checkId = checkId;
    }

    public List<Orderdetails> getOrderdetailsList() {
        return orderdetailsList;
    }

    public void setOrderdetailsList(List<Orderdetails> orderdetailsList) {
        this.orderdetailsList = orderdetailsList;
    }
}