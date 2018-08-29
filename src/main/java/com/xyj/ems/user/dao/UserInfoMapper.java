package com.xyj.ems.user.dao;

import com.xyj.ems.user.bean.UserInfo;

public interface UserInfoMapper {
    int insertUser(UserInfo userInfo);
    UserInfo getUserInfoByPhone(UserInfo info);
    UserInfo getUserInfoById(UserInfo info);
    int updateUserInfoById(UserInfo info);
}
