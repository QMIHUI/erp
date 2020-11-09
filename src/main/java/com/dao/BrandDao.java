package com.dao;

import com.bean.Brand;

import java.util.List;

public interface BrandDao {
    public Brand getBrandById(int id);
    //获取所有Brand
    public List<Brand> getAllBrands();
}