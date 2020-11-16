package com.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * c_product
 * @author 
 */
public class Product implements Serializable {
    /**
     * 商品id，主键，自动增长
     */
    private Integer productId;

    /**
     * 商品型号
     */
    private String productModel;

    /**
     * 商品单价（进价，售价不一样）
     */
    private double productPrice;

    /**
     * 商品状态, 1可用 2不可用
     */
    private Integer productStatus;

    /**
     * 商品单位
     */
    private String productUnit;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 创建人，外键
     */
    private Users creater;
    private Integer creareid;
    private Integer typeid;
    private Integer firmid;

    /**
     * 商品类型id，外键
     */
    private Type type;

    /**
     * 商品厂商id，外键
     */
    private Firm firm;

    private static final long serialVersionUID = 1L;

    public Product() {
    }

    public Product(Integer productId, String productModel, double productPrice, Integer productStatus, String productUnit, Date createTime, Users creater, Type type, Firm firm) {
        this.productId = productId;
        this.productModel = productModel;
        this.productPrice = productPrice;
        this.productStatus = productStatus;
        this.productUnit = productUnit;
        this.createTime = createTime;
        this.creater = creater;
        this.type = type;
        this.firm = firm;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getProductModel() {
        return productModel;
    }

    public void setProductModel(String productModel) {
        this.productModel = productModel;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public Integer getProductStatus() {
        return productStatus;
    }

    public void setProductStatus(Integer productStatus) {
        this.productStatus = productStatus;
    }

    public String getProductUnit() {
        return productUnit;
    }

    public void setProductUnit(String productUnit) {
        this.productUnit = productUnit;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Users getCreater() {
        return creater;
    }

    public void setCreater(Users creater) {
        this.creater = creater;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Firm getFirm() {
        return firm;
    }

    public void setFirm(Firm firm) {
        this.firm = firm;
    }

    public Integer getCreareid() {
        return creareid;
    }

    public void setCreareid(Integer creareid) {
        this.creareid = creareid;
    }

    public Integer getTypeid() {
        return typeid;
    }

    public void setTypeid(Integer typeid) {
        this.typeid = typeid;
    }

    public Integer getFirmid() {
        return firmid;
    }

    public void setFirmid(Integer firmid) {
        this.firmid = firmid;
    }


}