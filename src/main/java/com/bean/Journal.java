package com.bean;

import java.io.Serializable;

/**
 * journal
 * @author 
 */
public class Journal implements Serializable {
    private Integer jId;

    private String jcontent;

    private String jdate;
    
    private String bname;

    private Integer uId;

    private static final long serialVersionUID = 1L;

    public Integer getjId() {
        return jId;
    }

    public void setjId(Integer jId) {
        this.jId = jId;
    }

    public String getJcontent() {
        return jcontent;
    }

    public void setJcontent(String jcontent) {
        this.jcontent = jcontent;
    }

    public String getJdate() {
        return jdate;
    }

    public void setJdate(String jdate) {
        this.jdate = jdate;
    }

    public String getBname() {
        return bname;
    }

    public void setBname(String bname) {
        this.bname = bname;
    }

    public Integer getuId() {
        return uId;
    }

    public void setuId(Integer uId) {
        this.uId = uId;
    }

    public Journal() {
    }

    public Journal(String jcontent, String jdate, String bname, Integer uId) {
        this.jcontent = jcontent;
        this.jdate = jdate;
        this.bname = bname;
        this.uId = uId;
    }

    @Override
    public String toString() {
        return "Journal{" +
                "jId=" + jId +
                ", jcontent='" + jcontent + '\'' +
                ", jdate='" + jdate + '\'' +
                ", bname='" + bname + '\'' +
                ", uId=" + uId +
                '}';
    }
}