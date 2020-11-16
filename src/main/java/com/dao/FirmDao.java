package com.dao;

import com.bean.Firm;
import com.util.Pager;

import java.util.Date;
import java.util.List;

public interface FirmDao {
    //获取所有供应商分页
    public List<Firm> getAllFrimsPage(Pager<Firm> pager);
    //获取所有供应商
    public List<Firm> getAllFrims();
    //获取供应商个数
    public int coutFirm();
    //获取单个供应商(不含采购单)
    public Firm getFirmById(int id);

    //根据id注销供应商
    public int delFirm(int id);
    //恢复供应商
    public int recoverFirm(int id);

    //增加供应商
    public int addFirm(String firmName, String firmTel, String firmAddress, String firmContent, int c_id, Date createTime,int createId,int status,String firmFounder);

    //修改供应商
    public int updateFirm(String firmName, String firmTel, String firmAddress, String firmContent, int c_id,int createId,int status,String firmFounder,int firmId);

    //根据供应商名称查找供应商
    public int countFirmByName(String name);
}