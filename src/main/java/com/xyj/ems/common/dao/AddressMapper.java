package com.xyj.ems.common.dao;

import com.xyj.ems.common.bean.AreaBean;
import com.xyj.ems.common.bean.CityBean;
import com.xyj.ems.common.bean.ProvinceBean;

import java.util.List;

public interface AddressMapper {


    List<ProvinceBean> getAllProvince();

    List<CityBean> getAllCityByProvince(ProvinceBean bean);

    List<AreaBean> getAllAreaByCity(CityBean bean);
}
