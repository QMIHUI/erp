package com.bean;

import java.io.Serializable;

/**
 * orderdetails
 * @author 
 */
public class Orderdetails implements Serializable {
    /**
     * 详情编号，主键，自动增长
     */
    private Integer detailsId;

    /**
     * 商品的数量
     */
    private Integer purchaseNum;

    /**
     * 商品id，外键
     */
    private Integer productId;

    /**
     * 商品单价
     */
    private double proprice;

    /**
     * 商品总金额
     */
    private double prototal;

    /**
     * 订单编号，外键
     */
    private String orderId;
    private Product product;//商品型号
    private Type type;//商品类型
    private Brand brand;//商品品牌


    private static final long serialVersionUID = 1L;


    public Integer getDetailsId() {
        return detailsId;
    }

    public void setDetailsId(Integer detailsId) {
        this.detailsId = detailsId;
    }

    public Integer getPurchaseNum() {
        return purchaseNum;
    }

    public void setPurchaseNum(Integer purchaseNum) {
        this.purchaseNum = purchaseNum;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public double getProprice() {
        return proprice;
    }

    public void setProprice(double proprice) {
        this.proprice = proprice;
    }

    public double getPrototal() {
        return prototal;
    }

    public void setPrototal(double prototal) {
        this.prototal = prototal;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
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

    @Override
    public String toString() {
        return "Orderdetails{" +
                "detailsId=" + detailsId +
                ", purchaseNum=" + purchaseNum +
                ", productId=" + productId +
                ", proprice=" + proprice +
                ", prototal=" + prototal +
                ", orderId='" + orderId + '\'' +
                ", product=" + product +
                ", type=" + type +
                ", brand=" + brand +
                '}';
    }
}