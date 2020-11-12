package com.controller;

import com.bean.Custom;
import com.bean.Province;
import com.bean.Users;
import com.dao.CustomDao;
import com.dao.ProvinceDao;
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
 * @author HUI
 * @create 2020-11-10 20:46
 */
@Controller
public class CustBroController {

    @Autowired
    private CustomDao customDao;
    @Autowired
    private ProvinceDao provinceDao;


    @RequestMapping(value = "getAllCustomerBro.do",method = RequestMethod.GET)
    public String getAllCustomerBro(HttpServletRequest request, HttpSession session){
        Users user=(Users)session.getAttribute("user");
        List<Province> provinceList=provinceDao.getAllProvinces();
        request.getSession().setAttribute("provinceList",provinceList);
        if (user.getJobId()==1||user.getJobId()==2){
            System.out.println("执行所有客户浏览");
            int countCustNotNull = customDao.countCustom();
            System.out.println(countCustNotNull);
            int size = 5;
            int rowCustNotNull = countCustNotNull % size == 0 ? (countCustNotNull / size) : (countCustNotNull / size + 1);
            System.out.println(rowCustNotNull);
            String currentIndex= request.getParameter("pageIndex");
            //第一次访问(当前页码=1)
            int pageIndex = 1;
            if(currentIndex!=null) {
                pageIndex=Integer.parseInt(currentIndex);
            }
            if(currentIndex==null || Integer.parseInt(currentIndex) <= 0){
                pageIndex = 1;
            }else if(Integer.parseInt(currentIndex) >= rowCustNotNull){
                pageIndex=rowCustNotNull;
            }
            Pager<Custom> pager = new Pager<>();
            pager.setPage((pageIndex-1)*size);
            pager.setSize(size);
            pager.setTotal(countCustNotNull);
            List<Custom> listCustomNotNull = customDao.getAllCustom(pager);
            request.getSession().setAttribute("listCustomNotNull",listCustomNotNull);
            request.getSession().setAttribute("countCustNotNull",countCustNotNull);
            request.getSession().setAttribute("pageIndex",pageIndex);
            request.getSession().setAttribute("rowCustNotNull",rowCustNotNull);
        }else{
            System.out.println("执行个人所有客户浏览");
            int countCustNotNull = customDao.countCustomById(user.getuId());
            System.out.println(countCustNotNull);
            int size = 5;
            int rowCustNotNull = countCustNotNull % size == 0 ? (countCustNotNull / size) : (countCustNotNull / size + 1);
            System.out.println(rowCustNotNull);
            String currentIndex= request.getParameter("pageIndex");
            //第一次访问(当前页码=1)
            int pageIndex = 1;
            if(currentIndex!=null) {
                pageIndex=Integer.parseInt(currentIndex);
            }
            if(currentIndex==null || Integer.parseInt(currentIndex) <= 0){
                pageIndex = 1;
            }else if(Integer.parseInt(currentIndex) >= rowCustNotNull){
                pageIndex=rowCustNotNull;
            }
            Pager<Custom> pager = new Pager<>();
            pager.setPage((pageIndex-1)*size);
            pager.setSize(size);
            pager.setTotal(countCustNotNull);
            pager.setLeading(user.getuId());
            List<Custom> listCustomNotNull = customDao.getAllCustomById(pager);
            request.getSession().setAttribute("listCustomNotNull",listCustomNotNull);
            request.getSession().setAttribute("countCustNotNull",countCustNotNull);
            request.getSession().setAttribute("pageIndex",pageIndex);
            request.getSession().setAttribute("rowCustNotNull",rowCustNotNull);
        }
        return "market/customerBrowse/customerBrowse";
    }

