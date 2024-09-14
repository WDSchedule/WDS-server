package com.WDS.service.impl;

import com.WDS.mapper.UserMapper;
import com.WDS.pojo.User;
import com.WDS.service.UserService;
import com.WDS.utils.Md5Util;
import com.WDS.utils.ThreadLoaclUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User findByUserName(String username) {
        return userMapper.findByUserName(username);
    }

    @Override
    public void register(String username, String password) {
        //加密
        String md5String = Md5Util.getMd5String(password);

        //添加
        userMapper.add(username, md5String);
    }

    @Override
    public void updateUserInfo(User user) {
        user.setUpdateTime(LocalDateTime.now());
        userMapper.update(user);
    }

    @Override
    public void updatePortrait(String portraitUrl) {
        Map<String, Object> map = ThreadLoaclUtil.get();
        int id = (int) map.get("id");
        userMapper.updatePortrait(portraitUrl, LocalDateTime.now(), id);
    }

    @Override
    public void updatePwd(String newPwd) {
        Map<String, Object> map = ThreadLoaclUtil.get();
        int id = (int) map.get("id");
        userMapper.updatePwd(Md5Util.getMd5String(newPwd), LocalDateTime.now(), id);
    }

    @Override
    public boolean verifyPwd(String oldPwd) {
        Map<String, Object> map = ThreadLoaclUtil.get();
        String username = (String) map.get("username");
        return findByUserName(username).getPassword().equals(Md5Util.getMd5String(oldPwd));
    }
}
