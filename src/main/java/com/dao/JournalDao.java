package com.dao;

import com.bean.Journal;
import com.util.Pager;

import java.util.List;
import java.util.Map;

public interface JournalDao {

    //添加日志
    public int addJournal(Journal journal);
    //查询所有并分页
    public List<Journal> getAllJournal(Pager<Journal> pager);
    //查询所有日志数量
    public int countJournal();
    //根据日志id查找日志
    public Journal getOneJournal(int jid);
    //模糊查询并分页
    public List<Journal> getJournalByCon(Pager<Journal> pager);
    //数量
    public int countJournalByCon(Map<String,Object> map);


}