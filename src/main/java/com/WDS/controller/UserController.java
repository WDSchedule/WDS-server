package com.WDS.controller;

import com.WDS.pojo.User;
import com.WDS.pojo.Result;
import com.WDS.service.UserService;

import com.WDS.utils.JWTUtil;
import com.WDS.utils.Md5Util;
import com.WDS.utils.ThreadLoaclUtil;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.URL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/user")
@Validated
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @PostMapping("/register")
    public Result register(@Email String email, @Pattern(regexp = "^\\S{5,50}$") String password) {
        if (userService.findByEmail(email) != null) {
            return Result.error("邮箱已被占用!");
        }
        try {
            userService.register(email, password);
        }catch (Exception e) {
            return Result.error(e.getMessage());
        }

        return Result.success();
    }

    @PostMapping("/login")
    public Result<String> login(@Pattern(regexp = "^\\S{5,30}$") String loginInfo, @Pattern(regexp = "^\\S{5,50}$") String password){
        User loginUser = null;
        // 如果满足邮箱格式，则优先检查邮箱
        if (loginInfo.contains("@")){
            loginUser = userService.findByEmail(loginInfo);
        } else {
            loginUser = userService.findByUserName(loginInfo);
        }


        //判断用户是否存在
        if (loginUser == null){
            return Result.error("用户名错误");
        }

        //验证密码
        if (loginUser.getPassword().equals(Md5Util.getMd5String(password))){
            //登录成功
            Map<String, Object> claims = new HashMap<>();
            claims.put("id", loginUser.getId());
            claims.put("username", loginUser.getUsername());
            String token = JWTUtil.getToken(claims);
            //将token存储到redis
            stringRedisTemplate.opsForValue().set("User_"+ loginUser.getId(), token, 1, TimeUnit.HOURS);

            return Result.success(token);
        }
        return Result.error("密码错误");
    }

    @GetMapping("/userinfo")
    public Result<User> getUserInfo(){
        Map<String, Object> claims = ThreadLoaclUtil.get();
        String username = (String) claims.get("username");
        User user = userService.findByUserName(username);
        return Result.success(user);
    }

    @PutMapping("/update")
    public Result updateUserInfo(@RequestBody @Validated User user){
        userService.updateUserInfo(user);
        return Result.success();
    }

    @PatchMapping("/updatePortrait")
    public Result updatePortrait(@RequestParam @URL String portraitUrl){
        userService.updatePortrait(portraitUrl);
        return Result.success();
    }

    @PatchMapping("/updatePwd")
    public Result updatePwd(@RequestBody Map<String,String> params){
        //参数校验
        String oldPwd = params.get("old_pwd");
        String newPwd = params.get("new_pwd");

        if (!StringUtils.hasLength(oldPwd) || !StringUtils.hasLength(newPwd)){
            return Result.error("缺少必要参数");
        }

        //校验原密码
        if (!userService.verifyPwd(oldPwd))
        {
            return Result.error("原密码错误");
        }

        //更新密码
        userService.updatePwd(newPwd);

        // 删除redis中的token
        Map<String, Object> claims = ThreadLoaclUtil.get();
        int id = (int) claims.get("id");
        stringRedisTemplate.opsForValue().getOperations().delete("User_"+id);

        return Result.success();
    }

    @PatchMapping("updateUsername")
    public Result updateUsername(@Pattern(regexp = "^[a-z0-9]{5,30}$") String username){
        Map<String, Object> mapper = ThreadLoaclUtil.get();
        String oldUsername = (String) mapper.get("username");
        if (username.equals(oldUsername)){
            return Result.error("用户名相同!");
        }

        if (userService.findByUserName(username)!=null)
        {
            return Result.error("用户名已存在!");
        }

        userService.updateUsername(username);
        // 删除redis中的token
        Map<String, Object> claims = ThreadLoaclUtil.get();
        int id = (int) claims.get("id");
        stringRedisTemplate.opsForValue().getOperations().delete("User_"+id);

        return Result.success();
    }
}
