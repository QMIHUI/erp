package com.dao;

import com.bean.Job;

import java.util.List;

public interface JobDao {
    //查询所有职位
    public List<Job> getAllJob();

}