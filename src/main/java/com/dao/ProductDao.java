package com.dao;

import com.bean.Product;
import com.util.Pager;

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
    //根据typeId注销商品
    public int delProductByTypeId(int typeId);
    //根据typeId恢复商品
    public int recoverProductByTypeId(int typeId);
    //根据firmId注销商品
    public int delProductByFirmId(int firmId);
    //添加商品
    public int addProcduct(String productModel, double productPrice, int productStatus, String productUnit, Date createTime,int createId,int typeId,int firmId);
    //修改商品
    public int updateProduct(String productModel, double productPrice,String productUnit,int typeId,int firmId,int id);
    //修改商品(状态不可用)
    public int updateProduct2(String productModel, double productPrice,String productUnit,int typeId,int firmId,int id);
    //根据型号获取商品列表
    public List<Product> getProductsByTypeId(int typeId);


    public List<Product> getAllProductPage(Pager<Product> pager);
    public int countProduct();
}