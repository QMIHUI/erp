package com.dao;

import com.bean.CkWarehouse;

import java.util.List;

public interface CkWarehouseDao {
    //获取所有出库信息
    public List<CkWarehouse> getAllCkWarehouse();
}