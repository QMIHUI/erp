package com.bean;

import java.io.Serializable;

/**
 * kc_warehouse
 * @author
 */
public class KcWarehouse implements Serializable {
    /**
     * 主键
     */
    private Integer id;

    /**
     * 仓库id
     */
    private Integer warehouseId;
    private Warehouse warehouse;

    /**
     * 品牌id
     */
    private Integer brandId;
    private Brand brand;
    private Integer brandid;

    /**
     * 类型id
     */
    private Integer typeId;
    private  Type type;
    private Integer typeid;
    /**
     * 厂商id
     */
    private Integer factoryId;
    private Firm firm;
    private Integer firmid;

    /**
     * 商品id
     */
    private Integer productId;
    private Product product;
    private Integer productid;
    /**
     * 库存数量
     */
    private Integer repertory;

    /**
     * 订单详情编号
     */
    private String  orderdetatlsId;
    private int numck;
    private String indent;

    public KcWarehouse(){}

    public KcWarehouse(Integer repertory,Integer productId) {
        this.repertory = repertory;
        this.productId = productId;
    }

    public KcWarehouse(int numck, String indent) {
        this.numck = numck;
        this.indent = indent;
    }
    public KcWarehouse(Integer repertory,Integer brandId, Integer typeId, Integer productId) {
        this.brandId = brandId;
        this.typeId = typeId;
        this.repertory = repertory;
        this.productId=productId;
    }

    public KcWarehouse(Integer repertory,Integer brandId, Integer typeId, Integer factoryId, Integer productId) {
        this.brandId = brandId;
        this.typeId = typeId;
        this.factoryId = factoryId;
        this.repertory = repertory;
        this.productId=productId;
    }

    public KcWarehouse(Integer warehouseId, Integer brandid, Integer typeid, Integer firmid, Integer productid, Integer repertory) {
        this.warehouseId = warehouseId;
        this.brandid = brandid;
        this.typeid = typeid;
        this.firmid = firmid;
        this.productid = productid;
        this.repertory = repertory;
    }

    public int getNumck() {
        return numck;
    }

    public void setNumck(int numck) {
        this.numck = numck;
    }

    public String getIndent() {
        return indent;
    }

    public void setIndent(String indent) {
        this.indent = indent;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(Integer warehouseId) {
        this.warehouseId = warehouseId;
    }

    public Warehouse getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    public Integer getBrandId() {
        return brandId;
    }

    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Integer getFactoryId() {
        return factoryId;
    }

    public void setFactoryId(Integer factoryId) {
        this.factoryId = factoryId;
    }

    public Firm getFirm() {
        return firm;
    }

    public void setFirm(Firm firm) {
        this.firm = firm;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getRepertory() {
        return repertory;
    }

    public void setRepertory(Integer repertory) {
        this.repertory = repertory;
    }

    public String getOrderdetatlsId() {
        return orderdetatlsId;
    }

    public void setOrderdetatlsId(String orderdetatlsId) {
        this.orderdetatlsId = orderdetatlsId;
    }

    public Integer getBrandid() {
        return brandid;
    }

    public void setBrandid(Integer brandid) {
        this.brandid = brandid;
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

    public Integer getProductid() {
        return productid;
    }

    public void setProductid(Integer productid) {
        this.productid = productid;
    }

    private static final long serialVersionUID = 1L;
}