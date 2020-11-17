package com.dao;

import com.bean.RkWarehouse;
import com.util.Pager;

import java.util.List;

public interface RkWarehouseDao {
    //根据仓库Id获取所有入库信息
    public List<RkWarehouse> getAllRkWarehouse1(int warehouseId);

    public int countRkWarehouse(int warehouseId);
    public List<RkWarehouse> getAllRkWarehousePage(Pager<RkWarehouse> pager);
    int deleteByPrimaryKey(Integer id);

    int insert(RkWarehouse record);


    List<RkWarehouse> getAllRkWarehouse();//查询所有采购单单信息
    List<RkWarehouse> getRkWarehouseByuid(int uId);//根据用户id查询自己管理的仓库采购单信息
    RkWarehouse getAllRkwWrehouseByrkIndent(String rkIndent);//根据采购单号查询相关信息
    int updateRkWarehouseState(RkWarehouse rkWarehouse);//修改状态
    int addRkwarehouse(RkWarehouse rkWarehouse);//添加出库
}