package com.dao;

import com.bean.Custom;
import com.util.Pager;

import java.util.List;

public interface CustomDao {
    //查询所有并分页
    public List<Custom> getAllCustom(Pager<Custom> pager);
    //查询所有顾客数量
    public int countCustom();

}