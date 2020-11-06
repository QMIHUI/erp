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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public String queryAllJob(HttpServletRequest request){
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

    @RequestMapping(value = "addJob.do",method = RequestMethod.GET)
    public String addJob(HttpServletRequest request){
        System.out.println("执行添加职位！！！");
        String jobName = request.getParameter("jobName");
        int deptId = Integer.parseInt(request.getParameter("deptId"));
        Job job = new Job(jobName,deptId);
        System.out.println(jobName);
        System.out.println(deptId);
        int num = jobDao.addJob(job);
        System.out.println(num);
        if(num>0){
            return "forward:queryAllJob.do";
        }else{
            return "redirect:sys/dept/positionAdd.jsp";
        }
    }

    @RequestMapping(value = "getOneJob.do",method = RequestMethod.GET)
    public String getOneJob(HttpServletRequest request){
        System.out.println("根据职位id查找职位");
        int jid = Integer.parseInt(request.getParameter("jid"));
        System.out.println(jid);
        Job job = jobDao.getOneJob(jid);
        request.getSession().setAttribute("job",job);
        return "sys/dept/positionUpdate";
    }

    @RequestMapping(value = "updateJob.do",method = RequestMethod.GET)
    public String updateJob(HttpServletRequest request){
        System.out.println("执行修改职位！！！");
        int jobId = Integer.parseInt(request.getParameter("jobId"));
        String jobName = request.getParameter("jobName");
        int deptId = Integer.parseInt(request.getParameter("deptId"));
        Job job = new Job(jobId,jobName,deptId);
        int num = jobDao.updateJob(job);
        System.out.println(num);
        if(num>0){
            return "forward:queryAllJob.do";
        }else{
            return "redirect:sys/dept/positionUpdate.jsp";
        }
    }

    @RequestMapping(value = "cancelJob.do",method = RequestMethod.GET)
    public String cancelJob(HttpServletRequest request){
        System.out.println("注销职位！！！");
        int jid = Integer.parseInt(request.getParameter("jid"));
        int num = jobDao.cancelJob(jid);
        if(num>0){
            return "forward:queryAllJob.do";
        }else{
            return "forward:queryAllJob.do";
        }
    }

    @RequestMapping(value = "recoverJob.do",method = RequestMethod.GET)
    public String recoverJob(HttpServletRequest request){
        System.out.println("恢复职位！！！");
        int jid = Integer.parseInt(request.getParameter("jid"));
        int num = jobDao.recoverJob(jid);
        if(num>0){
            return "forward:queryAllJob.do";
        }else{
            return "forward:queryAllJob.do";
        }
    }

    @RequestMapping(value = "getJobByCon.do",method = RequestMethod.GET)
    public String getJobByCon(HttpServletRequest request){
        System.out.println("根据条件查询职位");
        String jobName = request.getParameter("jobName");
        int deptId = Integer.parseInt(request.getParameter("deptId"));
        System.out.println(jobName+"///"+deptId);
        Map<String,Object> map = new HashMap<>();
        map.put("jobName",jobName);
        map.put("jobDeptId",deptId);
        int countJobByCon = jobDao.countJobByCon(map);
        System.out.println(countJobByCon);
        return "";
    }



}
