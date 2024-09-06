package com.WDS.mapper;

import com.WDS.pojo.Account;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    //根据用户名查询用户
    @Select("select * from account where username = #{username}")
    Account findByUserName(String username);

    //添加
    @Insert("insert into account(username, password, create_time, update_time)" +
            " values(#{username}, #{password}, now(), now())")
    void add(String username, String password);
}
