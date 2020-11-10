package com.controller;

import com.bean.Custom;
import com.dao.CustomDao;
import com.util.Pager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author HUI
 * @create 2020-11-09 17:03
 */
@Controller
public class CustomController {
    @Autowired
    private CustomDao customDao;

    @RequestMapping(value = "queryAllCustom.do",method = RequestMethod.GET)
    public String queryAllCustom(HttpServletRequest request){
        System.out.println("执行查询所有");
        int countCust = customDao.countCustom();
        System.out.println(countCust);
        int size = 5;
        int rowCust = countCust % size == 0 ? (countCust / size) : (countCust / size + 1);
        System.out.println(rowCust);
        String currentIndex= request.getParameter("pageIndex");
        //第一次访问(当前页码=1)
        int pageIndex = 1;
        if(currentIndex!=null) {
            pageIndex=Integer.parseInt(currentIndex);
        }
        if(currentIndex==null || Integer.parseInt(currentIndex) <= 0){
            pageIndex = 1;
        }else if(Integer.parseInt(currentIndex) >= rowCust){
            pageIndex=rowCust;
        }
        Pager<Custom> pager = new Pager<>();
        pager.setPage((pageIndex-1)*size);
        pager.setSize(size);
        pager.setTotal(countCust);
        List<Custom> listCustom = customDao.getAllCustom(pager);
        request.getSession().setAttribute("listCustom",listCustom);
        request.getSession().setAttribute("countCust",countCust);
        request.getSession().setAttribute("pageIndex",pageIndex);
        request.getSession().setAttribute("rowCust",rowCust);
        return "market/customer/customerList";
    }

    @RequestMapping(value = "getOneCust.do",method = RequestMethod.GET)
    public String getOneCust(HttpServletRequest request){
        System.out.println("根据顾客ID查找顾客");
        String op = request.getParameter("op");
        System.out.println(op);
        int customId = Integer.parseInt(request.getParameter("customId"));
        Custom custom = customDao.getOneCustom(customId);
        request.getSession().setAttribute("custom",custom);
        if(op.equals("查看")){
            return "market/customer/customerView";
        }else{

        }
        return "market/customer/customerUpdate";
    }



}
