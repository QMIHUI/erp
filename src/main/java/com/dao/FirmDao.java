package com.dao;

import com.bean.Firm;

import java.util.List;

public interface FirmDao {
    //获取所有供应商
    public List<Firm> getAllFrims();

    //获取单个供应商(不含采购单)
    public Firm getFirmById(int id);

    //根据id删除供应商
    public int delFirm(int id);

}