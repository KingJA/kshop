package com.kshop.dao;

import com.kshop.pojo.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
    int checkUsername(@Param("username") String username);
    User selectUser(@Param("username") String username,@Param("password") String password);
}