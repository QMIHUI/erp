package com.bean;

import java.io.Serializable;

/**
 * province
 * @author 
 */
public class Province implements Serializable {
    /**
     * 省份id
     */
    private Integer id;

    /**
     * 省份名称
     */
    private String pName;

    public Province() {
    }

    public Province(Integer id, String pName) {
        this.id = id;
        this.pName = pName;
    }

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    @Override
    public String toString() {
        return "Province{" +
                "id=" + id +
                ", pName='" + pName + '\'' +
                '}';
    }
}