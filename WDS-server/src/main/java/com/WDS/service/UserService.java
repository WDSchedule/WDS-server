package com.WDS.service;

import com.WDS.pojo.Account;

public interface UserService {
    //根据用户名查询用户
    Account findByUserName(String username);

    //注册
    void register(String username, String password);
}
