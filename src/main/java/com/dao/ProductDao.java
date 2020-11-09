package com.dao;

import com.bean.Product;

import java.util.Date;
import java.util.List;

public interface ProductDao {
    //根据Id获取Product
    public Product getProductById(int id);
    //获取所有Product
    public List<Product> getAllProduct();
    //根据Id删除Product
    public int delProduct(int id);
    //添加商品
    public int addProcduct(String productModel, double productPrice, int productStatus, String productUnit, Date createTime,int createId,int typeId,int firmId);


}