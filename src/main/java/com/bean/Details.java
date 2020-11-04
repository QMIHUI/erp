package com.bean;

import java.io.Serializable;
import java.math.BigDecimal;

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
    private Integer productId;

    /**
     * 进价
     */
    private double purchasePrice;

    /**
     * 总金额
     */
    private double totalMoney;

    private static final long serialVersionUID = 1L;


}