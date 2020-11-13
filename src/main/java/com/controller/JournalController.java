package com.controller;

import com.bean.Journal;
import com.dao.JournalDao;
import com.util.Pager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author HUI
 * @create 2020-11-09 11:06
 */
@Controller
public class JournalController {
    @Autowired
    private JournalDao journalDao;

    @RequestMapping(value = "queryAllJournal.do",method = RequestMethod.GET)
    public String queryAllJournal(HttpServletRequest request){
        System.out.println("执行查询所有日志！！！");
        int countJournal = journalDao.countJournal();
        System.out.println("数量为:"+countJournal);
        int size = 5;
        int rowJournal = countJournal % size == 0 ? (countJournal / size) : (countJournal / size + 1);
        System.out.println(rowJournal);
        String currentIndex= request.getParameter("pageIndex");
        //第一次访问(当前页码=1)
        int pageIndex = 1;
        if(currentIndex!=null) {
            pageIndex=Integer.parseInt(currentIndex);
        }
        if(currentIndex==null || Integer.parseInt(currentIndex) <= 0){
            pageIndex = 1;
        }else if(Integer.parseInt(currentIndex) >= rowJournal){
            pageIndex=rowJournal;
        }
        if (pageIndex==0){
            pageIndex=1;
        }
        Pager<Journal> pager = new Pager<>();
        pager.setPage((pageIndex-1)*size);
        pager.setSize(size);
        pager.setTotal(countJournal);
        List<Journal> listJournal = journalDao.getAllJournal(pager);
        request.getSession().setAttribute("listJournal",listJournal);
        request.getSession().setAttribute("countJournal",countJournal);
        request.getSession().setAttribute("rowJournal",rowJournal);
        request.getSession().setAttribute("pageIndex",pageIndex);
        return "sys/logs/logList";
    }

    @RequestMapping(value = "getOneJournal.do",method = RequestMethod.GET)
    public String getOneJournal(HttpServletRequest request){
        System.out.println("执行查找单个日志！！！");
        int jId = Integer.parseInt(request.getParameter("jId"));
        Journal journal = journalDao.getOneJournal(jId);
        request.getSession().setAttribute("journal",journal);
        return "sys/logs/logView";
    }

    @RequestMapping(value = "getJournalByCon.do",method = RequestMethod.GET)
    public String getJournalByCon(HttpServletRequest request){
        System.out.println("执行模糊查询");
        String uname = request.getParameter("uname");
        String jcontent = request.getParameter("jcontent");
        String startDate = request.getParameter("startDate");
        String enddate = request.getParameter("enddate");
        System.out.println(uname+"....."+jcontent+"....."+startDate+"....."+enddate);
        Map<String,Object> map = new HashMap<>();
        map.put("bname",uname);
        map.put("jcontent",jcontent);
        map.put("startDate",startDate);
        map.put("endDate",enddate);
        int countJoByCon = journalDao.countJournalByCon(map);
        System.out.println(countJoByCon);
        int size = 5;
        int rowJournalByCon = countJoByCon % size == 0 ? (countJoByCon / size) : (countJoByCon / size + 1);
        System.out.println(rowJournalByCon);
        String currentIndex= request.getParameter("pageIndex");
        //第一次访问(当前页码=1)
        int pageIndex = 1;
        if(currentIndex!=null) {
            pageIndex=Integer.parseInt(currentIndex);
        }
        if(currentIndex==null || Integer.parseInt(currentIndex) <= 0){
            pageIndex = 1;
        }else if(Integer.parseInt(currentIndex) >= rowJournalByCon){
            pageIndex=rowJournalByCon;
        }
        if (pageIndex==0){
            pageIndex=1;
        }
        Pager<Journal> pager = new Pager<>();
        pager.setPage((pageIndex-1)*size);
        pager.setSize(size);
        pager.setTotal(countJoByCon);
        pager.setBname(uname);
        pager.setJcontent(jcontent);
        pager.setStartDate(startDate);
        pager.setEndDate(enddate);
        List<Journal> listJournalByCon = journalDao.getJournalByCon(pager);
        request.getSession().setAttribute("listJournalByCon",listJournalByCon);
        request.getSession().setAttribute("countJoByCon",countJoByCon);
        request.getSession().setAttribute("rowJournalByCon",rowJournalByCon);
        request.getSession().setAttribute("pageIndex",pageIndex);
        request.getSession().setAttribute("uname",uname);
        request.getSession().setAttribute("jcontent",jcontent);
        request.getSession().setAttribute("startDate",startDate);
        request.getSession().setAttribute("enddate",enddate);
        return "sys/logs/logListByCon";
    }




}
