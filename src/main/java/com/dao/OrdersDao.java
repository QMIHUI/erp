package com.dao;

import com.bean.Orders;

import java.util.List;

public interface OrdersDao {
    //得到所有并分页
    public List<Orders> getAllOrders();
    public int countOrders();
}