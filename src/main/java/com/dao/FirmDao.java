package com.dao;

import com.bean.Firm;

import java.util.Date;
import java.util.List;

public interface FirmDao {
    //获取所有供应商
    public List<Firm> getAllFrims();

    //获取单个供应商(不含采购单)
    public Firm getFirmById(int id);

    //根据id删除供应商
    public int delFirm(int id);

    //增加供应商
    public int addFirm(String firmName, String firmTel, String firmAddress, String firmContent, int c_id, Date createTime,int createId,int status,String firmFounder);


}