package com.controller;

import com.bean.*;
import com.dao.*;
import com.util.Pager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.*;

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
    @Autowired
    private UsersDao usersDao;
    @Autowired
    public BrandDao brandDao;
    @Autowired
    public TypeDao typeDao;
    @Autowired
    public WarehouseDao warehouseDao;

    @RequestMapping(value = "queryAllOrder.do",method = RequestMethod.GET)
    public String queryAllOrder(HttpServletRequest request, HttpSession session){
        Users user=(Users)session.getAttribute("user");
        if (user.getJobId()==1||user.getJobId()==2){
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
            if (pageIndex==0){
                pageIndex=1;
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
        }else{
            System.out.println("执行查找个人全部订单！！！");
            int countOrder = ordersDao.countOrdersById(user.getuId());
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
            if (pageIndex==0){
                pageIndex=1;
            }
            Pager<Orders> pager = new Pager<>();
            pager.setPage((pageIndex-1)*size);
            pager.setSize(size);
            pager.setTotal(countOrder);
            pager.setOperatorid(user.getuId());
            List<Orders> listOrder = ordersDao.getAllOrdersById(pager);
            request.getSession().setAttribute("listOrder",listOrder);
            request.getSession().setAttribute("countOrder",countOrder);
            request.getSession().setAttribute("rowOrder",rowOrder);
            request.getSession().setAttribute("pageIndex",pageIndex);
        }
        return "market/order/orderList";
    }

    @RequestMapping(value = "getOneOrder.do",method = RequestMethod.GET)
    public String getOneOrder(HttpServletRequest request,HttpSession session){
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
        List<Brand> brandList=brandDao.getAllBrands();
        session.setAttribute("brandList",brandList);
        List<Type> typeList=typeDao.getTypeListByBrandId(brandList.get(0).getBrandId());
        session.setAttribute("typeList",typeList);
        List<Product> productList=productDao.getProductsByTypeId(typeList.get(0).getTypeId());
        session.setAttribute("productList",productList);
        if(op.equals("查看")){
            return "market/order/orderView";
        }else if(op.equals("审核")){
            return "market/orderExamine/orderExamine";
        }else{
            return "market/order/orderUpdate";
        }
    }

    @RequestMapping(value = "chuKuXiangQing.do",method = RequestMethod.GET)
    public String chuKuXiangQing(HttpServletRequest request){
        System.out.println("出库详情！！！");
        String orderId = request.getParameter("orderId");
        Orders order = ordersDao.getOneOrder(orderId);
        request.getSession().setAttribute("order",order);
        List<Orderdetails> orderDetailsList = orderdetailsDao.getDetailsByOrderId(orderId);
        request.getSession().setAttribute("orderDetailsList",orderDetailsList);
        return "storage/delivery/deliveryView";
    }


    @RequestMapping(value = "deleteOrder.do",method = RequestMethod.GET)
    public String deleteOrder(HttpServletRequest request){
        System.out.println("执行删除订单！！！");
        String orderId = request.getParameter("orderId");
        System.out.println(orderId);
        //删除订单详情
        int n = orderdetailsDao.deleteOrderDetail(orderId);
        //删除订单
        int num = ordersDao.deleteOrder(orderId);
        System.out.println(num);
        System.out.println(n);
        if(num>0 && n>0 ){
            return "forward:queryAllOrder.do";
        }else{
            return "forward:queryAllOrder.do";
        }
    }
    @RequestMapping(value = "examineOrder.do",method = RequestMethod.GET)
    public String examineOrder(HttpServletRequest request){
        System.out.println("执行提交审核");
        String orderId = request.getParameter("orderId");
        System.out.println(orderId);
        int num = ordersDao.examineOrder(orderId);
        return "forward:queryAllOrder.do";
    }

    /* 模糊查询 */
    @RequestMapping(value = "getOrderByCon",method = RequestMethod.GET)
    public String getOrderByCon(HttpSession session,HttpServletRequest request){
        Users user=(Users)session.getAttribute("user");
        int size = 5;
        String orderId = request.getParameter("orderId");
        String startDate = request.getParameter("startDate");
        String enddate = request.getParameter("enddate");
        Double sPrice = 0.0;
        Double bPrice = 0.0;
        String sPrice01 =request.getParameter("sPrice");
        if(!(sPrice01.equals(""))){
            sPrice = Double.parseDouble(sPrice01);
        }
        String bPrice01 =request.getParameter("bPrice");
        if(!(bPrice01.equals(""))){
            bPrice = Double.parseDouble(bPrice01);
        }
        int dstatus = Integer.parseInt(request.getParameter("dstatus"));
        if (user.getJobId()==1||user.getJobId()==2){
            System.out.println("执行所有订单模糊查询！！！");
            System.out.println(orderId+".11-"+startDate+".11-"+enddate+".11-"+sPrice+".11-"+bPrice+".11-"+dstatus);
            Map<String,Object> map = new HashMap<>();
            map.put("orderId",orderId);
            map.put("startDate",startDate);
            map.put("enddate",enddate);
            map.put("sPrice",sPrice);
            map.put("bPrice",bPrice);
            map.put("dstatus",dstatus);
            int countOrderByCon = ordersDao.countOrdersByCon(map);
            System.out.println("个数为:"+countOrderByCon);
            int rowOrderByCon = countOrderByCon % size == 0 ? (countOrderByCon / size) : (countOrderByCon / size + 1);
            System.out.println(rowOrderByCon);
            String currentIndex= request.getParameter("pageIndex");
            //第一次访问(当前页码=1)
            int pageIndex = 1;
            if(currentIndex!=null) {
                pageIndex=Integer.parseInt(currentIndex);
            }
            if(currentIndex==null || Integer.parseInt(currentIndex) <= 0){
                pageIndex = 1;
            }else if(Integer.parseInt(currentIndex) >= rowOrderByCon){
                pageIndex=rowOrderByCon;
            }
            if (pageIndex==0){
                pageIndex=1;
            }
            Pager<Orders> pager = new Pager<>();
            pager.setPage((pageIndex-1)*size);
            pager.setSize(size);
            pager.setTotal(countOrderByCon);
            pager.setOrderId(orderId);
            pager.setStartDate(startDate);
            pager.setEndDate(enddate);
            pager.setbPrice(bPrice);
            pager.setsPrice(sPrice);
            pager.setDstatus(dstatus);
            List<Orders> listOrdersByCon = ordersDao.getOrdersByCon(pager);
            request.getSession().setAttribute("listOrdersByCon",listOrdersByCon);
            request.getSession().setAttribute("orderId",orderId);
            request.getSession().setAttribute("startDate",startDate);
            request.getSession().setAttribute("enddate",enddate);
            request.getSession().setAttribute("bPrice",bPrice);
            request.getSession().setAttribute("sPrice",sPrice);
            request.getSession().setAttribute("dstatus",dstatus);
            request.getSession().setAttribute("countOrderByCon",countOrderByCon);
            request.getSession().setAttribute("rowOrderByCon",rowOrderByCon);
            request.getSession().setAttribute("pageIndex",pageIndex);
        }else{
            System.out.println("执行个人所有订单模糊查询！！！");
            int uId = user.getuId();
            System.out.println(orderId+".11-"+startDate+".11-"+enddate+".11-"+sPrice+".11-"+bPrice+".11-"+dstatus);
            Map<String,Object> map = new HashMap<>();
            map.put("orderId",orderId);
            map.put("startDate",startDate);
            map.put("enddate",enddate);
            map.put("sPrice",sPrice);
            map.put("bPrice",bPrice);
            map.put("dstatus",dstatus);
            map.put("operatorid",uId);
            int countOrderByCon = ordersDao.countOrdersByCon(map);
            System.out.println("个数为:"+countOrderByCon);
            int rowOrderByCon = countOrderByCon % size == 0 ? (countOrderByCon / size) : (countOrderByCon / size + 1);
            System.out.println(rowOrderByCon);
            String currentIndex= request.getParameter("pageIndex");
            //第一次访问(当前页码=1)
            int pageIndex = 1;
            if(currentIndex!=null) {
                pageIndex=Integer.parseInt(currentIndex);
            }
            if(currentIndex==null || Integer.parseInt(currentIndex) <= 0){
                pageIndex = 1;
            }else if(Integer.parseInt(currentIndex) >= rowOrderByCon){
                pageIndex=rowOrderByCon;
            }
            if (pageIndex==0){
                pageIndex=1;
            }
            Pager<Orders> pager = new Pager<>();
            pager.setPage((pageIndex-1)*size);
            pager.setSize(size);
            pager.setTotal(countOrderByCon);
            pager.setOrderId(orderId);
            pager.setStartDate(startDate);
            pager.setEndDate(enddate);
            pager.setbPrice(bPrice);
            pager.setsPrice(sPrice);
            pager.setDstatus(dstatus);
            pager.setOperatorid(uId);
            List<Orders> listOrdersByCon = ordersDao.getOrdersByCon(pager);
            request.getSession().setAttribute("listOrdersByCon",listOrdersByCon);
            request.getSession().setAttribute("orderId",orderId);
            request.getSession().setAttribute("startDate",startDate);
            request.getSession().setAttribute("enddate",enddate);
            request.getSession().setAttribute("bPrice",bPrice);
            request.getSession().setAttribute("sPrice",sPrice);
            request.getSession().setAttribute("dstatus",dstatus);
            request.getSession().setAttribute("countOrderByCon",countOrderByCon);
            request.getSession().setAttribute("rowOrderByCon",rowOrderByCon);
            request.getSession().setAttribute("pageIndex",pageIndex);
            request.getSession().setAttribute("uId",uId);
        }
        return "market/order/orderListByCon";
    }

    @RequestMapping(value = "getAllExamineOrder.do",method = RequestMethod.GET)
    public String getAllExamineOrder(HttpServletRequest request){
        System.out.println("执行查询所有待审核的订单！！！");
        int size = 5;
        int countExamOrder = ordersDao.countExaminerOrder();
        System.out.println("待审核的订单数量为:"+countExamOrder);
        int rowExamOrder = countExamOrder % size == 0 ? (countExamOrder / size) : (countExamOrder / size + 1);
        System.out.println("页数为:"+rowExamOrder);
        String currentIndex= request.getParameter("pageIndex");
        //第一次访问(当前页码=1)
        int pageIndex = 1;
        if(currentIndex!=null) {
            pageIndex=Integer.parseInt(currentIndex);
        }
        if(currentIndex==null || Integer.parseInt(currentIndex) <= 0){
            pageIndex = 1;
        }else if(Integer.parseInt(currentIndex) >= rowExamOrder){
            pageIndex=rowExamOrder;
        }
        if (pageIndex==0){
            pageIndex=1;
        }
        Pager<Orders> pager = new Pager<>();
        pager.setPage((pageIndex-1)*size);
        pager.setSize(size);
        pager.setTotal(countExamOrder);
        List<Orders> listExamOrder = ordersDao.getAllExamineOrder(pager);
        request.getSession().setAttribute("listExamOrder",listExamOrder);
        request.getSession().setAttribute("rowExamOrder",rowExamOrder);
        request.getSession().setAttribute("pageIndex",pageIndex);
        request.getSession().setAttribute("countExamOrder",countExamOrder);
        return "market/orderExamine/orderExamineList";
    }

    @RequestMapping(value = "orderEamine.do",method = RequestMethod.GET)
    public String orderEamine(HttpServletRequest request, HttpSession session, HttpServletResponse response){
        System.out.println("审核订单！！！");
        PrintWriter out=null;
        try {
            out=response.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String orderId = request.getParameter("orderId");
        int checkId=((Users)session.getAttribute("user")).getuId();
        String opinion = request.getParameter("opinion");
        int dstatus = Integer.parseInt(request.getParameter("dstatus"));
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        String checkTime = format.format(date);
        int num = ordersDao.orderExamine(dstatus,checkId,checkTime,opinion,orderId);
        if(num>0){
            return "forward:getAllExamineOrder.do";
        }else{
            return "redirect:market/order/orderExamine";

        }
    }

    @RequestMapping(value = "getExamineOrderByCon.do",method = RequestMethod.GET)
    public String getExamineOrderByCon(HttpServletRequest request){
        System.out.println("执行待审核订单模糊查询！！！");
        String orderId = request.getParameter("orderId");
        String startDate = request.getParameter("startDate");
        String enddate = request.getParameter("enddate");
        Double sPrice = 0.0;
        Double bPrice = 0.0;
        String sPrice01 =request.getParameter("sPrice");
        if(!(sPrice01.equals(""))){
            sPrice = Double.parseDouble(sPrice01);
        }
        String bPrice01 =request.getParameter("bPrice");
        if(!(bPrice01.equals(""))){
            bPrice = Double.parseDouble(bPrice01);
        }
        Map<String,Object> map = new HashMap<>();
        map.put("orderId",orderId);
        map.put("startDate",startDate);
        map.put("enddate",enddate);
        map.put("sPrice",sPrice);
        map.put("bPrice",bPrice);
        int countExamineOrderByCon = ordersDao.countExamineOrderByCon(map);
        System.out.println(countExamineOrderByCon);
        int size = 5;
        int rowExamineOrderByCon = countExamineOrderByCon % size == 0 ? (countExamineOrderByCon / size) : (countExamineOrderByCon / size + 1);
        System.out.println(rowExamineOrderByCon);
        String currentIndex= request.getParameter("pageIndex");
        //第一次访问(当前页码=1)
        int pageIndex = 1;
        if(currentIndex!=null) {
            pageIndex=Integer.parseInt(currentIndex);
        }
        if(currentIndex==null || Integer.parseInt(currentIndex) <= 0){
            pageIndex = 1;
        }else if(Integer.parseInt(currentIndex) >= rowExamineOrderByCon){
            pageIndex=rowExamineOrderByCon;
        }
        if (pageIndex==0){
            pageIndex=1;
        }
        Pager<Orders> pager = new Pager<>();
        pager.setPage((pageIndex-1)*size);
        pager.setSize(size);
        pager.setTotal(countExamineOrderByCon);
        pager.setOrderId(orderId);
        pager.setStartDate(startDate);
        pager.setEndDate(enddate);
        pager.setbPrice(bPrice);
        pager.setsPrice(sPrice);
        List<Orders> listExamOrderByCon = ordersDao.getExamineOrderByCon(pager);
        request.getSession().setAttribute("listExamOrderByCon",listExamOrderByCon);
        request.getSession().setAttribute("rowExamineOrderByCon",rowExamineOrderByCon);
        request.getSession().setAttribute("pageIndex",pageIndex);
        request.getSession().setAttribute("countExamineOrderByCon",countExamineOrderByCon);
        request.getSession().setAttribute("orderId",orderId);
        request.getSession().setAttribute("startDate",startDate);
        request.getSession().setAttribute("enddate",enddate);
        request.getSession().setAttribute("bPrice",bPrice);
        request.getSession().setAttribute("sPrice",sPrice);
        return "market/orderExamine/orderExamineListByCon";
    }

    @RequestMapping(value = "addOrder.do",method = RequestMethod.GET)
    public String addOrder(HttpSession session){
        System.out.println("执行增加订单前的操作！！！");
        Users user=(Users)session.getAttribute("user");
        List<Custom> listCu = customDao.getCustomById(user.getuId());
        session.setAttribute("listCu",listCu);
        List<Brand> brandList=brandDao.getAllBrands();
        session.setAttribute("brandList",brandList);
        List<Type> typeList=typeDao.getTypeListByBrandId(brandList.get(0).getBrandId());
        session.setAttribute("typeList",typeList);
        List<Product> productList=productDao.getProductsByTypeId(typeList.get(0).getTypeId());
        session.setAttribute("productList",productList);
        List<Warehouse> warehouseList=warehouseDao.getAllWarehouse();
        session.setAttribute("warehouseList",warehouseList);
        //生成采购单编号
        String orderId="";
        Calendar orderTime=Calendar.getInstance();
        String year=""+orderTime.get(Calendar.YEAR);
        String month=""+(orderTime.get(Calendar.MONTH) + 1);
        String day=""+orderTime.get(Calendar.DAY_OF_MONTH);
        String date=year+month+day;
        System.out.println("date"+date);
        System.out.println(date);
        List<Orders> orderList=ordersDao.getAllOrder();
        int count=0;
        int max=0;
        for (int i=0;i<orderList.size();i++){
            String orderDate=orderList.get(i).getOrdertime().substring(0,10);
            System.out.println(orderDate);
            String s = orderDate.substring(0,4);
            String ss = orderDate.substring(5,7);
            String sss = orderDate.substring(8,10);
            String oo = s+ss+sss;
            System.out.println(oo);
            if (oo.equals(date)){
                int index=Integer.parseInt(orderList.get(i).getOrderId().substring(10));
                if (index>max){
                    max=index;
                }
                count++;
            }
        }
        if (count==0){
            orderId="DJ"+date+"0001";
        }else {
            String index=String.format("%04d", max+1);
            orderId="DJ"+date+index;
        }
        session.setAttribute("orderIdAdd",orderId);
        System.out.println(orderId);
        return "market/order/orderAdd";
    }

    @RequestMapping(value = "addOrderSucc.do",method = RequestMethod.GET)
    public String addOrderSucc(HttpServletRequest request,HttpSession session,String[] product,String[] count,String[] productPrice,String[] productTotalMoney){
        System.out.println("执行增加订单！！");
        String orderId = request.getParameter("orderId");
        int custName = Integer.parseInt(request.getParameter("custName"));
        int operator=((Users)session.getAttribute("user")).getuId();
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        String orderTime = format.format(date);
        double totalMoney=Double.parseDouble(request.getParameter("orderTotalMoney"));
        int num = ordersDao.addOrder(orderId,custName,orderTime,totalMoney,operator);
        //生成订购单详情
        for (int i=0;i<product.length;i++){
            orderdetailsDao.addOrderDetail(Integer.parseInt(count[i]),Integer.parseInt(product[i]),Double.parseDouble(productPrice[i]),Double.parseDouble(productTotalMoney[i]),orderId);
        }
        System.out.println("订购单生成成功");

        return "redirect:queryAllOrder.do";
    }

    @RequestMapping(value = "updateOrder.do",method = RequestMethod.GET)
    public String updateOrder(HttpServletRequest request,String[] product,String[] count,String[] productPrice,String[] productTotalMoney){
        System.out.println("执行修改订单！！！");
        Double orderTotalMoney = Double.parseDouble(request.getParameter("orderTotalMoney"));
        System.out.println(orderTotalMoney);
        String orderId = request.getParameter("orderId");
        System.out.println(orderId);
        Orders order = new Orders(orderId,orderTotalMoney);
        ordersDao.updateOrder(order);
        orderdetailsDao.deleteOrderDetail(orderId);
        for (int i=0;i<product.length;i++){
            orderdetailsDao.addOrderDetail(Integer.parseInt(count[i]),Integer.parseInt(product[i]),Double.parseDouble(productPrice[i]),Double.parseDouble(productTotalMoney[i]),orderId);
        }
        System.out.println("订购单生成成功");
        return "redirect:queryAllOrder.do";
    }



}
