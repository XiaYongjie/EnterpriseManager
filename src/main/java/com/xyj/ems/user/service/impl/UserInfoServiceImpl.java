package com.xyj.ems.user.service.impl;

import com.xyj.ems.user.bean.UserInfo;
import com.xyj.ems.user.dao.UserInfoMapper;
import com.xyj.ems.user.service.UserInfoService;
import com.xyj.ems.utils.MD5Utils;
import com.xyj.ems.utils.TokenUUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserInfoServiceImpl implements UserInfoService {

    private final UserInfoMapper mapper;

    @Autowired
    public UserInfoServiceImpl(UserInfoMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public int register(String userName, String password) {
        UserInfo info =new UserInfo();
        info.setUserName(userName);
        info.setPassword(MD5Utils.MD5Encode(password));
        info.setToken(TokenUUIDUtil.getUUID());
        info.setAuthority(1);
        info.setJobId(4);
        return  mapper.insertUser(info);
    }

    @Override
    public UserInfo getUserInfoByUserName(String userName) {
        UserInfo info =new UserInfo();
        info.setUserName(userName);
        return mapper.getUserInfoByPhone(info);
    }
}
