package com.dao;

import com.bean.CkWarehouse;
import com.util.Pager;

import java.util.List;

public interface CkWarehouseDao {
    //根据仓库Id获取所有出库信息(已出库的)
    public List<CkWarehouse> getAllCkWarehouse1(int warehouseId);

    public int countCkWarehouse(int warehouseId);
    public List<CkWarehouse> getAllCkWarehousePage(Pager<CkWarehouse> pager);
    int deleteByPrimaryKey(Integer id);

    int insert(CkWarehouse record);


    int updateByPrimaryKey(CkWarehouse record);

    List<CkWarehouse> getAllCkWarehouse();//查询所有订单信息
    List<CkWarehouse> getCkWarehouseByuid(int uId);//根据用户id查询自己管理的仓库订单信息
    CkWarehouse getAllCkwarehouseByindent(String indent);//根据订单号查询相关信息
    int updateCKWarehouseState(CkWarehouse ckWarehouse);//修改状态
    int addCkwarehouse(CkWarehouse ckWarehouse);//添加出库
}