package com.bean;

import java.io.Serializable;

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

    private String hiredate;

    private String leavedate;

    private String birthday;

    private Dept dept;
    private Job job;

    public Dept getDept() {
        return dept;
    }

    public void setDept(Dept dept) {
        this.dept = dept;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

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

    public String getHiredate() {
        return hiredate;
    }

    public void setHiredate(String hiredate) {
        this.hiredate = hiredate;
    }

    public String getLeavedate() {
        return leavedate;
    }

    public void setLeavedate(String leavedate) {
        this.leavedate = leavedate;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public Users() {
    }

    public Users(String uname, String upassword) {
        this.uname = uname;
        this.upassword = upassword;
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