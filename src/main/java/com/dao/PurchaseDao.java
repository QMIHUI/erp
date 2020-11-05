package com.dao;

import com.bean.Purchase;

import java.util.List;

public interface PurchaseDao {
    //根据厂商id查找采购单集合
    public List<Purchase> getPurchaseByCheckId(int id);
    //根据订单号查找采购单
    public Purchase getPurchaseById(String id);
}