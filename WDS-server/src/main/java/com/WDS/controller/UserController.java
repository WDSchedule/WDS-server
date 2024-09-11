package com.WDS.controller;

import com.WDS.pojo.Account;
import com.WDS.pojo.Result;
import com.WDS.service.UserService;

import com.WDS.utils.JWTUtil;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
@Validated
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public Result register(@Pattern(regexp = "^\\S{5,30}$") String username, @Pattern(regexp = "^\\S{5,50}$") String password) {
        //参数校验
        /**
         * Spring Validation
         * Spring 提提供的参数校验框架，使用预定义的注解完成参数校验
         * 步骤：
         * 1。引入起步依赖
         * 2. 参数前面添加Pattern注解
         * 3. 在Controller类前添加Validation注解
         */
        //查询用户
        Account acc = userService.findByUserName(username);
        if (acc == null) {
            //没有占用
            //执行注册
            userService.register(username, password);
            return Result.success();
        } else {
            //占用
            return Result.error("用户名已存在");
        }
    }

    @PostMapping("/login")
    public Result<String> login(@Pattern(regexp = "^\\S{5,30}$") String username, @Pattern(regexp = "^\\S{5,50}$") String password){
        // 根据用户名查询用户
        Account loginAcc = userService.findByUserName(username);

        //判断用户是否存在
        if (loginAcc == null){
            return Result.error("用户名错误");
        }

        //验证密码
        if (org.springframework.util.DigestUtils.md5DigestAsHex(password.getBytes()).equals(loginAcc.getPassword())){
            //登录成功
            Map<String, Object> claims = new HashMap<>();
            claims.put("id", loginAcc.getAccount_id());
            claims.put("username", loginAcc.getUsername());
            String token = JWTUtil.getToken(claims);
            return Result.success(token);
        }
        return Result.error("密码错误");
    }





}
