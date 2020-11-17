package com.controller;

import com.bean.*;
import com.dao.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class RkWarehouseController {
    @Autowired
    private RkWarehouseDao rkWarehouseDao;
    @Autowired
    private DetailsDao detailsDao;
    @Autowired
    private KcWarehouseDao kcWarehouseDao;
    @Autowired
    private PurchaseDao purchaseDao;
    @Autowired
    private WarehouseDao warehouseDao;

    @RequestMapping(value = "/{uId}/stockList.do",method = RequestMethod.GET)
    public String getRkwarehouse(@PathVariable("uId") int uId, HttpSession session){//根据用户id管理自己的仓库进行入库
        List<RkWarehouse> listRkWarehouse=rkWarehouseDao.getRkWarehouseByuid(uId);
        if(listRkWarehouse.isEmpty()){
            listRkWarehouse=rkWarehouseDao.getAllRkWarehouse();
        }
        session.setAttribute("listRkWarehouse",listRkWarehouse);
        return "redirect:../storage/stock/stockList.jsp";
    }

    @RequestMapping(value = "/{rkIndent}/stockView.do",method = RequestMethod.GET)
    public String getRkWarehouserkIndent(@PathVariable("rkIndent") String rkIndent,HttpSession session){
        List<Details> listDetails=detailsDao.getAllDetailsBypurchaseId(rkIndent);//根据采购单号查询相关的商品信息
        session.setAttribute("listDetails",listDetails);

        RkWarehouse rkWarehouse=rkWarehouseDao.getAllRkwWrehouseByrkIndent(rkIndent);//根据采购单号查询相关信息
        session.setAttribute("rkWarehouse",rkWarehouse);
        return "redirect:../storage/stock/stockView.jsp";
    }

    @RequestMapping(value = "/{id}/{state}/{uId}/{rkIndent}/{warehouseId}/updateRkstate.do",method = RequestMethod.GET)//未入库，需要入库走的方法
    public String updateRkState(@PathVariable("id")int id, @PathVariable("state")int state, @PathVariable("uId") int uId, @PathVariable("rkIndent") String rkIndent,@PathVariable("warehouseId") int warehouseId, HttpSession session){//根据ID修改状态
        if(state==1){
            state=2;
            System.out.println("rkIndent:"+rkIndent);
            List<Details> listDetails=detailsDao.getAllDetailsBypurchaseId(rkIndent);//根据采购单号查询相关的商品信息
            System.out.println("details条数:"+listDetails.size());
            List<KcWarehouse> listKcWarehouse=kcWarehouseDao.getKcWarehouseByuid(uId);
            System.out.println("listKcWarehouse条数:"+listKcWarehouse.size());
            for(int i=0;i<listDetails.size();i++){

                int num1=listDetails.get(i).getProduct().getProductId();
                int brandid=0;
                int typeid=0;
                int productid=0;
                int firmid=0;
                int repertory=0;//出库的数量

                int numkc=0;//库存
                int brandId=0;//品牌ID
                int typeId=0;//类型ID
                int productId=0;//型号ID
                int factoryId=0;//厂商ID
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
                if (count>0){
                    KcWarehouse kw=listKcWarehouse.get(i);
                    Details details=listDetails.get(i);
                    repertory=details.getCount();
                    brandId=kw.getBrandId();
                    typeId=kw.getTypeId();
                    productId=kw.getProductId();
                    factoryId=kw.getFactoryId();
                    numkc=kw.getRepertory();

                    //KcWarehouse kcWarehouse=new KcWarehouse(repertory,brandId,typeId,factoryId,productId);
                    KcWarehouse kcWarehouse=new KcWarehouse(repertory,productId);
                    kcWarehouseDao.updareKcWarehouseByRk(kcWarehouse);//调用方法，添加库存数量
                }else{
                    Details details=listDetails.get(i);
                    brandid=details.getBrand().getBrandId();
                    typeid=details.getType().getTypeId();
                    productid=details.getProduct().getProductId();
                    firmid=details.getFirm().getFirmId();
                    repertory=details.getCount();
                    KcWarehouse kcWarehouse=new KcWarehouse(warehouseId,brandid,typeid,firmid,productid,repertory);
                    kcWarehouseDao.addKcWarehouse(kcWarehouse);//调用方法，添加库存数量
                    //在库存中进行添加
                }
            }


            /*for(int i=0;i<listDetails.size();i++){
                System.out.println(brandid+"\t"+typeid+"\t"+productid+"\t"+firmid+"\t"+repertory);//采购单信息
                System.out.println(brandId+"\t"+typeId+"\t"+productId+"\t"+factoryId+"\t"+numkc);//库存信息
                if(brandid==brandId && typeid==typeId && productid==productId && firmid==factoryId){
                }else{
                }
            }*/
        }
        RkWarehouse rkWarehouse=new RkWarehouse(id,state);
        int num=rkWarehouseDao.updateRkWarehouseState(rkWarehouse);
        List<RkWarehouse> listRkWarehouse=rkWarehouseDao.getRkWarehouseByuid(uId);//修改状态后查询所有
        if(listRkWarehouse.isEmpty()){
            listRkWarehouse=rkWarehouseDao.getAllRkWarehouse();
        }
        session.setAttribute("listRkWarehouse",listRkWarehouse);
        return "redirect:../../../../../storage/stock/stockList.jsp";
    }



    @RequestMapping(value = "/{id}/{state}/{uId}/{rkIndent}/updateRkWarehousestate.do",method = RequestMethod.GET)//未入库，取消入库走的方法
    public String updateRkWarehouseState(@PathVariable("id")int id, @PathVariable("state")int state, @PathVariable("uId") int uId,HttpSession session){//根据ID修改状态
        if(state==1){
            state=3;
        }
        RkWarehouse rkWarehouse=new RkWarehouse(id,state);
        int num=rkWarehouseDao.updateRkWarehouseState(rkWarehouse);
        List<RkWarehouse> listRkWarehouse=rkWarehouseDao.getRkWarehouseByuid(uId);//修改状态后查询所有
        if(listRkWarehouse.isEmpty()){
            listRkWarehouse=rkWarehouseDao.getAllRkWarehouse();
        }
        session.setAttribute("listRkWarehouse",listRkWarehouse);
        return "redirect:../../../storage/stock/stockList.jsp";
    }


    @RequestMapping(value = "/{uId}/addRkwarehouse.do",method = RequestMethod.GET)
    public String addCkwarehouse(@PathVariable("uId") int uId,HttpSession session){//添加入库前的查询
        List<Purchase> listPurchase=purchaseDao.getAllByRkwarehouse();//查询所有通过审核未出库的订单
        session.setAttribute("listPurchase",listPurchase);//后来皮筋变成了手表，十二点变成了七点，只是两个人却变成了一个人 --2020/11/15 17:00
        List<Warehouse> listWarehouse=warehouseDao.selectWarehouseByuid(uId);//添加出库时查询该用户自己管理的仓库
        if(listWarehouse.isEmpty()){
            listWarehouse=warehouseDao.getAllWarehouse();
        }
        session.setAttribute("listWarehouse",listWarehouse);
        return "redirect:../storage/stock/stockAdd.jsp";
    }

    @RequestMapping(value = "/{uId}/stockAdd.do",method = RequestMethod.POST)
    public String deliveryAdd(@PathVariable("uId") int uId,RkWarehouse rkWarehouse,String rkIndent,HttpSession session){//添加入库
        int num=rkWarehouseDao.addRkwarehouse(rkWarehouse);//添加出库的方法
        int number=purchaseDao.updatePurchaseStateByRkwarehouse(rkIndent);//入库后修改状态
        List<RkWarehouse> listRkWarehouse=rkWarehouseDao.getRkWarehouseByuid(uId);//入库后查询所有
        if(listRkWarehouse.isEmpty()){
            listRkWarehouse=rkWarehouseDao.getAllRkWarehouse();
        }
        session.setAttribute("listRkWarehouse",listRkWarehouse);
        return "redirect:../storage/stock/stockList.jsp";
    }
}
