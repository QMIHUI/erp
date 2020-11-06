package com.controller;

import com.bean.Dept;
import com.bean.Job;
import com.bean.Users;
import com.dao.DeptDao;
import com.dao.JobDao;
import com.dao.UsersDao;
import com.util.Pager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


/**
 * @author HUI
 * @create 2020-11-03 15:42
 */
@Controller
public class UsersController {
    @Autowired
    private UsersDao usersDao;
    @Autowired
    private DeptDao deptDao;
    @Autowired
    private JobDao jobDao;

    //登录
    @RequestMapping(value = "login.do",method = RequestMethod.POST)
    public String login(HttpServletRequest request){
        System.out.println("执行登录！！！");
        String uname = request.getParameter("uname");
        System.out.println(uname);
        String upassword = request.getParameter("upassword");
        System.out.println(upassword);
        Users users = new Users(uname,upassword);
        Users user = usersDao.login(users);
        request.getSession().setAttribute("user",user);
        System.out.println(user);
        if(user!=null){
            return "main";
        }else{
            return "login";
        }
    }

    @RequestMapping(value = "queryAllUser.do",method = RequestMethod.GET)
    public String queryAllUser(HttpServletRequest request){
        int countUsers = usersDao.countUsers();
        System.out.println(countUsers);
        int size = 5;
        int row = countUsers % size == 0 ? (countUsers / size) : (countUsers / size + 1);
        System.out.println(row);
        System.out.println("执行查询所有用户");
        String currentIndex= request.getParameter("pageIndex");
        //第一次访问(当前页码=1)
        int pageIndex = 1;
        if(currentIndex!=null) {
            pageIndex=Integer.parseInt(currentIndex);
        }
        if(currentIndex==null || Integer.parseInt(currentIndex) <= 0){
            pageIndex = 1;
        }else if(Integer.parseInt(currentIndex) >= row){
            pageIndex=row;
        }
        Pager<Users> pager = new Pager<>();
        pager.setPage((pageIndex-1)*size);
        pager.setSize(size);
        pager.setTotal(countUsers);
        List<Users> listUsers = usersDao.getAllUsersByPage(pager);
        List<Dept> listDept = deptDao.getAllDept();
        List<Job> listJob = jobDao.getAllJob();
        request.getSession().setAttribute("listDept",listDept);
        request.getSession().setAttribute("listJob",listJob);
        request.getSession().setAttribute("row",row);
        request.getSession().setAttribute("pageIndex",pageIndex);
        request.getSession().setAttribute("countUsers",countUsers);
        request.getSession().setAttribute("listUsers",listUsers);
        return "sys/users/userList";
    }

    /*@RequestMapping(value="getJobByDeptId.do",method = RequestMethod.GET)
    public String GetEmployeeByDepartmentId(@RequestBody int deptId){
        System.out.println("执行根据部门id查找job");
        List<Job> listJob=jobDao.getJobByDid(deptId);
        return listJob.toString();
    }*/

    @RequestMapping(value = "addUser.do",method = RequestMethod.GET)
    public String addUser(){
        System.out.println("执行增加用户");
        return "";
    }

    @RequestMapping(value = "selectOneUser.do",method = RequestMethod.GET)
    public String selectOne(HttpServletRequest request){
        System.out.println("执行查找单个！！！");
        int uid =Integer.parseInt(request.getParameter("uid"));
        System.out.println(uid);
        Users user = usersDao.getOneUser(uid);
        request.getSession().setAttribute("user",user);
        return "sys/users/userUpdate";
    }






}
