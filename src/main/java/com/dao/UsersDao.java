package com.dao;

import com.bean.Users;
import com.util.Pager;

import java.util.List;
import java.util.Map;

public interface UsersDao {
    //登录
    public Users login(Users user);
    //查看所有用户
    public List<Users> getAllUsersByPage(Pager<Users> pager);
    //查询用户个数
    public int countUsers();
    //根据id查找用户
    public Users getOneUser(int uid);
    //增加用户
    public int addUser(Users user);
    //注销用户
    public int forbiddenUser(Users user);
    //恢复用户
    public int recoverUser(Users user);
    //修改用户
    public int updateUser(Users user);
    //条件查询
    public List<Users> getUsersByCon(Pager<Users> pager);
    //条件查询的个数
    public int countUsersBycon(Map<String,Object> map);
    //根据部门id查找用户
    public List<Users> getUsersByDid(int did);



}