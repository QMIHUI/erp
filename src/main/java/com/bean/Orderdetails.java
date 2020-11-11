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
    private Integer proprice;

    /**
     * 商品总金额
     */
    private Integer prototal;

    /**
     * 订单编号，外键
     */
    private String orderId;

    private Product product;

    private static final long serialVersionUID = 1L;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

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

    public Integer getProprice() {
        return proprice;
    }

    public void setProprice(Integer proprice) {
        this.proprice = proprice;
    }

    public Integer getPrototal() {
        return prototal;
    }

    public void setPrototal(Integer prototal) {
        this.prototal = prototal;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
}