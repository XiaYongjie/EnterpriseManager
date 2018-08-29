package com.xyj.ems.job.dao;

import com.xyj.ems.job.bean.Job;

import java.util.List;

public interface JobMapper {
    int insertJob(Job job);

    List<Job> selectAllJob();
}
