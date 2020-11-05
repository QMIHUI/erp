package com.controller;

import com.bean.Firm;
import com.bean.Purchase;
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
        return "redirect:statis/purchase/purchaseView.jsp";
    }
}
