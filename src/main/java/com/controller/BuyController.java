package com.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.bean.*;
import com.dao.*;
import com.util.Pager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

/**
 * @author psqing
 * @create 2020-11-04 16:12
 */
@Controller
public class BuyController {
    @Autowired
    public FirmDao firmDao;
    @Autowired
    public ProvinceDao provinceDao;
    @Autowired
    public CityDao cityDao;
    @Autowired
    public BrandDao brandDao;
    @Autowired
    public TypeDao typeDao;
    @Autowired
    public ProductDao productDao;

    @RequestMapping(value = "getAllFirm.do",method = RequestMethod.GET)
    public String getAllFirms(HttpSession session,HttpServletRequest request){

        int countFirm = firmDao.coutFirm();
        System.out.println(countFirm);
        int size = 5;
        int row = countFirm % size == 0 ? (countFirm / size) : (countFirm / size + 1);
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
        if (pageIndex==0){
            pageIndex=1;
        }
        Pager<Firm> pager = new Pager<>();
        pager.setPage((pageIndex-1)*size);
        pager.setSize(size);
        pager.setTotal(countFirm);
        List<Firm> firmList=firmDao.getAllFrimsPage(pager);
        request.getSession().setAttribute("row",row);
        request.getSession().setAttribute("pageIndex",pageIndex);
        request.getSession().setAttribute("countFirm",countFirm);
        request.getSession().setAttribute("firmList",firmList);

        List<Province> provinceList=provinceDao.getAllProvinces();
        session.setAttribute("provinceList",provinceList);
        List<City> cityList=cityDao.getAllCity();
        session.setAttribute("cityList",cityList);

        return "redirect:purchase/manufacturer/manufacturerList.jsp";
    }
    @RequestMapping(value = "getFirmDetails.do",method = RequestMethod.GET)
    public String getFirmDetails(HttpServletRequest request,HttpSession session){
        int id=Integer.parseInt(request.getParameter("id"));
        Firm firm=firmDao.getFirmById(id);
        session.setAttribute("firm",firm);
        return "redirect:purchase/manufacturer/manufacturerView.jsp";
    }
    @RequestMapping(value = "delFirm.do",method = RequestMethod.GET)
    public String delFirm(HttpServletRequest request){
        int id=Integer.parseInt(request.getParameter("id"));
        firmDao.delFirm(id);
        productDao.delProductByFirmId(id);
        return "redirect:getAllFirm.do";
    }
    @RequestMapping(value = "recoverFirm.do",method = RequestMethod.GET)
    public String recoverFirm(HttpServletRequest request){
        int id=Integer.parseInt(request.getParameter("id"));
        int num=firmDao.recoverFirm(id);
        if (num>0){
            System.out.println("恢复成功");
        }else{
            System.out.println("恢复失败");
        }
        return "redirect:getAllFirm.do";

    }
    @RequestMapping(value = "goToAddFirm.do",method = RequestMethod.GET)
    public String goToAddFirm(HttpSession session){
        List<Province> provinceList=provinceDao.getAllProvinces();
        session.setAttribute("provinceList",provinceList);
        int provinceId=provinceList.get(0).getId();
        List<City> cityList=cityDao.getAllCitiesByProvinceId(provinceId);
        session.setAttribute("cityList",cityList);
        return "redirect:purchase/manufacturer/manufacturerAdd.jsp";
    }
    //ajax省市两级联动
    @RequestMapping(value = "getAllCitiesByProvinceId.do",method = RequestMethod.POST)
    public @ResponseBody List<City> getAllCitiesByProvinceId(HttpServletRequest request, String id){
        System.out.println("111");
        int id1=Integer.parseInt(id);
        List<City> cityList=cityDao.getAllCitiesByProvinceId(id1);
        return cityList;
    }
    //添加供应商
    @RequestMapping(value = "addFirm.do",method = RequestMethod.POST)
    public void addFirm(HttpSession session, HttpServletRequest request, HttpServletResponse response){
        String firmName=request.getParameter("firmName");
        String firmTel=request.getParameter("firmTel");
        String firmAddress=request.getParameter("firmAddress");
        String firmContent=request.getParameter("firmContent");
        int c_id=Integer.parseInt(request.getParameter("city"));
        Date createTime=new Date();
        int createId=((Users)session.getAttribute("user")).getuId();
        int status=Integer.parseInt(request.getParameter("status"));
        String firmFounder=request.getParameter("firmFounder");
        int num=firmDao.countFirmByName(firmName);

        PrintWriter out=null;
        try {
            out=response.getWriter();

        } catch (IOException e) {
            e.printStackTrace();
        }
        if (num==0){
            firmDao.addFirm(firmName,firmTel,firmAddress,firmContent,c_id,createTime,createId,status,firmFounder);
            int countFirm = firmDao.coutFirm();
            System.out.println(countFirm);
            int size = 5;
            int row = countFirm % size == 0 ? (countFirm / size) : (countFirm / size + 1);
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
            if (pageIndex==0){
                pageIndex=1;
            }
            Pager<Firm> pager = new Pager<>();
            pager.setPage((pageIndex-1)*size);
            pager.setSize(size);
            pager.setTotal(countFirm);
            List<Firm> firmList=firmDao.getAllFrimsPage(pager);
            request.getSession().setAttribute("row",row);
            request.getSession().setAttribute("pageIndex",pageIndex);
            request.getSession().setAttribute("countFirm",countFirm);
            request.getSession().setAttribute("firmList",firmList);

            List<Province> provinceList=provinceDao.getAllProvinces();
            session.setAttribute("provinceList",provinceList);
            List<City> cityList=cityDao.getAllCity();
            session.setAttribute("cityList",cityList);

            out.print("<script type='text/javaScript'>alert('添加成功！');window.location.href='purchase/manufacturer/manufacturerList.jsp'</script>");
            out.flush();
        }else {
            out.print("<script type='text/javaScript'>alert('该厂商存在，添加失败！');window.location.href='purchase/manufacturer/manufacturerList.jsp'</script>");
            out.flush();
        }
    }
    //修改供应商
    @RequestMapping(value = "firmUpdate.do",method = RequestMethod.GET)
    public String firmUpdate(HttpSession session,HttpServletRequest request){
        int firmId=Integer.parseInt(request.getParameter("id"));
        Firm firm=firmDao.getFirmById(firmId);
        session.setAttribute("firm",firm);
        List<Province> provinceList=provinceDao.getAllProvinces();
        session.setAttribute("provinceList",provinceList);
        int provinceId=firm.getCity().getProvince().getId();
        List<City> cityList=cityDao.getAllCitiesByProvinceId(provinceId);
        session.setAttribute("cityList",cityList);
        return "redirect:purchase/manufacturer/manufacturerUpdate.jsp";
    }
    @RequestMapping(value = "firmDoUpdate.do",method = RequestMethod.POST)
    public void firmDoUpdate(HttpSession session,HttpServletRequest request,HttpServletResponse response){
        int firmId=Integer.parseInt(request.getParameter("id"));
        String firmName=request.getParameter("firmName");
        String firmTel=request.getParameter("firmTel");
        String firmAddress=request.getParameter("firmAddress");
        String firmContent=request.getParameter("firmContent");
        int c_id=Integer.parseInt(request.getParameter("city"));
        int createId=((Users)session.getAttribute("user")).getuId();
        int status=Integer.parseInt(request.getParameter("status"));
        String firmFounder=request.getParameter("firmFounder");
        int num=firmDao.countFirmByName(firmName);

        PrintWriter out=null;
        try {
            out=response.getWriter();

        } catch (IOException e) {
            e.printStackTrace();
        }
        if (num==0){
            firmDao.updateFirm(firmName,firmTel,firmAddress,firmContent,c_id,createId,status,firmFounder,firmId);
            int countFirm = firmDao.coutFirm();
            System.out.println(countFirm);
            int size = 5;
            int row = countFirm % size == 0 ? (countFirm / size) : (countFirm / size + 1);
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
            if (pageIndex==0){
                pageIndex=1;
            }
            Pager<Firm> pager = new Pager<>();
            pager.setPage((pageIndex-1)*size);
            pager.setSize(size);
            pager.setTotal(countFirm);
            List<Firm> firmList=firmDao.getAllFrimsPage(pager);
            request.getSession().setAttribute("row",row);
            request.getSession().setAttribute("pageIndex",pageIndex);
            request.getSession().setAttribute("countFirm",countFirm);
            request.getSession().setAttribute("firmList",firmList);

            List<Province> provinceList=provinceDao.getAllProvinces();
            session.setAttribute("provinceList",provinceList);
            List<City> cityList=cityDao.getAllCity();
            session.setAttribute("cityList",cityList);

            out.print("<script type='text/javaScript'>alert('修改成功！');window.location.href='purchase/manufacturer/manufacturerList.jsp'</script>");
            out.flush();
        }else {
            out.print("<script type='text/javaScript'>alert('该厂商存在，修改失败！');window.location.href='purchase/manufacturer/manufacturerList.jsp'</script>");
            out.flush();
        }
    }
    //获取所有品牌
    @RequestMapping(value ="getAllBrands.do",method = RequestMethod.GET)
    public String getAllBrands(HttpSession session,HttpServletRequest request){

        int countBrand = brandDao.countBrand();
        System.out.println(countBrand);
        int size = 5;
        int row = countBrand % size == 0 ? (countBrand / size) : (countBrand / size + 1);
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
        if (pageIndex==0){
            pageIndex=1;
        }
        Pager<Brand> pager = new Pager<>();
        pager.setPage((pageIndex-1)*size);
        pager.setSize(size);
        pager.setTotal(countBrand);
        List<Brand> brandList=brandDao.getAllBrandsPage(pager);
        request.getSession().setAttribute("row",row);
        request.getSession().setAttribute("pageIndex",pageIndex);
        request.getSession().setAttribute("countBrand",countBrand);
        request.getSession().setAttribute("brandList",brandList);

        return "redirect:purchase/brand/brandList.jsp";
    }
    //添加品牌
    @RequestMapping(value = "addBrand.do",method = RequestMethod.POST)
    public void addBrand(HttpServletRequest request,HttpSession session,HttpServletResponse response){
        String brandName=request.getParameter("brandName");
        int brandStatus=Integer.parseInt(request.getParameter("brandStatus"));
        Date createTime=new Date();
        int createId=((Users)session.getAttribute("user")).getuId();
        int num=brandDao.countBrandByName(brandName);
        PrintWriter out=null;
        try {
            out=response.getWriter();

        } catch (IOException e) {
            e.printStackTrace();
        }
        if (num==0){
            brandDao.addBrand(brandName,brandStatus,createTime,createId);
            int countBrand = brandDao.countBrand();
            System.out.println(countBrand);
            int size = 5;
            int row = countBrand % size == 0 ? (countBrand / size) : (countBrand / size + 1);
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
            if (pageIndex==0){
                pageIndex=1;
            }
            Pager<Brand> pager = new Pager<>();
            pager.setPage((pageIndex-1)*size);
            pager.setSize(size);
            pager.setTotal(countBrand);
            List<Brand> brandList=brandDao.getAllBrandsPage(pager);
            request.getSession().setAttribute("row",row);
            request.getSession().setAttribute("pageIndex",pageIndex);
            request.getSession().setAttribute("countBrand",countBrand);
            request.getSession().setAttribute("brandList",brandList);

            out.print("<script type='text/javaScript'>alert('添加成功！');window.location.href='purchase/brand/brandList.jsp'</script>");
            out.flush();
        }else {
            out.print("<script type='text/javaScript'>alert('该品牌已存在，添加失败！');window.location.href='purchase/brand/brandList.jsp'</script>");
            out.flush();
        }
    }
    //修改品牌
    @RequestMapping(value = "toBrandUpdate.do",method = RequestMethod.GET)
    public String toBrandUpdate(HttpSession session,HttpServletRequest request){
        int id=Integer.parseInt(request.getParameter("id"));
        Brand brand=brandDao.getBrandById(id);
        session.setAttribute("brand",brand);
        return "redirect:purchase/brand/brandUpdate.jsp";
    }


