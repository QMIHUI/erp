package com.controller;

import com.bean.*;
import com.dao.*;
import com.util.Pager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author psqing
 * @create 2020-11-10 16:50
 */
@Controller
public class PurchaseController {
    @Autowired
    public PurchaseDao purchaseDao;
    @Autowired
    public BrandDao brandDao;
    @Autowired
    public TypeDao typeDao;
    @Autowired
    public ProductDao productDao;
    @Autowired
    public FirmDao firmDao;
    @Autowired
    public WarehouseDao warehouseDao;
    @Autowired
    public DetailsDao detailsDao;
    //根据职务查找采购单
    @RequestMapping(value = "getAllPurchases.do",method = RequestMethod.GET)
    public String getAllPurchases(HttpSession session,HttpServletRequest request){

        Users user=(Users)session.getAttribute("user");
        if (user.getJobId()==1||user.getJobId()==7){

            int countPurchase = purchaseDao.countPurchaseAll();
            System.out.println(countPurchase);
            int size = 5;
            int row = countPurchase % size == 0 ? (countPurchase / size) : (countPurchase / size + 1);
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
            Pager<Purchase> pager = new Pager<>();
            if (pageIndex==0){
                pageIndex=1;
            }
            pager.setPage((pageIndex-1)*size);
            pager.setSize(size);
            pager.setTotal(countPurchase);
            List<Purchase> purchaseList=purchaseDao.getAllPurchasesPage(pager);
            request.getSession().setAttribute("row",row);
            request.getSession().setAttribute("pageIndex",pageIndex);
            System.out.println("pageIndex"+pageIndex);
            request.getSession().setAttribute("countPurchase",countPurchase);
            request.getSession().setAttribute("purchaseList",purchaseList);


        }else {

            int countPurchase = purchaseDao.countPurchasesByCreateId(user.getuId());
            System.out.println(countPurchase);
            int size = 5;
            int row = countPurchase % size == 0 ? (countPurchase / size) : (countPurchase / size + 1);
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
            Pager<Purchase> pager = new Pager<>();
            if (pageIndex==0){
                pageIndex=1;
            }
            pager.setPage((pageIndex-1)*size);
            pager.setSize(size);
            pager.setTotal(countPurchase);
            pager.setCreateId(user.getuId());
            List<Purchase> purchaseList=purchaseDao.getPurchasesByCreateIdPage(pager);
            request.getSession().setAttribute("row",row);
            request.getSession().setAttribute("pageIndex",pageIndex);
            System.out.println("pageIndex"+pageIndex);
            request.getSession().setAttribute("countPurchase",countPurchase);
            request.getSession().setAttribute("purchaseList",purchaseList);

        }
        return "redirect:purchase/purchase/purchaseList.jsp";
    }
    //添加采购单
    @RequestMapping(value = "toAddPurchase.do",method = RequestMethod.GET)
    public String toAddPurchase(HttpSession session){
        List<Brand> brandList=brandDao.getAllBrandsUseful();
        session.setAttribute("brandList",brandList);
        List<Type> typeList=typeDao.getTypeListByBrandId(brandList.get(0).getBrandId());
        session.setAttribute("typeList",typeList);
        List<Product> productList=productDao.getProductsByTypeId(typeList.get(0).getTypeId());
        session.setAttribute("productList",productList);
        List<Warehouse> warehouseList=warehouseDao.getAllWarehouse();
        session.setAttribute("warehouseList",warehouseList);
        //生成采购单编号
        String purchaseId="";
        Calendar purchaseTime=Calendar.getInstance();
        String year=""+purchaseTime.get(Calendar.YEAR);
        String month=""+(purchaseTime.get(Calendar.MONTH) + 1);
        String day=""+purchaseTime.get(Calendar.DAY_OF_MONTH);
        String date=year+month+day;
        List<Purchase> purchaseList=purchaseDao.getAllPurchases();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        int count=0;
        int max=0;
        for (int i=0;i<purchaseList.size();i++){
            String purchaseDate=formatter.format(purchaseList.get(i).getPurchaseTime());
            if (purchaseDate.equals(date)){
                int index=Integer.parseInt(purchaseList.get(i).getPurchaseId().substring(10));
                if (index>max){
                    max=index;
                }
                count++;
            }
        }
        if (count==0){
            purchaseId="CG"+date+"0001";
        }else {
            String index=String.format("%04d", max+1);
            purchaseId="CG"+date+index;
        }
            session.setAttribute("purchaseIdAdd",purchaseId);
        return "redirect:purchase/purchase/purchaseAdd.jsp";
    }
    @RequestMapping(value = "addPurchase.do",method = RequestMethod.POST)
    public String addPurchase(HttpServletRequest request,HttpSession session,String purchaseId,String warehouse,String purchaseTotalMoney,String[] product,String[] count,String[] productPrice,String[] productTotalMoney){
        System.out.println("执行添加");
        Date purchaseTime=new Date();
        int createId=((Users)session.getAttribute("user")).getuId();
        int warehouseId=Integer.parseInt(warehouse);
        double totalMoney=Double.parseDouble(purchaseTotalMoney);
        purchaseDao.addPurchase(purchaseId,createId,purchaseTime,warehouseId,totalMoney);
        //生成订购单详情
        for (int i=0;i<product.length;i++){
            int countNum=detailsDao.getCountDetailsIdByConditions(Integer.parseInt(product[i]),purchaseId);
            if (countNum==0){
                detailsDao.addPurchaseDetails(purchaseId,Integer.parseInt(count[i]),Integer.parseInt(product[i]),Double.parseDouble(productPrice[i]),Double.parseDouble(productTotalMoney[i]));
            }else {
                int detailsId=detailsDao.getDetailsIdByConditions(Integer.parseInt(product[i]),purchaseId);
                int countBefore=detailsDao.getCountByConditions(Integer.parseInt(product[i]),purchaseId);
                double moneyBefore=detailsDao.getMoneyByConditions(Integer.parseInt(product[i]),purchaseId);
                int countAfter=countBefore+Integer.parseInt(count[i]);
                double moneyAfter=moneyBefore+Double.parseDouble(productTotalMoney[i]);
                detailsDao.updateDetails(detailsId,countAfter,moneyAfter);
            }
        }
        System.out.println("采购单生成成功");

        return "redirect:getAllPurchases.do";
    }
    @RequestMapping(value = "updatePurchase.do",method = RequestMethod.GET)
    public String updatePurchase(HttpServletRequest request,HttpSession session){
        List<Brand> brandList=brandDao.getAllBrandsUseful();
        session.setAttribute("brandList",brandList);
        List<Type> typeList=typeDao.getTypeListByBrandId(brandList.get(0).getBrandId());
        session.setAttribute("typeList",typeList);
        List<Product> productList=productDao.getProductsByTypeId(typeList.get(0).getTypeId());
        session.setAttribute("productList",productList);

        String id=request.getParameter("id");
        Purchase purchase=purchaseDao.getPurchaseById(id);
        session.setAttribute("purchase",purchase);
        List<Details> detailsList=purchase.getDetailsList();
        List<Type>[] typeLists=new List[detailsList.size()];
        List<Product>[] productLists=new List[detailsList.size()];
        for (int i=0;i<detailsList.size();i++){
            int brandId=detailsList.get(i).getProduct().getType().getBrand().getBrandId();
            typeLists[i]=typeDao.getTypeListByBrandId(brandId);
            int tyId=detailsList.get(i).getProduct().getType().getTypeId();
            productLists[i]=productDao.getProductsByTypeId(tyId);
        }
        session.setAttribute("typeLists",typeLists);
        session.setAttribute("productLists",productLists);


        return "redirect:purchase/purchase/purchaseUpdate.jsp";
    }
    @RequestMapping(value = "doUpdatePurchase.do",method = RequestMethod.POST)
    public String doUpdatePurchase(HttpServletRequest request,HttpSession session,String purchaseId,String purchaseTotalMoney,String[] product,String[] count,String[] productPrice,String[] productTotalMoney,String[] detailsId){
        System.out.println("执行修改");
        double totalMoney=Double.parseDouble(purchaseTotalMoney);
        purchaseDao.updatePurchase(purchaseId,totalMoney);
        System.out.println("长度"+product.length);
        detailsDao.delDetails(purchaseId);
        //生成采购单详情
        for (int i=0;i<product.length;i++){
            int countNum=detailsDao.getCountDetailsIdByConditions(Integer.parseInt(product[i]),purchaseId);
            if (countNum==0){
                detailsDao.addPurchaseDetails(purchaseId,Integer.parseInt(count[i]),Integer.parseInt(product[i]),Double.parseDouble(productPrice[i]),Double.parseDouble(productTotalMoney[i]));
            }else {
                int detailsId1=detailsDao.getDetailsIdByConditions(Integer.parseInt(product[i]),purchaseId);
                int countBefore=detailsDao.getCountByConditions(Integer.parseInt(product[i]),purchaseId);
                double moneyBefore=detailsDao.getMoneyByConditions(Integer.parseInt(product[i]),purchaseId);
                int countAfter=countBefore+Integer.parseInt(count[i]);
                double moneyAfter=moneyBefore+Double.parseDouble(productTotalMoney[i]);
                detailsDao.updateDetails(detailsId1,countAfter,moneyAfter);
            }
        }
        System.out.println("采购单修改成功");

        return "redirect:getAllPurchases.do";
    }
    //ajaxL品牌类型商品三级联动
    @RequestMapping(value = "getAllTypesByBrandIdPurchase.do",method =RequestMethod.GET)
    public @ResponseBody
    Map getAllTypesByBrandId(HttpServletRequest request, String id){
        int id1=Integer.parseInt(id);
        List<Type> typeList=typeDao.getTypeListByBrandId(id1);
        List<Product> productList=productDao.getProductsByTypeId(typeList.get(0).getTypeId());
        Map map = new HashMap();
        map.put("typeList",typeList);
        map.put("productList",productList);
        return map;
    }
    @RequestMapping(value = "getAllProductsByTypeIdPurchase.do",method =RequestMethod.GET)
    public @ResponseBody
    Map getAllProductsByTypeIdPurchase(HttpServletRequest request, String id){
        int id1=Integer.parseInt(id);
        List<Product> productList=productDao.getProductsByTypeId(id1);
        Map map = new HashMap();
        map.put("productList",productList);
        return map;
    }
    @RequestMapping(value = "getProductByIdPurchase.do",method =RequestMethod.GET)
    public @ResponseBody
    Product getProductByIdPurchase(HttpServletRequest request, String id){
        int id1=Integer.parseInt(id);
        Product product=productDao.getProductById(id1);
        return product;
    }


