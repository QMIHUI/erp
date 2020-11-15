package com.dao;

import com.bean.Details;

import java.util.List;

public interface DetailsDao {
    public List<Details> getDetailsByPurchaseId(String purchaseId);
    //增加订购单详情
    public int addPurchaseDetails(String purchaseId,int count,int productId,double productPrice,double productTotalMoney);
    //修改订购单详情
    public int updatePurchaseDetails(int count,int productId,double productPrice,double productTotalMoney,int detailsId);
}