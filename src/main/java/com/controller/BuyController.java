package com.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.bean.City;
import com.bean.Firm;
import com.bean.Province;
import com.bean.Users;
import com.dao.CityDao;
import com.dao.FirmDao;
import com.dao.ProvinceDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

/**
 * @author psqing
 * @create 2020-11-04 16:12
 */
@Controller
public class BuyController {
    @Autowired
    public FirmDao firmDao;
    @Autowired
    public ProvinceDao provinceDao;
    @Autowired
    public CityDao cityDao;

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
        int id=Integer.parseInt(request.getParameter("id"));
        int num=firmDao.delFirm(id);
        if (num>0){
            System.out.println("删除成功");
        }else{
            System.out.println("删除失败");
        }
        return "redirect:getAllFirm.do";
    }
    @RequestMapping(value = "goToAddFirm.do",method = RequestMethod.GET)
    public String goToAddFirm(HttpSession session){
        List<Province> provinceList=provinceDao.getAllProvinces();
        session.setAttribute("provinceList",provinceList);
        int provinceId=provinceList.get(0).getId();
        List<City> cityList=cityDao.getAllCitiesByProvinceId(provinceId);
        session.setAttribute("cityList",cityList);
        return "redirect:purchase/manufacturer/manufacturerAdd.jsp";
    }
    //ajax省市两级联动
    @RequestMapping(value = "getAllCitiesByProvinceId.do",method = RequestMethod.POST)
    public @ResponseBody List<City> getAllCitiesByProvinceId(HttpServletRequest request, String id){
        int id1=Integer.parseInt(id);
        List<City> cityList=cityDao.getAllCitiesByProvinceId(id1);
        return cityList;
    }
    //添加供应商
    @RequestMapping(value = "addFirm.do",method = RequestMethod.POST)
    public void addFirm(HttpSession session, HttpServletRequest request, HttpServletResponse response){
        String firmName=request.getParameter("firmName");
        String firmTel=request.getParameter("firmTel");
        String firmAddress=request.getParameter("firmAddress");
        String firmContent=request.getParameter("firmContent");
        int c_id=Integer.parseInt(request.getParameter("city"));
        Date createTime=new Date();
        int createId=((Users)session.getAttribute("user")).getuId();
        int status=Integer.parseInt(request.getParameter("status"));
        String firmFounder=request.getParameter("firmFounder");
        int num=firmDao.addFirm(firmName,firmTel,firmAddress,firmContent,c_id,createTime,createId,status,firmFounder);
        PrintWriter out=null;
        try {
            out=response.getWriter();

        } catch (IOException e) {
            e.printStackTrace();
        }
        if (num>0){
            List<Firm> firmList=firmDao.getAllFrims();
            session.setAttribute("firmList",firmList);
            out.print("<script type='text/javaScript'>alert('添加成功！');window.location.href='purchase/manufacturer/manufacturerList.jsp'</script>");
            out.flush();
        }else {
            out.print("<script type='text/javaScript'>alert('添加失败！');window.location.href='purchase/manufacturer/manufacturerList.jsp'</script>");
            out.flush();
        }
    }
    //修改供应商
    @RequestMapping(value = "firmUpdate.do",method = RequestMethod.GET)
    public String firmUpdate(HttpSession session,HttpServletRequest request){
        int firmId=Integer.parseInt(request.getParameter("id"));
        Firm firm=firmDao.getFirmById(firmId);
        session.setAttribute("firm",firm);
        List<Province> provinceList=provinceDao.getAllProvinces();
        session.setAttribute("provinceList",provinceList);
        int provinceId=firm.getCity().getProvince().getId();
        List<City> cityList=cityDao.getAllCitiesByProvinceId(provinceId);
        session.setAttribute("cityList",cityList);
        return "redirect:purchase/manufacturer/manufacturerUpdate.jsp";
    }
    @RequestMapping(value = "firmDoUpdate.do",method = RequestMethod.POST)
    public void firmDoUpdate(HttpSession session,HttpServletRequest request,HttpServletResponse response){
        int firmId=Integer.parseInt(request.getParameter("id"));
        String firmName=request.getParameter("firmName");
        String firmTel=request.getParameter("firmTel");
        String firmAddress=request.getParameter("firmAddress");
        String firmContent=request.getParameter("firmContent");
        int c_id=Integer.parseInt(request.getParameter("city"));
        int createId=((Users)session.getAttribute("user")).getuId();
        int status=Integer.parseInt(request.getParameter("status"));
        String firmFounder=request.getParameter("firmFounder");
        int num=firmDao.updateFirm(firmName,firmTel,firmAddress,firmContent,c_id,createId,status,firmFounder,firmId);
        PrintWriter out=null;
        try {
            out=response.getWriter();

        } catch (IOException e) {
            e.printStackTrace();
        }
        if (num>0){
            List<Firm> firmList=firmDao.getAllFrims();
            session.setAttribute("firmList",firmList);
            out.print("<script type='text/javaScript'>alert('修改成功！');window.location.href='purchase/manufacturer/manufacturerList.jsp'</script>");
            out.flush();
        }else {
            out.print("<script type='text/javaScript'>alert('修改失败！');window.location.href='purchase/manufacturer/manufacturerList.jsp'</script>");
            out.flush();
        }
    }

}
