package com.dao;

import com.bean.Custom;
import com.util.Pager;

import java.util.List;
import java.util.Map;

public interface CustomDao {
    //查询所有并分页
    public List<Custom> getAllCustom(Pager<Custom> pager);
    //查询个人所有的顾客并分页
    public List<Custom> getAllCustomById(Pager<Custom> pager);
    public int countCustomById(int leading);
    //查询个人的顾客
    public List<Custom> getCustomById(int lid);

    //查询所有顾客数量
    public int countCustom();
    //查询所有
    public List<Custom> getCust();
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
    //查询分配时间不为空的
    public List<Custom> getCustsNotNull(Pager<Custom> pager);
    public int countNotNull();

    //模糊查询
    public List<Custom> getCustomByCon(Pager<Custom> pager);
    public int countCustByCon(Map<String,Object> map);

    public List<Custom> getCustomBroByCon(Pager<Custom> pager);
    public int countCustBroByCon(Map<String,Object> map);

    //数据统计页面查找所有客户(并分页)
    public List<Custom> getAllCustomsStatis(Pager<Custom> pager);
    public int countCustomStatus1();
    //不分页
    public List<Custom> getCustStatis();
    //数据统计模糊查询
    public List<Custom> getCustomByConStatis(Pager<Custom> pager);
    public int countCustByConStatic(Map<String,Object> map);
}