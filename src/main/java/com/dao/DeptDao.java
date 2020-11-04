package com.dao;

import com.bean.Dept;

import java.util.List;

public interface DeptDao {
    //查询所有部门
    public List<Dept> getAllDept();

}