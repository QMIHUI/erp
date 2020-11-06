package com.dao;

import com.bean.Dept;
import com.bean.Job;
import com.util.Pager;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DeptDao {
    //查询所有部门
    public List<Dept> getAllDept();
    //分页查询所有部门
    public List<Dept> getDeptByPager(Pager<Dept> pager);
    //查询部门个数
    public int countDept();
    //增加部门
    public int addDept(Dept dept);
    //根据ID查找部门
    public Dept getOneDept(int did);
    //修改部门
    public int updateDept(Dept dept);
    //注销部门
    public int forbiddenDept(int did);
    //恢复部门
    public int recoverDept(int did);
    //模糊查询
    public List<Dept> getDeptByName(Pager<Dept> pager);
    //模糊查询的个数
    public int countDeptByName(String deptName);



}