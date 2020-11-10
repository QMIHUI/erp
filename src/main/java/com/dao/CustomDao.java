package com.dao;

import com.bean.Custom;
import com.util.Pager;

import java.util.List;
import java.util.Map;

public interface CustomDao {
    //查询所有并分页
    public List<Custom> getAllCustom(Pager<Custom> pager);
    //查询所有顾客数量
    public int countCustom();
    //根据顾客id查找顾客
    public Custom getOneCustom(int customid);
    //修改顾客信息
    public int updateCustom(Custom custom);
    //注销顾客
    public int cancelCust(int custId);
    //恢复顾客
    public int recoverCust(int custId);
    //分配顾客
    public int distinctCust(Custom custom);
    //添加顾客
    public int addCustomer(Custom custom);

    //模糊查询
    public List<Custom> getCustomByCon(Pager<Custom> pager);
    public int countCustByCon(Map<String,Object> map);


}