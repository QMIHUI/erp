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
    private Integer cId;

    /**
     * 省份
     */
    private Integer pId;

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

    public City(Integer cId, Province province, String cName, String cNumber) {
        this.cId = cId;
        this.province = province;
        this.cName = cName;
        this.cNumber = cNumber;
    }

    public Integer getpId() {
        return pId;
    }

    public void setpId(Integer pId) {
        this.pId = pId;
    }

    public Integer getcId() {
        return cId;
    }

    public void setcId(Integer cId) {
        this.cId = cId;
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
                "cId=" + cId +
                ", pId=" + pId +
                ", province=" + province +
                ", cName='" + cName + '\'' +
                ", cNumber='" + cNumber + '\'' +
                '}';
    }
}