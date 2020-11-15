package com.controller;

import com.bean.*;
import com.dao.*;
import com.util.Pager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Array;
import java.util.ArrayList;
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
    @Autowired
    public CityDao cityDao;
    //获取所有厂商并分页
    @RequestMapping(value = "getAllFirms.do",method = RequestMethod.GET)
    public String getAllFirms(HttpSession session,HttpServletRequest request ){

        int countFirm = firmDao.coutFirm();
        System.out.println(countFirm);
        int size = 5;
        int row = countFirm % size == 0 ? (countFirm / size) : (countFirm / size + 1);
        System.out.println(row);
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
        if (pageIndex==0){
            pageIndex=1;
        }
        Pager<Firm> pager = new Pager<>();
        pager.setPage((pageIndex-1)*size);
        pager.setSize(size);
        pager.setTotal(countFirm);
        List<Firm> firmList=firmDao.getAllFrimsPage(pager);
        request.getSession().setAttribute("row",row);
        request.getSession().setAttribute("pageIndex",pageIndex);
        request.getSession().setAttribute("countFirm",countFirm);
        request.getSession().setAttribute("firmList",firmList);

        List<Province> provinceList=provinceDao.getAllProvinces();
        session.setAttribute("provinceList",provinceList);
        List<City> cityList=cityDao.getAllCity();
        session.setAttribute("cityList",cityList);

        List<Firm> firmList1=firmDao.getAllFrims();
        ArrayList<String> FirmNames=new ArrayList<>();
        ArrayList<Integer> FirmPurchaseCount=new ArrayList<>();
        ArrayList<Double> FirmPurchaseTotalMoney=new ArrayList<>();
        for (int i=0;i<firmList1.size();i++){
            FirmNames.add("'"+firmList1.get(i).getFirmName()+"'");
            List<Purchase> purchaseList=firmList1.get(i).getPurchaseList();
            double money=0;
            for (int j=0;j<purchaseList.size();j++){
                money+=purchaseList.get(j).getTotalMoney();
            }
            FirmPurchaseCount.add(purchaseList.size());
            FirmPurchaseTotalMoney.add(money);
        }
        session.setAttribute("FirmNames",FirmNames);
        session.setAttribute("FirmPurchaseCount",FirmPurchaseCount);
        session.setAttribute("FirmPurchaseTotalMoney",FirmPurchaseTotalMoney);

        return "redirect:statis/purchase/purchaseStatis.jsp";
    }
    @RequestMapping(value = "getFirmById.do",method = RequestMethod.GET)
    public String getFirmById(HttpServletRequest request,HttpSession session){
        int FirmId=Integer.parseInt(request.getParameter("id"));
        System.out.println(FirmId);
        int countPurchaseById = purchaseDao.countPurchaseByCheckId(FirmId);
        System.out.println("数量："+countPurchaseById);
        int size = 5;
        int row = countPurchaseById % size == 0 ? (countPurchaseById / size) : (countPurchaseById / size + 1);
        System.out.println("页数"+row);
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
        if (pageIndex==0){
            pageIndex=1;
        }
        Pager<Purchase> pager = new Pager<>();
        pager.setPage((pageIndex-1)*size);
        pager.setSize(size);
        pager.setTotal(countPurchaseById);
        pager.setFirmId(FirmId);
        List<Purchase> purchaseList=purchaseDao.getPurchaseByCheckIdPage(pager);
        request.getSession().setAttribute("row",row);
        request.getSession().setAttribute("pageIndex",pageIndex);
        request.getSession().setAttribute("countPurchaseById",countPurchaseById);
        request.getSession().setAttribute("purchaseList",purchaseList);

        String firmName=firmDao.getFirmById(FirmId).getFirmName();
        session.setAttribute("firmName",firmName);
        session.setAttribute("firmId",FirmId);
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

        int countCustom = customDao.countCustomStatus1();
        System.out.println(countCustom);
        int size = 5;
        int row = countCustom % size == 0 ? (countCustom / size) : (countCustom / size + 1);
        System.out.println(row);
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
        if (pageIndex==0){
            pageIndex=1;
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

        List<Custom> customArrayList=customDao.getCustStatis();
        ArrayList<String> customNames=new ArrayList<>();
        ArrayList<Integer> customOrderCount=new ArrayList<>();
        ArrayList<Double> customOrderTotalMoney=new ArrayList<>();
        for (int i=0;i<customArrayList.size();i++){
            customNames.add("'"+customArrayList.get(i).getCustomname()+"'");
            List<Orders> ordersList=customArrayList.get(i).getOrdersList();
            double money=0;
            for (int j=0;j<ordersList.size();j++){
                money+=ordersList.get(j).getOrdermoney();
            }
            customOrderCount.add(ordersList.size());
            customOrderTotalMoney.add(money);
        }
        session.setAttribute("customNames",customNames);
        session.setAttribute("customOrderCount",customOrderCount);
        session.setAttribute("customOrderTotalMoney",customOrderTotalMoney);


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
        if (pageIndex==0){
            pageIndex=1;
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
        session.setAttribute("customid",customId);

        int countOrdersById = ordersDao.countOrdersById1(customId);
        System.out.println(countOrdersById);
        int size = 5;
        int row = countOrdersById % size == 0 ? (countOrdersById / size) : (countOrdersById / size + 1);
        System.out.println(row);
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
        if (pageIndex==0){
            pageIndex=1;
        }
        Pager<Orders> pager = new Pager<>();
        pager.setPage((pageIndex-1)*size);
        pager.setSize(size);
        pager.setTotal(countOrdersById);
        pager.setCustomId(customId);
        List<Orders> orderList=ordersDao.getAllOrdersById1(pager);
        request.getSession().setAttribute("row",row);
        request.getSession().setAttribute("pageIndex",pageIndex);
        request.getSession().setAttribute("countOrdersById",countOrdersById);
        request.getSession().setAttribute("orderList",orderList);

        return "redirect:statis/sales/salesView.jsp";
    }


    //入库统计
    @RequestMapping(value = "intoWarehouseStatis.do",method = RequestMethod.GET)
    public String intoWarehouse(HttpSession session,HttpServletRequest request){

        int countWarehouse = warehouseDao.countWarehouse();
        System.out.println(countWarehouse);
        int size = 5;
        int row = countWarehouse % size == 0 ? (countWarehouse / size) : (countWarehouse / size + 1);
        System.out.println(row);
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
        if (pageIndex==0){
            pageIndex=1;
        }
        Pager<Warehouse> pager = new Pager<>();
        pager.setPage((pageIndex-1)*size);
        pager.setSize(size);
        pager.setTotal(countWarehouse);
        List<Warehouse> warehouseList=warehouseDao.getAllWarehouseStatisPage(pager);
        request.getSession().setAttribute("row",row);
        request.getSession().setAttribute("pageIndex",pageIndex);
        request.getSession().setAttribute("countWarehouse",countWarehouse);
        request.getSession().setAttribute("warehouseList",warehouseList);

        List<Province> provinceList=provinceDao.getAllProvinces();
        session.setAttribute("provinceList",provinceList);
        List<City> cityList=cityDao.getAllCity();
        session.setAttribute("cityList",cityList);

        List<Warehouse> warehouseList1=warehouseDao.getAllWarehouse();
        ArrayList<String> warehouseNames=new ArrayList<>();
        ArrayList<Integer> rkwarehouseOrderCount=new ArrayList<>();
        ArrayList<Double> rkwarehouseOrderTotalMoney=new ArrayList<>();
        for (int i=0;i<warehouseList1.size();i++){
            warehouseNames.add("'"+warehouseList1.get(i).getName()+"'");
            List<RkWarehouse> rkWarehouseList=rkWarehouseDao.getAllRkWarehouse( warehouseList1.get(i).getId());
            double money=0;
            for (int j=0;j<rkWarehouseList.size();j++){
                money+=rkWarehouseList.get(j).getPurchase().getTotalMoney();
            }
            rkwarehouseOrderCount.add(rkWarehouseList.size());
            rkwarehouseOrderTotalMoney.add(money);
        }
        session.setAttribute("warehouseNames",warehouseNames);
        session.setAttribute("rkwarehouseOrderCount",rkwarehouseOrderCount);
        session.setAttribute("rkwarehouseOrderTotalMoney",rkwarehouseOrderTotalMoney);

        return "redirect:statis/stock/stockStatis.jsp";
    }
    //根据仓库获取入库详情集合
    @RequestMapping(value = "getRkWarehouseByWarehouseId.do",method = RequestMethod.GET)
    public String getRkWarehouseByWarehouseId(HttpServletRequest request,HttpSession session){
        int id=Integer.parseInt(request.getParameter("id"));
        session.setAttribute("warehouseId",id);

        int countRkWarehouse = rkWarehouseDao.countRkWarehouse(id);
        System.out.println(countRkWarehouse);
        int size = 5;
        int row = countRkWarehouse % size == 0 ? (countRkWarehouse / size) : (countRkWarehouse / size + 1);
        System.out.println(row);
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
        if (pageIndex==0){
            pageIndex=1;
        }
        Pager<RkWarehouse> pager = new Pager<>();
        pager.setPage((pageIndex-1)*size);
        pager.setSize(size);
        pager.setTotal(countRkWarehouse);
        pager.setWarehouseId(id);
        List<RkWarehouse> rkWarehouseList=rkWarehouseDao.getAllRkWarehousePage(pager);
        request.getSession().setAttribute("row",row);
        request.getSession().setAttribute("pageIndex",pageIndex);
        request.getSession().setAttribute("countRkWarehouse",countRkWarehouse);
        request.getSession().setAttribute("rkWarehouseList",rkWarehouseList);

        return "redirect:statis/stock/stockView.jsp";
    }


    //出库统计
    @RequestMapping(value = "outWarehouseStatis.do",method = RequestMethod.GET)
    public String outWarehouse(HttpSession session,HttpServletRequest request){
        int countWarehouse = warehouseDao.countWarehouse();
        System.out.println(countWarehouse);
        int size = 5;
        int row = countWarehouse % size == 0 ? (countWarehouse / size) : (countWarehouse / size + 1);
        System.out.println(row);
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
        if (pageIndex==0){
            pageIndex=1;
        }
        Pager<Warehouse> pager = new Pager<>();
        pager.setPage((pageIndex-1)*size);
        pager.setSize(size);
        pager.setTotal(countWarehouse);
        List<Warehouse> warehouseList=warehouseDao.getAllWarehouseStatisPage(pager);
        request.getSession().setAttribute("row",row);
        request.getSession().setAttribute("pageIndex",pageIndex);
        request.getSession().setAttribute("countWarehouse",countWarehouse);
        request.getSession().setAttribute("warehouseList",warehouseList);

        List<Province> provinceList=provinceDao.getAllProvinces();
        session.setAttribute("provinceList",provinceList);
        List<City> cityList=cityDao.getAllCity();
        session.setAttribute("cityList",cityList);

        List<Warehouse> warehouseList1=warehouseDao.getAllWarehouse();
        ArrayList<String> warehouseNames=new ArrayList<>();
        ArrayList<Integer> ckwarehouseOrderCount=new ArrayList<>();
        ArrayList<Double> ckwarehouseOrderTotalMoney=new ArrayList<>();
        for (int i=0;i<warehouseList1.size();i++){
            warehouseNames.add("'"+warehouseList1.get(i).getName()+"'");
            List<CkWarehouse> ckWarehouseList=ckWarehouseDao.getAllCkWarehouse( warehouseList1.get(i).getId());
            double money=0;
            for (int j=0;j<ckWarehouseList.size();j++){
                money+=ckWarehouseList.get(j).getOrder().getOrdermoney();
            }
            ckwarehouseOrderCount.add(ckWarehouseList.size());
            ckwarehouseOrderTotalMoney.add(money);
        }
        session.setAttribute("warehouseNames",warehouseNames);
        session.setAttribute("ckwarehouseOrderCount",ckwarehouseOrderCount);
        session.setAttribute("ckwarehouseOrderTotalMoney",ckwarehouseOrderTotalMoney);

        return "redirect:statis/delivery/deliveryStatis.jsp";
    }
    //根据仓库获取出库详情集合
    @RequestMapping(value = "getCkWarehouseByWarehouseId.do",method = RequestMethod.GET)
    public String getCkWarehouseByWarehouseId(HttpServletRequest request,HttpSession session){
        int id=Integer.parseInt(request.getParameter("id"));
        session.setAttribute("warehouseId",id);

        int countCkWarehouse = ckWarehouseDao.countCkWarehouse(id);
        System.out.println(countCkWarehouse);
        int size = 5;
        int row = countCkWarehouse % size == 0 ? (countCkWarehouse / size) : (countCkWarehouse / size + 1);
        System.out.println(row);
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
        if (pageIndex==0){
            pageIndex=1;
        }
        Pager<CkWarehouse> pager = new Pager<>();
        pager.setPage((pageIndex-1)*size);
        pager.setSize(size);
        pager.setTotal(countCkWarehouse);
        pager.setWarehouseId(id);
        List<CkWarehouse> ckWarehouseList=ckWarehouseDao.getAllCkWarehousePage(pager);
        request.getSession().setAttribute("row",row);
        request.getSession().setAttribute("pageIndex",pageIndex);
        request.getSession().setAttribute("countCkWarehouse",countCkWarehouse);
        request.getSession().setAttribute("ckWarehouseList",ckWarehouseList);

        return "redirect:statis/delivery/deliveryView.jsp";
    }
}
