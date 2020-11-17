package com.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * c_details
 * @author 
 */
public class Details implements Serializable {
    /**
     * 详情编号，主键，自动增长
     */
    private Integer detailsId;

    /**
     * 采购单编号，外键
     */
    private String purchaseId;

    /**
     * 采购商品的数量
     */
    private Integer count;

    /**
     * 商品id，外键
     */
    private Product product;//商品型号
    private Type type;//商品类型
    private Brand brand;//商品品牌
    private Firm firm;//商品厂商
    private Integer priductid;

    /**
     * 进价
     */
    private double purchasePrice;

    /**
     * 总金额
     */
    private double totalMoney;

    private static final long serialVersionUID = 1L;

    public Details() {
    }

    public Details(Integer detailsId, String purchaseId, Integer count, Product product, double purchasePrice, double totalMoney) {
        this.detailsId = detailsId;
        this.purchaseId = purchaseId;
        this.count = count;
        this.product = product;
        this.purchasePrice = purchasePrice;
        this.totalMoney = totalMoney;
    }

    public Integer getDetailsId() {
        return detailsId;
    }

    public void setDetailsId(Integer detailsId) {
        this.detailsId = detailsId;
    }

    public String getPurchaseId() {
        return purchaseId;
    }

    public void setPurchaseId(String purchaseId) {
        this.purchaseId = purchaseId;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public double getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public double getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(double totalMoney) {
        this.totalMoney = totalMoney;
    }

    public Integer getPriductid() {
        return priductid;
    }

    public void setPriductid(Integer priductid) {
        this.priductid = priductid;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public Firm getFirm() {
        return firm;
    }

    public void setFirm(Firm firm) {
        this.firm = firm;
    }
}