package com.controller;

import com.bean.Firm;
import com.dao.FirmDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author psqing
 * @create 2020-11-04 16:12
 */
@Controller
public class BuyController {
    @Autowired
    public FirmDao firmDao;

    @RequestMapping(value = "getAllFirm.do",method = RequestMethod.GET)
    public String getAllFirms(HttpSession session){
        List<Firm> firmList=firmDao.getAllFrims();
        session.setAttribute("firmList",firmList);
        return "redirect:purchase/manufacturer/manufacturerList.jsp";
    }
    @RequestMapping(value = "getFirmDetails.do",method = RequestMethod.GET)
    public String getFirmDetails(HttpServletRequest request,HttpSession session){
        int id=Integer.parseInt(request.getParameter("id"));
        Firm firm=firmDao.getFirmById(id);
        session.setAttribute("firm",firm);
        return "redirect:purchase/manufacturer/manufacturerView.jsp";
    }
    @RequestMapping(value = "delFirm.do",method = RequestMethod.GET)
    public String delFirm(HttpServletRequest request,HttpSession session){
        System.out.println("执行del（）");
        int id=Integer.parseInt(request.getParameter("id"));
        System.out.println(id);





        List<Firm> firmList=firmDao.getAllFrims();
        session.setAttribute("firmList",firmList);
        return "redirect:purchase/manufacturer/manufacturerList.jsp";
    }
}
