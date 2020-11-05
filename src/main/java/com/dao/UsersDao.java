package com.dao;

import com.bean.Users;
import com.util.Pager;

import java.util.List;

public interface UsersDao {
    //登录
    public Users login(Users user);
    //查看所有用户
    public List<Users> getAllUsersByPage(Pager<Users> pager);
    //查询用户个数
    public int countUsers();
    //根据id查找用户
    public Users getOneUser(int uid);



}