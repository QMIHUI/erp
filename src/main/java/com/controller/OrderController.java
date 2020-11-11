package com.controller;

import com.dao.OrdersDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * @author HUI
 * @create 2020-11-11 10:33
 */
@Controller
public class OrderController {

    @Autowired
    private OrdersDao ordersDao;

    @RequestMapping(value = "queryAllOrder.do",method = RequestMethod.GET)
    public String queryAllOrder(HttpServletRequest request){
        System.out.println("执行查找全部订单！！！");

        return "market/order/orderList";
    }

}
