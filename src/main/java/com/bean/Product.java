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

    /**
     * 商品类型id，外键
     */
    private Type type;

    /**
     * 商品厂商id，外键
     */
    private Firm firm;

    private static final long serialVersionUID = 1L;


}