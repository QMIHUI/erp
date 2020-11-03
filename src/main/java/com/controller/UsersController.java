package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


/**
 * @author HUI
 * @create 2020-11-03 15:42
 */
@Controller
public class UsersController {

    @RequestMapping(value = "login.do",method = RequestMethod.POST)
    public String login(){
        System.out.println("执行登录！！！");

        return "main";
    }


}
