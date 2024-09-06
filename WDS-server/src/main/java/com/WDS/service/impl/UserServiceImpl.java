package com.WDS.service.impl;

import com.WDS.mapper.UserMapper;
import com.WDS.pojo.Account;
import com.WDS.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public Account findByUserName(String username) {
        return userMapper.findByUserName(username);
    }

    @Override
    public void register(String username, String password) {
        //加密
        String md5String = org.springframework.util.DigestUtils.md5DigestAsHex(password.getBytes());

        //添加
        userMapper.add(username, md5String);
    }
}
