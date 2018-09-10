package com.xyj.ems.common.service;

import com.xyj.ems.common.bean.AreaBean;
import com.xyj.ems.common.bean.CityBean;
import com.xyj.ems.common.bean.ProvinceBean;

import java.util.List;

public interface AddressService {

    List<ProvinceBean> getAllProvince();

    List<CityBean> getAllCityByProvince(int id);

    List<AreaBean> getAllAreaByCity(int id);
}
