package com.dao;

import com.bean.Purchase;

public interface PurchaseDao {
    int deleteByPrimaryKey(String purchaseId);

    int insert(Purchase record);

    int insertSelective(Purchase record);

    Purchase selectByPrimaryKey(String purchaseId);

    int updateByPrimaryKeySelective(Purchase record);

    int updateByPrimaryKey(Purchase record);
}