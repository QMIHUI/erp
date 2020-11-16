package com.dao;

import com.bean.RkWarehouse;
import com.util.Pager;

import java.util.List;

public interface RkWarehouseDao {
    //根据仓库Id获取所有入库信息
    public List<RkWarehouse> getAllRkWarehouse1(int warehouseId);

    public int countRkWarehouse(int warehouseId);
    public List<RkWarehouse> getAllRkWarehousePage(Pager<RkWarehouse> pager);
}