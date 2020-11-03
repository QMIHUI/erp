package com.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * users
 * @author 
 */
public class Users implements Serializable {
    private Integer uId;

    private String uname;

    private String upassword;

    private String utelephone;

    private Integer deptId;

    private Integer jobId;

    private String sex;

    private Integer status;

    private Date hiredate;

    private Date leavedate;

    private Date birthday;

    private static final long serialVersionUID = 1L;

    public Integer getuId() {
        return uId;
    }

    public void setuId(Integer uId) {
        this.uId = uId;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getUpassword() {
        return upassword;
    }

    public void setUpassword(String upassword) {
        this.upassword = upassword;
    }

    public String getUtelephone() {
        return utelephone;
    }

    public void setUtelephone(String utelephone) {
        this.utelephone = utelephone;
    }

    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    public Integer getJobId() {
        return jobId;
    }

    public void setJobId(Integer jobId) {
        this.jobId = jobId;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getHiredate() {
        return hiredate;
    }

    public void setHiredate(Date hiredate) {
        this.hiredate = hiredate;
    }

    public Date getLeavedate() {
        return leavedate;
    }

    public void setLeavedate(Date leavedate) {
        this.leavedate = leavedate;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "Users{" +
                "uId=" + uId +
                ", uname='" + uname + '\'' +
                ", upassword='" + upassword + '\'' +
                ", utelephone='" + utelephone + '\'' +
                ", deptId=" + deptId +
                ", jobId=" + jobId +
                ", sex='" + sex + '\'' +
                ", status=" + status +
                ", hiredate=" + hiredate +
                ", leavedate=" + leavedate +
                ", birthday=" + birthday +
                '}';
    }
}