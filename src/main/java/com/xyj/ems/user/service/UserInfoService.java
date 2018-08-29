package com.xyj.ems.user.service;

import com.xyj.ems.user.bean.UserInfo;

public interface UserInfoService {

    int register(String userName,String password);
    UserInfo getUserInfoByUserName(String userName);
    int updateUserInfoById(UserInfo info);
}
