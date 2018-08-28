package com.xyj.ems.user.controller;

import com.xyj.ems.user.bean.UserInfo;
import com.xyj.ems.user.service.UserInfoService;
import com.xyj.ems.utils.MD5Utils;
import com.xyj.ems.utils.ResultUtils;
import com.xyj.ems.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class UserInfoController {
    private final UserInfoService service;


    @Autowired
    public UserInfoController(UserInfoService service) {
        this.service = service;
    }


    @RequestMapping (value = "/login",method = RequestMethod.POST)
    public String login(@RequestParam("userName") String userName,@RequestParam("password") String password){
        try {
            if (StringUtil.isEmpty(userName)||StringUtil.isEmpty(password)){
                return ResultUtils.getErrorResult("用户名或密码不能为空");
            }
            UserInfo info = service.getUserInfoByUserName(userName);

            if (info==null){
                return ResultUtils.getErrorResult("用户不存在");
            }
            if (MD5Utils.MD5Encode(password).equals(info.getPassword())){
                return ResultUtils.getSuccessResult(info);
            }
            return ResultUtils.getErrorResult("密码错误");
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtils.getErrorResult("登陆异常");
        }
    }



    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public String register(@RequestParam Map<String,String> map){
        try {
            String userName = map.get("userName");
            String password = map.get("password");
            if (StringUtil.isEmpty(userName)||StringUtil.isEmpty(password)){
                return ResultUtils.getErrorResult("用户名密码不能为空");
            }
            UserInfo info = service.getUserInfoByUserName(userName);
            if (info!=null){
                return  ResultUtils.getErrorResult("用户已经存在");
            }
            int count = service.register(userName,password);
            if (count==-1){
                return  ResultUtils.getErrorResult("注册失败");
            }else {
                return  ResultUtils.getSuccessResult("注册成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtils.getErrorResult("注册失败");
        }

    }

}
