package com.bean;

import java.io.Serializable;

/**
 * city
 * @author 
 */
public class City implements Serializable {
    /**
     * 市id
     */
    private Integer id;

    /**
     * 省份
     */
    private Province province;

    /**
     * 市名称
     */
    private String cName;

    /**
     * 市编码
     */
    private String cNumber;

    private static final long serialVersionUID = 1L;

    public City() {
    }

    public City(Integer id, Province province, String cName, String cNumber) {
        this.id = id;
        this.province = province;
        this.cName = cName;
        this.cNumber = cNumber;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Province getProvince() {
        return province;
    }

    public void setProvince(Province province) {
        this.province = province;
    }

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    public String getcNumber() {
        return cNumber;
    }

    public void setcNumber(String cNumber) {
        this.cNumber = cNumber;
    }

    @Override
    public String toString() {
        return "City{" +
                "id=" + id +
                ", province=" + province +
                ", cName='" + cName + '\'' +
                ", cNumber='" + cNumber + '\'' +
                '}';
    }
}