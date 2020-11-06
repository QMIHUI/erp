package com.dao;

import com.bean.City;

import java.util.List;

public interface CityDao {

    public City getCityById(Integer id);
    //根据provinceId获得List<City>
    public List<City> getAllCitiesByProvinceId(int id);

}