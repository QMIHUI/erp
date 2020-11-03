package com.controller;

import com.bean.Users;
import com.dao.UsersDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;


/**
 * @author HUI
 * @create 2020-11-03 15:42
 */
@Controller
public class UsersController {
    @Autowired
    private UsersDao usersDao;

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
        if(user.getDeptId()==1){
            return "main";
        }else if(user.getDeptId()==2){
            return "main";
        }else if(user.getDeptId()==3){
            return "main";
        }else if(user.getDeptId()==4){
            return "main";
        }else{
            return "main";
        }

    }


}