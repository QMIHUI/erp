package com.controller;

import com.bean.Firm;
import com.dao.DeptDao;
import com.dao.FirmDao;
import com.dao.JobDao;
import com.dao.UsersDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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

    @RequestMapping(value = "getAllFirms.do",method = RequestMethod.GET)
    public String getAllFirms(HttpSession session){
        List<Firm> firmList=firmDao.getAllFrims();
        session.setAttribute("firmList",firmList);
        return "redirect:statis/purchase/purchaseStatis.jsp";
    }
}
