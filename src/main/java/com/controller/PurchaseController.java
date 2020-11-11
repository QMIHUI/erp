package com.controller;

import com.bean.Purchase;
import com.bean.Type;
import com.bean.Users;
import com.dao.PurchaseDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

/**
 * @author psqing
 * @create 2020-11-10 16:50
 */
@Controller
public class PurchaseController {
    @Autowired
    public PurchaseDao purchaseDao;

    //根据职务查找采购单
    @RequestMapping(value = "getAllPurchases.do",method = RequestMethod.GET)
    public String getAllPurchases(HttpSession session){
        Users user=(Users)session.getAttribute("user");
        if (user.getJobId()==1||user.getJobId()==7){
            List<Purchase> purchaseList=purchaseDao.getAllPurchases();
            session.setAttribute("purchaseList",purchaseList);
        }else {
            List<Purchase> purchaseList=purchaseDao.getPurchasesByCreateId(user.getuId());
            session.setAttribute("purchaseList",purchaseList);
        }
        return "redirect:purchase/purchase/purchaseList.jsp";
    }
    //添加采购单
    @RequestMapping(value = "toAddPurchase.do",method = RequestMethod.GET)
    public String toAddPurchase(){



        return "redirect:purchase/purchase/purchaseAdd.jsp";
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
    public String getAllPurchaseStatus2(HttpSession session){
        List<Purchase> purchaseList2=purchaseDao.getAllPurchaseStatus2();
        session.setAttribute("purchaseList2",purchaseList2);
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
            List<Purchase> purchaseList2=purchaseDao.getAllPurchaseStatus2();
            session.setAttribute("purchaseList2",purchaseList2);
            out.print("<script type='text/javaScript'>alert('审核成功！');window.location.href='purchase/purchaseExamine/purchaseExamineList.jsp'</script>");
            out.flush();
        }else {
            out.print("<script type='text/javaScript'>alert('审核失败！');window.location.href='purchase/purchaseExamine/purchaseExamineList.jsp'</script>");
            out.flush();
        }

    }


}
