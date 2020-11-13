package com.dao;

import com.bean.Orders;
import com.util.Pager;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface OrdersDao {
    //得到所有并分页
    public List<Orders> getAllOrders(Pager<Orders> pager);
    public int countOrders();
    //查找个人所拥有的订购单
    public List<Orders> getAllOrdersById(Pager<Orders> pager);
    public int countOrdersById(int operatorid);

    //通过订单编号获取订单
    public Orders getOneOrder(String orderId);
    //根据客户得到所有订单
    public List<Orders> getOrdersByCustomId(int customId);
    //删除订单
    public int deleteOrder(String orderId);
    //提交审核订单
    public int examineOrder(String orderId);
    //模糊查询
    public List<Orders> getOrdersByCon(Pager<Orders> pager);
    public int countOrdersByCon(Map<String,Object> map);

    //通过订单编号获取订单(含订单详情)
    public Orders getOneOrder1(String orderId);
    //根据客户得到所有订单(含订单详情)
    public List<Orders> getOrdersByCustomId1(int customId);
    //查找个人所拥有的订购单(含订购单详情)
    public List<Orders> getAllOrdersById1(Pager<Orders> pager);
    public int countOrdersById1(int customId);
    //查询所有待审核的订单
    public List<Orders> getAllExamineOrder(Pager<Orders> pager);
    public int countExaminerOrder();
    //待审核订单模糊查询
    public List<Orders> getExamineOrderByCon(Pager<Orders> pager);
    public int countExamineOrderByCon(Map<String,Object> map);

    //审核订购单
    public int orderExamine(@Param("dstatus") int dstatus,@Param("checkid") int checkid,@Param("chectime") String chectime,@Param("opinion") String opinion,@Param("orderId") String orderId);

}