package com.controller;

import com.bean.Custom;
import com.bean.Orderdetails;
import com.bean.Orders;
import com.bean.Product;
import com.dao.CustomDao;
import com.dao.OrderdetailsDao;
import com.dao.OrdersDao;
import com.dao.ProductDao;
import com.util.Pager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author HUI
 * @create 2020-11-11 10:33
 */
@Controller
public class OrderController {
    @Autowired
    private OrdersDao ordersDao;
    @Autowired
    private OrderdetailsDao orderdetailsDao;
    @Autowired
    private CustomDao customDao;
    @Autowired
    private ProductDao productDao;

    @RequestMapping(value = "queryAllOrder.do",method = RequestMethod.GET)
    public String queryAllOrder(HttpServletRequest request){
        System.out.println("执行查找全部订单！！！");
        int countOrder = ordersDao.countOrders();
        System.out.println(countOrder);
        int size = 5;
        int rowOrder = countOrder % size == 0 ? (countOrder / size) : (countOrder / size + 1);
        System.out.println(rowOrder);
        String currentIndex= request.getParameter("pageIndex");
        //第一次访问(当前页码=1)
        int pageIndex = 1;
        if(currentIndex!=null) {
            pageIndex=Integer.parseInt(currentIndex);
        }
        if(currentIndex==null || Integer.parseInt(currentIndex) <= 0){
            pageIndex = 1;
        }else if(Integer.parseInt(currentIndex) >= rowOrder){
            pageIndex=rowOrder;
        }
        Pager<Orders> pager = new Pager<>();
        pager.setPage((pageIndex-1)*size);
        pager.setSize(size);
        pager.setTotal(countOrder);
        List<Orders> listOrder = ordersDao.getAllOrders(pager);
        request.getSession().setAttribute("listOrder",listOrder);
        request.getSession().setAttribute("countOrder",countOrder);
        request.getSession().setAttribute("rowOrder",rowOrder);
        request.getSession().setAttribute("pageIndex",pageIndex);
        return "market/order/orderList";
    }

    @RequestMapping(value = "getOneOrder.do",method = RequestMethod.GET)
    public String getOneOrder(HttpServletRequest request){
        System.out.println("根据订单id查看订单");
        String orderId = request.getParameter("orderId");
        System.out.println(orderId);
        String op = request.getParameter("op");
        Orders order = ordersDao.getOneOrder(orderId);
        request.getSession().setAttribute("order",order);
        List<Orderdetails> orderDetailsList = orderdetailsDao.getDetailsByOrderId(orderId);
        request.getSession().setAttribute("orderDetailsList",orderDetailsList);
        List<Custom> listCust = customDao.getCust();
        request.getSession().setAttribute("listCust",listCust);
        List<Product> listProduct = productDao.getAllProduct();
        request.getSession().setAttribute("listProduct",listProduct);
        if(op.equals("查看")){
            return "market/order/orderView";
        }else {
            return "market/order/orderUpdate";
        }
    }

}