    @RequestMapping(value = "getCustBroByCon.do",method = RequestMethod.GET)
    public String getCustBroByCon(HttpServletRequest request,HttpSession session){
        Users user=(Users)session.getAttribute("user");
        if (user.getJobId()==1||user.getJobId()==2){
            System.out.println("所有客户浏览模糊查询！！！");
            String custCom = request.getParameter("custCom");
            String custName = request.getParameter("custName");
            int province = Integer.parseInt(request.getParameter("province"));
            System.out.println(custCom+"..."+custName+"..."+province);
            Map<String,Object> map = new HashMap<>();
            map.put("company",custCom);
            map.put("customname",custName);
            map.put("address",province);
            int countCustBroByCon = customDao.countCustBroByCon(map);
            System.out.println(countCustBroByCon);
            int size = 5;
            int rowCustBroByCon = countCustBroByCon % size == 0 ? (countCustBroByCon / size) : (countCustBroByCon / size + 1);
            System.out.println(rowCustBroByCon);
            String currentIndex= request.getParameter("pageIndex");
            //第一次访问(当前页码=1)
            int pageIndex = 1;
            if(currentIndex!=null) {
                pageIndex=Integer.parseInt(currentIndex);
            }
            if(currentIndex==null || Integer.parseInt(currentIndex) <= 0){
                pageIndex = 1;
            }else if(Integer.parseInt(currentIndex) >= rowCustBroByCon){
                pageIndex=rowCustBroByCon;
            }
            Pager<Custom> pager = new Pager<>();
            pager.setPage((pageIndex-1)*size);
            pager.setSize(size);
            pager.setTotal(countCustBroByCon);
            pager.setCompany(custCom);
            pager.setCustomname(custName);
            pager.setAddress(province);
            List<Custom> listCustomBroByCon = customDao.getCustomBroByCon(pager);
            request.getSession().setAttribute("listCustomBroByCon",listCustomBroByCon);
            request.getSession().setAttribute("countCustBroByCon",countCustBroByCon);
            request.getSession().setAttribute("pageIndex",pageIndex);
            request.getSession().setAttribute("rowCustBroByCon",rowCustBroByCon);
            request.getSession().setAttribute("custCom",custCom);
            request.getSession().setAttribute("custName",custName);
            request.getSession().setAttribute("province",province);
        }else{
            System.out.println("个人所有客户浏览模糊查询！！！");
            int uId = user.getuId();
            System.out.println(uId);
            String custCom = request.getParameter("custCom");
            String custName = request.getParameter("custName");
            int province = Integer.parseInt(request.getParameter("province"));
            System.out.println(custCom+"..."+custName+"..."+province);
            Map<String,Object> map = new HashMap<>();
            map.put("company",custCom);
            map.put("customname",custName);
            map.put("address",province);
            map.put("leading",uId);
            int countCustBroByCon = customDao.countCustBroByCon(map);
            System.out.println(countCustBroByCon);
            int size = 5;
            int rowCustBroByCon = countCustBroByCon % size == 0 ? (countCustBroByCon / size) : (countCustBroByCon / size + 1);
            System.out.println(rowCustBroByCon);
            String currentIndex= request.getParameter("pageIndex");
            //第一次访问(当前页码=1)
            int pageIndex = 1;
            if(currentIndex!=null) {
                pageIndex=Integer.parseInt(currentIndex);
            }
            if(currentIndex==null || Integer.parseInt(currentIndex) <= 0){
                pageIndex = 1;
            }else if(Integer.parseInt(currentIndex) >= rowCustBroByCon){
                pageIndex=rowCustBroByCon;
            }
            Pager<Custom> pager = new Pager<>();
            pager.setPage((pageIndex-1)*size);
            pager.setSize(size);
            pager.setTotal(countCustBroByCon);
            pager.setCompany(custCom);
            pager.setCustomname(custName);
            pager.setAddress(province);
            pager.setLeading(uId);
            List<Custom> listCustomBroByCon = customDao.getCustomBroByCon(pager);
            request.getSession().setAttribute("listCustomBroByCon",listCustomBroByCon);
            request.getSession().setAttribute("countCustBroByCon",countCustBroByCon);
            request.getSession().setAttribute("pageIndex",pageIndex);
            request.getSession().setAttribute("rowCustBroByCon",rowCustBroByCon);
            request.getSession().setAttribute("custCom",custCom);
            request.getSession().setAttribute("custName",custName);
            request.getSession().setAttribute("province",province);
            request.getSession().setAttribute("uId",uId);
        }

        return "market/customerBrowse/customerBrowseByCon";
    }

}
