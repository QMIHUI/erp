package com.dao;

import com.bean.Job;
import com.util.Pager;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface JobDao {
    //查询所有职位
    public List<Job> getAllJob01();
    //查询所有职位并分页
    public List<Job> getAllJob(Pager<Job> pager);
    //查询职位个数
    public int countJob();
    //根据部门id查找职位
    public List<Job> getJobByDid(int did);
    //增加职位
    public int addJob(Job job);
    //根据职位id查看职位信息
    public Job getOneJob(int jid);
    //修改职位信息
    public int updateJob(Job job);
    //注销职位
    public int cancelJob(int jid);
    //恢复职位
    public int recoverJob(int jid);
    //模糊查询
    public List<Job> getJobByCon(Pager<Job> pager);
    //根据条件查询职位个数
    /*public int countJobByCon(@Param("jobName") String jobName,@Param("jobDeptId") int jobDeptId);*/
    public int countJobByCon(Map<String,Object> map);

}