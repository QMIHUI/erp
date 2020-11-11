package com.dao;

import com.bean.Orderdetails;

import java.util.List;

public interface OrderdetailsDao {
    
    public List<Orderdetails> getDetailsByOrderId(String orderId);

}