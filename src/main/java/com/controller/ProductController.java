package com.controller;

import com.bean.Brand;
import com.bean.Product;
import com.bean.Type;
import com.dao.BrandDao;
import com.dao.ProductDao;
import com.dao.TypeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author psqing
 * @create 2020-11-09 10:07
 */
@Controller
public class ProductController {
    @Autowired
    public ProductDao productDao;
    @Autowired
    public BrandDao brandDao;
    @Autowired
    public TypeDao typeDao;
    //获取所有Product
    @RequestMapping(value = "getAllProduct.do",method = RequestMethod.GET)
    public String getAllProduct(HttpSession session){
        List<Product> productList=productDao.getAllProduct();
        session.setAttribute("productList",productList);
        return "redirect:purchase/product/productList.jsp";
    }
    //删除Product
    @RequestMapping(value = "delProduct.do",method = RequestMethod.GET)
    public String delProduct(HttpServletRequest request, HttpSession session){
        int id=Integer.parseInt(request.getParameter("id"));
        int num=productDao.delProduct(id);
        if (num>0){
            System.out.println("删除成功");
        }else{
            System.out.println("删除失败");
        }
        return "redirect:getAllProduct.do";
    }

    //增加Product
    @RequestMapping(value = "goToAddProduct.do",method = RequestMethod.GET)
    public String goToAddProduct(HttpSession session){
        List<Brand> brandList=brandDao.getAllBrands();
        session.setAttribute("brandList",brandList);

        int firstBrandId=brandList.get(0).getBrandId();
        List<Type> typeList=typeDao.getTypeListByBrandId(firstBrandId);
        session.setAttribute("typeList",typeList);

        return "redirect:purchase/product/productAdd.jsp";
    }

}
