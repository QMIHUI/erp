package com.bean;

import java.io.Serializable;

/**
 * job
 * @author 
 */
public class Job implements Serializable {
    private Integer jobId;

    private String jobName;

    private String jobState;

    private Integer jobDeptId;

    private Dept dept;

    public Dept getDept() {
        return dept;
    }

    public void setDept(Dept dept) {
        this.dept = dept;
    }

    private static final long serialVersionUID = 1L;

    public Integer getJobId() {
        return jobId;
    }

    public void setJobId(Integer jobId) {
        this.jobId = jobId;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getJobState() {
        return jobState;
    }

    public void setJobState(String jobState) {
        this.jobState = jobState;
    }

    public Integer getJobDeptId() {
        return jobDeptId;
    }

    public void setJobDeptId(Integer jobDeptId) {
        this.jobDeptId = jobDeptId;
    }


    @Override
    public String toString() {
        return "Job{" +
                "jobId=" + jobId +
                ", jobName='" + jobName + '\'' +
                ", jobState='" + jobState + '\'' +
                ", jobDeptId=" + jobDeptId +
                '}';
    }

    public Job() {
    }

    public Job(String jobName, Integer jobDeptId) {
        this.jobName = jobName;
        this.jobDeptId = jobDeptId;
    }

    public Job(Integer jobId, String jobName, Integer jobDeptId) {
        this.jobId = jobId;
        this.jobName = jobName;
        this.jobDeptId = jobDeptId;
    }
}