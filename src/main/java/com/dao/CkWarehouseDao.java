package com.dao;

import com.bean.CkWarehouse;
import com.util.Pager;

import java.util.List;

public interface CkWarehouseDao {
    //根据仓库Id获取所有出库信息(已出库的)
    public List<CkWarehouse> getAllCkWarehouse(int warehouseId);

    public int countCkWarehouse(int warehouseId);
    public List<CkWarehouse> getAllCkWarehousePage(Pager<CkWarehouse> pager);
}