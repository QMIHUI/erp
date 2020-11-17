package com.controller;

import com.bean.*;
import com.dao.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@Controller
public class CKWarehouseController {
    @Autowired
    private CkWarehouseDao ckWarehouseDao;
    @Autowired
    private OrderdetailsDao orderdetailsDao;
    @Autowired
    private KcWarehouseDao kcWarehouseDao;
    @Autowired
    private OrdersDao ordersDao;
    @Autowired
    private WarehouseDao warehouseDao;

    @RequestMapping(value = "/{uId}/deliveryList.do",method = RequestMethod.GET)
    public String getCkwarehouse(@PathVariable("uId") int uId, HttpSession session){
        List<CkWarehouse> listCkWarehouse=ckWarehouseDao.getCkWarehouseByuid(uId);
        if(listCkWarehouse.isEmpty()){
             listCkWarehouse=ckWarehouseDao.getAllCkWarehouse();
        }
        session.setAttribute("listCkWarehouse",listCkWarehouse);
        return "redirect:../storage/delivery/deliveryList.jsp";
    }

    @RequestMapping(value = "/{indent}/deliveryView.do",method = RequestMethod.GET)
    public String getCkWarehouseByident(@PathVariable("indent") String indent,HttpSession session){//根据订单号查询订单详情
        List<Orderdetails> listOrderdetails=orderdetailsDao.getAllOrderdatailsByOrderid(indent);
        session.setAttribute("listOrderdetails",listOrderdetails);
        CkWarehouse ckWarehouse=ckWarehouseDao.getAllCkwarehouseByindent(indent);//根据订单号查询相关信息
        session.setAttribute("ckWarehouse",ckWarehouse);
        return "redirect:../storage/delivery/deliveryView.jsp";
    }


     @RequestMapping(value = "/{id}/{state}/{uId}/{indent}/updateCKstate.do",method = RequestMethod.GET)//未发货，需要发货走的方法
    public String updateCKState(@PathVariable("id")int id, @PathVariable("state")int state, @PathVariable("uId") int uId, @PathVariable("indent") String indent, HttpSession session){//根据ID修改状态
        if(state==1){
            state=2;
            List<Orderdetails> listOrderdetails=orderdetailsDao.getAllOrderdatailsByOrderid(indent);
            int brandid=0;
            int typeid=0;
            int productid=0;
            int repertory=0;//出库的数量
            for (int i=0;i<listOrderdetails.size();i++){
                Orderdetails odr=listOrderdetails.get(i);
                repertory=odr.getPurchaseNum();
                brandid=odr.getBrand().getBrandId();
                typeid=odr.getType().getTypeId();
                productid=odr.getProduct().getProductId();
            }

            int numkc=0;//库存的数量
            int brandId=0;//品牌ID
            int typeId=0;//类型ID
            int productId=0;//型号ID
            List<KcWarehouse> listKcWarehouse=kcWarehouseDao.getKcWarehouseByuid(uId);
            for (int i=0;i<listKcWarehouse.size();i++){
                KcWarehouse kw=listKcWarehouse.get(i);
                brandId=kw.getBrandId();
                typeId=kw.getTypeId();
                productId=kw.getProductId();
                numkc=kw.getRepertory();
            }

            System.out.println(brandid+"\t"+typeid+"\t"+productid+"\t"+repertory);//订购单单信息
            System.out.println(brandId+"\t"+typeId+"\t"+productId+"\t"+numkc);//库存信息
            for (int i=0;i<listOrderdetails.size();i++){
                if(brandid==brandId && typeid==typeId && productid==productId && numkc>repertory){
                    KcWarehouse kcWarehouse=new KcWarehouse(repertory,brandId,typeId,productId);
                    kcWarehouseDao.updateKcWarehouseNumber(kcWarehouse);//调用方法，减少数量
                }else if(brandid==brandId && typeid==typeId && productid==productId && numkc<repertory){
                    /*PrintWriter out=null;
                    try {
                        out = response.getWriter();
                    }catch (IOException e) {
                        e.printStackTrace();
                    }*/
                    //out.print("<script type='text/javaScript'>alert('库存数量不足，请及时补货！');window.location.href='storage/delivery/deliveryList.jsp'</script>");
                    return "redirect:../../../../storage/delivery/deliveryList.jsp";//库存的数量<出库的数量
                }
            }

        }else if(state==2){
            state=3;
        }else{
            state=0;
        }
        CkWarehouse ckWarehouse=new CkWarehouse(id,state);
        int num=ckWarehouseDao.updateCKWarehouseState(ckWarehouse);
         List<CkWarehouse> listCkWarehouse=ckWarehouseDao.getCkWarehouseByuid(uId);//修改状态后的查询所有
         if(listCkWarehouse.isEmpty()){
             listCkWarehouse=ckWarehouseDao.getAllCkWarehouse();
         }
         session.setAttribute("listCkWarehouse",listCkWarehouse);
         //return "redirect:../../../../../storage/delivery/deliveryList.jsp";
         return "redirect:../../../../storage/delivery/deliveryList.jsp";
    }



