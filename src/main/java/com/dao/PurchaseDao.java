package com.dao;

import com.bean.Purchase;

import java.util.List;

public interface PurchaseDao {
    public List<Purchase> getPurchaseByCheckId(int id);
}