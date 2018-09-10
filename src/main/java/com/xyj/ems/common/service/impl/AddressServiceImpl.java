package com.xyj.ems.common.service.impl;

import com.xyj.ems.common.bean.AreaBean;
import com.xyj.ems.common.bean.CityBean;
import com.xyj.ems.common.bean.ProvinceBean;
import com.xyj.ems.common.dao.AddressMapper;
import com.xyj.ems.common.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {

    private final AddressMapper mapper;

    @Autowired
    public AddressServiceImpl(AddressMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public List<ProvinceBean> getAllProvince() {
        return mapper.getAllProvince();
    }

    @Override
    public List<CityBean> getAllCityByProvince(int id) {
        ProvinceBean bean = new ProvinceBean();
        bean.setId(id);
        return mapper.getAllCityByProvince(bean);
    }

    @Override
    public List<AreaBean> getAllAreaByCity(int id) {
        CityBean bean = new CityBean();
        bean.setId(id);
        return mapper.getAllAreaByCity(bean);
    }
}
