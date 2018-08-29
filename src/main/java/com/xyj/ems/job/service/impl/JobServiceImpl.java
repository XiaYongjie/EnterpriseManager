package com.xyj.ems.job.service.impl;

import com.xyj.ems.job.bean.Job;
import com.xyj.ems.job.dao.JobMapper;
import com.xyj.ems.job.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobServiceImpl implements JobService {
    private final JobMapper mapper;

    @Autowired
    public JobServiceImpl(JobMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public int addJob(String name) {
        Job job = new Job();
        job.setName(name);
        return mapper.insertJob(job);
    }

    @Override
    public List<Job> getAllJob() {
        return mapper.selectAllJob();
    }

}
