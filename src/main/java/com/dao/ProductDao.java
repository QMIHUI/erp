package com.dao;

import com.bean.Product;

import java.util.List;

public interface ProductDao {
    //根据Id获取Product
    public Product getProductById(int id);
    //获取所有Product
    public List<Product> getAllProduct();
    //根据Id删除Product
    public int delProduct(int id);

}