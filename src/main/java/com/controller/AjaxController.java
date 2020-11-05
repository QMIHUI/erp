package com.controller;

import com.bean.Dept;
import com.dao.DeptDao;
import com.dao.JobDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author HUI
 * @create 2020-11-05 9:47
 */
/*@RestController
public class AjaxController {
    @Autowired
    private DeptDao deptDao;
    @Autowired
    private JobDao jobDao;

    @RequestMapping(value = "getAllDept.do",method = RequestMethod.GET)
    @ResponseBody
    public Object getAllDept(){
        System.out.println("执行查询所有部门！！！");
        List<Dept> list = deptDao.getAllDept();
        return list;
    }




}*/
