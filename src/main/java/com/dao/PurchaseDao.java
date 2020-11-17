package com.dao;

import com.bean.Purchase;
import com.util.Pager;

import java.util.Date;
import java.util.List;

public interface PurchaseDao {
    //根据厂商id查找采购单集合
    public List<Purchase> getPurchaseByCheckId(int id);
    //根据厂商id查找采购单集合分页
    public List<Purchase> getPurchaseByCheckIdPage(Pager<Purchase> pager);
    public int countPurchaseByCheckId(int id);
    //根据订单号查找采购单
    public Purchase getPurchaseById(String id);
    //查找所有采购单
    public List<Purchase> getAllPurchases();

    public List<Purchase> getAllPurchasesPage(Pager<Purchase> pager);
    public int countPurchaseAll();

    //根据createId查找所有个人的采购单
    public List<Purchase> getPurchasesByCreateId(int createId);
    public List<Purchase> getPurchasesByCreateIdPage(Pager<Purchase> pager);
    public int countPurchasesByCreateId(int createId);
    //删除采购单
    public int deletePurchase(String id);
    public int deletePurchaseDetails(String id);
    //采购单提交审核
    public int submitPurchase(String id);
    //获取所有审核中采购单
    public List<Purchase> getAllPurchaseStatus2(Pager<Purchase> pager);
    public int countPurchaseStatus2();
    //审核采购单
    public int purchaseExamine(int checkId, Date checkTime,String checkOpinion,int checkStatus,String Id);
    //增加采购单
    public int addPurchase(String purchaseId,int createId,Date purchaseTime,int warehouseId,Double purchaseTotalMoney);
    //修改采购单
    public int updatePurchase(String purchaseId,Double purchaseTotalMoney);
    List<Purchase> getAllByRkwarehouse();//查询所有通过审核未入库的订单
    int updatePurchaseStateByRkwarehouse(String purchaseId);//入库后根据采购单号修改状态
}