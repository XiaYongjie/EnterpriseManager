package com.xyj.ems.job.controller;

import com.xyj.ems.job.service.JobService;
import com.xyj.ems.utils.ResultUtils;
import com.xyj.ems.utils.StringUtil;
import javafx.geometry.Pos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JobController {

    private final JobService service;

    @Autowired
    public JobController(JobService service) {
        this.service = service;
    }

    @RequestMapping(value = "/add/job", method = RequestMethod.POST)
    public String addJob(@RequestParam("name") String name) {
        try {
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
            return ResultUtils.getErrorResult("职位添加失败");
        }
    }

    @RequestMapping(value = "/job/selectAll", method = RequestMethod.POST)
    public String getAllJob() {
        return "";
    }
}
