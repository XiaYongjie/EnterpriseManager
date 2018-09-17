package com.xyj.ems.common.controller;

import com.xyj.ems.common.bean.AreaBean;
import com.xyj.ems.common.bean.CityBean;
import com.xyj.ems.common.bean.ProvinceBean;
import com.xyj.ems.common.service.AddressService;
import com.xyj.ems.utils.ResultUtils;
import com.xyj.ems.utils.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class AddressController {
    private Logger logger = LoggerFactory.getLogger(AddressController.class);
    private final AddressService service;

    @Autowired
    public AddressController(AddressService service) {
        this.service = service;
    }


    @RequestMapping(value = "/addressList", method = RequestMethod.POST)
    public String getAddressList(@RequestParam Map<String, String> params) {
        try {
            String type = params.get("type");
            String id = params.get("id");
            if (StringUtil.isEmpty(type) || StringUtil.isEmpty(id)) {
                return ResultUtils.getErrorResult("参数异常");
            }
            switch (type) {
                case "1":
                    List<ProvinceBean> beans = service.getAllProvince();
                    return ResultUtils.getSuccessResult(beans);
                case "2":
                    List<CityBean> cites = service.getAllCityByProvince(Integer.parseInt(id));
                    return ResultUtils.getSuccessResult(cites);
                case "3":
                    List<AreaBean> areas = service.getAllAreaByCity(Integer.parseInt(id));
                    return ResultUtils.getSuccessResult(areas);
                default:
                    return ResultUtils.getErrorResult("参数异常");
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            logger.error("error", e);
            return ResultUtils.getErrorResult("参数异常");
        }
    }
}
