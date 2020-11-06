package com.controller;

import com.bean.Firm;
import com.dao.FirmDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
}
