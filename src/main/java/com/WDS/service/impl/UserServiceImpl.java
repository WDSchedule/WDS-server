package com.WDS.service.impl;

import com.WDS.exception.UserException;
import com.WDS.mapper.UserMapper;
import com.WDS.pojo.User;
import com.WDS.service.UserService;
import com.WDS.utils.Md5Util;
import com.WDS.utils.RandomStringGeneratorUtil;
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
    public void register(String email, String password) throws UserException {
        //加密
        String md5String = Md5Util.getMd5String(password);
        //随机生成用户名
        String username = RandomStringGeneratorUtil.getRandomUsername(8);
        //检测冲突、解决冲突
        int step = 1;
        while (findByUserName(username) != null) {
            String newUsername = RandomStringGeneratorUtil.neighborString(username, step);
            if (!newUsername.equals(username)) {
                username = newUsername;
            }else if (step != -1){
                step = -1;
            }else{
                UserException e = new UserException("长度为8的用户名已全部被占用！！");
                throw e;
            }
        }

        //添加
        LocalDateTime now = LocalDateTime.now();
        userMapper.add(username, email, md5String, now, now);
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

    @Override
    public User findByEmail(String email) {
        return userMapper.findByEmail(email);
    }

    @Override
    public void updateUsername(String username){
        LocalDateTime now = LocalDateTime.now();
        Map<String, Object> map = ThreadLoaclUtil.get();
        int id = (int) map.get("id");
        userMapper.updateUsername(username, now, id);
    }
}
