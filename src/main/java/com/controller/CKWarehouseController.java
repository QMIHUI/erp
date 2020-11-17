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
    public String updateCKState(@PathVariable("id")int id, @PathVariable("state")int state, @PathVariable("uId") int uId, @PathVariable("indent") String indent, HttpSession session,HttpServletResponse response){//根据ID修改状态
        if(state==1){
            state=2;
            List<Orderdetails> listOrderdetails=orderdetailsDao.getAllOrderdatailsByOrderid(indent);
            List<KcWarehouse> listKcWarehouse=kcWarehouseDao.getKcWarehouseByuid(uId);
            for (int i=0;i<listOrderdetails.size();i++){
                int num1=listOrderdetails.get(i).getProduct().getProductId();
                int repertory=0;//出库的数量

                int numkc=0;//库存
                int productId=0;//型号ID
                int count=0;
                for (int j=0;j<listKcWarehouse.size();j++){
                    int num=listKcWarehouse.get(j).getProductId();
                    System.out.println("num1:"+num1);
                    System.out.println("num:"+num);
                    if (num1==num){
                        System.out.println("存在该商品");
                        count++;

                    }
                }
                KcWarehouse kw=listKcWarehouse.get(i);
                Orderdetails odr=listOrderdetails.get(i);
                repertory=odr.getPurchaseNum();

                productId=kw.getProductId();
                numkc=kw.getRepertory();
                if (count>0 && repertory<=numkc){
                    KcWarehouse kcWarehouse=new KcWarehouse(repertory,productId);
                    kcWarehouseDao.updateKcWarehouseNumber(kcWarehouse);//调用方法，减少数量
                }else if(repertory>numkc){
                    PrintWriter out=null;
                    /*try {
                        out = response.getWriter();
                        response.setContentType("text/html;charset=utf-8");
                    }catch (IOException e) {
                        e.printStackTrace();
                    }*/
                    //out.write("<script>alert('库存数量不足，请及时补货'); window.location='../../../../storage/delivery/deliveryList.jsp' ;window.close();</script>");
                    //out.flush();
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
            List<KcWarehouse> listKcWarehouse=kcWarehouseDao.getKcWarehouseByuid(uId);
            for (int i=0;i<listOrderdetails.size();i++){
                int num1=listOrderdetails.get(i).getProduct().getProductId();
                int repertory=0;//出库的数量

                int numkc=0;//库存
                int productId=0;//型号ID
                int count=0;
                for (int j=0;j<listKcWarehouse.size();j++){
                    int num=listKcWarehouse.get(j).getProductId();
                    System.out.println("num1:"+num1);
                    System.out.println("num:"+num);
                    if (num1==num){
                        System.out.println("存在该商品");
                        count++;
                    }
                }
                KcWarehouse kw=listKcWarehouse.get(i);
                Orderdetails odr=listOrderdetails.get(i);
                repertory=odr.getPurchaseNum();

                productId=kw.getProductId();
                if (count>0 ){
                    KcWarehouse kcWarehouse=new KcWarehouse(repertory,productId);
                    kcWarehouseDao.updateKcWarehouseNumberadd(kcWarehouse);//调用方法，加上库存
                }
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
