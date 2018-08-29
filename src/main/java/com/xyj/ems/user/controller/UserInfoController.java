package com.xyj.ems.user.controller;

import com.xyj.ems.user.bean.UserInfo;
import com.xyj.ems.user.service.UserInfoService;
import com.xyj.ems.utils.MD5Utils;
import com.xyj.ems.utils.ResultUtils;
import com.xyj.ems.utils.StringUtil;
import com.xyj.ems.utils.TokenUUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin
public class UserInfoController {
    private final UserInfoService service;


    @Autowired
    public UserInfoController(UserInfoService service) {
        this.service = service;
    }


    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@RequestParam("userName") String userName, @RequestParam("password") String password) {
        try {
            if (StringUtil.isEmpty(userName) || StringUtil.isEmpty(password)) {
                return ResultUtils.getErrorResult("用户名或密码不能为空");
            }
            UserInfo info = service.getUserInfoByUserName(userName);

            if (info == null) {
                return ResultUtils.getErrorResult("用户不存在");
            }
            if (MD5Utils.MD5Encode(password).equals(info.getPassword())) {
                service.updateUserInfoTokenById(info.getId(), TokenUUIDUtil.getUUID());
                return ResultUtils.getSuccessResult(info);
            }
            return ResultUtils.getErrorResult("密码错误");
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtils.getErrorResult("登陆异常");
        }
    }


    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(@RequestParam Map<String, String> map) {
        try {
            String userName = map.get("userName");
            String password = map.get("password");
            if (StringUtil.isEmpty(userName) || StringUtil.isEmpty(password)) {
                return ResultUtils.getErrorResult("用户名密码不能为空");
            }
            UserInfo info = service.getUserInfoByUserName(userName);
            if (info != null) {
                return ResultUtils.getErrorResult("用户已经存在");
            }
            int count = service.register(userName, password);
            if (count == -1) {
                return ResultUtils.getErrorResult("注册失败");
            } else {
                return ResultUtils.getSuccessResult("注册成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtils.getErrorResult("注册失败");
        }

    }

    @RequestMapping(value = "/update/userInfo", method = RequestMethod.POST)
    public String updateUserInfo(@RequestParam Map<String, String> map) {
        try {
            UserInfo info = new UserInfo();
            String nickName = map.get("nickName");
            if (StringUtil.isEmpty(nickName)) {
                info.setUserName(nickName);
            }
            String realName = map.get("realName");
            if (StringUtil.isEmpty(realName)) {
                info.setUserName(realName);
            }
            String phone = map.get("phone");
            if (StringUtil.isEmpty(phone)) {
                info.setUserName(phone);
            }
            String headUrl = map.get("head_url");
            if (StringUtil.isEmpty(headUrl)) {
                info.setUserName(headUrl);
            }
            String age = map.get("age");
            if (StringUtil.isEmpty(age)) {
                info.setUserName(age);
            }
            String sex = map.get("sex");
            if (StringUtil.isEmpty(sex)) {
                info.setUserName(sex);
            }
            String idCard = map.get("id_card");
            if (StringUtil.isEmpty(idCard)) {
                info.setUserName(idCard);
            }
            String jobId = map.get("job_id");
            if (StringUtil.isEmpty(jobId)) {
                info.setUserName(jobId);
            }
            String token = map.get("token");
            if (StringUtil.isEmpty(token)) {
                info.setUserName(token);
            }
            String password = map.get("password");
            if (StringUtil.isEmpty(password)) {
                info.setUserName(MD5Utils.MD5Encode(password));
            }
            String authority = map.get("authority");
            if (StringUtil.isEmpty(authority)) {
                info.setUserName(authority);
            }
            int count = service.updateUserInfoById(info);
            if (count == -1) {
                return ResultUtils.getErrorResult("修改失败");
            } else {
                return ResultUtils.getSuccessResult("修改成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtils.getErrorResult("修改失败");
        }
    }

}
