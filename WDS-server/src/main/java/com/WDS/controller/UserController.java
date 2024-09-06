package com.WDS.controller;

import com.WDS.pojo.Account;
import com.WDS.pojo.Result;
import com.WDS.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public Result register(String username, String password) {
        //查询用户
        Account acc = userService.findByUserName(username);
        if (acc == null)
        {
            //没有占用
            //执行注册
            userService.register(username, password);
            return Result.success();
        }else{
            //占用
            return Result.error("用户名已存在");
        }
        //注册
    }
}
