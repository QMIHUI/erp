package com.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * c_purchase
 * @author 
 */
public class Purchase implements Serializable {
    private String purchaseId;

    private Integer creatId;

    private Integer checkId;

    private Date purchaseTime;

    private Date checkTime;

    private String checkOpinion;

    private Integer warehuoseid;

    private Integer cgState;

    private Double totalMoney;

    private Integer checkStatus;

    private static final long serialVersionUID = 1L;

    public Purchase() {
    }


    public String getPurchaseId() {
        return purchaseId;
    }

    public void setPurchaseId(String purchaseId) {
        this.purchaseId = purchaseId;
    }

    public Integer getCreatId() {
        return creatId;
    }

    public void setCreatId(Integer creatId) {
        this.creatId = creatId;
    }

    public Integer getCheckId() {
        return checkId;
    }

    public void setCheckId(Integer checkId) {
        this.checkId = checkId;
    }

    public Date getPurchaseTime() {
        return purchaseTime;
    }

    public void setPurchaseTime(Date purchaseTime) {
        this.purchaseTime = purchaseTime;
    }

    public Date getCheckTime() {
        return checkTime;
    }

    public void setCheckTime(Date checkTime) {
        this.checkTime = checkTime;
    }

    public String getCheckOpinion() {
        return checkOpinion;
    }

    public void setCheckOpinion(String checkOpinion) {
        this.checkOpinion = checkOpinion;
    }

    public Integer getWarehuoseid() {
        return warehuoseid;
    }

    public void setWarehuoseid(Integer warehuoseid) {
        this.warehuoseid = warehuoseid;
    }

    public Integer getCgState() {
        return cgState;
    }

    public void setCgState(Integer cgState) {
        this.cgState = cgState;
    }

    public Double getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(Double totalMoney) {
        this.totalMoney = totalMoney;
    }

    public Integer getCheckStatus() {
        return checkStatus;
    }

    public void setCheckStatus(Integer checkStatus) {
        this.checkStatus = checkStatus;
    }
}