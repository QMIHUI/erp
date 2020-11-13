package com.dao;

import com.bean.Warehouse;
import com.util.Pager;

import java.util.List;

public interface WarehouseDao {
    int deleteByPrimaryKey(Integer id);

    /* int insert(Warehouse record);*/

    /*int insertSelective(Warehouse record);*/

    Warehouse selectByPrimaryKey(int id);//根据id查询单个对象(修改前的查询)
    List<Warehouse> getAllWarehouse(); //查询所有
    int updateWarehouseState(Warehouse warehouse);//根据ID修改状态
    int UpdateWarehouse(Warehouse warehouse);//修改仓库
    int addWarehouse(Warehouse warehouse);//添加仓库
    List<Warehouse> selectWarehouseByuid(int uId);//根据用户ID查询自己管理的仓库

    public Warehouse getWarehouseById(int id);
    public List<Warehouse> getAllWarehouseStatis();

    public int countWarehouse();
    public List<Warehouse> getAllWarehouseStatisPage(Pager<Warehouse> pager);

}