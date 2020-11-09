package com.dao;

import com.bean.Type;

import java.util.List;

public interface TypeDao {
    public Type getTypeById(int id);

    //根据BrandId获取Type集合
    public List<Type> getTypeListByBrandId(int brandId);
}