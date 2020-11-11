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
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author HUI
 * @create 2020-11-09 17:03
 */
@Controller
public class CustomController {
    @Autowired
    private CustomDao customDao;
    @Autowired
    public ProvinceDao provinceDao;
    @Autowired
    public CityDao cityDao;
    @Autowired
    private JournalDao journalDao;
    @Autowired
    private UsersDao usersDao;

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
        List<Province> provinceList=provinceDao.getAllProvinces();
        request.getSession().setAttribute("provinceList",provinceList);
        request.getSession().setAttribute("listCustom",listCustom);
        request.getSession().setAttribute("countCust",countCust);
        request.getSession().setAttribute("pageIndex",pageIndex);
        request.getSession().setAttribute("rowCust",rowCust);
        return "market/customer/customerList";
    }

    @RequestMapping(value = "addCustomer.do",method = RequestMethod.GET)
    public String addCustomer(HttpServletRequest request){
        System.out.println("添加顾客");
        String custName = request.getParameter("custName");
        String custSex = request.getParameter("custSex");
        String custTelephone = request.getParameter("custTelephone");
        String custCompany = request.getParameter("custCompany");
        int custProvince = Integer.parseInt(request.getParameter("custProvince"));
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        String creationDate = format.format(date);
        int uId = Integer.parseInt(request.getParameter("uId"));
        String custDesc = request.getParameter("custDesc");
        String custHomeAddress = request.getParameter("custHomeAddress");
        Custom custom = new Custom(custName,custSex,custTelephone,custCompany,custProvince,creationDate,uId,custDesc,custHomeAddress);
        int num = customDao.addCustomer(custom);
        //添加日志
        String jcontent = "添加顾客";
        Journal journal = new Journal(jcontent,creationDate,custName,uId);
        int n = journalDao.addJournal(journal);
        if(num>0 && n>0){
            return "forward:queryAllCustom.do";
        }else{
            return "redirect:market/customer/customerAdd.jsp";
        }
    }

    @RequestMapping(value = "getOneCust.do",method = RequestMethod.GET)
    public String getOneCust(HttpServletRequest request, HttpSession session){
        System.out.println("根据顾客ID查找顾客");
        String op = request.getParameter("op");
        System.out.println(op);
        int customId = Integer.parseInt(request.getParameter("customId"));
        Custom custom = customDao.getOneCustom(customId);
        request.getSession().setAttribute("custom",custom);
        List<Province> provinceList=provinceDao.getAllProvinces();
        session.setAttribute("provinceList",provinceList);
        int provinceId=provinceList.get(0).getId();
        List<City> cityList=cityDao.getAllCitiesByProvinceId(provinceId);
        session.setAttribute("cityList",cityList);
        if(op.equals("查看")){
            return "market/customer/customerView";
        }else{
            return "market/customer/customerUpdate";
        }

    }

    @RequestMapping(value = "getCitiesByProvinceId.do",method = RequestMethod.POST)
    public @ResponseBody
    List<City> getAllCitiesByProvinceId(HttpServletRequest request, String id){
        System.out.println("222");
        int id1=Integer.parseInt(id);
        List<City> cityList=cityDao.getAllCitiesByProvinceId(id1);
        return cityList;
    }

    @RequestMapping(value = "updateCust.do",method = RequestMethod.GET)
    public String updateCust(HttpServletRequest request){
        System.out.println("执行修改顾客！！!");
        int custId = Integer.parseInt(request.getParameter("custId"));
        String custName = request.getParameter("custName");
        String custSex = request.getParameter("custSex");
        String custCompany = request.getParameter("custCompany");
        String custTel = request.getParameter("custTel");
        String custHomeAddress = request.getParameter("custHomeAddress");
        int province = Integer.parseInt(request.getParameter("province"));
        String desc = request.getParameter("desc");
        int status = Integer.parseInt(request.getParameter("status"));
        Custom custom = new Custom(custId,custName,custSex,custTel,custCompany,province,status,desc,custHomeAddress);
        int num = customDao.updateCustom(custom);
        //添加日志
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        String creationDate = format.format(date);
        int uId = Integer.parseInt(request.getParameter("uId"));
        String jcontent = "修改顾客";
        Journal journal = new Journal(jcontent,creationDate,custName,uId);
        int n = journalDao.addJournal(journal);
        if(num>0 && n>0){
            return "forward:queryAllCustom.do";
        }else{
            return "redirect:market/customer/customerUpdate.jsp";
        }
    }

    @RequestMapping(value = "distributionCust.do",method = RequestMethod.GET)
    public String distributionCust(HttpServletRequest request){
        System.out.println("分配客户！！！");
        int custId = Integer.parseInt(request.getParameter("custId"));
        System.out.println("custId:"+custId);
        Custom custom = customDao.getOneCustom(custId);
        request.getSession().setAttribute("custom",custom);
        List<Users> listUsersMarket = usersDao.getUsersByDid(2);
        request.getSession().setAttribute("listUsersMarket",listUsersMarket);
        return "market/customer/distinctCstom";
    }

    @RequestMapping(value = "distrctCustSucc.do",method = RequestMethod.GET)
    public String distrctCustSucc(HttpServletRequest request){
        System.out.println("执行分配成功！！！");
        int custId = Integer.parseInt(request.getParameter("custId"));
        int luId = Integer.parseInt(request.getParameter("luId"));
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        String creationDate = format.format(date);
        Custom custom = new Custom(custId,luId,creationDate);
        //添加日志
        int uId = Integer.parseInt(request.getParameter("uId"));
        String jcontent = "分配客户";
        String custName = request.getParameter("custName");
        Journal journal = new Journal(jcontent,creationDate,custName,uId);
        int num = customDao.distinctCust(custom);
        int n = journalDao.addJournal(journal);
        if(num>0 && n>0){
            return "forward:queryAllCustom.do";
        }else{
            return "redirect:market/customer/distinctCstom.jsp";
        }
    }

    @RequestMapping(value = "cancelCust.do",method = RequestMethod.GET)
    public String cancelCust(HttpServletRequest request){
        System.out.println("注销顾客！！！");
        int custId = Integer.parseInt(request.getParameter("custId"));
        System.out.println("custId:"+custId);
        int num = customDao.cancelCust(custId);
        return "forward:queryAllCustom.do";
    }

    @RequestMapping(value = "recoverCust.do",method = RequestMethod.GET)
    public String recoverCust(HttpServletRequest request){
        System.out.println("恢复顾客！！！");
        int custId = Integer.parseInt(request.getParameter("custId"));
        System.out.println("custId:"+custId);
        int num = customDao.recoverCust(custId);
        return "forward:queryAllCustom.do";
    }

    @RequestMapping(value = "getCustomersByCon.do",method = RequestMethod.GET)
    public String getCustomersByCon(HttpServletRequest request){
        System.out.println("执行模糊查询顾客");
        String custCom = request.getParameter("custCom");
        String custName = request.getParameter("custName");
        int province = Integer.parseInt(request.getParameter("province"));
        int cstatus = Integer.parseInt(request.getParameter("cstatus"));
        System.out.println(custCom+"..."+custName+"..."+province+"..."+cstatus);
        Map<String,Object> map = new HashMap<>();
        map.put("company",custCom);
        map.put("customname",custName);
        map.put("address",province);
        map.put("cstatus",cstatus);
        int countCustByCon = customDao.countCustByCon(map);
        System.out.println(countCustByCon);
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
        Pager<Custom> pager = new Pager<>();
        pager.setPage((pageIndex-1)*size);
        pager.setSize(size);
        pager.setTotal(countCustByCon);
        pager.setCompany(custCom);
        pager.setCstatus(cstatus);
        pager.setCustomname(custName);
        pager.setAddress(province);
        List<Custom> listCustomByCon = customDao.getCustomByCon(pager);
        request.getSession().setAttribute("listCustomByCon",listCustomByCon);
        request.getSession().setAttribute("countCustByCon",countCustByCon);
        request.getSession().setAttribute("pageIndex",pageIndex);
        request.getSession().setAttribute("rowCustByCon",rowCustByCon);
        request.getSession().setAttribute("custCom",custCom);
        request.getSession().setAttribute("custName",custName);
        request.getSession().setAttribute("cstatus",cstatus);
        request.getSession().setAttribute("province",province);
        return "market/customer/customerListByCon";
    }



}
