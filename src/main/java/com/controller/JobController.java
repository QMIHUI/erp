package com.controller;

import com.bean.Dept;
import com.bean.Job;
import com.dao.DeptDao;
import com.dao.JobDao;
import com.util.Pager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author HUI
 * @create 2020-11-06 10:37
 */
@Controller
public class JobController {
    @Autowired
    private JobDao jobDao;
    @Autowired
    private DeptDao deptDao;

    @RequestMapping(value = "queryAllJob",method = RequestMethod.GET)
    public String getAllJob(HttpServletRequest request){
        System.out.println("执行查询所有职位！！！");
        int countJob = jobDao.countJob();
        System.out.println(countJob);
        int size = 5;
        int rowJob = countJob % size == 0 ? (countJob / size) : (countJob / size + 1);
        System.out.println(rowJob);
        String currentIndex= request.getParameter("pageIndex");
        //第一次访问(当前页码=1)
        int pageIndex = 1;
        if(currentIndex!=null) {
            pageIndex=Integer.parseInt(currentIndex);
        }
        if(currentIndex==null || Integer.parseInt(currentIndex) <= 0){
            pageIndex = 1;
        }else if(Integer.parseInt(currentIndex) >= rowJob){
            pageIndex=rowJob;
        }
        Pager<Job> pager = new Pager<>();
        pager.setPage((pageIndex-1)*size);
        pager.setSize(size);
        pager.setTotal(countJob);
        List<Job> listJob = jobDao.getAllJob(pager);
        List<Dept> listDept = deptDao.getAllDept();
        request.getSession().setAttribute("listDept",listDept);
        request.getSession().setAttribute("listJob",listJob);
        request.getSession().setAttribute("countJob",countJob);
        request.getSession().setAttribute("pageIndex",pageIndex);
        request.getSession().setAttribute("rowJob",rowJob);
        return "sys/dept/positionList";
    }


}
