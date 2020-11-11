package com.controller;


import com.bean.City;
import com.bean.Province;
import com.bean.Users;
import com.bean.Warehouse;
import com.dao.CityDao;
import com.dao.ProvinceDao;
import com.dao.UsersDao;
import com.dao.WarehouseDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class WarehouseController {
    @Autowired
    private WarehouseDao warehouseDao;
    @Autowired
    private CityDao cityDao;
    @Autowired
    private ProvinceDao provinceDao;
    @Autowired
    private UsersDao usersDao;

    @RequestMapping(value = "storageList.do",method = RequestMethod.GET)
    public String getAllWatehouse(HttpSession session){//查询所有
        List<Warehouse> warehouseList=warehouseDao.getAllWarehouse();
        session.setAttribute("warehouseList",warehouseList);
        return "redirect:storage/storage/storageList.jsp";
    }


    @RequestMapping(value = "/{id}/storageView.do",method = RequestMethod.GET)
    public String getWarehouse(@PathVariable("id") int id,HttpSession session){//根据id查询单个对象
        Warehouse warehouse=warehouseDao.selectByPrimaryKey(id);
        session.setAttribute("warehouse",warehouse);
        return "redirect:../storage/storage/storageView.jsp";
    }



    @RequestMapping(value = "/{id}/{state}/storageState.do",method = RequestMethod.GET)
    public String updateState(@PathVariable("id")int id,@PathVariable("state")int state,HttpSession session){//根据ID修改状态
        if(state==1){
            state=0;
        }else {
            state=1;
        }
        Warehouse warehouse=new Warehouse(id,state);
        int num=warehouseDao.updateWarehouseState(warehouse);
        List<Warehouse> warehouseList=warehouseDao.getAllWarehouse();//修改状态后的查询所有
        session.setAttribute("warehouseList",warehouseList);
        return "redirect:../../storage/storage/storageList.jsp";
    }

    @RequestMapping(value = "/{id}/storageUpdate.do",method = RequestMethod.GET)
    public String getWarehouseById(@PathVariable("id") int id,HttpSession session){//根据ID修改前的查询
        Warehouse warehouse=warehouseDao.selectByPrimaryKey(id);
        session.setAttribute("warehouse",warehouse);
        List<Province> listprovince=provinceDao.getAllProvinces();//查询所有省
        session.setAttribute("listprovince",listprovince);
        List<City> listcity=cityDao.getAllCityorWarehouse();//查询所有市
        session.setAttribute("listcity",listcity);
        List<Users> listusers=usersDao.getUsersByWarehouse();
        session.setAttribute("listusers",listusers);
        return "redirect:../storage/storage/storageUpdate.jsp";
    }
    @RequestMapping(value = "updateWarehouse.do",method = RequestMethod.POST)
    public String UpdateWarehouse(Warehouse warehouse,HttpSession session){
        System.out.println(warehouse);
        int num=warehouseDao.UpdateWarehouse(warehouse);
        List<Warehouse> warehouseList=warehouseDao.getAllWarehouse();//修改状态后的查询所有
        session.setAttribute("warehouseList",warehouseList);
        return "redirect:storage/storage/storageList.jsp";
    }

    @RequestMapping(value = "addSelectWarehouse.do",method = RequestMethod.GET)
    public String addselect(HttpSession session){//添加前的查询
        List<Province> listprovince=provinceDao.getAllProvinces();//查询所有省
        session.setAttribute("listprovince",listprovince);
        List<City> listcity=cityDao.getAllCityorWarehouse();//查询所有市
        session.setAttribute("listcity",listcity);
        List<Users> listusers=usersDao.getUsersByWarehouse();//查询所有仓库管理员
        session.setAttribute("listusers",listusers);
        return "redirect:storage/storage/storageAdd.jsp";
    }

    @RequestMapping(value = "addWarehouse.do",method = RequestMethod.POST)
    public String addwarehouse(HttpSession session,Warehouse warehouse){//添加仓库
        int num=warehouseDao.addWarehouse(warehouse);
        List<Warehouse> warehouseList=warehouseDao.getAllWarehouse();//修改状态后的查询所有
        session.setAttribute("warehouseList",warehouseList);
        return "redirect:storage/storage/storageList.jsp";
    }

    @RequestMapping(value = "/{uId}/storageBrowse.do",method = RequestMethod.GET)
    public String selectWarehouseByname(@PathVariable("uId") int uId,HttpSession session){//根据用户ID查询自己管理的仓库
        List<Warehouse> listWarehouse=warehouseDao.selectWarehouseByuid(uId);
        System.out.println(listWarehouse);
       if(listWarehouse.isEmpty()){
           listWarehouse=warehouseDao.getAllWarehouse();
       }
        session.setAttribute("listWarehouse",listWarehouse);
        return "redirect:../storage/storageBrowse/storageBrowse.jsp";
    }

}
