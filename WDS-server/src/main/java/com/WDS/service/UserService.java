package com.WDS.service;

import com.WDS.exception.UserException;
import com.WDS.pojo.User;

public interface UserService {
    //根据用户名查询用户
    User findByUserName(String username);

    //注册
    void register(String email, String password) throws UserException;

    //跟新用户信息
    void updateUserInfo(User user);

    //更新头像
    void updatePortrait(String portraitUrl);

    //更新密码
    void updatePwd(String newPwd);

    //校验客户密码
    boolean verifyPwd(String oldPwd);

    // 根据邮箱查询用户信息
    User findByEmail(String loginInfo);
}
