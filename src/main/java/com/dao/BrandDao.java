package com.dao;

import com.bean.Brand;

import java.util.Date;
import java.util.List;

public interface BrandDao {
    public Brand getBrandById(int id);
    //获取所有Brand
    public List<Brand> getAllBrands();
    //添加Brand
    public int addBrand(String brandName, int brandStatus, Date createTime,int createId);
    //更新brand
    public int updateBrand(String brandName, int brandStatus,int id);
    //注销brand
    public int delBrand(int id);
    //恢复brand
    public int recoverBrand(int id);
}