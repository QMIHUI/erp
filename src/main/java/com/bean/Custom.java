package com.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * custom
 * @author 
 */
public class Custom implements Serializable {
    private Integer customid;

    private String customname;

    private String sex;

    private String telephone;

    private String company;

    private Integer address;

    private Integer cstatus;

    private String createtime;

    private Integer createid;

    private Integer leading;

    private String distractime;

    private Integer serviceid;

    private String distract;

    private String homeaddress;

    private Province province;
    private Users users;
    private List<Orders> ordersList;

    private static final long serialVersionUID = 1L;

    public Province getProvince() {
        return province;
    }

    public void setProvince(Province province) {
        this.province = province;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public Integer getCustomid() {
        return customid;
    }

    public void setCustomid(Integer customid) {
        this.customid = customid;
    }

    public String getCustomname() {
        return customname;
    }

    public void setCustomname(String customname) {
        this.customname = customname;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public Integer getAddress() {
        return address;
    }

    public void setAddress(Integer address) {
        this.address = address;
    }

    public Integer getCstatus() {
        return cstatus;
    }

    public void setCstatus(Integer cstatus) {
        this.cstatus = cstatus;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public Integer getCreateid() {
        return createid;
    }

    public void setCreateid(Integer createid) {
        this.createid = createid;
    }

    public Integer getLeading() {
        return leading;
    }

    public void setLeading(Integer leading) {
        this.leading = leading;
    }

    public String getDistractime() {
        return distractime;
    }

    public void setDistractime(String distractime) {
        this.distractime = distractime;
    }

    public Integer getServiceid() {
        return serviceid;
    }

    public void setServiceid(Integer serviceid) {
        this.serviceid = serviceid;
    }

    public String getDistract() {
        return distract;
    }

    public void setDistract(String distract) {
        this.distract = distract;
    }

    public String getHomeaddress() {
        return homeaddress;
    }

    public void setHomeaddress(String homeaddress) {
        this.homeaddress = homeaddress;
    }

    public List<Orders> getOrdersList() {
        return ordersList;
    }

    public void setOrdersList(List<Orders> ordersList) {
        this.ordersList = ordersList;
    }

    @Override
    public String toString() {
        return "Custom{" +
                "customid=" + customid +
                ", customname='" + customname + '\'' +
                ", sex='" + sex + '\'' +
                ", telephone='" + telephone + '\'' +
                ", company='" + company + '\'' +
                ", address=" + address +
                ", status='" + cstatus + '\'' +
                ", createtime='" + createtime + '\'' +
                ", createid=" + createid +
                ", distractime='" + distractime + '\'' +
                ", serviceid=" + serviceid +
                ", distract='" + distract + '\'' +
                ", homeaddress='" + homeaddress + '\'' +
                ", province=" + province +
                ", users=" + users +
                '}';
    }

    public Custom() {
    }

    public Custom(Integer customid, Integer leading) {
        this.customid = customid;
        this.leading = leading;
    }

    public Custom(Integer customid, Integer leading, String distractime) {
        this.customid = customid;
        this.leading = leading;
        this.distractime = distractime;
    }

    public Custom(String customname, String sex, String telephone, String company, Integer address, String createtime, Integer createid, String distract, String homeaddress) {
        this.customname = customname;
        this.sex = sex;
        this.telephone = telephone;
        this.company = company;
        this.address = address;
        this.createtime = createtime;
        this.createid = createid;
        this.distract = distract;
        this.homeaddress = homeaddress;
    }

    public Custom(Integer customid, String customname, String sex, String telephone, String company, Integer address, Integer cstatus, String distract, String homeaddress) {
        this.customid = customid;
        this.customname = customname;
        this.sex = sex;
        this.telephone = telephone;
        this.company = company;
        this.address = address;
        this.cstatus = cstatus;
        this.distract = distract;
        this.homeaddress = homeaddress;
    }

    public Custom(Integer customid, String customname, String telephone, Integer cstatus, Province province, List<Orders> ordersList) {
        this.customid = customid;
        this.customname = customname;
        this.telephone = telephone;
        this.cstatus = cstatus;
        this.province = province;
        this.ordersList = ordersList;
    }
}