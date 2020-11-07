package com.controller;

import com.bean.City;
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
import java.text.SimpleDateFormat;
import java.util.Date;
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
        System.out.println("分页查询所有！！！");
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
        List<Job> listJob = jobDao.getAllJob01();
        request.getSession().setAttribute("listDept",listDept);
        request.getSession().setAttribute("listJob",listJob);
        request.getSession().setAttribute("row",row);
        request.getSession().setAttribute("pageIndex",pageIndex);
        request.getSession().setAttribute("countUsers",countUsers);
        request.getSession().setAttribute("listUsers",listUsers);
        return "sys/users/userList";
    }

    @RequestMapping(value="getJobByDeptId.do",method = RequestMethod.POST)
    public @ResponseBody List<Job> getJobByDeptId(HttpServletRequest request){
        System.out.println("执行根据部门id查找job");
        int did = Integer.parseInt(request.getParameter("did"));
        System.out.println(did);
        List<Job> listJob=jobDao.getJobByDid(did);
        listJob.forEach((e)-> System.out.println(e));
        return listJob;
    }

    @RequestMapping(value = "addUser.do",method = RequestMethod.GET)
    public String addUser(HttpServletRequest request){
        System.out.println("执行增加用户");
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        String telephone = request.getParameter("telephone");
        int deptId = Integer.parseInt(request.getParameter("deptId"));
        int jobId = Integer.parseInt(request.getParameter("jobId"));
        String gender = request.getParameter("gender");
        String birthday = request.getParameter("birthday");
        System.out.println(name+"///"+password+"///"+telephone+"///"+deptId+"///"+jobId+"///"+gender+"///"+birthday);
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        String creationDate = format.format(date);
        Users user = new Users(name,password,telephone,deptId,jobId,gender,creationDate,birthday);
        int num = usersDao.addUser(user);
        if(num>0){
            return "forward:queryAllUser.do";
        }else{
            return "redirect:sys/users/userAdd.jsp";
        }
    }

    @RequestMapping(value = "selectOneUser.do",method = RequestMethod.GET)
    public String selectOne(HttpServletRequest request){
        System.out.println("执行查找单个！！！");
        int uid =Integer.parseInt(request.getParameter("uid"));
        System.out.println(uid);
        int did = Integer.parseInt(request.getParameter("did"));
        List<Job> listJobByDid = jobDao.getJobByDid(did);
        request.getSession().setAttribute("listJobByDid",listJobByDid);
        Users user = usersDao.getOneUser(uid);
        request.getSession().setAttribute("user",user);
        return "sys/users/userUpdate";
    }

    @RequestMapping(value = "forbiddenUser.do",method = RequestMethod.GET)
    public String forbiddenUser(HttpServletRequest request){
        System.out.println("注销用户！！！");
        int uid = Integer.parseInt(request.getParameter("uid"));
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        String leaveDate = format.format(date);
        Users user = new Users(uid,leaveDate);
        int num = usersDao.forbiddenUser(user);
        return "forward:queryAllUser.do";
    }

    @RequestMapping(value = "recoverUser.do",method = RequestMethod.GET)
    public String recoverUser(HttpServletRequest request){
        System.out.println("恢复被注销的用户！！！");
        int uid = Integer.parseInt(request.getParameter("uid"));
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        String createDate = format.format(date);
        String leaveDate = "";
        Users user = new Users(uid,createDate,leaveDate);
        int num = usersDao.recoverUser(user);
        return "forward:queryAllUser.do";
    }

    @RequestMapping(value = "updateUser.do",method = RequestMethod.GET)
    public String updateUser(HttpServletRequest request){
        System.out.println("执行修改用户！！！");
        int uid = Integer.parseInt(request.getParameter("uid"));
        String uname = request.getParameter("uname");
        String upwd = request.getParameter("upwd");
        String utelephone = request.getParameter("utelephone");
        int deptId = Integer.parseInt(request.getParameter("deptId"));
        int jobId = Integer.parseInt(request.getParameter("jobId"));
        String gender = request.getParameter("gender");
        System.out.println(gender);
        String birthday = request.getParameter("birthday");
        Users user = new Users(uid,uname,upwd,utelephone,deptId,jobId,gender,birthday);
        int num = usersDao.updateUser(user);
        System.out.println(num);
        if(num>0){
            return "forward:queryAllUser.do";
        }else{
            return "redirect:sys/users/userUpdate.jsp";
        }

    }











}
