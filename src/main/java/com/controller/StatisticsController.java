package com.controller;

import com.bean.*;
import com.dao.*;
import com.util.Pager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @Autowired
    public WarehouseDao warehouseDao;
    @Autowired
    public ProvinceDao provinceDao;

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
    public String getAllcustomsStatis(HttpSession session,HttpServletRequest request){

        int countCustom = customDao.countCustom();
        System.out.println(countCustom);
        int size = 5;
        int row = countCustom % size == 0 ? (countCustom / size) : (countCustom / size + 1);
        System.out.println(row);
        System.out.println("执行查询所有用户");
        String currentIndex= request.getParameter("pageIndex");
        //第一次访问(当前页码=1)
        int pageIndex = 1;
        if(currentIndex!=null) {
            pageIndex=Integer.parseInt(currentIndex);
        }
        if(currentIndex==null || Integer.parseInt(currentIndex) <= 0){
            pageIndex = 1;
        }else if(Integer.parseInt(currentIndex) >= row){
            pageIndex=row;
        }
        Pager<Custom> pager = new Pager<>();
        pager.setPage((pageIndex-1)*size);
        pager.setSize(size);
        pager.setTotal(countCustom);
        List<Custom> customList=customDao.getAllCustomsStatis(pager);
        request.getSession().setAttribute("row",row);
        request.getSession().setAttribute("pageIndex",pageIndex);
        request.getSession().setAttribute("countCustom",countCustom);
        request.getSession().setAttribute("customList",customList);

        List<Province> provinceList=provinceDao.getAllProvinces();
        session.setAttribute("provinceList",provinceList);

        return "redirect:statis/sales/salesStatis.jsp";
    }
    //客户模糊查询
    @RequestMapping(value = "getCustomersByConStatis.do",method = RequestMethod.GET)
    public String getCustomersByConStatis(HttpServletRequest request){
        System.out.println("执行模糊查询顾客");
        String custName = request.getParameter("custName");
        System.out.println(custName);
        int province = Integer.parseInt(request.getParameter("province"));
        String startDate = request.getParameter("startDate");
        String enddate = request.getParameter("enddate");

        Map<String,Object> map = new HashMap<>();
        map.put("customname",custName);
        map.put("address",province);
        map.put("startDate",startDate);
        map.put("endDate",enddate);

        int countCustByCon = customDao.countCustByConStatic(map);

        int size = 5;
        int rowCustByCon = countCustByCon % size == 0 ? (countCustByCon / size) : (countCustByCon / size + 1);
        System.out.println(rowCustByCon);
        String currentIndex= request.getParameter("pageIndex");
        //第一次访问(当前页码=1)
        int pageIndex = 1;
        if(currentIndex!=null) {
            pageIndex=Integer.parseInt(currentIndex);
        }
        if(currentIndex==null || Integer.parseInt(currentIndex) <= 0){
            pageIndex = 1;
        }else if(Integer.parseInt(currentIndex) >= rowCustByCon){
            pageIndex=rowCustByCon;
        }

        Pager<Custom> pager = new Pager<>();
        pager.setPage((pageIndex-1)*size);
        pager.setSize(size);
        pager.setTotal(countCustByCon);

        pager.setCustomname(custName);
        pager.setAddress(province);
        pager.setStartDate(startDate);
        pager.setEndDate(enddate);

        List<Custom> listCustomByCon = customDao.getCustomByConStatis(pager);
        listCustomByCon.forEach((x->System.out.println(x)));

        request.getSession().setAttribute("listCustomByCon",listCustomByCon);
        request.getSession().setAttribute("countCustByCon",countCustByCon);
        request.getSession().setAttribute("pageIndex",pageIndex);
        request.getSession().setAttribute("rowCustByCon",rowCustByCon);

        request.getSession().setAttribute("custName",custName);
        request.getSession().setAttribute("province",province);
        request.getSession().setAttribute("startDate",startDate);
        request.getSession().setAttribute("enddate",enddate);

        return "redirect:statis/sales/salesStatisByCon.jsp";
    }






    //获取单个客户下的订购单
    @RequestMapping(value = "getOrdersByCustomId.do",method = RequestMethod.GET)
    public String getOrdersByCustomId(HttpServletRequest request ,HttpSession session){
        int customId=Integer.parseInt(request.getParameter("id"));
        List<Orders> ordersList=ordersDao.getOrdersByCustomId1(customId);
        session.setAttribute("ordersList",ordersList);
        return "redirect:statis/sales/salesView.jsp";
    }


    //入库统计
    @RequestMapping(value = "intoWarehouseStatis.do",method = RequestMethod.GET)
    public String intoWarehouse(HttpSession session){
        //获取所有仓库
        List<Warehouse> warehouseList=warehouseDao.getAllWarehouseStatis();
        session.setAttribute("warehouseList",warehouseList);
        return "redirect:statis/stock/stockStatis.jsp";
    }
    //根据仓库获取入库详情集合
    @RequestMapping(value = "getRkWarehouseByWarehouseId.do",method = RequestMethod.GET)
    public String getRkWarehouseByWarehouseId(HttpServletRequest request,HttpSession session){
        int id=Integer.parseInt(request.getParameter("id"));
        List<RkWarehouse> rkWarehouseList=rkWarehouseDao.getAllRkWarehouse(id);
        session.setAttribute("rkWarehouseList",rkWarehouseList);
        return "redirect:statis/stock/stockView.jsp";
    }


    //出库统计
    @RequestMapping(value = "outWarehouseStatis.do",method = RequestMethod.GET)
    public String outWarehouse(HttpSession session){
        //获取所有仓库
        List<Warehouse> warehouseList=warehouseDao.getAllWarehouseStatis();
        session.setAttribute("warehouseList",warehouseList);
        return "redirect:statis/delivery/deliveryStatis.jsp";
    }
    //根据仓库获取出库详情集合
    @RequestMapping(value = "getCkWarehouseByWarehouseId.do",method = RequestMethod.GET)
    public String getCkWarehouseByWarehouseId(HttpServletRequest request,HttpSession session){
        int id=Integer.parseInt(request.getParameter("id"));
        List<CkWarehouse> ckWarehouseList=ckWarehouseDao.getAllCkWarehouse(id);
        session.setAttribute("ckWarehouseList",ckWarehouseList);
        return "redirect:statis/delivery/deliveryView.jsp";
    }
}
