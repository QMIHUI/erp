package com.dao;

import com.bean.Details;

import java.util.List;

public interface DetailsDao {
    public List<Details> getDetailsByPurchaseId(String purchaseId);
    //增加采购单详情
    public int addPurchaseDetails(String purchaseId,int count,int productId,double productPrice,double productTotalMoney);

    //根据采购单号删除采购单详情
    public int delDetails(String purchaseId);
    List<Details> getAllDetailsBypurchaseId(String purchaseId);//根据采购单号查询相关的商品信息

    //根据条件返回detailsId
    public int getCountDetailsIdByConditions(int productId,String purchaseId);
    public int getDetailsIdByConditions(int productId,String purchaseId);
    public int getCountByConditions(int productId,String purchaseId);
    public double getMoneyByConditions(int productId,String purchaseId);
    public int updateDetails(int detailsId,int count,double totalMoney);
}