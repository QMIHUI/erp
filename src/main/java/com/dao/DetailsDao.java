package com.dao;

import com.bean.Details;

import java.util.List;

public interface DetailsDao {
    public List<Details> getDetailsByPurchaseId(String purchaseId);
    //增加采购单详情
    public int addPurchaseDetails(String purchaseId,int count,int productId,double productPrice,double productTotalMoney);

    //根据采购单号删除采购单详情
    public int delDetails(String purchaseId);
}