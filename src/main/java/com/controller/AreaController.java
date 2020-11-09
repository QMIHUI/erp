package com.controller;

import com.bean.City;
import com.bean.Province;
import com.dao.CityDao;
import com.dao.ProvinceDao;
import com.util.Pager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author HUI
 * @create 2020-11-08 12:22
 */
@Controller
public class AreaController {
    @Autowired
    private CityDao cityDao;
    @Autowired
    private ProvinceDao provinceDao;

    @RequestMapping(value = "queryAllProCity.do",method = RequestMethod.GET)
    public String queryAllProCity(HttpServletRequest request, HttpSession session){
        System.out.println("执行查询所有地区！！！");
        int countCity = cityDao.countCity();
        System.out.println("city个数"+countCity);
        int size=5;
        int rowCity = countCity % size == 0 ? (countCity / size) : (countCity / size + 1);
        System.out.println("页数"+rowCity);
        String currentIndex= request.getParameter("pageIndex");
        //第一次访问(当前页码=1)
        int pageIndex = 1;
        if(currentIndex!=null) {
            pageIndex=Integer.parseInt(currentIndex);
        }
        if(currentIndex==null || Integer.parseInt(currentIndex) <= 0){
            pageIndex = 1;
        }else if(Integer.parseInt(currentIndex) >= rowCity){
            pageIndex=rowCity;
        }
        Pager<City> pager = new Pager<>();
        pager.setPage((pageIndex-1)*size);
        pager.setSize(size);
        pager.setTotal(rowCity);
        List<City> listCity = cityDao.getAllProCity(pager);
        List<City> listC = cityDao.getAllCity();
        List<Province> listPro = provinceDao.getAllProvinces();
        session.setAttribute("listPro",listPro);
        session.setAttribute("listC",listC);
        session.setAttribute("listCity",listCity);
        session.setAttribute("rowCity",rowCity);
        session.setAttribute("pageIndex",pageIndex);
        session.setAttribute("countCity",countCity);
        return "sys/area/areaList";
    }

    @RequestMapping(value = "getProCityByCon.do",method = RequestMethod.GET)
    public String getProCityByCon(HttpServletRequest request,HttpSession session){
        System.out.println("执行条件查询查找区域！！！");
        int province = Integer.parseInt(request.getParameter("province"));
        int city = Integer.parseInt(request.getParameter("city"));
        System.out.println(province+"...."+city);
        int countByCon = cityDao.countCityByCon(province,city);
        System.out.println(countByCon);
        int size=5;
        int rowByCon = countByCon % size == 0 ? (countByCon / size) : (countByCon / size + 1);
        System.out.println("页数"+rowByCon);
        String currentIndex= request.getParameter("pageIndex");
        //第一次访问(当前页码=1)
        int pageIndex = 1;
        if(currentIndex!=null) {
            pageIndex=Integer.parseInt(currentIndex);
        }
        if(currentIndex==null || Integer.parseInt(currentIndex) <= 0){
            pageIndex = 1;
        }else if(Integer.parseInt(currentIndex) >= rowByCon){
            pageIndex=rowByCon;
        }
        Pager<City> pager = new Pager<>();
        pager.setPage((pageIndex-1)*size);
        pager.setSize(size);
        pager.setTotal(rowByCon);
        pager.setpId(province);
        pager.setcId(city);
        List<City> listCityByCon = cityDao.getProCityByCon(pager);
        session.setAttribute("listCityByCon",listCityByCon);
        session.setAttribute("rowByCon",rowByCon);
        session.setAttribute("pageIndex",pageIndex);
        session.setAttribute("countByCon",countByCon);
        session.setAttribute("province",province);
        session.setAttribute("city",city);
        return "sys/area/areaListByCon";
    }

}
