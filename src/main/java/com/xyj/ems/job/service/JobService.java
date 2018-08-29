package com.xyj.ems.job.service;

import com.xyj.ems.job.bean.Job;

import java.util.List;

public interface JobService {
    int addJob(String name);

    List<Job> getAllJob();
}
