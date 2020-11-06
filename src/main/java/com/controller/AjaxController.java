package com.controller;

import com.alibaba.fastjson.JSONArray;
import com.bean.Dept;
import com.bean.Job;
import com.dao.DeptDao;
import com.dao.JobDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author HUI
 * @create 2020-11-05 9:47
 */
@RestController
@RequestMapping("deptJob")
public class AjaxController {
    @Autowired
    private DeptDao deptDao;
    @Autowired
    private JobDao jobDao;

    @RequestMapping(value="getJobByDeptId.do",method = RequestMethod.GET)
    public String getJobByDeptId(@RequestBody int deptId){
        System.out.println("执行根据部门id查找job");
        System.out.println(deptId);
        /*List<Job> listJob=jobDao.getJobByDid(deptId);
        return listJob.toString();*/
        return "";
    }

    @RequestMapping(value = "test.do",method = RequestMethod.GET)
    public String test(){
        System.out.println("测试！！！");
        return "";
    }




}