    @RequestMapping(value = "/{id}/{state}/{uId}/{indent}/updateCKwarehouseState.do",method = RequestMethod.GET)//未发货取消订单走的方法
    public String updateCKwarehouseState(@PathVariable("id")int id, @PathVariable("state")int state, @PathVariable("uId") int uId, @PathVariable("indent") String indent, HttpSession session){//根据ID修改状态
        if(state==1){
            state=5;
        }else if(state==2){
            state=4;
        }else if(state==4){
            state=5;
            List<Orderdetails> listOrderdetails=orderdetailsDao.getAllOrderdatailsByOrderid(indent);//根据订单查询到该订单数量
            int brandid=0;
            int typeid=0;
            int productid=0;
            int repertory=0;//出库的数量
            for (int i=0;i<listOrderdetails.size();i++){
                Orderdetails odr=listOrderdetails.get(i);
                repertory=odr.getPurchaseNum();
                brandid=odr.getBrand().getBrandId();
                typeid=odr.getType().getTypeId();
                productid=odr.getProduct().getProductId();
            }

            int numkc=0;//库存的数量
            int brandId=0;//品牌ID
            int typeId=0;//类型ID
            int productId=0;//型号ID
            List<KcWarehouse> listKcWarehouse=kcWarehouseDao.getKcWarehouseByuid(uId);//查询该用户库存的数量
            for (int i=0;i<listKcWarehouse.size();i++){
                KcWarehouse kw=listKcWarehouse.get(i);
                brandId=kw.getBrandId();
                typeId=kw.getTypeId();
                productId=kw.getProductId();
                numkc=kw.getRepertory();
            }
            if(brandid==brandId && typeid==typeId && productid==productId){
                KcWarehouse kcWarehouse=new KcWarehouse(repertory,brandId,typeId,productId);
                kcWarehouseDao.updateKcWarehouseNumberadd(kcWarehouse);//调用方法，加上库存
            }


        }
        CkWarehouse ckWarehouse=new CkWarehouse(id,state);
        int num=ckWarehouseDao.updateCKWarehouseState(ckWarehouse);
        List<CkWarehouse> listCkWarehouse=ckWarehouseDao.getCkWarehouseByuid(uId);//修改状态后的查询所有
        if(listCkWarehouse.isEmpty()){
            listCkWarehouse=ckWarehouseDao.getAllCkWarehouse();
        }
        session.setAttribute("listCkWarehouse",listCkWarehouse);
        return "redirect:../../../../storage/delivery/deliveryList.jsp";
    }


    @RequestMapping(value = "/{uId}/addCkwarehouse.do",method = RequestMethod.GET)
    public String addCkwarehouse(@PathVariable("uId") int uId,HttpSession session){//添加出库前的查询
        List<Orders> listOrders=ordersDao.getAllByCkwarehouse();//查询所有通过审核未出库的订单
        session.setAttribute("listOrders",listOrders);
        List<Warehouse> listWarehouse=warehouseDao.selectWarehouseByuid(uId);//添加出库时查询该用户自己管理的仓库
        if(listWarehouse.isEmpty()){
            listWarehouse=warehouseDao.getAllWarehouse();
        }
        session.setAttribute("listWarehouse",listWarehouse);
        return "redirect:../storage/delivery/deliveryAdd.jsp";
    }

    @RequestMapping(value = "/{uId}/deliveryAdd.do",method = RequestMethod.POST)
    public String deliveryAdd(@PathVariable("uId") int uId, CkWarehouse ckWarehouse, String indent, HttpSession session, HttpServletRequest request){//添加出库
        int num=ckWarehouseDao.addCkwarehouse(ckWarehouse);//添加出库的方法
        int warehouseId=Integer.parseInt(request.getParameter("warehouseId"));
        Orders orders=new Orders(indent,warehouseId);
        int number=ordersDao.updateOrdersStateByCkwarehouse(orders);//出库后修改该订单号的状态
        List<CkWarehouse> listCkWarehouse=ckWarehouseDao.getCkWarehouseByuid(uId);
        if(listCkWarehouse.isEmpty()){
            listCkWarehouse=ckWarehouseDao.getAllCkWarehouse();
        }
        session.setAttribute("listCkWarehouse",listCkWarehouse);
        return "redirect:../storage/delivery/deliveryList.jsp";
    }

}
