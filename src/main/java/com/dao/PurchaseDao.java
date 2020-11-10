package com.dao;

import com.bean.Purchase;

import java.util.List;

public interface PurchaseDao {
    //根据厂商id查找采购单集合
    public List<Purchase> getPurchaseByCheckId(int id);
    //根据订单号查找采购单
    public Purchase getPurchaseById(String id);
    //查找所有采购单
    public List<Purchase> getAllPurchases();
    //根据createId查找所有个人的采购单
    public List<Purchase> getPurchasesByCreateId(int createId);
}