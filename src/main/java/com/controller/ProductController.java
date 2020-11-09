package com.controller;

import com.bean.*;
import com.dao.BrandDao;
import com.dao.FirmDao;
import com.dao.ProductDao;
import com.dao.TypeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
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
    @Autowired
    public FirmDao firmDao;
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
        List<Firm> firmList= firmDao.getAllFrims();
        session.setAttribute("firmList",firmList);
        return "redirect:purchase/product/productAdd.jsp";
    }

    //ajaxL品牌类型两级联动
    @RequestMapping(value = "getAllTypesByBrandId.do",method =RequestMethod.POST)
    public @ResponseBody List<Type> getAllTypesByBrandId(HttpServletRequest request, String id){
        int id1=Integer.parseInt(id);
        List<Type> typeList=typeDao.getTypeListByBrandId(id1);
        return typeList;
    }
    //添加商品
    @RequestMapping(value = "addProduct.do",method = RequestMethod.POST)
    public void addProduct(HttpSession session, HttpServletRequest request, HttpServletResponse response){
        String productModel=request.getParameter("productModel");
        double productPrice=Double.parseDouble(request.getParameter("productPrice"));
        int productStatus=Integer.parseInt(request.getParameter("productStatus"));
        String productUnit=request.getParameter("productUnit");
        Date createTime=new Date();
        int createId=((Users)session.getAttribute("user")).getuId();
        int typeId=Integer.parseInt(request.getParameter("type"));
        int firmId=Integer.parseInt(request.getParameter("firm"));
        int num=productDao.addProcduct(productModel,productPrice,productStatus,productUnit,createTime,createId,typeId,firmId);
        PrintWriter out=null;
        try {
            out=response.getWriter();

        } catch (IOException e) {
            e.printStackTrace();
        }
        if (num>0){
            List<Product> productList=productDao.getAllProduct();
            session.setAttribute("productList",productList);
            out.print("<script type='text/javaScript'>alert('添加成功！');window.location.href='purchase/product/productList.jsp'</script>");
            out.flush();
        }else {
            out.print("<script type='text/javaScript'>alert('添加失败！');window.location.href='purchase/product/productList.jsp'</script>");
            out.flush();
        }
    }


}