    //删除采购单
    @RequestMapping(value = "delPurchase.do",method = RequestMethod.GET)
    public String delPurchase(HttpServletRequest request){
        String id=request.getParameter("id");
        purchaseDao.deletePurchaseDetails(id);
        purchaseDao.deletePurchase(id);
        return "redirect:getAllPurchases.do";
    }
    //采购单提交审核
    @RequestMapping(value = "submitPurchase.do",method = RequestMethod.GET)
    public void submitPurchase(HttpServletRequest request, HttpServletResponse response,HttpSession session){
        String id=request.getParameter("id");
        int num=purchaseDao.submitPurchase(id);
        PrintWriter out=null;
        try {
            out=response.getWriter();

        } catch (IOException e) {
            e.printStackTrace();
        }
        if (num>0){
            Users user=(Users)session.getAttribute("user");
            if (user.getJobId()==1||user.getJobId()==7){
                List<Purchase> purchaseList=purchaseDao.getAllPurchases();
                session.setAttribute("purchaseList",purchaseList);
            }else {
                List<Purchase> purchaseList=purchaseDao.getPurchasesByCreateId(user.getuId());
                session.setAttribute("purchaseList",purchaseList);
            }
            out.print("<script type='text/javaScript'>alert('提交成功！');window.location.href='purchase/purchase/purchaseList.jsp'</script>");
            out.flush();
        }else {
            out.print("<script type='text/javaScript'>alert('提交失败！');window.location.href='purchase/purchase/purchaseList.jsp'</script>");
            out.flush();
        }
    }
    //查看采购单详情
    @RequestMapping(value = "purchaseView.do",method = RequestMethod.GET)
    public String purchaseView(HttpServletRequest request,HttpSession session){
        String id=request.getParameter("id");
        Purchase purchase=purchaseDao.getPurchaseById(id);
        session.setAttribute("purchase",purchase);
        return "redirect:purchase/purchase/purchaseView.jsp";
    }
    //获取所有审核中的采购单
    @RequestMapping(value = "getAllPurchaseStatus2.do",method = RequestMethod.GET)
    public String getAllPurchaseStatus2(HttpSession session,HttpServletRequest request){

        int countPurchaseStatus2 = purchaseDao.countPurchaseStatus2();
        System.out.println(countPurchaseStatus2);
        int size = 5;
        int row = countPurchaseStatus2 % size == 0 ? (countPurchaseStatus2 / size) : (countPurchaseStatus2 / size + 1);
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
        Pager<Purchase> pager = new Pager<>();
        if (pageIndex==0){
            pageIndex=1;
        }
        pager.setPage((pageIndex-1)*size);
        pager.setSize(size);
        pager.setTotal(countPurchaseStatus2);
        List<Purchase> purchaseList=purchaseDao.getAllPurchaseStatus2(pager);
        request.getSession().setAttribute("row",row);
        request.getSession().setAttribute("pageIndex",pageIndex);
        System.out.println("pageIndex"+pageIndex);
        request.getSession().setAttribute("countPurchaseStatus2",countPurchaseStatus2);
        request.getSession().setAttribute("purchaseList",purchaseList);

        return "redirect:purchase/purchaseExamine/purchaseExamineList.jsp";
    }
    //采购单审核
    @RequestMapping(value = "toPurchaseExamine.do",method = RequestMethod.GET)
    public String toPurchaseExamine(HttpServletRequest request,HttpSession session){
        String id=request.getParameter("id");
        Purchase purchase=purchaseDao.getPurchaseById(id);
        session.setAttribute("purchase",purchase);
        return "redirect:purchase/purchaseExamine/purchaseExamine.jsp";
    }
    @RequestMapping(value = "purchaseExamine.do",method = RequestMethod.POST)
    public void purchaseExamine(HttpServletRequest request,HttpServletResponse response,HttpSession session){
        String id=request.getParameter("id");
        int checkId=((Users)session.getAttribute("user")).getuId();
        String checkOpinion=request.getParameter("checkOpinion");
        Date checkTime=new Date();
        int checkStatus=Integer.parseInt(request.getParameter("checkStatus"));
        int num=purchaseDao.purchaseExamine(checkId,checkTime,checkOpinion,checkStatus,id);
        //入库
        PrintWriter out=null;
        try {
            out=response.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (num>0){

            int countPurchaseStatus2 = purchaseDao.countPurchaseStatus2();
            System.out.println(countPurchaseStatus2);
            int size = 5;
            int row = countPurchaseStatus2 % size == 0 ? (countPurchaseStatus2 / size) : (countPurchaseStatus2 / size + 1);
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
            Pager<Purchase> pager = new Pager<>();
            pager.setPage((pageIndex-1)*size);
            pager.setSize(size);
            pager.setTotal(countPurchaseStatus2);
            List<Purchase> purchaseList=purchaseDao.getAllPurchaseStatus2(pager);
            request.getSession().setAttribute("row",row);
            request.getSession().setAttribute("pageIndex",pageIndex);
            request.getSession().setAttribute("countPurchaseStatus2",countPurchaseStatus2);
            request.getSession().setAttribute("purchaseList",purchaseList);

            out.print("<script type='text/javaScript'>alert('审核成功！');window.location.href='purchase/purchaseExamine/purchaseExamineList.jsp'</script>");
            out.flush();
        }else {
            out.print("<script type='text/javaScript'>alert('审核失败！');window.location.href='purchase/purchaseExamine/purchaseExamineList.jsp'</script>");
            out.flush();
        }

    }


}
