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

    User selectUser(@Param("username") String username, @Param("password") String password);

    String selectForgetQuestion(@Param("username")String username);

    int checkForgetAnswer(@Param("username")String username, @Param("question")String question, @Param("answer")String answer);

    int updatePassword(@Param("username")String username, @Param("password")String password);

    int checkPassword(@Param("password")String password, @Param("id")Integer id);

    int checkEmailById(@Param("id")Integer id, @Param("email")String email);
}