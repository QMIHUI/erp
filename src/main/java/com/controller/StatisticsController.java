package com.controller;

import com.bean.*;
import com.dao.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author psqing
 * @create 2020-11-04 16:16
 */
@Controller
public class StatisticsController {
    @Autowired
    public FirmDao firmDao;
    @Autowired
    public PurchaseDao purchaseDao;
    @Autowired
    public CustomDao customDao;
    @Autowired
    public OrdersDao ordersDao;
    @Autowired
    public CkWarehouseDao ckWarehouseDao;
    @Autowired
    public RkWarehouseDao rkWarehouseDao;

    @RequestMapping(value = "getAllFirms.do",method = RequestMethod.GET)
    public String getAllFirms(HttpSession session){
        List<Firm> firmList=firmDao.getAllFrims();
        session.setAttribute("firmList",firmList);
        return "redirect:statis/purchase/purchaseStatis.jsp";
    }
    @RequestMapping(value = "getFirmById.do",method = RequestMethod.GET)
    public String getFirmById(HttpServletRequest request,HttpSession session){
        int FirmId=Integer.parseInt(request.getParameter("id"));
        List<Purchase> purchaseList=purchaseDao.getPurchaseByCheckId(FirmId);
        session.setAttribute("purchaseList",purchaseList);
        String firmName=firmDao.getFirmById(FirmId).getFirmName();
        session.setAttribute("firmName",firmName);
        return "redirect:statis/purchase/purchaseView.jsp";
    }
    @RequestMapping(value = "getDetails.do",method = RequestMethod.GET)
    public String getDetails(HttpServletRequest request,HttpSession session){
        String purchaseId=request.getParameter("purchaseId");
        Purchase purchase=purchaseDao.getPurchaseById(purchaseId);
        session.setAttribute("purchase",purchase);
        return "redirect:statis/purchase/details.jsp";
    }
    //数据统计获取所有客户
    @RequestMapping(value = "getAllcustomsStatis.do",method = RequestMethod.GET)
    public String getAllcustomsStatis(HttpSession session){
        List<Custom> customList=customDao.getAllCustomsStatis();
        session.setAttribute("customList",customList);
        return "redirect:statis/sales/salesStatis.jsp";
    }
    //获取单个客户下的订购单
    @RequestMapping(value = "getOrdersByCustomId.do",method = RequestMethod.GET)
    public String getOrdersByCustomId(HttpServletRequest request ,HttpSession session){
        int customId=Integer.parseInt(request.getParameter("id"));
        List<Orders> ordersList=ordersDao.getOrdersByCustomId(customId);
        session.setAttribute("ordersList",ordersList);
        return "redirect:statis/sales/salesView.jsp";
    }


    //入库统计
    @RequestMapping(value = "intoWarehouseStatis.do",method = RequestMethod.GET)
    public String intoWarehouse(){

        return "redirect:statis/stock/stockStatis.jsp";
    }



    //出库统计
    @RequestMapping(value = "outWarehouseStatis.do",method = RequestMethod.GET)
    public String outWarehouse(HttpSession session){
        //获取所有出库信息
        List<CkWarehouse> ckWarehouseList=ckWarehouseDao.getAllCkWarehouse();
        session.setAttribute("ckWarehouseList",ckWarehouseList);
        return "redirect:statis/delivery/deliveryStatis.jsp";
    }
}
