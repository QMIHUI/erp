package com.controller;

import com.bean.Users;
import com.dao.UsersDao;
import com.util.Pager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
        System.out.println("执行查询所有用户");
        String currentIndex= request.getParameter("pageIndex");
        //第一次访问(当前页码=1)
        int pageIndex = 1;
        int size = 5;
        if(currentIndex!=null) {
            System.out.println("111");
            pageIndex=Integer.parseInt(currentIndex);
        }
        int countUsers = usersDao.countUsers();
        System.out.println(countUsers);
        Pager<Users> pager = new Pager<>();
        pager.setPage((pageIndex-1)*size);
        pager.setSize(size);
        pager.setTotal(countUsers);
        List<Users> listUsers = usersDao.getAllUsersByPage(pager);
        int row = countUsers % size == 0 ? (countUsers / size) : (countUsers / size + 1);
        System.out.println(row);
        request.getSession().setAttribute("row",row);
        request.getSession().setAttribute("countUsers",countUsers);
        request.getSession().setAttribute("listUsers",listUsers);
        return "sys/users/userList";
    }




}
