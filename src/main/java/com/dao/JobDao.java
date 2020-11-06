package com.dao;

import com.bean.Job;
import com.util.Pager;

import java.util.List;

public interface JobDao {
    //查询所有职位
    public List<Job> getAllJob01();
    public List<Job> getAllJob(Pager<Job> pager);
    //查询职位个数
    public int countJob();

    public List<Job> getJobByDid(int did);

}