package com.dao;

import com.bean.RkWarehouse;

import java.util.List;

public interface RkWarehouseDao {
    //获取所有入库信息
    public List<RkWarehouse> getAllRkWarehouse();
}