    @RequestMapping(value = "brandUpdate.do",method = RequestMethod.POST)
    public void brandUpdate(HttpSession session,HttpServletRequest request,HttpServletResponse response){
        int id=Integer.parseInt(request.getParameter("id"));
        String brandName=request.getParameter("brandName");
        int brandStatus=Integer.parseInt(request.getParameter("brandStatus"));
        int num=brandDao.countBrandByName(brandName);

        PrintWriter out=null;
        try {
            out=response.getWriter();

        } catch (IOException e) {
            e.printStackTrace();
        }
        if (num==0){
            brandDao.updateBrand(brandName,brandStatus,id);
            int countBrand = brandDao.countBrand();
            System.out.println(countBrand);
            int size = 5;
            int row = countBrand % size == 0 ? (countBrand / size) : (countBrand / size + 1);
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
            if (pageIndex==0){
                pageIndex=1;
            }
            Pager<Brand> pager = new Pager<>();
            pager.setPage((pageIndex-1)*size);
            pager.setSize(size);
            pager.setTotal(countBrand);
            List<Brand> brandList=brandDao.getAllBrandsPage(pager);
            request.getSession().setAttribute("row",row);
            request.getSession().setAttribute("pageIndex",pageIndex);
            request.getSession().setAttribute("countBrand",countBrand);
            request.getSession().setAttribute("brandList",brandList);

            out.print("<script type='text/javaScript'>alert('修改成功！');window.location.href='purchase/brand/brandList.jsp'</script>");
            out.flush();
        }else {
            out.print("<script type='text/javaScript'>alert('该品牌存在，修改失败！');window.location.href='purchase/brand/brandList.jsp'</script>");
            out.flush();
        }

    }

