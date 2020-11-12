package com.dao;

import com.bean.Orderdetails;

import java.util.List;

public interface OrderdetailsDao {
    
    public List<Orderdetails> getDetailsByOrderId(String orderId);
    //删除订单详情
    public int deleteOrderDetail(String orderId);

}