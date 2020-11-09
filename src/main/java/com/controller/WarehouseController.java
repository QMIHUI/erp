package com.controller;


import com.bean.Warehouse;
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

    @RequestMapping(value = "storageList.do",method = RequestMethod.GET)
    public String getAllWatehouse(HttpSession session){//查询所有
        System.out.println("sgsgdfgs");
        List<Warehouse> warehouseList=warehouseDao.getAllWarehouse();
        session.setAttribute("warehouseList",warehouseList);
        return "redirect:storage/storage/storageList.jsp";
    }


    @RequestMapping(value = "/{id}/storageView.do",method = RequestMethod.GET)
    public String storageView(@PathVariable("id") int id,HttpSession session){//根据id查询单个对象
        Warehouse warehouse=warehouseDao.selectByPrimaryKey(id);
        System.out.println(id);
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
        List<Warehouse> warehouseList=warehouseDao.getAllWarehouse();
        session.setAttribute("warehouseList",warehouseList);
        return "redirect:../../storage/storage/storageList.jsp";
    }




}
