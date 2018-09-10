package com.xyj.ems.common.controller;

import com.xyj.ems.common.bean.AreaBean;
import com.xyj.ems.common.bean.CityBean;
import com.xyj.ems.common.bean.ProvinceBean;
import com.xyj.ems.common.service.AddressService;
import com.xyj.ems.utils.ResultUtils;
import com.xyj.ems.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AddressController {
    private final AddressService service;

    @Autowired
    public AddressController(AddressService service) {
        this.service = service;
    }


    @RequestMapping(value = "/addressList", method = RequestMethod.POST)
    public String getAddressList(@RequestParam("type") String type, @RequestParam("id") String id) {
        try {
            if (StringUtil.isEmpty(type) || StringUtil.isEmpty(id)) {
                return ResultUtils.getErrorResult("参数异常");
            }
            switch (type) {
                case "1":
                    List<ProvinceBean> beans = service.getAllProvince();
                    return ResultUtils.getSuccessResult(beans);
                case "2":
                    List<CityBean> citys = service.getAllCityByProvince(Integer.parseInt(id));
                    return ResultUtils.getSuccessResult(citys);
                case "3":
                    List<AreaBean> areas = service.getAllAreaByCity(Integer.parseInt(id));
                    return ResultUtils.getSuccessResult(areas);
                default:
                    return ResultUtils.getErrorResult("参数异常");
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return ResultUtils.getErrorResult("参数异常");
        }
    }
}
