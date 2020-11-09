package com.dao;

import com.bean.Warehouse;

import java.util.List;

public interface WarehouseDao {
    int deleteByPrimaryKey(Integer id);

    /* int insert(Warehouse record);*/

    /*int insertSelective(Warehouse record);*/

    Warehouse selectByPrimaryKey(int id);//根据id查询单个对象
    List<Warehouse> getAllWarehouse(); //查询所有
    int updateWarehouseState(Warehouse warehouse);//根据ID修改状态

}