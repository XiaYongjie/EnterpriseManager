package com.xyj.ems.job.controller;

import com.xyj.ems.job.service.JobService;
import com.xyj.ems.utils.ResultUtils;
import com.xyj.ems.utils.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin
public class JobController {
    private Logger logger = LoggerFactory.getLogger(JobController.class);
    private final JobService service;

    @Autowired
    public JobController(JobService service) {
        this.service = service;
    }

    @RequestMapping(value = "/add/job", method = RequestMethod.POST)
    public String addJob(@RequestParam Map<String,String> params) {
        try {
            String name = params.get("name");
            if (StringUtil.isEmpty(name)) {
                return ResultUtils.getErrorResult("职位名称不能为空");
            }
            int count = service.addJob(name);
            if (count == -1) {
                return ResultUtils.getErrorResult("职位添加失败");
            } else {
                return ResultUtils.getSuccessResult("职位添加成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("error",e);
            return ResultUtils.getErrorResult("职位添加失败");
        }
    }

    @RequestMapping(value = "/job/selectAll", method = RequestMethod.POST)
    public String getAllJob() {
        return "";
    }
}