    //注销brand
    @RequestMapping(value = "delBrand",method = RequestMethod.GET)
    public String delBrand(HttpServletRequest request){
        int id=Integer.parseInt(request.getParameter("id"));
        brandDao.delBrand(id);
        //注销brand下的所有商品类型
        typeDao.delTypeByBrandId(id);
        //注销brand下的所有商品
        List<Type> typeList=typeDao.getTypeListByBrandId(id);
        for (int i=0;i<typeList.size();i++){
            productDao.delProductByTypeId(typeList.get(i).getTypeId());
        }
        return "redirect:getAllBrands.do";
    }
    //恢复brand
    @RequestMapping(value = "recoverBrand.do",method = RequestMethod.GET)
    public String recoverBrand(HttpServletRequest request){
        int id=Integer.parseInt(request.getParameter("id"));
        brandDao.recoverBrand(id);
        //恢复brand下的所有商品类型
        //typeDao.recoverTypeByBrandId(id);
        //恢复brand下的所有商品
        /*List<Type> typeList=typeDao.getTypeListByBrandId(id);
        for (int i=0;i<typeList.size();i++){
            productDao.recoverProductByTypeId(typeList.get(i).getTypeId());
        }*/
        return "redirect:getAllBrands.do";
    }
    //获取所有商品类型
    @RequestMapping(value = "getAllTypes.do",method = RequestMethod.GET)
    public String getAllTypes(HttpSession session,HttpServletRequest request){

        int countType = typeDao.countType();
        System.out.println(countType);
        int size = 5;
        int row = countType % size == 0 ? (countType / size) : (countType / size + 1);
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
        if (pageIndex==0){
            pageIndex=1;
        }
        Pager<Type> pager = new Pager<>();
        pager.setPage((pageIndex-1)*size);
        pager.setSize(size);
        pager.setTotal(countType);
        List<Type> typeList=typeDao.getAllTypePage(pager);
        request.getSession().setAttribute("row",row);
        request.getSession().setAttribute("pageIndex",pageIndex);
        request.getSession().setAttribute("countType",countType);
        request.getSession().setAttribute("typeList",typeList);

        List<Brand> brandListSelect=brandDao.getAllBrands();
        session.setAttribute("brandListSelect",brandListSelect);

        return "redirect:purchase/productType/productTypeList.jsp";
    }
    //添加商品类型
    @RequestMapping(value = "toAddType.do",method = RequestMethod.GET)
    public String toAddType(HttpSession session){
        List<Brand> brandList=brandDao.getAllBrands();
        session.setAttribute("brandList",brandList);
        return "redirect:purchase/productType/productTypeAdd.jsp";
    }
    @RequestMapping(value = "addType.do",method = RequestMethod.POST)
    public void addType(HttpServletRequest request,HttpSession session,HttpServletResponse response){
        String typeName=request.getParameter("typeName");
        int typeStatu=Integer.parseInt(request.getParameter("typeStatus"));
        Date createTime=new Date();
        int createId=((Users)session.getAttribute("user")).getuId();
        int brandId=Integer.parseInt(request.getParameter("brand"));
        int num=typeDao.countTypeByNameAndBrand(brandId,typeName);

        PrintWriter out=null;
        try {
            out=response.getWriter();

        } catch (IOException e) {
            e.printStackTrace();
        }
        if (num==0){
            typeDao.addType(typeName,typeStatu,createTime,createId,brandId);
            int countType = typeDao.countType();
            System.out.println(countType);
            int size = 5;
            int row = countType % size == 0 ? (countType / size) : (countType / size + 1);
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
            if (pageIndex==0){
                pageIndex=1;
            }
            Pager<Type> pager = new Pager<>();
            pager.setPage((pageIndex-1)*size);
            pager.setSize(size);
            pager.setTotal(countType);
            List<Type> typeList=typeDao.getAllTypePage(pager);
            request.getSession().setAttribute("row",row);
            request.getSession().setAttribute("pageIndex",pageIndex);
            request.getSession().setAttribute("countType",countType);
            request.getSession().setAttribute("typeList",typeList);

            List<Brand> brandListSelect=brandDao.getAllBrands();
            session.setAttribute("brandListSelect",brandListSelect);

            out.print("<script type='text/javaScript'>alert('添加成功！');window.location.href='purchase/productType/productTypeList.jsp'</script>");
            out.flush();
        }else {
            out.print("<script type='text/javaScript'>alert('该类型存在，添加失败！');window.location.href='purchase/productType/productTypeList.jsp'</script>");
            out.flush();
        }
    }
    //修改商品类型
    @RequestMapping(value = "toTypeUpdate.do",method = RequestMethod.GET)
    public String toTypeUpdate(HttpServletRequest request,HttpSession session){
        int id=Integer.parseInt(request.getParameter("id"));
        Type type= typeDao.getTypeById(id);
        session.setAttribute("type",type);
        List<Brand> brandList=brandDao.getAllBrands();
        session.setAttribute("brandList",brandList);
        return "redirect:purchase/productType/productTypeUpdate.jsp";
    }
    @RequestMapping(value = "updateType.do",method = RequestMethod.POST)
    public void updateType(HttpSession session,HttpServletRequest request,HttpServletResponse response){
        int id=Integer.parseInt(request.getParameter("id"));
        String typeName=request.getParameter("typeName");
        //int typeStatu=Integer.parseInt(request.getParameter("typeStatus"));
        int brandId=Integer.parseInt(request.getParameter("brand"));
        int num=typeDao.countTypeByNameAndBrand(brandId,typeName);

        PrintWriter out=null;
        try {
            out=response.getWriter();

        } catch (IOException e) {
            e.printStackTrace();
        }
        if (num==0){
            if (brandDao.getBrandById(brandId).getBrandStatus()==2){
                typeDao.updateType2(typeName,brandId,id);
            }else {
                typeDao.updateType(typeName,brandId,id);
            }
            int countType = typeDao.countType();
            System.out.println(countType);
            int size = 5;
            int row = countType % size == 0 ? (countType / size) : (countType / size + 1);
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
            if (pageIndex==0){
                pageIndex=1;
            }
            Pager<Type> pager = new Pager<>();
            pager.setPage((pageIndex-1)*size);
            pager.setSize(size);
            pager.setTotal(countType);
            List<Type> typeList=typeDao.getAllTypePage(pager);
            request.getSession().setAttribute("row",row);
            request.getSession().setAttribute("pageIndex",pageIndex);
            request.getSession().setAttribute("countType",countType);
            request.getSession().setAttribute("typeList",typeList);

            List<Brand> brandListSelect=brandDao.getAllBrands();
            session.setAttribute("brandListSelect",brandListSelect);

            out.print("<script type='text/javaScript'>alert('修改成功！');window.location.href='purchase/productType/productTypeList.jsp'</script>");
            out.flush();
        }else {
            out.print("<script type='text/javaScript'>alert('该类型存在，修改失败！');window.location.href='purchase/productType/productTypeList.jsp'</script>");
            out.flush();
        }

    }
    //注销Type
    @RequestMapping(value = "delType.do",method = RequestMethod.GET)
    public String delType(HttpServletRequest request){
        int id=Integer.parseInt(request.getParameter("id"));
        typeDao.delType(id);
        //注销所有该type下的商品
        productDao.delProductByTypeId(id);
        return "redirect:getAllTypes.do";
    }
    //恢复type
    @RequestMapping(value = "recoverType.do",method = RequestMethod.GET)
    public String recoverType(HttpServletRequest request){
        int id=Integer.parseInt(request.getParameter("id"));
        int num=typeDao.recoverType(id);
        //恢复所有该type下的商品
        //productDao.recoverProductByTypeId(id);
        return "redirect:getAllTypes.do";
    }

}
