package com.dao;

import com.bean.Orders;
import com.util.Pager;

import java.util.List;

public interface OrdersDao {
    //得到所有并分页
    public List<Orders> getAllOrders(Pager<Orders> pager);
    public int countOrders();
    //查找个人所拥有的订购单
    public List<Orders> getAllOrdersById(Pager<Orders> pager);
    public int countOrdersById(String oId);
    //通过订单编号获取订单
    public Orders getOneOrder(String orderId);
    //删除订单
    public int deleteOrder(String orderId);
    //提交审核订单
    public int examineOrder(String orderId);

    
}