package com.WDS.mapper;

import com.WDS.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.time.LocalDateTime;

@Mapper
public interface UserMapper {
    //根据用户名查询用户
    @Select("select * from user where username = #{username}")
    User findByUserName(String username);

    //添加
    @Insert("insert into user(username, password, email, create_time, update_time)" +
            " values(#{username}, #{password}, #{email}, #{create_time}, #{update_time})")
    void add(String username, String email, String password, LocalDateTime create_time, LocalDateTime update_time);

    //更新用户信息
    @Update("update  user set nickname=#{nickname},email=#{email}, update_time=#{updateTime} where id=#{id}")
    void update(User user);

    @Update("update user set portrait=#{portraitUrl}, update_time=#{updateTime} where id=#{id}")
    void updatePortrait(String portraitUrl, LocalDateTime updateTime, int id);

    @Update("update user set password=#{md5String}, update_time=#{now} where id=#{id}")
    void updatePwd(String md5String, LocalDateTime now, int id);

    @Select("SELECT * FROM user WHERE email=#{email}")
    User findByEmail(String email);
}
