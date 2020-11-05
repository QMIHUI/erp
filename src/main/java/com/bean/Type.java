package com.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * c_type
 * @author 
 */
public class Type implements Serializable {
    /**
     * 商品编号
     */
    private Integer typeId;

    /**
     * 商品类型名称(手机,电脑)
     */
    private String typeName;

    /**
     * 商品状态，1可用 2不可用
     */
    private Integer typeStatus;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 创建人
     */
    private Users creater;

    /**
     * 品牌id、外键
     */
    private Integer brandId;

    private static final long serialVersionUID = 1L;


}