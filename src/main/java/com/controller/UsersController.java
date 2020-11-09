package com.controller;

import com.bean.*;
import com.dao.DeptDao;
import com.dao.JobDao;
import com.dao.JournalDao;
import com.dao.UsersDao;
import com.util.Pager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


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
    @Autowired
    private JournalDao journalDao;

    //登录
    @RequestMapping(value = "login.do",method = RequestMethod.POST)
    public String login(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("执行登录！！！");
        //设置编码格式
        request.setCharacterEncoding("utf-8"); // 1
        response.setContentType("text/html;charset=utf-8"); // 2
        response.setCharacterEncoding("utf-8"); // 3
        String uname = request.getParameter("uname");
        System.out.println(uname);
        String upassword = request.getParameter("upassword");
        System.out.println(upassword);
        Users users = new Users(uname,upassword);
        Users user = usersDao.login(users);
        request.getSession().setAttribute("user",user);
        System.out.println(user);
        PrintWriter out = response.getWriter();
        if(user!=null){
            if(user.getStatus()==1){
                if(user.getDept().getDeptState().equals("正常")){
                    return "main";
                }else{
                    out.print("<script>alert('您账号的部门为注销状态！！！');location.href='login.jsp'</script>");
                    out.flush();
                    return "login";
                }
            }else{
                out.print("<script>alert('此账号为注销状态！！！');location.href='login.jsp'</script>");
                out.flush();
                return "login";
            }
        }else{
            out.print("<script>alert('用户名或密码输入错误！！！');location.href='login.jsp'</script>");
            out.flush();
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
        //日志的信息
        int uId = Integer.parseInt(request.getParameter("uId"));
        String jcontent = "添加用户";

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
        Journal journal = new Journal(jcontent,creationDate,name,uId);
        int n = journalDao.addJournal(journal);
        if(num>0 && n>0){
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
        Users users = usersDao.getOneUser(uid);
        request.getSession().setAttribute("users",users);
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
        String uname = request.getParameter("uname");
        int uId = Integer.parseInt(request.getParameter("uId"));
        System.out.println(uname+"...."+uId);
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
        int uId = Integer.parseInt(request.getParameter("uId"));
        String jcontent = "修改用户";
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        String creationDate = format.format(date);
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
        Journal journal = new Journal(jcontent,creationDate,uname,uId);
        int n = journalDao.addJournal(journal);
        System.out.println(num);
        if(num>0 && n>0){
            return "forward:queryAllUser.do";
        }else{
            return "redirect:sys/users/userUpdate.jsp";
        }

    }

    @RequestMapping(value = "getUsersByCon.do",method = RequestMethod.GET)
    public String getUsersByCon(HttpServletRequest request){
        System.out.println("执行条件查询查找用户！！！");
        /*int uid = Integer.parseInt(request.getParameter("uid"));
        System.out.println("uid"+uid);*/
        String uname = request.getParameter("uname");
        int deptId = Integer.parseInt(request.getParameter("deptId"));
        int status = Integer.parseInt(request.getParameter("status"));
        Map<String,Object> map = new HashMap<>();
        /*map.put("uId",uid);*/
        map.put("uname",uname);
        map.put("deptId",deptId);
        map.put("status",status);
        int countUserByCon = usersDao.countUsersBycon(map);
        System.out.println("总个数："+countUserByCon);
        int size = 5;
        int rowJobByCon = countUserByCon % size == 0 ? (countUserByCon / size) : (countUserByCon / size + 1);
        System.out.println("总页数："+rowJobByCon);
        String currentIndex= request.getParameter("pageIndex");
        //第一次访问(当前页码=1)
        int pageIndex = 1;
        if(currentIndex!=null) {
            pageIndex=Integer.parseInt(currentIndex);
        }
        if(currentIndex==null || Integer.parseInt(currentIndex) <= 0){
            pageIndex = 1;
        }else if(Integer.parseInt(currentIndex) >= rowJobByCon){
            pageIndex=rowJobByCon;
        }
        Pager<Users> pager = new Pager<>();
        pager.setPage((pageIndex-1)*size);
        pager.setSize(size);
        pager.setTotal(countUserByCon);
       /* pager.setuId(uid);*/
        pager.setUname(uname);
        pager.setDeptId(deptId);
        pager.setStatus(status);
        List<Users> listUsersByCon = usersDao.getUsersByCon(pager);
        request.getSession().setAttribute("listUsersByCon",listUsersByCon);
        request.getSession().setAttribute("pageIndex",pageIndex);
        request.getSession().setAttribute("rowJobByCon",rowJobByCon);
        request.getSession().setAttribute("countUserByCon",countUserByCon);
        /*request.getSession().setAttribute("uid",uid);*/
        request.getSession().setAttribute("uname",uname);
        request.getSession().setAttribute("deptId",deptId);
        request.getSession().setAttribute("status",status);
        return "sys/users/userListByCon";
    }














}
