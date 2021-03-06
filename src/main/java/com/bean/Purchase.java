package com.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * c_purchase
 * @author 
 */
public class Purchase implements Serializable {
    private String purchaseId;

    private Integer creatid;
    private Integer checkid;
    private Integer warehuoseid;

    private Users buyer;

    private Users checker;

    private Date purchaseTime;

    private Date checkTime;

    private String checkOpinion;

    private Warehouse warehuose;

    private Integer cgState;

    private Double totalMoney;
    //未审核1 审核中2  审核通过3 审核未通过4
    private Integer checkStatus;

    private List<Details> detailsList;

    private static final long serialVersionUID = 1L;

    public Purchase() {
    }

    public Purchase(String purchaseId, Integer warehuoseid) {
        this.purchaseId = purchaseId;
        this.warehuoseid = warehuoseid;
    }

    public Purchase(String purchaseId, Users buyer, Date purchaseTime, Integer checkStatus) {
        this.purchaseId = purchaseId;
        this.buyer = buyer;
        this.purchaseTime = purchaseTime;
        this.checkStatus = checkStatus;
    }

    public Purchase(String purchaseId, Users buyer, Users checker, Date purchaseTime, Date checkTime, String checkOpinion, Warehouse warehuose, Integer cgState, Double totalMoney, Integer checkStatus) {
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

    public Purchase(String purchaseId, Users buyer, Users checker, Date purchaseTime, Date checkTime, String checkOpinion, Warehouse warehuose, Integer cgState, Double totalMoney, Integer checkStatus, List<Details> detailsList) {
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
        this.detailsList = detailsList;
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

    public Users getChecker() {
        return checker;
    }

    public void setChecker(Users checker) {
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

    public List<Details> getDetailsList() {
        return detailsList;
    }

    public void setDetailsList(List<Details> detailsList) {
        this.detailsList = detailsList;
    }

    public Integer getCreatid() {
        return creatid;
    }

    public void setCreatid(Integer creatid) {
        this.creatid = creatid;
    }

    public Integer getCheckid() {
        return checkid;
    }

    public void setCheckid(Integer checkid) {
        this.checkid = checkid;
    }

    public Integer getWarehuoseid() {
        return warehuoseid;
    }

    public void setWarehuoseid(Integer warehuoseid) {
        this.warehuoseid = warehuoseid;
    }
}