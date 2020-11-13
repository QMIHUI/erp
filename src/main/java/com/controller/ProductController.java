package com.controller;

import com.bean.*;
import com.dao.BrandDao;
import com.dao.FirmDao;
import com.dao.ProductDao;
import com.dao.TypeDao;
import com.util.Pager;
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
    public String getAllProduct(HttpSession session,HttpServletRequest request){

        int countProduct = productDao.countProduct();
        System.out.println(countProduct);
        int size = 5;
        int row = countProduct % size == 0 ? (countProduct / size) : (countProduct / size + 1);
        System.out.println(row);
        String currentIndex= request.getParameter("pageIndex");
        //第一次访问(当前页码=1)
        int pageIndex = 1;
        if(currentIndex!=null) {
            pageIndex=Integer.parseInt(currentIndex);
        }
        if(currentIndex==null || Integer.parseInt(currentIndex) <= 0){
            pageIndex = 1;
        }else if(Integer.parseInt(currentIndex) >= row){
            pageIndex=row;
        }
        Pager<Product> pager = new Pager<>();
        pager.setPage((pageIndex-1)*size);
        pager.setSize(size);
        pager.setTotal(countProduct);
        List<Product> productList=productDao.getAllProductPage(pager);
        request.getSession().setAttribute("row",row);
        request.getSession().setAttribute("pageIndex",pageIndex);
        request.getSession().setAttribute("countProduct",countProduct);
        request.getSession().setAttribute("productList",productList);

        List<Brand> brandListSelect=brandDao.getAllBrands();
        session.setAttribute("brandListSelect",brandListSelect);
        List<Type> typeListSelect=typeDao.getAllType();
        session.setAttribute("typeListSelect",typeListSelect);


        return "redirect:purchase/product/productList.jsp";
    }
    //注销Product
    @RequestMapping(value = "delProduct.do",method = RequestMethod.GET)
    public String delProduct(HttpServletRequest request){
        int id=Integer.parseInt(request.getParameter("id"));
        int num=productDao.delProduct(id);
        if (num>0){
            System.out.println("注销成功");
        }else{
            System.out.println("注销失败");
        }
        return "redirect:getAllProduct.do";
    }
    //恢复
    @RequestMapping(value = "recoverProduct.do",method = RequestMethod.GET)
    public String recoverProduct(HttpServletRequest request){
        int id=Integer.parseInt(request.getParameter("id"));
        int num=productDao.recoverProduct(id);
        if (num>0){
            System.out.println("恢复成功");
        }else{
            System.out.println("恢复失败");
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

            int countProduct = productDao.countProduct();
            System.out.println(countProduct);
            int size = 5;
            int row = countProduct % size == 0 ? (countProduct / size) : (countProduct / size + 1);
            System.out.println(row);
            String currentIndex= request.getParameter("pageIndex");
            //第一次访问(当前页码=1)
            int pageIndex = 1;
            if(currentIndex!=null) {
                pageIndex=Integer.parseInt(currentIndex);
            }
            if(currentIndex==null || Integer.parseInt(currentIndex) <= 0){
                pageIndex = 1;
            }else if(Integer.parseInt(currentIndex) >= row){
                pageIndex=row;
            }
            Pager<Product> pager = new Pager<>();
            pager.setPage((pageIndex-1)*size);
            pager.setSize(size);
            pager.setTotal(countProduct);
            List<Product> productList=productDao.getAllProductPage(pager);
            request.getSession().setAttribute("row",row);
            request.getSession().setAttribute("pageIndex",pageIndex);
            request.getSession().setAttribute("countProduct",countProduct);
            request.getSession().setAttribute("productList",productList);

            List<Brand> brandListSelect=brandDao.getAllBrands();
            session.setAttribute("brandListSelect",brandListSelect);
            List<Type> typeListSelect=typeDao.getAllType();
            session.setAttribute("typeListSelect",typeListSelect);

            out.print("<script type='text/javaScript'>alert('添加成功！');window.location.href='purchase/product/productList.jsp'</script>");
            out.flush();
        }else {
            out.print("<script type='text/javaScript'>alert('添加失败！');window.location.href='purchase/product/productList.jsp'</script>");
            out.flush();
        }
    }
    //修改商品
    @RequestMapping(value = "gotoUpdateProduct.do",method = RequestMethod.GET)
    public String gotoUpdateProduct(HttpSession session,HttpServletRequest request){
        int productId=Integer.parseInt(request.getParameter("id"));
        Product product=productDao.getProductById(productId);
        session.setAttribute("product",product);
        List<Brand> brandList=brandDao.getAllBrands();
        session.setAttribute("brandList",brandList);
        int brandId=product.getType().getBrand().getBrandId();
        List<Type> typeList=typeDao.getTypeListByBrandId(brandId);
        session.setAttribute("typeList",typeList);
        List<Firm> firmList=firmDao.getAllFrims();
        session.setAttribute("firmList",firmList);
        return "redirect:purchase/product/productUpdate.jsp";
    }
    @RequestMapping(value = "doUpdateProduct.do",method = RequestMethod.POST)
    public void updateProduct(HttpSession session,HttpServletRequest request,HttpServletResponse response){
        int id=Integer.parseInt(request.getParameter("id"));
        String productModel=request.getParameter("productModel");
        double productPrice=Double.parseDouble(request.getParameter("productPrice"));
        //int productStatus=Integer.parseInt(request.getParameter("productStatus"));
        String productUnit=request.getParameter("productUnit");
        int typeId=Integer.parseInt(request.getParameter("type"));
        int firmId=Integer.parseInt(request.getParameter("firm"));
        int num=0;
        if(typeDao.getTypeById(typeId).getTypeStatus()==2||firmDao.getFirmById(firmId).getStatus()==2){
            num=productDao.updateProduct2(productModel,productPrice,productUnit,typeId,firmId,id);
        }else {
            num=productDao.updateProduct(productModel,productPrice,productUnit,typeId,firmId,id);
        }
        PrintWriter out=null;
        try {
            out=response.getWriter();

        } catch (IOException e) {
            e.printStackTrace();
        }
        if (num>0){

            int countProduct = productDao.countProduct();
            System.out.println(countProduct);
            int size = 5;
            int row = countProduct % size == 0 ? (countProduct / size) : (countProduct / size + 1);
            System.out.println(row);
            String currentIndex= request.getParameter("pageIndex");
            //第一次访问(当前页码=1)
            int pageIndex = 1;
            if(currentIndex!=null) {
                pageIndex=Integer.parseInt(currentIndex);
            }
            if(currentIndex==null || Integer.parseInt(currentIndex) <= 0){
                pageIndex = 1;
            }else if(Integer.parseInt(currentIndex) >= row){
                pageIndex=row;
            }
            Pager<Product> pager = new Pager<>();
            pager.setPage((pageIndex-1)*size);
            pager.setSize(size);
            pager.setTotal(countProduct);
            List<Product> productList=productDao.getAllProductPage(pager);
            request.getSession().setAttribute("row",row);
            request.getSession().setAttribute("pageIndex",pageIndex);
            request.getSession().setAttribute("countProduct",countProduct);
            request.getSession().setAttribute("productList",productList);

            List<Brand> brandListSelect=brandDao.getAllBrands();
            session.setAttribute("brandListSelect",brandListSelect);
            List<Type> typeListSelect=typeDao.getTypeListByBrandId(brandListSelect.get(0).getBrandId());
            session.setAttribute("typeListSelect",typeListSelect);

            out.print("<script type='text/javaScript'>alert('修改成功！');window.location.href='purchase/product/productList.jsp'</script>");
            out.flush();
        }else {
            out.print("<script type='text/javaScript'>alert('修改失败！');window.location.href='purchase/product/productList.jsp'</script>");
            out.flush();
        }
    }

}
