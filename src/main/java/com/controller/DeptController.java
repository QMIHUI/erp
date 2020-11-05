package com.controller;

import com.bean.Dept;
import com.bean.Users;
import com.dao.DeptDao;
import com.util.Pager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author HUI
 * @create 2020-11-05 15:52
 */
@Controller
public class DeptController {
    @Autowired
    private DeptDao deptDao;

    @RequestMapping(value = "queryAllDept.do",method = RequestMethod.GET)
    public String getDeptByPager(HttpServletRequest request){
        System.out.println("分页查询所有部门！！!");
        int countDept = deptDao.countDept();
        System.out.println(countDept);
        int size = 5;
        int rowDept = countDept % size == 0 ? (countDept / size) : (countDept / size + 1);
        System.out.println(rowDept);
        String currentIndex= request.getParameter("pageIndex");
        //第一次访问(当前页码=1)
        int pageIndex = 1;
        if(currentIndex!=null) {
            pageIndex=Integer.parseInt(currentIndex);
        }
        if(currentIndex==null || Integer.parseInt(currentIndex) <= 0){
            pageIndex = 1;
        }else if(Integer.parseInt(currentIndex) >= rowDept){
            pageIndex=rowDept;
        }
        Pager<Dept> pager = new Pager<>();
        pager.setPage((pageIndex-1)*size);
        pager.setSize(size);
        pager.setTotal(countDept);
        List<Dept> listDeptPager = deptDao.getDeptByPager(pager);
        request.getSession().setAttribute("listDeptPager",listDeptPager);
        request.getSession().setAttribute("countDept",countDept);
        request.getSession().setAttribute("rowDept",rowDept);
        request.getSession().setAttribute("pageIndex",pageIndex);
        return "sys/dept/deptList";
    }

    @RequestMapping(value = "addDept.do",method = RequestMethod.GET)
    public String addDept(HttpServletRequest request){
        System.out.println("执行增加部门！！！");
        String deptName = request.getParameter("deptName");
        System.out.println(deptName);
        Dept dept = new Dept(deptName);
        int num = deptDao.addDept(dept);
        if(num>0){
            return "forward:queryAllDept.do";
        }else{
            return "redirect:sys/dept/deptAdd.jsp";
        }
    }

    @RequestMapping(value = "getOneDept.do",method = RequestMethod.GET)
    public String getOneDept(HttpServletRequest request){
        System.out.println("执行根据id查找部门！！！");
        int did = Integer.parseInt(request.getParameter("did"));
        System.out.println(did);
        Dept dept = deptDao.getOneDept(did);
        request.getSession().setAttribute("dept",dept);
        return "sys/dept/deptUpdate";
    }

    @RequestMapping(value = "updateDept.do",method = RequestMethod.GET)
    public String updateDept(HttpServletRequest request){
        System.out.println("执行修改部门！！！");
        int deptId = Integer.parseInt(request.getParameter("deptId"));
        String deptName = request.getParameter("deptName");
        Dept dept = new Dept(deptId,deptName);
        int num = deptDao.updateDept(dept);
        if(num > 0){
            return "forward:queryAllDept.do";
        }else{
            return "redirect:sys/dept/deptUpdate.jsp";
        }
    }




}
