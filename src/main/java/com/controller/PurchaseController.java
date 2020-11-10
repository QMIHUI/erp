package com.controller;

import com.bean.Purchase;
import com.bean.Users;
import com.dao.PurchaseDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
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



}
