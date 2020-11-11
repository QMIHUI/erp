package com.dao;

import com.bean.City;
import com.util.Pager;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface CityDao {

    public City getCityById(Integer id);
    //根据provinceId获得List<City>
    public List<City> getAllCitiesByProvinceId(int id);
    //分页得到所有
    public List<City> getAllProCity(Pager<City> pager);
    //计算city个数
    public int countCity();
    //得到所有
    public List<City> getAllCity();
    //条件查询并分页得到
    public List<City> getProCityByCon(Pager<City> pager);
    //条件查询的个数
    public int countCityByCon(@Param("pId") int pId,@Param("cId") int cId);

    public List<City> getAllCityorWarehouse();

}