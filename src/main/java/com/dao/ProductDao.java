package com.dao;

import com.bean.Product;

import java.util.Date;
import java.util.List;

public interface ProductDao {
    //根据Id获取Product
    public Product getProductById(int id);
    //获取所有Product
    public List<Product> getAllProduct();
    //根据Id注销Product
    public int delProduct(int id);
    //恢复商品
    public int recoverProduct(int id);
    //添加商品
    public int addProcduct(String productModel, double productPrice, int productStatus, String productUnit, Date createTime,int createId,int typeId,int firmId);
    //修改商品
    public int updateProduct(String productModel, double productPrice, int productStatus, String productUnit,int typeId,int firmId,int id);

}