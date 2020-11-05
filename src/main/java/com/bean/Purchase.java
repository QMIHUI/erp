package com.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * c_purchase
 * @author 
 */
public class Purchase implements Serializable {
    private String purchaseId;

    private Users buyer;

    private Firm checker;

    private Date purchaseTime;

    private Date checkTime;

    private String checkOpinion;

    private Warehouse warehuose;

    private Integer cgState;

    private Double totalMoney;

    private Integer checkStatus;

    private static final long serialVersionUID = 1L;

    public Purchase() {
    }

    public Purchase(String purchaseId, Users buyer, Firm checker, Date purchaseTime, Date checkTime, String checkOpinion, Warehouse warehuose, Integer cgState, Double totalMoney, Integer checkStatus) {
        this.purchaseId = purchaseId;
        this.buyer = buyer;
        this.checker = checker;
        this.purchaseTime = purchaseTime;
        this.checkTime = checkTime;
        this.checkOpinion = checkOpinion;
        this.warehuose = warehuose;
        this.cgState = cgState;
        this.totalMoney = totalMoney;
        this.checkStatus = checkStatus;
    }

    public String getPurchaseId() {
        return purchaseId;
    }

    public void setPurchaseId(String purchaseId) {
        this.purchaseId = purchaseId;
    }

    public Users getBuyer() {
        return buyer;
    }

    public void setBuyer(Users buyer) {
        this.buyer = buyer;
    }

    public Firm getChecker() {
        return checker;
    }

    public void setChecker(Firm checker) {
        this.checker = checker;
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

    public Warehouse getWarehuose() {
        return warehuose;
    }

    public void setWarehuose(Warehouse warehuose) {
        this.warehuose = warehuose;
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