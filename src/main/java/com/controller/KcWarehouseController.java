package com.controller;

import com.bean.CkWarehouse;
import com.bean.KcWarehouse;
import com.dao.KcWarehouseDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class KcWarehouseController {
    @Autowired
    private KcWarehouseDao kcWarehouseDao;
    @RequestMapping(value = "/{uId}/inventoryList.do",method = RequestMethod.GET)
    public String getKcWarehouse(@PathVariable("uId") int uId, HttpSession session){
        List<KcWarehouse> listKcWarehouse=kcWarehouseDao.getKcWarehouseByuid(uId);
        if(listKcWarehouse.isEmpty()){
            listKcWarehouse=kcWarehouseDao.getKcWarehouse();
        }
        session.setAttribute("listKcWarehouse",listKcWarehouse);
        return "redirect:../storage/inventory/inventoryList.jsp";
    }
}
