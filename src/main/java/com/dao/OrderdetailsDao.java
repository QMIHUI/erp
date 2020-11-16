package com.dao;

import com.bean.Orderdetails;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderdetailsDao {

    public List<Orderdetails> getDetailsByOrderId(String orderId);

    //删除订单详情
    public int deleteOrderDetail(String orderId);

    //添加
    public int addOrderDetail(@Param("purchaseNum") int purchaseNum, @Param("productId") int productId, @Param("proprice") double proprice, @Param("prototal") double prototal, @Param("orderId") String orderId);

    List<Orderdetails> getAllOrderdatailsByOrderid(String indent);//根据订单号查询类型、商品等相关信息
}