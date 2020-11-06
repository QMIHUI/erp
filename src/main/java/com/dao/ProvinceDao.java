package com.dao;

import com.bean.Province;

import java.util.List;

public interface ProvinceDao {
    public Province getProvinceById(Integer id);

    //获取所有省
    public List<Province> getAllProvinces();

}