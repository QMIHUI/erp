package com.dao;

import com.bean.KcWarehouse;

import java.util.List;

public interface KcWarehouseDao {
    int deleteByPrimaryKey(Integer id);

    int insert(KcWarehouse record);

    KcWarehouse selectByPrimaryKey(Integer id);

    List<KcWarehouse> getKcWarehouse();//查询所有库存
    List<KcWarehouse> getKcWarehouseByuid(int uId);//根据用户id查询自己仓库的库存

    int updateKcWarehouseNumber(KcWarehouse kcWarehouse);//修改库存数量(出库)
    int updateKcWarehouseNumberadd(KcWarehouse kcWarehouse);//修改库存数量(退货)
    int updareKcWarehouseByRk(KcWarehouse kcWarehouse);//修改库存数量(入库)
    int addKcWarehouse(KcWarehouse kcWarehouse);//当采购单的商品是库存中没有时，添加库存
}