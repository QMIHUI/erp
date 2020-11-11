package com.dao;

import com.bean.Orders;
import com.util.Pager;

import java.util.List;

public interface OrdersDao {
    //得到所有并分页
    public List<Orders> getAllOrders(Pager<Orders> pager);
    public int countOrders();
    //通过订单编号获取订单
    public Orders getOneOrder(String orderId);
    
}