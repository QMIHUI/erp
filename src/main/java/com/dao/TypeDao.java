package com.dao;

import com.bean.Type;
import com.util.Pager;

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
    public int updateType(String typeName,int brandId,int id);
    //修改type(状态为不可用)
    public int updateType2(String typeName,int brandId,int id);
    //注销Type
    public int delType(int id);
    //恢复type
    public int recoverType(int id);
    //根据brandId注销type
    public int delTypeByBrandId(int brandId);
    //根据brandId恢复type
    public int recoverTypeByBrandId(int brandId);

    public List<Type> getAllTypePage(Pager<Type> pager);
    public int countType();

    //根据类型名查找类型
    public int countTypeByNameAndBrand(int brandId, String name);
}