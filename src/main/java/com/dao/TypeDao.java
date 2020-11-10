package com.dao;

import com.bean.Type;

import java.util.Date;
import java.util.List;

public interface TypeDao {
    public Type getTypeById(int id);

    //根据BrandId获取Type集合
    public List<Type> getTypeListByBrandId(int brandId);

    //获取所有type集合
    public List<Type> getAllType();

    //添加type
    public int addType(String typeName, int typeStatu, Date createTime,int createId,int brandId);
    //修改type
    public int updateType(String typeName, int typeStatu,int brandId,int id);
    //注销Type
    public int delType(int id);
    //恢复type
    public int recoverType(int id);
}